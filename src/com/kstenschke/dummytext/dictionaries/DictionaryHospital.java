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

public class DictionaryHospital extends Dictionary {

	/**
	 * Constructor
	 */
	public DictionaryHospital() {

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
			while(sentence.contains("8")) sentence = TextualHelper.replaceIfNew(sentence, "8", getPlace() );
			while(sentence.contains("9")) sentence = TextualHelper.replaceIfNew(sentence, "9", getBodypart() );
		}

		sentence = fixIndefiniteArticles(sentence);

		sentence = sentence.replaceAll(" go at the ",   " go to the ");
		sentence = sentence.replaceAll(" gos ",         " goes ");
		sentence = sentence.replaceAll("anuss",         "anuses");
		sentence = sentence.replaceAll("bodys",         "bodies");
		sentence = sentence.replaceAll("familys ",      "families ");
		sentence = sentence.replaceAll("itchs",         "itches");
		sentence = sentence.replaceAll("peniss",        "penises");
		sentence = sentence.replaceAll("talk the",      "talk to the");
		sentence = sentence.replaceAll("undergos",      "undergoes");
		sentence = sentence.replaceAll("wait from",     "wait for");

		String[] unincreasables   = { "bronchitis", "diagnosis", "illness", "kidneys", "lungs"};
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
				  "when the 1 PSIMPLE4 for 8, all PLURAL1 3 5, 5 PLURAL1.",
				  "don't 3 the 1 or you might 3 2.",
				  "to 3 the 5 1 of 9, 6 3 the 1.",
				  "to 3 the 1 of 5 9, 3 the 1.",
				  "PLURAL1 are the PLURAL1 of the 5 2.",
				  "the 1 PSIMPLE4 by 2 like a 5 1.",
				  "PLURAL1 4 from PLURAL2 like 5 PLURAL1.",
				  "PLURAL1 4 with 2 at the 5 8!",
				  "5, 5 PLURAL1 6 3 a 5, 5 1.",
				  "PLURAL1 of a 5 2, 3 the 2!",
				  "the 5 1 6 PLURAL3 the 1.",
				  "5 PLURAL2 lead to the 2.",
				  "PLURAL1 4 on 2 at the 8!",
				  "why does the 1 4?",
				  "all PLURAL1 3 5, 5 PLURAL1.",
				  "where is the 5 1?",
				  "it is a 5 2, sir.",
				  "4 6 like a 5 1.",
				  "PLURAL1 4 with 2!",
				  "2, 2, and 2.",
				  "never 3 a 1.",
				  "2 is a 5 1.",
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
			"abdomen", "anus", "band-aid", "body", "daughter", "defibrillator", "dietican", "doctor",
			"doctor\'s assistant", "dr", "dr", "family", "father", "heart", "implant", "kidney", "lungs",
			"medical lab technologist", "medical student", "neighbour", "nurse", "patient", "pharmacist",
			"physician", "professor", "scalpel", "scientist", "sensor", "silicone", "son", "surgeon", "therapist",
			"ulcer"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 2:  abstract noun
	 */
	private static String getNounAbstract() {
		String[] words = {
			"advice", "aggressive euthanasia", "akathisia", "anaphylactic shock", "anemia", "attitude", "attraction",
			"beauty", "blood plasma", "blood", "botanophobia", "bronchitis", "cancer", "cat scratch disease", "code zero",
			"collywobble", "coryza", "courage", "death", "desease", "desinfection", "diabetes", "diagnosis", "disorder",
			"ebola", "faith", "fatigue", "fever", "heart attack", "hope", "illness", "infection", "life", "love",
			"malaise", "nutrition", "operation", "oxygen", "parrot fever", "plague", "pneumonia", "prodrome", "q fever",
			"reaction", "treatment", "ultrasound", "wound", "x-ray"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 3:  transitive verb
	 */
	private static String getVerbTransitive() {
		String[] words = {
			"admire", "assimilate", "attest", "avoid", "bathe", "breathe", "capture", "consume", "contact", "control", "convert",
			"clamp", "cut", "damage", "depend", "desire", "discover", "educate", "examine", "experience",
			"foster", "grant", "hammer", "handle", "help", "infect", "influence", "inject", "inquire", "kill", "leave", "love",
			"manifest", "observe", "open", "press", "promise", "question", "receive", "spread", "spread", "steal", "swell",
			"take", "transplant", "treat", "trip", "undergo", "undress", "view"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 4:  intransitive verb
	 */
	private static String getVerbIntransitive() {
		String[] words = {
			"collapse", "cry", "die", "fall", "go", "itch", "meet", "suffer", "shiver", "stand", "wait"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 5:  adjective
	 */
	private static String getAdjective() {
		String[] words = {
			"abdominal", "amorous", "anemic", "attracted", "bacterial", "bad", "beautiful", "bloody", "brave", "caring", "chemical",
			"clear", "dead", "disastrous", "distant", "ectopic", "erotic", "fat", "fatal", "final", "frightened",
			"gastric", "genetic", "genital", "happy", "human", "iatrogenic", "ill", "infected", "loving", "medical",
			"neutral", "paranoid", "remarkable", "romantic", "seductive", "sick", "skilled", "smitten", "strange",
			"traumatic", "ugly", "vital", "morbid", "unsound", "cool-headed", "tender", "affectionate", "fond"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 6:  adverb
	 */
	private static String getAdverb() {
		String[] words = {
			"badly", "dizzily", "ethically", "finally", "fondly", "gingerly", "girlyshly", "hysterically", "impressively",
			"mechanically", "methodically", "nervously", "patiently", "physically", "quickly", "routinely", "rudely",
			"shyly", "surprisingly", "wisely", "sickly", "tenderly", "mentally", "critically", "seriously"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 7:  interjection
	 */
	private static String getInterjection() {
		String[] words ={
			"we had to open his 9 for a 5 2", "we must 6 3 the 5 patient",
			"1, inform the 1 that 2 is 5", "when 1s 4 from the 8, 4, 4 and 4"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 8:  place
	 */
	private static String getPlace() {
		String[] words ={
			"adminisphere", "clinic", "consulting room", "delivery room", "dispensary", "emergency room",
			"hospital", "intensive care", "nursery", "pharmacy", "ward"
		};

		return pickRandomString(words);
	}

	/**
	 * @return  Word of group 9:  bodypart
	 */
	private static String getBodypart() {
		String[] words ={
			"bladder", "bone", "blood", "breast", "cancer", "chest", "heart", "kidneys", "liver", "lungs", "penis",
			"stomach", "skin", "tooth", "brain"
		};

		return pickRandomString(words);
	}



}