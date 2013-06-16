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

import com.kstenschke.dummytext.helpers.InflectionHelper;
import com.kstenschke.dummytext.helpers.TextualHelper;

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
			while(sentence.contains("7")) sentence = TextualHelper.replaceIfNew(sentence, "7", getInterjection() );

			while(sentence.contains("PLURAL1")) sentence = TextualHelper.replaceIfNew(sentence, "PLURAL1", InflectionHelper.plural(getNounConcrete()) );
			while(sentence.contains("PLURAL2")) sentence = TextualHelper.replaceIfNew(sentence, "PLURAL2", InflectionHelper.plural(getNounAbstract()) );
			while(sentence.contains("GERUND3")) sentence = TextualHelper.replaceIfNew(sentence, "GERUND3", InflectionHelper.gerund(getVerbTransitive()) );
			while(sentence.contains("GERUND4")) sentence = TextualHelper.replaceIfNew(sentence, "GERUND4", InflectionHelper.gerund(getVerbIntransitive()) );
			while(sentence.contains("PSIMPLE3")) sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE3", InflectionHelper.presentSimple(getVerbTransitive()) );
			while(sentence.contains("PSIMPLE4")) sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE4", InflectionHelper.presentSimple(getVerbIntransitive()) );
			while(sentence.contains("PASTTENSE3")) sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE3", InflectionHelper.pastTense(getVerbTransitive()) );
			while(sentence.contains("PASTTENSE4")) sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE4", InflectionHelper.pastTense(getVerbIntransitive()) );

			while(sentence.contains("1")) sentence = TextualHelper.replaceIfNew(sentence, "1", getNounConcrete());
			while(sentence.contains("2")) sentence = TextualHelper.replaceIfNew(sentence, "2", getNounAbstract() );
			while(sentence.contains("3")) sentence = TextualHelper.replaceIfNew(sentence, "3", getVerbTransitive() );
			while(sentence.contains("4")) sentence = TextualHelper.replaceIfNew(sentence, "4", getVerbIntransitive() );
			while(sentence.contains("5")) sentence = TextualHelper.replaceIfNew(sentence, "5", getAdjective() );
			while(sentence.contains("6")) sentence = TextualHelper.replaceIfNew(sentence, "6", getAdverb() );
			while(sentence.contains("8")) sentence = TextualHelper.replaceIfNew(sentence, "8", getPlaceAbstract() );
			while(sentence.contains("9")) sentence = TextualHelper.replaceIfNew(sentence, "9", getPlaceConcrete() );
		}

		sentence = InflectionHelper.fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll(" flys ",        " flies ");
		sentence = sentence.replaceAll(" gos ",         " goes ");
		sentence = sentence.replaceAll("imitateed",	"imitated");

		String[] unincreasables   = { "species", "people"};
		sentence = InflectionHelper.depluralize(sentence, unincreasables);

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
				  "All those PLURAL2 will be lost in PLURAL2 like PLURAL2 in PLURAL2",
				  "When the 1 PSIMPLE4 for 8, all PLURAL1 3 5, 5 PLURAL1.",
				  "2 at the 9 was the 2 of 2, PASTTENSE3 to a 5 1.",
				  "This 2 has only been PASTTENSE3 by a 5 1.",
				  "PLURAL1 4 from PLURAL2 like 5 PLURAL1.",
				  "The 1 is more 1 now than 1. 5 and 6 5.",
				  "2 at the 9 that is when 5 PLURAL1 4.",
				  "PLURAL1 are the PLURAL1 of the 5 2.",
				  "4 wihtout 2, and we won’t 3 a 1.",
				  "I 3 this 2, it's called 5 2.",
				  "The 1 PSIMPLE4 2 like a 5 1.",
				  "PLURAL1 4 with 2 at the 5 9!",
				  "All PLURAL1 3 5, 5 PLURAL1.",
				  "5, 5 PLURAL1 6 3 a 5, 5 1.",
				  "The 5 1 6 PSIMPLE3 the 1.",
				  "5 PLURAL2 lead to the 2.",
				  "5 PLURAL1, to the 9.",
				  "PLURAL1 4 on 2 at 8!",
				  "1 of a 5 2, 3 the 2!",
				  "Why does the 1 4?",
				  "Where is the 5 1?",
				  "It is a 5 2, sir.",
				  "PLURAL1 4 with 2!",
				  "The 2 is a 5 1.",
				  "4 6 like a 5 1.",
				  "The 1 is 6 5.",
				  "Never 3 a 1.",
				  "2, 2, and 2.",
				  "7, 5 2!",
				  "7, 2!",
				  "7.",
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"AE35 unit", "admiral", "alien", "astronaut", "c-beam", "captain", "collective", "cosmonaut", "creature",
			"crew", "crewmate", "dosi", "emitter", "ferengi", "girl", "green people", "hur\'q", "kahless", "klingon",
			"lieutenant commander", "machine", "mermaid", "moon", "nanomachine", "parasite", "particle", "particle",
			"pathway", "planet", "processor", "proton", "queen", "sensor", "ship", "ship", "sonic shower", "space suit",
			"space", "spacecraft", "space suit", "species", "star", "starship", "sun", "teleporter", "transformator", "transporter",
			"planet", "tribble", "vogon", "phenomenan"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "advice", "alarm", "alignment", "anomaly", "assimilation", "attitude", "beauty",
			"collision course", "coordinates", "core", "courage", "death", "definition", "devastation", "disconnection",
			"ellipse", "energy", "faith", "flight", "friendship", "future", "galaxy", "history", "honor", "hypnosis",
			"ionic cannon", "life", "love", "mankind", "metamorphosis", "mind", "mineral", "modification", "moon",
			"mystery", "nuclear flux", "paralysis", "pattern", "peace", "plasma", "powerdrain", "pressure", "procedure",
			"resistance", "rumour", "sensor", "shield", "sonic shower", "starlight travel", "stigma",
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
			"offer", "open", "outweigh", "place", "promise", "pull", "question", "raise", "teleport", "transfer",
			"transform", "translate", "travel", "unite", "yearn"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"die", "experiment", "fly", "go", "harvest", "malfunction", "meet", "reproduce", "resist", "tremble", "view",
			"walk", "warp", "wobble", "yell"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"ancient", "apocalyptic", "bare", "biological", "boldly", "brave", "calm", "carnivorous", "chemical",
			"clear", "cloudy", "cold", "collective", "colorful", "conscious", "crazy", "dead", "delighted", "devastated",
			"distant", "evasive", "evil", "extraterrestrial", "fantastic", "final", "futile", "galactic", "gravimetric",
			"greatly exaggerated", "harmless", "huge", "human", "intelligent", "interstellar", "lunar", "modern",
			"most unusual", "mysterious", "neutral", "ordinary", "photonic", "post-apocalyptic", "proud", "quirky",
			"real", "reliable", "remarkable", "seismic", "senior", "ship-wide", "small", "solid", "spheroid", "strange",
			"sub-light", "terrifying", "twisted", "ugly", "united", "unrelated", "virtual", "vital"
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
			"alpha quadrant", "bridge", "cabin", "center", "colony", "cosmos", "galaxy", "holodeck", "homeworld", "moon",
			"parallel universe", "planet", "port", "ready room", "saucer section", "solar sphere", "solar system",
			"space station", "universe", "wormhole"
		};

		return pickRandomString(words);
	}

}
