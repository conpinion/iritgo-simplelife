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


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import org.junit.Test;
import de.iritgo.simplelife.math.Predicate;


public class CollectionToolsTest
{
	@Test
	public void find () throws Exception
	{
		ArrayList<Integer> c = new ArrayList ();
		c.add (1);
		c.add (3);
		c.add (4);
		c.add (5);

		Integer i = CollectionTools.find (c, new Predicate<Integer> ()
		{
			public Boolean eval (Integer x)
			{
				return x % 2 == 0;
			}
		});

		assertThat (i, notNullValue ());
		assertThat (i, equalTo (4));
	}
}
