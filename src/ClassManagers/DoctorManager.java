package ClassManagers;

import Enums.GenderType;
import Profiles.Doctor;

import java.io.*;
import java.util.*;

public class DoctorManager {

    //private static final String filename = "Doctors.csv";
    private List<Doctor> doctors;
    private Scanner scanner;

    public DoctorManager() {
        this.doctors = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        //loadFromFile("Doctors.csv");
    }

    public void inputDoctor() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine().trim();
        //System.out.println("First Name: " + firstName); // Debugging print

        System.out.println("Enter last name:");
        String lastName = scanner.nextLine().trim();
        //System.out.println("Last Name: " + lastName); // Debugging print

        System.out.println("Enter email:");
        String email = scanner.nextLine().trim();
        //System.out.println("Email: " + email); // Debugging print

        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine().trim();
        //System.out.println("Phone Number: " + phoneNumber); // Debugging print

        System.out.println("Enter gender (MALE, FEMALE, OTHER):");
        GenderType gender = GenderType.valueOf(scanner.nextLine().trim().toUpperCase());
        //System.out.println("Gender: " + gender); // Debugging print

        System.out.println("Enter specialization:");
        String specialization = scanner.nextLine().trim();
        //System.out.println("Specialization: " + specialization); // Debugging print

        Doctor doctor = new Doctor(firstName, lastName, email, phoneNumber, gender, specialization);
        addDoctor(doctor);

        System.out.println("Doctor added successfully!");
        //saveToFile("Doctors.csv");
    }


    // Add a doctor to the list
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            for (Doctor doctor : doctors) {
                if (doctor != null) {
                    writer.write( doctor.getId() + "," +
                            doctor.getFirstName() + "," +
                            doctor.getLastName() + "," +
                            doctor.getEmail() + "," +
                            doctor.getPhoneNumber() + "," +
                            doctor.getGender() + "," +
                            doctor.getSpecialization());
                    writer.newLine();
                    System.out.println("Writing doctor to file: " + doctor); // Debugging print
                } else {
                    System.out.println("Null doctor encountered!"); // Debugging print
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        // Load doctors from CSV file
        public List<Doctor> loadFromFile(String fileName) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                int maxIdNumber = 0;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 7) {
                        String id = parts[0];
                        String idNumberPart = id.split("-")[1];  // Extract the numeric part of the ID
                        int idNumber = Integer.parseInt(idNumberPart);
                        maxIdNumber = Math.max(maxIdNumber, idNumber);

                        Doctor doctor = new Doctor(parts[1], parts[2], parts[3], parts[4],
                                GenderType.valueOf(parts[5]), parts[6]);
                        doctor.setId(id);  // Set the full ID (including prefix) to the Doctor
                        doctors.add(doctor);
                    }
                }

                // Update the counter based on the highest ID number found
                Doctor.setIdCounter(maxIdNumber);

            } catch (IOException e) {
                System.err.println("Error loading file: " + e.getMessage());
            }
            return null;
        }


    // Display all doctors
        public void displayDoctors() {
            for (Doctor doctor : doctors) {
                System.out.println(doctor);
            }
        }

    // Search for a Doctor by Name or ID
    public void searchDoctor(String query) {
        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getFirstName().equalsIgnoreCase(query) ||
                    doctor.getLastName().equalsIgnoreCase(query) ||
                    doctor.getId().equals(query)) {
                System.out.println("Doctor found: " + doctor);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Doctor not found.");
        }
    }

        // Close the scanner (optional cleanup)
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

