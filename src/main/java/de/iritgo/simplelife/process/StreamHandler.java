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

package de.iritgo.simplelife.process;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 *
 */
public class StreamHandler implements Runnable
{
	@SuppressWarnings("unused")
	private String name;

	protected InputStream is;

	Thread thread;

	public StreamHandler (InputStream is, boolean startImmediately)
	{
		this.name = "StreamHandler-" + this.hashCode ();
		this.is = is;

		if (startImmediately)
		{
			start ();
		}
	}

	public StreamHandler (String name, InputStream is, boolean startImmediately)
	{
		this.name = name;
		this.is = is;

		if (startImmediately)
		{
			start ();
		}
	}

	public void start ()
	{
		thread = new Thread (this);
		thread.start ();
	}

	public void run ()
	{
		try
		{
			InputStreamReader isr = new InputStreamReader (is);
			BufferedReader br = new BufferedReader (isr);
			@SuppressWarnings("unused")
			String line = null;

			while ((line = br.readLine ()) != null)
			{
			}

			is.close ();
		}
		catch (Exception ignored)
		{
		}
	}
}
