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
package org.lorislab.smonitor.admin.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.lorislab.smonitor.admin.client.handler.MenuPanelHandler;
import org.lorislab.smonitor.rs.service.ConfigService;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class MainLayout extends Composite {

    @UiField
    SimplePanel mainPanel;
    @UiField
    SpanElement version;
    @UiField
    MenuPanel menu;
    
    private AgentsView agentsView;
    private SessionsView sessionsView;
    private DashboardView dashboardView;

    public MainLayout() {
        initWidget(uiBinder.createAndBindUi(this));

        agentsView = new AgentsView();
        sessionsView = new SessionsView();
        dashboardView = new DashboardView();
                
        mainPanel.setWidget(dashboardView);

        menu.setHandler(new MenuPanelHandler() {
            @Override
            public void switchToAgent() {
                agentsView.close();
                mainPanel.setWidget(agentsView);
            }

            @Override
            public void switchToSession() {
                agentsView.close();
                mainPanel.setWidget(sessionsView);
            }

            @Override
            public void switchToDashboard() {
                agentsView.close();
                mainPanel.setWidget(dashboardView);
            }
        });
    }

    public void init() {
        try {
            version.setInnerText("Loading ...");
            RestClient.create(ConfigService.class, versionCallback).getVersion();
        } catch (Exception ex) {
            Window.alert("Error: " + ex.getMessage());
        }
        agentsView.refresh();
    }

    interface MyUiBinder extends UiBinder<Widget, MainLayout> {
    }
    
    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
    
    final RemoteCallback<String> versionCallback = new RemoteCallback<String>() {
        @Override
        public void callback(String value) {
            version.setInnerText(value);
        }
    };
}
