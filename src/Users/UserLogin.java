package Users;

import Access.AccessControlManager;
import Access.AccessPermissions;
import Enums.AccountType;

import java.io.*;
import java.util.*;


public class UserLogin {
    private List<String[]> credentials;
    private AccessControlManager accessControlManager;
    private Scanner scanner;
    private AccountType loggedInAccountType; // Store the logged-in account type

    public UserLogin() {
        this.credentials = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.accessControlManager = new AccessControlManager(); // Initialize access control manager
        loadFromFile("credentials.csv");
    }

    private void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    credentials.add(parts);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login() {
        System.out.println("Enter Username:");
        String username = scanner.nextLine().trim();

        System.out.println("Enter Password:");
        String password = scanner.nextLine().trim();

        for (String[] user : credentials) {
            //System.out.println("Checking user: " + user[2] + " with password: " + user[3]);

            if (user[2].equals(username) && user[3].equals(password)) {
                loggedInAccountType = AccountType.valueOf(user[4]); // Store the logged-in account type

                AccessPermissions permissions = AccessControlManager.getPermissions(loggedInAccountType);

                System.out.println("Login successful! Account Type: " + loggedInAccountType);
                displayPermissions(permissions);
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    private void displayPermissions(AccessPermissions permissions) {
        System.out.println("Access Permissions:");
        System.out.println("Can access patient info: " + permissions.canAccessPatientInfo());
        System.out.println("Can access billing: " + permissions.canAccessBilling());
        System.out.println("Can access inventory: " + permissions.canAccessInventory());
        System.out.println("Can access lab tests: " + permissions.canAccessLabTests());
    }

    public AccountType getLoggedInAccountType() {
        return loggedInAccountType; // Return the stored account type
    }

    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }
}
