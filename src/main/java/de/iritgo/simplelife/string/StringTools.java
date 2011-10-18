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

package de.iritgo.simplelife.string;


import java.io.*;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.*;
import de.iritgo.simplelife.math.NumberTools;
import de.iritgo.simplelife.tools.Option;


/**
 * Useful string methods.
 */
public final class StringTools
{
	/** Random string used to encode/decode a string. */
	private static String codeKey = "Gss654gsf$2f24a$312GKHGskgjgcztwwugd86f3u3j3v##..,fgsf732tg2wdf"
					+ "sdudufj476633sdewvnv88304kb-ndm,tnx763jnKDjhm,S,Jdzhenncgflr.md."
					+ "fjczucGGshz36lcvsdfdsgh4734gcmSdfm,dhw62gcklghdks;.;sjcj/dfhd6%&"
					+ "Jhggdd6%&ddhdjcbst%&ielf,b.nm/&82hrghcm:cngst2364rivn:,jnvhs63Hh";

	/** Hex characters. */
	private static String hexCodes = "0123456789ABCDEF";

	/** Regular expression: Strings of digits and letters. */
	private static Pattern reOnlyCharsAndNumbers = Pattern.compile ("\\w+");

	/** Regular expression: Template variables ${var}. */
	private static Pattern reReplaceTemplateVariables = Pattern.compile ("([$#]\\{([.&[^\\{\\}]]*)\\})");

	/**
	 * Regular expression: For Template to trim or extends (with space) the
	 * length.
	 */
	private static Pattern reTrimOrExtendTemplateVariables = Pattern.compile ("^(.+):(\\+?)([0-9]+):?(\\D*)");

	/** Regular expression: For the format function %5:5s. */
	private static Pattern reStringFormat = Pattern.compile ("((%([0-9]+)):(.??[0-9]+.?))");

	/** Regular expression: MAC address */
	private static Pattern reMACAddress1 = Pattern.compile ("\\w\\w(:\\w\\w){5}");

	/** Regular expression: MAC address */
	private static Pattern reMACAddress2 = Pattern.compile ("\\w\\w(-\\w\\w){5}");

	/** Regular expression: MAC address */
	private static Pattern reMACAddress3 = Pattern.compile ("(\\w\\w){6}");

	/**
	 * Check whether 'test' is a substring of 'text'.
	 *
	 * @param text The text to check.
	 * @param test The substring to find.
	 * @return True if 'test' is part of 'text'.
	 */
	public static boolean contains (String text, String test)
	{
		return text.indexOf (test) != - 1;
	}

	/**
	 * Check whether a string is empty (length == 0) or not.
	 *
	 * @return True if the supplied string is empty.
	 */
	public static boolean isEmpty (Object text)
	{
		return (text == null) || (text.toString ().length () == 0);
	}

	/**
	 * Check whether a trimmed string is empty (length == 0) or not.
	 *
	 * @return True if the supplied string is empty.
	 */
	public static boolean isTrimEmpty (Object text)
	{
		return (text == null) || (text.toString ().trim ().length () == 0);
	}

	/**
	 * Check whether a trimmed string is not empty (!= null && length != 0).
	 *
	 * @return True if the supplied string is not empty.
	 */
	public static boolean isNotTrimEmpty (Object text)
	{
		return ! isTrimEmpty (text);
	}

	/**
	 * Trim a string.
	 *
	 * @param text The string to trim.
	 * @param defaultValue Return this string if text is null.
	 * @return The trimmed string.
	 */
	public static String trim (Object text, String defaultValue)
	{
		if (text == null)
		{
			return defaultValue;
		}

		return text.toString ().trim ();
	}

	/**
	 * Trim a string.
	 *
	 * @param text The string to trim.
	 * @return The trimmed string. If text is null, the empty string is
	 *         returned.
	 */
	public static String trim (Object text)
	{
		return trim (text, "");
	}

	/**
	 * Returns a new string with the first character converted to upper case.
	 *
	 * @param value The string to convert.
	 * @return The converted string.
	 */
	public static String initialUpperCase (String value)
	{
		return Character.toUpperCase (value.charAt (0)) + value.substring (1);
	}

	/**
	 * Returns a new string with the first character converted to lower case.
	 *
	 * @param value The string to convert.
	 * @return The converted string.
	 */
	public static String initialLowerCase (String value)
	{
		return Character.toLowerCase (value.charAt (0)) + value.substring (1);
	}

	/**
	 * Count the number of occurrences of a single character.
	 *
	 * @param text The string to check.
	 * @param ch The character to count.
	 * @return The number of occurrences of the character.
	 */
	public static int countChar (String text, char ch)
	{
		int count = 0;
		int index = - 1;

		while ((index = text.indexOf (ch, index + 1)) != - 1)
		{
			++count;
		}

		return count;
	}

	/**
	 * Encode the specified string.
	 *
	 * @param s This is the string to encode.
	 * @return The encoded string.
	 */
	public static String encode (String s)
	{
		String strCoded = new String ();

		for (int i = 0; i < s.length (); ++i)
		{
			String codePart = new String ();
			int code = 0;
			int keypos = 0;

			for (keypos = 0; true; ++keypos)
			{
				code = (int) s.charAt (i) ^ (int) codeKey.charAt ((i + keypos) % 256);

				if (code != 0)
				{
					break;
				}
			}

			codePart += intToHex (keypos, 2);
			codePart += intToHex (code, 4);

			strCoded += codePart;
		}

		return strCoded;
	}

	/**
	 * Decode the specified string.
	 *
	 * @param s This is the string to decode.
	 * @return The decoded string.
	 */
	public static String decode (String s)
	{
		if (s == null)
		{
			return null;
		}

		String strDecoded = new String ();

		for (int i = 0; i < s.length (); i += 6)
		{
			int code;
			int keypos;
			String codePartKey = s.substring (i, i + 2);
			String codePartCode = s.substring (i + 2, i + 6);

			keypos = hexToInt (codePartKey);
			code = hexToInt (codePartCode);

			char addChar = (char) ((int) code ^ (int) codeKey.charAt (((i / 6) + keypos) % 256));

			strDecoded += addChar;
		}

		return strDecoded;
	}

	/**
	 * Convert an integer value to a hex string.
	 *
	 * @param value The value to convert.
	 * @param numChars The number of chars (nibbles) that should be converted.
	 * @return The hex string.
	 */
	public static String intToHex (int value, int numChars)
	{
		String hex = new String ();

		long mask = ((1 << ((numChars) * 4)) - 1);

		value &= mask;

		for (int i = numChars - 1; i >= 0; --i)
		{
			int nibbleValue = value >> (i * 4);

			hex += hexCodes.charAt (nibbleValue);
			value &= ((1 << (i * 4)) - 1);
		}

		return hex;
	}

	/**
	 * Convert a hex string to an integer value .
	 *
	 * @param s The hexString to convert.
	 * @return The integer value.
	 */
	public static int hexToInt (String s)
	{
		int dez = 0;
		int value = 0;
		char code;

		s = s.toUpperCase ();

		for (int i = s.length (); i > 0; --i)
		{
			code = s.charAt (s.length () - i);

			for (dez = 0; dez < hexCodes.length (); ++dez)
			{
				if (code == hexCodes.charAt (dez))
				{
					break;
				}
			}

			if (dez >= hexCodes.length ())
			{
				return - 1;
			}

			value += (dez * (1 << ((i - 1) * 4)));
		}

		return value;
	}

	/**
	 * Escape a string for use with regular expressions. This method escapes all
	 * meta characters.
	 *
	 * @param s The string to convert.
	 * @return The escaped string.
	 */
	public static String escpapePattern (String s)
	{
		return s.replaceAll ("(\\$|\\{|\\})", "\\\\$1");
	}

	/**
	 * Create a string that contains 'repeat' occurrences of the string 's'.
	 *
	 * @param s The base string.
	 * @param repeat The number of concatenations.
	 * @return The new string.
	 */
	public static String createStringRepeat (String s, int repeat)
	{
		StringBuffer sb = new StringBuffer ();

		for (int i = 0; i < repeat; ++i)
		{
			sb.append (s);
		}

		return sb.toString ();
	}

	/**
	 * Convert the parameter to a string array.
	 *
	 * @param value Can be a String an Object array or null.
	 * @return A String array (with length = 0 if the supplied value is null).
	 */
	public static String[] toStringArray (Object value)
	{
		if (value == null)
		{
			return new String[0];
		}
		else if (value instanceof Object[])
		{
			Object[] v = (Object[]) value;
			String[] res = new String[v.length];

			for (int i = 0; i < v.length; ++i)
			{
				res[i] = v[i].toString ();
			}

			return res;
		}
		else
		{
			return new String[]
			{
				value.toString ()
			};
		}
	}

	/**
	 * Check if the given string only contains chars, numbers and "_"
	 *
	 * @param s The string to check.
	 * @return True if the string is valid.
	 */
	public static boolean isOnlyCharsAndNumbers (String s)
	{
		Matcher m = reOnlyCharsAndNumbers.matcher (s);

		return m.matches ();
	}

	/**
	 * Convert the given string so that it only contains chars, numbers and "_".
	 * Every other character will be deleted.
	 *
	 * @param s The string to convert.
	 * @return The converted string.
	 */
	public static String toOnlyCharsAndNumbers (String s)
	{
		return s.replaceAll ("[^\\w\\d_]", "");
	}

	/**
	 * Check to strings for equality. The null string (null) is regarded as
	 * being equal to the empty string ("").
	 *
	 * @param s1 The first string to check.
	 * @param s2 The second string to check. return True if the strings are
	 *            equal.
	 */
	public static boolean equals (String s1, String s2)
	{
		return ((s1 == null) ? "" : s1).equals (((s2 == null) ? "" : s2));
	}

	/**
	 * Replace all occurrences of a substring with another string.
	 *
	 * @param text The string in which to replace the substring.s
	 * @param from The search pattern (regular expression).
	 * @param to The replacement string.
	 */
	public static String replaceAll (String text, String from, String to)
	{
		if (text == null)
		{
			return text;
		}

		return text.replaceAll (from, to);
	}

	/**
	 * Replace variables (${name} of #{name}) with property values.
	 *
	 * @param text String to convert.
	 * @return The converted string.
	 */
	public static String replaceTemplate (String text, Map map)
	{
		return replaceTemplate (text, map, false);
	}

	/**
	 * Replace variables (${name} of #{name}) with property values. It can cut
	 * the destination variable by ${name:x} or append spaces by ${name:+x}.
	 * Also you can add a "cutString" by e.g. ${name:x:...}
	 *
	 * @param text String to convert.
	 * @param keepUnfoundKeys If true the template key will not be deleted
	 * @return The converted string.
	 */
	public static String replaceTemplate (String text, Map map, boolean keepUnfoundKeys)
	{
		StringBuffer buffer = new StringBuffer ();
		Matcher m = reReplaceTemplateVariables.matcher (text);
		int pos = 0;

		while (m.find (pos))
		{
			buffer.append (text.substring (pos, m.start ()));

			String propertyName = m.group (2) != null ? m.group (2) : m.group (3);
			String value = null;

			Matcher m2 = reTrimOrExtendTemplateVariables.matcher (propertyName);
			int trimOrExtend = - 1;

			if (m2.matches ())
			{
				propertyName = m2.group (1);
				trimOrExtend = Integer.parseInt (m2.group (3));
			}

			if (map.get (propertyName) != null || ! keepUnfoundKeys)
			{
				if (trimOrExtend > 0)
				{
					value = trim (map.get (propertyName));

					if (! isTrimEmpty (m2.group (2)) && value.length () < trimOrExtend)
					{
						if (isTrimEmpty (value))
						{
							value = createStringRepeat (" ", trimOrExtend);
						}
						else
						{
							value = value.concat (createStringRepeat (" ", trimOrExtend - value.length ()));
						}
					}
					else
					{
						String cutString = m2.group (4);

						if (! isTrimEmpty (cutString))
						{
							if (cutString.length () < trimOrExtend)
							{
								value = value.substring (0, Math.min (Math.max (trimOrExtend - cutString.length (), 0),
												value.length ()))
												+ cutString;
							}
							else if (trimOrExtend < value.length ())
							{
								value = value.substring (0, Math.min (Math.max (trimOrExtend, 0), value.length ()))
												+ cutString;
							}
							else
							{
								if (StringTools.isTrimEmpty (value))
								{
									value = cutString;
								}
								else
								{
									value = value.substring (0, 1) + cutString;
								}
							}
						}
						else
						{
							if (StringTools.isTrimEmpty (value))
							{
								value = "";
							}
							else
							{
								value = value.substring (0, Math.min (Math.max (trimOrExtend, 0), value.length ()));
							}
						}
					}

					if (isTrimEmpty (map.get (propertyName)))
					{
						value = "";
					}
				}
				else
				{
					value = trim (map.get (propertyName));
				}
			}
			else
			{
				value = m.group (1);
			}

			buffer.append (value);
			pos = m.end ();
		}

		buffer.append (text.substring (pos, text.length ()));

		return buffer.toString ();
	}

	/**
	 * Replace a given text with an object array. In the text you can use {0}
	 * for seq. replacement
	 *
	 * @param text The text
	 * @param reReplaceTemplateVariables The given outbound pattern
	 * @param reTrimOrExtendTemplateVariables The given inbound pattern
	 * @param keepUnfoundKeys
	 * @param params The object param list
	 * @return The converted string
	 */
	public static String replaceTemplate (String text, Pattern reReplaceTemplateVariables,
					Pattern reTrimOrExtendTemplateVariables, boolean keepUnfoundKeys, Object... params)
	{
		StringBuffer buffer = new StringBuffer ();
		Matcher m = reReplaceTemplateVariables.matcher (text);
		int pos = 0;

		while (m.find (pos))
		{
			buffer.append (text.substring (pos, m.start ()));

			String propertyName = m.group (2) != null ? m.group (2) : m.group (3);
			String value = null;

			Matcher m2 = reTrimOrExtendTemplateVariables.matcher (propertyName);
			int trimOrExtend = - 1;

			if (m2.matches ())
			{
				propertyName = m2.group (1);
				trimOrExtend = Integer.parseInt (m2.group (3));
			}

			int propIndex = Integer.parseInt (propertyName);

			String paramValue = "";

			if (propIndex < params.length)
			{
				paramValue = params[propIndex].toString ();
			}

			if (propIndex > params.length || ! keepUnfoundKeys)
			{
				if (trimOrExtend > 0)
				{
					value = trim (paramValue);

					if (! isTrimEmpty (m2.group (2)) && value.length () < trimOrExtend)
					{
						value = value.concat (createStringRepeat (" ", trimOrExtend - value.length ()));
					}
					else
					{
						String cutString = m2.group (4);

						if (! isTrimEmpty (cutString) && value.length () > cutString.length ())
						{
							value = value.substring (0, trimOrExtend - cutString.length ()) + cutString;
						}
						else
						{
							value = value.substring (0, trimOrExtend);
						}
					}
				}
				else
				{
					value = trim (paramValue);
				}
			}
			else
			{
				value = m.group (1);
			}

			buffer.append (value);
			pos = m.end ();
		}

		buffer.append (text.substring (pos, text.length ()));

		return buffer.toString ();
	}

	/**
	 * Append a string to an appendable and separate each appended string with
	 * the specified delimiter:
	 *
	 * StringTools.appendWithDelimiter ("aaa", ":");
	 * StringTools.appendWithDelimiter ("bbb", ":");
	 * StringTools.appendWithDelimiter ("ccc", ":");
	 *
	 * results in "aaa:bbb:ccc".
	 *
	 * @param buf The appendable to which the text is appended.
	 * @param len The current length of the appendable.
	 * @param s The text to append.
	 * @param delim The delimiter to append.
	 */
	public static void appendWithDelimiter (Appendable buf, int len, String s, String delim)
	{
		appendIf ((len > 0) && ! isTrimEmpty (s), buf, delim);

		try
		{
			buf.append (s);
		}
		catch (IOException e)
		{
		}
	}

	/**
	 * @see StringTools#appendWithDelimiter(Appendable, int, String, String)
	 *
	 *      Convenience method that takes a StringBuffer.
	 */
	public static void appendWithDelimiter (StringBuffer buf, String s, String delim)
	{
		appendWithDelimiter (buf, buf.length (), s, delim);
	}

	/**
	 * @see StringTools#appendWithDelimiter(Appendable, int, String, String)
	 *
	 *      Convenience method that takes a StringBuilder.
	 */
	public static void appendWithDelimiter (StringBuilder buf, String s, String delim)
	{
		appendWithDelimiter (buf, buf.length (), s, delim);
	}

	/**
	 * Connecatenate all provided strings and place the delimiter between them.
	 *
	 * @param buf The StringBuilder to append to
	 * @param strings The strings to concatenate
	 * @param delim The delimiter
	 */
	public static void appendWithDelimiter (StringBuilder buf, String[] strings, String delim)
	{
		for (String string : strings)
		{
			appendWithDelimiter (buf, string, delim);
		}
	}

	/**
	 * Append a string to an appendable only if the given predicate is true.
	 *
	 * @param p The predicate.
	 * @param buf The appendable.
	 * @param s The string to add.
	 */
	public static void appendIf (boolean p, Appendable buf, String s)
	{
		if (p)
		{
			try
			{
				buf.append (s);
			}
			catch (IOException e)
			{
			}
		}
	}

	/**
	 * Concatenate all elements in the given collection. Elements are separated
	 * with the specified delimiter. Each element is converted to a string with
	 * the specified converter.
	 *
	 * @param values The string array to convert to a string.
	 * @param delim The delimiter.
	 * @param converter The string converter to use.
	 * @return The concatenated string.
	 */
	public static String concatWithDelimiter (Collection<?> values, String delim, StringConverter converter)
	{
		StringBuilder buf = new StringBuilder ();

		for (Object value : values)
		{
			appendWithDelimiter (buf, converter.toString (value), delim);
		}

		return buf.toString ();
	}

	/**
	 * Concatenate all elements in the given collection. Elements are separated
	 * with the specified delimiter. Each element is converted to a string with
	 * the toString() method.
	 *
	 * @param values The string array to convert to a string.
	 * @param delim The delimiter.
	 * @return The concatenated string.
	 */
	public static String concatWithDelimiter (Collection<?> values, String delim)
	{
		return concatWithDelimiter (values, delim, new SimpleStringConverter ());
	}

	/**
	 * Normalize a MAC address. A normilized MAC address matches the pattern
	 * "dd:dd:dd:dd:dd:dd:dd:dd".
	 *
	 * @param macAddress The MAC address to normalize
	 * @return The normalized MAC address
	 */
	public static String normalizeMACAddress (String macAddress)
	{
		if (isTrimEmpty (macAddress))
		{
			throw new IllegalArgumentException ("Unknwon MAC address format");
		}

		if (reMACAddress1.matcher (macAddress).matches ())
		{
			return macAddress.toUpperCase ();
		}

		if (reMACAddress2.matcher (macAddress).matches ())
		{
			return macAddress.replace ('-', ':').toUpperCase ();
		}

		if (reMACAddress3.matcher (macAddress).matches ())
		{
			StringBuilder newMACAddress = new StringBuilder ();

			for (int i = 0; (i + 1) < macAddress.length (); i += 2)
			{
				appendWithDelimiter (newMACAddress, macAddress.substring (i, i + 2), ":");
			}

			return newMACAddress.toString ().toUpperCase ();
		}

		throw new IllegalArgumentException ("Unknwon MAC address format");
	}

	/**
	 * Create a stack trace string from a throwable.
	 *
	 * @param t The throwable
	 * @return The stack strace string
	 */
	public static String stackTraceToString (Throwable t)
	{
		StringWriter stw = new StringWriter ();
		PrintWriter pw = new PrintWriter (stw);

		t.printStackTrace (pw);

		return stw.getBuffer ().toString ();
	}

	/**
	 * Create a hex SHA1 digest from the specified string.
	 *
	 * @param value The string to digest
	 * @return The digest, i.e. hex(sha1(value)). If the supplied value is null,
	 *         null is returned
	 */
	public static final String digest (String value)
	{
		if (value == null)
		{
			return null;
		}

		String digest = null;

		try
		{
			MessageDigest messageDigest = MessageDigest.getInstance ("SHA1");

			digest = bytesToHex (messageDigest.digest (value.getBytes ()));
		}
		catch (Exception e)
		{
		}

		return digest;
	}

	/**
	 * Create a hex SHA1 digest from the specified string.
	 *
	 * @param value The string to digest
	 * @return The digest, i.e. hex(sha1(value)). If the supplied value is null,
	 *         null is returned
	 */
	public static final String digest (Object value)
	{
		if (value == null)
		{
			return null;
		}

		return digest (trim (value));
	}

	/**
	 * This utility method is passed an array of bytes. It returns this array as
	 * a String in hexadecimal format.
	 *
	 * @param data The data bytes
	 * @return The hex string
	 */
	public static String bytesToHex (byte[] data)
	{
		StringBuffer retval = new StringBuffer ();

		for (int i = 0; i < data.length; i++)
		{
			retval.append (hexCodes.charAt ((data[i] >> 4) & 0x0F));
			retval.append (hexCodes.charAt (data[i] & 0x0F));
		}

		return retval.toString ();
	}

	/**
	 * Extends the String.format function to cut to the string to a given
	 * length. Use %5:5s to to cut at 5 characters
	 *
	 * @param text The format text
	 * @param objects The objects
	 * @return The formated string
	 */
	public static String format (String text, String[] objects)
	{
		return format (text, objects, true);
	}

	/**
	 * Extends the String.format function to cut to the string to a given
	 * length. Use %5:5s to to cut at 5 characters
	 *
	 * @param text The format text
	 * @param objects The objects
	 * @param leftCut Is false the string will cutted from right
	 * @return The formated string
	 */
	public static String format (String text, String[] objects, boolean leftCut)
	{
		StringBuffer buffer = new StringBuffer ();
		Matcher m = reStringFormat.matcher (text);
		int pos = 0;
		int objectPos = 0;

		while (m.find (pos))
		{
			buffer.append (text.substring (pos, m.start ()));

			int cut = NumberTools.toInt (m.group (3), 0);

			buffer.append ("%" + m.group (4));

			if (leftCut)
			{
				objects[objectPos] = objects[objectPos].substring (0, objects[objectPos].length () > cut ? cut
								: objects[objectPos].length ());
			}
			else
			{
				if (objectPos > objects.length)
				{
					break;
				}

				int cutting = objects[objectPos].length () - cut;

				if (cutting > 0)
				{
					objects[objectPos] = objects[objectPos].substring (cutting, objects[objectPos].length ());
				}
			}

			pos = m.end ();
			++objectPos;
		}

		return String.format (buffer.toString (), (Object[]) objects);
	}

	/**
	 * Trim and convert to lower case
	 *
	 * @param value The object
	 * @return The lower case string
	 */
	public static String toLowerCase (Object value)
	{
		return trim (value).toLowerCase ();
	}

	/**
	 * Same as String.substring (pos), but doesn't throw any exception. If pos
	 * is < 0, s is returned. If pos is > s.length (), an empty string is
	 * returned.
	 *
	 * @param s The string to substring
	 * @param pos The starting position
	 * @return The substring
	 */
	public static String substring (String s, int pos)
	{
		if (pos >= 0 && pos <= s.length ())
		{
			return s.substring (pos);
		}

		if (pos > s.length ())
		{
			return "";
		}

		return s;
	}

	/**
	 * Return a string with only numbers. All other chars are filtered out
	 *
	 * @param s The string
	 * @return The number string
	 */
	public static String getOnlyNumbers (String s)
	{
		return s.replaceAll ("[^\\d]", "");
	}

	/**
	 * Return a common prefix from the given strings. Like this: "test1234" -
	 * "test5332" -> "test"
	 *
	 * @param s1 The first string
	 * @param s2 the second string
	 * @return The common prefix from the strings
	 */
	public static String getCommonPrefix (String s1, String s2)
	{
		int i = 0;

		while (! s2.startsWith (s1.substring (0, s1.length () - i)))
		{
			++i;

			if (i > s1.length ())
			{
				return "";
			}
		}

		return s1.substring (0, s1.length () - i);
	}

	/**
	 * Return a regex. pattern for a given range of numbers. The numbers must
	 * have the same length. Sample: start: 1245 stop: 4723 result:
	 * 124[5-9]|12[5
	 * -9][0-9]|1[3-9][0-9][0-9]|2[0-9]{3}|3[0-9]{3}|472[0-3]|47[0-1
	 * ][0-9]|4[0-6][0-9][0-9]
	 *
	 * @param start The start range
	 * @param stop The stop range
	 * @return The pattern for the range of numbers
	 */
	public static String getRegexPatternByNumberRangeWithSameNumberLength (String start, String stop)
	{
		String prefix = getCommonPrefix (start, stop);
		StringBuilder pattern = new StringBuilder ();

		start = start.substring (prefix.length (), start.length ());
		stop = stop.substring (prefix.length (), stop.length ());

		if (start.length () > 1)
		{
			genMin (start, pattern, 0, prefix);
			genMid (start, stop, pattern, prefix);
			genHigh (stop, pattern, 0, prefix);

			return pattern.toString ().substring (0, pattern.toString ().length () - 1);
		}
		else
		{
			return prefix + "[" + start + "-" + stop + "]";
		}
	}

	public static void genMin (String number, StringBuilder pattern, int counter, String prefix)
	{
		if (number.length () == 1)
		{
			return;
		}

		String high = number.substring (0, number.length () - 1);
		String current = number.substring (number.length () - 1, number.length ());

		if (Integer.parseInt (current) + 1 > 9)
		{
			pattern.append (prefix + high + "[0-9]");
		}
		else if (counter == 0)
		{
			pattern.append (prefix + high + "[" + (Integer.parseInt (current)) + "-9]");
		}
		else
		{
			pattern.append (prefix + high + "[" + (Integer.parseInt (current) + 1) + "-9]");
		}

		for (int i = 0; i < counter; ++i)
		{
			pattern.append ("[0-9]");
		}

		pattern.append ("|");
		++counter;
		genMin (high, pattern, counter, prefix);
	}

	public static void genMid (String start, String stop, StringBuilder pattern, String prefix)
	{
		int startNext = Integer.valueOf (start.substring (0, 1)) + 1;
		int stopPrev = Integer.valueOf (stop.substring (0, 1)) - 1;

		for (int i = startNext; i <= stopPrev; ++i)
		{
			pattern.append (prefix + i + "[0-9]{" + (start.length () - 1) + "}|");
		}
	}

	public static void genHigh (String number, StringBuilder pattern, int counter, String prefix)
	{
		if (number.length () == 1)
		{
			return;
		}

		String high = number.substring (0, number.length () - 1);
		String current = number.substring (number.length () - 1, number.length ());

		if (Integer.parseInt (current) - 1 < 1)
		{
			pattern.append (prefix + high + "0");
		}
		else if (counter == 0)
		{
			pattern.append (prefix + high + "[0-" + (Integer.parseInt (current)) + "]");
		}
		else
		{
			pattern.append (prefix + high + "[0-" + (Integer.parseInt (current) - 1) + "]");
		}

		for (int i = 0; i < counter; ++i)
		{
			pattern.append ("[0-9]");
		}

		pattern.append ("|");
		++counter;
		genHigh (high, pattern, counter, prefix);
	}

	public static Matcher match (Pattern pattern, String s)
	{
		Matcher matcher = pattern.matcher (s);
		matcher.matches ();
		return matcher;
	}
}
