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


import de.iritgo.simplelife.math.IntRange;
import de.iritgo.simplelife.math.Pair;
import de.iritgo.simplelife.math.Predicate;
import de.iritgo.simplelife.process.Procedure1;
import java.util.Collection;


/**
 * Useful methods for working with collections.
 */
public final class CollectionTools
{
	/**
	 * Search for an element in an array.
	 *
	 * @param array The array to search.
	 * @param object The object to search for.
	 * @return True if the object was found.
	 */
	public static boolean contains (Object[] array, Object object)
	{
		for (int i = 0; i < array.length; ++i)
		{
			if (array[i].equals (object))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Search for an element in int an array.
	 *
	 * @param array The array to search.
	 * @param object The object to search for.
	 * @return True if the object was found.
	 */
	public static boolean contains (int[] array, int object)
	{
		for (int i = 0; i < array.length; ++i)
		{
			if (array[i] == object)
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Search for an element that satisfies a predicate
	 *
	 * @param <T>
	 * @param collection
	 * @param predicate
	 * @return The element or null if none was found.
	 */
	public static <T> T find (Collection<T> collection, Predicate<T> predicate)
	{
		for (T t : collection)
		{
			if (predicate.eval (t))
			{
				return t;
			}
		}

		return null;
	}

	/**
	 * Search for an element that satisfies a predicate and return its index.
	 *
	 * @param <T>
	 * @param collection
	 * @param predicate
	 * @return The element index or -1 if none was found
	 */
	public static <T> Integer findIndex (Collection<T> collection, Predicate<T> predicate)
	{
		for (Pair<T, Integer> it : new JointIterator<T, Integer> (collection, IntRange.N0 ()))
		{
			if (predicate.eval (it.get1 ()))
			{
				return it.get2 ();
			}
		}

		return - 1;
	}

	/**
	 * Iterate over a collection and call a procedure on every element.
	 *
	 * @param <T> Element type
	 * @param collection The collection
	 * @param procedure The procedure to call
	 */
	public static <T> void forEach (Collection<T> collection, Procedure1<T> procedure)
	{
		for (T elem : collection)
		{
			procedure.execute (elem);
		}
	}
}
