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

import java.time.LocalTime;

public class Utils {

	public static int setInteger(byte one, byte two) {
		int integer = two & 0xFF;
		integer = integer << 8;
		integer = integer | (one & 0xFF);
		return integer;
	}

	public static LocalTime setTime(byte one, byte two) {
		int num = two & 0xFF;
		num = num << 8;
		num = num | (one & 0xFF);
		String time = String.format("%04d", num);
		return LocalTime.of(Integer.parseInt(
				time.substring(0, 2)), Integer.parseInt(time.substring(2)));
	}
}
