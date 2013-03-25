/*
 * Copyright 2013 Andrej Petras <andrej@ajka-andrej.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ajkaandrej.smonitor.admin.client.navigation.model;

import com.google.gwt.view.client.ProvidesKey;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class HostTreeModel {

    public static final ProvidesKey<HostTreeModel> KEY_PROVIDER = new ProvidesKey<HostTreeModel>() {
        @Override
        public Object getKey(HostTreeModel item) {
            return item == null ? null : item.remote + item.id;
        }
    };
    
    public String remote;
    public String name;
    public String id;
}
