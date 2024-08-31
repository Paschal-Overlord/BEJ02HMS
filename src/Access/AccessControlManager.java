package Access;

import Enums.AccountType;

import java.util.EnumMap;
import java.util.Map;

public class AccessControlManager {
    private static final Map<AccountType, AccessPermissions> accessControl = new EnumMap<>(AccountType.class);

    static {
        // Define access permissions for each AccountType
        accessControl.put(AccountType.DOCTOR, new AccessPermissions(true, true,
                false, true, true, false));
        accessControl.put(AccountType.PATIENT, new AccessPermissions(true, false,
                false, false, false, false));
        accessControl.put(AccountType.NURSE, new AccessPermissions(true, false,
                false, false, false, false));
        accessControl.put(AccountType.ADMIN, new AccessPermissions(true, true,
                true, true,false,true));
        accessControl.put(AccountType.LABTECH, new AccessPermissions(false, false,
                false, true,false, false));
        accessControl.put(AccountType.ACCOUNTANT, new AccessPermissions(false, true,
                false, false, false, false));
    }

    public static AccessPermissions getPermissions(AccountType accountType) {
        return accessControl.get(accountType);
    }
}
