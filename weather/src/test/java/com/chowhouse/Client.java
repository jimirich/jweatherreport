/*
 * Copyright 2016 James Rich
 *
 * jweather is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * jweather is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with jweather; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.chowhouse;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import com.chowhouse.weather.HighLow;
import com.chowhouse.weather.Loop;
import com.chowhouse.weather.Loop2;
import com.chowhouse.weather.VantagePro2Client;
import com.chowhouse.wunderground.Uploader;

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

		/*
		HighLow highlow = client.getHighsLows();
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

		Loop2 loop2 = client.getLoop2(1);
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
		uploader.uploadData();
		client.close();
	}
}
