package pl.admonster.ClassInstancesGenerator;

import pl.admonster.ClassInstancesGenerator.example.Student;
import pl.admonster.ClassInstancesGenerator.service.Generator;

import java.io.FileNotFoundException;

public class ClassInstancesGenerator {
    public static void main( String[] args ) {

        try {
            Generator.generate(Student.class, 1);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
