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

package de.iritgo.simplelife.collection;


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import de.iritgo.simplelife.math.IntRange;
import de.iritgo.simplelife.math.Pair;


public class JointIteratorTest
{
	@Test
	public void intSeqCollection () throws Exception
	{
		List<String> l = new LinkedList ();
		l.add ("a");
		l.add ("b");
		l.add ("c");

		JointIterator i = new JointIterator (new IntRange (1).iterator (), l.iterator ());

		assertThat (i.hasNext (), is (true));
		Pair<Integer, String> p = i.next ();
		assertThat (p.get1 (), equalTo (1));
		assertThat (p.get2 (), equalTo ("a"));
		assertThat (i.hasNext (), is (true));
		p = i.next ();
		assertThat (p.get1 (), equalTo (2));
		assertThat (p.get2 (), equalTo ("b"));
		assertThat (i.hasNext (), is (true));
		p = i.next ();
		assertThat (p.get1 (), equalTo (3));
		assertThat (p.get2 (), equalTo ("c"));
		assertThat (i.hasNext (), is (false));
	}

	@Test
	public void emptyArray () throws Exception
	{
		String[] array = new String[]
		{};

		Iterator<String> i = new ArrayIterator<String> (array);

		assertThat (i.hasNext (), is (false));
	}

	@Test
	public void iteration () throws Exception
	{
		String[] array = new String[]
		{
						"a", "b"
		};

		Iterator<String> i = new ArrayIterator<String> (array);

		assertThat (i.hasNext (), is (true));
		assertThat (i.next (), equalTo ("a"));
		assertThat (i.hasNext (), is (true));
		assertThat (i.next (), equalTo ("b"));
		assertThat (i.hasNext (), is (false));
	}
}
