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

package de.iritgo.simplelife.data;


import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;
import org.junit.Test;


public class KeyedValueTest
{
	@Test
	public void equalKeyCompair()
	{
		KeyedValue<String, String> kv1 = new KeyedValue<String, String>("key", "value1");
		KeyedValue<String, String> kv2 = new KeyedValue<String, String>("key", "value2");

		assertThat(kv1, equalTo(kv2));
		assertThat(kv2, equalTo(kv1));
		assertThat(kv1.hashCode(), equalTo(kv2.hashCode()));
	}

	@Test
	public void notEqualKeyCompair()
	{
		KeyedValue<String, String> kv1 = new KeyedValue<String, String>("key1", "value1");
		KeyedValue<String, String> kv2 = new KeyedValue<String, String>("key2", "value2");

		assertThat(kv1, not(equalTo(kv2)));
		assertThat(kv2, not(equalTo(kv1)));
	}
}
