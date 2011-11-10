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


import java.util.Iterator;


/**
 * An ArrayIterator iterates over the elements of an array.
 */
public class ArrayIterator<ElementType> implements Iterator<ElementType>, Iterable<ElementType>
{
	/** The array to iterate. */
	protected ElementType[] array;

	/** Next array position. */
	protected int pos;

	/**
	 * Create a new ArrayIterator from an array.
	 * 
	 * @param array The array to iterate.
	 */
	public ArrayIterator(ElementType[] array)
	{
		this.array = array;
		pos = 0;
	}

	/**
	 * Check if we can return another pair of elements.
	 * 
	 * @return True if another pair is available.
	 */
	public boolean hasNext()
	{
		return (array != null) && (pos < array.length);
	}

	/**
	 * Return another element. A call to this method is only valid if hasNext()
	 * == true.
	 * 
	 * @return The next element.
	 */
	public ElementType next()
	{
		return array[pos++];
	}

	/**
	 * This method is currently not implemented and does nothing!
	 */
	public void remove()
	{
	}

	/**
	 * We implement Iterable, so that we can convert an array into an Iterable
	 * that returns an ArrayIterator.
	 * 
	 * You can use this to do the following:
	 * 
	 * String[][ a = new String[] { "a", "b", "c", "d", "e" }; for (String s :
	 * new ArrayIterator<String> (a)) { System.out.println (s); }
	 */
	public Iterator<ElementType> iterator()
	{
		return this;
	}
}
