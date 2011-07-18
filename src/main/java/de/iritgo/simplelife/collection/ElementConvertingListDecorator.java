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
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * 
 * @param <RealElementType>
 * @param <ConvertedElementType>
 */
public class ElementConvertingListDecorator<RealElementType, ConvertedElementType> implements
				List<ConvertedElementType>
{
	/** The decorated list */
	private List<RealElementType> list;

	/** The element converter */
	private DataConverter<RealElementType, ConvertedElementType> converter;

	/**
	 * Initialize the decorator.
	 * 
	 * @param list The list to decorate
	 * @param converter The data converter
	 */
	public ElementConvertingListDecorator (List<RealElementType> list,
					DataConverter<RealElementType, ConvertedElementType> converter)
	{
		this.list = list;
		this.converter = converter;
	}

	/**
	 * @see java.util.List#add(java.lang.Object)
	 */
	public boolean add (ConvertedElementType element)
	{
		return list.add (converter.convertFromType2 (element));
	}

	/**
	 * @see java.util.List#add(int, java.lang.Object)
	 */
	public void add (int index, ConvertedElementType element)
	{
		list.add (index, converter.convertFromType2 (element));
	}

	/**
	 * @see java.util.List#addAll(java.util.Collection)
	 */
	public boolean addAll (Collection<? extends ConvertedElementType> c)
	{
		for (ConvertedElementType e : c)
		{
			add (e);
		}

		return c.size () > 0;
	}

	/**
	 * @see java.util.List#addAll(int, java.util.Collection)
	 */
	public boolean addAll (int index, Collection<? extends ConvertedElementType> c)
	{
		for (ConvertedElementType e : c)
		{
			add (index++, e);
		}

		return c.size () > 0;
	}

	/**
	 * @see java.util.List#clear()
	 */
	public void clear ()
	{
		list.clear ();
	}

	/**
	 * @see java.util.List#contains(java.lang.Object)
	 */
	public boolean contains (Object o)
	{
		return list.contains (converter.convertFromType2 ((ConvertedElementType) o));
	}

	/**
	 * @see java.util.List#containsAll(java.util.Collection)
	 */
	public boolean containsAll (Collection<?> c)
	{
		for (Iterator<ConvertedElementType> i = iterator (); i.hasNext ();)
		{
			if (! c.contains (i.next ()))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * @see java.util.List#get(int)
	 */
	public ConvertedElementType get (int index)
	{
		return converter.convertFromType1 (list.get (index));
	}

	/**
	 * @see java.util.List#indexOf(java.lang.Object)
	 */
	public int indexOf (Object o)
	{
		return list.indexOf (converter.convertFromType2 ((ConvertedElementType) o));
	}

	/**
	 * @see java.util.List#isEmpty()
	 */
	public boolean isEmpty ()
	{
		return list.isEmpty ();
	}

	/**
	 * @see java.util.List#iterator()
	 */
	public Iterator<ConvertedElementType> iterator ()
	{
		return new ConvertingIteratorAdapter<RealElementType, ConvertedElementType> (list.iterator (), converter);
	}

	/**
	 * @see java.util.List#lastIndexOf(java.lang.Object)
	 */
	public int lastIndexOf (Object o)
	{
		return list.lastIndexOf (converter.convertFromType2 ((ConvertedElementType) o));
	}

	/**
	 * @see java.util.List#listIterator()
	 */
	public ListIterator<ConvertedElementType> listIterator ()
	{
		return new ConvertingListIteratorDecorator<RealElementType, ConvertedElementType> (list.listIterator (),
						converter);
	}

	/**
	 * @see java.util.List#listIterator(int)
	 */
	public ListIterator<ConvertedElementType> listIterator (int index)
	{
		return new ConvertingListIteratorDecorator<RealElementType, ConvertedElementType> (list.listIterator (index),
						converter);
	}

	/**
	 * @see java.util.List#remove(java.lang.Object)
	 */
	public boolean remove (Object o)
	{
		return list.remove (converter.convertFromType2 ((ConvertedElementType) o));
	}

	/**
	 * @see java.util.List#remove(int)
	 */
	public ConvertedElementType remove (int index)
	{
		return converter.convertFromType1 (list.remove (index));
	}

	public boolean removeAll (Collection<?> c)
	{
		return false;
	}

	public boolean retainAll (Collection<?> c)
	{
		return false;
	}

	/**
	 * @see java.util.List#set(int, java.lang.Object)
	 */
	public ConvertedElementType set (int index, ConvertedElementType element)
	{
		return converter.convertFromType1 (list.set (index, converter.convertFromType2 (element)));
	}

	/**
	 * @see java.util.List#size()
	 */
	public int size ()
	{
		return list.size ();
	}

	public List<ConvertedElementType> subList (int fromIndex, int toIndex)
	{
		return null;
	}

	/**
	 * @see java.util.List#toArray()
	 */
	public Object[] toArray ()
	{
		Object[] res = new Object[list.size ()];
		int index = 0;

		for (Iterator<ConvertedElementType> i = iterator (); i.hasNext ();)
		{
			res[index++] = i.next ();
		}

		return res;
	}

	/**
	 * @see java.util.List#toArray(Object[])
	 */
	public <T> T[] toArray (T[] a)
	{
		if (a.length < list.size ())
		{
			a = (T[]) java.lang.reflect.Array.newInstance (a.getClass ().getComponentType (), list.size ());
		}

		Object[] res = new Object[list.size ()];
		int index = 0;

		for (Iterator<ConvertedElementType> i = iterator (); i.hasNext ();)
		{
			res[index++] = i.next ();
		}

		if (a.length > list.size ())
		{
			a[list.size ()] = null;
		}

		return a;
	}
}
