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

package com.kstenschke.dummytext.dictionaries;

import com.kstenschke.dummytext.helpers.TextualHelper;

public class Dictionary {

	/**
	 * @param   amountWords
	 * @param   amountSentences
	 * @return  Random line/s of text, consisting from the given amount of words if 1 sentence / sentences
	 */
	public String getRandomLine(Integer amountWords, Integer amountSentences) {
		return "";
	}

	/**
	 * @param   amountWords
	 * @return  One random line of text, consisting from roughly the given amount of words
	 */
	public String getRandomLine(Integer amountWords) {
		Integer amountSentences  = 1;

		return getRandomLine(amountWords, amountSentences).trim();
	}

	/**
	 * @param   max     Maximum
	 * @return  Random  number within min to max
	 */
	protected static Integer getRandomNumber(Integer max) {
		return (int)(Math.random() * ((max) + 1));
	}

	/**
	 * @param   words    Array of strings
	 * @return  One random item out of the given array, optionally one that contains the given amount of words
	 */
	protected static String pickRandomString(String[] words, Integer amountWords)  {
		if( amountWords != null && amountWords > 0 ) {
				// Reduce sentences to items with the closest to the given amount of words
			words = TextualHelper.filterByWordCount(words, amountWords, 2);
		}

		return words[ getRandomNumber( words.length -1 ) ];
	}

	/**
	 * @param   words
	 * @return  One random item of the given array of words
	 */
	protected static String pickRandomString(String[] words) {
		return pickRandomString(words, null);
	}

	/**
	 * @param   str
	 * @return  Given string with first character made uppercase
	 */
	protected static String ucFirst(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
