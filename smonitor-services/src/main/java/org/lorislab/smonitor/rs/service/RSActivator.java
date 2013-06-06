/*
 * Copyright 2013 lorislab.org.
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
package org.lorislab.smonitor.rs.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.lorislab.smonitor.rs.admin.service.AgentRestServiceImpl;

/**
 *
 * @author Andrej Petras
 */
@ApplicationPath("rs")
public class RSActivator extends Application {

    private static final Set<Class<?>> RESOURCES = new HashSet<Class<?>>();
    
    private static final Set<Object> SINGLETONS = new HashSet<Object>();
    
    public RSActivator() {
        RESOURCES.add(ConfigServiceImpl.class);
        RESOURCES.add(AgentRestServiceImpl.class);        
        RESOURCES.add(ServerServiceImpl.class);
        
//        SINGLETONS.add(new AgentRestServiceImpl());
    }
    
    @Override
    public Set<Class<?>> getClasses() {                
        return RESOURCES;
    }

    @Override
    public Set<Object> getSingletons() {
        return SINGLETONS;
    }

}
