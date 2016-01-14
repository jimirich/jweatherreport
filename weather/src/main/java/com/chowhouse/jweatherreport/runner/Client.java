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

package com.chowhouse.jweatherreport.runner;

import com.chowhouse.jweatherreport.station.Loop2;
import com.chowhouse.jweatherreport.station.NetworkClient;
import com.chowhouse.jweatherreport.station.StandardCommands;
import com.chowhouse.jweatherreport.station.VantagePro2Client;
import com.chowhouse.jweatherreport.wunderground.Uploader;
import java.io.Closeable;
import java.io.IOException;
import java.net.ConnectException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Client implements Runnable, Closeable {
	private static final AtomicBoolean ERROR = new AtomicBoolean(false);
	private final VantagePro2Client client;

	public Client(final String hostname, final int port) {
		client = new NetworkClient(hostname, port);
	}

	public static void main(String[] args)
			throws Exception {

		// Process the arguments
		if (args == null || args.length < 2) {
			printUsage();
			System.exit(1);
		}

		final String hostname = args[0];
		int port = 0;
		int period = 30;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException ignore) {
			System.err.printf("Invalid port %s%n", args[1]);
			printUsage();
			System.exit(1);
		}
		if (args.length > 2) {
			try {
				period = Integer.parseInt(args[2]);
			} catch (NumberFormatException ignore) {
				System.err.printf("Invalid period %s%n", args[2]);
				printUsage();
				System.exit(1);
			}
		}

		// Setup a scheduled task
		final ScheduledExecutorService service = configureExecutor();

		try (final Client client = new Client(hostname, port)) {
			client.client.connect();
			service.scheduleAtFixedRate(client, 0, period, TimeUnit.SECONDS);
			while (!ERROR.get()) {
				TimeUnit.MILLISECONDS.sleep(20L);
			}
		} finally {
			service.shutdown();
			try {
				service.awaitTermination(5, TimeUnit.SECONDS);
			} catch (InterruptedException ignore) {
				service.shutdownNow();
			}
		}

	}

	@Override
	public void run() {
		try {
			if (client.testConnection()) {
				System.out.println("Testing successful");
			} else {
				System.out.println("Test failed");
			}

			System.out.println("Firmware version " + client.execute(StandardCommands.FIRMWARE_VERSION));
			System.out.println("Firmware date " + client.execute(StandardCommands.FIRMWARE_DATE));
			System.out.println("Current time " + client.execute(StandardCommands.TIME).format(
					DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

		/*
		HighLow highlow = client.execute(StandardCommands.HIGH_LOW);
		System.out.println("Day high bar " + highlow.getDayHighBarometer());
		System.out.println("Day high bar time " +
				highlow.getTimeOfDayHighBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Day low bar " + highlow.getDayLowBarometer());
		System.out.println("Day low bar time " +
				highlow.getTimeOfDayLowBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high bar " + highlow.getMonthHighBarometer());
		System.out.println("Month low bar " + highlow.getMonthLowBarometer());
		System.out.println("Year high bar " + highlow.getYearHighBarometer());
		System.out.println("Year low bar " + highlow.getYearLowBarometer());

		System.out.println("Day high wind " + highlow.getDayHighWindSpeed());
		System.out.println("Day high wind time " +
				highlow.getTimeOfDayHighWindSpeed().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high wind " + highlow.getMonthHighWindSpeed());
		System.out.println("Year high wind " + highlow.getYearHighWindSpeed());

		System.out.println("Day high inside temperature " +
				highlow.getDayHighInsideTemperature());
		System.out.println("Day high inside temperature time " +
				highlow.getTimeOfDayHighInsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Day low inside temperature " +
				highlow.getDayLowInsideTemperature());
		System.out.println("Day low inside temperature time " +
				highlow.getTimeOfDayLowInsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high inside temperature " +
				highlow.getMonthHighInsideTemperature());
		System.out.println("Month low inside temperature " +
				highlow.getMonthLowInsideTemperature());
		System.out.println("Year high inside temperature " +
				highlow.getYearHighInsideTemperature());
		System.out.println("Year low inside temperature " +
				highlow.getYearLowInsideTemperature());

		System.out.println("Day high outside temperature " +
				highlow.getDayHighOutsideTemperature());
		System.out.println("Day high outside temperature time " +
				highlow.getTimeOfDayHighOutsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Day low outside temperature " +
				highlow.getDayLowOutsideTemperature());
		System.out.println("Day low outside temperature time " +
				highlow.getTimeOfDayLowOutsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high outside temperature " +
				highlow.getMonthHighOutsideTemperature());
		System.out.println("Month low outside temperature " +
				highlow.getMonthLowOutsideTemperature());
		System.out.println("Year high outside temperature " +
				highlow.getYearHighOutsideTemperature());
		System.out.println("Year low outside temperature " +
				highlow.getYearLowOutsideTemperature());

		System.out.println("Day high inside humidity " +
				highlow.getDayHighInsideHumidity());
		System.out.println("Day high inside humidity time " +
				highlow.getTimeOfDayHighInsideHumidity().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Day low inside humidity " +
				highlow.getDayLowInsideHumidity());
		System.out.println("Day low inside humidity time " +
				highlow.getTimeOfDayLowInsideHumidity().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high inside humidity " +
				highlow.getMonthHighInsideHumidity());
		System.out.println("Month low inside humidity " +
				highlow.getMonthLowInsideHumidity());
		System.out.println("Year high inside humidty " +
				highlow.getYearHighInsideHumidity());
		System.out.println("Year low inside humidty " +
				highlow.getYearLowInsideHumidity());

		System.out.println("Day high dew point " +
				highlow.getDayHighDewPoint());
		System.out.println("Day high dew point time " +
				highlow.getTimeOfDayHighDewPoint().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Day low dew point " +
				highlow.getDayLowDewPoint());
		System.out.println("Day low dew point time " +
				highlow.getTimeOfDayLowDewPoint().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high dew point " +
				highlow.getMonthHighDewPoint());
		System.out.println("Month low dew point " +
				highlow.getMonthLowDewPoint());
		System.out.println("Year high dew point " +
				highlow.getYearHighDewPoint());
		System.out.println("Year low dew point " +
				highlow.getYearLowDewPoint());

		System.out.println("Day low wind chill " +
				highlow.getDayLowWindChill());
		System.out.println("Day low wind chill time " +
				highlow.getTimeOfDayLowWindChill().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month low wind chill " +
				highlow.getMonthLowWindChill());
		System.out.println("Year low wind chill " +
				highlow.getYearLowWindChill());

		System.out.println("Day high heat index " +
				highlow.getDayHighHeatIndex());
		System.out.println("Day high heat index time " +
				highlow.getTimeOfDayHighHeatIndex().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("Month high heat index " +
				highlow.getMonthHighHeatIndex());
		System.out.println("Year high heat index " +
				highlow.getYearHighHeatIndex());

		if (highlow.getTimeOfDayHighTHSWIndex() != null) {
			System.out.println("Day high THSW index " +
					highlow.getDayHighTHSWIndex());
			System.out.println("Day high THSW index time " +
					highlow.getTimeOfDayHighTHSWIndex().format(
							DateTimeFormatter.ofPattern("hh:mm")));
			System.out.println("Month high THSW index " +
					highlow.getMonthHighTHSWIndex());
			System.out.println("Year high THSW index " +
					highlow.getYearHighTHSWIndex());
		}

		if (highlow.getTimeOfDayHighSolarRadiation() != null) {
			System.out.println("Day high solar radiation " +
					highlow.getDayHighSolarRadiation());
			System.out.println("Day high solar radiation time " +
					highlow.getTimeOfDayHighSolarRadiation().format(
							DateTimeFormatter.ofPattern("hh:mm")));
			System.out.println("Month high solar radiation " +
					highlow.getMonthHighSolarRadiation());
			System.out.println("Year high solar radiation " +
					highlow.getYearHighSolarRadiation());
		}

		if (highlow.getTimeOfDayHighUltraViolet() != null) {
			System.out.println("Day high UV " +
					highlow.getDayHighUltraViolet());
			System.out.println("Day high UV time " +
					highlow.getTimeOfDayHighUltraViolet().format(
							DateTimeFormatter.ofPattern("hh:mm")));
			System.out.println("Month high UV " +
					highlow.getMonthHighUltraViolet());
			System.out.println("Year high UV " +
					highlow.getYearHighUltraViolet());
		}

		System.out.println("Day high rain rate " +
				highlow.getDayHighRainRate());

		if (highlow.getTimeOfDayHighRainRate() != null) {
			System.out.println("Day high rain rate time " +
					highlow.getTimeOfDayHighRainRate().format(
							DateTimeFormatter.ofPattern("hh:mm")));
		}

		System.out.println("Month high rain rate " +
				highlow.getMonthHighRainRate());
		System.out.println("Year high rain rate " +
				highlow.getYearHighRainRate());

		Loop loop = client.getLoop(1);
		System.out.println("Current outside temperature " +
				loop.getOutsideTemperature());
		System.out.println("Current inside temperature " +
				loop.getInsideTemperature());
		System.out.println("Current outside humidity " +
				loop.getOutsideHumidity());
		System.out.println("Current inside humidity " +
				loop.getInsideHumidity());
		System.out.println("Current barometric pressure " +
				loop.getBarometricPressure());
		System.out.println("Current wind speed " +
				loop.getWindSpeed());
		System.out.println("Current wind direction " +
				loop.getWindDirection());
		System.out.println("10 minute average wind speed " +
				loop.getTenMinuteAverageWindSpeed());
		System.out.println("Rain today " +
				loop.getDayRain());
		System.out.println("Rain this month " +
				loop.getMonthRain());
		System.out.println("Rain this year " +
				loop.getYearRain());
				*/

			Loop2 loop2 = client.execute(StandardCommands.createLoop2(1));
			//System.out.println("Dew point " + loop2.getDewPoint());

			Uploader uploader = new Uploader();
			uploader.setBarometricPressure(
					loop2.getBarometricPressure().toString());
			uploader.setHourRain(loop2.getHourRain().toString());
			uploader.setHumidity(String.valueOf(loop2.getOutsideHumidity()));
			uploader.setDewPoint(String.valueOf(loop2.getDewPoint()));
			uploader.setTemperature(loop2.getOutsideTemperature().toString());
			uploader.setRainRate(loop2.getRainRate().toString());
			uploader.setDayRain(loop2.getDayRain().toString());
			uploader.setTenMinuteAverageWindSpeed(String.valueOf(
					loop2.getTenMinuteAverageWindSpeed()));
			uploader.setTenMinuteWindGust(loop2.getTenMinuteWindGust().toString());
			uploader.setTenMinuteWindGustDirection(String.valueOf(
					loop2.getTenMinuteWindGustDirection()));
			uploader.setTwoMinuteAverageWindSpeed(
					loop2.getTwoMinuteAverageWindSpeed().toString());
			uploader.setWindDirection(String.valueOf(loop2.getWindDirection()));
			uploader.setWindSpeed(String.valueOf(loop2.getWindSpeed()));

			try {
				uploader.uploadData();
			} catch (ConnectException e) {
				System.out.println("Connection to wunderground failed");
			}
		} catch (IOException e) {
			ERROR.set(true);
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		client.close();
	}

	private static ScheduledExecutorService configureExecutor() {
		final int cores = Runtime.getRuntime().availableProcessors() * 2;
		return Executors.newScheduledThreadPool(cores, new ThreadFactory() {
			final AtomicInteger counter = new AtomicInteger();

			@Override
			public Thread newThread(Runnable r) {
				final Thread t = new Thread(r, "JWeatherReport Client - " + counter.incrementAndGet());
				t.setDaemon(true);
				return t;
			}
		});
	}

	private static void printUsage() {
		System.out.println("The first argument must be the hostname and the second argument must be a valid port. " +
				"An optional third argument is the number of minutes to wait between uploading the data.");
	}
}
