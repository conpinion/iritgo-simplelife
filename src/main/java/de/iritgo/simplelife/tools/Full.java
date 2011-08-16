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


public class Full<T> extends Option<T>
{
	private T elem;

	protected Full ()
	{
	}

	public Full (T elem)
	{
		assert (elem != null);
		this.elem = elem;
	}

	@Override
	public boolean empty ()
	{
		return false;
	}

	@Override
	public boolean full ()
	{
		return true;
	}

	@Override
	public T get ()
	{
		return elem;
	}

	@Override
	public T getOrElse (T defaultElem)
	{
		return elem;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (obj instanceof Full)
		{
			return get ().equals (((Full) obj).get ());
		}
		return false;
	}
}
