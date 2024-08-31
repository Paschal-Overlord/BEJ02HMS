package Profiles;

import Enums.GenderType;

import java.io.*;
import java.util.*;

public class Doctor extends Profile {
    private static int idCounter = 0;
    private final String specialization;
    private String id;

    public Doctor(String firstName, String lastName, String email, String phoneNumber, GenderType gender, String specialization) {
        super(firstName, lastName, email, phoneNumber, gender);
        this.specialization = specialization;
        this.id = generateId();
    }

    public void setId(String id) {
        this.id = id;
    }

    private String generateId() {
        idCounter++;
        return String.format("D-%03d", idCounter);
    }

    public String getId() {
        return id;
    }

    public static void setIdCounter(int maxId) {
        idCounter = maxId;
    }

    public String getSpecialization() {
        return specialization;
    }
}
