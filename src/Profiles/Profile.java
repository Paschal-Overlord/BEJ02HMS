package Profiles;

import Enums.GenderType;
import java.util.Scanner;

public class Profile {
    private String firstName;
    private String lastName;
    private GenderType gender;
    private String email;
    private String phoneNumber;

    public Profile(String firstName, String lastName, String email, String phoneNumber, GenderType gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    /*public void inputProfileDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter First Name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        lastName = scanner.nextLine();

        System.out.print("Enter Gender (MALE/FEMALE/OTHER): ");
        gender = GenderType.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Enter Email: ");
        email = scanner.nextLine();

        System.out.print("Enter Phone Number: ");
        phoneNumber = scanner.nextLine();
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + "\n" +
                "Last Name: " + lastName + "\n" +
                "Gender: " + gender + "\n" +
                "Email: " + email + "\n" +
                "Phone Number: " + phoneNumber;
    }
}

