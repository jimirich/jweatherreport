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

package com.chowhouse.jweatherreport.vproweather;

public class VProWeather {

	private HighLow highlow;
	private Loop loop;
	private Loop2 loop2;
	private String realTimeDataFileName;
	private String summaryDataFileName;

	public HighLow getHighlow() {
		return highlow;
	}

	public void setHighlow(HighLow highlow) {
		this.highlow = highlow;
	}

	public Loop getLoop() {
		return loop;
	}

	public void setLoop(Loop loop) {
		this.loop = loop;
	}

	public Loop2 getLoop2() {
		return loop2;
	}

	public void setLoop2(Loop2 loop2) {
		this.loop2 = loop2;
	}

	public String getRealTimeDataFileName() {
		return realTimeDataFileName;
	}

	public void setRealTimeDataFileName(String realTimeDataFileName) {
		this.realTimeDataFileName = realTimeDataFileName;
	}

	public String getSummaryDataFileName() {
		return summaryDataFileName;
	}

	public void setSummaryDataFileName(String summaryDataFileName) {
		this.summaryDataFileName = summaryDataFileName;
	}
}
