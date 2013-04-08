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

public class DictionarySciFi extends Dictionary {

	public DictionarySciFi() {

	}



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
			while(sentence.contains("8")) sentence = sentence.replaceFirst("8", getPlace() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll("galaxys ",   "galaxies ");
		sentence = sentence.replaceAll(" flys ",     " flies ");
		sentence = sentence.replaceAll(" gos ",      " goes ");
		sentence = sentence.replaceAll("peoples ",   "people ");
		sentence = sentence.replaceAll("speciess ",  "species ");

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
			"1 of a 5 2, 3 the 2!", "1s are the 1s of the 5 2.",
			"the 5 1 6 3s the 1.",
			"5, 5 1s 6 3 a 5, 5 1.",
			"5 2s lead to the 2.",
			"the 2 is a 5 1.",
			"7.", "7, 2!", "7, 5 2!",
			"1s 4 with 2!", "1s 4 on 2 at the 8!", "1s 4 with 2 at the 5 8!",
			"it is a 5 2, sir.",
			"the 1 4s 2 like a 5 1.", "1s 4 from 2s like 5 1s.",
			"why does the 1 4?",
			"4 6 like a 5 1.",
			"2, 2, and 2.",
			"where is the 5 1?", "all 1s 3 5, 5 1s.", "never 3 a 1.",
			"when the 1 4s for 8, all 1s 3 5, 5 1s."
		};

		return pickRandomString(structures, amountWords);
	}

	/**
	 * @return  Word of group 1:  concrete noun
	 */
	private static String getNounConcrete() {
		String[] words = {
			"admiral", "alien", "astronaut", "captain", "collective", "cosmonaut", "crew", "crewmate", "dosi",
			"emitter", "ferengi", "girl", "green people", "hur\'q", "kahless", "klingon", "lieutenant commander",
			"machine", "moon", "nanomachine", "parasite", "particle", "particle", "pathway", "planet", "processor",
			"queen", "sensor", "ship", "ship", "sonic shower", "space suit", "space", "spacecraft", "species",
			"species", "star", "star", "starship", "sun", "teleporter", "transformator", "transporter", "tribble",
			"vogon"
		};

		return pickRandomString(words);
	}



	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"adventure", "advice", "alarm", "alignment", "assimilation", "attitude", "beauty", "collision course",
			"coordinates", "core", "courage", "death", "definition", "faith", "flight", "friendship", "future",
			"galaxy", "history", "honor", "hypnosis", "ionic cannon", "life", "love", "mankind", "metamorphosis",
			"mind", "mineral", "moon", "nuclear flux", "paralysis", "pattern", "planet", "plasma", "pressure",
			"resistance", "rumour", "sensor", "shields", "sonic shower", "space suit", "starlight travel",
			"voyage", "wind", "x-ray vision"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"acquire", "arrest", "attack", "avoid", "capture", "command", "consume", "contact", "control",
			"convert", "deceive", "deserve", "desire", "destroy", "discover", "empower", "examine", "experience",
			"fight", "grab", "handle", "imitate", "influence", "invade", "love", "lower", "manifest", "offer",
			"open", "place", "promise", "pull", "question", "raise", "teleport", "translate", "view", "yearn"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"accelerate", "assimilate", "beam", "die", "dissolve", "eat", "experiment", "feed", "fly", "gather",
			"go", "harvest", "infiltrate", "observe", "reproduce", "resist", "transform", "travel", "tremble",
			"unite", "warp", "wobble", "yell"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"ancient", "apocalyptic", "bare", "biological", "boldly", "brave", "calm", "carnivorous", "chemical",
			"clear", "cloudy", "cold", "collective", "crazy", "dead", "delighted", "distant", "extraterrestrial",
			"final", "futile", "galactic", "greatly exaggerated", "huge", "human", "most unusual", "neutral",
			"photonic", "proud", "real", "seismic", "small", "solid", "spheroid", "strange", "ugly", "united",
			"virtual", "vital"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"accelerative", "bravely", "cunningly", "finally", "impressively", "mechanically", "nosily", "oddly",
			"patiently", "proudly", "quickly", "revolutionary", "rudely", "surprisingly", "technically",
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
	 * @return  Word of group 8:  place
	 */
	private static String getPlace() {
		String[] words ={
			"alpha quadrant", "astral city", "atlantis tower", "bridge", "cabin", "captain\'s quarters", "colony",
			"cosmos", "deep space", "earth", "galaxy", "holodeck", "homeworld", "hyperspace", "parallel universe",
			"planet", "ready room", "saucer section", "solar sphere", "space station", "starfleet headquarters",
			"universe", "wormhole"
		};

		return pickRandomString(words);
	}

}