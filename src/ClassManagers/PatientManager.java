package ClassManagers;

import Enums.GenderType;
import Profiles.Patient;

import java.io.*;
import java.util.*;

public class PatientManager {

    private static final String fileName = "PatientsInfo.csv";
    private List<Patient> patients;
    private Scanner scanner;

    public PatientManager() {
        this.patients = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void inputPatient() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine().trim();

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine().trim();

        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine().trim();

        System.out.println("Enter gender (MALE, FEMALE, OTHER):");
        GenderType gender = GenderType.valueOf(scanner.nextLine().trim().toUpperCase());

        Patient patient = new Patient(firstName, lastName, email, phoneNumber, gender);
        addPatient(patient);

        System.out.println("Patient added successfully!");
        //saveToFile("PatientsInfo.csv");
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Patient patient : patients) {
                if (patient != null) {
                    writer.write(  patient.getId() + "," +
                            patient.getFirstName() + "," +
                            patient.getLastName() + "," +
                            patient.getEmail() + "," +
                            patient.getPhoneNumber() + "," +
                            patient.getGender());
                    writer.newLine();
                    System.out.println("Writing patient to file: " + patient); // Debugging print
                } else {
                    System.out.println("Null patient encountered!"); // Debugging print
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Patient> loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int maxIdNumber = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0];
                    String idNumberPart = id.split("-")[1];  // Extract the numeric part of the ID
                    int idNumber = Integer.parseInt(idNumberPart);
                    maxIdNumber = Math.max(maxIdNumber, idNumber);

                    Patient patient = new Patient(parts[1], parts[2], parts[3], parts[4],
                            GenderType.valueOf(parts[5]));
                    patient.setId(id);  // Set the full ID (including prefix) to the Patient
                    patients.add(patient);
                }
            }

            // Update the counter based on the highest ID number found
            Patient.setIdCounter(maxIdNumber);

        } catch (IOException e) {
            System.err.println("Error loading file: " + e.getMessage());
        }
        return patients;
    }


    public void displayPatients() {
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }


    // Search for a Patient by Name or ID
    public void searchPatient(String query) {
        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getFirstName().equalsIgnoreCase(query) ||
                    patient.getLastName().equalsIgnoreCase(query) ||
                    patient.getId().equals(query)) {
                System.out.println("Patient found: " + patient);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Patient not found.");
        }
    }

    // Close the scanner (optional cleanup)
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
