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
package com.orientechnologies.orient.core.command;

import com.orientechnologies.common.listener.OProgressListener;
import com.orientechnologies.orient.core.command.OCommandContext.TIMEOUT_STRATEGY;
import com.orientechnologies.orient.core.config.OGlobalConfiguration;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.storage.OStorage;

import java.util.*;

/**
 * Text based Command Request abstract class.
 * 
 * @author Luca Garulli
 * 
 */
@SuppressWarnings("serial")
public abstract class OCommandRequestAbstract implements OCommandRequestInternal, ODistributedCommand {
  protected OCommandResultListener    resultListener;
  protected OProgressListener         progressListener;
  protected int                       limit           = -1;
  protected long                      timeoutMs       = OGlobalConfiguration.COMMAND_TIMEOUT.getValueAsLong();
  protected TIMEOUT_STRATEGY          timeoutStrategy = TIMEOUT_STRATEGY.EXCEPTION;
  protected OStorage.LOCKING_STRATEGY lockStrategy    = OStorage.LOCKING_STRATEGY.NONE;
  protected Map<Object, Object>       parameters;
  protected String                    fetchPlan       = null;
  protected boolean                   useCache        = false;
  protected OCommandContext           context;

  private final Set<String>           nodesToExclude  = new HashSet<String>();

  protected OCommandRequestAbstract() {
  }

  @Override
  public OCommandResultListener getResultListener() {
    return resultListener;
  }

  @Override
  public void setResultListener(OCommandResultListener iListener) {
    resultListener = iListener;
  }

  @Override
  public Map<Object, Object> getParameters() {
    return parameters;
  }

  protected void setParameters(final Object... iArgs) {
    if (iArgs != null && iArgs.length > 0) {
        parameters = convertToParameters(iArgs);
    }
  }

  @SuppressWarnings("unchecked")
  protected Map<Object, Object> convertToParameters(final Object... iArgs) {
    final Map<Object, Object> params;

    if (iArgs.length == 1 && iArgs[0] instanceof Map) {
      params = (Map<Object, Object>) iArgs[0];
    } else {
      params = new HashMap<Object, Object>(iArgs.length);
      for (int i = 0; i < iArgs.length; ++i) {
        Object par = iArgs[i];

        if (par instanceof OIdentifiable && ((OIdentifiable) par).getIdentity().isValid()) {
            // USE THE RID ONLY
            par = ((OIdentifiable) par).getIdentity();
        }

        params.put(i, par);
      }
    }
    return params;
  }

  @Override
  public OProgressListener getProgressListener() {
    return progressListener;
  }

  @Override
  public OCommandRequestAbstract setProgressListener(OProgressListener progressListener) {
    this.progressListener = progressListener;
    return this;
  }

  @Override
  public void reset() {
  }

  @Override
  public int getLimit() {
    return limit;
  }

  @Override
  public OCommandRequestAbstract setLimit(final int limit) {
    this.limit = limit;
    return this;
  }

  @Override
  public String getFetchPlan() {
    return fetchPlan;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <RET extends OCommandRequest> RET setFetchPlan(String fetchPlan) {
    this.fetchPlan = fetchPlan;
    return (RET) this;
  }

  public boolean isUseCache() {
    return useCache;
  }

  @Override
  public void setUseCache(boolean useCache) {
    this.useCache = useCache;
  }

  @Override
  public OCommandContext getContext() {
    if (context == null) {
        context = new OBasicCommandContext();
    }
    return context;
  }

  @Override
  public OCommandRequestAbstract setContext(final OCommandContext iContext) {
    context = iContext;
    return this;
  }

  @Override
  public long getTimeoutTime() {
    return timeoutMs;
  }

  @Override
  public void setTimeout(final long timeout, final TIMEOUT_STRATEGY strategy) {
    this.timeoutMs = timeout;
    this.timeoutStrategy = strategy;
  }

  @Override
  public TIMEOUT_STRATEGY getTimeoutStrategy() {
    return timeoutStrategy;
  }

  public OStorage.LOCKING_STRATEGY getLockingStrategy() {
    return lockStrategy;
  }

  public void setLockStrategy(final OStorage.LOCKING_STRATEGY lockStrategy) {
    this.lockStrategy = lockStrategy;
  }

  @Override
  public Set<String> nodesToExclude() {
    return Collections.unmodifiableSet(nodesToExclude);
  }

  public void addExcludedNode(String node) {
    nodesToExclude.add(node);
  }

  public void removeExcludedNode(String node) {
    nodesToExclude.remove(node);
  }
}
