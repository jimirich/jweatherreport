/*
 * JBoss, Home of Professional Open Source.
 *
 * Copyright 2016 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chowhouse.jweatherreport.station;

import java.math.BigDecimal;

public class Loop {

	private BigDecimal barometricPressure;
	private BigDecimal dayRain;
	private int insideHumidity;
	private BigDecimal insideTemperature;
	private BigDecimal monthRain;
	private int outsideHumidity;
	private BigDecimal outsideTemperature;
	private BigDecimal rainRate;
	private int tenMinuteAverageWindSpeed;
	private int windDirection;
	private int windSpeed;
	private BigDecimal yearRain;

	public BigDecimal getBarometricPressure() {
		return barometricPressure;
	}

	protected void setBarometricPressure(BigDecimal barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	protected void setBarometricPressure(byte one, byte two) {
		int bar = Utils.setInteger(one, two);
		barometricPressure = new BigDecimal(bar);
		barometricPressure = barometricPressure.movePointLeft(3);
	}

	public BigDecimal getDayRain() {
		return dayRain;
	}

	protected void setDayRain(BigDecimal dayRain) {
		this.dayRain = dayRain;
	}

	protected void setDayRain(byte one, byte two) {
		int rain = Utils.setInteger(one, two);
		dayRain = new BigDecimal(rain);
		dayRain = dayRain.movePointLeft(3);
	}

	public int getInsideHumidity() {
		return insideHumidity;
	}

	protected void setInsideHumidity(int insideHumidity) {
		this.insideHumidity = insideHumidity;
	}

	public BigDecimal getInsideTemperature() {
		return insideTemperature;
	}

	protected void setInsideTemperature(BigDecimal insideTemperature) {
		this.insideTemperature = insideTemperature;
	}

	protected void setInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		insideTemperature = new BigDecimal(temp);
		insideTemperature = insideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthRain() {
		return monthRain;
	}

	protected void setMonthRain(BigDecimal monthRain) {
		this.monthRain = monthRain;
	}

	protected void setMonthRain(byte one, byte two) {
		int rain = Utils.setInteger(one, two);
		monthRain = new BigDecimal(rain);
		monthRain = monthRain.movePointLeft(3);
	}

	public int getOutsideHumidity() {
		return outsideHumidity;
	}

	protected void setOutsideHumidity(int outsideHumidity) {
		this.outsideHumidity = outsideHumidity;
	}

	public BigDecimal getOutsideTemperature() {
		return outsideTemperature;
	}

	protected void setOutsideTemperature(BigDecimal outsideTemperature) {
		this.outsideTemperature = outsideTemperature;
	}

	protected void setOutsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		outsideTemperature = new BigDecimal(temp);
		outsideTemperature = outsideTemperature.movePointLeft(1);
	}

	public BigDecimal getRainRate() {
		return rainRate;
	}

	protected void setRainRate(BigDecimal rainRate) {
		this.rainRate = rainRate;
	}

	protected void setRainRate(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		rainRate = new BigDecimal(temp);
		rainRate = rainRate.movePointLeft(2);
	}

	public int getTenMinuteAverageWindSpeed() {
		return tenMinuteAverageWindSpeed;
	}

	protected void setTenMinuteAverageWindSpeed(int tenMinuteAverageWindSpeed) {
		this.tenMinuteAverageWindSpeed = tenMinuteAverageWindSpeed;
	}

	public int getWindDirection() {
		return windDirection;
	}

	protected void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}

	protected void setWindDirection(byte one, byte two) {
		windDirection = Utils.setInteger(one, two);
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	protected void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}

	public BigDecimal getYearRain() {
		return yearRain;
	}

	protected void setYearRain(BigDecimal yearRain) {
		this.yearRain = yearRain;
	}

	protected void setYearRain(byte one, byte two) {
		int rain = Utils.setInteger(one, two);
		yearRain = new BigDecimal(rain);
		yearRain = yearRain.movePointLeft(3);
	}
}
