package com.kstenschke.dummytext.dictionaries;

public class Dictionary {

	public String getRandomLine(Integer amountSentences) {
		return "";
	}

	public String getRandomLine() {
		Integer amountSentences  = getRandomNumber(4) > 3 ? 2 : 1;

		return getRandomLine(amountSentences);
	}




	protected String fixIndefiniteArticles(String sentence) {
		sentence = sentence.replaceAll("a a", "an a");
		sentence = sentence.replaceAll("a e", "an e");
		sentence = sentence.replaceAll("a i", "an i");
		sentence = sentence.replaceAll("a o", "an o");

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

	protected static Integer getRandomNumber(Integer max) {
		return getRandomNumber(0, max);
	}



	/**
	 * @param   words    Array of strings
	 * @return  One random item out of the given array
	 */
	protected static String pickRandomWord(String[] words)  {
		return words[ getRandomNumber( words.length -1 ) ];
	}


	/**
	 * @param   str
	 * @return  Given string with first character made uppercase
	 */
	protected static String ucfirst(String str){
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
