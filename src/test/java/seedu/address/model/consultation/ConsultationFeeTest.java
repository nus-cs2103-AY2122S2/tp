package seedu.address.model.consultation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConsultationFeeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConsultationFee(null));
    }

    @Test
    public void constructor_invalidConsultationFee_throwsIllegalArgumentException() {
        String invalidConsultationFee = "";
        assertThrows(IllegalArgumentException.class, () -> new ConsultationFee(invalidConsultationFee));
    }

    @Test
    public void isValidFee() {
        // null consultation fee
        assertThrows(NullPointerException.class, () -> ConsultationFee.isValidFee(null));

        // invalid consultation fee
        assertFalse(ConsultationFee.isValidFee("")); // empty string
        assertFalse(ConsultationFee.isValidFee(" ")); // space only
        assertFalse(ConsultationFee.isValidFee("54.")); // missing decimal places
        assertFalse(ConsultationFee.isValidFee("54.0")); // wrong decimal places
        assertFalse(ConsultationFee.isValidFee("54.000")); // wrong decimal places
        assertFalse(ConsultationFee.isValidFee("54.00.00")); // incorrect decimal point
        assertFalse(ConsultationFee.isValidFee("Lorem ipsum")); // non-number string

        // valid consultation fee
        assertTrue(ConsultationFee.isValidFee("9")); // 1 digit dollars
        assertTrue(ConsultationFee.isValidFee("9.00")); // 1 digit dollars w/ redundant 2 decimal places
        assertTrue(ConsultationFee.isValidFee("9.50")); // 1 digit dollars w/ needed 2 decimal places
        assertTrue(ConsultationFee.isValidFee("54")); // 2 digit dollars
        assertTrue(ConsultationFee.isValidFee("54.00")); // 2 digit dollars w/ redundant 2 decimal places
        assertTrue(ConsultationFee.isValidFee("54.50")); // 2 digit dollars w/ needed 2 decimal places
        assertTrue(ConsultationFee.isValidFee("5000")); // multiple digit dollars
        assertTrue(ConsultationFee.isValidFee("5000000000.00")); // multiple digit dollars w/ redundant 2 decimal places
        assertTrue(ConsultationFee.isValidFee("5000000000.50")); // multiple digit dollars w/ needed 2 decimal places
    }
}
