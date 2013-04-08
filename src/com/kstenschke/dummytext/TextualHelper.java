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

import java.util.LinkedList;
import java.util.List;

public class TextualHelper {

	/**
	 * @param   str      String to be checked
	 * @return  Boolean  Are all characters in the given string lower case?
	 */
	public static boolean isAllUppercase(String str) {
		return str.equals(str.toUpperCase());
	}

	/**
	 * @param   str      String to be checked
	 * @return  Boolean  Are all characters in the given string lower case?
	 */
	public static boolean isAllLowercase(String str) {
		return str.equals(str.toLowerCase());
	}

	/**
	 * @param   str   A string
	 * @return  The given string with a capital fist letter
	 */
	public static String ucFirst(String str) {
//		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 *
	 * @param   str   String to be analyzed
	 * @return  Check whether the given string begins with a capital letter
	 */
	public static boolean isUcFirst(String str) {
		return str.substring(0,1).toUpperCase().equals(str.substring(0,1));
	}

	/**
	 * @param   str   String to be parsed
	 * @return  Is the given string not alphabetic (e.g. a punctuation)?
	 */
	public static Boolean isAlphabetic(String str) {
		return     str.matches("[a-z|A-Z]");
	}

	/**
	 * @param   str   String to be parsed
	 * @return  Last character out of given string
	 */
	public static String getLastChar(String str) {
		return str == null || str.isEmpty() ? "" : str.substring(str.length() - 1);
	}

	/**
	 * @param   str   String to be parsed
	 * @return  The trailing punctuation mark character, or null if the string does not end with a punctuation
	 */
	public static String getTrailingPunctuationMark(String str) {
		str   = getLastChar(str);

		return isAlphabetic(str) ? null : str;
	}

	/**
	 * @param   str   String to be analyzed
	 * @return  Amount of (space-separated) words in given string
	 */
	public static Integer getWordCount(String str) {
		str = str.trim();

		return str.isEmpty() ? null : str.split("\\s+").length;
	}

	/**
	 * @param   str                  String to be parsed
	 * @param   trailingPunctuation  Trailing punctuation to be cast to the string's end
	 * @return  String with original trailing punctuation replace by- or extended with- given punctuation.
	 *          If 'trailingPunctuation' is null: the string's trailing punctuation is removed as well
	 */
	public static String castTrailingPunctuation(String str, String trailingPunctuation) {
		str   = str.trim();
		Boolean endsAlphabetic  = TextualHelper.isAlphabetic(TextualHelper.getLastChar(str));

		if( trailingPunctuation != null ) {
			// Replace or add given trailing punctuation
			if( endsAlphabetic ) {
				str   = str.concat(trailingPunctuation);
			} else {
				str   = str.substring(0, str.length() - 1) + trailingPunctuation;
			}
		} else if( !endsAlphabetic) {
			// Remove trailing non-alphabetic character if selection didn't have any either
			str   =	str.substring(0, str.length() - 1);
		}

		return str;
	}

	/**
	 * Reduce given array of sentences (words separated by space) to items with the closest to the given amount of words.
	 * No filtering is done if the closest amount difference is greater than the given tolerance.
	 *
	 * @param   sentences
	 * @param   amountWords
	 * @return  Filtered items
	 */
	public static String[] filterByWordCount(String[] sentences, Integer amountWords, Integer tolerance) {
		Integer curDiff;
		Integer leastDiff    = tolerance + 1;
		String ignorePattern = ".*[7|8].*";   // ignore interjection and place sentences

			// Find items with closest amount of words
		for( String sentence : sentences ) {
			if( !sentence.matches(ignorePattern) ) {
				curDiff     = Math.abs(amountWords - getWordCount(sentence));
				if( curDiff < leastDiff ) {
					leastDiff   = curDiff;
				}
			}
		}

			// Filter to items with closest word count, if within tolerance
		if( leastDiff <= tolerance ) {
			List<String> filtered  = new LinkedList<String>();
			for( String sentence : sentences ) {
				if( !sentence.matches(ignorePattern) ) {
					curDiff  = Math.abs(amountWords - getWordCount(sentence));
					if( curDiff.equals(leastDiff) ) {
						filtered.add(sentence);
					}
				}
			}

			return filtered.toArray( new String[filtered.size()] );
		}

		return sentences;
	}

}
