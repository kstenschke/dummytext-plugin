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

public class DictionaryEsoteric extends Dictionary {

	/**
	 * Constructor
	 */
	public DictionaryEsoteric() {

	}

	/**
	 * @param   amountWords
	 * @param   amountSentences
	 * @return  Random line(s) of text, consisting from the given amount of words (1 line) or lines
	 */
	public String getRandomLine(Integer amountWords, Integer amountSentences) {
			// Esoteric wisdom creates mostly long sentences, so only one per line.
		String sentence   = getSentenceStructure(amountWords);

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

		sentence = sentence.replaceAll("a one ",        "one ");
		sentence = sentence.replaceAll("becomeing ",    "becoming ");
		sentence = sentence.replaceAll("desireing ",    "desiring ");
		sentence = sentence.replaceAll("dos ",          "does ");
		sentence = sentence.replaceAll("emergeing ",    "emerging ");
		sentence = sentence.replaceAll("es each other", "e each other");
		sentence = sentence.replaceAll("forgeting ",    "forgetting ");
		sentence = sentence.replaceAll("illuminate ",   "illuminating ");
		sentence = sentence.replaceAll("loves",         "love");
		sentence = sentence.replaceAll("manys",         "many");
		sentence = sentence.replaceAll("peaces",        "peace");
		sentence = sentence.replaceAll("riseing ",      "rising ");
		sentence = sentence.replaceAll("s meets",       "s meet");
		sentence = sentence.replaceAll("sexs",          "sex");
		sentence = sentence.replaceAll("siting ",       "sitting ");
		sentence = sentence.replaceAll("the peace",     "peace");

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

		String[] structures = {
			"the 1 3s.",
			"the 2 is a 5 1.",
			"all 5 1s 3s each other, only 5 1s have a 2.",
			"one must 3 the 1 in order to 3 the 1 of 5 2.",
			"Who can 3 the 2 and 2 of a 1 if he has the 5 2 of the 1.",
			"Never 3 the 1, for you can’t 3 it.",
			"Always 6 3 the 5 1.",
			"the 1 has 2, but not everyone 3s it.",
			"7.", "confucius says: 7.",
			"the 9 is full of 2.",
			"2, 2 and a 5 9.",
			"8 is not the 6 2 of the 1.",
			"not 8 or 8, 3 the 2.",
			"yes, there is 8, it 4s with 2.",
			"the 1 3s 2 which is not 5.",
			"the 2 of your 2s will 4 6 when you 3 that 2 is the 1.",
			"A 5 form of 2 is the 2."
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"believer", "body", "cow", "creator", "death", "doer", "ego", "explosion of the 2", "follower", "guru", "individual", "karma",
			"lama", "lord", "love", "lover", "man", "master", "monkey", "moon", "scholar", "self", "seeker", "sinner",
			"source", "sun", "teacher", "therapist", "thing", "visitor", "wind", "yogi", "aspect"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"acceptance", "advice", "afterlife", "anger", "affirmation", "ascension", "attitude", "attraction", "awareness",
			"beauty", "blessing", "bliss", "booda-hood", "career", "chaos", "control", "core", "courage", "definition",
			"density", "dimension", "emptiness", "energy", "enlightenment", "enlightenment", "everything", "extend", "faith",
			"fear", "freedom", "grace", "happiness", "heaven", "heaven", "history", "hypnosis", "issue", "life", "light",
			"living", "manifestation", "meditation", "milk", "mind", "mineral", "money", "moonlight", "pain", "peace",
			"purpose", "relativity", "samadhi", "satori", "sex", "silence", "solitude", "suffering", "surrender", "tantra",
			"totality", "trust", "truth", "uniqueness", "vision", "volume", "zen"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"absorb", "acquire", "avoid", "believe", "capture", "desire", "discover", "emerge", "experience", "feel",
			"forget", "gain", "handle", "hear", "illuminate", "know", "love", "meet", "praise", "realize", "receive",
			"remember", "respect", "study", "synthesise", "understand", "view", "visualize", "witness", "yearn"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"appear", "balance", "become", "contact", "convert", "die", "disappear", "disturb", "do", "empower", "exist",
			"experiment", "fly", "grow", "listen", "occur", "remain", "rise", "shine", "sit", "travel"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"abstruse", "alchemistic", "ancient", "apostolic", "atomic", "beloved", "bright", "brilliant", "calm",
			"celestine", "closest", "crystal", "enlightened", "fraternal", "further", "great", "hermetic", "holographic",
			"holy", "imminent", "important", "inner", "inward", "magical", "mediocre", "mysterious", "new", "new", "occult",
			"one", "outer", "parallel", "pictorial", "popular", "powerful", "private", "psychic", "remarkable", "secret",
			"secret", "separate", "shining", "simple", "sincere", "small", "special", "spiritual", "strange", "superior",
			"synthetic", "true", "ultimate", "united", "unveiled", "wonderful"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"agreeable", "authoratively", "balanced", "cosmically", "daily", "earthly", "oddly", "sincerely", "gently",
			"qabalistic", "silently", "solitary", "theosophical", "truly", "wisely", "wonderfully",
			"spiritually", "confidently", "esoterically", "essentially"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 7:  interjection
	 */
	private static String getInterjection() {
		String[] words ={
			"the 1 is like the 1", "2 and om",
			"stop to 3 and 4", "confused by 2s, the 1 is",
			"the 2 of 2 leads to 2", "2, 2, 2", "2, 2 and 4",
			"never 3 for others what you would not 3 for the 2 of it",
			"6 2 is to 3 the 2 of the 1\'s ignorance",
			"i 4 and i 4. i 4 and i 4. i 4 and i 4",
			"the 5 1 is 4ing, the 5 1 is 4ing",
			"in 8 all 1s 3 2", "in the 9 all 1s 3 2",
			"in 8 4s 6 2"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
	 */
	private static String getPlaceAbstract() {
		String[] words ={
			"5 places", "chaos", "heavens", "hell", "nirvana", "order", "paradise", "shangri-la", "upstairs",
			"wonderland", "zion", "over there"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 9: abstract place (usable with article: "entering the 9")
	 */
	private static String getPlaceConcrete() {
		String[] words ={
			"5 mind", "5 world", "afterworld", "body of 2", "great unknown", "heavens of 2", "home", "kingdom",
			"material world", "mind", "monastery", "next world", "pit", "pyramid", "realm of 2", "state of 2",
			"underworld"
		};

		return pickRandomString(words);
	}



}