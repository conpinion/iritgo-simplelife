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

package de.iritgo.simplelife.constants;


/**
 *
 */
public enum SortOrder
{
	NONE("none", "asc"), ASCENDING("asc", "asc"), DESCENDING("desc", "desc");

	private String id;

	private String hql;

	SortOrder(String id, String hql)
	{
		this.id = id;
		this.hql = hql;
	}

	public String hql()
	{
		return hql;
	}

	public String id()
	{
		return id;
	}

	public static SortOrder byId(String anId)
	{
		for (SortOrder order : SortOrder.values())
		{
			if (order.id.equals(anId))
			{
				return order;
			}
		}

		throw new IllegalArgumentException("Unknown SortOrder id '" + anId + "'");
	}
}
