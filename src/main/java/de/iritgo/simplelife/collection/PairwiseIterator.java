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


import de.iritgo.simplelife.math.Pair;
import java.util.Collection;
import java.util.Iterator;


/**
 * A PairwiseIterator iterates over collections or arrays and returns two
 * succeeding elements as a Pair object.
 * 
 * E.g. a list of Integers [1,2,3,4,5] is returned as (1,2), (3,4). The last
 * element is not returned, since the iterator can not return another pair if
 * the source list only contains one more element.
 */
public class PairwiseIterator<ElementType, FirstType, SecondType> implements Iterator<Pair<FirstType, SecondType>>,
				Iterable<Pair<FirstType, SecondType>>
{
	/** Next collection element. */
	protected Iterator<ElementType> pos;

	/** Next position in the collection. */
	protected int index;

	/** Size of the collection. */
	protected int size;

	/**
	 * Create a new PairwiseIterator from a Collection object.
	 * 
	 * @param collection The collection to iterate.
	 */
	public PairwiseIterator (Collection<ElementType> collection)
	{
		pos = (collection != null) ? collection.iterator () : null;
		index = 0;
		size = (collection != null) ? collection.size () : 0;
	}

	/**
	 * Create a new PairwiseIterator from an array.
	 * 
	 * @param array The array to iterate.
	 */
	public PairwiseIterator (ElementType[] array)
	{
		pos = new ArrayIterator<ElementType> (array);
		index = 0;
		size = (array != null) ? array.length : 0;
	}

	/**
	 * Check if we can return another pair of elements.
	 * 
	 * @return True if another pair is available.
	 */
	public boolean hasNext ()
	{
		return (index + 1) < size;
	}

	/**
	 * Return another pair of elements. A call to this method is only valid if
	 * hasNext() == true.
	 * 
	 * @return The next pair.
	 */
	@SuppressWarnings("unchecked")
	public Pair<FirstType, SecondType> next () throws ClassCastException
	{
		index += 2;

		return new Pair<FirstType, SecondType> ((FirstType) pos.next (), (SecondType) pos.next ());
	}

	/**
	 * This method is currently not implemented and does nothing!
	 */
	public void remove ()
	{
	}

	/**
	 * We implement Iterable, so that we can convert a collection or an array
	 * into an Iterable that returns a PairwiseIterator.
	 * 
	 * You can use this to do the following:
	 * 
	 * List<String> l = Arrays.asList (new String[] { "a", "b", "c", "d", "e"
	 * }); for (Pair<String, String> p : new PairwiseIterator<String, String,
	 * String> (l)) { System.out.println ("(" + p.getFirst () + "," +
	 * p.getSecond () + ")"); }
	 */
	public Iterator<Pair<FirstType, SecondType>> iterator ()
	{
		return this;
	}
}
