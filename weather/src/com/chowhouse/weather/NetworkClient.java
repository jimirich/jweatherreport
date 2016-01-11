package com.chowhouse.weather;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkClient implements VantagePro2Client {

	private String hostName = "172.16.1.8";
	private int portNumber = 5000;
	private Socket socket = null;

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public void read()
	throws UnknownHostException, IOException {
		socket = new Socket(hostName, portNumber);

		try (DataOutputStream out = new DataOutputStream(
				socket.getOutputStream());
				//DataInputStream in = new DataInputStream(
						//socket.getInputStream());
				InputStream in = socket.getInputStream();
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(
						System.in))) {
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

			System.out.format("received: %h%h\n", buffer[0], buffer[1]);
			timer.cancel();

			/* All commands must be in upper case and must end with '\n'.
			 * 
			 */
			out.writeBytes("TEST\n");
			len = in.read(buffer, 0, 7);

			if (len < 7) {
				len = in.read(buffer, 1, 6);
			}

			System.out.format("received: %h%h%h%h%h%h%h\n", buffer[0], buffer[1],
					buffer[2], buffer[3], buffer[4], buffer[5], buffer[6]);
			System.out.format("received: %h\n", in.read());
			System.out.format("received: %h\n", in.read());

			//while (in.available() > 0) {
				//System.out.println("received: " + in.readChar());
			//}

			out.writeBytes("NVER\n");
			//System.out.format("received: %h\n", in.readChar());
			System.out.format("received: %h\n", in.read());

			//while (in.available() > 0) {
				//System.out.println("received: " + in.readChar());
			//}
		} finally {
			socket.close();
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

	public NetworkClient() {
	}

	public static void main(String[] args) throws IOException {
		NetworkClient client = new NetworkClient();
		client.setHostName(args[0]);

		try {
			client.setPortNumber(Integer.parseInt(args[1]));
		} catch (NumberFormatException e) {
			System.out.println("Unknown port " + args[0]);
		}

		/*
		try (Socket socket = new Socket(args[0], Integer.parseInt(args[1]));
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						true);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(
						System.in))) {
			String userInput;
			while ((userInput = stdIn.readLine()) != null) {
				out.println("TEST");
				System.out.println("echo: " + in.readLine());
			}
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + args[0]);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
					args[0]);
			System.exit(1);
		}
		*/

		try {
			client.read();
		} catch (UnknownHostException e) {
			System.out.println("Unknown host " + args[0]);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}

	@Override
	public void connect(String host, int port) throws IOException {
		// TODO Auto-generated method stub
		
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
	public void close()
	throws IOException {
		if (socket != null) {
			socket.close();
		}

		socket = null;
	}
}
