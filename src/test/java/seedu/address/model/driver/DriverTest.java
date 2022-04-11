package seedu.address.model.driver;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAREL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.TypicalAddressBook.ABEL;
import static seedu.address.testutil.TypicalAddressBook.BAREL;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DriverBuilder;

public class DriverTest {


    @Test
    public void isSameDriver() {
        // same object -> returns true
        assertTrue(ABEL.isSameDriver(ABEL));

        // null -> returns false
        assertFalse(ABEL.isSameDriver(null));

        // same name and number, all other attributes different -> returns true
        Driver editedAbel =
                new DriverBuilder(ABEL)
                        .withStatus(DriverStatus.FREE).build();
        assertTrue(ABEL.isSameDriver(editedAbel));

        // different name and number, all other attributes same -> returns false
        editedAbel = new DriverBuilder(ABEL).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ABEL.isSameDriver(editedAbel));

        // name differs in case, different number, all other attributes same -> returns false
        Driver editedBarel = new DriverBuilder(BAREL).withName(VALID_NAME_BAREL.toLowerCase())
                .withPhone(VALID_PHONE_AMY).build();
        assertTrue(BAREL.isSameDriver(editedBarel));

        // name has trailing spaces, different number, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BAREL + " ";
        editedBarel = new DriverBuilder(BAREL).withName(nameWithTrailingSpaces).withPhone(VALID_PHONE_AMY).build();
        assertFalse(BAREL.isSameDriver(editedBarel));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Driver abelCopy = new DriverBuilder(ABEL).build();
        assertTrue(ABEL.equals(abelCopy));

        // same object -> returns true
        assertTrue(ABEL.equals(ABEL));

        // null -> returns false
        assertFalse(ABEL.equals(null));

        // different type -> returns false
        assertFalse(ABEL.equals(5));

        // different driver -> returns false
        assertFalse(ABEL.equals(BAREL));

        // different name -> returns false
        Driver editedAbel = new DriverBuilder(ABEL).withName(VALID_NAME_BOB).build();
        assertFalse(ABEL.equals(editedAbel));

        // different phone -> returns false
        editedAbel = new DriverBuilder(ABEL).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ABEL.equals(editedAbel));

    }
}
