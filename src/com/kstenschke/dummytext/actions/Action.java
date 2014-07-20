/*
 * Copyright 2013-2014 Kay Stenschke
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
package com.kstenschke.dummytext.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.project.Project;
import com.kstenschke.dummytext.PluginPreferences;
import com.kstenschke.dummytext.resources.StaticTexts;

public class Action extends AnAction {

	/**
	 * Disable when no editor available
	 *
	 * @param	event		Action system event
	 */
	public void update( AnActionEvent event ) {
		Boolean isEditorAvailable = event.getData(PlatformDataKeys.EDITOR) != null;

		event.getPresentation().setEnabled(isEditorAvailable);
	}

	/**
	 * Insert or replace selection with random dummy text
	 *
	 * @param   event    ActionSystem event
	 */
	public void actionPerformed(final AnActionEvent event) {
		Project currentProject = event.getData(PlatformDataKeys.PROJECT);

		CommandProcessor.getInstance().executeCommand(currentProject, new Runnable() {
			public void run() {
				ApplicationManager.getApplication().runWriteAction(new Runnable() {
					public void run() {
						String genreCode  = PluginPreferences.getGenreCode();
						new ActionPerformer(genreCode).write(event);
					}
				});
			}
		}, StaticTexts.HISTORY_INSERT_DUMMY_TEXT, UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION);
	}

}