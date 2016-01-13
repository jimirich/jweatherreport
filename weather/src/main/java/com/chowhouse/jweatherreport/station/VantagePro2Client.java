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

package com.chowhouse.jweatherreport.station;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;

public interface VantagePro2Client extends Closeable {
	void connect() throws IOException;
	boolean isConnected();
	boolean testConnection() throws IOException;
	String getFirmwareDate() throws IOException;
	String getFirmwareVersion() throws IOException;
	HighLow getHighsLows() throws IOException;
	Loop getLoop(int packets) throws IOException;
	Loop2 getLoop2(int packets) throws IOException;
	LocalDateTime getTime() throws IOException;
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