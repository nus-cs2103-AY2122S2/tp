package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DrugNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DrugName(null));
    }

    @Test
    public void constructor_invalidDrugName_throwsIllegalArgumentException() {
        String invalidDrugName = "";
        assertThrows(IllegalArgumentException.class, () -> new DrugName(invalidDrugName));
    }

    @Test
    public void isValidDrugName() {
        // null name
        assertThrows(NullPointerException.class, () -> DrugName.isValidName(null));

        // invalid drug name
        assertFalse(DrugName.isValidName("")); // empty string
        assertFalse(DrugName.isValidName(" ")); // spaces only
        assertFalse(DrugName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(DrugName.isValidName("Adderall*")); // contains non-alphanumeric characters

        // valid drug name
        assertTrue(DrugName.isValidName("Adderall")); // alphabets only
        assertTrue(DrugName.isValidName("12345")); // numbers only
        assertTrue(DrugName.isValidName("Adderall2")); // alphanumeric characters
        assertTrue(DrugName.isValidName("Talimogene Laherparepvec")); // long names
    }
}
