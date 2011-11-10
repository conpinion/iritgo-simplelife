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


import java.util.Iterator;


public class IntRange implements Iterable<Integer>
{
	private int from;

	private int to;

	public IntRange(int from, int to)
	{
		this.from = from;
		this.to = to;
	}

	public IntRange(int from)
	{
		this.from = from;
		this.to = Integer.MAX_VALUE;
	}

	public static IntRange N()
	{
		return new IntRange(1);
	}

	public static IntRange N0()
	{
		return new IntRange(0);
	}

	public int getFrom()
	{
		return from;
	}

	public int getTo()
	{
		return to;
	}

	public Iterator<Integer> iterator()
	{
		return new Iterator<Integer>()
		{
			private int current = from;

			public boolean hasNext()
			{
				return current <= to;
			}

			public Integer next()
			{
				return current++;
			}

			public void remove()
			{
			}
		};
	}
}
