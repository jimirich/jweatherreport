package com.chowhouse.test;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.chowhouse.weather.HighLow;
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
		System.out.println("Current time " + client.getTime().format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
		HighLow highlow = client.getHighsLows();
		System.out.println("Day high bar " + highlow.getDayHighBarometer());
		System.out.println("Day low bar " + highlow.getDayLowBarometer());
		System.out.println("Month high bar " + highlow.getMonthHighBarometer());
		System.out.println("Month low bar " + highlow.getMonthLowBarometer());
		System.out.println("Year high bar " + highlow.getYearHighBarometer());
		System.out.println("Year low bar " + highlow.getYearLowBarometer());
		System.out.println("Day high wind " + highlow.getDayHighWindSpeed());
		System.out.println("Month high wind " + highlow.getMonthHighWindSpeed());
		System.out.println("Year high wind " + highlow.getYearHighWindSpeed());
		client.close();
	}
}
