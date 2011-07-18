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
 * Keyed values contain key, value pairs where only the key component is used
 * for a comparison operations.
 * 
 * @param <Key> The type of the keys
 * @param <V1> The type of the first values
 * @param <V2> The type of the second values
 */
public class KeyedValue2<Key, V1, V2>
{
	/** The key */
	private Key key;

	/** The first value */
	private V1 v1;

	/** The second value */
	private V2 v2;

	/**
	 * Create a new keyed value.
	 * 
	 * @param key The key
	 * @param v1 The first value
	 * @param v2 The second value
	 */
	public KeyedValue2 (Key key, V1 v1, V2 v2)
	{
		this.key = key;
		this.v1 = v1;
		this.v2 = v2;
	}

	/**
	 * Get the key.
	 * 
	 * @return The key
	 */
	public Key getKey ()
	{
		return key;
	}

	/**
	 * Set the key.
	 * 
	 * @param key The key
	 */
	public void setKey (Key key)
	{
		this.key = key;
	}

	/**
	 * Get the first value.
	 * 
	 * @return The first value
	 */
	public V1 getV1 ()
	{
		return v1;
	}

	/**
	 * Set the first value.
	 * 
	 * @param v1 The first value
	 */
	public void setV1 (V1 v1)
	{
		this.v1 = v1;
	}

	/**
	 * Get the second value.
	 * 
	 * @return The second value
	 */
	public V2 getV2 ()
	{
		return v2;
	}

	/**
	 * Set the second value.
	 * 
	 * @param v2 The second value
	 */
	public void setV2 (V2 v2)
	{
		this.v2 = v2;
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

		if (! (obj instanceof KeyedValue2))
		{
			return false;
		}

		return key.equals (((KeyedValue2<Key, V1, V2>) obj).key);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode ()
	{
		return key.hashCode ();
	}
}
