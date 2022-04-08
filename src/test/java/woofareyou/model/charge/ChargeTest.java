package woofareyou.model.charge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.VALID_CHARGEAMT_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_CHARGEAMT_BOB;

import org.junit.jupiter.api.Test;


public class ChargeTest {

    private Charge chargeAmy = new Charge(VALID_CHARGEAMT_AMY);

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(chargeAmy.equals(chargeAmy));

        // null -> returns false
        assertFalse(chargeAmy.equals(null));

        // same charge amt -> returns true
        Charge amyCopy = new Charge(VALID_CHARGEAMT_AMY);
        assertTrue(chargeAmy.equals(amyCopy));

        // different charge amt -> returns false
        Charge bobCopy = new Charge(VALID_CHARGEAMT_BOB);
        assertFalse(chargeAmy.equals(bobCopy));

        // charge amt has trailing spaces -> returns true
        String chargeWithTrailingSpaces = VALID_CHARGEAMT_AMY + " ";
        Charge amyTrailing = new Charge(chargeWithTrailingSpaces);
        assertTrue(chargeAmy.equals(amyTrailing));

    }

}
