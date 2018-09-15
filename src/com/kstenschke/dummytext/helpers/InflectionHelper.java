/*
 * Copyright 2013-2018 Kay Stenschke
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
package com.kstenschke.dummytext.helpers;

/**
 * Helper methods for creating derived word forms
 */
public class InflectionHelper {

    /**
     * Create gerund form of given verb
     * ex; hear       => hearing
     *     trade      => trading     (trailing e is detected + removed)
     *     trade up   => mashing up  (trailing 2nd word is detected and the gerund is made on the first word)
     *
     * @param   verb
     * @return
     */
    public static String gerund(String verb) {
        verb = verb.trim();
        if (verb.contains(" ")) {
            String[] words = verb.split(" ");
            return gerund(words[0]) + " " + words[1];
        }
        if (verb.endsWith("at")
         || verb.endsWith("et")
         || verb.endsWith("it")
         || verb.endsWith("ot")
         || verb.endsWith("ut")) {
            return verb + "ting";
        }
        return verb.endsWith("e")
                ? verb.substring(0, verb.length()-1) + "ing"
                : verb + "ing";
    }

    /**
     * Create past tense form of given verb
     * ex; bake       => baked
     *     travel     => travelled
     *     picknic    => picknicked
     *
     * @param   verb
     * @return
     */
    public static String pastTense(String verb) {
        verb = verb.trim();

        if (verb.contains(" ")) {
            String[] words = verb.split(" ");
            return pastTense(words[0]) + " " + words[1];
        }
        if (verb.equals("eaked")) {
            return "broken";
        }
        if (verb.equals("eated")) {
            return "eaten";
        }
        if (verb.endsWith("e")) {
            return verb + "d";
        }
        if (verb.endsWith("al")
         || verb.endsWith("el")
         || verb.endsWith("il")
         || verb.endsWith("ol")
         || verb.endsWith("ul")) {
            return verb + "led";
        }
        if (verb.endsWith("ap")
         || verb.endsWith("ep")
         || verb.endsWith("ip")
         || verb.endsWith("op")
         || verb.endsWith("up")) {
            return verb + "ped";
        }
        if (verb.endsWith("ic")) {
            return verb + "ked";
        }
        if (verb.endsWith("y")
         && !verb.endsWith("ay")
         && !verb.endsWith("ey")
         && !verb.endsWith("oy")
         && !verb.endsWith("uy")) {
            return verb.substring(0, verb.length()-1) + "ied";
        }

        return verb + "ed";
    }

    /**
     * @param   word
     * @return  plural of given word
     */
    public static String plural(String word) {
        if (word.endsWith("y")
            && !word.endsWith("ay")
            && !word.endsWith("ey")
            && !word.endsWith("oy")
           && !word.endsWith("uy")) {
            word = word.subSequence(0, word.length()-1 ).toString() + "ies";
        } else if (word.endsWith("ss")) {
            word = word + "es";
        } else if (!word.endsWith("s")) {
            word = word + "s";
        }

        return word;
    }

    /**
     * @param   verb
     * @return  present simple form of given verb
     */
    public static String presentSimple(String verb) {
        if (verb.endsWith("ch")) {
            verb = verb + "es";
        } else if (verb.endsWith("y")) {
            verb = verb.subSequence(0, verb.length()-1 ).toString() + "ies";
        } else if (!verb.endsWith("s")) {
            verb = verb + "s";
        }

        if (verb.equals("dos")) {
            verb = "does";
        }

        return verb;
    }

    /**
     * Remove additional trailing "s" from given words
     *
     * @param   sentence
     * @param   unincreasables
     * @return
     */
    public static String depluralize(String sentence, String[] unincreasables) {
        for (String unincreasable: unincreasables) {
            sentence = depluralize(sentence, unincreasable);
        }

        return sentence;
    }

    private static String depluralize(String sentence, String unincreasable) {
        return sentence.replaceAll(unincreasable + "s", unincreasable);
    }

    /**
     * @param   sentence
     * @return  The sentence with the indefinite article "a" changed into "an", when preceding words starting with a vowel
     */
    public static String fixIndefiniteArticles(String sentence) {
        sentence = sentence.replaceAll(" a a",    " an a");
        sentence = sentence.replaceAll(" a e",    " an e");
        sentence = sentence.replaceAll(" a i",    " an i");
        sentence = sentence.replaceAll(" a o",    " an o");
        sentence = sentence.replaceAll(" a ul",   " an ul");

        String sentenceLower = sentence.toLowerCase();
        if (   sentenceLower.startsWith("a a")
            || sentenceLower.startsWith("a e")
            || sentenceLower.startsWith("a o")
            || sentenceLower.startsWith("a i")
            || sentenceLower.startsWith("a u")
        ) {
            sentence = "An " + sentence.substring(2);
        }

        return sentence;
    }
}
