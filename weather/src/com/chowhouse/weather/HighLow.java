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
import java.time.LocalTime;

public class HighLow {

	private BigDecimal dayHighBarometer;
	private BigDecimal dayHighDewPoint;
	private BigDecimal dayHighHeatIndex;
	private int dayHighInsideHumidity;
	private BigDecimal dayHighInsideTemperature;
	private BigDecimal dayHighOutsideTemperature;
	private BigDecimal dayHighRainRate;
	private BigDecimal dayHighSolarRadiation;
	private BigDecimal dayHighTHSWIndex;
	private int dayHighUltraViolet;
	private int dayHighWindSpeed;
	private BigDecimal dayLowBarometer;
	private BigDecimal dayLowDewPoint;
	private int dayLowInsideHumidity;
	private BigDecimal dayLowInsideTemperature;
	private BigDecimal dayLowOutsideTemperature;
	private BigDecimal dayLowWindChill;
	private BigDecimal hourHighRainRate;
	private BigDecimal monthHighBarometer;
	private BigDecimal monthHighDewPoint;
	private BigDecimal monthHighHeatIndex;
	private int monthHighInsideHumidity;
	private BigDecimal monthHighInsideTemperature;
	private BigDecimal monthHighOutsideTemperature;
	private BigDecimal monthHighRainRate;
	private BigDecimal monthHighSolarRadiation;
	private BigDecimal monthHighTHSWIndex;
	private int monthHighUltraViolet;
	private int monthHighWindSpeed;
	private BigDecimal monthLowBarometer;
	private BigDecimal monthLowDewPoint;
	private int monthLowInsideHumidity;
	private BigDecimal monthLowInsideTemperature;
	private BigDecimal monthLowOutsideTemperature;
	private BigDecimal monthLowWindChill;
	private LocalTime timeOfDayHighBarometer;
	private LocalTime timeOfDayHighDewPoLocalTime;
	private LocalTime timeOfDayHighHeatIndex;
	private LocalTime timeOfDayHighInsideHumidity;
	private LocalTime timeOfDayHighInsideTemperature;
	private LocalTime timeOfDayHighOutsideTemperature;
	private LocalTime timeOfDayHighRainRate;
	private LocalTime timeOfDayHighSolarRadiation;
	private LocalTime timeOfDayHighTHSWIndex;
	private LocalTime timeOfDayHighUltraViolet;
	private LocalTime timeOfDayHighWindSpeed;
	private LocalTime timeOfDayLowBarometer;
	private LocalTime timeOfDayLowDewPoLocalTime;
	private LocalTime timeOfDayLowInsideHumidity;
	private LocalTime timeOfDayLowInsideTemperature;
	private LocalTime timeOfDayLowOutsideTemperature;
	private LocalTime timeOfDayLowWindChill;
	private BigDecimal yearHighBarometer;
	private BigDecimal yearHighDewPoint;
	private BigDecimal yearHighHeatIndex;
	private int yearHighInsideHumidity;
	private BigDecimal yearHighInsideTemperature;
	private BigDecimal yearHighOutsideTemperature;
	private BigDecimal yearHighRainRate;
	private BigDecimal yearHighSolarRadiation;
	private BigDecimal yearHighTHSWIndex;
	private int yearHighUltraViolet;
	private int yearHighWindSpeed;
	private BigDecimal yearLowBarometer;
	private BigDecimal yearLowDewPoint;
	private int yearLowInsideHumidity;
	private BigDecimal yearLowInsideTemperature;
	private BigDecimal yearLowOutsideTemperature;
	private BigDecimal yearLowWindChill;

	public BigDecimal getDayHighBarometer() {
		return dayHighBarometer;
	}

	protected void setDayHighBarometer(BigDecimal dayHighBarometer) {
		this.dayHighBarometer = dayHighBarometer;
	}

	protected void setDayHighBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		dayHighBarometer = new BigDecimal(bar);
		dayHighBarometer = dayHighBarometer.movePointLeft(3);
	}

	public BigDecimal getDayHighDewPoint() {
		return dayHighDewPoint;
	}

	protected void setDayHighDewPoint(BigDecimal dayHighDewPoint) {
		this.dayHighDewPoint = dayHighDewPoint;
	}

	public BigDecimal getDayHighHeatIndex() {
		return dayHighHeatIndex;
	}

	protected void setDayHighHeatIndex(BigDecimal dayHighHeatIndex) {
		this.dayHighHeatIndex = dayHighHeatIndex;
	}

	public int getDayHighInsideHumidity() {
		return dayHighInsideHumidity;
	}

	protected void setDayHighInsideHumidity(int dayHighInsideHumidity) {
		this.dayHighInsideHumidity = dayHighInsideHumidity;
	}

	public BigDecimal getDayHighInsideTemperature() {
		return dayHighInsideTemperature;
	}

	protected void setDayHighInsideTemperature(BigDecimal dayHighInsideTemperature) {
		this.dayHighInsideTemperature = dayHighInsideTemperature;
	}

	protected void setDayHighInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		dayHighInsideTemperature = new BigDecimal(temp);
		dayHighInsideTemperature = dayHighInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayHighOutsideTemperature() {
		return dayHighOutsideTemperature;
	}

	protected void setDayHighOutsideTemperature(BigDecimal dayHighOutsideTemperature) {
		this.dayHighOutsideTemperature = dayHighOutsideTemperature;
	}

	protected void setDayHighOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		dayHighOutsideTemperature = new BigDecimal(temp);
		dayHighOutsideTemperature = dayHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayHighRainRate() {
		return dayHighRainRate;
	}

	protected void setDayHighRainRate(BigDecimal dayHighRainRate) {
		this.dayHighRainRate = dayHighRainRate;
	}

	public BigDecimal getDayHighSolarRadiation() {
		return dayHighSolarRadiation;
	}

	protected void setDayHighSolarRadiation(BigDecimal dayHighSolarRadiation) {
		this.dayHighSolarRadiation = dayHighSolarRadiation;
	}

	public BigDecimal getDayHighTHSWIndex() {
		return dayHighTHSWIndex;
	}

	protected void setDayHighTHSWIndex(BigDecimal dayHighTHSWIndex) {
		this.dayHighTHSWIndex = dayHighTHSWIndex;
	}

	public int getDayHighUltraViolet() {
		return dayHighUltraViolet;
	}

	protected void setDayHighUltraViolet(int dayHighUltraViolet) {
		this.dayHighUltraViolet = dayHighUltraViolet;
	}

	public int getDayHighWindSpeed() {
		return dayHighWindSpeed;
	}

	protected void setDayHighWindSpeed(int dayHighWindSpeed) {
		this.dayHighWindSpeed = dayHighWindSpeed;
	}

	public BigDecimal getDayLowBarometer() {
		return dayLowBarometer;
	}

	protected void setDayLowBarometer(BigDecimal dayLowBarometer) {
		this.dayLowBarometer = dayLowBarometer;
	}

	protected void setDayLowBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		dayLowBarometer = new BigDecimal(bar);
		dayLowBarometer = dayLowBarometer.movePointLeft(3);
	}

	public BigDecimal getDayLowDewPoint() {
		return dayLowDewPoint;
	}

	protected void setDayLowDewPoint(BigDecimal dayLowDewPoint) {
		this.dayLowDewPoint = dayLowDewPoint;
	}

	public int getDayLowInsideHumidity() {
		return dayLowInsideHumidity;
	}

	protected void setDayLowInsideHumidity(int dayLowInsideHumidity) {
		this.dayLowInsideHumidity = dayLowInsideHumidity;
	}

	public BigDecimal getDayLowInsideTemperature() {
		return dayLowInsideTemperature;
	}

	protected void setDayLowInsideTemperature(BigDecimal dayLowInsideTemperature) {
		this.dayLowInsideTemperature = dayLowInsideTemperature;
	}

	protected void setDayLowInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		dayLowInsideTemperature = new BigDecimal(temp);
		dayLowInsideTemperature = dayLowInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayLowOutsideTemperature() {
		return dayLowOutsideTemperature;
	}

	protected void setDayLowOutsideTemperature(BigDecimal dayLowOutsideTemperature) {
		this.dayLowOutsideTemperature = dayLowOutsideTemperature;
	}

	protected void setDayLowOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		dayLowOutsideTemperature = new BigDecimal(temp);
		dayLowOutsideTemperature = dayLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayLowWindChill() {
		return dayLowWindChill;
	}

	protected void setDayLowWindChill(BigDecimal dayLowWindChill) {
		this.dayLowWindChill = dayLowWindChill;
	}

	public BigDecimal getHourHighRainRate() {
		return hourHighRainRate;
	}

	protected void setHourHighRainRate(BigDecimal hourHighRainRate) {
		this.hourHighRainRate = hourHighRainRate;
	}

	public BigDecimal getMonthHighBarometer() {
		return monthHighBarometer;
	}

	protected void setMonthHighBarometer(BigDecimal monthHighBarometer) {
		this.monthHighBarometer = monthHighBarometer;
	}

	protected void setMonthHighBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		monthHighBarometer = new BigDecimal(bar);
		monthHighBarometer = monthHighBarometer.movePointLeft(3);
	}

	public BigDecimal getMonthHighDewPoint() {
		return monthHighDewPoint;
	}

	protected void setMonthHighDewPoint(BigDecimal monthHighDewPoint) {
		this.monthHighDewPoint = monthHighDewPoint;
	}

	public BigDecimal getMonthHighHeatIndex() {
		return monthHighHeatIndex;
	}

	protected void setMonthHighHeatIndex(BigDecimal monthHighHeatIndex) {
		this.monthHighHeatIndex = monthHighHeatIndex;
	}

	public int getMonthHighInsideHumidity() {
		return monthHighInsideHumidity;
	}

	protected void setMonthHighInsideHumidity(int monthHighInsideHumidity) {
		this.monthHighInsideHumidity = monthHighInsideHumidity;
	}

	public BigDecimal getMonthHighInsideTemperature() {
		return monthHighInsideTemperature;
	}

	protected void setMonthHighInsideTemperature(
			BigDecimal monthHighInsideTemperature) {
		this.monthHighInsideTemperature = monthHighInsideTemperature;
	}

	protected void setMonthHighInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		monthHighInsideTemperature = new BigDecimal(temp);
		monthHighInsideTemperature = monthHighInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthHighOutsideTemperature() {
		return monthHighOutsideTemperature;
	}

	protected void setMonthHighOutsideTemperature(
			BigDecimal monthHighOutsideTemperature) {
		this.monthHighOutsideTemperature = monthHighOutsideTemperature;
	}

	protected void setMonthHighOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		monthHighOutsideTemperature = new BigDecimal(temp);
		monthHighOutsideTemperature = monthHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthHighRainRate() {
		return monthHighRainRate;
	}

	protected void setMonthHighRainRate(BigDecimal monthHighRainRate) {
		this.monthHighRainRate = monthHighRainRate;
	}

	public BigDecimal getMonthHighSolarRadiation() {
		return monthHighSolarRadiation;
	}

	protected void setMonthHighSolarRadiation(BigDecimal monthHighSolarRadiation) {
		this.monthHighSolarRadiation = monthHighSolarRadiation;
	}

	public BigDecimal getMonthHighTHSWIndex() {
		return monthHighTHSWIndex;
	}

	protected void setMonthHighTHSWIndex(BigDecimal monthHighTHSWIndex) {
		this.monthHighTHSWIndex = monthHighTHSWIndex;
	}

	public int getMonthHighUltraViolet() {
		return monthHighUltraViolet;
	}

	protected void setMonthHighUltraViolet(int monthHighUltraViolet) {
		this.monthHighUltraViolet = monthHighUltraViolet;
	}

	public int getMonthHighWindSpeed() {
		return monthHighWindSpeed;
	}

	protected void setMonthHighWindSpeed(int monthHighWindSpeed) {
		this.monthHighWindSpeed = monthHighWindSpeed;
	}

	public BigDecimal getMonthLowBarometer() {
		return monthLowBarometer;
	}

	protected void setMonthLowBarometer(BigDecimal monthLowBarometer) {
		this.monthLowBarometer = monthLowBarometer;
	}

	protected void setMonthLowBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		monthLowBarometer = new BigDecimal(bar);
		monthLowBarometer = monthLowBarometer.movePointLeft(3);
	}

	public BigDecimal getMonthLowDewPoint() {
		return monthLowDewPoint;
	}

	protected void setMonthLowDewPoint(BigDecimal monthLowDewPoint) {
		this.monthLowDewPoint = monthLowDewPoint;
	}

	public int getMonthLowInsideHumidity() {
		return monthLowInsideHumidity;
	}

	protected void setMonthLowInsideHumidity(int monthLowInsideHumidity) {
		this.monthLowInsideHumidity = monthLowInsideHumidity;
	}

	public BigDecimal getMonthLowInsideTemperature() {
		return monthLowInsideTemperature;
	}

	protected void setMonthLowInsideTemperature(BigDecimal monthLowInsideTemperature) {
		this.monthLowInsideTemperature = monthLowInsideTemperature;
	}

	protected void setMonthLowInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		monthLowInsideTemperature = new BigDecimal(temp);
		monthLowInsideTemperature = monthLowInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthLowOutsideTemperature() {
		return monthLowOutsideTemperature;
	}

	protected void setMonthLowOutsideTemperature(
			BigDecimal monthLowOutsideTemperature) {
		this.monthLowOutsideTemperature = monthLowOutsideTemperature;
	}

	protected void setMonthLowOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		monthLowOutsideTemperature = new BigDecimal(temp);
		monthLowOutsideTemperature = monthLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthLowWindChill() {
		return monthLowWindChill;
	}

	protected void setMonthLowWindChill(BigDecimal monthLowWindChill) {
		this.monthLowWindChill = monthLowWindChill;
	}

	public LocalTime getTimeOfDayHighBarometer() {
		return timeOfDayHighBarometer;
	}

	protected void setTimeOfDayHighBarometer(LocalTime timeOfDayHighBarometer) {
		this.timeOfDayHighBarometer = timeOfDayHighBarometer;
	}

	protected void setTimeOfDayHighBarometer(byte one, byte two) {
		timeOfDayHighBarometer = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighDewPoLocalTime() {
		return timeOfDayHighDewPoLocalTime;
	}

	protected void setTimeOfDayHighDewPoLocalTime(
			LocalTime timeOfDayHighDewPoLocalTime) {
		this.timeOfDayHighDewPoLocalTime = timeOfDayHighDewPoLocalTime;
	}

	protected void setTimeOfDayHighDewPoLocalTime(byte one, byte two) {
		timeOfDayHighDewPoLocalTime = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighHeatIndex() {
		return timeOfDayHighHeatIndex;
	}

	protected void setTimeOfDayHighHeatIndex(LocalTime timeOfDayHighHeatIndex) {
		this.timeOfDayHighHeatIndex = timeOfDayHighHeatIndex;
	}

	protected void setTimeOfDayHighHeatIndex(byte one, byte two) {
		timeOfDayHighHeatIndex = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighInsideHumidity() {
		return timeOfDayHighInsideHumidity;
	}

	protected void setTimeOfDayHighInsideHumidity(
			LocalTime timeOfDayHighInsideHumidity) {
		this.timeOfDayHighInsideHumidity = timeOfDayHighInsideHumidity;
	}

	protected void setTimeOfDayHighInsideHumidity(byte one, byte two) {
		timeOfDayHighInsideHumidity = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighInsideTemperature() {
		return timeOfDayHighInsideTemperature;
	}

	protected void setTimeOfDayHighInsideTemperature(
			LocalTime timeOfDayHighInsideTemperature) {
		this.timeOfDayHighInsideTemperature = timeOfDayHighInsideTemperature;
	}

	protected void setTimeOfDayHighInsideTemperature(byte one, byte two) {
		timeOfDayHighInsideTemperature = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighOutsideTemperature() {
		return timeOfDayHighOutsideTemperature;
	}

	protected void setTimeOfDayHighOutsideTemperature(
			LocalTime timeOfDayHighOutsideTemperature) {
		this.timeOfDayHighOutsideTemperature = timeOfDayHighOutsideTemperature;
	}

	protected void setTimeOfDayHighOutsideTemperature(byte one, byte two) {
		timeOfDayHighOutsideTemperature = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighRainRate() {
		return timeOfDayHighRainRate;
	}

	protected void setTimeOfDayHighRainRate(LocalTime timeOfDayHighRainRate) {
		this.timeOfDayHighRainRate = timeOfDayHighRainRate;
	}

	protected void setTimeOfDayHighRainRate(byte one, byte two) {
		timeOfDayHighRainRate = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighSolarRadiation() {
		return timeOfDayHighSolarRadiation;
	}

	protected void setTimeOfDayHighSolarRadiation(
			LocalTime timeOfDayHighSolarRadiation) {
		this.timeOfDayHighSolarRadiation = timeOfDayHighSolarRadiation;
	}

	protected void setTimeOfDayHighSolarRadiation(byte one, byte two) {
		timeOfDayHighSolarRadiation = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighTHSWIndex() {
		return timeOfDayHighTHSWIndex;
	}

	protected void setTimeOfDayHighTHSWIndex(LocalTime timeOfDayHighTHSWIndex) {
		this.timeOfDayHighTHSWIndex = timeOfDayHighTHSWIndex;
	}

	protected void setTimeOfDayHighTHSWIndex(byte one, byte two) {
		timeOfDayHighTHSWIndex = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighUltraViolet() {
		return timeOfDayHighUltraViolet;
	}

	protected void setTimeOfDayHighUltraViolet(LocalTime timeOfDayHighUltraViolet) {
		this.timeOfDayHighUltraViolet = timeOfDayHighUltraViolet;
	}

	protected void setTimeOfDayHighUltraViolet(byte one, byte two) {
		timeOfDayHighUltraViolet = setTime(one, two);
	}

	public LocalTime getTimeOfDayHighWindSpeed() {
		return timeOfDayHighWindSpeed;
	}

	protected void setTimeOfDayHighWindSpeed(LocalTime timeOfDayHighWindSpeed) {
		this.timeOfDayHighWindSpeed = timeOfDayHighWindSpeed;
	}

	protected void setTimeOfDayHighWindSpeed(byte one, byte two) {
		timeOfDayHighWindSpeed = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowBarometer() {
		return timeOfDayLowBarometer;
	}

	protected void setTimeOfDayLowBarometer(LocalTime timeOfDayLowBarometer) {
		this.timeOfDayLowBarometer = timeOfDayLowBarometer;
	}

	protected void setTimeOfDayLowBarometer(byte one, byte two) {
		timeOfDayLowBarometer = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowDewPoLocalTime() {
		return timeOfDayLowDewPoLocalTime;
	}

	protected void setTimeOfDayLowDewPoLocalTime(
			LocalTime timeOfDayLowDewPoLocalTime) {
		this.timeOfDayLowDewPoLocalTime = timeOfDayLowDewPoLocalTime;
	}

	protected void setTimeOfDayLowDewPoLocalTime(byte one, byte two) {
		timeOfDayLowDewPoLocalTime = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowInsideHumidity() {
		return timeOfDayLowInsideHumidity;
	}

	protected void setTimeOfDayLowInsideHumidity(
			LocalTime timeOfDayLowInsideHumidity) {
		this.timeOfDayLowInsideHumidity = timeOfDayLowInsideHumidity;
	}

	protected void setTimeOfDayLowInsideHumidity(byte one, byte two) {
		timeOfDayLowInsideHumidity = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowInsideTemperature() {
		return timeOfDayLowInsideTemperature;
	}

	protected void setTimeOfDayLowInsideTemperature(
			LocalTime timeOfDayLowInsideTemperature) {
		this.timeOfDayLowInsideTemperature = timeOfDayLowInsideTemperature;
	}

	protected void setTimeOfDayLowInsideTemperature(byte one, byte two) {
		timeOfDayLowInsideTemperature = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowOutsideTemperature() {
		return timeOfDayLowOutsideTemperature;
	}

	protected void setTimeOfDayLowOutsideTemperature(
			LocalTime timeOfDayLowOutsideTemperature) {
		this.timeOfDayLowOutsideTemperature = timeOfDayLowOutsideTemperature;
	}

	protected void setTimeOfDayLowOutsideTemperature(byte one, byte two) {
		timeOfDayLowOutsideTemperature = setTime(one, two);
	}

	public LocalTime getTimeOfDayLowWindChill() {
		return timeOfDayLowWindChill;
	}

	protected void setTimeOfDayLowWindChill(LocalTime timeOfDayLowWindChill) {
		this.timeOfDayLowWindChill = timeOfDayLowWindChill;
	}

	protected void setTimeOfDayLowWindChill(byte one, byte two) {
		timeOfDayLowWindChill = setTime(one, two);
	}

	public BigDecimal getYearHighBarometer() {
		return yearHighBarometer;
	}

	protected void setYearHighBarometer(BigDecimal yearHighBarometer) {
		this.yearHighBarometer = yearHighBarometer;
	}

	protected void setYearHighBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		yearHighBarometer = new BigDecimal(bar);
		yearHighBarometer = yearHighBarometer.movePointLeft(3);
	}

	public BigDecimal getYearHighDewPoint() {
		return yearHighDewPoint;
	}

	protected void setYearHighDewPoint(BigDecimal yearHighDewPoint) {
		this.yearHighDewPoint = yearHighDewPoint;
	}

	public BigDecimal getYearHighHeatIndex() {
		return yearHighHeatIndex;
	}

	protected void setYearHighHeatIndex(BigDecimal yearHighHeatIndex) {
		this.yearHighHeatIndex = yearHighHeatIndex;
	}

	public int getYearHighInsideHumidity() {
		return yearHighInsideHumidity;
	}

	protected void setYearHighInsideHumidity(int yearHighInsideHumidity) {
		this.yearHighInsideHumidity = yearHighInsideHumidity;
	}

	public BigDecimal getYearHighInsideTemperature() {
		return yearHighInsideTemperature;
	}

	protected void setYearHighInsideTemperature(BigDecimal yearHighInsideTemperature) {
		this.yearHighInsideTemperature = yearHighInsideTemperature;
	}

	protected void setYearHighInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		yearHighInsideTemperature = new BigDecimal(temp);
		yearHighInsideTemperature = yearHighInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearHighOutsideTemperature() {
		return yearHighOutsideTemperature;
	}

	protected void setYearHighOutsideTemperature(
			BigDecimal yearHighOutsideTemperature) {
		this.yearHighOutsideTemperature = yearHighOutsideTemperature;
	}

	protected void setYearHighOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		yearHighOutsideTemperature = new BigDecimal(temp);
		yearHighOutsideTemperature = yearHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearHighRainRate() {
		return yearHighRainRate;
	}

	protected void setYearHighRainRate(BigDecimal yearHighRainRate) {
		this.yearHighRainRate = yearHighRainRate;
	}

	public BigDecimal getYearHighSolarRadiation() {
		return yearHighSolarRadiation;
	}

	protected void setYearHighSolarRadiation(BigDecimal yearHighSolarRadiation) {
		this.yearHighSolarRadiation = yearHighSolarRadiation;
	}

	public BigDecimal getYearHighTHSWIndex() {
		return yearHighTHSWIndex;
	}

	protected void setYearHighTHSWIndex(BigDecimal yearHighTHSWIndex) {
		this.yearHighTHSWIndex = yearHighTHSWIndex;
	}

	public int getYearHighUltraViolet() {
		return yearHighUltraViolet;
	}

	protected void setYearHighUltraViolet(int yearHighUltraViolet) {
		this.yearHighUltraViolet = yearHighUltraViolet;
	}

	public int getYearHighWindSpeed() {
		return yearHighWindSpeed;
	}

	protected void setYearHighWindSpeed(int yearHighWindSpeed) {
		this.yearHighWindSpeed = yearHighWindSpeed;
	}

	public BigDecimal getYearLowBarometer() {
		return yearLowBarometer;
	}

	protected void setYearLowBarometer(BigDecimal yearLowBarometer) {
		this.yearLowBarometer = yearLowBarometer;
	}

	protected void setYearLowBarometer(byte one, byte two) {
		int bar = setInteger(one, two);
		yearLowBarometer = new BigDecimal(bar);
		yearLowBarometer = yearLowBarometer.movePointLeft(3);
	}

	public BigDecimal getYearLowDewPoint() {
		return yearLowDewPoint;
	}

	protected void setYearLowDewPoint(BigDecimal yearLowDewPoint) {
		this.yearLowDewPoint = yearLowDewPoint;
	}

	public int getYearLowInsideHumidity() {
		return yearLowInsideHumidity;
	}

	protected void setYearLowInsideHumidity(int yearLowInsideHumidity) {
		this.yearLowInsideHumidity = yearLowInsideHumidity;
	}

	public BigDecimal getYearLowInsideTemperature() {
		return yearLowInsideTemperature;
	}

	protected void setYearLowInsideTemperature(BigDecimal yearLowInsideTemperature) {
		this.yearLowInsideTemperature = yearLowInsideTemperature;
	}

	protected void setYearLowInsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		yearLowInsideTemperature = new BigDecimal(temp);
		yearLowInsideTemperature = yearLowInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearLowOutsideTemperature() {
		return yearLowOutsideTemperature;
	}

	protected void setYearLowOutsideTemperature(BigDecimal yearLowOutsideTemperature) {
		this.yearLowOutsideTemperature = yearLowOutsideTemperature;
	}

	protected void setYearLowOutsideTemperature(byte one, byte two) {
		int temp = setInteger(one, two);
		yearLowOutsideTemperature = new BigDecimal(temp);
		yearLowOutsideTemperature = yearLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearLowWindChill() {
		return yearLowWindChill;
	}

	protected void setYearLowWindChill(BigDecimal yearLowWindChill) {
		this.yearLowWindChill = yearLowWindChill;
	}

	public static int setInteger(byte one, byte two) {
		int integer = two & 0xFF;
		integer = integer << 8;
		integer = integer | (one & 0xFF);
		return integer;
	}

	public static LocalTime setTime(byte one, byte two) {
		int num = two & 0xFF;
		num = num << 8;
		num = num | (one & 0xFF);
		String time = String.format("%04d", num);
		return LocalTime.of(Integer.parseInt(
				time.substring(0, 2)), Integer.parseInt(time.substring(2)));
	}
}
