package seedu.address.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IdTest {

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        int invalidId = -123;
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    void isValidId() {
        // invalid id
        assertFalse(Id.isValidId("-345")); // negative int

        // valid id
        assertTrue(Id.isValidId("345")); // positive int
    }

}
