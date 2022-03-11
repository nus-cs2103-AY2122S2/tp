package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SIZE_BOB;
import static seedu.address.testutil.TypicalProperties.PROPERTY_ALICE;
import static seedu.address.testutil.TypicalProperties.PROPERTY_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PropertyBuilder;

public class PropertyTest {

    @Test
    public void isSameProperty() {
        // same object -> returns true
        assertTrue(PROPERTY_ALICE.isSameProperty(PROPERTY_ALICE));

        // null -> returns false
        assertFalse(PROPERTY_ALICE.isSameProperty(null));

        // same address, all other attributes different -> returns true
        Property editedPropertyAlice = new PropertyBuilder(PROPERTY_ALICE).withRegion(VALID_REGION_BOB)
                .withSize(VALID_SIZE_BOB).withPrice(VALID_PRICE_BOB).build();
        assertTrue(PROPERTY_ALICE.isSameProperty(editedPropertyAlice));

        // different address, all other attributes same -> returns false
        editedPropertyAlice = new PropertyBuilder(PROPERTY_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(PROPERTY_ALICE.isSameProperty(editedPropertyAlice));

        // address differs in case, all other attributes same -> returns false
        Property editedPropertyBob =
                new PropertyBuilder(PROPERTY_BOB).withAddress(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(PROPERTY_BOB.isSameProperty(editedPropertyBob));

        // address has trailing spaces, all other attributes same -> returns false
        String addressWithTrailingSpaces = VALID_ADDRESS_BOB + " ";
        editedPropertyBob = new PropertyBuilder(PROPERTY_BOB).withAddress(addressWithTrailingSpaces).build();
        assertFalse(PROPERTY_BOB.isSameProperty(editedPropertyBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Property propertyAliceCopy = new PropertyBuilder(PROPERTY_ALICE).build();
        assertTrue(PROPERTY_ALICE.equals(propertyAliceCopy));

        // same object -> returns true
        assertTrue(PROPERTY_ALICE.equals(PROPERTY_ALICE));

        // null -> returns false
        assertFalse(PROPERTY_ALICE.equals(null));

        // different type -> returns false
        assertFalse(PROPERTY_ALICE.equals(5));

        // different property -> returns false
        assertFalse(PROPERTY_ALICE.equals(PROPERTY_BOB));

        // different address -> returns false
        Property editedAliceProperty = new PropertyBuilder(PROPERTY_ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(PROPERTY_ALICE.equals(editedAliceProperty));

        // different region -> returns false
        editedAliceProperty = new PropertyBuilder(PROPERTY_ALICE).withRegion(VALID_REGION_BOB).build();
        assertFalse(PROPERTY_ALICE.equals(editedAliceProperty));

        // different size -> returns false
        editedAliceProperty = new PropertyBuilder(PROPERTY_ALICE).withSize(VALID_SIZE_BOB).build();
        assertFalse(PROPERTY_ALICE.equals(editedAliceProperty));

        // different price -> returns false
        editedAliceProperty = new PropertyBuilder(PROPERTY_ALICE).withAddress(VALID_PRICE_BOB).build();
        assertFalse(PROPERTY_ALICE.equals(editedAliceProperty));
    }
}
