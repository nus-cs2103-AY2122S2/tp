package seedu.address.model.charge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHARGEDATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CHARGEDATE_BOB;

import org.junit.jupiter.api.Test;

public class ChargeDateTest {

    private ChargeDate chargeDateAmy = new ChargeDate(VALID_CHARGEDATE_AMY);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(chargeDateAmy.equals(chargeDateAmy));

        // null -> returns false
        assertFalse(chargeDateAmy.equals(null));

        // same charge date -> returns true
        ChargeDate chargeDateAmyCopy = new ChargeDate(VALID_CHARGEDATE_AMY);
        assertTrue(chargeDateAmy.equals(chargeDateAmyCopy));

        // different charge date -> returns false
        ChargeDate chargeDateBob = new ChargeDate(VALID_CHARGEDATE_BOB);
        assertFalse(chargeDateAmy.equals(chargeDateBob));

        // charge date has trailing spaces -> returns true
        String chargeDateWithTrailingSpaces = VALID_CHARGEDATE_AMY + " ";
        ChargeDate amyTrailing = new ChargeDate(chargeDateWithTrailingSpaces);
        assertTrue(chargeDateAmy.equals(amyTrailing));

    }
}
