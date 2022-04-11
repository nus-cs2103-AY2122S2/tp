package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InsurancePackageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new InsurancePackage(null));
    }

    @Test
    public void constructor_invalidInsurancePackage_throwsIllegalArgumentException() {
        String invalidInsurancePackage = "";
        assertThrows(IllegalArgumentException.class, () -> new InsurancePackage(invalidInsurancePackage));
    }

    @Test
    public void isValidInsurancePackage() {
        // null address
        assertThrows(NullPointerException.class, () -> InsurancePackage.isValidInsurancePackage(null));

        // invalid addresses
        assertFalse(InsurancePackage.isValidInsurancePackage("")); // empty string
        assertFalse(InsurancePackage.isValidInsurancePackage(" ")); // spaces only

        // valid addresses
        assertTrue(InsurancePackage.isValidInsurancePackage("Golden Package Premium Plus"));
        assertTrue(InsurancePackage.isValidInsurancePackage("3")); // single character, number
        assertTrue(InsurancePackage.isValidInsurancePackage("Plus 123! :) #//")); // string with special characters
    }
}
