package ClassManagers;

import Enums.GenderType;
import Profiles.Patient;
import ClassManagers.PatientManager;
import Records.MedicalRecords;

import java.io.*;
import java.util.*;

public class MedicalRecordsManager {
    private Map<String, MedicalRecords> medicalRecords = new HashMap<>();
    private Map<String, Patient> patients = new HashMap<>();



    public void loadPatientsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader("PatientsInfo.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String firstName = parts[0];
                    String lastName = parts[1];
                    String email = parts[2];
                    String phoneNumber = parts[3];
                    GenderType gender = GenderType.valueOf(parts[4]);
                    Patient patient = new Patient(firstName, lastName, email, phoneNumber, gender);
                    patients.put(patient.getId(), patient);
                }
            }
            System.out.println("Patients loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading patients from file: " + e.getMessage());
        }
    }


    public void inputMedicalRecord() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine();

        if (!isPatientValid(patientId)) {
            System.out.println("Invalid patient ID. Record not added.");
            return;
        }

        System.out.print("Enter Diagnosis: ");
        String diagnosis = scanner.nextLine();

        System.out.print("Enter Treatment: ");
        String treatment = scanner.nextLine();

        MedicalRecords record = new MedicalRecords(diagnosis, treatment);
        medicalRecords.put(patientId, record);

        System.out.println("Medical record added successfully.");
    }

    public boolean isPatientValid(String patientId) {
        return patients.containsKey(patientId);
    }

    public void addMedicalRecord(String patientId, MedicalRecords record) {
        if (isPatientValid(patientId)) {
            medicalRecords.put(patientId, record);
            System.out.println("Medical record added successfully.");
        } else {
            System.out.println("Invalid patient ID. Record not added.");
        }
    }

    public MedicalRecords getMedicalRecord(String patientId) {
        if (isPatientValid(patientId)) {
            return medicalRecords.get(patientId);
        } else {
            System.out.println("Invalid patient ID.");
            return null;
        }
    }


    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, MedicalRecords> entry : medicalRecords.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue().getDiagnosis() + "," + entry.getValue().getTreatment());
                writer.newLine();
            }
            System.out.println("Medical records saved to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String patientId = parts[0];
                    String diagnosis = parts[1];
                    String treatment = parts[2];
                    MedicalRecords record = new MedicalRecords(diagnosis, treatment);
                    medicalRecords.put(patientId, record);
                }
            }
            System.out.println("Medical records loaded from file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading from file: " + e.getMessage());
        }
    }

    public void displayRecords() {
        if (medicalRecords.isEmpty()) {
            System.out.println("No medical records available.");
        } else {
            for (Map.Entry<String, MedicalRecords> entry : medicalRecords.entrySet()) {
                String patientId = entry.getKey();
                MedicalRecords record = entry.getValue();
                System.out.println("Patient ID: " + patientId);
                System.out.println("Diagnosis: " + record.getDiagnosis());
                System.out.println("Treatment: " + record.getTreatment());
                System.out.println("---------------------------");
            }
        }
    }

}
