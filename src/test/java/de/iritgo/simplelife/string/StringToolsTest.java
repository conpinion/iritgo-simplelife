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


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import java.util.Properties;
import org.junit.Test;


public class StringToolsTest
{
	@Test
	public void empty () throws Exception
	{
		assertThat (StringTools.isTrimEmpty (null), is (true));
		assertThat (StringTools.isTrimEmpty (""), is (true));
		assertThat (StringTools.isTrimEmpty (" "), is (true));
		assertThat (StringTools.isTrimEmpty ("a"), is (false));
		assertThat (StringTools.isTrimEmpty (" a "), is (false));
		assertThat (StringTools.isNotTrimEmpty (""), is (false));
		assertThat (StringTools.isNotTrimEmpty ("a"), is (true));
	}

	@Test
	public void appendIf () throws Exception
	{
		StringBuilder sb = new StringBuilder ("abc");

		StringTools.appendIf (true, sb, "xyz");

		assertThat (sb.toString (), equalTo ("abcxyz"));
	}

	@Test
	public void appendWithDelimiter () throws Exception
	{
		StringBuilder sb = new StringBuilder ("");

		StringTools.appendWithDelimiter (sb, "abc", ",");
		assertThat (sb.toString (), equalTo ("abc"));

		StringTools.appendWithDelimiter (sb, "xyz", ",");
		assertThat (sb.toString (), equalTo ("abc,xyz"));
	}

	@Test
	public void appendArrayWithDelimiter () throws Exception
	{
		StringBuilder sb = new StringBuilder ("");
		String[] strings1 =
		{};

		StringTools.appendWithDelimiter (sb, strings1, ",");

		assertThat (sb.toString (), equalTo (""));

		sb = new StringBuilder ("");
		String[] strings2 =
		{
			"abc"
		};
		StringTools.appendWithDelimiter (sb, strings2, ",");

		assertThat (sb.toString (), equalTo ("abc"));

		sb = new StringBuilder ("");
		String[] strings3 =
		{
						"abc", "efg", "hij"
		};

		StringTools.appendWithDelimiter (sb, strings3, ",");

		assertThat (sb.toString (), equalTo ("abc,efg,hij"));
	}

	@Test
	public void normalizeMACAddressWithColons ()
	{
		assertThat (StringTools.normalizeMACAddress ("1A:2B:3C:4D:5E:6F"), equalTo ("1A:2B:3C:4D:5E:6F"));
	}

	@Test
	public void normalizeMACAddressWithHyphens ()
	{
		assertThat (StringTools.normalizeMACAddress ("1A-2B-3C-4D-5E-6F"), equalTo ("1A:2B:3C:4D:5E:6F"));
	}

	@Test
	public void normalizeMACAddressWithoutDelimiters ()
	{
		assertThat (StringTools.normalizeMACAddress ("1A2B3C4D5E6F"), equalTo ("1A:2B:3C:4D:5E:6F"));
	}

	@Test
	public void normalizeMACAddressWithLowerCaseChars ()
	{
		assertThat (StringTools.normalizeMACAddress ("1a:2b:3c:4d:5e:6f"), equalTo ("1A:2B:3C:4D:5E:6F"));
	}

	@Test
	public void normalizeBadMACAddress ()
	{
		try
		{
			StringTools.normalizeMACAddress ("invalid");
			fail ();
		}
		catch (IllegalArgumentException x)
		{
			// Expected
		}
	}

	@Test
	public void replacteTemplate ()
	{
		String s = "aa ${bb}xx cc $bb #{ff}$!$";
		Properties p = new Properties ();
		p.setProperty ("bb", "123");
		p.setProperty ("ff", "456");

		String replacedTemplate = StringTools.replaceTemplate (s, p);

		assertThat (replacedTemplate, equalTo ("aa 123xx cc $bb 456$!$"));
	}

	@Test
	public void replacteTemplateWithTrimAndExtend ()
	{
		String s = "aa ${bb:+6}xx cc $bb #{ff:6:...}$!$ ${gg:3:.} ${gg:1:. }";
		Properties p = new Properties ();
		p.setProperty ("bb", "123");
		p.setProperty ("ff", "123456789");
		p.setProperty ("gg", "1111");

		String replacedTemplate = StringTools.replaceTemplate (s, p);

		assertThat (replacedTemplate, equalTo ("aa 123   xx cc $bb 123...$!$ 11. 1. "));
	}

	@Test
	public void replacteTemplateWithKeepUnfoundKeys ()
	{
		String s = "aa ${bb}xx cc $bb #{ff}$!$";
		Properties p = new Properties ();
		p.setProperty ("bb", "123");

		String replacedTemplate = StringTools.replaceTemplate (s, p, true);

		assertThat (replacedTemplate, equalTo ("aa 123xx cc $bb #{ff}$!$"));
	}

	@Test
	public void checkRegExForRangeNumbers ()
	{
		assertThat (
						StringTools.getRegexPatternByNumberRangeWithSameNumberLength ("1245", "4723"),
						equalTo ("124[5-9]|12[5-9][0-9]|1[3-9][0-9][0-9]|2[0-9]{3}|3[0-9]{3}|472[0-3]|47[0-1][0-9]|4[0-6][0-9][0-9]"));
		assertThat (StringTools.getRegexPatternByNumberRangeWithSameNumberLength ("9090", "9099"), equalTo ("909[0-9]"));
		assertThat (StringTools.getRegexPatternByNumberRangeWithSameNumberLength ("90", "99"), equalTo ("9[0-9]"));
		assertThat (StringTools.getRegexPatternByNumberRangeWithSameNumberLength ("0", "9"), equalTo ("[0-9]"));
	}
}
