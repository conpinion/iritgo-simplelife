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


import de.iritgo.simplelife.data.DataConverter;
import java.util.Iterator;


/**
 * A decorator for Iterators that converts between the real type of the iterated
 * elements and a another type.
 * 
 * @param <RealType> The real type of the iterated elements
 * @param <ConvertedType> The converted type
 */
public class ConvertingIteratorAdapter<RealType, ConvertedType> implements Iterator<ConvertedType>
{
	/** The decorated iterator */
	private Iterator<RealType> iterator;

	/** The element converter */
	private DataConverter<RealType, ConvertedType> converter;

	/**
	 * Initialize the decorator.
	 * 
	 * @param iterator The iterator to decorate
	 * @param converter The data converter
	 */
	public ConvertingIteratorAdapter (Iterator<RealType> iterator, DataConverter<RealType, ConvertedType> converter)
	{
		this.iterator = iterator;
		this.converter = converter;
	}

	/**
	 * @see java.util.Iterator#hasNext()
	 */
	public boolean hasNext ()
	{
		return iterator.hasNext ();
	}

	/**
	 * @see java.util.Iterator#next()
	 */
	public ConvertedType next ()
	{
		return converter.convertFromType1 (iterator.next ());
	}

	/**
	 * @see java.util.Iterator#remove()
	 */
	public void remove ()
	{
		iterator.remove ();
	}
}
