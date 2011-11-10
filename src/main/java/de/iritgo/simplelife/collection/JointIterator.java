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
import java.util.Iterator;


public class JointIterator<T1, T2> implements Iterator<Pair<T1, T2>>, Iterable<Pair<T1, T2>>
{
	private Iterator<T1> i1;

	private Iterator<T2> i2;

	public JointIterator(Iterable<T1> iable1, Iterable<T2> iable2)
	{
		this.i1 = iable1.iterator();
		this.i2 = iable2.iterator();
	}

	public JointIterator(Iterator<T1> i1, Iterator<T2> i2)
	{
		this.i1 = i1;
		this.i2 = i2;
	}

	public boolean hasNext()
	{
		return i1.hasNext() && i2.hasNext();
	}

	public Pair<T1, T2> next()
	{
		return new Pair(i1.next(), i2.next());
	}

	public void remove()
	{
	}

	public Iterator<Pair<T1, T2>> iterator()
	{
		return this;
	}
}
