/**
 * This file is part of the Iritgo/SimpleLife Library.
 *
 * Copyright (C) 2005-2011 Iritgo Technologies.
 * Copyright (C) 2003-2005 BueroByte GbR.
 *
 * Iritgo licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.iritgo.simplelife.math;


/**
 * Useful methods for working with numbers.
 */
public final class NumberTools
{
	/** An array of the first 30 roman values. */
	static String[] romanValues =
	{
					"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV",
					"XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII",
					"XXVIII", "XXIX", "XXX"
	};

	/**
	 * Convert a string to an integer value.
	 * 
	 * @param value The string value.
	 * @param defaultValue Integer value to return if the conversion fails.
	 * @return The integer value.
	 */
	public static int toInt(Object value, int defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return defaultValue;
		}

		try
		{
			return Integer.parseInt(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return defaultValue;
		}
	}

	/**
	 * Convert a string to an integer instance.
	 * 
	 * @param value The string value.
	 * @param defaultValue Integer value to return if the conversion fails.
	 * @return The integer instance.
	 */
	public static Integer toIntInstance(Object value, int defaultValue)
	{
		return new Integer(toInt(value, defaultValue));
	}

	/**
	 * Convert a string to an integer instance.
	 * 
	 * @param value The string value.
	 * @return The integer instance or null.
	 */
	public static Integer toIntInstance(Object value)
	{
		if (value == null)
		{
			return null;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return null;
		}

		try
		{
			return new Integer(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return null;
		}
	}

	/**
	 * Convert a string to a long value.
	 * 
	 * @param value The string value.
	 * @param defaultValue Int value to return if the conversion fails.
	 * @return The long value.
	 */
	public static long toLong(Object value, long defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return defaultValue;
		}

		try
		{
			return Long.parseLong(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return defaultValue;
		}
	}

	/**
	 * Convert a string to a long instance.
	 * 
	 * @param value The string value.
	 * @param defaultValue Long value to return if the conversion fails.
	 * @return The long instance.
	 */
	public static Long toLongInstance(Object value, long defaultValue)
	{
		return new Long(toLong(value, defaultValue));
	}

	/**
	 * Convert a string to a long instance.
	 * 
	 * @param value The string value.
	 * @return The long instance or null.
	 */
	public static Long toLongInstance(Object value)
	{
		if (value == null)
		{
			return null;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return null;
		}

		try
		{
			return new Long(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return null;
		}
	}

	/**
	 * Convert a string to a double value.
	 * 
	 * @param value The string value.
	 * @param defaultValue Double value to return if the conversion fails.
	 * @return The double value.
	 */
	public static double toDouble(Object value, double defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return defaultValue;
		}

		try
		{
			return Double.parseDouble(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return defaultValue;
		}
	}

	/**
	 * Convert a string to a double instance.
	 * 
	 * @param value The string value.
	 * @param defaultValue Double value to return if the conversion fails.
	 * @return The double instance.
	 */
	public static Double toDoubleInstance(Object value, double defaultValue)
	{
		return new Double(toDouble(value, defaultValue));
	}

	/**
	 * Convert a string to a double instance.
	 * 
	 * @param value The string value.
	 * @return The double instance.
	 */
	public static Double toDoubleInstance(Object value)
	{
		if (value == null)
		{
			return null;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return null;
		}

		try
		{
			return new Double(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return null;
		}
	}

	/**
	 * Convert a string to a boolean value.
	 * 
	 * @param value The string value.
	 * @param defaultValue Boolean value to return if the conversion fails.
	 * @return The boolean value.
	 */
	public static boolean toBool(Object value, boolean defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return defaultValue;
		}

		strValue = strValue.trim().toLowerCase();

		if ("on".equals(strValue) || "true".equals(strValue) || "1".equals(strValue))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Convert a string to a boolean instance.
	 * 
	 * @param value The string value.
	 * @param defaultValue Boolean value to return if the conversion fails.
	 * @return The boolean instance.
	 */
	public static Boolean toBoolInstance(Object value, boolean defaultValue)
	{
		return new Boolean(toBool(value, defaultValue));
	}

	/**
	 * Convert a string to a boolean instance.
	 * 
	 * @param value The string value.
	 * @return The boolean instance.
	 */
	public static Boolean toBoolInstance(Object value)
	{
		if (value == null)
		{
			return null;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return null;
		}

		try
		{
			return new Boolean(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return null;
		}
	}

	/**
	 * Convert a string to a float value.
	 * 
	 * @param value The string value.
	 * @param defaultValue Float value to return if the conversion fails.
	 * @return The float value.
	 */
	public static float toFloat(Object value, float defaultValue)
	{
		if (value == null)
		{
			return defaultValue;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return defaultValue;
		}

		try
		{
			return Float.parseFloat(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return defaultValue;
		}
	}

	/**
	 * Convert a string to a float instance.
	 * 
	 * @param value The string value.
	 * @param defaultValue Float value to return if the conversion fails.
	 * @return The float instance.
	 */
	public static Float toFloatInstance(Object value, float defaultValue)
	{
		return new Float(toFloat(value, defaultValue));
	}

	/**
	 * Convert a string to a float instance.
	 * 
	 * @param value The string value.
	 * @return The float instance.
	 */
	public static Float toFloatInstance(Object value)
	{
		if (value == null)
		{
			return null;
		}

		String strValue = value.toString();

		if (strValue.length() == 0)
		{
			return null;
		}

		try
		{
			return new Float(strValue.trim());
		}
		catch (NumberFormatException x)
		{
			return null;
		}
	}

	/**
	 * Translate an integer to a roman number. Only the first 30 numbers are
	 * available.
	 * 
	 * @param value The value to convert.
	 * @return A roman string representation of the value.
	 */
	public static String romanNumber(int value)
	{
		if (value >= 30)
		{
			return String.valueOf(value);
		}

		return romanValues[value - 1];
	}
}
