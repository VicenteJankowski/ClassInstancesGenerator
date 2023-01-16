package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.prototype.StringValuePrototype;

import java.util.*;

public class RandomString {

    enum CharsProvider {
        LatinLettersUppercase("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        LatinLettersLowercase(LatinLettersUppercase.possibleChars.toString().toLowerCase()),
        Digits("1234567890");

        private final Set<Character> possibleChars = new TreeSet<>();
        CharsProvider(String possibleChars){
            for (char singleChar : possibleChars.toCharArray()){
                this.possibleChars.add(singleChar);
            }
        }

        public Character getRandom() {
            int randomIndex = RandomInteger.getRandomInt(0, possibleChars.size() - 1);
            return iterateUntil(randomIndex, possibleChars.iterator());
        }

        public static Character getRandom(CharsProvider... desireCharset) {
            List<Character> possibleChars = new ArrayList<>();
            for (CharsProvider singleCharset : desireCharset)
                possibleChars.addAll(singleCharset.possibleChars);

            int randomIndex = RandomInteger.getRandomInt(0, possibleChars.size() - 1);
            return iterateUntil(randomIndex, possibleChars.iterator());
        }

        private static Character iterateUntil(int randomIndex, Iterator<Character> iterator) {
            Character result = null;
            for (int i = 0; i <= randomIndex; i++)
                result = iterator.next();

            return result;
        }
    }

    public static String generate(StringValuePrototype prototype) {
        int generatedValueLength = RandomInteger.getRandomInt(prototype.getLength().getMin(),
                                                              prototype.getLength().getMax());

        StringBuilder generatedValueBuilder = new StringBuilder();
        for (int i = 0 ; i < generatedValueLength; i++)
            generatedValueBuilder.append(CharsProvider.LatinLettersUppercase.getRandom());

        return generatedValueBuilder.toString();
    }

}
