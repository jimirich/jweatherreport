package com.chowhouse.test;

import java.io.IOException;
import com.chowhouse.weather.VantagePro2Client;

public class Client {


	public static void main(String[] args)
	throws IOException {
		final VantagePro2Client client = VantagePro2Client.of(args[0],
				Integer.parseInt(args[1]));
		client.connect();

		if (client.testConnection()) {
			System.out.println("Testing successful");
		} else {
			System.out.println("Test failed");
		}

		System.out.println("Firmware version " + client.getFirmwareVersion());
		System.out.println("Firmware date " + client.getFirmwareDate());
		client.close();
	}
}
