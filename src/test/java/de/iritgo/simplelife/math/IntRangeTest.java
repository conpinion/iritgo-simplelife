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


import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import java.util.Iterator;
import org.junit.Test;


public class IntRangeTest
{
	@Test
	public void seq1to1() throws Exception
	{
		IntRange s = new IntRange(1, 1);

		Iterator<Integer> i = s.iterator();

		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(1));
		assertThat(i.hasNext(), is(false));
	}

	@Test
	public void seq1to3() throws Exception
	{
		IntRange s = new IntRange(1, 3);

		Iterator<Integer> i = s.iterator();

		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(1));
		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(2));
		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(3));
		assertThat(i.hasNext(), is(false));
	}

	@Test
	public void seq1ToInfinite()
	{
		IntRange s = new IntRange(1);

		Iterator<Integer> i = s.iterator();

		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(1));
		assertThat(i.hasNext(), is(true));
		assertThat(i.next(), equalTo(2));
		assertThat(i.hasNext(), is(true));
	}
}
