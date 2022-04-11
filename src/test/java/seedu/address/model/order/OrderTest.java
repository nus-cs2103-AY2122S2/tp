package seedu.address.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERYDATETIME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UUID_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalOrders.EMILY;
import static seedu.address.testutil.TypicalOrders.SIMON;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.OrderBuilder;

public class OrderTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Order order = new OrderBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> order.getDetails().remove(0));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order emilyCopy = new OrderBuilder(EMILY).build();
        assertTrue(EMILY.equals(emilyCopy));

        // same object -> returns true
        assertTrue(EMILY.equals(EMILY));

        // null -> returns false
        assertFalse(EMILY.equals(null));

        // different type -> returns false
        assertFalse(EMILY.equals(5));

        // different order -> returns false
        assertFalse(EMILY.equals(SIMON));

        // different uuid -> returns false
        Order editedEmily = new OrderBuilder(EMILY).withUuid(VALID_UUID_BOB).build();
        assertFalse(EMILY.equals(editedEmily));

        // different details -> returns false
        editedEmily = new OrderBuilder(EMILY).withDetails(VALID_DETAILS_BOB).build();
        assertFalse(EMILY.equals(editedEmily));

        // different remarks -> returns true
        editedEmily = new OrderBuilder(EMILY).withRemark(VALID_REMARK_BOB).build();
        assertTrue(EMILY.equals(editedEmily));

        // different deliveryDateTime -> returns false
        editedEmily = new OrderBuilder(EMILY).withDeliveryDateTime(VALID_DELIVERYDATETIME_BOB).build();
        assertFalse(EMILY.equals(editedEmily));

        // different collectionType -> returns true
        editedEmily = new OrderBuilder(EMILY).withCollectionType(CollectionType.PICKUP).build();
        assertTrue(EMILY.equals(editedEmily));


    }
}
