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

import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PopupChoiceAction extends EditorAction {

    public PopupChoiceAction() {
        super(new MyEditorWriteActionHandler(null) {
            @NotNull

            @Override
            protected Pair beforeWriteAction(Editor editor, DataContext dataContext) {
                ListPopup popup = JBPopupFactory.getInstance().createActionGroupPopup(
                    null,
                    (ActionGroup) ActionManager.getInstance().getAction("Dummytext.GeneratePopupGroup"),
                    dataContext, JBPopupFactory.ActionSelectionAid.ALPHA_NUMBERING, true
                );

                popup.showInBestPositionFor(dataContext);
                return stopExecution();
            }

            @Override
            protected void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext,
                                              @Nullable Object additionalParameter) {
            }
        });
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        Editor editor = CommonDataKeys.EDITOR.getData(e.getDataContext());
        if (editor == null) {
            e.getPresentation().setEnabled(false);
            return;
        }
        if (getEventProject(e) != null) {
            e.getPresentation().setEnabled(true);
        }
    }
}
