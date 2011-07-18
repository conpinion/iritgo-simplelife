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
import static org.junit.Assert.assertThat;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import de.iritgo.simplelife.data.DataConverter;


public class ElementConvertingListDecoratorTest
{
	@Test
	public void accessor () throws Exception
	{
		List<String> sl = new LinkedList<String> ();
		sl.add ("123");
		sl.add ("456");

		List<Integer> il = new ElementConvertingListDecorator<String, Integer> (sl,
						new DataConverter<String, Integer> ()
						{
							public Integer convertFromType1 (String o)
							{
								return Integer.valueOf (o);
							}

							public String convertFromType2 (Integer o)
							{
								return o.toString ();
							}
						});

		assertThat (il.get (0), equalTo (123));
		assertThat (il.get (1), equalTo (456));
	}
}
