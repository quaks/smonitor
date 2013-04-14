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
package org.lorislab.smonitor.admin.client.app.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class SessionDetailsModel {
    
    public String remote;
    public String hostName;
    public int hostPort;
    public String host;
    public String application;
    
    public String id;
    
    public String user;

    public Date creationTime;
    
    public Date lastAccessedTime;
        
    public boolean valid;
    
    public long lastAccessedTimeInternal;
        
    public int maxInactiveInterval;
    
    public String info;
    
    public double size;

    public double sizeSerializable;

    public boolean newSession;
    
    public List<String> roles;

}