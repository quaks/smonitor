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

import com.ajkaandrej.smonitor.agent.rs.model.Application;
import com.ajkaandrej.smonitor.agent.rs.model.Server;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public class ServerTree extends Tree {

    public interface ServerTreeImages extends Tree.Resources {

        ImageResource inbox();

        /**
         * Use noimage.png, which is a blank 1x1 image.
         */
        @Override
        @Source("noimage.png")
        ImageResource treeLeaf();
    }

    private ServerTreeImages images;
    
    public ServerTree(ServerTreeImages resources) {
        super(resources);
        setAnimationEnabled(true);
        images = resources;
    }
    
    public void loadServer(Server server) {
        this.clear();
        TreeItem parent = this.addTextItem(server.getName());
        for (Application app : server.getApplications()) {
            addItem(parent, images.inbox(), app.getName());
        }
    }

    private void addItem(TreeItem root, ImageResource image, String label) {
        SafeHtmlBuilder itemHtml = new SafeHtmlBuilder();
        itemHtml.append(AbstractImagePrototype.create(image).getSafeHtml());
        itemHtml.appendHtmlConstant(" ").appendEscaped(label);
        root.addItem(itemHtml.toSafeHtml());
    }
}