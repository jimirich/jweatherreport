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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class NetworkClient implements VantagePro2Client {

	private final String hostName;
	private InputStream in = null;
	private DataOutputStream out = null;
	private final int portNumber;
	private Socket socket = null;

	public NetworkClient(final String hostName, final int portNumber) {
		this.hostName = hostName;
		this.portNumber = portNumber;
	}

	public NetworkClient(final InetAddress address, final int portNumber) {
		this.hostName = address.getHostAddress();
		this.portNumber = portNumber;
	}

	@Override
	public void close()
	throws IOException {
		in.close();
		out.close();
		socket.close();
	}

	@Override
	public void connect()
	throws IOException {
		socket = new Socket(hostName, portNumber);
		out = new DataOutputStream(socket.getOutputStream());
		in = socket.getInputStream();

		byte[] buffer = new byte[512];
		int len;
		/* Vantage Pro2 console wake up procedure:
		 * 
		 * 1.  Send a Line Feed character, '\n' (decimal 10, hex 0x0A).
		 * 2.  Listen for a returned response of Line Feed and Carriage
		 *     Return character, ('\n\r').
		 * 3.  If there is no response within a reasonable interval (say 1.2
		 *     seconds), then try steps 1 and 2 again up to a total of 3
		 *     attempts.
		 * 4.  If the console has not woken up after 3 attempts, then signal
		 *     a connection error.
		 */
		Timer timer = new Timer();
		TimerTask task = new WakeUpTask(out, 3);
		timer.schedule(task, 0, 1200);
		len = in.read(buffer, 0, 2);
		//System.out.format("Read %d bytes\n", len);

		if (len < 2) {
			len = in.read(buffer, 1, 1);
			//System.out.format("Read %d bytes\n", len);
		}

		//System.out.format("received: %h%h\n", buffer[0], buffer[1]);
		//System.out.println("Awake");
		timer.cancel();
	}

	@Override
	public boolean isConnected() {
		if (socket != null) {
			return socket.isConnected();
		} else {
			return false;
		}
	}

	@Override
	public byte[] sendCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean testConnection()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;

		/* The TEST command response appears to be \n\rTEST\n\r and not just
		 * TEST\n as stated in the documentation.
		 */
		out.write((new String("TEST\n")).getBytes());
		len = in.read(buffer, 0, 8);
		//System.out.format("Read %d bytes\n", len);

		while (len < 8) {
			len += in.read(buffer, len, 8 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'T') && (buffer[3] == 'E') &&(buffer[4] == 'S') &&
				(buffer[5] == 'T')) {
			return true;
		}

		return false;
	}

	@Override
	public String getFirmwareDate()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("VER\n")).getBytes());
		// get the first 6 bytes which should be \n\rOK\n\r
		len = in.read(buffer, 0, 6);
		//System.out.format("Read %d bytes\n", len);

		while (len < 6) {
			len += in.read(buffer, len, 6 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'O') && (buffer[3] == 'K')) {
			//System.out.println("Retrieving firmware date...");
		} else {
			return "unavailable";
		}

		len = 0;

		for (int i = 0; i < 512; i++) {
			len += in.read(buffer, i, 1);

			if (buffer[i] == '\n') {
				break;
			}
		}

		// retrieve the trailing \r
		in.read();
		return new String(buffer, 0, len - 1);
	}

	@Override
	public String getFirmwareVersion()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("NVER\n")).getBytes());
		// get the first 6 bytes which should be \n\rOK\n\r
		len = in.read(buffer, 0, 6);
		//System.out.format("Read %d bytes\n", len);

		while (len < 6) {
			len += in.read(buffer, len, 6 - len);
			//System.out.format("Read %d bytes\n", len);
		}

		if ((buffer[2] == 'O') && (buffer[3] == 'K')) {
			//System.out.println("Retrieving firmware version...");
		} else {
			return "unavailable";
		}

		len = 0;

		for (int i = 0; i < 512; i++) {
			len += in.read(buffer, i, 1);

			if (buffer[i] == '\n') {
				break;
			}
		}

		// retrieve the trailing \r
		in.read();
		return new String(buffer, 0, len - 1);
	}

	@Override
	public HighLow getHighsLows()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("HILOWS\n")).getBytes());
		// get the first byte which should be ACK (0x06)
		len = in.read(buffer, 0, 1);
		//System.out.format("Read %d bytes\n", len);

		if ((buffer[0] == 0x06)) {
			//System.out.println("Retrieving highs/lows...");
		} else {
			throw new IOException("Command invalid");
		}

		len = 0;

		for (int i = 0; i < 438; i++) {
			len += in.read(buffer, i, 1);
			//System.out.format("Read %d bytes\n", len);
		}

		if (CRC.checkCRC(438, buffer) != 0) {
			throw new IOException("Incorrect CRC checksum");
		}

		HighLow highlow = new HighLow();
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

	@Override
	public Loop getLoop(int packets)
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		StringBuilder sb = new StringBuilder("LOOP ");
		sb.append(String.valueOf(packets));
		sb.append('\n');
		out.write((new String(sb.toString())).getBytes());
		// get the first byte which should be ACK (0x06)
		len = in.read(buffer, 0, 1);
		//System.out.format("Read %d bytes\n", len);

		if ((buffer[0] == 0x06)) {
			//System.out.println("Retrieving LOOP data...");
		} else {
			throw new IOException("Command invalid");
		}

		len = 0;

		for (int i = 0; i < 99; i++) {
			len += in.read(buffer, i, 1);
			//System.out.format("Read %d bytes\n", len);
		}

		if (CRC.checkCRC(99, buffer) != 0) {
			throw new IOException("Incorrect CRC checksum");
		}

		Loop loop = new Loop();
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

	@Override
	public Loop2 getLoop2(int packets)
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		StringBuilder sb = new StringBuilder("LPS 2 ");
		sb.append(String.valueOf(packets));
		sb.append('\n');
		out.write((new String(sb.toString())).getBytes());
		// get the first byte which should be ACK (0x06)
		len = in.read(buffer, 0, 1);
		//System.out.format("Read %d bytes\n", len);

		if ((buffer[0] == 0x06)) {
			//System.out.println("Retrieving LOOP data...");
		} else {
			throw new IOException("Command invalid");
		}

		len = 0;

		for (int i = 0; i < 99; i++) {
			len += in.read(buffer, i, 1);
			//System.out.format("Read %d bytes\n", len);
		}

		if (CRC.checkCRC(99, buffer) != 0) {
			throw new IOException("Incorrect CRC checksum");
		}

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

	@Override
	public LocalDateTime getTime()
	throws IOException {
		byte[] buffer = new byte[512];
		int len;
		out.write((new String("GETTIME\n")).getBytes());
		// get the first byte which should be ACK (0x06)
		len = in.read(buffer, 0, 1);
		//System.out.format("Read %d bytes\n", len);

		if ((buffer[0] == 0x06)) {
			//System.out.println("Retrieving time...");
		} else {
			throw new IOException("Command invalid");
		}

		len = 0;

		for (int i = 0; i < 8; i++) {
			len += in.read(buffer, i, 1);
			//System.out.format("Read %d bytes\n", len);
			//System.out.format("Read %d\n", buffer[i]);
		}

		if (CRC.checkCRC(8, buffer) != 0) {
			throw new IOException("Incorrect CRC checksum");
		}

		int seconds = buffer[0];
		int minutes = buffer[1];
		int hours = buffer[2];
		int day = buffer[3];
		int month = buffer[4];
		int year = buffer[5];
		year = year + 1900;
		return LocalDateTime.of(year, month, day, hours, minutes, seconds);
	}

	/**
	 * Class to implement the wake timer task.
	 */
	public class WakeUpTask extends TimerTask {

		private int attempt = 1;
		private DataOutputStream out;
		private int tries = 1;

		public WakeUpTask(DataOutputStream out, int tries) {
			this.out = out;
			this.tries = tries;
		}

		public void run() {
			if (attempt > tries) {
				throw new RuntimeException("Connection failed");
			}

			try {
				out.writeInt(10); // send line feed in decimal
				System.out.println("sent wake up signal...");
				attempt++;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
