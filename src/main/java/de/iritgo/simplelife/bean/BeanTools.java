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

package de.iritgo.simplelife.bean;


import org.apache.commons.beanutils.PropertyUtils;
import java.beans.PropertyDescriptor;
import java.util.Map;


public class BeanTools
{
	/**
	 * Copy all attributes from the given bean to the given map
	 * 
	 * @param object The bean
	 * @param map The map
	 * @param overwrite If true existing attributes in the map will be
	 *            overwritten
	 */
	static public void copyBean2Map (Object object, Map<String, Object> map, boolean overwrite)
	{
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors (object))
		{
			String name = pd.getName ();

			if (! overwrite && map.containsKey (name))
			{
				continue;
			}

			try
			{
				map.put (name, PropertyUtils.getProperty (object, name));
			}
			catch (Exception ignore)
			{
			}
		}
	}

	/**
	 * Copy all attributes from the given map to the given bean
	 * 
	 * @param map The map The map
	 * @param object The bean The bean
	 */
	static public void copyMap2Bean (Map<String, Object> map, Object object)
	{
		for (String name : map.keySet ())
		{
			if (PropertyUtils.isWriteable (object, name))
			{
				try
				{
					PropertyUtils.setSimpleProperty (object, name, map.get (name));
				}
				catch (Exception ignore)
				{
				}
			}
		}
	}

	/**
	 * Return true if the given property exists in the given class
	 * 
	 * @param klass The class
	 * @param propertyName The property name
	 * @return True if the property exists
	 */
	static public boolean hasProperty (Class klass, String propertyName)
	{
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors (klass))
		{
			if (pd.getName ().equals (propertyName))
			{
				return true;
			}
		}

		return false;
	}
}
