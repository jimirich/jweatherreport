package com.chowhouse.weather;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;

public interface VantagePro2Client extends Closeable {
	void connect() throws IOException;
	boolean isConnected();
	boolean testConnection() throws IOException;
	String getFirmwareDate() throws IOException;
	String getFirmwareVersion() throws IOException;
	byte[] sendCommand(String command);

	public static VantagePro2Client of(final String host,
			final int port) {
		return new NetworkClient(host, port);
	}

	public static VantagePro2Client of(final InetAddress address,
			final int port) {
		return new NetworkClient(address, port);
	}
}