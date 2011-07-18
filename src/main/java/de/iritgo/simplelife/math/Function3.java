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


/**
 * An executable that takes three parameters.
 */
public interface Function3<RES, ARG_1, ARG_2, ARG_3>
{
	/**
	 * Function.
	 * 
	 * @param a1 Argument 1
	 * @param a2 Argument 2
	 * @param a3 Argument 3
	 */
	public RES eval (ARG_1 a1, ARG_2 a2, ARG_3 a3);
}
