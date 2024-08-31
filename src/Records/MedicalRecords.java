package Records;

public class MedicalRecords {
    private String diagnosis;
    private String treatment;

    public MedicalRecords(String diagnosis, String treatment) {
        this.diagnosis = diagnosis;
        this.treatment = treatment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

}

