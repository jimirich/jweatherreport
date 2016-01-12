package com.chowhouse.weather;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public class NetworkClient implements VantagePro2Client {

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
		in.close();
		out.close();
		socket.close();
	}

	@Override
	public void connect()
	throws IOException {
		socket = new Socket(hostName, portNumber);
		out = new DataOutputStream(socket.getOutputStream());
		in = socket.getInputStream();

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
		//System.out.format("Read %d bytes\n", len);

		if (len < 2) {
			len = in.read(buffer, 1, 1);
			//System.out.format("Read %d bytes\n", len);
		}

		//System.out.format("received: %h%h\n", buffer[0], buffer[1]);
		//System.out.println("Awake");
		timer.cancel();
	}

	@Override
	public boolean isConnected() {
		if (socket != null) {
			return socket.isConnected();
		} else {
			return false;
		}
	}

	@Override
	public byte[] sendCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testConnection()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;

		/* The TEST command response appears to be \n\rTEST\n\r and not just
		 * TEST\n as stated in the documentation.
		 */
		out.write((new String("TEST\n")).getBytes());
		len = in.read(buffer, 0, 8);
		//System.out.format("Read %d bytes\n", len);

		while (len < 8) {
			len += in.read(buffer, len, 8 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'T') && (buffer[3] == 'E') &&(buffer[4] == 'S') &&
				(buffer[5] == 'T')) {
			return true;
		}

		return false;
	}

	@Override
	public String getFirmwareDate()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("VER\n")).getBytes());
		// get the first 6 bytes which should be \n\rOK\n\r
		len = in.read(buffer, 0, 6);
		//System.out.format("Read %d bytes\n", len);

		while (len < 6) {
			len += in.read(buffer, len, 6 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'O') && (buffer[3] == 'K')) {
			//System.out.println("Retrieving firmware date...");
		} else {
			return "unavailable";
		}

		len = 0;

		for (int i = 0; i < 512; i++) {
			len += in.read(buffer, i, 1);

			if (buffer[i] == '\n') {
				break;
			}
		}

		// retrieve the trailing \r
		in.read();
		return new String(buffer, 0, len - 1);
	}

	@Override
	public String getFirmwareVersion()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("NVER\n")).getBytes());
		// get the first 6 bytes which should be \n\rOK\n\r
		len = in.read(buffer, 0, 6);
		//System.out.format("Read %d bytes\n", len);

		while (len < 6) {
			len += in.read(buffer, len, 6 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'O') && (buffer[3] == 'K')) {
			//System.out.println("Retrieving firmware version...");
		} else {
			return "unavailable";
		}

		len = 0;

		for (int i = 0; i < 512; i++) {
			len += in.read(buffer, i, 1);

			if (buffer[i] == '\n') {
				break;
			}
		}

		// retrieve the trailing \r
		in.read();
		return new String(buffer, 0, len - 1);
	}

	@Override
	public String getTime()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("GETTIME\n")).getBytes());
		// get the first byte which should be ACK (0x06)
		len = in.read(buffer, 0, 1);
		//System.out.format("Read %d bytes\n", len);

		if ((buffer[0] == 0x06)) {
			System.out.println("Retrieving time...");
		} else {
			return "unavailable";
		}

		len = 0;
		Checksum checksum = new CRC32();

		for (int i = 0; i < 512; i++) {
			len += in.read(buffer, i, 1);
			System.out.format("Read %d bytes\n", len);
			/*
			checksum.update(buffer, 0, len);
			long checksumValue = checksum.getValue();
			System.out.format("Checksum:  %d\n", checksumValue);

			if (checksumValue == 0) {
				break;
			}
			*/

			int crc = 0;
			crc = crc_table[(crc >> 8) ^ buffer[i]] ^ (crc << 8);
			System.out.format("CRC: %d\n", crc);

			if (crc == 0) {
				break;
			}
		}

		// retrieve the trailing \r
		in.read();
		return new String(buffer, 0, len - 1);
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
