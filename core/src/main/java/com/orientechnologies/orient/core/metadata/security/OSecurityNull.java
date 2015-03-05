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
package com.orientechnologies.orient.core.metadata.security;

import java.util.List;
import java.util.Set;

import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.record.impl.ODocument;

/**
 * Dummy security implementation.
 * 
 * @author Luca Garulli
 * 
 */
public class OSecurityNull implements OSecurity {

  @Override
  public boolean isAllowed(final Set<OIdentifiable> iAllowAll, final Set<OIdentifiable> iAllowOperation) {
    return true;
  }

  @Override
  public OUser create() {
    return null;
  }

  @Override
  public void load() {
  }

  @Override
  public OUser getUser(String iUserName) {
    return null;
  }

  @Override
  public OUser getUser(ORID iUserId) {
    return null;
  }

  @Override
  public OUser createUser(String iUserName, String iUserPassword, String... iRoles) {
    return null;
  }

  @Override
  public OUser createUser(String iUserName, String iUserPassword, ORole... iRoles) {
    return null;
  }

  @Override
  public ORole getRole(String iRoleName) {
    return null;
  }

  @Override
  public ORole getRole(OIdentifiable iRole) {
    return null;
  }

  @Override
  public ORole createRole(String iRoleName, OSecurityRole.ALLOW_MODES iAllowMode) {
    return null;
  }

  @Override
  public ORole createRole(String iRoleName, ORole iParent, OSecurityRole.ALLOW_MODES iAllowMode) {
    return null;
  }

  @Override
  public List<ODocument> getAllUsers() {
    return null;
  }

  @Override
  public List<ODocument> getAllRoles() {
    return null;
  }

  @Override
  public OUser authenticate(String iUsername, String iUserPassword) {
    return null;
  }

  @Override
  public OUser authenticate(OToken authToken) {
    return null;
  }

  @Override
  public void close(boolean onDelete) {
  }

  public OUser repair() {
    return null;
  }

  @Override
  public boolean dropUser(String iUserName) {
    return false;
  }

  @Override
  public boolean dropRole(String iRoleName) {
    return false;
  }

  @Override
  public OIdentifiable allowUser(ODocument iDocument, String iAllowFieldName, String iUserName) {
    return null;
  }

  @Override
  public OIdentifiable allowRole(ODocument iDocument, String iAllowFieldName, String iRoleName) {
    return null;
  }

  @Override
  public OIdentifiable allowIdentity(ODocument iDocument, String iAllowFieldName, OIdentifiable iId) {
    return null;
  }

  @Override
  public OIdentifiable disallowUser(ODocument iDocument, String iAllowFieldName, String iUserName) {
    return null;
  }

  @Override
  public OIdentifiable disallowRole(ODocument iDocument, String iAllowFieldName, String iRoleName) {
    return null;
  }

  @Override
  public OIdentifiable disallowIdentity(ODocument iDocument, String iAllowFieldName, OIdentifiable iId) {
    return null;
  }

  @Override
  public void createClassTrigger() {
  }

  @Override
  public OSecurity getUnderlying() {
    return null;
  }

  @Override
  public long getVersion() {
    return 0;
  }

  @Override
  public void incrementVersion() {
  }
}
