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

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.kstenschke.dummytext.helpers.TextualHelper;
import com.kstenschke.dummytext.dictionaries.*;
import com.kstenschke.dummytext.resources.StaticTexts;

class ActionPerformer {

    /**
     * Dictionary of preferred genre vocabulary
     */
    final private Dictionary genreDictionary;

    /**
     * Constructor
     */
    ActionPerformer(String genreCode) {
        PluginPreferences.saveGenre(genreCode);

        switch (genreCode) {
            case StaticTexts.GENRE_CODE_PIRATES:
                genreDictionary = new DictionaryPirates();
                break;
            case StaticTexts.GENRE_CODE_SCIFI:
                genreDictionary = new DictionarySciFi();
                break;
            case StaticTexts.GENRE_CODE_ESCOTERIC:
                genreDictionary = new DictionaryEsoteric();
                break;
            case StaticTexts.GENRE_CODE_COOKERY:
                genreDictionary = new DictionaryCookery();
                break;
            default:
                PluginPreferences.saveGenre(StaticTexts.GENRE_CODE_LATIN);
                genreDictionary = new DictionaryLatin();
                break;
        }
    }

    /**
     * @param   event    ActionSystem event
     */
    void write(final AnActionEvent event) {
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        if (editor != null) {
            final Document document = editor.getDocument();
    
            CaretModel caretModel = editor.getCaretModel();
    
            for (Caret caret : caretModel.getAllCarets()) {
                boolean hasSelection = caret.hasSelection();
                String selectedText  = caret.getSelectedText();
    
                String trailingCharacter    = TextualHelper.getTrailingPunctuationMark(selectedText);
                String leadingCharacters    = TextualHelper.getLeadingPreservation(selectedText);
    
                int amountLines     = 1;
                /* applies only when replacing a single-lined selection, can be rounded up */
                Integer amountWords = null;
    
                /* Generated dummy text will replace the current selected text */
                if (hasSelection && selectedText != null ) {
                    int selectionLength = selectedText.length();
                    if (selectionLength > 0) {
                        amountLines = TextualHelper.countLines(selectedText);
                        if (amountLines == 1) {
                            amountWords = TextualHelper.getWordCount(selectedText);
                        }
                    }
                }
    
                /* Generate and insert / replace selection with dummy text */
                String dummyText = generateText(amountLines, amountWords, leadingCharacters, trailingCharacter,
                        selectedText).toString();
    
                Integer dummyTextLength = dummyText.length();
                Integer offsetStart;
    
                if (hasSelection) {     /* Move caret to end of selection */
                    offsetStart = caret.getSelectionStart();
                    int offsetEnd = caret.getSelectionEnd();
    
                    document.replaceString(offsetStart, offsetEnd, dummyText);
                    caret.setSelection(offsetStart, offsetStart + dummyTextLength);
                } else {                /* Move caret to end of inserted text */
                    offsetStart  = caretModel.getOffset();
                    dummyText   = dummyText.trim();
                    document.insertString(offsetStart, dummyText);
                }
            }
    
        }
    }

    /**
     * @param   amountLines          Amount of lines
     * @param   amountWords          Amount of words (per line, only given for single lined selection)
     * @param   leadingCharacters    Leading whitespace and e.g. quotation to be preserved
     * @param   trailingPunctuation  Trailing punctuation to be cast to the generated string's ending
     * @return  Random dummy text of the given amount of lines and at least the given string-length
     */
    private CharSequence generateText(Integer amountLines, Integer amountWords,
                                      String leadingCharacters, String trailingPunctuation, String textToBeReplaced) {
        String dummyText = "";

        if (amountLines > 1) {
            amountWords = null;
        }

        /* Add random sentences until the given text length is reached */
        Integer linesCount = 0;
        String[] originalLines = textToBeReplaced != null ? textToBeReplaced.split("\\n") : null;
        String dummyLine;

        while (linesCount < amountLines) {
            String originalLine;
            String leadingWhiteSpace    = "";
            int casing                  = 0;
            boolean isEmpty             = false;

            if (originalLines != null) {
                originalLine      = originalLines[linesCount];
                amountWords       = TextualHelper.getWordCount(originalLine);
                isEmpty           = amountWords == null || amountWords == 0;
                leadingWhiteSpace = TextualHelper.getLeadingWhiteSpace(originalLine);
                casing            = TextualHelper.getCasing(originalLine);
            }

            int amountWordsLacking;
            if (!isEmpty) {
                dummyLine   = leadingWhiteSpace + genreDictionary.getRandomLine(amountWords);
                while (null != amountWords && TextualHelper.getWordCount(dummyLine) < amountWords - 2) {
                    amountWordsLacking  = amountWords - TextualHelper.getWordCount(dummyLine);
                    if (amountWordsLacking < 4) {
                        amountWordsLacking = 4;
                    }
                    dummyLine += " " + genreDictionary.getRandomLine(amountWordsLacking);
                }

                dummyLine = TextualHelper.setCasing(dummyLine, casing);
            } else {
                dummyLine = "";
            }

            dummyText = dummyText.concat(dummyLine);
            dummyText = dummyText.concat(amountLines > 1 ? "\n" : " ");

            linesCount++;
        }

        if (
               textToBeReplaced != null
            && leadingCharacters != null
            && !textToBeReplaced.isEmpty()
            && !leadingCharacters.isEmpty()
        ) {
            dummyText = leadingCharacters + dummyText;
        }
        if (
               textToBeReplaced != null
            && trailingPunctuation != null
            && !textToBeReplaced.isEmpty()
            && !trailingPunctuation.isEmpty()
        ) {
            dummyText = TextualHelper.castTrailingPunctuation(dummyText, trailingPunctuation);
        }

        return dummyText;
    }
}
