/*
 * Copyright Kay Stenschke
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
        String sentence = "";
        for (Integer i =0; i< amountSentences; i++) {
            sentence = sentence.concat((i > 0 ? " " : "") + getSentenceStructure(amountWords));
        }

        while (sentence.matches(".*[0-9].*") ) {
            while (sentence.contains("7")) sentence = TextualHelper.replaceIfNew(sentence, "7", getInterjection());

            while (sentence.contains("PLURAL1")) sentence = TextualHelper.replaceIfNew(sentence, "PLURAL1", InflectionHelper.plural(getNounConcrete()));
            while (sentence.contains("PLURAL2")) sentence = TextualHelper.replaceIfNew(sentence, "PLURAL2", InflectionHelper.plural(getNounAbstract()));
            while (sentence.contains("GERUND3")) sentence = TextualHelper.replaceIfNew(sentence, "GERUND3", InflectionHelper.gerund(getVerbTransitive()));
            while (sentence.contains("GERUND4")) sentence = TextualHelper.replaceIfNew(sentence, "GERUND4", InflectionHelper.gerund(getVerbIntransitive()));
            while (sentence.contains("PSIMPLE3")) sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE3", InflectionHelper.presentSimple(getVerbTransitive()));
            while (sentence.contains("PSIMPLE4")) sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE4", InflectionHelper.presentSimple(getVerbIntransitive()));
            while (sentence.contains("PASTTENSE3")) sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE3", InflectionHelper.pastTense(getVerbTransitive()));
            while (sentence.contains("PASTTENSE4")) sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE4", InflectionHelper.pastTense(getVerbIntransitive()));

            while (sentence.contains("1")) sentence = TextualHelper.replaceIfNew(sentence, "1", getNounConcrete());
            while (sentence.contains("2")) sentence = TextualHelper.replaceIfNew(sentence, "2", getNounAbstract());
            while (sentence.contains("3")) sentence = TextualHelper.replaceIfNew(sentence, "3", getVerbTransitive());
            while (sentence.contains("4")) sentence = TextualHelper.replaceIfNew(sentence, "4", getVerbIntransitive());
            while (sentence.contains("5")) sentence = TextualHelper.replaceIfNew(sentence, "5", getAdjective());
            while (sentence.contains("6")) sentence = TextualHelper.replaceIfNew(sentence, "6", getAdverb());
            while (sentence.contains("8")) sentence = TextualHelper.replaceIfNew(sentence, "8", getPlaceAbstract());
            while (sentence.contains("9")) sentence = TextualHelper.replaceIfNew(sentence, "9", getPlaceConcrete());
        }

        sentence = InflectionHelper.fixIndefiniteArticles(sentence);

        sentence = sentence.replaceAll(" a sea ",                 " the sea");
        sentence = sentence.replaceAll("at the old east india",   "in east india");
        sentence = sentence.replaceAll("to the love",             "to love");
        sentence = sentence.replaceAll(" crushs ",                " crushes ");

        String[] unincreasables   = { "doubloons", "lass", "pants" };
        sentence = InflectionHelper.depluralize(sentence, unincreasables);

        return ucFirst(sentence);
    }

    /**
     * @param   amountWords
     * @return  Random sentence structure with numbers as word type placeholders
     */
    private static String getSentenceStructure(Integer amountWords) {
        if (null != amountWords && 1 == amountWords) {
            String[] structures  = {"1", "2", "8"};
            return pickRandomString(structures);
        }

        String[] structures  = {
                "When the 1 PSIMPLE4 for 8, all PLURAL1 3 5, 5 PLURAL1.",
                "The 1 PSIMPLE3 with 2, 3 the 9 until it PSIMPLE4.",
                "The 1 PSIMPLE3 with 2, 3 the 9 before it PSIMPLE4.",
                "7 there's nothing like the 5 2 GERUND4 on the 1.",
                "7, 5 1. go to 8.", "7, 5 1. you won't 3 the 9.",
                "PLURAL1 4 from PLURAL2 like 5 PLURAL1.",
                "PLURAL1 are the PLURAL1 of the 5 2.",
                "7, yer not GERUND3 me without a 2!",
                "The 1 PSIMPLE3 with 2, 3 the 9.",
                "The 1 PSIMPLE4 2 like a 5 1.",
                "PLURAL1 4 with 2 at the 5 8!",
                "7! Pieces o\' 2 are forever 5.",
                "All PLURAL1 3 5, 5 PLURAL1.",
                "7, ye 5 1- set sails for 2!",
                "5, 5 PLURAL1 6 3 a 5, 5 1.",
                "2 ho! 3 to be PASTTENSE3.",
                "The 5 1 6 PSIMPLE3 the 1.",
                "5 PLURAL2 lead to the 2.",
                "Damn yer 1, feed the 1.",
                "How 5. You 3 like a 1.",
                "1 of a 5 2, 3 the 2!",
                "PLURAL1 4 on 2 at 8!",
                "PLURAL1 4 with 2!",
                "Ho-ho-ho! 2 of 2.",
                "Why does the 1 4?",
                "Where is the 5 1?",
                "7, 3 me 1, ye 5 1!",
                "7, never 3 a 1.",
                "3 me 1, ye 5 1!",
                "4 6 like a 5 1.",
                "2, 2, and 2.",
                "Never 3 a 1.",
                "2 is a 5 1.",
                "7, 7.",
                "7, 2!",
                "7, 5 2!",
        };

        return pickRandomString(structures, amountWords);
    }

    /**
     * @return  Word of group 1:  concrete noun
     */
    private static String getNounConcrete() {
        String[] words = {
            "ale", "anchor", "bilge rat", "biscuit eater", "breeze", "bucaneer", "bung hole", "cannibal", "cannon",
            "captain", "cloud", "codfish" , "cannibal", "cockroach", "corsair", "dagger", "doubloons", "dubloon", "fish", "freebooter",
            "furner", "gibbet", "girl", "gold", "grog", "gull", "hornpipe", "jack", "jolly roger", "kraken", "lad",
            "lagoon", "lass", "landlubber", "lubber", "mainland", "mast", "mate", "moon", "pants", "parrot", "parrot", "pegleg",
            "pin", "pirate", "plank", "plunder", "reef", "rum", "sail", "sailor", "scabbard", "scallywag", "sea",
            "sea-dog", "seashell", "shark", "ship", "shipmate", "shore", "skiff", "skull", "son", "sun", "swabbie",
            "tobacco", "tuna", "wave", "wench", "whale", "wind", "woodchuck", "yardarm", "comrade"
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
            "haul", "hoist", "lead", "loot", "love", "mark", "pull", "raid", "ransack", "rob", "sail", "scrape", "taste",
            "trade", "vandalize", "view"
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
            "gutless", "heavy-hearted", "jolly", "lively", "mighty", "misty", "old", "proud", "rainy", "real", "rough",
            "salty", "scurvy", "shiny", "small", "stormy", "scrawny", "sunny", "swashbuckling", "undead", "warm", "weird", "wet"
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
            "ah", "ahoy", "arg", "arrr", "avast", "aw", "aww", "aye", "belay", "c\'mon", "gar", "god", "golly gosh",
            "jolly", "jolly roger", "lord", "o", "oh", "ooh", "yo-ho-ho", "well", "wow", "yarr", "yuck"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
     */
    private static String getPlaceAbstract() {
        String[] words ={
            "cabo rojo", "east india", "fort charles", "french polynesia", "haiti", "isla de muerta", "isla de sangria",
            "jamaica", "la marsa beach", "madagascar", "madagascar", "pantano river", "port degas", "port royal",
            "prison", "puerto rico", "rummage island", "singapore", "tortuga", "tubbataha reef", "norman island"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 9: concrete place (usable with article: "entering the 9")
     */
    private static String getPlaceConcrete() {
        String[] words ={
            "bahamas", "bikini atoll", "brig", "captain\'s quarters", "cook islands", "fortress", "freighter", "galley",
            "lighthouse", "pacific ocean", "quarter-deck", "reef", "seychelles"
        };

        return pickRandomString(words);
    }
}