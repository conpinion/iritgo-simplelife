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


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import de.iritgo.simplelife.data.Tuple2;


public class TupleTest
{
	@Test
	public void equality() throws Exception
	{
		Tuple2<String, Integer> a = new Tuple2("a", 1);
		Tuple2<String, Integer> b = new Tuple2("a", 1);
		Tuple2<String, Integer> c = new Tuple2("a", 2);
		Tuple2<String, Integer> d = new Tuple2("b", 1);
		Tuple2<String, Integer> e = new Tuple2(null, 1);
		Tuple2<String, Integer> f = new Tuple2(null, 1);
		Tuple2<String, Integer> g = new Tuple2("a", null);
		Tuple2<String, Integer> h = new Tuple2("a", null);
		Tuple2<String, Integer> i = new Tuple2(null, null);
		Tuple2<String, Integer> j = new Tuple2(null, null);

		assertThat(a, equalTo(a));
		assertThat(a, equalTo(b));
		assertThat(e, equalTo(f));
		assertThat(g, equalTo(h));
		assertThat(i, equalTo(j));
		assertThat(a, not(equalTo(c)));
		assertThat(a, not(equalTo(d)));
		assertThat(a, not(equalTo(e)));
		assertThat(a, not(equalTo(g)));
		assertThat(a, not(equalTo(i)));
	}
}
