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

import java.io.IOException;
import java.io.InputStream;

/**
 * Represents a command to be executed. The response from the command will be available to be read from the input.
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public interface Command<T> {

	/**
	 * Executes the command and returns the response.
	 *
	 * @param in the stream used to read the response from
	 *
	 * @return the response to the command
	 *
	 * @throws IOException if an error occurs reading the response
	 */
	T execute(InputStream in) throws IOException;

	/**
	 * The command to execute.
	 *
	 * @return the command
	 */
	String getCommand();

	/**
	 * A friendly description for the command.
	 *
	 * @return the commands description
	 */
	String getDescription();
}
