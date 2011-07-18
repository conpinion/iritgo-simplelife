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


/**
 * This class implements the concept of a pair, i.e. a tuple consisting of two
 * components.
 */
public class Pair<T1, T2>
{
	/** First pair component. */
	protected T1 _1;

	/** Second pair component. */
	protected T2 _2;

	/**
	 * Create a new pair.
	 * 
	 * @param _1 First pair component.
	 * @param _2 Second pair component.
	 */
	public Pair (T1 _1, T2 _2)
	{
		this._1 = _1;
		this._2 = _2;
	}

	/**
	 * Retrieve the first pair component.
	 * 
	 * @return The first component.
	 */
	public T1 get1 ()
	{
		return _1;
	}

	/**
	 * Set the first pair component.
	 * 
	 * @param first The new first.
	 */
	public void set1 (T1 first)
	{
		this._1 = first;
	}

	/**
	 * Retrieve the second pair component.
	 * 
	 * @return The second component.
	 */
	public T2 get2 ()
	{
		return _2;
	}

	/**
	 * Set the second pair component.
	 * 
	 * @param second The new second.
	 */
	public void set2 (T2 second)
	{
		this._2 = second;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals (Object obj)
	{
		if (obj == this)
		{
			return true;
		}

		if (! (obj instanceof Pair))
		{
			return false;
		}

		return _1.equals (((Pair<T1, T2>) obj)._1) && _2.equals (((Pair<T1, T2>) obj)._2);
	}
}
