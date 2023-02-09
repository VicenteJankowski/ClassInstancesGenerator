package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.annotation.AutoGenerateValueFromTxtFile;
import pl.admonster.ClassInstancesGenerator.model.prototype.NumericValuePrototype;
import pl.admonster.ClassInstancesGenerator.model.prototype.StringValuePrototype;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Generator {

    private static List<Object> generatedObjects = new LinkedList<>();

    public static int generate(final Class<?> modelClass, int requestedCount) throws FileNotFoundException, IllegalAccessException {

        Object newModelClassInstance = createNewInstanceOf(modelClass);
        List<Field> fieldsOfModelClass = getFieldsOf(modelClass);

        for (Field singleField : fieldsOfModelClass) {
            generateRandomValueFor(newModelClassInstance, singleField);
        }

        generatedObjects.add(newModelClassInstance);

        return requestedCount > 1 ? generate(modelClass, requestedCount - 1) : 1;
    }

    private static List<Field> getFieldsOf(Class<?> modelClass) {
        List<Field> fieldsOfModelClass = new ArrayList<>(List.of(modelClass.getDeclaredFields()));

        Class<?> superClassOfModel = modelClass.getSuperclass();
        while (superClassOfModel != null) {
            fieldsOfModelClass.addAll(List.of(superClassOfModel.getDeclaredFields()));
            superClassOfModel = superClassOfModel.getSuperclass();
        }

        System.out.println("Number of found class's fields " + modelClass.getName() + " to " + fieldsOfModelClass.size());

        return fieldsOfModelClass;
    }

    private static void generateRandomValueFor(Object newModelClassInstance, Field singleField) throws IllegalAccessException {
        System.out.println("Name of found field: "
                + singleField.getName() + " : " + singleField.getGenericType().getTypeName() + " # " + singleField.toGenericString());
        try {
            singleField.setAccessible(true);
            singleField.set(newModelClassInstance, getValueFromAdequateGenratorService(singleField));
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Error during assignation of generated value for field " + singleField.getName() + ": " + e);
        }
        System.out.println("Generated value: " + singleField.get(newModelClassInstance));
    }

    private static Object getValueFromAdequateGenratorService(final Field singleField) {
        if (singleField.isAnnotationPresent(AutoGenerateValueFromTxtFile.class)) {
            return generateRandomValueFromTxtFileFor(singleField);
        } else {
            switch (singleField.getGenericType().getTypeName()) {
                case "int" -> {
                    NumericValuePrototype numericValuePrototype = new NumericValuePrototype(singleField);
                    return RandomNumeric.generate(numericValuePrototype);
                }
                case "java.lang.String" -> {
                    StringValuePrototype stringValuePrototype = new StringValuePrototype(singleField);
                    return RandomString.generate(stringValuePrototype);
                }
            }
        }

        return null;
    }

    private static String generateRandomValueFromTxtFileFor(Field singleField) {
        String txtFilePath = "./src/main/resources/" + singleField.getName() + ".txt";
        BufferedReader bufferedReader;

        try {
            bufferedReader = new BufferedReader(new FileReader(txtFilePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Txt file not found for class " + singleField.getName());
        }

        List<String> possibleValues = bufferedReader.lines().toList();
        int randomIndex = (int) RandomNumeric.generate(0, possibleValues.size() - 1);
        String generatedValue = possibleValues.get(randomIndex);

        if (generatedValue.isBlank())
            System.out.println("Warning! Value obtained form txt file is empty. Correct the txt file, unless that is what you want to obtain.");

        return generatedValue.trim();
    }

    private static Object createNewInstanceOf(final Class<?> modelClass) {
        try {
            return modelClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Error during generation of new class instance: " + e);
        }
    }

}
