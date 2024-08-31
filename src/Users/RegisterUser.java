package Users;

import Enums.AccountType;
import Profiles.Doctor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterUser {
    private List<String[]> users;
    private Scanner scanner;

    public RegisterUser() {
        this.users = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void register() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();

        System.out.println("Enter username:");
        String username = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter account type (ADMIN, DOCTOR, PATIENT, NURSE, LABTECH, ACCOUNTANT):");
        AccountType accountType = AccountType.valueOf(scanner.nextLine().toUpperCase());

        String[] user = {firstName, lastName, username, password, accountType.name()};
        users.add(user);

        System.out.println("User registered successfully!");

        saveToFile("credentials.csv");
    }

    private void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (String[] user : users) {
                writer.write(String.join(",", user));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String[]> getUsers() {
        return users;
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}


