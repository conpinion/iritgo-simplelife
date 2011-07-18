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
 * An executable that takes 4 parameters and returns a result.
 */
public interface ResultExecutor4<Result, T1, T2, T3, T4>
{
	/**
	 * Workload.
	 * 
	 * @param t1 Parameter 1
	 * @param t2 Parameter 2
	 * @param t3 Parameter 3
	 * @param t4 Parameter 4
	 * @return The executor results
	 */
	public Result execute (T1 t1, T2 t2, T3 t3, T4 t4);
}
