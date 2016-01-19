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

package com.chowhouse.jweatherreport.station;

public enum BarometerTrend {

	FR(-60, "Falling Rapidly"),
	FS(-20, "Falling Slowly"),
	STEADY(0, "Steady"),
	RS(20, "Rising Slowly"),
	RR(60, "Rising Slowly"),
	UNSUPPORTED(80, "Unavailable");

	private final int code;
	private final String description;

	private BarometerTrend(final int code, final String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * Gets the trend code.
	 * 
	 * @return The trend code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Gets the trend description.
	 * 
	 * @return The trend description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Validates that a given type description is in the array of types.
	 * 
	 * @param type A string containing the description of the type to check.
	 * @return A BarometerTrend matching the type value given if valid,
	 * null otherwise.
	 */
	public static BarometerTrend validateType(int type) {
		for (BarometerTrend curtype : BarometerTrend.values()) {
			if (curtype.getCode() == type) {
				return curtype;
			}
		}

		return null;
	}
}
