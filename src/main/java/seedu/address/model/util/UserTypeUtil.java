package seedu.address.model.util;

import seedu.address.model.person.UserType;

/**
 * Utility methods related to Collections
 */

public class UserTypeUtil {

    private static final String BUYER = "buyer";
    private static final String SELLER = "seller";

    public static UserType createBuyer() {
        return new UserType(BUYER);
    }

    public static UserType createSeller() {
        return new UserType(SELLER);
    }
}
