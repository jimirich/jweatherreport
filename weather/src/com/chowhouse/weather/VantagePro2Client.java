package com.chowhouse.weather;

import java.io.Closeable;
import java.io.IOException;

public interface VantagePro2Client extends Closeable {
	void connect(String host, int port) throws IOException;
	boolean isConnected();
	byte[] sendCommand(String command);
}