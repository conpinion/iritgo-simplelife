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
 * This class implements a 3-tuple.
 */
public class Tuple3<T1, T2, T3>
{
	/** First component. */
	private T1 _1;

	/** Second component. */
	private T2 _2;

	/** Third component. */
	private T3 _3;

	/**
	 * Create a new 3-tuple.
	 *
	 * @param _1 First component.
	 * @param _2 Second component.
	 * @param _3 Third component.
	 */
	public Tuple3(T1 _1, T2 _2, T3 _3)
	{
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	/**
	 * Retrieve the first component.
	 *
	 * @return The first component.
	 */
	public T1 get1()
	{
		return _1;
	}

	/**
	 * Set the first component.
	 *
	 * @param _1 The new first component.
	 */
	public void set1(T1 _1)
	{
		this._1 = _1;
	}

	/**
	 * Retrieve the second component.
	 *
	 * @return The second component.
	 */
	public T2 get2()
	{
		return _2;
	}

	/**
	 * Set the second component.
	 *
	 * @param _2 The new second component.
	 */
	public void set2(T2 _2)
	{
		this._2 = _2;
	}

	/**
	 * Retrieve the third component.
	 *
	 * @return The third component.
	 */
	public T3 get3()
	{
		return _3;
	}

	/**
	 * Set the third component.
	 *
	 * @param _3 The new third component.
	 */
	public void set3(T3 _3)
	{
		this._3 = _3;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}

		if (! (obj instanceof Tuple3))
		{
			return false;
		}

		Tuple3<T1, T2, T3> o = (Tuple3<T1, T2, T3>) obj;
		return (_1 != null ? _1.equals(o._1) : o._1 == null) && (_2 != null ? _2.equals(o._2) : o._2 == null)
						&& (_3 != null ? _3.equals(o._3) : o._3 == null);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return (_1 != null ? _1.hashCode() : 0) + 11 * (_2 != null ? _2.hashCode() : 0) + 17
						* (_3 != null ? _3.hashCode() : 0);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "(" + (_1 != null ? _1.toString() : "<null>") + "," + (_2 != null ? _2.toString() : "<null>") + ","
						+ (_3 != null ? _3.toString() : "<null>") + ")";
	}
}
