package seedu.address.model.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RequirementTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Requirement(null));
    }

    @Test
    public void constructor_invalidRequirement_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Requirement(""));
        assertThrows(IllegalArgumentException.class, () -> new Requirement(" "));
    }

    @Test
    public void isValidRequirement() {
        // null requirement
        assertThrows(NullPointerException.class, () -> Requirement.isValidRequirementText(null));

        // invalid requirements
        assertFalse(Requirement.isValidRequirementText("")); // empty string
        assertFalse(Requirement.isValidRequirementText(" ")); // only whitespace
        assertFalse(Requirement.isValidRequirementText("*!@#")); // special characters only
        assertFalse(Requirement.isValidRequirementText(" asdas")); // start with whitespaces
        assertFalse(Requirement.isValidRequirementText(
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")); // more than 20 characters

        // valid requirements
        assertTrue(Requirement.isValidRequirementText("2")); // only numbers
        assertTrue(Requirement.isValidRequirementText("abc")); // only alphabets
        assertTrue(Requirement.isValidRequirementText("5.0 GPA")); // alphanumeric with special characters
        assertTrue(Requirement.isValidRequirementText("HTML & CSS"));
        assertTrue(Requirement.isValidRequirementText(">=5 years of experience")); // start with special characters
        assertTrue(Requirement.isValidRequirementText("Master at Golang")); // upper and lower cases
    }
}
