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

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.lorislab.smonitor.rs.model.SessionInfo;
import org.lorislab.smonitor.rs.model.SessionInfoDetails;
import org.lorislab.smonitor.rs.model.SessionSearchCriteria;

/**
 *
 * @author Andrej Petras
 */
@Path("service")
public interface ApplicationService {
    
    @GET
    @Path("session/m/{guid}/{host}/{application}/{session}")     
    @Produces(MediaType.APPLICATION_JSON)    
    public SessionInfo getSesssion(@PathParam("guid") String guid, @PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws Exception;
                
    @GET
    @Path("session/d/{guid}/{host}/{application}/{session}")     
    @Produces(MediaType.APPLICATION_JSON)     
    public SessionInfoDetails getSesssionDetails(@PathParam("guid") String guid, @PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws Exception;

    @DELETE
    @Path("session/m/{guid}/{host}/{application}/{session}")     
    @Produces(MediaType.APPLICATION_JSON)     
    public String deleteSesssion(@PathParam("guid") String guid, @PathParam("host") String host, @PathParam("application") String application, @PathParam("session") String id) throws Exception;
    
    @POST
    @Path("session/search")
    @Produces(MediaType.APPLICATION_JSON)    
    @Consumes(MediaType.APPLICATION_JSON)    
    public List<SessionInfo> findSessions(SessionSearchCriteria criteria) throws Exception;
}
