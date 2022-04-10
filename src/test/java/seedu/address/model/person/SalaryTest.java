package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SalaryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Salary(null));
    }

    @Test
    public void constructor_invalidSalary_throwsIllegalArgumentException() {
        String invalidSalary = "a";
        assertThrows(IllegalArgumentException.class, () -> new Salary(invalidSalary));
    }

    @Test
    public void constructorSalary_defaultValueCheck_success() {
        // Ensures this constructor always uses the default salary value
        assertEquals(Salary.DEFAULT_VALUE, new Salary().value);
    }

    @Test
    public void isValidSalary() {
        // Null
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // Invalid
        assertFalse(Salary.isValidSalary("-1"));    // Negative value
        assertFalse(Salary.isValidSalary(""));      // Empty string
        assertFalse(Salary.isValidSalary(" "));     // Space
        assertFalse(Salary.isValidSalary("a"));     // non-integer
        assertFalse(Salary.isValidSalary("!"));     // non-integer special char

        // Valid
        assertTrue(Salary.isValidSalary("0"));
        assertTrue(Salary.isValidSalary("1"));
        assertTrue(Salary.isValidSalary("100"));
        assertTrue(Salary.isValidSalary("5000"));

        // null salary
        assertThrows(NullPointerException.class, () -> Salary.isValidSalary(null));

        // salary with more than 15 digits
        assertFalse(Salary.isValidSalary("1234567890123456"));

        // salary with 15 digits
        assertTrue(Salary.isValidSalary("123456789012345"));

        // salary with digits and non digits
        assertFalse(Salary.isValidSalary("12345ABCD"));

        // salary with digits and special characters
        assertFalse(Salary.isValidSalary("1234*!"));
    }

    @Test
    public void testCompare() {
        Salary highSalary = new Salary("5000");
        Salary highSalaryClone = new Salary("5000");
        Salary lowSalary = new Salary("100");
        int expectedForSameSalary = 0;

        assertEquals(expectedForSameSalary, highSalary.compare(highSalary));
        assertEquals(expectedForSameSalary, highSalary.compare(highSalaryClone));
        assertTrue(lowSalary.compare(highSalary) > 0);  // when lowSalary < HighSalary
        assertTrue(highSalary.compare(lowSalary) < 0);  // when highSalary > lowSalary
    }

    @Test
    public void toString_outputCheck_success() {
        Salary validSalary = new Salary("5000");
        String expected = "5000";
        assertEquals(expected, validSalary.toString());
    }
}
