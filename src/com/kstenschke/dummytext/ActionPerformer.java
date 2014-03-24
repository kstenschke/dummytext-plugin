/*
 * Copyright 2014 Kay Stenschke
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
import com.kstenschke.dummytext.helpers.TextualHelper;
import com.kstenschke.dummytext.dictionaries.*;

class ActionPerformer {

	/**
	 * Dictionary of preferred genre vocabulary
	 */
	Dictionary genreDictionary;



	/**
	 * Constructor
	 */
	public ActionPerformer(String genreCode) {
		PluginPreferences.saveGenre(genreCode);

		if( genreCode.equals(StaticTexts.GENRE_CODE_PIRATES) ) {
			genreDictionary   = new DictionaryPirates();

		} else if( genreCode.equals(StaticTexts.GENRE_CODE_SCIFI) ) {
			genreDictionary   = new DictionarySciFi();

		} else if( genreCode.equals(StaticTexts.GENRE_CODE_ESCOTERIC) ) {
			genreDictionary   = new DictionaryEsoteric();

		} else if( genreCode.equals(StaticTexts.GENRE_CODE_COOKERY) ) {
			genreDictionary   = new DictionaryCookery();

		} else {
			PluginPreferences.saveGenre(StaticTexts.GENRE_CODE_LATIN);
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

			String trailingPunctuation  = TextualHelper.getTrailingPunctuationMark(selectedText);
			Integer amountLines         = 1;
			Integer amountWords         = null;  // applies only when replacing a single-lined selection, can be rounded up

				// Generated dummy text will replace the current selected text
			if (hasSelection && selectedText != null ) {
				Integer selectionLength = selectedText.length();

				if( selectionLength > 0 ) {
					amountLines = TextualHelper.countLines(selectedText);

					if( amountLines == 1) {
						amountWords = TextualHelper.getWordCount(selectedText);
					}
				}
			}

                // Generate and insert / replace selection with dummy text
            String dummyText  = generateText(amountLines, amountWords, trailingPunctuation, selectedText).toString();

            CaretModel caretModel   = editor.getCaretModel();
            Integer dummyTextLength = dummyText.length();
            Integer offsetStart;

            if( hasSelection ) {
                    // Move caret to end of selection
                offsetStart = selectionModel.getSelectionStart();
                Integer offsetEnd = selectionModel.getSelectionEnd();

                document.replaceString(offsetStart, offsetEnd, dummyText);
                selectionModel.setSelection(offsetStart, offsetStart + dummyTextLength );
                caretModel.moveToOffset( offsetStart + dummyTextLength );
            } else {
                    // Move caret to end of inserted text
                offsetStart  = caretModel.getOffset();
                dummyText   = dummyText.trim();
                document.insertString(offsetStart, dummyText);
                caretModel.moveToOffset(offsetStart + dummyText.length() );
            }
		}
	}

	/**
	 * @param   amountLines          Amount of lines
	 * @param   amountWords          Amount of words (per line, only given for single lined selection)
	 * @param   trailingPunctuation  Trailing punctuation to be cast to the generated string's ending
	 * @return  Random dummy text of the given amount of lines and at least the given string-length
	 */
	private CharSequence generateText(Integer amountLines, Integer amountWords, String trailingPunctuation, String textToBeReplaced) {
		String dummyText = "";

		if( amountLines > 1 ) {
			amountWords = null;
		}

			// Add random sentences until the given text length is reached
		Integer linesCount = 0;
		String[] originalLines = textToBeReplaced != null ? textToBeReplaced.split("\\n") : null;
		String dummyLine;

		while( linesCount < amountLines ) {
			String originalLine;
			String leadingWhiteSpace    = "";
			int casing                  = 0;
			Boolean isEmpty             = false;

			if( originalLines != null ) {
				originalLine      = originalLines[linesCount];

				amountWords       = TextualHelper.getWordCount(originalLine);
				isEmpty           = amountWords == null || amountWords == 0;
				leadingWhiteSpace = TextualHelper.getLeadingWhiteSpace(originalLine);
				casing            = TextualHelper.getCasing(originalLine);
			}

            Integer amountWordsLacking;
			if(!isEmpty) {
				dummyLine   = leadingWhiteSpace + genreDictionary.getRandomLine(amountWords);
                while( amountWords != null && TextualHelper.getWordCount(dummyLine) < amountWords - 2 ) {
                    amountWordsLacking  = amountWords - TextualHelper.getWordCount(dummyLine);
                    if( amountWordsLacking < 4 ) {
                        amountWordsLacking = 4;
                    }
                    dummyLine   += " " + genreDictionary.getRandomLine( amountWordsLacking  );
                }

				dummyLine   = TextualHelper.setCasing(dummyLine, casing);
			} else {
				dummyLine   = "";
			}

			dummyText   = dummyText.concat(dummyLine);
			dummyText   = dummyText.concat(amountLines > 1 ? "\n" : " ");

			linesCount++;
		}

		if( textToBeReplaced != null && trailingPunctuation != null && !textToBeReplaced.isEmpty() && !trailingPunctuation.isEmpty() ) {
			dummyText = TextualHelper.castTrailingPunctuation(dummyText, trailingPunctuation);
		}

		return dummyText;
	}

}
