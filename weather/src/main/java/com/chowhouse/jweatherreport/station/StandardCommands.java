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
 * You should have received a copy of the GNU General Public License
 * along with jweatherreport; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.chowhouse.jweatherreport.station;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class StandardCommands {

	/**
	 * A command used to retrieve the firmware date.
	 */
	public static final Command<String> FIRMWARE_DATE = new SimpleCommand("VER", "Firmware Date");

	/**
	 * A command used to retrieve the firmware version.
	 */
	public static final Command<String> FIRMWARE_VERSION = new SimpleCommand("NVER", "Firmware Version");

	/**
	 * A command used to retrieve the current time.
	 */
	public static final Command<LocalDateTime> TIME = new AbstractCommand<LocalDateTime>("GETTIME", "Current TIme") {
		@Override
		public LocalDateTime execute(final InputStream in) throws IOException {
			final byte[] buffer = readAck(in, 8, true);

			final int seconds = buffer[0];
			final int minutes = buffer[1];
			final int hours = buffer[2];
			final int day = buffer[3];
			final int month = buffer[4];
			final int year = buffer[5] + 1900;
			return LocalDateTime.of(year, month, day, hours, minutes, seconds);
		}
	};

	/**
	 * A command used to retrieve the high's and lows.
	 */
	public static final Command<HighLow> HIGH_LOW = new AbstractCommand<HighLow>("HILOWS", "Highs and Lows") {
		@Override
		public HighLow execute(final InputStream in) throws IOException {
			final byte[] buffer = readAck(in, 438, true);

			final HighLow highlow = new HighLow();
			highlow.setDayLowBarometer(buffer[0], buffer[1]);
			highlow.setDayHighBarometer(buffer[2], buffer[3]);
			highlow.setMonthLowBarometer(buffer[4], buffer[5]);
			highlow.setMonthHighBarometer(buffer[6], buffer[7]);
			highlow.setYearLowBarometer(buffer[8], buffer[9]);
			highlow.setYearHighBarometer(buffer[10], buffer[11]);
			highlow.setTimeOfDayLowBarometer(buffer[12], buffer[13]);
			highlow.setTimeOfDayHighBarometer(buffer[14], buffer[15]);

			highlow.setDayHighWindSpeed((new BigInteger(Arrays.copyOfRange(
					buffer, 16, 17))).intValue());
			highlow.setTimeOfDayHighWindSpeed(buffer[17], buffer[18]);
			highlow.setMonthHighWindSpeed((new BigInteger(Arrays.copyOfRange(
					buffer, 19, 20))).intValue());
			highlow.setYearHighWindSpeed((new BigInteger(Arrays.copyOfRange(
					buffer, 20, 21))).intValue());

			highlow.setDayHighInsideTemperature(buffer[21], buffer[22]);
			highlow.setDayLowInsideTemperature(buffer[23], buffer[24]);
			highlow.setTimeOfDayHighInsideTemperature(buffer[25], buffer[26]);
			highlow.setTimeOfDayLowInsideTemperature(buffer[27], buffer[28]);
			highlow.setMonthLowInsideTemperature(buffer[29], buffer[30]);
			highlow.setMonthHighInsideTemperature(buffer[31], buffer[32]);
			highlow.setYearLowInsideTemperature(buffer[33], buffer[34]);
			highlow.setYearHighInsideTemperature(buffer[35], buffer[36]);

			highlow.setDayHighInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 37, 38))).intValue());
			highlow.setDayLowInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 38, 39))).intValue());
			highlow.setTimeOfDayHighInsideHumidity(buffer[39], buffer[40]);
			highlow.setTimeOfDayLowInsideHumidity(buffer[41], buffer[42]);
			highlow.setMonthHighInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 43, 44))).intValue());
			highlow.setMonthLowInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 44, 45))).intValue());
			highlow.setYearHighInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 45, 46))).intValue());
			highlow.setYearLowInsideHumidity((new BigInteger(Arrays.copyOfRange(
					buffer, 46, 47))).intValue());

			highlow.setDayLowOutsideTemperature(buffer[47], buffer[48]);
			highlow.setDayHighOutsideTemperature(buffer[49], buffer[50]);
			highlow.setTimeOfDayLowOutsideTemperature(buffer[51], buffer[52]);
			highlow.setTimeOfDayHighOutsideTemperature(buffer[53], buffer[54]);
			highlow.setMonthHighOutsideTemperature(buffer[55], buffer[56]);
			highlow.setMonthLowOutsideTemperature(buffer[57], buffer[58]);
			highlow.setYearHighOutsideTemperature(buffer[59], buffer[60]);
			highlow.setYearLowOutsideTemperature(buffer[61], buffer[62]);

			highlow.setDayLowDewPoint(buffer[63], buffer[64]);
			highlow.setDayHighDewPoint(buffer[65], buffer[66]);
			highlow.setTimeOfDayLowDewPoint(buffer[67], buffer[68]);
			highlow.setTimeOfDayHighDewPoint(buffer[69], buffer[70]);
			highlow.setMonthHighDewPoint(buffer[71], buffer[72]);
			highlow.setMonthLowDewPoint(buffer[73], buffer[74]);
			highlow.setYearHighDewPoint(buffer[75], buffer[76]);
			highlow.setYearLowDewPoint(buffer[77], buffer[78]);

			highlow.setDayLowWindChill(buffer[79], buffer[80]);
			highlow.setTimeOfDayLowWindChill(buffer[81], buffer[82]);
			highlow.setMonthLowWindChill(buffer[83], buffer[84]);
			highlow.setYearLowWindChill(buffer[85], buffer[86]);

			highlow.setDayHighHeatIndex(buffer[87], buffer[88]);
			highlow.setTimeOfDayHighHeatIndex(buffer[89], buffer[90]);
			highlow.setMonthHighHeatIndex(buffer[91], buffer[92]);
			highlow.setYearHighHeatIndex(buffer[93], buffer[94]);

			try {
				highlow.setTimeOfDayHighTHSWIndex(buffer[97], buffer[98]);
				highlow.setDayHighTHSWIndex(buffer[95], buffer[96]);
				highlow.setMonthHighTHSWIndex(buffer[99], buffer[100]);
				highlow.setYearHighTHSWIndex(buffer[101], buffer[102]);
			} catch (DateTimeException e) {
				// do nothing
			}

			try {
				highlow.setTimeOfDayHighSolarRadiation(buffer[105], buffer[106]);
				highlow.setDayHighSolarRadiation(buffer[103], buffer[104]);
				highlow.setMonthHighSolarRadiation(buffer[107], buffer[108]);
				highlow.setYearHighSolarRadiation(buffer[109], buffer[110]);
			} catch (DateTimeException e) {
				// do nothing
			}

			try {
				highlow.setTimeOfDayHighUltraViolet(buffer[112], buffer[113]);
				highlow.setDayHighUltraViolet((new BigInteger(Arrays.copyOfRange(
						buffer, 111, 112))).intValue());
				highlow.setMonthHighUltraViolet((new BigInteger(Arrays.copyOfRange(
						buffer, 114, 115))).intValue());
				highlow.setYearHighUltraViolet((new BigInteger(Arrays.copyOfRange(
						buffer, 115, 116))).intValue());
			} catch (DateTimeException e) {
				// do nothing
			}

			highlow.setDayHighRainRate(buffer[116], buffer[117]);

			try {
				highlow.setTimeOfDayHighRainRate(buffer[118], buffer[119]);
			} catch (DateTimeException e) {
				// do nothing
			}

			highlow.setMonthHighRainRate(buffer[122], buffer[123]);
			highlow.setYearHighRainRate(buffer[124], buffer[125]);

			return highlow;
		}
	};

	/**
	 * A command used to test the connection.
	 */
	public static Command<Boolean> TEST_CONNECTION = new AbstractCommand<Boolean>("TEST", "Test Connection") {
		@Override
		public Boolean execute(InputStream in) throws IOException {
			/* The TEST command response appears to be \n\rTEST\n\r and not just
			 * TEST\n as stated in the documentation.
			 */
			return expectedResponse(in, "TEST\n");
		}
	};

	public static Command<Loop> createLoop(final int packets) {
		return new AbstractCommand<Loop>("LOOP " + packets, "LOOP") {
			@Override
			public Loop execute(final InputStream in) throws IOException {
				final byte[] buffer = readAck(in, 99, true);
				final Loop loop = new Loop();
				loop.setBarometricPressure(buffer[7], buffer[8]);
				loop.setInsideTemperature(buffer[9], buffer[10]);
				loop.setInsideHumidity((new BigInteger(Arrays.copyOfRange(
						buffer, 11, 12))).intValue());
				loop.setOutsideTemperature(buffer[12], buffer[13]);
				loop.setWindSpeed((new BigInteger(Arrays.copyOfRange(
						buffer, 14, 15))).intValue());
				loop.setTenMinuteAverageWindSpeed((new BigInteger(Arrays.copyOfRange(
						buffer, 15, 16))).intValue());
				loop.setWindDirection(buffer[16], buffer[17]);
				loop.setOutsideHumidity((new BigInteger(Arrays.copyOfRange(
						buffer, 33, 34))).intValue());
				loop.setRainRate(buffer[41], buffer[42]);
				loop.setDayRain(buffer[50], buffer[51]);
				loop.setMonthRain(buffer[52], buffer[53]);
				loop.setYearRain(buffer[54], buffer[55]);
				return loop;
			}
		};
	}

	public static Command<Loop2> createLoop2(final int packets) {
		return new AbstractCommand<Loop2>("LPS 2 " + packets, "LOOP2") {
			@Override
			public Loop2 execute(final InputStream in) throws IOException {
				final byte[] buffer = readAck(in, 99, true);
				Loop2 loop = new Loop2();
				loop.setBarometricPressure(buffer[7], buffer[8]);
				loop.setInsideTemperature(buffer[9], buffer[10]);
				loop.setInsideHumidity((new BigInteger(Arrays.copyOfRange(
						buffer, 11, 12))).intValue());
				loop.setOutsideTemperature(buffer[12], buffer[13]);
				loop.setWindSpeed((new BigInteger(Arrays.copyOfRange(
						buffer, 14, 15))).intValue());
				loop.setWindDirection(buffer[16], buffer[17]);
				loop.setTenMinuteAverageWindSpeed(buffer[18], buffer[19]);
				loop.setTwoMinuteAverageWindSpeed(buffer[20], buffer[21]);
				loop.setTenMinuteWindGust(buffer[22], buffer[23]);
				loop.setTenMinuteWindGustDirection(buffer[24], buffer[25]);
				loop.setDewPoint(buffer[30], buffer[31]);
				loop.setOutsideHumidity((new BigInteger(Arrays.copyOfRange(
						buffer, 33, 34))).intValue());
				loop.setRainRate(buffer[41], buffer[42]);
				loop.setDayRain(buffer[50], buffer[51]);
				loop.setFifteenMinuteRain(buffer[52], buffer[53]);
				loop.setHourRain(buffer[54], buffer[55]);
				return loop;
			}
		};
	}
}
