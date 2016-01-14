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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public abstract class AbstractCommand<T> implements Command<T> {
	public static final byte LF = (byte) '\n';
	public static final byte CR = (byte) '\r';
	public static final String OK = "\n\rOK\n\r";
	public static final byte ACK = 0x06;

	private final String command;
	private final String description;

	protected AbstractCommand(final String command, final String description) {
		this.command = command;
		this.description = description;
	}

	@Override
	public String getCommand() {
		return command;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(command);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof AbstractCommand)) {
			return false;
		}
		final AbstractCommand<?> other = (AbstractCommand<?>) obj;
		return Objects.equals(command, other.command);
	}

	static String readResponse(final InputStream in, final String expected) throws IOException {
		return readResponse(in, expected.getBytes());
	}

	static String readResponse(final InputStream in, final byte[] expected) throws IOException {
		if (expectedResponse(in, expected)) {
			return readString(in);
		}
		return "unavailable";
	}

	static boolean expectedResponse(final InputStream in, final String expected) throws IOException {
		return expectedResponse(in, expected.getBytes());
	}

	static boolean expectedResponse(final InputStream in, final byte[] expected) throws IOException {
		final byte[] buffer = new byte[expected.length];
		final int len = in.read(buffer);
		return len == expected.length && Arrays.equals(expected, buffer);
	}

	static String readString(final InputStream in) throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream(512);
		boolean lfFound = false;
		do {
			final byte b = (byte) in.read();
			if (b == LF) {
				lfFound = true;
			} else if (b == CR && lfFound) {
				break;
			} else {
				lfFound = false;
				out.write(b);
			}
		} while (true);
		return out.toString();
	}

	static byte[] readAck(final InputStream in, final int len, final boolean validateCrc) throws IOException {

		// get the first byte which should be ACK (0x06)
		if (((byte) in.read() != ACK)) {
			throw new IOException("Command invalid");
		}
		final byte[] buffer = new byte[len];
		final int readLen = in.read(buffer);
		if (readLen != buffer.length) {
			throw new IOException("Could not parse the response: " + Arrays.toString(Arrays.copyOfRange(buffer, 0, readLen)));
		}

		if (validateCrc && CRC.checkCRC(buffer.length, buffer) != 0) {
			throw new IOException("Incorrect CRC checksum");
		}
		return buffer;
	}
}
