package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // salary with more than 15 digits
        assertFalse(Salary.isValidSalary("1234567890123456"));

        // salary with 15 digits
        assertTrue(Salary.isValidSalary("123456789012345"));

        // salary with non digits
        assertFalse(Salary.isValidSalary("12345ABCD"));

        // salary with special characters
        assertFalse(Salary.isValidSalary("1234*!"));
    }
}
