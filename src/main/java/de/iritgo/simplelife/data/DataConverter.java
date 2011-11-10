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


/**
 * Interface for data converters which are able to convert between to types
 * Type1 and Type2.
 * 
 * @param <Type1> First convertable type
 * @param <Type2> Second convertable type
 */
public interface DataConverter<Type1, Type2>
{
	/**
	 * Convert from the first type to the second. If the supplied object is
	 * null, this method should return null.
	 * 
	 * @param o The object to convert
	 * @return The converted object
	 */
	public Type2 convertFromType1(Type1 o);

	/**
	 * Convert from the second type to the first. If the supplied object is
	 * null, this method should return null.
	 * 
	 * @param o The object to convert
	 * @return The converted object
	 */
	public Type1 convertFromType2(Type2 o);
}
