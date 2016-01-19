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

package com.chowhouse.jweatherreport.data;

import com.chowhouse.jweatherreport.station.BarometerTrend;
import com.chowhouse.jweatherreport.station.HighLow;
import com.chowhouse.jweatherreport.station.Loop;
import com.chowhouse.jweatherreport.station.Loop2;
import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VProWeather implements DataWriter {

	private final HighLow highlow;
	private final Loop loop;
	private final Loop2 loop2;
	private final Path realTimeDataFile;
	private final Path summaryDataFile;
	private final LocalDateTime time;

	public VProWeather(LocalDateTime time, HighLow highlow, Loop loop,
			Loop2 loop2, Path realTimeDataFile, Path summaryDataFile) {
		this.time = time;
		this.highlow = highlow;
		this.loop = loop;
		this.loop2 = loop2;
		this.realTimeDataFile = realTimeDataFile;
		this.summaryDataFile = summaryDataFile;
	}

	public HighLow getHighLow() {
		return highlow;
	}

	public Loop getLoop() {
		return loop;
	}

	public Loop2 getLoop2() {
		return loop2;
	}

	public Path getRealTimeDataFile() {
		return realTimeDataFile;
	}

	public Path getSummaryDataFile() {
		return summaryDataFile;
	}

	public void createRealTimeData()
	throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(realTimeDataFile,
				StandardCharsets.US_ASCII)) {
			writer.write("DavisTime = " +
				time.format(DateTimeFormatter.ofPattern(
						"yyyy-MM-dd hh:mm:ss")));
			writer.newLine();

			BarometerTrend trend = loop.getBarometerTrend();
			writer.write("rtBaroTrend = " + trend.getDescription());
			writer.newLine();

			switch (trend.getCode()) {
			case -60:
				writer.write("rtBaroTrendImg = baro_fr");
				break;
			case -20:
				writer.write("rtBaroTrendImg = baro_fs");
				break;
			case 0:
				writer.write("rtBaroTrendImg = baro_s");
				break;
			case 20:
				writer.write("rtBaroTrendImg = baro_rs");
				break;
			case 60:
				writer.write("rtBaroTrendImg = baro_rr");
				break;
			default:
				writer.write("rtBaroTrendImg = baro_none");
				break;
			}

			writer.newLine();
			writer.write("rtBaroCurr = " + loop.getBarometricPressure());
			writer.newLine();

			writer.write("rtInsideTemp = " + loop.getInsideTemperature());
			writer.newLine();
			writer.write("rtInsideHum = " + loop.getInsideHumidity());
			writer.newLine();

			writer.write("rtOutsideTemp = " + loop.getOutsideTemperature());
			writer.newLine();
			writer.write("rtOutsideHum = " + loop.getOutsideHumidity());
			writer.newLine();

			writer.write("rtWindSpeed = " + loop.getWindSpeed());
			writer.newLine();
			writer.write("rtWindDir = " + loop.getWindDirection());
			writer.newLine();
			writer.write("rtWindAvgSpeed = " +
					loop.getTenMinuteAverageWindSpeed());
			writer.newLine();
			writer.write("rtWindDirRose = ");

			if (loop.getWindDirection() >= 347 &&
					loop.getWindDirection() < 12) {
				writer.write("N");
			} else if (loop.getWindDirection() >= 12 &&
					loop.getWindDirection() < 34) {
				writer.write("NNE");
			} else if (loop.getWindDirection() >= 34 &&
					loop.getWindDirection() < 57) {
				writer.write("NE");
			} else if (loop.getWindDirection() >= 57 &&
					loop.getWindDirection() < 79) {
				writer.write("ENE");
			} else if (loop.getWindDirection() >= 79 &&
					loop.getWindDirection() < 102) {
				writer.write("E");
			} else if (loop.getWindDirection() >= 102 &&
					loop.getWindDirection() < 124) {
				writer.write("ESE");
			} else if (loop.getWindDirection() >= 124 &&
					loop.getWindDirection() < 147) {
				writer.write("SE");
			} else if (loop.getWindDirection() >= 147 &&
					loop.getWindDirection() < 170) {
				writer.write("SSE");
			} else if (loop.getWindDirection() >= 170 &&
					loop.getWindDirection() < 192) {
				writer.write("S");
			} else if (loop.getWindDirection() >= 192 &&
					loop.getWindDirection() < 214) {
				writer.write("SSW");
			} else if (loop.getWindDirection() >= 214 &&
					loop.getWindDirection() < 237) {
				writer.write("SW");
			} else if (loop.getWindDirection() >= 237 &&
					loop.getWindDirection() < 259) {
				writer.write("WSW");
			} else if (loop.getWindDirection() >= 280 &&
					loop.getWindDirection() < 303) {
				writer.write("WNW");
			} else if (loop.getWindDirection() >= 303 &&
					loop.getWindDirection() < 347) {
				writer.write("NW");
			} else {
				writer.write("NNW");
			}

			writer.newLine();

			writer.write("rtRainRate = " + loop.getRainRate());
			writer.newLine();
			writer.write("rtRainStorm = " + loop.getStormRain());
			writer.newLine();
			writer.write("rtDayRain = " + loop.getDayRain());
			writer.newLine();
			writer.write("rtMonthRain = " + loop.getMonthRain());
			writer.newLine();
			writer.write("rtYearRain = " + loop.getYearRain());
			writer.newLine();

			if (loop.getRainRate().compareTo(BigDecimal.ZERO) != 0) {
				writer.write("rtIsRaining = yes");
			} else {
				writer.write("rtIsRaining = no");
			}

			writer.newLine();

			writer.write("rtUVLevel = n/a");
			writer.newLine();
			writer.write("rtSolarRad = n/a");
			writer.newLine();

			writer.write("rtDewPoint = " + loop2.getDewPoint());
			writer.newLine();
		}
	}

	public void createSummaryData()
	throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(summaryDataFile,
				StandardCharsets.US_ASCII)) {
			DateTimeFormatter timeformat =
					DateTimeFormatter.ofPattern("hh:mma");
			writer.write("hlBaroHiDay = " + highlow.getDayHighBarometer());
			writer.newLine();
			writer.write("hlBaroHiTime = " +
					highlow.getTimeOfDayHighBarometer().format(timeformat));
			writer.newLine();
			writer.write("hlBaroLoDay = " + highlow.getDayLowBarometer());
			writer.newLine();
			writer.write("hlBaroLoTime = " +
					highlow.getTimeOfDayLowBarometer().format(timeformat));
			writer.newLine();
			writer.write("hlBaroHiMonth = " + highlow.getMonthHighBarometer());
			writer.newLine();
			writer.write("hlBaroLoMonth = " + highlow.getMonthLowBarometer());
			writer.newLine();
			writer.write("hlBaroHiYear = " + highlow.getYearHighBarometer());
			writer.newLine();
			writer.write("hlBaroLoYear = " + highlow.getYearLowBarometer());
			writer.newLine();

			writer.write("hlWindHiDay = " + highlow.getDayHighWindSpeed());
			writer.newLine();
			writer.write("hlWindHiTime = " +
					highlow.getTimeOfDayHighWindSpeed().format(timeformat));
			writer.newLine();
			writer.write("hlWindHiMonth = " + highlow.getMonthHighWindSpeed());
			writer.newLine();
			writer.write("hlWindHiYear = " + highlow.getYearHighWindSpeed());
			writer.newLine();

			writer.write("hlInTempHiDay = " +
					highlow.getDayHighInsideTemperature());
			writer.newLine();
			writer.write("hlInTempHiTime = " +
					highlow.getTimeOfDayHighInsideTemperature().format(
							timeformat));
			writer.newLine();
			writer.write("hlInTempLoDay = " +
					highlow.getDayLowInsideTemperature());
			writer.newLine();
			writer.write("hlInTempLoTime = " +
					highlow.getTimeOfDayLowInsideTemperature().format(
							timeformat));
			writer.newLine();
			writer.write("hlInTempHiMonth = " +
					highlow.getMonthHighInsideTemperature());
			writer.newLine();
			writer.write("hlInTempLoMonth = " +
					highlow.getMonthLowInsideTemperature());
			writer.newLine();
			writer.write("hlInTempHiYear = " +
					highlow.getYearHighInsideTemperature());
			writer.newLine();
			writer.write("hlInTempLoYear = " +
					highlow.getYearLowInsideTemperature());
			writer.newLine();

			writer.write("hlOutTempHiDay = " +
					highlow.getDayHighOutsideTemperature());
			writer.newLine();
			writer.write("hlOutTempHiTime = " +
					highlow.getTimeOfDayHighOutsideTemperature().format(
							timeformat));
			writer.newLine();
			writer.write("hlOutTempLoDay = " +
					highlow.getDayLowOutsideTemperature());
			writer.newLine();
			writer.write("hlOutTempLoTime = " +
					highlow.getTimeOfDayLowOutsideTemperature().format(
							timeformat));
			writer.newLine();
			writer.write("hlOutTempHiMonth = " +
					highlow.getMonthHighOutsideTemperature());
			writer.newLine();
			writer.write("hlOutTempLoMonth = " +
					highlow.getMonthLowOutsideTemperature());
			writer.newLine();
			writer.write("hlOutTempHiYear = " +
					highlow.getYearHighOutsideTemperature());
			writer.newLine();
			writer.write("hlOutTempLoYear = " +
					highlow.getYearLowOutsideTemperature());
			writer.newLine();

			writer.write("hlInHumHiDay = " +
					highlow.getDayHighInsideHumidity());
			writer.newLine();
			writer.write("hlInHumHiTime = " +
					highlow.getTimeOfDayHighInsideHumidity().format(
							timeformat));
			writer.newLine();
			writer.write("hlInHumLoDay = " +
					highlow.getDayLowInsideHumidity());
			writer.newLine();
			writer.write("hlInHumLoTime = " +
					highlow.getTimeOfDayLowInsideHumidity().format(timeformat));
			writer.newLine();
			writer.write("hlInHumHiMonth = " +
					highlow.getMonthHighInsideHumidity());
			writer.newLine();
			writer.write("hlInHumLoMonth = " +
					highlow.getMonthLowInsideHumidity());
			writer.newLine();
			writer.write("hlInHumHiYear = " +
					highlow.getYearHighInsideHumidity());
			writer.newLine();
			writer.write("hlInHumLoYear = " +
					highlow.getYearLowInsideHumidity());
			writer.newLine();

			writer.write("hlDewHiDay = " + highlow.getDayHighDewPoint());
			writer.newLine();
			writer.write("hlDewHiTime = " +
					highlow.getTimeOfDayHighDewPoint().format(timeformat));
			writer.newLine();
			writer.write("hlDewHiDay = " + highlow.getDayLowDewPoint());
			writer.newLine();
			writer.write("hlDewLoTime = " +
					highlow.getTimeOfDayLowDewPoint().format(timeformat));
			writer.newLine();
			writer.write("hlDewHiMonth = " + highlow.getMonthHighDewPoint());
			writer.newLine();
			writer.write("hlDewLoMonth = " + highlow.getMonthLowDewPoint());
			writer.newLine();
			writer.write("hlDewHiYear = " + highlow.getYearHighDewPoint());
			writer.newLine();
			writer.write("hlDewLoYear = " + highlow.getYearLowDewPoint());
			writer.newLine();

			writer.write("hlChillLoDay = " + highlow.getDayLowWindChill());
			writer.newLine();
			writer.write("hlChillLoTime = " +
					highlow.getTimeOfDayLowWindChill().format(timeformat));
			writer.newLine();
			writer.write("hlChillLoMonth = " + highlow.getMonthLowWindChill());
			writer.newLine();
			writer.write("hlChillLoYear = " + highlow.getYearLowWindChill());
			writer.newLine();

			writer.write("hlHeatHiDay = " + highlow.getDayHighHeatIndex());
			writer.newLine();
			writer.write("hlHeatHiTime = " +
					highlow.getTimeOfDayHighHeatIndex().format(timeformat));
			writer.newLine();
			writer.write("hlHeatHiMonth = " + highlow.getMonthHighHeatIndex());
			writer.newLine();
			writer.write("hlHeatHiYear = " + highlow.getYearHighHeatIndex());
			writer.newLine();

			if (highlow.getTimeOfDayHighTHSWIndex() != null) {
				writer.write("Day high THSW index " +
						highlow.getDayHighTHSWIndex());
				writer.write("Day high THSW index time " +
						highlow.getTimeOfDayHighTHSWIndex().format(timeformat));
				writer.write("Month high THSW index " +
						highlow.getMonthHighTHSWIndex());
				writer.write("Year high THSW index " +
						highlow.getYearHighTHSWIndex());
			}

			if (highlow.getTimeOfDayHighSolarRadiation() != null) {
				writer.write("hlSolarHiDay = " +
						highlow.getDayHighSolarRadiation());
				writer.newLine();
				writer.write("hlSolarHiTime = " +
						highlow.getTimeOfDayHighSolarRadiation().format(
								timeformat));
				writer.newLine();
				writer.write("hlSolarHiMonth = " +
						highlow.getMonthHighSolarRadiation());
				writer.newLine();
				writer.write("hlSolarHiYear = " +
						highlow.getYearHighSolarRadiation());
				writer.newLine();
			} else {
				writer.write("hlSolarHiDay = 0.0");
				writer.newLine();
				writer.write("hlSolarHiTime = n/a");
				writer.newLine();
				writer.write("hlSolarHiMonth = 0.0");
				writer.newLine();
				writer.write("hlSolarHiYear = 0.0");
				writer.newLine();
			}

			if (highlow.getTimeOfDayHighUltraViolet() != null) {
				writer.write("hlUVHiDay = " + highlow.getDayHighUltraViolet());
				writer.newLine();
				writer.write("hlUVHiTime = " +
						highlow.getTimeOfDayHighUltraViolet().format(
								timeformat));
				writer.newLine();
				writer.write("hlUVHiMonth = " +
						highlow.getMonthHighUltraViolet());
				writer.newLine();
				writer.write("hlUVHiYear = " +
						highlow.getYearHighUltraViolet());
				writer.newLine();
			} else {
				writer.write("hlUVHiDay = 0.0");
				writer.newLine();
				writer.write("hlUVHiTime = n/a");
				writer.newLine();
				writer.write("hlUVHiMonth = 0.0");
				writer.newLine();
				writer.write("hlUVHiYear = 0.0");
				writer.newLine();
			}

			writer.write("hlRainRateHiDay = " + highlow.getDayHighRainRate());
			writer.newLine();

			if (highlow.getTimeOfDayHighRainRate() != null) {
				writer.write("hlRainRateHiTime = " +
						highlow.getTimeOfDayHighRainRate().format(timeformat));
				writer.newLine();
			} else {
				writer.write("hlRainRateHiTime = n/a");
				writer.newLine();
			}

			writer.write("hlRainRateHiMonth = " +
					highlow.getMonthHighRainRate());
			writer.newLine();
			writer.write("hlRainRateHiYear = " + highlow.getYearHighRainRate());
			writer.newLine();
		}
	}

	@Override
	public void write()
	throws IOException {
		createRealTimeData();
		createSummaryData();
	}
}
