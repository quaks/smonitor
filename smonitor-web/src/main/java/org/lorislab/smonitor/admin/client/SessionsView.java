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

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import org.lorislab.smonitor.gwt.uc.page.ViewPage;

/**
 *
 * @author Andrej Petras
 */
public class SessionsView extends ViewPage {

    @Override
    public void openPage() {
        
    }

    @Override
    public void closePage() {
        
    }
    
    interface MyUiBinder extends UiBinder<Widget, SessionsView> { }
    private static SessionsView.MyUiBinder uiBinder = GWT.create(SessionsView.MyUiBinder.class);
    
    public SessionsView() {
        initWidget(uiBinder.createAndBindUi(this));
    }    
}
