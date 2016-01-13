package com.chowhouse.wunderground;

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
	private String humidity;
	private String rainRate;
	private String temperature;
	private String tenMinuteAverageWindSpeed;
	private String windDirection;
	private String windSpeed;

	public String getBarometricPressure() {
		return barometricPressure;
	}

	public void setBarometricPressure(String barometricPressure) {
		this.barometricPressure = barometricPressure;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getRainRate() {
		return rainRate;
	}

	public void setRainRate(String rainRate) {
		this.rainRate = rainRate;
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
		String id = "KUTBRIGH11";
		String password = "MwsiaDIVP2";
		String action = "updateraw";

		String query = String.format("ID=%s&PASSWORD=%s&action=%s&dateutc=now&humidity=%s&tempf=%s&baromin=%s&winddir=%s&windspeedmph=%s",
				URLEncoder.encode(id, charset), 
				URLEncoder.encode(password, charset),
				URLEncoder.encode(action, charset),
				URLEncoder.encode(humidity, charset),
				URLEncoder.encode(temperature, charset),
				URLEncoder.encode(barometricPressure, charset),
				URLEncoder.encode(windDirection, charset),
				URLEncoder.encode(windSpeed, charset));
        System.out.println(query.toString());

		URLConnection connection = new URL(url + "?" + query).openConnection();
		connection.setRequestProperty("Accept-Charset", charset);
		InputStream response = connection.getInputStream();
		HttpURLConnection httpConnection = (HttpURLConnection) connection;
		int status = httpConnection.getResponseCode();

		for (Entry<String, List<String>> header : connection.getHeaderFields().entrySet()) {
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
		    try (BufferedReader reader = new BufferedReader(new InputStreamReader(response, rcvcharset))) {
		        for (String line; (line = reader.readLine()) != null;) {
		            System.out.println(line);
		        }
		    }
		}
		else {
		    // It's likely binary content, use InputStream/OutputStream.
		}

		return "unimplemented";
	}
}
