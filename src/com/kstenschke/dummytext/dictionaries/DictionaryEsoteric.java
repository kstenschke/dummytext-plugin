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

	public DictionaryEsoteric() {

	}



	public String getRandomLine(Integer amountSentences) {
			// Esoteric wisdom creates mostly long sentences, so only one per line.
		String sentence   = getSentenceStructure();

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
		sentence = sentence.replaceAll("the peace",  "peace");
		sentence = sentence.replaceAll("peaces",     "peace");
		sentence = sentence.replaceAll("manys",      "many");
		sentence = sentence.replaceAll("sexs",       "sex");

		sentence = sentence.replaceAll("becomeing",  "becoming");
		sentence = sentence.replaceAll("desireing",  "desiring");
		sentence = sentence.replaceAll("emergeing",  "emerging");
		sentence = sentence.replaceAll("forgeting",  "forgetting");
		sentence = sentence.replaceAll("illuminate", "illuminating");
		sentence = sentence.replaceAll("riseing",    "rising");
		sentence = sentence.replaceAll("siting",     "sitting");

		return ucfirst(sentence);
	}



	/**
	 * @return  Random sentence structure with numbers as word type placeholders
	 */
	private static String getSentenceStructure() {
		String[] words = {
			"the 1 3s.",
			"2 is a 5 1.",
			"all 5 1s 3 each other, only 5 1s have a 2.",
			"one must 3 the 1 in order to 3 the 1 of 5 2.",
			"Who can 3 the 2 and 2 of a 1 if he has the 5 2 of the 1.",
			"Never 3 that the 1 can’t 3 your 2.",
			"the 1 has 2, but not everyone 3s it.",
			"7.", "confucius says: 7.",
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"believer", "cow", "ego", "explosion of the 2", "follower", "guru", "individual", "karma", "lama",
			"lover", "man", "master", "monkey", "moon", "one", "scholar", "seeker", "sinner", "source", "sun",
			"teacher", "therapist", "thing", "visitor", "yogi"
		};

		return pickRandomWord(words);
	}



	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"advice", "afterlife", "anger", "attitude", "attraction", "awareness", "beauty", "blessing", "bliss",
			"career", "core", "courage", "death", "definition", "enlightenment", "everything", "extend", "faith",
			"fear", "grace", "happiness", "heaven", "history", "hypnosis", "issue", "life", "life", "life",
			"living", "love", "love", "meditation", "milk", "mind", "mind", "mineral", "money", "moon", "pain",
			"peace", "religion", "samadhi", "satori", "sex", "silence", "solitude", "suffering",
			"surrender", "tantra", "totality", "trust", "uniqueness", "vision", "volume", "zen"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"absorb", "acquire", "avoid", "balance", "capture", "contact", "convert", "desire", "discover",
			"disturb", "empower", "experience", "feel", "handle", "illuminate", "know", "love", "meet", "raise",
			"receive", "synthesise", "understand", "view", "yearn"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"become", "desire", "die", "do", "emerge", "exist", "experiment", "fly", "forget", "hear",
			"hear", "remain", "remember", "rise", "see", "sit", "travel", "understand", "will"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"alchemistic", "ancient", "atomic", "calm", "celestine", "crystal", "enlightened", "further",
			"great", "hermetic", "holographic", "magical", "mysterious", "new", "parallel", "pictorial",
			"psychic", "remarkable", "secret", "separate", "small", "strange", "superior",
			"true", "united", "unveiled", "wonderful"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"agreeable", "authorative", "balanced", "cosmically", "earthly", "oddly",
			"qabalistic", "silently", "solitary", "theosophical", "wisely", "wonderfully"
		};

		return pickRandomWord(words);
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
			"the 5 1 is 4ing, the 5 1 is 4ing"
		};

		return pickRandomWord(words);
	}

	/**
	 * @return  Word of group 8:  place
	 */
	private static String getPlace() {
		String[] words ={
			"ashram"
		};

		return pickRandomWord(words);
	}

}