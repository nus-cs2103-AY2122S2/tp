package seedu.address.model.util;

import seedu.address.model.person.UserType;

/**
 * Utility methods related to Collections
 */

public class UserTypeUtil {

    /** Returns a "buyer" String */
    private static String isBuyer() {
        return "buyer";
    }

    /** Returns a "seller" String */
    private static String isSeller() {
        return "seller";
    }

    public static UserType createBuyer() {
        return new UserType(isBuyer());
    }

    public static UserType createSeller() {
        return new UserType(isSeller());
    }
}
