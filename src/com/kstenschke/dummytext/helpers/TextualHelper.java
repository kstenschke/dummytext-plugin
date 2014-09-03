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
package com.kstenschke.dummytext.helpers;

import java.util.LinkedList;
import java.util.List;

public class TextualHelper {

	public static final int STYLE_NONE        = 0;
	public static final int STYLE_UPPERCASE   = 1;
	public static final int STYLE_LOWERCASE   = 2;
	public static final int STYLE_UCFIRST     = 3;

	/**
	 * @param   str
	 * @return
	 */
	public static int getCasing(String str) {
		if( isAllUppercase(str) ) {
			return STYLE_UPPERCASE;
		} else if(isAllLowercase(str)) {
			return STYLE_LOWERCASE;
		} else if(isUcFirst(str)) {
			return STYLE_UCFIRST;
		}

		return STYLE_NONE;
	}

	/**
	 * @param   str
	 * @param   casing
	 * @return  Given string updated to given casing (upper, lower, upper-first, none)
	 */
	public static String setCasing(String str, int casing) {
		if( casing == TextualHelper.STYLE_LOWERCASE) {
			str   = str.toLowerCase();
		} else if( casing == TextualHelper.STYLE_UPPERCASE) {
			str   = str.toUpperCase();
		} else if( casing == TextualHelper.STYLE_UCFIRST) {
			str   = TextualHelper.ucFirst(str);
		}

		return str;
	}

	/**
	 * @param   str  String to be analyzed
	 * @return  Integer  Amount of lines in given string
	 */
	public static int countLines(String str) {
		String[] lines = str.split("\r\n|\r|\n");
		return  lines.length;
	}

	/**
	 * @param   str      String to be checked
	 * @return  boolean  Are all characters in the given string lower case?
	 */
	public static boolean isAllUppercase(String str) {
		return str.equals(str.toUpperCase());
	}

	/**
	 * @param   str      String to be checked
	 * @return  boolean  Are all characters in the given string lower case?
	 */
	public static boolean isAllLowercase(String str) {
		return str.equals(str.toLowerCase());
	}

	/**
	 * @param   str   A string
	 * @return  The given string with a capital fist letter
	 */
	public static String ucFirst(String str) {
		if(str.length() < 1) {
			return str;
		}

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
	 * @return  Is the given string all white-space?
	 */
	public static boolean isWhiteSpace(String str) {
		return str.trim().length() == 0;
	}

    public static boolean isWhiteSpace(char c) {
		return isWhiteSpace(String.valueOf(c));
	}

	/**
	 * @param   str   String to be parsed
	 * @return  Is the given string not alphabetic (e.g. a punctuation)?
	 */
	public static boolean isAlphabeticLetter(String str) {
		return str.matches("[a-z|A-Z]");
	}

    public static boolean isAlphabeticLetter(char c) {
        return isAlphabeticLetter( String.valueOf(c) );
    }

    /**
     * @param   {String}  str
     * @return  Is a punctuation letter? (one of: ./ ,/ ;/ :/ ?/ !)
     */
    public static boolean isPunctuationLetter(String str) {
        return ".,;:?!¡¿".contains(str);
    }

    /**
     * @param   {String}  str
     * @return  Is a quotation letter? (one of: ‘/ “/ '/ ")
     */
    public static boolean isQuotationLetter(String str) {
        return "‘“'\"".contains(str);
    }
    public static boolean isQuotationLetter(char c) {
        return isQuotationLetter(String.valueOf(c));
    }

	/**
	 * @param   str   String to be parsed
	 * @return  Last character out of given string
	 */
	public static String getLastChar(String str) {
		return str == null || str.isEmpty() ? "" : str.substring(str.length() - 1);
	}

    /**
     * @param   str
     * @return  Lead character to be preserved, such as quotation letters
     */
    public static String getLeadingPreservation(String str) {
        if( str == null || str.isEmpty() ) {
            return null;
        }

        String leadChars = "";
        int offset      = 0;
        char curLetter  = str.charAt(offset);
        boolean done    = false;
        while(  offset < str.length() && !done
                && (isWhiteSpace(curLetter) || isQuotationLetter(curLetter) )
                && !isAlphabeticLetter(curLetter)
        ) {
            leadChars = leadChars + curLetter;
            if( isQuotationLetter(curLetter) ) {
                    // Preserve no more after first quotation letter
                done = true;
            }
            offset++;
            curLetter  = str.charAt(offset);
        }

        return leadChars.isEmpty() ? null : leadChars.trim();
    }

	/**
	 * @param   str   String to be parsed
	 * @return  The trailing punctuation mark character, or null if the string does not end with a punctuation
	 */
	public static String getTrailingPunctuationMark(String str) {
        if( str == null || str.isEmpty() ) {
            return null;
        }

        str = str.trim();
		str = getLastChar(str);

		return isPunctuationLetter(str) || isQuotationLetter(str) ? str: null;
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
		String leadingWhiteSpace   = TextualHelper.getLeadingWhiteSpace(str);
		str                        = str.trim();

		boolean endsAlphabetic  = TextualHelper.isAlphabeticLetter(TextualHelper.getLastChar(str));

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

		return leadingWhiteSpace + str;
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

	/**
	 * Extract leading white space characters from given string.
	 *
	 * @param   str   The string
	 */
	public static String getLeadingWhiteSpace(String str) {
		String whitespace = "";

		int strLen  = str.length();
		int i       =0;

		loop:   for(;i < strLen;) {
			char curChar = str.charAt(i);
			switch(curChar)
			{
				case ' ':
				case '\t':
					whitespace = whitespace + curChar;
					i++;
					break;
				default:
					break loop;
			}
		}

		return whitespace;
	}

	/**
	 * @param   sentence
	 * @param   original
	 * @param   replacement
	 * @return  Given sentence with word replaced by replacement, if the sentence did not contain the replacement afore
	 */
	public static String replaceIfNew(String sentence, String original, String replacement) {
		if( !sentence.contains(replacement) ) {
			sentence = sentence.replaceFirst(original, replacement);
		}

		return sentence;
	}
}