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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class CommandTestCase {

	@Test
	public void testFirmwareVersion() throws Exception {
		final String expectedResponse = "4.2";
		Assert.assertEquals(expectedResponse, StandardCommands.FIRMWARE_VERSION.execute(createOkResponse(expectedResponse)));
	}

	private InputStream createOkResponse(final String value) throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		out.write(AbstractCommand.OK.getBytes(StandardCharsets.UTF_8));
		out.write(value.getBytes(StandardCharsets.UTF_8));

		// Responses should end with \n\r
		out.write((byte) '\n');
		out.write((byte) '\r');
		return new ByteArrayInputStream(out.toByteArray());
	}
}
