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


import de.iritgo.simplelife.data.DataConverter;


/**
 * Data converter that converts between pairs and two-dimensional object arrays.
 * The conversion is defined as p.getFirst () == (Type1) a[0] and p.getSecond ()
 * == (Type2) a[1] for all p in Pair<Type1, Type2> and a in Object[2].
 */
public class ObjectArrayPairConverter<Type1, Type2> implements DataConverter<Object[], Pair<Type1, Type2>>
{
	public Pair<Type1, Type2> convertFromType1 (Object[] o)
	{
		if (o != null)
		{
			return new Pair<Type1, Type2> ((Type1) o[0], (Type2) o[1]);
		}
		else
		{
			return null;
		}
	}

	public Object[] convertFromType2 (Pair<Type1, Type2> o)
	{
		if (o != null)
		{
			return new Object[]
			{
							o.get1 (), o.get2 ()
			};
		}
		else
		{
			return null;
		}
	}
}
