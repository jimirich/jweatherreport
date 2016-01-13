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

package jweatherreport.station;

import java.math.BigDecimal;

public class Loop2 {

	private BigDecimal barometricPressure;
	private BigDecimal dayRain;
	private int dewPoint;
	private BigDecimal fifteenMinuteRain;
	private BigDecimal hourRain;
	private int insideHumidity;
	private BigDecimal insideTemperature;
	private int outsideHumidity;
	private BigDecimal outsideTemperature;
	private BigDecimal rainRate;
	private BigDecimal tenMinuteAverageWindSpeed;
	private BigDecimal tenMinuteWindGust;
	private int tenMinuteWindGustDirection;
	private BigDecimal twoMinuteAverageWindSpeed;
	private int windDirection;
	private int windSpeed;

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

	public int getDewPoint() {
		return dewPoint;
	}

	protected void setDewPoint(int dewPoint) {
		this.dewPoint = dewPoint;
	}

	protected void setDewPoint(byte one, byte two) {
		dewPoint = Utils.setInteger(one, two);
	}

	public BigDecimal getFifteenMinuteRain() {
		return fifteenMinuteRain;
	}

	protected void setFifteenMinuteRain(BigDecimal fifteenMinuteRain) {
		this.fifteenMinuteRain = fifteenMinuteRain;
	}

	protected void setFifteenMinuteRain(byte one, byte two) {
		int rain = Utils.setInteger(one, two);
		fifteenMinuteRain = new BigDecimal(rain);
		fifteenMinuteRain = fifteenMinuteRain.movePointLeft(3);
	}

	public BigDecimal getHourRain() {
		return hourRain;
	}

	protected void setHourRain(BigDecimal hourRain) {
		this.hourRain = hourRain;
	}

	protected void setHourRain(byte one, byte two) {
		int rain = Utils.setInteger(one, two);
		hourRain = new BigDecimal(rain);
		hourRain = hourRain.movePointLeft(3);
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

	public BigDecimal getTenMinuteAverageWindSpeed() {
		return tenMinuteAverageWindSpeed;
	}

	protected void setTenMinuteAverageWindSpeed(
			BigDecimal tenMinuteAverageWindSpeed) {
		this.tenMinuteAverageWindSpeed = tenMinuteAverageWindSpeed;
	}

	protected void setTenMinuteAverageWindSpeed(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		tenMinuteAverageWindSpeed = new BigDecimal(temp);
		tenMinuteAverageWindSpeed = tenMinuteAverageWindSpeed.movePointLeft(1);
	}

	public BigDecimal getTenMinuteWindGust() {
		return tenMinuteWindGust;
	}

	protected void setTenMinuteWindGust(BigDecimal tenMinuteWindGust) {
		this.tenMinuteWindGust = tenMinuteWindGust;
	}

	protected void setTenMinuteWindGust(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		tenMinuteWindGust = new BigDecimal(temp);
		tenMinuteWindGust = tenMinuteWindGust.movePointLeft(1);
	}

	public int getTenMinuteWindGustDirection() {
		return tenMinuteWindGustDirection;
	}

	protected void setTenMinuteWindGustDirection(
			int tenMinuteWindGustDirection) {
		this.tenMinuteWindGustDirection = tenMinuteWindGustDirection;
	}

	protected void setTenMinuteWindGustDirection(byte one, byte two) {
		tenMinuteWindGustDirection = Utils.setInteger(one, two);
	}

	public BigDecimal getTwoMinuteAverageWindSpeed() {
		return twoMinuteAverageWindSpeed;
	}

	protected void setTwoMinuteAverageWindSpeed(
			BigDecimal twoMinuteAverageWindSpeed) {
		this.twoMinuteAverageWindSpeed = twoMinuteAverageWindSpeed;
	}

	protected void setTwoMinuteAverageWindSpeed(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		twoMinuteAverageWindSpeed = new BigDecimal(temp);
		twoMinuteAverageWindSpeed = twoMinuteAverageWindSpeed.movePointLeft(1);
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
}
