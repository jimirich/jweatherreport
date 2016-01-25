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

package com.chowhouse.jweatherreport.data;

import com.chowhouse.jweatherreport.station.HighLow;
import com.chowhouse.jweatherreport.station.Loop;
import com.chowhouse.jweatherreport.station.Loop2;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Properties;
import org.jboss.logging.Logger;

public class DatabaseWriter implements DataWriter {

	private final HighLow highlow;
	private final Loop loop;
	private final Loop2 loop2;
	private String password;
	private final LocalDateTime time;
	private String user;
	private static final Logger LOGGER = Logger.getLogger(DatabaseWriter.class);

	public DatabaseWriter(LocalDateTime time, HighLow highlow, Loop loop,
			Loop2 loop2) {
		this.time = time;
		this.highlow = highlow;
		this.loop = loop;
		this.loop2 = loop2;
	}

	public HighLow getHighLow() {
		return highlow;
	}

	public Loop getLoop() {
		return loop;
	}

	public Loop2 getLoop2() {
		return loop2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void createRealTimeData()
	throws IOException {
		try {
			String url = "jdbc:postgresql://localhost/jweatherreport";
			Properties props = new Properties();
			props.setProperty("user",user);
			props.setProperty("password",password);
			props.setProperty("ssl","true");
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, props);
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO " +
					"loop " +
					"(reportdate,bartrend,barometer,insidetemp,insidehum," +
					"outsidetemp,outsidehum,windspeed,tenminavgwindspeed," +
					"winddirection,rainrate,stormrain,dayrain,monthrain," +
					"yearrain) " +
					"values " +
					"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stmt.setTimestamp(1, Timestamp.valueOf(time));
			stmt.setInt(2, loop.getBarometerTrend().getCode());
			stmt.setBigDecimal(3, loop.getBarometricPressure());
			stmt.setBigDecimal(4, loop.getInsideTemperature());
			stmt.setInt(5, loop.getInsideHumidity());
			stmt.setBigDecimal(6, loop.getOutsideTemperature());
			stmt.setInt(7, loop.getOutsideHumidity());
			stmt.setInt(8, loop.getWindSpeed());
			stmt.setInt(9, loop.getTenMinuteAverageWindSpeed());
			stmt.setInt(10, loop.getWindDirection());
			stmt.setBigDecimal(11, loop.getRainRate());
			stmt.setBigDecimal(12, loop.getStormRain());
			stmt.setBigDecimal(13, loop.getDayRain());
			stmt.setBigDecimal(14, loop.getMonthRain());
			stmt.setBigDecimal(15, loop.getYearRain());
			stmt.executeUpdate();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			LOGGER.error(e.getMessage(), e);
			throw new IOException(e);
		}
	}

	@Override
	public void write() throws IOException {
		createRealTimeData();
	}
}
