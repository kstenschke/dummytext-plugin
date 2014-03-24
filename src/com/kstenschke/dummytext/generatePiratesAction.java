package com.kstenschke.dummytext;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.command.UndoConfirmationPolicy;
import com.intellij.openapi.project.Project;

public class generatePiratesAction extends AnAction {

	public void actionPerformed(final AnActionEvent event) {
		Project currentProject = event.getData(PlatformDataKeys.PROJECT);

		CommandProcessor.getInstance().executeCommand(currentProject, new Runnable() {
			public void run() {
				ApplicationManager.getApplication().runWriteAction(new Runnable() {
					public void run() {
						new ActionPerformer(StaticTexts.GENRE_CODE_PIRATES).write(event);
					}
				});
			}
		}, StaticTexts.HISTORY_INSERT_PIRATES, UndoConfirmationPolicy.DO_NOT_REQUEST_CONFIRMATION);
	}

}
