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
package com.ajkaandrej.smonitor.admin.client.view;

import com.ajkaandrej.smonitor.agent.rs.model.ApplicationDetails;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabLayoutPanel;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ApplicationPanel extends TabLayoutPanel {
    
    private SessionsTable sessionTable;
    
    public ApplicationPanel() {
        super(2.5, Unit.EM);
        setAnimationDuration(1000);
        getElement().getStyle().setMarginBottom(10.0, Unit.PX);

        HTML homeText = new HTML("Application panel");
        add(homeText, "Details");        
        
        sessionTable = new SessionsTable();
        
//        HTML sessions = new HTML("Session panel");
        add(sessionTable, "Sessions");   

        selectTab(0);
    }
    
    public void reset() {
        sessionTable.reset();
    }
    
    public void loadApplication(ApplicationDetails application) {
        sessionTable.load(application.getSessions());
    }
}
