/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.iotdb.os.cache;

import org.apache.iotdb.os.conf.ObjectStorageConfig;
import org.apache.iotdb.os.conf.ObjectStorageDescriptor;

import java.util.ArrayList;
import java.util.List;

public class CacheManger {
  private final ObjectStorageConfig config = ObjectStorageDescriptor.getInstance().getConfig();
  private final List<PersistentCache> caches = new ArrayList<>();

  private CacheManger() {
    for (String cacheDir : config.getCacheDirs()) {
      caches.add(new PersistentCache(cacheDir));
    }
  }

  public static CacheManger getInstance() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {
    private InstanceHolder() {}

    private static final CacheManger INSTANCE = new CacheManger();
  }
}