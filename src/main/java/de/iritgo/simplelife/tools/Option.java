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

package de.iritgo.simplelife.tools;


public abstract class Option<T>
{
	public static Empty Empty = new Empty();

	public static Empty Empty()
	{
		return Empty;
	}

	public static Empty None()
	{
		return Empty;
	}

	public static <T> Full Full(T t)
	{
		return new Full(t);
	}

	public static <T> Full Some(T t)
	{
		return new Full(t);
	}

	public static <T> Option<T> Optional(T t)
	{
		return t != null ? new Full (t) : Empty;
	}

	public abstract boolean empty();

	public boolean isEmpty()
	{
		return empty();
	}

	public boolean absent()
	{
		return empty();
	}

	public abstract boolean full();

	public boolean isFull()
	{
		return full();
	}

	public static <T> boolean oo(Option<T> o)
	{
		return o.full();
	}

	public boolean present()
	{
		return full();
	}

	public abstract T get();

	public static <T> T o(Option<T> o)
	{
		return o.get();
	}

	public static <T> T o(Option<T> o, T defaultElem)
	{
		return o.or(defaultElem);
	}

	public abstract T getOrElse(T defaultElem);

	public T or(T defaultElem)
	{
		return getOrElse(defaultElem);
	}
}
