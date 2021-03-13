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

import com.kstenschke.dummytext.helpers.TextualHelper;

public class DictionaryLatin extends Dictionary {

    /**
     * Constructor
     */
    public DictionaryLatin() {

    }

    /**
     * @param   amountWords
     * @param   amountSentences
     * @return  One line of random text, consisting from the given amount of words and sentences
     */
    public String getRandomLine(Integer amountWords, Integer amountSentences) {
        String sentence   = "";

        for (Integer i = 0; i < amountSentences; i++) {
            sentence = sentence.concat((i > 0 ? " " : "") + getSentenceStructure(amountWords));
        }

        while (sentence.matches(".*[0-9].*")) {
            while (sentence.contains("1")) sentence = TextualHelper.replaceIfNew(sentence, "1", getNoun());
            while (sentence.contains("2")) sentence = TextualHelper.replaceIfNew(sentence, "2", getNoun());
            while (sentence.contains("3")) sentence = TextualHelper.replaceIfNew(sentence, "3", getVerbTransitive());
            while (sentence.contains("4")) sentence = TextualHelper.replaceIfNew(sentence, "4", getVerbIntransitive());
            while (sentence.contains("5")) sentence = TextualHelper.replaceIfNew(sentence, "5", getAdjective());
            while (sentence.contains("6")) sentence = TextualHelper.replaceIfNew(sentence, "6", getAdverb());
            while (sentence.contains("7")) sentence = TextualHelper.replaceIfNew(sentence, "7", getInterjection());
            while (sentence.contains("8")) sentence = TextualHelper.replaceIfNew(sentence, "8", getPlace());
        }

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
            "1 de 5 2, 3 2!", "1s sunt 1s de 5 2.",
            "5 1 6 3s 1 est.",
            "5, 5 1s 6 3 de 5, 5 1.",
            "5 2s ducunt ad 2.",
            "2 5 1 est.",
            "a falsis, 2 5 1.",
            "7.", "7, 2!", "7, 5 2!",
            "1s 4!", "1s 4 in 8!", "1s 4 in 5 8!",
            "est 5 2, cesaris.",
            "1 4s, tanquam 5 1.", "1s 4, tanquam 5 1.",
            "Cur 1 4?",
            "4 6 ducunt ad 5 1.",
            "2, 2, et 2.",
            "Ubi est 5 1?", "sunt 1es 3 5, 5 1es.", "nunquam 3 1.",
            "Cum 1 4, omnes 1es 3 5, 5 1es."
        };

        return pickRandomString(structures, amountWords);
    }

    /**
     * @return  Word of group 1:  concrete noun
     */
    private static String getNoun() {
        String[] words = {
            "abactor", "abactus", "abaculus", "abnoba", "absolutio", "accentor", "accola", "acipenser", "adelphis", "adgium",
            "adiurator", "advena", "agripeta", "amicitia", "amor", "animalis", "aonides", "apolloniates", "armarium",
            "assimilatio", "ausus", "axona", "barcas", "boreas", "brabeuta", "brodium", "bromium", "bubo", "bulla", "burgus",
            "bursa", "buxum", "byssus", "cacula", "caesium", "calcaria", "calceus", "candidatus", "canis", "cannabis", "capio",
            "castor", "cedrium", "clabulare", "classis", "clinias", "cobaltum", "compater", "competition", "consilium",
            "contencio", "coordinatae", "cotta", "cursus", "danista", "decor", "demissio", "demolitione", "detrius", "deus",
            "devatio", "devirginato", "diatria", "domina", "domus", "eleates", "elevatus", "elogium", "epos", "equiso", "era",
            "exemplar", "exsul", "extum", "fermium", "fides", "finis", "fiscina", "fluctui", "fluctus", "fortis", "fraticinida",
            "frondator", "fuga", "gabalium", "galatae", "gallus", "gemna", "genetrix", "glos", "gluten", "guttus", "habena",
            "habitio", "heuretes", "hibrida", "hilotae", "hippotoxota", "historia", "historia", "homo", "humani generis",
            "hydra", "idoleum", "ignigena", "imber", "impositio", "index", "indictio", "ionicis tormento", "itineris tramitem",
            "lacta", "lactea", "lamia", "lanista", "lapsus", "liberi", "lixa", "luba", "lumen", "luna", "luna", "lura",
            "magister", "medicina", "mens", "mensa", "messor", "mineralis", "mons", "mortem", "musa", "navis", "nix", "nixus",
            "nomen", "nuclear vexatum iacere", "nuptia", "nutrix", "olla", "omnia", "onus", "orexis", "orgia", "palus", "parma",
            "pars", "particula", "pes", "planeta", "plasmator", "poeta", "pulchritudine", "quadra", "racana", "ratione",
            "rector", "repressor", "resistentia", "rumor", "rumor", "saga", "scutum", "sectam", "secula", "sensorem", "silva",
            "solem", "solitudo", "spatii", "species", "stella", "tabes", "tata", "terror", "torquis", "torus", "triticum",
            "tumultumque", "turpis", "tus", "urbs", "uria", "usus", "valebat", "ventus", "verpa", "victrix", "vigil", "visus",
            "vita", "vortex", "vox", "xiphias", "zelus", "zeta", "zirbus"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 3:  transitive verb
     */
    private static String getVerbTransitive() {
        String[] words = {
            "magicae", "acquirere", "amor", "anhelare", "aperto", "attrahendam", "captis", "carpseris",
            "consumere", "contactus", "convertam", "demitto", "desiderium", "dignus", "examinare",
            "experientia", "fallere", "gratia", "imitari", "imperium", "imperium", "locus", "manifestum",
            "perdere", "prensionem", "promissio", "pugna", "quaestio", "reperire", "resuscitabo", "talem",
            "tractare", "transferre", "visum", "vitare"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 4:  intransitive verb
     */
    private static String getVerbIntransitive() {
        String[] words = {
            "accelerare", "assimilant", "cadunt", "cantare", "congregabo", "credere", "crescere", "experimentum",
            "favere", "ire", "manducare", "messis", "mori", "mori", "nocere", "observare", "ortum",
            "peregrinatione", "peregrinationes", "persuadere", "potus", "prarere", "resistere", "ridetis",
            "studere", "tolerare", "trabem", "unda", "velum", "volare"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 5:  adjective
     */
    private static String getAdjective() {
        String[] words = {
            "albus", "alter", "altus", "audax", "azureus", "barbatus", "bassus", "bi-color", "brevis",
            "camerarius", "castus", "clemens", "dexter", "domesticus", "emeritis", "fatalis", "ferox",
            "festus", "fidelis", "flavum", "fortis", "germanus", "gratis", "grandis", "lotus",
            "magnum", "mirabilis", "neuter", "nobilis", "noster", "peritus", "pius", "placidus", "primus",
            "raptus", "regius", "rusticus", "salvus", "secundus", "superbus", "talis", "teres", "varius", "velox"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 6:  adverb
     */
    private static String getAdverb() {
        String[] words = {
            "absolute", "acceleratrix", "aegre", "aliquando", "callide", "cito", "diligenter", "etiam", "foris",
            "grauiter", "hic", "inciviliter", "interdum", "mechanice", "nunquam", "patienter", "rare", "recte",
            "saepe", "sapienter", "satis", "sed mire", "semper", "sensim", "solite", "superbe", "tandem", "una",
            "unus", "velox", "virtualiter", "vix"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 7:  interjection
     */
    private static String getInterjection() {
        String[] words ={
            "ecce", "eheu", "hercle, 2 5!", "heu", "pol", "vae", "pol, a bene 1"
        };

        return pickRandomString(words);
    }

    /**
     * @return  Word of group 8:  place
     */
    private static String getPlace() {
        String[] words ={
            "aboa", "aetheres", "alta muta", "amivadum", "amivadum", "antenna", "antverpia", "asopus",
            "avenio", "berolinum", "brema", "brigantium", "burdigala", "caelos", "cella", "chremisa",
            "cirpi", "cirpi", "copinga", "cubiculum", "cubiculum", "culina", "divio", "gandavum", "hafnia",
            "hamburgum", "hortus", "infernum", "lentia", "lutetia", "mare", "moscua", "oenipons", "ostravia",
            "palatium", "piscinam", "quadrata", "quadrata", "revalia", "rugensis civitas", "rugensis civitas",
            "sala", "tectum", "tolosa", "tornacum", "tubinga", "tubinga", "vasa", "vierium", "virundum"
        };

        return pickRandomString(words);
    }
}