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
package org.lorislab.smonitor.gwt.uc.form.property;

import com.google.gwt.user.client.ui.CheckBox;

/**
 * The boolean check-box property.
 *
 * @author Andrej Petras
 */
public abstract class BooleanCheckBoxProperty<T> extends ModelFormProperty<T, CheckBox, Boolean> {

    /**
     * The default constructor.
     */
    public BooleanCheckBoxProperty() {
        this(false);
    }

    /**
     * The default constructor.
     *
     * @param readOnly the read only flag.
     */
    public BooleanCheckBoxProperty(boolean readOnly) {
        super(new CheckBox());
        widget.setEnabled(!readOnly);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setWidgetValue(Boolean value) {
        widget.setValue(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean getWidgetValue() {
        return widget.getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CheckBox getWidget(String styleName) {
        if (styleName != null) {
            widget.setStyleName(styleName);
        }
        return widget;
    }
}
