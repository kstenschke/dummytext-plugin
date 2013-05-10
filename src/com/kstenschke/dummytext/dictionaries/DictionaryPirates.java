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

public class DictionaryPirates extends Dictionary {

	/**
	 * Constructor
	 */
	public DictionaryPirates() {

	}

	/**
	 * @param   amountWords
	 * @param   amountSentences
	 * @return  One line of random text, consisting from the given amount of words and sentences
	 */
	public String getRandomLine(Integer amountWords, Integer amountSentences) {
		String sentence   = "";

		for(Integer i =0; i< amountSentences; i++) {
			sentence = sentence.concat( (i > 0 ? " " : "") + getSentenceStructure(amountWords) );
		}

		while( sentence.matches(".*[0-9].*") ) {
			while(sentence.contains("1")) sentence = sentence.replaceFirst("1", getNounConcrete() );
			while(sentence.contains("2")) sentence = sentence.replaceFirst("2", getNounAbstract() );
			while(sentence.contains("3")) sentence = sentence.replaceFirst("3", getVerbTransitive() );
			while(sentence.contains("4")) sentence = sentence.replaceFirst("4", getVerbIntransitive() );
			while(sentence.contains("5")) sentence = sentence.replaceFirst("5", getAdjective() );
			while(sentence.contains("6")) sentence = sentence.replaceFirst("6", getAdverb() );
			while(sentence.contains("7")) sentence = sentence.replaceFirst("7", getInterjection() );
			while(sentence.contains("8")) sentence = sentence.replaceFirst("8", getPlaceAbstract() );
			while(sentence.contains("9")) sentence = sentence.replaceFirst("9", getPlaceConcrete() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll("at the old east india",   "in east india");
		sentence = sentence.replaceAll("beautys ",                "beauties ");
		sentence = sentence.replaceAll("breaked",                 "broken");
		sentence = sentence.replaceAll("cockroachs",              "cockroaches");
		sentence = sentence.replaceAll("lasss ",                  "lass ");
		sentence = sentence.replaceAll("loveed",                  "loved");
		sentence = sentence.replaceAll("pantss ",                 "pants ");
		sentence = sentence.replaceAll("scrapeed",                "scraped");
		sentence = sentence.replaceAll("to the love",             "to love");
		sentence = sentence.replaceAll("vandalizeed",             "vandalized");

		return ucfirst(sentence);
	}

	/**
	 * @param   amountWords
	 * @return  Random sentence structure with numbers as word type placeholders
	 */
	private static String getSentenceStructure(Integer amountWords) {
		if( amountWords != null && amountWords == 1 ) {
			String[] structures  = {"1", "2", "8"};
			return pickRandomString(structures);
		}

		String[] structures  = {
			"damn yer 1, feed the 1.",
			"1 of a 5 2, 3 the 2!",  "1s are the 1s of the 5 2.",
			"the 5 1 6 3s the 1.",
			"5, 5 1s 6 3 a 5, 5 1.",
			"5 2s lead to the 2.",
			"2 is a 5 1.",
			"7, 7.", "7, 2!", "7, 5 2!", "7, ye 5 1- set sails for 2!",
			"pieces o\' 2 are forever 5.",
			"1s 4 with 2!", "1s 4 on 2 at 8!", "1s 4 with 2 at the 5 8!",
			"the 1 4s 2 like a 5 1.", "1s 4 from 2s like 5 1s.",
			"why does the 1 4?",
			"4 6 like a 5 1.",
			"2, 2, and 2.",
			"where is the 5 1?", "all 1s 3 5, 5 1s.", "never 3 a 1.",
			"the 1 3s with 2, 3 the 9 until it 4s.",
			"when the 1 4s for 8, all 1s 3 5, 5 1s.",
			"ho-ho-ho! 2 of 2.",
			"2 ho! 3 to be 3ed.",
			"7, 5 1. go to 8.", "7, 5 1. you won't 3 the 9."
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"anchor", "bilge rat", "biscuit eater", "breeze", "bucaneer", "bung hole", "cannibal", "cannon",
			"captain", "cloud", "cockroach", "corsair", "dagger", "dubloon", "fish", "freebooter", "furner", "girl",
			"gold", "grog", "gull", "hornpipe", "jack", "jolly roger", "kraken", "lad", "lagoon", "lass", "lubber",
			"mainland", "mast", "mate", "moon", "pants", "parrot", "pegleg", "pin", "pirate", "plank", "plunder", "reef",
			"sail", "sailor", "scabbard", "scallywag", "sea", "seashell", "shark", "ship", "shipmate", "shore", "skiff",
			"parrot", "rum", "skull", "son", "sun", "tuna", "wave", "wench", "whale", "wind", "yardarm"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "amnesty", "beauty", "booty", "courage", "death", "desolation", "endurance",
			"faith", "fortune", "grace", "greed", "horror", "hunger", "life", "love", "madness", "malaria",
			"passion", "pestilence", "power", "punishment", "strength", "treasure", "urchin", "yellow fever",
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"blow", "break", "burn", "command", "crush", "desire", "drink", "endure", "fight", "fire", "hail",
			"haul", "hoist", "lead", "loot", "love", "mark", "pull", "raid", "ransack", "rob", "sail", "scrape", "trade",
			"vandalize", "view"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"die", "fall", "grow", "hobble", "laugh", "rise", "scream", "sing", "stutter", "travel", "wave", "whine"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"addled", "big", "black", "clear", "cloudy", "coal-black", "cold", "dark", "dead", "finest", "golden",
			"gutless", "jolly", "lively", "misty", "old", "proud", "rainy", "real", "rough", "scurvy", "shiny", "small",
			"stormy", "sunny", "swashbuckling", "warm", "wet"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"awkwardly", "begrudgingly", "calmly", "cowardly", "cruelly", "darkly", "fast", "fiery", "greedily",
			"heavily", "impatiently", "loudly", "oppressively", "quietly", "quirky", "roughly", "smartly", "swiftly",
			"unlawfully"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 7:  interjection
	 */
	private static String getInterjection() {
		String[] words ={
			"ah", "ahoy", "arg", "arrr", "avast", "aye", "belay", "c\'mon", "god", "golly gosh", "jolly", "lord",
			"o", "oh", "ooh", "yo-ho-ho", "wow", "yarr"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
	 */
	private static String getPlaceAbstract() {
		String[] words ={
			"cabo rojo", "east india", "fort charles", "french polynesia", "haiti", "isla de muerta", "isla de sangria",
			"jamaica", "la marsa beach", "madagascar", "pantano river", "port degas", "port royal", "prison",
			"puerto rico", "rummage island", "singapore", "tortuga", "tubbataha reef"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 9: concrete place (usable with article: "entering the 9")
	 */
	private static String getPlaceConcrete() {
		String[] words ={
			"captain\'s quarters", "bikini atoll", "reef", "freighter", "lighthouse", "pacific ocean", "fortress",
			"cook islands", "seychelles"
		};

		return pickRandomString(words);
	}

}