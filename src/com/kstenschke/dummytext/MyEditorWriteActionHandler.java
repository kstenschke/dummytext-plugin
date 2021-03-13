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

import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class MyEditorWriteActionHandler<T> extends EditorActionHandler {
    private final Class actionClass;

    MyEditorWriteActionHandler(Class actionClass) {
        super(false);
        this.actionClass = actionClass;
    }

    @Override
    protected final void doExecute(@NotNull final Editor editor, @Nullable final Caret caret, final DataContext dataContext) {
        MyApplicationComponent.setAction(actionClass);

        final Pair<Boolean, T> additionalParameter = beforeWriteAction(editor, dataContext);
        if (!additionalParameter.first) {
            return;
        }

        final Runnable runnable = () -> executeWriteAction(editor, caret, dataContext, additionalParameter.second);
        new EditorWriteActionHandler(false) {
            @Override
            public void executeWriteAction(Editor editor1, @Nullable Caret caret1, DataContext dataContext1) {
                runnable.run();
            }
        }.doExecute(editor, caret, dataContext);
    }

    protected abstract void executeWriteAction(Editor editor, @Nullable Caret caret, DataContext dataContext, @Nullable T additionalParameter);

    @NotNull
    protected Pair<Boolean, T> beforeWriteAction(Editor editor, DataContext dataContext) {
        return continueExecution();
    }

    final Pair<Boolean, T> stopExecution() {
        return new Pair<>(false, null);
    }

    private Pair<Boolean, T> continueExecution() {
        return new Pair<>(true, null);
    }
}
