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
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class PairObjectArrayConverterTest
{
	@Test
	public void conversionFromPairToObjectArray () throws Exception
	{
		Pair<Integer, Integer> pair = new Pair<Integer, Integer> (123, 456);

		Object[] array = new PairObjectArrayConverter<Integer, Integer> ().convertFromType1 (pair);

		assertThat (array.length, equalTo (2));
		assertThat ((Integer) array[0], equalTo (123));
		assertThat ((Integer) array[1], equalTo (456));
	}

	@Test
	public void conversionFromObjectArrayToPair () throws Exception
	{
		Object[] array = new Object[]
		{
						123, 456
		};

		Pair<Integer, Integer> pair = new PairObjectArrayConverter<Integer, Integer> ().convertFromType2 (array);

		assertThat (pair.get1 (), equalTo (123));
		assertThat (pair.get2 (), equalTo (456));
	}
}
