package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.prototype.StringValuePrototype;

import java.util.*;
import java.util.stream.Collectors;

public class RandomString {

    public enum CharsProvider {
        LatinLettersUppercase("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        LatinLettersLowercase(LatinLettersUppercase.possibleChars.stream()
                                                                 .map(String::valueOf)
                                                                 .map(String::toLowerCase)
                                                                 .collect(Collectors.joining())),
        Digits("1234567890");

        private final Set<Character> possibleChars = new TreeSet<>();
        CharsProvider(String possibleChars){
            for (char singleChar : possibleChars.toCharArray()){
                this.possibleChars.add(singleChar);
            }
        }

        public Character generate() {
            int randomIndex = (int) RandomNumeric.generate(0, possibleChars.size() - 1);
            return iterateUntil(randomIndex, possibleChars.iterator());
        }

        public static Character generate(CharsProvider... desireCharset) {
            Set<CharsProvider> desireCharsetProcessed = Set.of(desireCharset[0]);
            for (int i = 1; i < desireCharset.length; i++)
                desireCharsetProcessed.add(desireCharset[i]);
            return CharsProvider.generate(desireCharsetProcessed);
        }

        public static Character generate(Set<CharsProvider> desireCharset) {
            List<Character> possibleChars = new ArrayList<>();
            for (CharsProvider singleCharset : desireCharset)
                possibleChars.addAll(singleCharset.possibleChars);

            int randomIndex = (int) RandomNumeric.generate(0, possibleChars.size() - 1);
            return iterateUntil(randomIndex, possibleChars.iterator());
        }

        private static Character iterateUntil(int randomIndex, Iterator<Character> iterator) {
            Character result = null;
            for (int i = 0; i <= randomIndex; i++)
                result = iterator.next();

            return result;
        }

        public Set<Character> getPossibleChars() {
            return possibleChars;
        }
    }

    public static String generate(StringValuePrototype prototype) {
        int generatedValueLength = (int) RandomNumeric.generate(prototype.getRequestedLength().getMin(),
                                                              prototype.getRequestedLength().getMax());

        StringBuilder generatedValueBuilder = new StringBuilder();
        for (int i = 0 ; i < generatedValueLength; i++)
            generatedValueBuilder.append(CharsProvider.generate(prototype.getDesireChars()));

        return generatedValueBuilder.toString();
    }

}
