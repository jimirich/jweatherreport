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

package com.chowhouse.jweatherreport.wunderground;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map.Entry;

public class Uploader {

	private String barometricPressure;
	private String dayRain;
	private String dewPoint;
	private String hourRain;
	private String humidity;
	private String password;
	private String rainRate;
	private String stationid;
	private String temperature;
	private String tenMinuteAverageWindSpeed;
	private String tenMinuteWindGust;
	private String tenMinuteWindGustDirection;
	private String twoMinuteAverageWindSpeed;
	private String windDirection;
	private String windSpeed;

	public String getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(String barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public String getDayRain() {
		return dayRain;
	}

	public void setDayRain(String dayRain) {
		this.dayRain = dayRain;
	}

	public String getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(String dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getHourRain() {
		return hourRain;
	}

	public void setHourRain(String hourRain) {
		this.hourRain = hourRain;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRainRate() {
		return rainRate;
	}

	public void setRainRate(String rainRate) {
		this.rainRate = rainRate;
	}

	public String getStationID() {
		return stationid;
	}

	public void setStationID(String stationid) {
		this.stationid = stationid;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTenMinuteAverageWindSpeed() {
		return tenMinuteAverageWindSpeed;
	}

	public void setTenMinuteAverageWindSpeed(String tenMinuteAverageWindSpeed) {
		this.tenMinuteAverageWindSpeed = tenMinuteAverageWindSpeed;
	}

	public String getTenMinuteWindGust() {
		return tenMinuteWindGust;
	}

	public void setTenMinuteWindGust(String tenMinuteWindGust) {
		this.tenMinuteWindGust = tenMinuteWindGust;
	}

	public String getTenMinuteWindGustDirection() {
		return tenMinuteWindGustDirection;
	}

	public void setTenMinuteWindGustDirection(
			String tenMinuteWindGustDirection) {
		this.tenMinuteWindGustDirection = tenMinuteWindGustDirection;
	}

	public String getTwoMinuteAverageWindSpeed() {
		return twoMinuteAverageWindSpeed;
	}

	public void setTwoMinuteAverageWindSpeed(String twoMinuteAverageWindSpeed) {
		this.twoMinuteAverageWindSpeed = twoMinuteAverageWindSpeed;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String uploadData()
	throws MalformedURLException, IOException {
		String url = "http://weatherstation.wunderground.com/weatherstation/" +
				"updateweatherstation.php";
		String charset = java.nio.charset.StandardCharsets.UTF_8.name();
		String action = "updateraw";

		String query = String.format("ID=%s&PASSWORD=%s&action=%s&dateutc=now&humidity=%s&tempf=%s&baromin=%s&winddir=%s&windspeedmph=%s&windspdmph_avg2m=%s&windgustmph_10m=%s&windgustdir_10m=%s&rainin=%s&dailyrainin=%s&dewptf=%s&softwaretype=jweatherreport",
				URLEncoder.encode(stationid, charset), 
				URLEncoder.encode(password, charset),
				URLEncoder.encode(action, charset),
				URLEncoder.encode(humidity, charset),
				URLEncoder.encode(temperature, charset),
				URLEncoder.encode(barometricPressure, charset),
				URLEncoder.encode(windDirection, charset),
				URLEncoder.encode(windSpeed, charset),
				URLEncoder.encode(twoMinuteAverageWindSpeed, charset),
				URLEncoder.encode(tenMinuteWindGust, charset),
				URLEncoder.encode(tenMinuteWindGustDirection, charset),
				URLEncoder.encode(hourRain, charset),
				URLEncoder.encode(dayRain, charset),
				URLEncoder.encode(dewPoint, charset));
        System.out.println(query.toString());

		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int status = httpConnection.getResponseCode();

		for (Entry<String, List<String>> header :
			connection.getHeaderFields().entrySet()) {
			System.out.println(header.getKey() + "=" + header.getValue());
		}

		String contentType = connection.getHeaderField("Content-Type");
		String rcvcharset = null;

		for (String param : contentType.replace(" ", "").split(";")) {
		    if (param.startsWith("charset=")) {
		        rcvcharset = param.split("=", 2)[1];
		        break;
		    }
		}

		if (rcvcharset != null) {
		    try (BufferedReader reader = new BufferedReader(
		    		new InputStreamReader(response, rcvcharset))) {
		        for (String line; (line = reader.readLine()) != null;) {
		            System.out.println(line);
		        }
		    }
		} else {
		    // It's likely binary content, use InputStream/OutputStream.
		}

		return "unimplemented";
	}
}
