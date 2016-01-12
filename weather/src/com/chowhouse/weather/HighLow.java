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

public class HighLow {

	private int dayLowBarometer;
	private int dayLowInsideTemperature;
	private int dayHighBarometer;
	private int dayHighInsideTemperature;
	private int dayHighWindSpeed;
	private int monthLowBarometer;
	private int monthLowInsideTemperature;
	private int monthHighBarometer;
	private int monthHighInsideTemperature;
	private int monthHighWindSpeed;
	private int timeOfDayLowBarometer;
	private int timeOfDayLowInsideTemperature;
	private int timeOfDayHighBarometer;
	private int timeOfDayHighInsideTemperature;
	private int timeOfDayHighWindSpeed;
	private int yearLowBarometer;
	private int yearLowInsideTemperature;
	private int yearHighBarometer;
	private int yearHighInsideTemperature;
	private int yearHighWindSpeed;

	public int getDayHighBarometer() {
		return dayHighBarometer;
	}

	public void setDayHighBarometer(int dayHighBarometer) {
		this.dayHighBarometer = dayHighBarometer;
	}

	public int getDayHighWindSpeed() {
		return dayHighWindSpeed;
	}

	public void setDayHighWindSpeed(int dayHighWindSpeed) {
		this.dayHighWindSpeed = dayHighWindSpeed;
	}

	public int getDayLowBarometer() {
		return dayLowBarometer;
	}

	public void setDayLowBarometer(int dayLowBarometer) {
		this.dayLowBarometer = dayLowBarometer;
	}

	public int getMonthHighBarometer() {
		return monthHighBarometer;
	}

	public void setMonthHighBarometer(int monthHighBarometer) {
		this.monthHighBarometer = monthHighBarometer;
	}

	public int getMonthHighWindSpeed() {
		return monthHighWindSpeed;
	}

	public void setMonthHighWindSpeed(int monthHighWindSpeed) {
		this.monthHighWindSpeed = monthHighWindSpeed;
	}

	public int getMonthLowBarometer() {
		return monthLowBarometer;
	}

	public void setMonthLowBarometer(int monthLowBarometer) {
		this.monthLowBarometer = monthLowBarometer;
	}

	public int getTimeOfDayHighBarometer() {
		return timeOfDayHighBarometer;
	}

	public void setTimeOfDayHighBarometer(int timeOfDayHighBarometer) {
		this.timeOfDayHighBarometer = timeOfDayHighBarometer;
	}

	public int getTimeOfDayHighWindSpeed() {
		return timeOfDayHighWindSpeed;
	}

	public void setTimeOfDayHighWindSpeed(int timeOfDayHighWindSpeed) {
		this.timeOfDayHighWindSpeed = timeOfDayHighWindSpeed;
	}

	public int getTimeOfDayLowBarometer() {
		return timeOfDayLowBarometer;
	}

	public void setTimeOfDayLowBarometer(int timeOfDayLowBarometer) {
		this.timeOfDayLowBarometer = timeOfDayLowBarometer;
	}

	public int getYearHighBarometer() {
		return yearHighBarometer;
	}

	public void setYearHighBarometer(int yearHighBarometer) {
		this.yearHighBarometer = yearHighBarometer;
	}

	public int getYearHighWindSpeed() {
		return yearHighWindSpeed;
	}

	public void setYearHighWindSpeed(int yearHighWindSpeed) {
		this.yearHighWindSpeed = yearHighWindSpeed;
	}

	public int getYearLowBarometer() {
		return yearLowBarometer;
	}

	public void setYearLowBarometer(int yearLowBarometer) {
		this.yearLowBarometer = yearLowBarometer;
	}
}
