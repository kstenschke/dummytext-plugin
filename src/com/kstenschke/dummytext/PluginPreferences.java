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

package com.kstenschke.dummytext;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import org.jetbrains.annotations.NonNls;
import com.intellij.ide.util.PropertiesComponent;

/**
 * Utility functions for preferences handling
 * All preferences of the DummyText plugin are stored on project level
 */
public class PluginPreferences {

	// @NonNls = element is not a string requiring internationalization and it does not contain such strings.
	@NonNls
	private static final String PROPERTY_GENRE = "PluginDummyText.Genre";



	/**
	 * @return	The currently opened project
	 */
	private static Project getOpenProject() {
		Project[] projects = ProjectManager.getInstance().getOpenProjects();

		return (projects.length > 0) ? projects[0] : null;
	}



	/**
	 * @return	PropertiesComponent (project level)
	 */
	private static PropertiesComponent getPropertiesComponent() {
		Project project = getOpenProject();

		return project != null ? PropertiesComponent.getInstance(project) : null;
	}



	/**
	 * Store preference: genre
	 *
	 * @param	genre       Genre code, e.g. "pirates" (default) / "scifi" / ...
	 */
	public static void saveGenre(String genre) {
		PropertiesComponent propertiesComponent = getPropertiesComponent();

		if( propertiesComponent != null ) {
			propertiesComponent.setValue(PROPERTY_GENRE, genre);
		}
	}



	/**
	 * Get preference: genre
	 *
	 * @return  String      Genre code, e.g. "scifi", "pirates", "latin" (default)
	 */
	public static String getGenreCode() {
		PropertiesComponent propertiesComponent = getPropertiesComponent();
		String genre	= null;

		if( propertiesComponent != null ) {
			genre	= propertiesComponent.getValue(PROPERTY_GENRE);
		}

		return genre == null ? "latin" : genre;
	}

}