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

package com.chowhouse.weather;

import java.math.BigDecimal;

public class Loop {

	private BigDecimal barometricPressure;
	private int insideHumidity;
	private BigDecimal insideTemperature;
	private int outsideHumidity;
	private BigDecimal outsideTemperature;
	private int windSpeed;

	public BigDecimal getBarometricPressure() {
		return barometricPressure;
	}

	protected void setBarometricPressure(BigDecimal barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	protected void setBarometricPressure(byte one, byte two) {
		int bar = HighLow.setInteger(one, two);
		barometricPressure = new BigDecimal(bar);
		barometricPressure = barometricPressure.movePointLeft(3);
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
		int temp = HighLow.setInteger(one, two);
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
		int temp = HighLow.setInteger(one, two);
		outsideTemperature = new BigDecimal(temp);
		outsideTemperature = outsideTemperature.movePointLeft(1);
	}

	public int getWindSpeed() {
		return windSpeed;
	}

	protected void setWindSpeed(int windSpeed) {
		this.windSpeed = windSpeed;
	}
}
