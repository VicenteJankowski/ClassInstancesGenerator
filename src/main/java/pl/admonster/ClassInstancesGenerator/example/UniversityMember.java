package pl.admonster.ClassInstancesGenerator.example;



import java.security.InvalidParameterException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


abstract class UniversityMember {

    public UniversityMember(String firstName, String lastName, int age, String email) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
        this.setEmail(email);
    }

    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    public UniversityMember() {
    }

    public void setFirstName(String firstName) throws InvalidParameterException{
        if(firstName.length() < 3)
            throw new InvalidParameterException("First name is too short");

        this.firstName = firstName;
    }

    public void setAge(int age) throws IllegalArgumentException{
        if(age <= 18)
            throw new IllegalArgumentException("age under 19");
        this.age = age;
    }

    public void setEmail(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern emailValidation = Pattern.compile(regex);
        Matcher matcher = emailValidation.matcher(email);

        if(!matcher.matches())
            throw new InvalidParameterException("wrong e-mail address");
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof UniversityMember))
            return false;

        UniversityMember castedObj = (UniversityMember) obj;

        return this.email.equals(castedObj.getEmail());
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'';
    }
}
