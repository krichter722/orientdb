/*
  *
  *  *  Copyright 2014 Orient Technologies LTD (info(at)orientechnologies.com)
  *  *
  *  *  Licensed under the Apache License, Version 2.0 (the "License");
  *  *  you may not use this file except in compliance with the License.
  *  *  You may obtain a copy of the License at
  *  *
  *  *       http://www.apache.org/licenses/LICENSE-2.0
  *  *
  *  *  Unless required by applicable law or agreed to in writing, software
  *  *  distributed under the License is distributed on an "AS IS" BASIS,
  *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  *  *  See the License for the specific language governing permissions and
  *  *  limitations under the License.
  *  *
  *  * For more information: http://www.orientechnologies.com
  *
  */
package com.orientechnologies.orient.core.config;

public class OStorageClusterHoleConfiguration extends OStorageFileConfiguration {
	private static final long		serialVersionUID		= 1L;
	
	private static final String	DEF_EXTENSION				= ".och";
	private static final String	DEF_INCREMENT_SIZE	= "50%";

	public OStorageClusterHoleConfiguration() {
		super();
	}

	public OStorageClusterHoleConfiguration(OStorageSegmentConfiguration iParent, String iPath, String iType, String iMaxSize) {
		super(iParent, iPath + DEF_EXTENSION, iType, iMaxSize, DEF_INCREMENT_SIZE);
	}

}
