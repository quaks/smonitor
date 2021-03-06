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
package org.lorislab.smonitor.admin.client.factory;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import org.lorislab.smonitor.admin.client.panel.PleaseWaitPanel;

/**
 * The please wait panel factory.
 * 
 * @author Andrej Petras
 */
public final class PleaseWaitPanelFactory {
 /**
     * The panel instance.
     */
    private static PleaseWaitPanel PANEL = null;
    
    /**
     * The open flag.
     */
    private static boolean IS_OPEN = false;
    
    /**
     * Opens the please wait dialog.
     */
    public static void open() {
	
		if (PANEL == null) {
			PANEL = new PleaseWaitPanel();
		}

		if (!IS_OPEN) {
			PANEL.setPopupPosition(Window.getClientWidth() / 2 - 50, Window.getClientHeight() / 2 - 45);
			PANEL.show();
			IS_OPEN = true;
		}
	}

    /**
     * Close the please wait dialog.
     * 
     * @param reload the reload flag.
     */
	public static void close(boolean reload) {
		if (PANEL != null) {
			PANEL.hide();

			IS_OPEN = false;
			if (reload) {
				Window.Location.reload();
			}
		}

	}        
}
