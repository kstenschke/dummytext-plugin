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

public class DictionaryEsoteric extends Dictionary {

    public DictionaryEsoteric() {
    }

    /**
     * @param   amountWords
     * @param   amountSentences
     * @return  Random line(s) of text, consisting from the given amount of words (1 line) or lines
     */
    public String getRandomLine(Integer amountWords, Integer amountSentences) {
        /* Esoteric wisdom creates mostly long sentences, so only one per line. */
        String sentence   = getSentenceStructure(amountWords);

        while (sentence.matches(".*[0-9].*")) {
            while (sentence.contains("7")) sentence = TextualHelper.replaceIfNew(sentence, "7", getInterjection());

            while (sentence.contains("PLURAL1")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PLURAL1", InflectionHelper.plural(getNounConcrete()));

            while (sentence.contains("PLURAL2")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PLURAL2", InflectionHelper.plural(getNounAbstract()));

            while (sentence.contains("GERUND3")) sentence =
                    TextualHelper.replaceIfNew(sentence, "GERUND3", InflectionHelper.gerund(getVerbTransitive()));

            while (sentence.contains("GERUND4")) sentence =
                    TextualHelper.replaceIfNew(sentence, "GERUND4", InflectionHelper.gerund(getVerbIntransitive()));

            while (sentence.contains("PSIMPLE3")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PSIMPLE3", InflectionHelper.presentSimple(getVerbTransitive()));

            while (sentence.contains("PSIMPLE4")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PSIMPLE4", InflectionHelper.presentSimple(getVerbIntransitive()));

            while (sentence.contains("PASTTENSE3")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PASTTENSE3", InflectionHelper.pastTense(getVerbTransitive()));

            while (sentence.contains("PASTTENSE4")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PASTTENSE4", InflectionHelper.pastTense(getVerbIntransitive()));

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

        sentence = sentence.replaceAll("a one ",        "one ");
        sentence = sentence.replaceAll("an one ",       "one ");
        sentence = sentence.replaceAll("es each other", "e each other");
        sentence = sentence.replaceAll("feelled ",      "felt ");
        sentence = sentence.replaceAll("forgeted ",     "forgotten ");
        sentence = sentence.replaceAll("meeted ",       "met ");
        sentence = sentence.replaceAll("praysing ",     "praising ");
        sentence = sentence.replaceAll("s meets",       "s meet");
        sentence = sentence.replaceAll("the peace",     "peace");
        sentence = sentence.replaceAll("understanded",  "understood");

        String[] unincreasables   = { "everything", "many", "peace", "sex" };
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

        String[] structures = {
                  "2 happens when you 3 2 so 6 that whatsoever you are GERUND4 is your 2.",
                  "Be 5 for whoever PSIMPLE4, because each has been PASTTENSE3 with 2.",
                  "When the 1 of 2 PSIMPLE3 the PLURAL2 of the 1, the 2 will know 1.",
                  "Going to the 9 doesn’t 3 2 anymore than GERUND3 creates 5 2.",
                  "the 2 of your PLURAL2 will 4 6 when you 3 that 2 is the 1.",
                  "PLURAL1, PLURAL1, and 5 PLURAL1 will always protect them.",
                  "Who can 3 the 2 and 2 of a 1 if he has the 5 2 of the 1?",
                  "all 5 PLURAL1 3 each other, only 5 PLURAL1 have a 2.",
                  "As i have PASTTENSE3 you, so you must 3 one another.",
                  "Everything we do is connected with 2: 2, 2, 2, 2.",
                  "2 doesn’t 6 3 any 1 — but the 1 is what PSIMPLE4.",
                  "When one PSIMPLE3 2 and 2, one is able to 3 2.",
                  "2 is not 5 in 8, the 9, or 8, but everywhere.",
                  "One must 3 the 1 in order to 3 the 1 of 5 2.",
                  "2 is the only 2, the only guarantee of 2.",
                  "The 1 has 2, but not everyone PSIMPLE3 it.",
                  "If you 4 or 4 with a 5 2, 2 PSIMPLE3 you.",
                  "You have to 4, and 3 2 by your GERUND4.",
                  "Yes, there is 8, it PSIMPLE4 with 2.",
                  "Never 3 the 1, for you cannot 3 it.",
                  "One 5 2 i give you: 3 each other. ",
                  "To some, a 1 is a 2 for GERUND3.",
                  "5 PLURAL2 PSIMPLE3 most PLURAL2.",
                  "The 1 PSIMPLE3 2 which is not 5.",
                  "4 and you will be PASTTENSE3 6.",
                  "Our 5 2 for 2 is to 3 others 6.",
                  "2 PSIMPLE3 when you 3 with 2.",
                  "The 5 2 of 2 is to 3 with 2.",
                  "The 2 of GERUND3 PLURAL1 is 5.",
                  "8 is not the 5 2 of the 1.",
                  "8 of 2 will 6 3 a 5 1.",
                  "7.", "confucius says: 7.",
                  "A 5 form of 2 is the 2.",
                  "Not 8 or 8, 3 the 2.",
                  "The 9 is full of 2.",
                  "Always 6 3 the 5 1.",
                  "The 2 is a 5 1.",
                  "2, 2 and a 5 9.",
                  "The 1 PSIMPLE3.",
                  "Be 5.",
        };

        return pickRandomString(structures, amountWords);
    }

    /**
     * @return  Word of group 1:  concrete noun
     */
    private static String getNounConcrete() {
        String[] words = {
            "aspect", "believer", "body", "cow", "creator", "doer", "ego", "explosion of the 2", "follower",
            "guru", "individual", "karma", "lama", "lord", "lotus", "lover", "master", "monkey", "moon", "power",
            "scholar", "saint", "seeker", "self", "sinner", "source", "spirit", "sun", "teacher", "therapist", "thing",
            "visitor", "wind", "yogi"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 2:  abstract noun
     */
    private static String getNounAbstract() {
        String[] words = {
            "acceptance", "advice", "affirmation", "afterlife", "anger", "art", "ascension", "attitude", "attraction",
            "awareness", "beauty", "blessing", "bliss", "booda-hood", "career", "chaos", "conclusion", "control",
            "core", "courage", "death", "definition", "density", "dimension", "dogma", "emptiness", "energy",
            "enlightenment", "enlightenment", "everything", "extend", "faith", "fear", "freedom", "futility",
            "grace", "happiness", "harmony", "heaven", "heaven", "history", "hypnosis", "intuition", "issue", "joy",
            "justice", "life", "light", "living", "love", "man", "manifestation", "meditation", "milk", "mind",
            "mineral", "moonlight", "music", "pain", "paradox", "peace", "politics", "purpose", "reincarnation",
            "relativity", "result", "resurrection", "sainthood", "samadhi", "satori", "sex", "shame", "silence",
            "solitude", "sorrow", "stigma", "suffering", "surrender", "tantra", "thought", "totality", "trust", "truth",
            "uniqueness", "vision", "volume", "zen"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 3:  transitive verb
     */
    private static String getVerbTransitive() {
        String[] words = {
            "absorb", "acquire", "avoid", "believe", "capture", "desire", "develop", "discover", "emerge", "experience",
            "facilitate", "fear", "feel", "follow", "forget", "gain", "grasp", "handle", "hear", "hurt", "illuminate",
            "invent", "know", "love", "meet", "need", "praise", "realize", "receive", "reject", "remember", "respect",
            "shape", "study", "synthesise", "trap", "understand", "view", "visualize", "witness", "yearn"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 4:  intransitive verb
     */
    private static String getVerbIntransitive() {
        String[] words = {
            "appear", "balance", "become", "contact", "convert", "die", "disappear", "disturb", "do", "ease", "empower",
            "exist", "experiment", "fail", "fly", "grow", "listen", "lure", "occur", "preach", "remain", "rise",
            "shine", "sit", "travel", "wrestle"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 5:  adjective
     */
    private static String getAdjective() {
        String[] words = {
            "abstruse", "alchemistic", "ancient", "apostolic", "atomic", "beloved", "boundless", "bright", "brilliant",
            "calm", "celestine", "closest", "crystal", "embittered", "enlightened", "eternal", "evil", "fraternal",
            "further", "great", "hermetic", "holographic", "holy", "honorable", "imminent", "important", "inner",
            "inward", "magical", "meaningless", "mediocre", "mysterious", "new", "occult", "one", "outer", "parallel",
            "pictorial", "playful", "popular", "powerful", "prime", "private", "psychic", "pure", "remarkable",
            "searious", "secret", "separate", "shining", "simple", "sincere", "small", "soft", "special", "spiritual",
            "strange", "superior", "synthetic", "true", "ultimate", "unbiased", "unconditional", "united", "unprepared",
            "unveiled", "wonderful"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 6:  adverb
     */
    private static String getAdverb() {
        String[] words = {
            "agreeable", "authoratively", "balanced", "beautifully", "cheerfully", "compassionately", "confidently",
            "cosmically", "daily", "earthly", "esoterically", "essentially", "extraordinarilly", "gently",
            "harmoniously", "oddly", "purely", "qabalistic", "safely", "silently", "sincerely", "solitary",
            "spiritually", "substantially", "theosophically", "truly", "wisely", "wonderfully"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 7:  interjection
     */
    private static String getInterjection() {
        String[] words ={
            "the 1 is like the 1", "2 and om",
            "stop to 3 and 4", "confused by PLURAL2, the 1 is",
            "the 2 of 2 leads to 2", "2, 2, 2", "2, 2 and 4",
            "never 3 for others what you would not 3 for the 2 of it",
            "6 2 is to 3 the 2 of the 1's ignorance",
            "i 4 and i 4. i 4 and i 4. i 4 and i 4",
            "the 5 1 is GERUND4, the 5 1 is GERUND4",
            "in 8 all PLURAL1 3 2", "in the 9 all PLURAL1 3 2",
            "in 8 PLURAL4 6 2"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
     */
    private static String getPlaceAbstract() {
        String[] words ={
            "5 places", "chaos", "earth", "heavens", "hell", "nirvana", "order", "paradise", "shangri-la", "space",
            "upstairs", "wonderland", "zion", "over there"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 9: abstract place (usable with article: "entering the 9")
     */
    private static String getPlaceConcrete() {
        String[] words ={
            "5 mind", "5 world", "afterworld", "body of 2", "country of 2", "great unknown", "heavens of 2", "home",
            "kingdom", "material world", "mind", "monastery", "next world", "pit", "pyramid", "realm of 2", "state of 2",
            "underworld"
        };

        return pickRandomString(words);
    }
}