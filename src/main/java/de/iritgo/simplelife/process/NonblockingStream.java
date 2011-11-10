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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Wraps a normal input stream into a non-blocking, buffered stream.
 */
public class NonblockingStream extends Thread
{
	/** The blocking input stream */
	private BufferedReader in;

	/** Buffered stream data */
	private StringBuilder buffer;

	/** If true, the reader thread is running */
	private volatile boolean running;

	/**
	 * Create a new NonblockingStream.
	 * 
	 * @param in The blocking input stream
	 */
	public NonblockingStream(InputStream in)
	{
		this.in = new BufferedReader(new InputStreamReader(in));
		this.buffer = new StringBuilder();
		this.running = true;
	}

	/**
	 * Continuously read from the input stream and append to the buffer.
	 */
	@Override
	public void run()
	{
		try
		{
			int c = - 1;

			while (running && (c = in.read()) != - 1)
			{
				appendToBuffer((char) c);
			}

			in.close();
		}
		catch (IOException ignored)
		{
		}
	}

	/**
	 * Terminate the reader thread.
	 */
	public synchronized void terminate()
	{
		running = false;
		interrupt();
	}

	/**
	 * Append a character to the buffer.
	 * 
	 * @param c The character to append
	 */
	private synchronized void appendToBuffer(char c)
	{
		buffer.append(c);
	}

	/**
	 * Retrieve the current buffer contents as a string.
	 * 
	 * @return The buffer contents as a string
	 */
	public synchronized String getDataAsString()
	{
		return buffer.toString();
	}
}
