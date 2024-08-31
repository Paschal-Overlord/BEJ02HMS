package Main;

import Access.AccessControlManager;
import Access.AccessPermissions;
import ClassManagers.MedicalRecordsManager;
import Profiles.Doctor;
import Profiles.Patient;
import Records.MedicalRecords;
import Users.RegisterUser;
import Users.UserLogin;
import ClassManagers.DoctorManager;
import ClassManagers.PatientManager;
import Enums.AccountType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserLogin userLogin = new UserLogin();
        RegisterUser registerUser = new RegisterUser();
        DoctorManager doctorManager = new DoctorManager();
        PatientManager patientManager = new PatientManager();

        List<Patient> patients = patientManager.loadFromFile("PatientsInfo.csv");
        if (patients == null) {
            patients = new ArrayList<>();
        }
        List<Doctor> doctors = doctorManager.loadFromFile("Doctors.csv");
        if (doctors == null) {
            doctors = new ArrayList<>();
        }
        MedicalRecordsManager medicalRecordsManager = new MedicalRecordsManager();


        // User login
        if (userLogin.login()) {
            AccountType accountType = userLogin.getLoggedInAccountType();
            AccessPermissions permissions = AccessControlManager.getPermissions(accountType);

            // Main menu loop
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Main Menu:");
                if (permissions.canAccessBilling()) {
                    System.out.println("1. Billing");
                }
                if (permissions.canAccessPatientInfo()) {
                    System.out.println("2. Patient Management");
                }
                if (permissions.canAccessLabTests()) {
                    System.out.println("3. Lab");
                }
                if (permissions.canAccessRegisterUser()) {
                    System.out.println("4. Register New User");
                }
                System.out.println("5. Doctor Management");
                if (permissions.canAccessMedicalRecords()) {
                    System.out.println("6 Medical Records");
                }
                System.out.println("7. Exit");

                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (permissions.canAccessBilling()) {
                            System.out.println("Accessing Billing...");
                            // Billing logic here
                        } else {
                            System.out.println("You do not have permission to access Billing.");
                        }
                        break;
                    case 2:
                        if (permissions.canAccessPatientInfo()) {
                            System.out.println("Accessing Patient Management...");
                            patientMenu(patientManager, scanner);
                        } else {
                            System.out.println("You do not have permission to access Patient Management.");
                        }
                        break;
                    case 3:
                        if (permissions.canAccessLabTests()) {
                            System.out.println("Accessing Lab...");
                            // Lab logic here
                        } else {
                            System.out.println("You do not have permission to access Lab.");
                        }
                        break;
                    case 4:
                        if (permissions.canAccessRegisterUser()) {
                            System.out.println("Registering New User...");
                            registerUser.register();
                        } else {
                            System.out.println("You do not have permission to register a new user.");
                        }
                        break;
                    case 5:
                        System.out.println("Accessing Doctor Management...");
                        doctorMenu(doctorManager, scanner);
                        break;
                    case 6:
                        System.out.println("Accessing Medical Records");
                        medicalRecordsMenu(medicalRecordsManager, scanner);
                        break;
                    case 7:
                        System.out.println("Exiting...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }

            // Close resources
            userLogin.closeScanner();
            doctorManager.closeScanner();
            patientManager.closeScanner();
        } else {
            System.out.println("Login failed. Exiting the application.");
        }
    }

    private static void medicalRecordsMenu(MedicalRecordsManager medicalRecordsManager, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("Medical Records Management Menu:");
            System.out.println("1. Add Medical Record");
            System.out.println("2. View Medical Records");
            System.out.println("3. Back to Main Menu");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    medicalRecordsManager.inputMedicalRecord();
                    break;
                case 2:
                    medicalRecordsManager.displayRecords();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

        private static void patientMenu(PatientManager patientManager, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("Patient Management Menu:");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient List");
            System.out.println("3. Search Patient by Name or ID");
            System.out.println("4. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    patientManager.inputPatient();
                    patientManager.saveToFile("PatientsInfo.csv");
                    break;
                case 2:
                    patientManager.displayPatients();
                    break;
                case 3:
                    System.out.print("Enter Patient's Name or ID: ");
                    String query = scanner.nextLine();
                    patientManager.searchPatient(query);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void doctorMenu(DoctorManager doctorManager, Scanner scanner) {
        boolean back = false;

        while (!back) {
            System.out.println("Doctor Management Menu:");
            System.out.println("1. Add Doctor");
            System.out.println("2. View Doctor List");
            System.out.println("3. Search Doctor by Name or ID");
            System.out.println("4. Back to Main Menu");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    doctorManager.inputDoctor();
                    doctorManager.saveToFile("Doctors.csv");
                    break;
                case 2:
                    doctorManager.displayDoctors();
                    break;
                case 3:
                    System.out.print("Enter Doctor's Name or ID: ");
                    String query = scanner.nextLine();
                    doctorManager.searchDoctor(query);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

}