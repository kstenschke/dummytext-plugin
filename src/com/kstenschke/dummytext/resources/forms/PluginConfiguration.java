/*
 * Copyright 2013 Kay Stenschke
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

package com.kstenschke.dummytext.resources.forms;

import com.kstenschke.dummytext.PluginPreferences;

import javax.swing.*;
import java.awt.event.*;


public class PluginConfiguration {

    public JPanel rootPanel;
	private JPanel TopPanel;

	private JRadioButton hospitalRadioButton;
	private JRadioButton latinRadioButton;
	private JRadioButton priratesRadioButton;
	private JRadioButton scifiRadioButton;
	private JRadioButton esotericRadioButton;


	/**
     * Constructor
     */
    public PluginConfiguration() {
        InitForm();
    }



    /**
     * Initialize the form: render project tree, select nodes from project preference
     */
    private void InitForm() {
			// Select default/preferred genre
		String preferredGenre   = PluginPreferences.getGenre();

	    if( preferredGenre != null ) {
		    if( preferredGenre.equals("scifi") ) {
			    scifiRadioButton.setSelected(true);
		    } else if( preferredGenre.equals("pirates") ) {
			    priratesRadioButton.setSelected(true);
		    } else if( preferredGenre.equals("hospital") ) {
			    hospitalRadioButton.setSelected(true);
		    } else if( preferredGenre.equals("esoteric") ) {
			    esotericRadioButton.setSelected(true);
		    } else {
			    latinRadioButton.setSelected(true);
		    }
	    }
        
	}



    /**
     * Reset the form to factory default
     */
    private void onClickReset(ActionEvent e) {

    }



    public JPanel getRootPanel() {
        return rootPanel;
    }



    /**
     * Config modified?
     *
     * @return Boolean
     */
    public boolean isModified() {
        return ! (	getData().equals( PluginPreferences.getGenre() ) );
    }



	public Boolean isSelectedPirates() {
		return priratesRadioButton.isSelected();
	}

	public Boolean isSelectedSciFi() {
		return scifiRadioButton.isSelected();
	}

	public Boolean isSelectedLatin() {
		return latinRadioButton.isSelected();
	}

	public Boolean isSelectedHospital() {
		return hospitalRadioButton.isSelected();
	}

	public Boolean isSelectedEsoteric() {
		return esotericRadioButton.isSelected();
	}



    public void setData() {

	}



	/**
	 * Get selected genre
	 *
	 * @return  String
	 */
	public String getData() {
		return isSelectedPirates() ? "pirates" :
				   (isSelectedSciFi() ? "scifi" :
							(isSelectedLatin() ? "latin" :
									(isSelectedHospital() ? "hospital" :
											"esoteric"
				  )));
	}

    
    private void createUIComponents() {
// TODO: place custom component creation code here
    }

}
