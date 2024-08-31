package Profiles;

import Enums.GenderType;
import Records.MedicalRecords;

import java.io.*;
import java.util.*;

public class Patient extends Profile {
    private static int idCounter = 0;
    private String id;
    private List<MedicalRecords> medicalRecords;

    public Patient(String firstName, String lastName, String email, String phoneNumber, GenderType gender) {
        super(firstName, lastName, email, phoneNumber, gender);
        this.id = generateId();
        this.medicalRecords = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addMedicalRecord(MedicalRecords record) {
        this.medicalRecords.add(record);
    }

    public List<MedicalRecords> getMedicalRecords() {
        return medicalRecords;
    }

    private String generateId() {
        idCounter++;
        return String.format("P-%03d", idCounter);
    }

    public String getId() {
        return id;
    }

    public static void setIdCounter(int maxId) {
        idCounter = maxId;
    }
}
