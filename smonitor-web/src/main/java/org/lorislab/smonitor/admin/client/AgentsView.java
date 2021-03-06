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
package org.lorislab.smonitor.admin.client;

import org.lorislab.smonitor.admin.client.listener.AgentChangeListener;
import org.lorislab.smonitor.admin.client.panel.AgentDialogBox;
import org.lorislab.smonitor.gwt.uc.dialogbox.EntityDialogBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.util.List;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.lorislab.smonitor.admin.client.handler.DialogEventHandler;
import org.lorislab.smonitor.admin.client.panel.AgentGridPanel;
import org.lorislab.smonitor.admin.client.model.AgentWrapper;
import org.lorislab.smonitor.admin.client.handler.TableRowHoverHandler;
import org.lorislab.smonitor.admin.client.panel.QuestionDialogBox;
import org.lorislab.smonitor.admin.client.service.RestServiceExceptionCallback;
import org.lorislab.smonitor.admin.client.service.Client;
import org.lorislab.smonitor.admin.client.service.ClientFactory;
import org.lorislab.smonitor.admin.client.toolbar.AgentToolbarPanel;
import org.lorislab.smonitor.gwt.uc.page.ViewPage;
import org.lorislab.smonitor.gwt.uc.table.EntityDataGrid;
import org.lorislab.smonitor.rs.admin.model.Agent;
import org.lorislab.smonitor.rs.admin.service.AgentRestService;
import org.lorislab.smonitor.rs.exception.RestServiceException;
import org.lorislab.smonitor.rs.model.ServerInfo;
import org.lorislab.smonitor.rs.service.ServerService;

/**
 * The agents view.
 * 
 * @author Andrej Petras
 */
public final class AgentsView extends ViewPage {

    @UiField
    AgentGridPanel agentPanel;
    @UiField
    Button btnAgentRefresh;
    @UiField
    Button btnAgentAdd;
    
    @UiField
    Label resultCount;
    private AgentChangeListener agentChangeListener;
    
    private AgentDialogBox dialogBox = new AgentDialogBox();
    private Client<ServerService> serverService = ClientFactory.create(ServerService.class);
    private Client<AgentRestService> agentService = ClientFactory.create(AgentRestService.class);
    private AgentToolbarPanel tableMenu = new AgentToolbarPanel();    
    private QuestionDialogBox<String> deleteQuestion = new QuestionDialogBox<String>();

    public AgentsView() {
        initWidget(uiBinder.createAndBindUi(this));

        dialogBox.setCreateHandler(new DialogEventHandler<Agent>() {
            @Override
            public void event(Agent value) {
                agentService.call(agentUpdate).update(value.getGuid(), value);

            }
        });

        dialogBox.setUpdateHandler(new DialogEventHandler<Agent>() {
            @Override
            public void event(Agent value) {
                agentService.call(agentUpdate).update(value.getGuid(), value);
            }
        });

        btnAgentRefresh.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                refresh();
            }
        });

        btnAgentAdd.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                agentService.call(agentCreate).create();
            }
        });

        deleteQuestion.setOkHandler(new DialogEventHandler<String>() {
            @Override
            public void event(String value) {
                agentService.call(agentDelete).delete(value);
            }
        });

        tableMenu.setHandler(new AgentToolbarPanel.ClickButtonHandler() {
            @Override
            public void edit(AgentWrapper data) {
                openDialog(data.data, EntityDialogBox.Mode.UPDATE);
            }

            @Override
            public void password(AgentWrapper data) {
            }

            @Override
            public void info(AgentWrapper data) {
            }

            @Override
            public void refresh(AgentWrapper data) {
                refreshAgent(data);
            }

            @Override
            public void delete(AgentWrapper data) {
                deleteQuestion.open(data.data.getGuid(), "Delete Agent", "Do you really want to delete selected agent " + data.data.getName() + " ?");
            }
        });


        agentPanel.setTableRowHoverHandler(new TableRowHoverHandler() {
            @Override
            public void onRowOver(TableRowElement row) {
                int index = row.getRowIndex();
                AgentWrapper w = agentPanel.get(index);
                TableCellElement cell = row.getCells().getItem(0);
                tableMenu.open(cell.getAbsoluteLeft(), cell.getAbsoluteTop(), w);
            }

            @Override
            public void onRowOut() {
                tableMenu.close();
            }
        });

        agentPanel.setChangeSizeHandler(new EntityDataGrid.ChangeSizeHandler() {
            @Override
            public void changeSize(int size) {
                resultCount.setText("# " + size);
            }
        });
    }

    public void setAgentChangeListener(AgentChangeListener agentChangeListener) {
        this.agentChangeListener = agentChangeListener;
    }
    
    public void refresh() {
        agentService.call(agents).get();
    }
    
    final RemoteCallback<List<Agent>> agents = new RemoteCallback<List<Agent>>() {
        @Override
        public void callback(List<Agent> value) {
            agentPanel.reset();
            if (value != null) {                
                for (Agent agent : value) {
                    AgentWrapper w = agentPanel.addItem(agent);
                    refreshAgent(w);     
                }
            }
        }
    };
    
    private void refreshAgent(AgentWrapper data) {      
        if (data != null) {
            if (data.data.isEnabled() && !data.request) {
                agentPanel.request(data);
                serverService.call(serverInfo, serverInfoError).getServer(data.data.getGuid());
            } else {
                agentPanel.error(data.data.getGuid(), null);
                // update choose list in the session view
                if (agentChangeListener != null) {
                    agentChangeListener.agentChanged(agentPanel.get());
                }                   
            }        
        }
    }
    
    final RestServiceExceptionCallback serverInfoError = new RestServiceExceptionCallback() {
        @Override
        public void exception(RestServiceException exception) {
            agentPanel.error(exception.getRef(), exception.getMessage());
        }
    };
    /**
     * The remote call to get the service info for the agent.
     */
    final RemoteCallback<ServerInfo> serverInfo = new RemoteCallback<ServerInfo>() {
        /**
         * {@inheritDoc}
         */
        @Override
        public void callback(ServerInfo value) {
            // update the server info in the agent panel
            AgentWrapper w = agentPanel.update(value);
            // update choose list in the session view
            if (w != null && agentChangeListener != null) {
                agentChangeListener.agentChanged(agentPanel.get());
            }            
        }
    };
    final RemoteCallback<String> agentDelete = new RemoteCallback<String>() {
        @Override
        public void callback(String value) {
            AgentWrapper w = agentPanel.removeById(value);
            if (w != null && agentChangeListener != null) {
                agentChangeListener.agentChanged(agentPanel.get());
            }
            deleteQuestion.close();
        }
    };
    final RemoteCallback<Agent> agentCreate = new RemoteCallback<Agent>() {
        @Override
        public void callback(Agent value) {
            openDialog(value, EntityDialogBox.Mode.CREATE);
        }
    };
    final RemoteCallback<Agent> agentUpdate = new RemoteCallback<Agent>() {
        @Override
        public void callback(Agent value) {
            AgentWrapper w = agentPanel.update(value);
            dialogBox.close();
            refreshAgent(w);                                    
        }
    };

    private void openDialog(Agent agent, EntityDialogBox.Mode mode) {
        dialogBox.center();
        dialogBox.open(agent, mode);
    }

    @Override
    public void openPage() {
        tableMenu.close();
        deleteQuestion.close();
    }

    @Override
    public void closePage() {
        tableMenu.close();
        deleteQuestion.close();
    }

    @Override
    public String getPageTitle() {
        return "Agents";
    }

    interface MyUiBinder extends UiBinder<Widget, AgentsView> {
    }
    private static AgentsView.MyUiBinder uiBinder = GWT.create(AgentsView.MyUiBinder.class);
}
