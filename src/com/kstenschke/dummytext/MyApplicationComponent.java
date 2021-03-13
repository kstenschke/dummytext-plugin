/*
 * Copyright Kay Stenschke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kstenschke.dummytext;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.ex.ActionManagerEx;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.extensions.PluginId;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MyApplicationComponent {

    private Class lastAction;
    private Map<Class, AnAction> classToActionMap;

    private static MyApplicationComponent getInstance() {
        return ApplicationManager.getApplication().getComponent(MyApplicationComponent.class);
    }

    public AnAction getAnAction() {
        return getActionMap().get(lastAction);
    }

    static void setAction(Class itsAction) {
        if (itsAction != null) {
            getInstance().lastAction = itsAction;
        }
    }

    @NotNull
    private Map<Class, AnAction> getActionMap() {
        if (this.classToActionMap != null) {
            return this.classToActionMap;
        }

        ActionManagerEx instanceEx = ActionManagerEx.getInstanceEx();
        PluginId pluginId = PluginId.getId("String Manipulation");

        HashMap<Class, AnAction> classToActionMap = new HashMap<>();
        for (String string_manipulation : instanceEx.getPluginActions(pluginId)) {
            AnAction action = instanceEx.getAction(string_manipulation);
            classToActionMap.put(action.getClass(), action);
        }
        this.classToActionMap = classToActionMap;

        return this.classToActionMap;
    }
}
