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

import java.time.format.DateTimeFormatter;
import com.chowhouse.jweatherreport.station.HighLow;
import com.chowhouse.jweatherreport.station.Loop;
import com.chowhouse.jweatherreport.station.Loop2;

public class VProWeather implements DataWriter {

	private final HighLow highlow;
	private final Loop loop;
	private final Loop2 loop2;
	private final String realTimeDataFileName;
	private final String summaryDataFileName;

	public VProWeather(HighLow highlow, Loop loop, Loop2 loop2,
			String realTimeDataFileName, String summaryDataFileName) {
		this.highlow = highlow;
		this.loop = loop;
		this.loop2 = loop2;
		this.realTimeDataFileName = realTimeDataFileName;
		this.summaryDataFileName = summaryDataFileName;
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

	public String getRealTimeDataFileName() {
		return realTimeDataFileName;
	}

	public String getSummaryDataFileName() {
		return summaryDataFileName;
	}

	public void createRealTimeData() {
	}

	public void createSummaryData() {
		System.out.println("hlBaroHiDay = " + highlow.getDayHighBarometer());
		System.out.println("hlBaroHiTime = " +
				highlow.getTimeOfDayHighBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("hlBaroLoDay = " + highlow.getDayLowBarometer());
		System.out.println("hlBaroLoTime = " +
				highlow.getTimeOfDayLowBarometer().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("hlBaroHiMonth = " + highlow.getMonthHighBarometer());
		System.out.println("hlBaroLoMonth = " + highlow.getMonthLowBarometer());
		System.out.println("hlBaroHiYear = " + highlow.getYearHighBarometer());
		System.out.println("hlBaroLoYear = " + highlow.getYearLowBarometer());

		System.out.println("hlWindHiDay = " + highlow.getDayHighWindSpeed());
		System.out.println("hlWindHiTime = " +
				highlow.getTimeOfDayHighWindSpeed().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("hlWindHiMonth = " + highlow.getMonthHighWindSpeed());
		System.out.println("hlWindHiYear = " + highlow.getYearHighWindSpeed());

		System.out.println("hlInTempHiDay = " +
				highlow.getDayHighInsideTemperature());
		System.out.println("hlInTempHiTime = " +
				highlow.getTimeOfDayHighInsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("hlInTempLoDay = " +
				highlow.getDayLowInsideTemperature());
		System.out.println("hlInTempLoTime = " +
				highlow.getTimeOfDayLowInsideTemperature().format(
						DateTimeFormatter.ofPattern("hh:mm")));
		System.out.println("hlInTempHiMonth = " +
				highlow.getMonthHighInsideTemperature());
		System.out.println("hlInTempLoMonth = " +
				highlow.getMonthLowInsideTemperature());
		System.out.println("hlInTempHiYear = " +
				highlow.getYearHighInsideTemperature());
		System.out.println("hlInTempLoYear " +
				highlow.getYearLowInsideTemperature());
	}

	@Override
	public void write() {
		createRealTimeData();
		createSummaryData();
	}
}
