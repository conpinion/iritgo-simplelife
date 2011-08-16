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


import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import org.junit.*;
import de.iritgo.simplelife.tools.*;


public class OptionTest
{
	@Test
	public void getAFilledOption1 ()
	{
		Option<String> option = new Full ("option");
		assertThat (option.empty (), is (false));
		assertThat (option.get (), equalTo ("option"));
	}

	public void getAFilledOption2 ()
	{
		Option<String> option = Option.Full ("option");
		assertThat (option.empty (), is (false));
		assertThat (option.get (), equalTo ("option"));
	}

	@Test
	public void getAFilledOptionWithDefaultValue1 ()
	{
		Option<String> option = new Full ("option");
		assertThat (option.empty (), is (false));
		assertThat (option.getOrElse ("default"), equalTo ("option"));
	}

	@Test
	public void getAFilledOptionWithDefaultValue2 ()
	{
		Option<String> option = Option.Full ("option");
		assertThat (option.empty (), is (false));
		assertThat (option.getOrElse ("default"), equalTo ("option"));
	}

	@Test(expected = EmptyOptionException.class)
	public void getAnEmptyOption1 ()
	{
		Option<String> option = new Empty ();
		assertThat (option.empty (), is (true));
		option.get ();
	}

	@Test(expected = EmptyOptionException.class)
	public void getAnEmptyOption2 ()
	{
		Option<String> option = Option.Empty;
		assertThat (option.empty (), is (true));
		option.get ();
	}

	@Test
	public void getAnEmptyOptionWithDefaultValue1 ()
	{
		Option<String> option = new Empty ();
		assertThat (option.empty (), is (true));
		assertThat (option.getOrElse ("default"), equalTo ("default"));
	}

	@Test
	public void getAnEmptyOptionWithDefaultValue2 ()
	{
		Option<String> option = Option.Empty;
		assertThat (option.empty (), is (true));
		assertThat (option.getOrElse ("default"), equalTo ("default"));
	}
}
