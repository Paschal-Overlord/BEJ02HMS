package Access;

public class AccessPermissions {
    private boolean canAccessPatientInfo;
    private boolean canAccessBilling;
    private boolean canAccessInventory;
    private boolean canAccessLabTests;
    private boolean canAccessMedicalRecords;
    private boolean canAccessRegisterUser;

    public AccessPermissions(boolean canAccessPatientInfo, boolean canAccessBilling, boolean canAccessInventory,
                             boolean canAccessLabTests, boolean canAccessMedicalRecords, boolean canAccessRegisterUser) {
        this.canAccessPatientInfo = canAccessPatientInfo;
        this.canAccessBilling = canAccessBilling;
        this.canAccessInventory = canAccessInventory;
        this.canAccessLabTests = canAccessLabTests;
        this.canAccessMedicalRecords = canAccessMedicalRecords;
        this.canAccessRegisterUser = canAccessRegisterUser;
    }

    public boolean canAccessPatientInfo() {
        return canAccessPatientInfo;
    }

    public boolean canAccessBilling() {
        return canAccessBilling;
    }

    public boolean canAccessInventory() {
        return canAccessInventory;
    }

    public boolean canAccessLabTests() {
        return canAccessLabTests;
    }

    public boolean canAccessMedicalRecords() {
        return canAccessMedicalRecords;
    }

    public boolean canAccessRegisterUser() {
        return canAccessRegisterUser;
    }
}
