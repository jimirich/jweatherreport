package com.chowhouse.weather;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

public class VantagePro2Client {

	private String hostName = "172.16.1.8";
	private int portNumber = 5000;

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public void read()
	throws UnknownHostException, IOException {
		try (Socket socket = new Socket(hostName, portNumber);
				DataOutputStream out = new DataOutputStream(
						socket.getOutputStream());
				//DataInputStream in = new DataInputStream(
						//socket.getInputStream());
				//Reader in = new InputStreamReader(socket.getInputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(
						System.in))) {
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
			if (socket.isConnected()) {
				System.out.println("Connected");
			} else {
				System.out.println("Not connected");
				return;
			}

			Timer timer = new Timer();
			TimerTask task = new WakeUpTask(out, 3);
			timer.schedule(task, 0, 1200);
			//System.out.format("received: %h\n", in.readChar());
			System.out.format("received: %h\n", in.read());

			//while (in.available() > 0) {
				//System.out.println("received: " + in.readChar());
			//}

			timer.cancel();
			out.writeBytes("TEST\n");
			//System.out.format("received: %h\n", in.readChar());
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

	public VantagePro2Client() {
	}

	public static void main(String[] args) throws IOException {
		VantagePro2Client client = new VantagePro2Client();
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
}
