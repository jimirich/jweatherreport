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
import java.time.LocalTime;

public class HighLow {

	private BigDecimal dayHighBarometer;
	private BigDecimal dayHighDewPoint;
	private BigDecimal dayHighHeatIndex;
	private int dayHighInsideHumidity;
	private BigDecimal dayHighInsideTemperature;
	private BigDecimal dayHighOutsideTemperature;
	private BigDecimal dayHighRainRate = null;
	private BigDecimal dayHighSolarRadiation = null;
	private BigDecimal dayHighTHSWIndex = null;
	private Integer dayHighUltraViolet = null;
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
	private BigDecimal monthHighRainRate = null;
	private BigDecimal monthHighSolarRadiation = null;
	private BigDecimal monthHighTHSWIndex = null;
	private Integer monthHighUltraViolet = null;
	private int monthHighWindSpeed;
	private BigDecimal monthLowBarometer;
	private BigDecimal monthLowDewPoint;
	private int monthLowInsideHumidity;
	private BigDecimal monthLowInsideTemperature;
	private BigDecimal monthLowOutsideTemperature;
	private BigDecimal monthLowWindChill;
	private LocalTime timeOfDayHighBarometer;
	private LocalTime timeOfDayHighDewPoint;
	private LocalTime timeOfDayHighHeatIndex;
	private LocalTime timeOfDayHighInsideHumidity;
	private LocalTime timeOfDayHighInsideTemperature;
	private LocalTime timeOfDayHighOutsideTemperature;
	private LocalTime timeOfDayHighRainRate = null;
	private LocalTime timeOfDayHighSolarRadiation = null;
	private LocalTime timeOfDayHighTHSWIndex = null;
	private LocalTime timeOfDayHighUltraViolet = null;
	private LocalTime timeOfDayHighWindSpeed;
	private LocalTime timeOfDayLowBarometer;
	private LocalTime timeOfDayLowDewPoint;
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
	private BigDecimal yearHighRainRate = null;
	private BigDecimal yearHighSolarRadiation = null;
	private BigDecimal yearHighTHSWIndex = null;
	private Integer yearHighUltraViolet = null;
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
		int bar = Utils.setInteger(one, two);
		dayHighBarometer = new BigDecimal(bar);
		dayHighBarometer = dayHighBarometer.movePointLeft(3);
	}

	public BigDecimal getDayHighDewPoint() {
		return dayHighDewPoint;
	}

	protected void setDayHighDewPoint(BigDecimal dayHighDewPoint) {
		this.dayHighDewPoint = dayHighDewPoint;
	}

	protected void setDayHighDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighDewPoint = new BigDecimal(temp);
	}

	public BigDecimal getDayHighHeatIndex() {
		return dayHighHeatIndex;
	}

	protected void setDayHighHeatIndex(BigDecimal dayHighHeatIndex) {
		this.dayHighHeatIndex = dayHighHeatIndex;
	}

	protected void setDayHighHeatIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighHeatIndex = new BigDecimal(temp);
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

	protected void setDayHighInsideTemperature(
			BigDecimal dayHighInsideTemperature) {
		this.dayHighInsideTemperature = dayHighInsideTemperature;
	}

	protected void setDayHighInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighInsideTemperature = new BigDecimal(temp);
		dayHighInsideTemperature = dayHighInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayHighOutsideTemperature() {
		return dayHighOutsideTemperature;
	}

	protected void setDayHighOutsideTemperature(
			BigDecimal dayHighOutsideTemperature) {
		this.dayHighOutsideTemperature = dayHighOutsideTemperature;
	}

	protected void setDayHighOutsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighOutsideTemperature = new BigDecimal(temp);
		dayHighOutsideTemperature = dayHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayHighRainRate() {
		return dayHighRainRate;
	}

	protected void setDayHighRainRate(BigDecimal dayHighRainRate) {
		this.dayHighRainRate = dayHighRainRate;
	}

	protected void setDayHighRainRate(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighRainRate = new BigDecimal(temp);
		dayHighRainRate = dayHighRainRate.movePointLeft(2);
	}

	public BigDecimal getDayHighSolarRadiation() {
		return dayHighSolarRadiation;
	}

	protected void setDayHighSolarRadiation(BigDecimal dayHighSolarRadiation) {
		this.dayHighSolarRadiation = dayHighSolarRadiation;
	}

	protected void setDayHighSolarRadiation(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighSolarRadiation = new BigDecimal(temp);
		dayHighSolarRadiation = dayHighSolarRadiation.movePointLeft(1);
	}

	public BigDecimal getDayHighTHSWIndex() {
		return dayHighTHSWIndex;
	}

	protected void setDayHighTHSWIndex(BigDecimal dayHighTHSWIndex) {
		this.dayHighTHSWIndex = dayHighTHSWIndex;
	}

	protected void setDayHighTHSWIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayHighTHSWIndex = new BigDecimal(temp);
		dayHighTHSWIndex = dayHighTHSWIndex.movePointLeft(1);
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
		int bar = Utils.setInteger(one, two);
		dayLowBarometer = new BigDecimal(bar);
		dayLowBarometer = dayLowBarometer.movePointLeft(3);
	}

	public BigDecimal getDayLowDewPoint() {
		return dayLowDewPoint;
	}

	protected void setDayLowDewPoint(BigDecimal dayLowDewPoint) {
		this.dayLowDewPoint = dayLowDewPoint;
	}

	protected void setDayLowDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayLowDewPoint = new BigDecimal(temp);
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

	protected void setDayLowInsideTemperature(
			BigDecimal dayLowInsideTemperature) {
		this.dayLowInsideTemperature = dayLowInsideTemperature;
	}

	protected void setDayLowInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayLowInsideTemperature = new BigDecimal(temp);
		dayLowInsideTemperature = dayLowInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayLowOutsideTemperature() {
		return dayLowOutsideTemperature;
	}

	protected void setDayLowOutsideTemperature(
			BigDecimal dayLowOutsideTemperature) {
		this.dayLowOutsideTemperature = dayLowOutsideTemperature;
	}

	protected void setDayLowOutsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayLowOutsideTemperature = new BigDecimal(temp);
		dayLowOutsideTemperature = dayLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getDayLowWindChill() {
		return dayLowWindChill;
	}

	protected void setDayLowWindChill(BigDecimal dayLowWindChill) {
		this.dayLowWindChill = dayLowWindChill;
	}

	protected void setDayLowWindChill(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		dayLowWindChill = new BigDecimal(temp);
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
		int bar = Utils.setInteger(one, two);
		monthHighBarometer = new BigDecimal(bar);
		monthHighBarometer = monthHighBarometer.movePointLeft(3);
	}

	public BigDecimal getMonthHighDewPoint() {
		return monthHighDewPoint;
	}

	protected void setMonthHighDewPoint(BigDecimal monthHighDewPoint) {
		this.monthHighDewPoint = monthHighDewPoint;
	}

	protected void setMonthHighDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighDewPoint = new BigDecimal(temp);
	}

	public BigDecimal getMonthHighHeatIndex() {
		return monthHighHeatIndex;
	}

	protected void setMonthHighHeatIndex(BigDecimal monthHighHeatIndex) {
		this.monthHighHeatIndex = monthHighHeatIndex;
	}

	protected void setMonthHighHeatIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighHeatIndex = new BigDecimal(temp);
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
		int temp = Utils.setInteger(one, two);
		monthHighInsideTemperature = new BigDecimal(temp);
		monthHighInsideTemperature =
				monthHighInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthHighOutsideTemperature() {
		return monthHighOutsideTemperature;
	}

	protected void setMonthHighOutsideTemperature(
			BigDecimal monthHighOutsideTemperature) {
		this.monthHighOutsideTemperature = monthHighOutsideTemperature;
	}

	protected void setMonthHighOutsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighOutsideTemperature = new BigDecimal(temp);
		monthHighOutsideTemperature =
				monthHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthHighRainRate() {
		return monthHighRainRate;
	}

	protected void setMonthHighRainRate(BigDecimal monthHighRainRate) {
		this.monthHighRainRate = monthHighRainRate;
	}

	protected void setMonthHighRainRate(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighRainRate = new BigDecimal(temp);
		monthHighRainRate = monthHighRainRate.movePointLeft(2);
	}

	public BigDecimal getMonthHighSolarRadiation() {
		return monthHighSolarRadiation;
	}

	protected void setMonthHighSolarRadiation(
			BigDecimal monthHighSolarRadiation) {
		this.monthHighSolarRadiation = monthHighSolarRadiation;
	}

	protected void setMonthHighSolarRadiation(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighSolarRadiation = new BigDecimal(temp);
		monthHighSolarRadiation = monthHighSolarRadiation.movePointLeft(1);
	}

	public BigDecimal getMonthHighTHSWIndex() {
		return monthHighTHSWIndex;
	}

	protected void setMonthHighTHSWIndex(BigDecimal monthHighTHSWIndex) {
		this.monthHighTHSWIndex = monthHighTHSWIndex;
	}

	protected void setMonthHighTHSWIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthHighTHSWIndex = new BigDecimal(temp);
		monthHighTHSWIndex = monthHighTHSWIndex.movePointLeft(1);
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
		int bar = Utils.setInteger(one, two);
		monthLowBarometer = new BigDecimal(bar);
		monthLowBarometer = monthLowBarometer.movePointLeft(3);
	}

	public BigDecimal getMonthLowDewPoint() {
		return monthLowDewPoint;
	}

	protected void setMonthLowDewPoint(BigDecimal monthLowDewPoint) {
		this.monthLowDewPoint = monthLowDewPoint;
	}

	protected void setMonthLowDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthLowDewPoint = new BigDecimal(temp);
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

	protected void setMonthLowInsideTemperature(
			BigDecimal monthLowInsideTemperature) {
		this.monthLowInsideTemperature = monthLowInsideTemperature;
	}

	protected void setMonthLowInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
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
		int temp = Utils.setInteger(one, two);
		monthLowOutsideTemperature = new BigDecimal(temp);
		monthLowOutsideTemperature =
				monthLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getMonthLowWindChill() {
		return monthLowWindChill;
	}

	protected void setMonthLowWindChill(BigDecimal monthLowWindChill) {
		this.monthLowWindChill = monthLowWindChill;
	}

	protected void setMonthLowWindChill(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		monthLowWindChill = new BigDecimal(temp);
	}

	public LocalTime getTimeOfDayHighBarometer() {
		return timeOfDayHighBarometer;
	}

	protected void setTimeOfDayHighBarometer(LocalTime timeOfDayHighBarometer) {
		this.timeOfDayHighBarometer = timeOfDayHighBarometer;
	}

	protected void setTimeOfDayHighBarometer(byte one, byte two) {
		timeOfDayHighBarometer = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighDewPoint() {
		return timeOfDayHighDewPoint;
	}

	protected void setTimeOfDayHighDewPoint(
			LocalTime timeOfDayHighDewPoint) {
		this.timeOfDayHighDewPoint = timeOfDayHighDewPoint;
	}

	protected void setTimeOfDayHighDewPoint(byte one, byte two) {
		timeOfDayHighDewPoint = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighHeatIndex() {
		return timeOfDayHighHeatIndex;
	}

	protected void setTimeOfDayHighHeatIndex(LocalTime timeOfDayHighHeatIndex) {
		this.timeOfDayHighHeatIndex = timeOfDayHighHeatIndex;
	}

	protected void setTimeOfDayHighHeatIndex(byte one, byte two) {
		timeOfDayHighHeatIndex = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighInsideHumidity() {
		return timeOfDayHighInsideHumidity;
	}

	protected void setTimeOfDayHighInsideHumidity(
			LocalTime timeOfDayHighInsideHumidity) {
		this.timeOfDayHighInsideHumidity = timeOfDayHighInsideHumidity;
	}

	protected void setTimeOfDayHighInsideHumidity(byte one, byte two) {
		timeOfDayHighInsideHumidity = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighInsideTemperature() {
		return timeOfDayHighInsideTemperature;
	}

	protected void setTimeOfDayHighInsideTemperature(
			LocalTime timeOfDayHighInsideTemperature) {
		this.timeOfDayHighInsideTemperature = timeOfDayHighInsideTemperature;
	}

	protected void setTimeOfDayHighInsideTemperature(byte one, byte two) {
		timeOfDayHighInsideTemperature = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighOutsideTemperature() {
		return timeOfDayHighOutsideTemperature;
	}

	protected void setTimeOfDayHighOutsideTemperature(
			LocalTime timeOfDayHighOutsideTemperature) {
		this.timeOfDayHighOutsideTemperature = timeOfDayHighOutsideTemperature;
	}

	protected void setTimeOfDayHighOutsideTemperature(byte one, byte two) {
		timeOfDayHighOutsideTemperature = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighRainRate() {
		return timeOfDayHighRainRate;
	}

	protected void setTimeOfDayHighRainRate(LocalTime timeOfDayHighRainRate) {
		this.timeOfDayHighRainRate = timeOfDayHighRainRate;
	}

	protected void setTimeOfDayHighRainRate(byte one, byte two) {
		timeOfDayHighRainRate = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighSolarRadiation() {
		return timeOfDayHighSolarRadiation;
	}

	protected void setTimeOfDayHighSolarRadiation(
			LocalTime timeOfDayHighSolarRadiation) {
		this.timeOfDayHighSolarRadiation = timeOfDayHighSolarRadiation;
	}

	protected void setTimeOfDayHighSolarRadiation(byte one, byte two) {
		timeOfDayHighSolarRadiation = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighTHSWIndex() {
		return timeOfDayHighTHSWIndex;
	}

	protected void setTimeOfDayHighTHSWIndex(LocalTime timeOfDayHighTHSWIndex) {
		this.timeOfDayHighTHSWIndex = timeOfDayHighTHSWIndex;
	}

	protected void setTimeOfDayHighTHSWIndex(byte one, byte two) {
		timeOfDayHighTHSWIndex = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighUltraViolet() {
		return timeOfDayHighUltraViolet;
	}

	protected void setTimeOfDayHighUltraViolet(
			LocalTime timeOfDayHighUltraViolet) {
		this.timeOfDayHighUltraViolet = timeOfDayHighUltraViolet;
	}

	protected void setTimeOfDayHighUltraViolet(byte one, byte two) {
		timeOfDayHighUltraViolet = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayHighWindSpeed() {
		return timeOfDayHighWindSpeed;
	}

	protected void setTimeOfDayHighWindSpeed(LocalTime timeOfDayHighWindSpeed) {
		this.timeOfDayHighWindSpeed = timeOfDayHighWindSpeed;
	}

	protected void setTimeOfDayHighWindSpeed(byte one, byte two) {
		timeOfDayHighWindSpeed = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowBarometer() {
		return timeOfDayLowBarometer;
	}

	protected void setTimeOfDayLowBarometer(LocalTime timeOfDayLowBarometer) {
		this.timeOfDayLowBarometer = timeOfDayLowBarometer;
	}

	protected void setTimeOfDayLowBarometer(byte one, byte two) {
		timeOfDayLowBarometer = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowDewPoint() {
		return timeOfDayLowDewPoint;
	}

	protected void setTimeOfDayLowDewPoint(
			LocalTime timeOfDayLowDewPoint) {
		this.timeOfDayLowDewPoint = timeOfDayLowDewPoint;
	}

	protected void setTimeOfDayLowDewPoint(byte one, byte two) {
		timeOfDayLowDewPoint = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowInsideHumidity() {
		return timeOfDayLowInsideHumidity;
	}

	protected void setTimeOfDayLowInsideHumidity(
			LocalTime timeOfDayLowInsideHumidity) {
		this.timeOfDayLowInsideHumidity = timeOfDayLowInsideHumidity;
	}

	protected void setTimeOfDayLowInsideHumidity(byte one, byte two) {
		timeOfDayLowInsideHumidity = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowInsideTemperature() {
		return timeOfDayLowInsideTemperature;
	}

	protected void setTimeOfDayLowInsideTemperature(
			LocalTime timeOfDayLowInsideTemperature) {
		this.timeOfDayLowInsideTemperature = timeOfDayLowInsideTemperature;
	}

	protected void setTimeOfDayLowInsideTemperature(byte one, byte two) {
		timeOfDayLowInsideTemperature = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowOutsideTemperature() {
		return timeOfDayLowOutsideTemperature;
	}

	protected void setTimeOfDayLowOutsideTemperature(
			LocalTime timeOfDayLowOutsideTemperature) {
		this.timeOfDayLowOutsideTemperature = timeOfDayLowOutsideTemperature;
	}

	protected void setTimeOfDayLowOutsideTemperature(byte one, byte two) {
		timeOfDayLowOutsideTemperature = Utils.setTime(one, two);
	}

	public LocalTime getTimeOfDayLowWindChill() {
		return timeOfDayLowWindChill;
	}

	protected void setTimeOfDayLowWindChill(LocalTime timeOfDayLowWindChill) {
		this.timeOfDayLowWindChill = timeOfDayLowWindChill;
	}

	protected void setTimeOfDayLowWindChill(byte one, byte two) {
		timeOfDayLowWindChill = Utils.setTime(one, two);
	}

	public BigDecimal getYearHighBarometer() {
		return yearHighBarometer;
	}

	protected void setYearHighBarometer(BigDecimal yearHighBarometer) {
		this.yearHighBarometer = yearHighBarometer;
	}

	protected void setYearHighBarometer(byte one, byte two) {
		int bar = Utils.setInteger(one, two);
		yearHighBarometer = new BigDecimal(bar);
		yearHighBarometer = yearHighBarometer.movePointLeft(3);
	}

	public BigDecimal getYearHighDewPoint() {
		return yearHighDewPoint;
	}

	protected void setYearHighDewPoint(BigDecimal yearHighDewPoint) {
		this.yearHighDewPoint = yearHighDewPoint;
	}

	protected void setYearHighDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearHighDewPoint = new BigDecimal(temp);
	}

	public BigDecimal getYearHighHeatIndex() {
		return yearHighHeatIndex;
	}

	protected void setYearHighHeatIndex(BigDecimal yearHighHeatIndex) {
		this.yearHighHeatIndex = yearHighHeatIndex;
	}

	protected void setYearHighHeatIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearHighHeatIndex = new BigDecimal(temp);
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

	protected void setYearHighInsideTemperature(
			BigDecimal yearHighInsideTemperature) {
		this.yearHighInsideTemperature = yearHighInsideTemperature;
	}

	protected void setYearHighInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
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
		int temp = Utils.setInteger(one, two);
		yearHighOutsideTemperature = new BigDecimal(temp);
		yearHighOutsideTemperature =
				yearHighOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearHighRainRate() {
		return yearHighRainRate;
	}

	protected void setYearHighRainRate(BigDecimal yearHighRainRate) {
		this.yearHighRainRate = yearHighRainRate;
	}

	protected void setYearHighRainRate(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearHighRainRate = new BigDecimal(temp);
		yearHighRainRate = yearHighRainRate.movePointLeft(2);
	}

	public BigDecimal getYearHighSolarRadiation() {
		return yearHighSolarRadiation;
	}

	protected void setYearHighSolarRadiation(
			BigDecimal yearHighSolarRadiation) {
		this.yearHighSolarRadiation = yearHighSolarRadiation;
	}

	protected void setYearHighSolarRadiation(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearHighSolarRadiation = new BigDecimal(temp);
		yearHighSolarRadiation = yearHighSolarRadiation.movePointLeft(1);
	}

	public BigDecimal getYearHighTHSWIndex() {
		return yearHighTHSWIndex;
	}

	protected void setYearHighTHSWIndex(BigDecimal yearHighTHSWIndex) {
		this.yearHighTHSWIndex = yearHighTHSWIndex;
	}

	protected void setYearHighTHSWIndex(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearHighTHSWIndex = new BigDecimal(temp);
		yearHighTHSWIndex = yearHighTHSWIndex.movePointLeft(1);
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
		int bar = Utils.setInteger(one, two);
		yearLowBarometer = new BigDecimal(bar);
		yearLowBarometer = yearLowBarometer.movePointLeft(3);
	}

	public BigDecimal getYearLowDewPoint() {
		return yearLowDewPoint;
	}

	protected void setYearLowDewPoint(BigDecimal yearLowDewPoint) {
		this.yearLowDewPoint = yearLowDewPoint;
	}

	protected void setYearLowDewPoint(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearLowDewPoint = new BigDecimal(temp);
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

	protected void setYearLowInsideTemperature(
			BigDecimal yearLowInsideTemperature) {
		this.yearLowInsideTemperature = yearLowInsideTemperature;
	}

	protected void setYearLowInsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearLowInsideTemperature = new BigDecimal(temp);
		yearLowInsideTemperature = yearLowInsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearLowOutsideTemperature() {
		return yearLowOutsideTemperature;
	}

	protected void setYearLowOutsideTemperature(
			BigDecimal yearLowOutsideTemperature) {
		this.yearLowOutsideTemperature = yearLowOutsideTemperature;
	}

	protected void setYearLowOutsideTemperature(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearLowOutsideTemperature = new BigDecimal(temp);
		yearLowOutsideTemperature = yearLowOutsideTemperature.movePointLeft(1);
	}

	public BigDecimal getYearLowWindChill() {
		return yearLowWindChill;
	}

	protected void setYearLowWindChill(BigDecimal yearLowWindChill) {
		this.yearLowWindChill = yearLowWindChill;
	}

	protected void setYearLowWindChill(byte one, byte two) {
		int temp = Utils.setInteger(one, two);
		yearLowWindChill = new BigDecimal(temp);
	}
}
