package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.AutoGenerateValueFromTxtFile;
import pl.admonster.ClassInstancesGenerator.model.prototype.IntegerValuePrototype;
import pl.admonster.ClassInstancesGenerator.model.prototype.StringValuePrototype;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private List<Object> generatedObjects = new ArrayList<>();

    public static void generate(final Class<?> modelClass, int requestedCount) throws FileNotFoundException, IllegalAccessException {

        Object newModelClassInstance;
        try {
            newModelClassInstance = createNewInstanceOf(modelClass);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error during generation of new class instance: " + e);
        }

        List<Field> fieldsOfModelClass = new ArrayList<>(List.of(modelClass.getDeclaredFields()));

        Class<?> superClassOfModel = modelClass.getSuperclass();
        while (superClassOfModel != null) {
            fieldsOfModelClass.addAll(List.of(superClassOfModel.getDeclaredFields()));
            superClassOfModel = superClassOfModel.getSuperclass();
        }

        System.out.println("Number of found class's fields " + modelClass.getName() + " to " + fieldsOfModelClass.size());

        for (Field singleField : fieldsOfModelClass) {
            System.out.println("Name of found field: "
                    + singleField.getName() + " : " + singleField.getGenericType().getTypeName() + " # " + singleField.toGenericString());
            try {
                singleField.setAccessible(true);
                singleField.set(newModelClassInstance, generateRandomValueFor(singleField));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error during assignation of generated value for field " + singleField.getName() + ": " + e);
            }
            System.out.println("Generated value: " + singleField.get(newModelClassInstance));
        }
    }

    private static Object generateRandomValueFor(final Field singleField) {
        if (singleField.isAnnotationPresent(AutoGenerateValueFromTxtFile.class)) {
            String txtFilePath = "./src/main/resources/" + singleField.getName() + ".txt";
            BufferedReader bufferedReader;

            try {
                bufferedReader = new BufferedReader(new FileReader(txtFilePath));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Txt file not found for class " + singleField.getName());
            }

            List<String> possibleValues = bufferedReader.lines().toList();
            int randomIndex = RandomInteger.getRandomInt(0, possibleValues.size() - 1);
            String generatedValue = possibleValues.get(randomIndex);

            if (generatedValue.isBlank())
                System.out.println("Warning! Value obtained form txt file is empty. Correct the txt file, unless that is what you want to obtain.");

            return generatedValue;

        } else {
            switch (singleField.getGenericType().getTypeName()) {
                case "int" -> {
                    IntegerValuePrototype integerValuePrototype = new IntegerValuePrototype(singleField);
                    return RandomInteger.getRandomInt(integerValuePrototype);
                }
                case "java.lang.String" -> {
                    StringValuePrototype stringValuePrototype = new StringValuePrototype(singleField);
                    return RandomString.generate(stringValuePrototype);
                }
            }
        }

        return null;
    }

    private static Object createNewInstanceOf(final Class<?> modelClass) {
        try {
            return modelClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
