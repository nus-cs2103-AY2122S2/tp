package seedu.address.model.job;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class SalaryTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null, null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidStartSalary = "";
        String invalidEndSalary = "";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidStartSalary, invalidEndSalary));
    }

    @Test
    public void isValidSalary() {
        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(
                null, null, null));

        // invalid Salary
        assertFalse(Salary.isValidSalary("", "", "-")); // empty string
        assertFalse(Salary.isValidSalary(" ", " ", " - ")); // spaces only
        assertFalse(Salary.isValidSalary(
                "^", "%", "^-%")); // only non-alphanumeric characters other than "-"
        assertFalse(Salary.isValidSalary("4000*", "5000#",
                "4000*-5000#")); // contains non-alphanumeric characters other than "-"
        assertFalse(Salary.isValidSalary("-", "-",
                "---")); // only "-"
        assertFalse(Salary.isValidSalary("5000", "4000",
                "5000-4000")); // starting salary more than end salary
        assertFalse(Salary.isValidSalary("abc", "def",
                "abc-def")); // only alphabets
        assertFalse(Salary.isValidSalary("abc", "4000",
                "abc-4000")); // only starting salary alphabets
        assertFalse(Salary.isValidSalary("5000", "abc",
                "5000-abc")); // only end salary alphabets
        assertFalse(Salary.isValidSalary("-4000", "-3000",
                "-4000-5000")); // only end salary alphabets
        assertFalse(Salary.isValidSalary("02000", "4000",
                "02000-4000")); // salary starts with 0
        assertFalse(Salary.isValidSalary("1234567890", "1234567899",
                "1234567890-1234567890")); // salary more than 9 digits

        // valid Salary
        assertTrue(Salary.isValidSalary(
                "4000", "5000", "4000-5000")); // starting salary less than end salary
        assertTrue(Salary.isValidSalary(
                "3000", "3000", "3000-3000")); // starting salary equal to end salary
        assertTrue(Salary.isValidSalary(
                "3000", "4000", "3000 - 4000")); // space in salary
        assertTrue(Salary.isValidSalary(
                " 3000  -  4000  ")); // any number of spaces between salary range
        assertTrue(Salary.isValidSalary(
                " 123456789  -  123456789  ")); // any number of spaces between salary range
    }
}
