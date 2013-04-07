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

package com.kstenschke.dummytext;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.kstenschke.dummytext.dictionaries.*;

class ActionPerformer {

	/**
	 * Dictionary of preferred genre vocabulary
	 */
	Dictionary genreDictionary;



	/**
	 * Constructor
	 */
	public ActionPerformer() {
		String genreCode   = PluginPreferences.getGenre();

		if( genreCode.equals("pirates") ) {
			genreDictionary   = new DictionaryPirates();
		} else if( genreCode.equals("scifi") ) {
			genreDictionary   = new DictionarySciFi();
		} else if( genreCode.equals("hospital") ) {
			genreDictionary   = new DictionaryHospital();
		} else if( genreCode.equals("esoteric") ) {
			genreDictionary   = new DictionaryEsoteric();
		} else {
			genreDictionary   = new DictionaryLatin();
		}
	}



	/**
	 * @param   event    ActionSystem event
	 */
	public void write(final AnActionEvent event) {
		Editor editor = event.getData(PlatformDataKeys.EDITOR);

		if (editor != null) {
			final Document document = editor.getDocument();
			SelectionModel selectionModel = editor.getSelectionModel();
			boolean hasSelection = selectionModel.hasSelection();
			String selectedText  = selectionModel.getSelectedText();

			Boolean isUpperCase  = false;
			Boolean isLowerCase  = false;
			Boolean isUcFirst    = false;

			String trailingPunctuation = TextualHelper.getTrailingPunctuationMark(selectedText);

			Integer dummyLength  = 100; // minimum overall string length
			Integer amountLines  = 1;
			Integer amountWords  = 1;  // applies only when replacing a single-lined selection, can be rounded up

				// Generated dummy text will replace the current selected text
			if (hasSelection && selectedText != null ) {
				if( TextualHelper.isAllUppercase(selectedText) ) {
					isUpperCase = true;
					isLowerCase = false;
					isUcFirst   = false;
				} else {
					isLowerCase = TextualHelper.isAllLowercase(selectedText);
				}
				if( ! isLowerCase ) {
					isUcFirst   = TextualHelper.isUcFirst(selectedText);
				}

				Integer selectionLength = selectedText.length();

				if( selectionLength > 0 ) {
					dummyLength = selectedText.length();
					amountLines = selectedText.split("\\n").length;

					if( amountLines == 1) {
						amountWords = TextualHelper.countWords(selectedText);
					}
				}
			}

			if( dummyLength != null ) {
				// Generate and insert / replace selection with dummy text
				String dummyText  = generateText(dummyLength, amountLines, amountWords, trailingPunctuation).toString();

				if( isLowerCase ) {
					dummyText   = dummyText.toLowerCase();
				} else if( isUpperCase ) {
					dummyText   = dummyText.toUpperCase();
				} else if( isUcFirst ) {
					dummyText   = TextualHelper.ucFirst(dummyText);
				}

				CaretModel caretModel   = editor.getCaretModel();
				Integer dummyTextLength = dummyText.length();
				Integer offsetStart;

				if( hasSelection ) {
					offsetStart = selectionModel.getSelectionStart();
					Integer offsetEnd = selectionModel.getSelectionEnd();

					document.replaceString(offsetStart, offsetEnd, dummyText);
					selectionModel.setSelection(offsetStart, offsetStart + dummyTextLength );
					caretModel.moveToOffset( offsetStart + dummyTextLength );
				} else {
					offsetStart  = caretModel.getOffset();

					document.insertString(offsetStart, dummyText + " ");
					caretModel.moveToOffset( offsetStart + dummyTextLength + 1 );
				}
			}
		}
	}



	/**
	 * @param   approxMaxChars       Minimum string length
	 * @param   amountLines          Amount of lines
	 * @param   amountWords          Amount of words (per line, only given for single lined selection)
	 * @param   trailingPunctuation  Trailing punctuation to be cast to the generated string's ending
	 * @return  Random dummy text of the given amount of lines and at least the given string-length
	 */
	private CharSequence generateText(Integer approxMaxChars, Integer amountWords, Integer amountLines, String trailingPunctuation) {
		String dummyText = "";

			// Add random sentences until the given text length is reached
		Integer linesCount = 0;
		while( dummyText.length() < approxMaxChars && linesCount < amountLines ) {
			dummyText   = dummyText.concat( genreDictionary.getRandomLine(amountWords) );
			dummyText   = dummyText.concat( amountLines > 1 ? "\n" : " ");

			linesCount++;
		}

		return TextualHelper.castTrailingPunctuation(dummyText, trailingPunctuation);
	}

}
