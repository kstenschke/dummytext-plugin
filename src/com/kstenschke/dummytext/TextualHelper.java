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
		return str.substring(str.length() - 1);
	}

	/**
	 * @param   str   String to be parsed
	 * @return  The trailing punctuation mark character, or null if the string does not end with a punctuation
	 */
	public static String getTrailingPunctuationMark(String str) {
		str   = getLastChar(str);

		return isAlphabetic(str) ? null : str;
	}

}
