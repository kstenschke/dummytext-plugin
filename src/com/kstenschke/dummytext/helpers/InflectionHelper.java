package com.kstenschke.dummytext.helpers;

/**
 * Helper methods for creating derived word forms
 */
public class InflectionHelper {

	/**
	 * Create gerund form of given verb
	 * ex; hear       => hearing
	 *     trade      => trading     (trailing e is detected + removed)
	 *     trade up   => mashing up  (trailing 2nd word is detected and the gerund is made on the first word)
	 *
	 * @param   verb
	 * @return
	 */
	public static String gerund(String verb) {
		verb = verb.trim();
		if(verb.contains(" ")) {
			String[] words = verb.split(" ");
			return gerund(words[0]) + " " + words[1];
		}

		if(  verb.endsWith("at")
		  || verb.endsWith("et")
		  || verb.endsWith("it")
		  || verb.endsWith("ot")
		  || verb.endsWith("ut")) {
			verb = verb + "ting";
		} else if(verb.endsWith("e")) {
			verb = verb.substring(0, verb.length()-1) + "ing";
		} else {
			verb = verb + "ing";
		}

		return verb;
	}

	/**
	 * Create past tense form of given verb
	 * ex; bake       => baked
	 *     travel     => travelled
	 *     picknic    => picknicked
	 *
	 * @param   verb
	 * @return
	 */
	public static String pastTense(String verb) {
		verb = verb.trim();

		if(verb.contains(" ")) {
			String[] words = verb.split(" ");
			return pastTense(words[0]) + " " + words[2];
		}

		if (verb.equals("eaked")) {
			verb = "broken";
		} else if (verb.equals("eated")) {
			verb = "eaten";
		} else if (verb.endsWith("e")) {
			verb = verb + "d";
		} else if (verb.endsWith("al")
				  || verb.endsWith("el")
				  || verb.endsWith("il")
				  || verb.endsWith("ol")
				  || verb.endsWith("ul")) {
			verb = verb + "led";
		} else if (verb.endsWith("ap")
				  || verb.endsWith("ep")
				  || verb.endsWith("ip")
				  || verb.endsWith("op")
				  || verb.endsWith("up")) {
			verb = verb + "ped";
		} else if (verb.endsWith("ic")) {
			verb = verb + "ked";
		} else if (verb.endsWith("y")
				  && !verb.endsWith("ay")
				  && !verb.endsWith("ey")
				  && !verb.endsWith("oy")
				  && !verb.endsWith("uy")) {
			verb = verb.substring(0, verb.length()-1) + "ied";
		} else {
			verb = verb + "ed";
		}

		return verb;
	}

	/**
	 * @param   word
	 * @return  plural of given word
	 */
	public static String plural(String word) {
		if(word.endsWith("y")
			&& !word.endsWith("ay")
			&& !word.endsWith("ey")
			&& !word.endsWith("oy")
		   && !word.endsWith("uy")) {
			word = word.subSequence(0, word.length()-1 ).toString() + "ies";
		} else if(word.endsWith("ss")) {
			word = word + "es";
		} else if(! word.endsWith("s")) {
			word = word + "s";
		}

		return word;
	}

	/**
	 * @param   verb
	 * @return  present simple form of given verb
	 */
	public static String presentSimple(String verb) {
		if(verb.endsWith("ch")) {
			verb = verb + "es";
		} else if(verb.endsWith("y")) {
			verb = verb.subSequence(0, verb.length()-1 ).toString() + "ies";
		} else if(! verb.endsWith("s")) {
			verb = verb + "s";
		}

		if( verb.equals("dos")) {
			verb = "does";
		}

		return verb;
	}

	/**
	 * Remove additional trailing "s" from given words
	 *
	 * @param   sentence
	 * @param   unincreasables
	 * @return
	 */
	public static String depluralize(String sentence, String[] unincreasables) {
		for(String unincreasable: unincreasables) {
			sentence = depluralize(sentence, unincreasable);
		}

		return sentence;
	}

	private static String depluralize(String sentence, String unincreasable) {
		return sentence.replaceAll(unincreasable + "s", unincreasable);
	}

	/**
	 * @param   sentence
	 * @return  The sentence with the indefinite article "a" changed into "an", when preceding words starting with a vowel
	 */
	public static String fixIndefiniteArticles(String sentence) {
		sentence = sentence.replaceAll(" a a",    " an a");
		sentence = sentence.replaceAll(" a e",    " an e");
		sentence = sentence.replaceAll(" a i",    " an i");
		sentence = sentence.replaceAll(" a o",    " an o");
		sentence = sentence.replaceAll(" a ul",   " an ul");

		String sentenceLower = sentence.toLowerCase();
		if(   sentenceLower.startsWith("a a")
			|| sentenceLower.startsWith("a e")
			|| sentenceLower.startsWith("a o")
			|| sentenceLower.startsWith("a i")
			|| sentenceLower.startsWith("a u")
		) {
			sentence = "An " + sentence.substring(2);
		}

		return sentence;
	}

}
