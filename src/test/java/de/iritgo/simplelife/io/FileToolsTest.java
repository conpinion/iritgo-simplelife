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


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class FileToolsTest
{
	private File tmpFile;

	@Before
	public void createTmpFile () throws Exception
	{
		tmpFile = File.createTempFile ("de.iritgo.simplelife.FileToolsTest", "test");
	}

	@After
	public void deleteTmpFile ()
	{
		tmpFile.delete ();
	}

	@Test
	public void parentDir ()
	{
		boolean isParent = FileTools.isParent (tmpFile.getParentFile (), tmpFile);

		assertThat (isParent, is (true));
	}
}
