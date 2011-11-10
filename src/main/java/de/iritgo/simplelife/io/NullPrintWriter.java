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

package de.iritgo.simplelife.io;


import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


/**
 * A do nothing PrintWriter.
 */
public class NullPrintWriter extends PrintWriter
{
	/**
	 * Create a new NullPrintWriter.
	 */
	public NullPrintWriter()
	{
		super(new OutputStreamWriter(new OutputStream()
		{
			@Override
			public void write(int b) throws IOException
			{
			}

			@Override
			public void write(byte[] b) throws IOException
			{
			}

			@Override
			public void write(byte[] b, int off, int len) throws IOException
			{
			}

			@Override
			public void flush() throws IOException
			{
			}

			@Override
			public void close() throws IOException
			{
			}
		}));
	}
}
