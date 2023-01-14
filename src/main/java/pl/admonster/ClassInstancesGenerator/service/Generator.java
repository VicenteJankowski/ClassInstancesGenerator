package pl.admonster.ClassInstancesGenerator.service;

import pl.admonster.ClassInstancesGenerator.model.IntegerValuePrototype;
import pl.admonster.ClassInstancesGenerator.model.StringValuePrototype;

import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Generator {

    private List<Object> generatedObjects = new ArrayList<>();

    public final static void generate(final Class modelClass, int requestedCount) throws FileNotFoundException {

        Object newModelClassInstance;
        try {
            newModelClassInstance = createNewInstanceOf(modelClass);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error during generation of new class instance: " + e);
        }

        List<Field> fieldsOfModelClass = new ArrayList<>();
        fieldsOfModelClass.addAll(List.of(modelClass.getDeclaredFields()));

        Class superClassOfModel = modelClass.getSuperclass();
        while (superClassOfModel != null) {
            fieldsOfModelClass.addAll(List.of(superClassOfModel.getDeclaredFields()));
            superClassOfModel = superClassOfModel.getSuperclass();
        }

        System.out.println("Siema, liczba pól klasy " + modelClass.getName() + " to " + fieldsOfModelClass.size());

        for (Field singleField : fieldsOfModelClass) {
            try {
                singleField.set(newModelClassInstance, generateRandomValueFor(singleField));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error during assignation of generated value for field " + singleField.getName() + ": " + e);
            }
            System.out.println("Nazwa odnalezionego pola to: "
                    + singleField.getName() + " : " + singleField.getGenericType().getTypeName() + " # " + singleField.toGenericString());
        }
    }

    private static Object generateRandomValueFor(final Field singleField) {
        switch (singleField.getGenericType().getTypeName()) {
            case "int":
                IntegerValuePrototype integerValuePrototype = new IntegerValuePrototype(singleField);
                return RandomInteger.getRandomInt(integerValuePrototype);
            case "java.lang.String":
                StringValuePrototype stringValuePrototype = new StringValuePrototype(singleField);
                return RandomString.generate(stringValuePrototype);
        }

        return null;
    }

    private static Object createNewInstanceOf(final Class modelClass) {
        try {
            return modelClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<?> getPossibleValuesFor(final Field analyzedVar) {
        return new ArrayList<Object>();
    }
}
