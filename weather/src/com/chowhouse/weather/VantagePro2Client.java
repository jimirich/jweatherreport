package com.chowhouse.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface VantagePro2Client {
	void connect(String host, int port) throws IOException;
	boolean isConnected();
	void sendTest(InputStream in, OutputStream out) throws IOException;
	String getFirmwareVersion(InputStream in, OutputStream out)
			throws IOException;
	byte[] sendCommand(String command);
}