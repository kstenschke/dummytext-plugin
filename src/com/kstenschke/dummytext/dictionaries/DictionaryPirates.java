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

	public DictionaryPirates() {

	}



	public String getRandomLine(Integer amountSentences) {
		String sentence   = "";

		for(Integer i =0; i< amountSentences; i++) {
			sentence = sentence.concat( (i > 0 ? " " : "") + getSentenceStructure() );
		}

		while( sentence.matches(".*[0-9].*") ) {
			while(sentence.contains("1")) sentence = sentence.replaceFirst("1", getNounConcrete() );
			while(sentence.contains("2")) sentence = sentence.replaceFirst("2", getNounAbstract() );
			while(sentence.contains("3")) sentence = sentence.replaceFirst("3", getVerbTransitive() );
			while(sentence.contains("4")) sentence = sentence.replaceFirst("4", getVerbIntransitive() );
			while(sentence.contains("5")) sentence = sentence.replaceFirst("5", getAdjective() );
			while(sentence.contains("6")) sentence = sentence.replaceFirst("6", getAdverb() );
			while(sentence.contains("7")) sentence = sentence.replaceFirst("7", getInterjection() );
			while(sentence.contains("8")) sentence = sentence.replaceFirst("8", getPlace() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll("beautys ",   "beauties ");
		sentence = sentence.replaceAll("lasss ",     "lass ");
		sentence = sentence.replaceAll("pantss ",    "pants ");
		sentence = sentence.replaceAll("to the love","to love");

		return ucfirst(sentence);
	}



	/**
	 * @return  Random sentence structure with numbers as word type placeholders
	 */
	private static String getSentenceStructure() {
		String[] words = {
			"damn yer 1, feed the 1.",
			"1 of a 5 2, 3 the 2!",  "1s are the 1s of the 5 2.",
			"the 5 1 6 3s the 1.",
			"5, 5 1s 6 3 a 5, 5 1.",
			"5 2s lead to the 2.",
			"2 is a 5 1.",
			"7, 7.", "7, 2!", "7, 5 2!",
			"pieces o\' 2 are forever 5.",
			"1s 4 with 2!", "1s 4 on 2 at the 8!", "1s 4 with 2 at the 5 8!",
			"the 1 4s 2 like a 5 1.", "1s 4 from 2s like 5 1s.",
			"why does the 1 4?",
			"4 6 like a 5 1.",
			"2, 2, and 2.",
			"where is the 5 1?", "all 1s 3 5, 5 1s.", "never 3 a 1.",
			"when the 1 4s for 8, all 1s 3 5, 5 1s."
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"anchor", "bilge rat", "biscuit eater", "breeze", "bucaneer", "bung hole", "cannibal", "cannon",
			"captain", "cloud", "corsair", "dagger", "dubloon", "fish", "freebooter", "furner", "girl", "grog",
			"gull", "hornpipe", "jack", "jolly roger", "kraken", "lad", "lagoon", "lass", "lubber", "mainland",
			"malaria", "mast", "moon", "pants", "parrot", "pegleg", "pin", "pirate", "plunder", "reef", "sail",
			"sailor", "scabbard", "scallywag", "sea", "seashell", "shark", "ship", "shipmate", "shore", "skiff",
			"skull", "son", "sun", "tuna", "urchin", "wave", "wench", "whale", "wind", "yardarm"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "amnesty", "beauty", "booty", "courage", "death", "deck", "desolation", "endurance",
			"faith", "fortune", "grace", "horror", "hunger", "life", "love", "malaria", "passion", "plank",
			"power", "punishment", "strength", "treasure", "yellow fever"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"blow", "command", "crush", "desire", "fight", "fire", "haul", "lead", "loot", "love", "mark",
			"pull", "raid", "ransack", "rob", "scrape", "trade", "vandalize", "view"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"die", "drink", "endure", "fall", "grow", "laugh", "rise", "sail", "sing", "travel", "wave"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"addled", "big", "black", "clear", "cloudy", "coal-black", "cold", "dark", "dead", "golden", "jolly",
			"lively", "misty", "old", "proud", "rainy", "real", "rough", "scurvy", "shiny", "small", "smartly",
			"stormy", "sunny", "warm", "wet"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"awkwardly", "begrudgingly", "calmly", "cruelly", "darkly", "fast", "fiery", "greedily", "heavily",
			"impatiently", "loudly", "oppressively", "quietly", "quirky", "roughly", "swiftly", "unlawfully"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 7:  interjection
	 */
	private static String getInterjection() {
		String[] words ={
			"ah", "ahoy", "arg", "arrr", "avast", "aye", "belay", "c\'mon", "god", "golly gosh", "jolly", "lord",
			"o", "oh", "ooh", "yo-ho-ho", "wow", "yarr"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 8:  place
	 */
	private static String getPlace() {
		String[] words ={
			"cabo rojo", "captain\'s quarters", "east india", "fort charles", "isla de muerta", "jamaica",
			"la marsa beach", "pantano river", "port royal", "prison", "puerto rico", "singapore", "the cave",
			"the island", "tortuga"
		};

		return pickRandomWord(words);
	}

}