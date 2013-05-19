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
			while(sentence.contains("1")) sentence = replaceIfNew(sentence, "1", getNounConcrete() );
			while(sentence.contains("2")) sentence = replaceIfNew(sentence, "2", getNounAbstract() );
			while(sentence.contains("3")) sentence = replaceIfNew(sentence, "3", getVerbTransitive() );
			while(sentence.contains("4")) sentence = replaceIfNew(sentence, "4", getVerbIntransitive() );
			while(sentence.contains("5")) sentence = replaceIfNew(sentence, "5", getAdjective() );
			while(sentence.contains("6")) sentence = replaceIfNew(sentence, "6", getAdverb() );
			while(sentence.contains("7")) sentence = replaceIfNew(sentence, "7", getInterjection() );
			while(sentence.contains("8")) sentence = replaceIfNew(sentence, "8", getPlaceAbstract() );
			while(sentence.contains("9")) sentence = replaceIfNew(sentence, "9", getPlaceConcrete() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll(" a sea ",                 " the sea");
		sentence = sentence.replaceAll("at the old east india",   "in east india");
		sentence = sentence.replaceAll("beautys ",                "beauties ");
		sentence = sentence.replaceAll("breaked",                 "broken");
		sentence = sentence.replaceAll("cockroachs",              "cockroaches");
		sentence = sentence.replaceAll("hobbleing",               "waving");
		sentence = sentence.replaceAll("loveed",                  "loved");
		sentence = sentence.replaceAll("riseing",                 "rising");
		sentence = sentence.replaceAll("scrapeed",                "scraped");
		sentence = sentence.replaceAll("to the love",             "to love");
		sentence = sentence.replaceAll("vandalizeed",             "vandalized");
		sentence = sentence.replaceAll("waveing",                 "waving");
		sentence = sentence.replaceAll("whineing",                "whining");


		//"hobble", "laugh", "rise", "scream", "sing", "stutter", "travel", "wave", "whine"

		String[] unincreasables   = { "lass", "pants" };
		sentence = TextualHelper.depluralize(sentence, unincreasables);

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
			"1s 4 with 2!", "1s 4 on 2 at 8!", "1s 4 with 2 at the 5 8!",
			"7, 7.", "7, 2!", "7, 5 2!", "7, ye 5 1- set sails for 2!",
			"where is the 5 1?", "all 1s 3 5, 5 1s.", "never 3 a 1.",
			"1 of a 5 2, 3 the 2!",  "1s are the 1s of the 5 2.",
			"the 1 4s 2 like a 5 1.", "1s 4 from 2s like 5 1s.",
			"7, 5 1. go to 8.", "7, 5 1. you won't 3 the 9.",
			"7 there's nothing like the 5 2 4ing on the 1.",
			"when the 1 4s for 8, all 1s 3 5, 5 1s.",
			"the 1 3s with 2, 3 the 9 until it 4s.",
			"pieces o\' 2 are forever 5.",
			"damn yer 1, feed the 1.",
			"how 5. You 3 like a 1.",
			"5, 5 1s 6 3 a 5, 5 1.",
			"7, yer not 3ing me without a 2!",
			"the 5 1 6 3s the 1.",
			"5 2s lead to the 2.",
			"ho-ho-ho! 2 of 2.",
			"2 ho! 3 to be 3ed.",
			"why does the 1 4?",
			"4 6 like a 5 1.",
			"2, 2, and 2.",
			"2 is a 5 1.",
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
			"parrot", "rum", "skull", "son", "sun", "tuna", "wave", "wench", "whale", "wind", "woodchuck", "yardarm"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "amnesty", "beauty", "booty", "courage", "death", "desolation", "endurance", "faith", "fight",
			"fortune", "grace", "greed", "halitosis", "horror", "hunger", "life", "love", "madness", "malaria", "passion",
			"pestilence", "power", "punishment", "riddle", "strength", "treasure", "urchin", "yellow fever"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"blow", "break", "burn", "command", "crush", "desire", "drink", "endure", "fear", "fight", "fire", "hail",
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
			"addled", "big", "black", "clear", "cloudy", "coal-black", "cold", "dark", "dead", "evil", "fine", "golden",
			"gutless", "jolly", "lively", "mighty", "misty", "old", "proud", "rainy", "real", "rough", "scurvy", "shiny", "small",
			"stormy", "sunny", "swashbuckling", "undead", "warm", "weird", "wet"
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
			"ah", "ahoy", "arg", "arrr", "avast", "aw", "aww", "aye", "belay", "c\'mon", "god", "golly gosh", "jolly",
			"lord", "o", "oh", "ooh", "yo-ho-ho", "well", "wow", "yarr", "yuck"
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