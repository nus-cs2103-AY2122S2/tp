package seedu.address.model.assessment;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class SimpleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SimpleName(null));
    }

    @Test
    public void constructor_invalidSimpleName_throwsIllegalArgumentException() {
        String invalidSimpleName = "";
        assertThrows(IllegalArgumentException.class, () -> new SimpleName(invalidSimpleName));
    }

    @Test
    public void isValidSimpleName() throws IOException {
        assertFalse(SimpleName.isValidSimpleName("")); //blank
        assertFalse(SimpleName.isValidSimpleName(" ")); //one space
        assertFalse(SimpleName.isValidSimpleName("A@B")); //No special characters
        assertFalse(SimpleName.isValidSimpleName("Class Participation")); //has space

        //Valid simpleNames
        assertTrue(SimpleName.isValidSimpleName("ClassParticipation"));
        assertTrue(SimpleName.isValidSimpleName("OralPresentation1"));
        assertTrue(SimpleName.isValidSimpleName("lab"));
        assertTrue(SimpleName.isValidSimpleName("lab1"));
        assertTrue(SimpleName.isValidSimpleName("LabReport"));
        assertTrue(SimpleName.isValidSimpleName("1234567890"));
    }
}
