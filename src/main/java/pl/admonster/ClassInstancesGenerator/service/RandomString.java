package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.StringValuePrototype;

import java.nio.charset.Charset;
import java.nio.charset.spi.CharsetProvider;
import java.util.*;

public class RandomString {

    enum CharsProvider {
        LatinLetters("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        Digits("1234567890");

        private final Set<Character> possibleChars = new TreeSet<>();
        CharsProvider(String possibleChars){
            for (char singleChar : possibleChars.toCharArray()){
                this.possibleChars.add(singleChar);
            }
        }

        public Character getRandom() {
            int randomIndex = RandomInteger.getRandomInt(0, possibleChars.size() - 1);
            Iterator<Character> possibleCharsIterator = possibleChars.iterator();

            Character result = null;
            for (int i = 0; i <= randomIndex; i++) {
                result = possibleCharsIterator.next();
            }
            return result;
        }
    }

    public static String generate(StringValuePrototype prototype) {

        int generatedValueLength = RandomInteger.getRandomInt(prototype.getLength().getMin(), prototype.getLength().getMax());

        StringBuilder generatedValueBuilder = new StringBuilder();
        for (int i = 0 ; i < generatedValueLength; i++)
            generatedValueBuilder.append(CharsProvider.LatinLetters.getRandom());

        return generatedValueBuilder.toString();
    }

}
