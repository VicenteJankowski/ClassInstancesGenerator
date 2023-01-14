package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.StringValuePrototype;

import java.util.ArrayList;
import java.util.List;

public class RandomString {

    public static String generate(StringValuePrototype prototype) {

        int generatedValueLength = RandomInteger.getRandomInt(prototype.getLength().getMin(), prototype.getLength().getMax());
        List<Character> possibleCharacters = new ArrayList<>();
        StringBuilder generatedValueBuilder = new StringBuilder();

        for (int i = 0 ; i < generatedValueLength; i++) {
            //generatedValueBuilder.append();
        }

        return "";
    }

}
