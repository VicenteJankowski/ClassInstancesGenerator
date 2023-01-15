package pl.admonster.ClassInstancesGenerator.example;


public class Student extends UniversityMember {

    public Student() {
        super();
    }

    public Student(String firstName, String lastName, int age, String email, String faculty) {
        super(firstName, lastName, age, email);
        setFaculty(faculty);
    }

    String faculty;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Student{ " +
                super.toString() + '\'' +
                "faculty='" + faculty + '\'' +
                '}';
    }
}
