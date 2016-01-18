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

import com.chowhouse.jweatherreport.station.HighLow;
import com.chowhouse.jweatherreport.station.Loop;
import com.chowhouse.jweatherreport.station.Loop2;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;

public class VProWeather implements DataWriter {

	private final HighLow highlow;
	private final Loop loop;
	private final Loop2 loop2;
	private final Path realTimeDataFile;
	private final Path summaryDataFile;

	public VProWeather(HighLow highlow, Loop loop, Loop2 loop2,
			Path realTimeDataFile, Path summaryDataFile) {
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

	public void createRealTimeData() {
	}

	public void createSummaryData()
	throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(summaryDataFile,
				StandardCharsets.US_ASCII);
		writer.newLine();
		writer.write("hlBaroHiDay = " + highlow.getDayHighBarometer());
		writer.newLine();
		writer.write("hlBaroHiTime = " +
				highlow.getTimeOfDayHighBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		writer.newLine();
		writer.write("hlBaroLoDay = " + highlow.getDayLowBarometer());
		writer.newLine();
		writer.write("hlBaroLoTime = " +
				highlow.getTimeOfDayLowBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
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
				highlow.getTimeOfDayHighWindSpeed().format(
						DateTimeFormatter.ofPattern("hh:mm")));
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
						DateTimeFormatter.ofPattern("hh:mm")));
		writer.newLine();
		writer.write("hlInTempLoDay = " +
				highlow.getDayLowInsideTemperature());
		writer.newLine();
		writer.write("hlInTempLoTime = " +
				highlow.getTimeOfDayLowInsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
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
		writer.write("hlInTempLoYear " +
				highlow.getYearLowInsideTemperature());
		writer.newLine();
	}

	@Override
	public void write()
	throws IOException {
		createRealTimeData();
		createSummaryData();
	}
}
