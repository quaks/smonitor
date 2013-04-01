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
package com.ajkaandrej.gwt.uc.form.item;

import com.google.gwt.cell.client.TextCell;

/**
 *
 * @author Andrej Petras <andrej@ajka-andrej.com>
 */
public abstract class TextFormItem<T> extends AbstractFormItem<T, String, String> {
    
    public TextFormItem() {
        super(new TextCell());
    }
}