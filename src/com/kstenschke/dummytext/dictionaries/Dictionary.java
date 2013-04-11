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

package com.kstenschke.dummytext.dictionaries;

import com.kstenschke.dummytext.TextualHelper;

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
		Integer amountSentences  = getRandomNumber(4) > 3 ? 2 : 1;

		return getRandomLine(amountWords, amountSentences);
	}

	/**
	 * @param   sentence
	 * @return  The sentence with the indefinite article "a" changed into "an", when preceding words starting with a vowel
	 */
	protected String fixIndefiniteArticles(String sentence) {
		sentence = sentence.replaceAll(" a a", " an a");
		sentence = sentence.replaceAll(" a e", " an e");
		sentence = sentence.replaceAll(" a i", " an i");
		sentence = sentence.replaceAll(" a o", " an o");

		if( sentence.startsWith("a a") || sentence.startsWith("a e") || sentence.startsWith("a o") ) {
			sentence = "An " + sentence.substring(3);
		}

		return sentence;
	}

	/**
	 * @param   min   Minimum
	 * @param   max   Maximum
	 * @return  Random number within min to max
	 */
	protected static Integer getRandomNumber(Integer min, Integer max) {
		return min + (int)(Math.random() * ((max - min) + 1));
	}

	/**
	 * @param   max
	 * @return  Random number between 0 and max
	 */
	protected static Integer getRandomNumber(Integer max) {
		return getRandomNumber(0, max);
	}

	/**
	 * @param   words    Array of strings
	 * @return  One random item out of the given array, optionally one that contains the given amount of words
	 */
	protected static String pickRandomString(String[] words, Integer amountWords)  {
		if( amountWords != null && amountWords > 0 ) {
				// Reduce sentences to items with the closest to the given amount of words
			words = TextualHelper.filterByWordCount(words, amountWords, 5);
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
	protected static String ucfirst(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
