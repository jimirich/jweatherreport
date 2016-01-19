/*
 * Copyright 2016 James Rich
 *
 * jweatherreport is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jweatherreport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jweatherreport; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.chowhouse.jweatherreport.station;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class NetworkClient implements VantagePro2Client {

	private final AtomicBoolean connected = new AtomicBoolean(false);
	private final String hostName;
	private InputStream in = null;
	private DataOutputStream out = null;
	private final int portNumber;
	private Socket socket = null;

	public NetworkClient(final String hostName, final int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	public NetworkClient(final InetAddress address, final int portNumber) {
		this.hostName = address.getHostAddress();
		this.portNumber = portNumber;
	}

	@Override
	public void close()
	throws IOException {
		connected.set(false);
		synchronized (this) {
			safeClose(in);
			safeClose(out);
			safeClose(socket);
		}
	}

	@Override
	public void connect()
	throws IOException {
		if (connected.compareAndSet(false, true)) {
			final InputStream in;
			final DataOutputStream out;
			synchronized (this) {
				socket = new Socket(hostName, portNumber);
				out = this.out = new DataOutputStream(socket.getOutputStream());
				in = this.in = socket.getInputStream();
			}

			byte[] buffer = new byte[512];
			int len;
			/* Vantage Pro2 console wake up procedure:
			 *
			 * 1.  Send a Line Feed character, '\n' (decimal 10, hex 0x0A).
			 * 2.  Listen for a returned response of Line Feed and Carriage
			 *     Return character, ('\n\r').
			 * 3.  If there is no response within a reasonable interval (say 1.2
			 *     seconds), then try steps 1 and 2 again up to a total of 3
			 *     attempts.
			 * 4.  If the console has not woken up after 3 attempts, then signal
			 *     a connection error.
			 */
			Timer timer = new Timer();
			TimerTask task = new WakeUpTask(out, 3);
			timer.schedule(task, 0, 1200);
			len = in.read(buffer, 0, 2);

			if (len < 2) {
				len = in.read(buffer, 1, 1);
			}

			timer.cancel();
		}
	}

	@Override
	public boolean isConnected() {
		return connected.get() && socket != null && socket.isConnected();
	}

	@Override
	public synchronized <T> T execute(Command<T> command)
	throws IOException {
		out.writeBytes(command.getCommand());
		out.writeChar('\n');
		return command.execute(in);
	}

	@Override
	public boolean testConnection()
	throws IOException {
		return execute(StandardCommands.TEST_CONNECTION);
	}

	private static void safeClose(final Closeable closeable) {
		if (closeable != null) try {
			closeable.close();
		} catch (Exception ignore) {
		}
	}

	/**
	 * Class to implement the wake timer task.
	 */
	public class WakeUpTask extends TimerTask {

		private int attempt = 1;
		private DataOutputStream out;
		private int tries = 1;

		public WakeUpTask(DataOutputStream out, int tries) {
			this.out = out;
			this.tries = tries;
		}

		public void run() {
			if (attempt > tries) {
				throw new RuntimeException("Connection failed");
			}

			try {
				out.writeInt(10); // send line feed in decimal
				System.out.println("sent wake up signal...");
				attempt++;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
