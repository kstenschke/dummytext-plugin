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

public class DictionarySciFi extends Dictionary {

	/**
	 * Constructor
	 */
	public DictionarySciFi() {

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

		sentence = sentence.replaceAll("galaxys ",   "galaxies ");
		sentence = sentence.replaceAll(" flys ",     " flies ");
		sentence = sentence.replaceAll(" gos ",      " goes ");
		sentence = sentence.replaceAll("controled",  "controlled");
		sentence = sentence.replaceAll("paralysiss", "paralyses");
		sentence = sentence.replaceAll("kalesss",    "forefathers");

		String[] unincreasables   = { "species", "people"};
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

		String[] structures = {
			"1s 4 with 2!", "1s 4 on 2 at 8!", "1s 4 with 2 at the 5 9!",
			"where is the 5 1?", "all 1s 3 5, 5 1s.", "never 3 a 1.",
			"1 of a 5 2, 3 the 2!", "1s are the 1s of the 5 2.",
			"the 1 4s 2 like a 5 1.", "1s 4 from 2s like 5 1s.",
			"when the 1 4s for 8, all 1s 3 5, 5 1s.",
			"this 2 has only been 3ed by a 5 1.",
			"2 at the 9 that is when 5 1s 4.",
			"i 3 this 2, it's called 5 2.",
			"7.", "7, 2!", "7, 5 2!",
			"5, 5 1s 6 3 a 5, 5 1.",
			"the 5 1 6 3s the 1.",
			"5 2s lead to the 2.",
			"why does the 1 4?",
			"it is a 5 2, sir.",
			"the 2 is a 5 1.",
			"5 1s, to the 9.",
			"4 6 like a 5 1.",
			"the 1 is 6 5.",
			"2, 2, and 2.",
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"admiral", "alien", "astronaut", "captain", "collective", "cosmonaut", "creature", "crew", "crewmate", "dosi",
			"emitter", "ferengi", "girl", "green people", "hur\'q", "kahless", "klingon", "lieutenant commander", "machine",
			"mermaid", "moon", "nanomachine", "parasite", "particle", "particle", "pathway", "planet", "processor", "proton",
			"queen", "sensor", "ship", "ship", "sonic shower", "space suit", "space", "spacecraft", "species", "star",
			"star", "starship", "sun", "teleporter", "transformator", "transporter", "planet",
			"tribble", "vogon", "phenomenan"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "advice", "alarm", "alignment", "anomaly", "assimilation", "attitude", "beauty",
			"collision course", "coordinates", "core", "courage", "death", "definition", "devastation", "ellipse",
			"energy", "faith", "flight", "friendship", "future", "galaxy", "history", "honor", "hypnosis",
			"ionic cannon", "life", "love", "mankind", "metamorphosis", "mind", "mineral", "modification", "moon",
			"mystery", "nuclear flux", "paralysis", "pattern", "peace", "plasma", "powerdrain", "pressure", "procedure",
			"resistance", "rumour", "sensor", "shield", "sonic shower", "space suit", "starlight travel", "stigma",
			"tragedy", "turbulence", "understanding", "vision", "voyage", "wind", "x-ray vision"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"accelerate", "acquire", "arrest", "assimilate", "attack", "avoid", "beam", "capture", "command",
			"consume", "contact", "control", "convert", "deceive", "deserve", "desire", "destroy", "discover",
			"disrupt", "dissolve", "eat", "empower", "evacuate", "examine", "experience", "feed", "fight", "gather",
			"grab", "handle", "imitate", "infiltrate", "influence", "invade", "love", "lower", "manifest", "observe",
			"offer", "open", "place", "promise", "pull", "question", "raise", "teleport", "transfer", "transform",
			"translate", "travel", "unite", "yearn"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"die", "experiment", "fly", "go", "harvest", "meet", "reproduce", "resist", "tremble", "view", "warp",
			"wobble", "yell"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"ancient", "apocalyptic", "bare", "biological", "boldly", "brave", "calm", "carnivorous", "chemical",
			"clear", "cloudy", "cold", "collective", "colorful", "crazy", "dead", "delighted", "devastated",
			"distant", "evasive", "extraterrestrial", "fantastic", "final", "futile", "galactic", "gravimetric",
			"greatly exaggerated", "harmless", "huge", "human", "intelligent", "interstellar", "modern",
			"most unusual", "mysterious", "neutral", "ordinary", "photonic", "post-apocalyptic", "proud", "quirky",
			"real", "remarkable", "seismic", "senior", "ship-wide", "small", "solid", "spheroid", "strange",
			"sub-light", "terrifying", "ugly", "united", "unrelated", "virtual", "vital"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"accelerative", "bravely", "cunningly", "finally", "impressively", "mechanically", "nosily", "oddly", "patiently",
			"pedantically", "proudly", "quickly", "revolutionary", "rudely", "surprisingly", "technically", "tightly",
			"unearthly", "virtually", "wildly", "wisely"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 7:  interjection
	 */
	private static String getInterjection() {
		String[] words ={
			"4, scotty", "engage", "6, indeed", "make it so", "red alert", "shields up", "all hands 4"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
	 */
	private static String getPlaceAbstract() {
		String[] words ={
			"astral city", "atlantis tower", "captain\'s quarters", "deep space", "earth", "hyperspace", "nowhere",
			"subspace", "starfleet headquarters"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 9: concrete place (usable with article: "entering the 9")
	 */
	private static String getPlaceConcrete() {
		String[] words ={
			"alpha quadrant", "bridge", "cabin", "center", "colony", "cosmos", "galaxy", "holodeck", "homeworld",
			"parallel universe", "planet", "ready room", "saucer section", "solar sphere", "space station", "universe",
			"wormhole"
		};

		return pickRandomString(words);
	}

}