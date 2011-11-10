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
 * @param <Value> The type of the values
 */
public class KeyedValue<Key, Value>
{
	/** The key */
	private Key key;

	/** The value */
	private Value value;

	/**
	 * Create a new keyed value.
	 * 
	 * @param key The key
	 * @param value The value
	 */
	public KeyedValue(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}

	/**
	 * Get the key.
	 * 
	 * @return The key
	 */
	public Key getKey()
	{
		return key;
	}

	/**
	 * Set the key.
	 * 
	 * @param key The key
	 */
	public void setKey(Key key)
	{
		this.key = key;
	}

	/**
	 * Get the value.
	 * 
	 * @return The value
	 */
	public Value getValue()
	{
		return value;
	}

	/**
	 * Set the value.
	 * 
	 * @param value The value
	 */
	public void setValue(Value value)
	{
		this.value = value;
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

		if (! (obj instanceof KeyedValue))
		{
			return false;
		}

		return key.equals(((KeyedValue<Key, Value>) obj).key);
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return key.hashCode();
	}
}
