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

public class DictionaryCookery extends Dictionary {

    /**
     * Constructor
     */
    public DictionaryCookery() {
    }

    /**
     * @param   amountWords
     * @param   amountSentences
     * @return  One line of random text, consisting from the given amount of words and sentences
     */
    public String getRandomLine(Integer amountWords, Integer amountSentences) {
        String sentence   = "";

        for (Integer i =0; i< amountSentences; i++) {
            sentence = sentence.concat((i > 0 ? " " : "") + getSentenceStructure(amountWords));
        }

        while (sentence.matches(".*[0-9].*")) {
            while (sentence.contains("PLURAL1")) sentence =
                    TextualHelper.replaceIfNew(sentence, "PLURAL1", InflectionHelper.plural(getIngredient()));

            while (sentence.contains("PLURAL2"))
                sentence = TextualHelper.replaceIfNew(sentence, "PLURAL2", InflectionHelper.plural(getFluid()));

            while (sentence.contains("GERUND3"))
                sentence = TextualHelper.replaceIfNew(sentence, "GERUND3", InflectionHelper.gerund(getVerbTransitive()));

            while (sentence.contains("GERUND4"))
                sentence = TextualHelper.replaceIfNew(sentence, "GERUND4", InflectionHelper.gerund(getVerbIntransitive()));

            while (sentence.contains("PSIMPLE3"))
                sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE3", InflectionHelper.presentSimple(getVerbTransitive()));

            while (sentence.contains("PSIMPLE4"))
                sentence = TextualHelper.replaceIfNew(sentence, "PSIMPLE4", InflectionHelper.presentSimple(getVerbIntransitive()));

            while (sentence.contains("PASTTENSE3"))
                sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE3", InflectionHelper.pastTense(getVerbTransitive()));

            while (sentence.contains("PASTTENSE4"))
                sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSE4", InflectionHelper.pastTense(getVerbIntransitive()));

            while (sentence.contains("PASTTENSEHEATUP"))
                sentence = TextualHelper.replaceIfNew(sentence, "PASTTENSEHEATUP", InflectionHelper.pastTense(getVerbHeatUp()));

            while (sentence.contains("0")) sentence = TextualHelper.replaceIfNew(sentence, "0", getDish());
            while (sentence.contains("1")) sentence = TextualHelper.replaceIfNew(sentence, "1", getIngredient());
            while (sentence.contains("2")) sentence = TextualHelper.replaceIfNew(sentence, "2", getFluid());
            while (sentence.contains("3")) sentence = TextualHelper.replaceIfNew(sentence, "3", getVerbTransitive());
            while (sentence.contains("4")) sentence = TextualHelper.replaceIfNew(sentence, "4", getVerbIntransitive());
            while (sentence.contains("5")) sentence = TextualHelper.replaceIfNew(sentence, "5", getAdjective());
            while (sentence.contains("6")) sentence = TextualHelper.replaceIfNew(sentence, "6", getAdverb());
            while (sentence.contains("7")) sentence = TextualHelper.replaceIfNew(sentence, "7", getSeasonings());
            while (sentence.contains("8")) sentence = TextualHelper.replaceIfNew(sentence, "8", getContainer());
            while (sentence.contains("9")) sentence = TextualHelper.replaceIfNew(sentence, "9", getAmount());

            while(sentence.contains("RNDNUM")) sentence = sentence.replaceFirst("RNDNUM", getNumber() );
            while(sentence.contains("HEATUP")) sentence = sentence.replaceFirst("HEATUP", getVerbHeatUp() );
            while(sentence.contains("MANIPULATE")) sentence = sentence.replaceFirst("MANIPULATE", getVerbManipulate() );

            while(sentence.contains("CHARACTERISTIC"))
                sentence = sentence.replaceFirst("CHARACTERISTIC", getNounCharacteristic() );
        }

        sentence = InflectionHelper.fixIndefiniteArticles(sentence);

        sentence = sentence.replaceAll(" beefs",        " beef ");
        sentence = sentence.replaceAll(" butters ",     " butter ");
        sentence = sentence.replaceAll(" caviars ",     " caviar ");
        sentence = sentence.replaceAll(" chickens ",    " chicken ");
        sentence = sentence.replaceAll(" choping ",     " chopping ");
        sentence = sentence.replaceAll(" cuted ",       " cut ");
        sentence = sentence.replaceAll(" jumbleed ",    " jumbled ");
        sentence = sentence.replaceAll(" mash uped ",   " mashed up ");
        sentence = sentence.replaceAll(" rubing ",      " rubbing ");
        sentence = sentence.replaceAll(" sauce soup ",  " soup ");
        sentence = sentence.replaceAll(" scrapeed ",    " scraped ");
        sentence = sentence.replaceAll(" shakeed ",     " shaken ");
        sentence = sentence.replaceAll(" shreding ",    " shredding ");
        sentence = sentence.replaceAll(" spinachs ",    " spinach ");

        String[] unincreasables   = {
            "beefs", "berries", "breasts", "carrots", "chicken", "chicken", "chickpeas", "crumps", "eggs", "lentils",
            "meatballs", "nachos", "noodles", "oysters", "oysters", "peanuts", "peas", "pickles", "pickles", "rice",
            "s taste", "saucages", "sauerkraut", "seeds", "seeds", "shrimps", "spinach", "truffles", "turkey"
        };
        sentence = InflectionHelper.depluralize(sentence, unincreasables);

        return ucFirst(sentence);
    }

    /**
     * @param   amountWords
     * @return  Random sentence structure with numbers as word type placeholders
     */
    private static String getSentenceStructure(Integer amountWords) {
        if (null != amountWords && amountWords == 1) {
            String[] structures  = {"1", "2", "8"};
            return pickRandomString(structures);
        }

        String[] structures  = {
                "4 RNDNUM PLURAL1, 1, and 7 in a large 8 over medium heat, HEATUP for RNDNUM minutes and 3 with some 1.",
                "HEATUP 5 1s in a 8 with 2 for about an hour to MANIPULATE their CHARACTERISTIC.",
                "combine 1, 1 and 1. 3 with 5 7 and serve PASTTENSE4 with 1. Enjoy!",
                "3 the 1 with 5 7, 7, 7, and 7 making sure to cover all of it.",
                "When GERUND4 5 PLURAL1, be sure they are room temperature.",
                "Everyone just loves the CHARACTERISTIC of 1 0 3d with 7.",
                "Remember: 4ed 1 tastes best when 4ed in a 8 3ed with 7.",
                "After GERUND4 the PLURAL1, 3 1, 1 and 2 with it in a 8.",
                "per guest prepare 9 of 2 with PASTTENSE4 1 for dessert.",
                "2 soup is just not the same without 7 and 5 5 PLURAL1.",
                "1 can be 3ed with 5 1, also try 3ing the 0 with 2.",
                "Everyone loves the CHARACTERISTIC of 1 0 3d with 5 7.",
                "Try GERUND3 the 2 1s with 5 2 and 2, PASTTENSEHEATUP.",
                "What’s the secret to 5 and 5 1? Always use 5 7.",
                "Instead of GERUND3 5 2 with 1, use 9 2 and 9 7 8.",
                "All children like PASTTENSE4 PLURAL1 in 2 and 7.",
                "5, 5 pudding is best PASTTENSE3 with 5 2.",
                "4 1 6, then mix with 2 and serve 6 in 8.",
                "Place the 1 in a 8, and 3 6 with 5 2.",
                "5 1 can be made 5 by GERUND3 with 2.",
                "1 0 has to have a 5, 5 1 component.",
                "1 tastes best with 2 and lots of 7.",
                "try 4ing 0 3ed with 2, 3ed with 7.",
                "3 each side of the 1 with 9 of 1.",
                "To the 5 1 add 1, 1, 2 and 5 1.",
                "For a 5 5 0, add some 2 and 7.",
                "1 combines greatly with 5 1.",
                "Try 4ing 1 0 3ed with 2.",
                "With PLURAL1 drink 2.",
                "3 9 of 1 in 9 of 2.",
        };

        return pickRandomString(structures, amountWords);
    }

    /**
     * @return  Word of group 0: dishes
     */
    private static String getDish() {
        String[] words ={
            "cake", "casserole", "cheesecake", "chili", "curry", "frittata", "fritters", "kebab", "loaf", "mousse",
            "paste", "pie", "pudding", "punch", "salad", "sauce", "smoothie", "soup", "stew", "stir-fry", "pie", "pilaf",
            "platter", "porridge", "ricotta", "tart"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 1: ingredients
     */
    private static String getIngredient() {
        String[] words = {
            "apple", "asparagus", "avocado", "bagel", "blood oranges", "blueberries", "bok choy", "broccoli", "butter",
            "cabbage", "carrots", "cauliflower", "caviar", "celery", "chicken breasts", "chicken lard", "chicken",
            "chickpeas", "chickpeas", "chicory", "chili", "chocolate", "cracker crumps", "cucumber", "doughnut",
            "eggs", "escargot", "garlic", "ginger", "ghee", "ground beef", "herring", "leek", "lentils", "lettuce",
            "lobster", "mackerel", "margerine", "marshmellow", "meatballs", "meatloaf", "melon", "nachos", "noodles",
            "okra", "onion", "oysters", "peanut butter", "peanuts", "pickles", "popcorn", "pork butt", "pork shoulder",
            "pumpkin seeds", "quinoa", "ramen", "raspberries", "rhubarb", "rice", "sauerkraut", "sausages", "seaweed",
            "shrimps", "spinach", "squid", "steak", "strawberries", "strudel", "tofu", "truffels", "tuna", "turkey",
            "walnut", "watermelon", "white bread", "zucchini"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 2:  fluid
     */
    private static String getFluid() {
        String[] words ={
            "adobo sauce", "anchovy essence", "BBQ sauce", "beer", "bourbon", "buttermilk", "champaign",
            "cocktail sauce", "coconut milk", "coffee", "condensed milk", "cream", "crême fraîche", "emeril\'s essence",
            "fish sauce", "gold tequila", "gravy", "hollandaise sauce", "honey", "iced tea", "ice water", "joghurt",
            "1 juice", "kefir", "ketchup", "lemon juice", "1 lassi", "lime", "maple syrup", "mayonnaise", "milk",
            "1 sauce", "mint sauce", "olive oil", "orange juice", "oyster sauce", "peanut sauce", "peppermint tea",
            "plain vinegar", "red wine", "remoulade", "rice vinegar", "ricotta", "rum", "salad cream", "salsa verde",
            "sour milk", "soy sauce", "sweet chili sauce", "tabasco", "tea", "teriyaki", "triple sec", "vinaigrette",
            "vinegar", "water", "whipped cream", "whiskey", "white wine", "worcestershire sauce"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 3:  transitive verb
     */
    private static String getVerbTransitive() {
        String[] words = {
            "blend", "brush", "cover", "enamel", "flavor", "garnish", "jumble", "mash up", "marinate", "mix", "rinse",
            "rub", "soak", "season", "varnish", "whisk", "toss", "decorate"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 4:  intransitive verb
     */
    private static String getVerbIntransitive() {
        String[] words = {
            "boil", "break", "chop", "cook", "crush", "cut", "drain", "dry", "flatten", "fluff", "grill", "heat",
            "mash", "peel", "press", "roast", "scrape", "shake", "shred", "simmer", "slice", "smash", "squeeze",
            "toast", "warm"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group "HEATUP"
     */
    private static String getVerbHeatUp() {
        String[] words = {
            "boil", "cook", "grill", "heat", "ice", "refrigerate", "roast", "sauté", "simmer", "steam", "warm"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group MANIPULATE - manipulation word (e.g. "to cut the bitterness")
     */
    private static String getVerbManipulate() {
        String[] words ={
                "bring down", "chamfer", "cut", "enhance", "increase", "milden", "perfect", "soothe", "upgrade"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 5:  adjective
     */
    private static String getAdjective() {
        String[] words = {
            "aged", "al dente", "aromatic", "bitter", "bloody", "canned", "chilled", "chopped", "clammy", "cold",
            "cored", "crushed", "crusted", "dark", "delicious", "diced", "divided", "dried", "fluffy", "fresh", "gooey",
            "grey", "ground", "hardened", "heated", "hot", "iced", "instant", "juicy", "large", "large", "melted",
            "mild", "minced", "muddy", "niffy", "nutty", "old", "packaged", "puréed", "quartered", "raw", "rich", "ripe",
            "roasted", "salted", "salty", "shredded", "sichuan-style", "sliced", "slobbery", "small", "smashed",
            "smooth", "soaked", "sour", "sour", "springy", "squeezed", "sticky", "sun-dried", "sweet", "tangy", "tasty",
            "tender", "thin", "warm", "whole", "yellow"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 6:  adverb
     */
    private static String getAdverb() {
        String[] words = {
            "carefully 5", "carefully", "equally", "exactly", "fairly", "fast", "freshly", "fully", "immediately",
            "patiently", "quickly", "regularly 5", "roughly", "smoothly", "tenderly", "thoroughly", "ultimately"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 7:  seasonings
     */
    private static String getSeasonings() {
        String[] words ={
            "anise", "baking powder", "basil leafs", "basil", "black cardamon", "black cardamon", "black pepper",
            "brine", "brown sugar", "butter", "butterscotch", "cayenne pepper", "celery", "chipotle chile powder",
            "chocolate", "cinnamon", "corn syrup", "cumin", "curry", "dill", "dill", "flower", "garlic", "green curry",
            "jasmine", "lime", "marmalade", "mustard", "nutmeg", "onion powder", "oregano", "parsley", "pepper",
            "radish sprouts", "rosemary", "rum", "sugar", "szechuan pepper", "thyme", "vegemite", "vodka", "wasabi",
            "woodruff", "za\'atar"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 8: abstract place (usable w/o article: "going to 8")
     */
    private static String getContainer() {
        String[] words ={
            "basin", "bottle", "bowl", "bucket", "casserole", "cooker", "fine-mesh strainer", "frying pan", "grinder",
            "ice blender", "jar", "pan", "saucepan", "sauté pan", "soup pot", "wok", "plastic bag"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 9: amounts
     */
    private static String getAmount() {
        String[] words ={
            "RNDNUM peaces", "RNDNUM pounds", "RNDNUM teaspoons", "half a kilo", "one container", "one cup", "one jar",
            "one package", "one quarter cup", "RNDNUM oz", "RNDNUM pounds", "RNDNUM tablespoons", "RNDNUM and a half teaspoons"
        };

        return pickRandomString(words);
    }

    /**
     * @return  A number word
     */
    private static String getNumber() {
        String[] words ={
            "two", "three", "four", "five", "six", "seven", "eight", "nine", "eleven", "twelve", "fifteen", "twenty",
            "a dozen", "a handfull"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Characteristic nouns ("how" a food is)
     */
    private static String getNounCharacteristic() {
        String[] words ={
            "aroma", "asperity", "bitterness", "consistency", "creaminess", "fierceness", "flavor", "keenness",
            "mossiness", "pepperiness", "saltyness", "sourness", "sweetness", "tartness", "thickness", "viscosity"
        };

        return pickRandomString(words);
    }
}
