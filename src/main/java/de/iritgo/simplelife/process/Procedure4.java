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


/**
 * An executable that takes 4 parameters.
 */
public interface Procedure4<ARG_1, ARG_2, ARG_3, ARG_4>
{
	/**
	 * Workload.
	 * 
	 * @param a1 Parameter 1
	 * @param a2 Parameter 2
	 * @param a3 Parameter 3
	 * @param a4 Parameter 4
	 */
	public void execute(ARG_1 a1, ARG_2 a2, ARG_3 a3, ARG_4 a4);
}
