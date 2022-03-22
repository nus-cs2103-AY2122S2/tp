package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME_AMY_DIFF_TIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATETIME_OTHER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DESCRIPTION_OTHER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_OTHER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT_AMY_BIRTHDAY;
import static seedu.address.testutil.TypicalEvents.EVENT_NO_DESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getFriendNames().remove(0));
    }

    @Test
    void areFriendNamesValid() {
        // Not going to write a test for this for the time being because this might change.
    }

    @Test
    void isSameEvent() {
        // same object -> returns true
        assertTrue(EVENT_AMY_BIRTHDAY.isSameEvent(EVENT_AMY_BIRTHDAY));

        // null -> returns false
        assertFalse(EVENT_AMY_BIRTHDAY.isSameEvent(null));

        // same name and date, all other attributes different -> returns true
        Event editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY).withNames(VALID_NAME_BOB)
                .withDescription(VALID_EVENT_DESCRIPTION).build();
        assertTrue(editedEvent.isSameEvent(EVENT_AMY_BIRTHDAY));

        // different name, all other attributes same -> returns false
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY).withName(VALID_EVENT_NAME_OTHER).build();
        assertFalse(EVENT_AMY_BIRTHDAY.isSameEvent(editedEvent));

        // different date, all other attributes same -> returns false
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY)
                .withDateTime(VALID_EVENT_DATETIME_OTHER).build();
        assertFalse(EVENT_AMY_BIRTHDAY.isSameEvent(editedEvent));

        // different time, all other attributes same -> returns true
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY)
                .withDateTime(VALID_EVENT_DATETIME_AMY_DIFF_TIME).build();
        assertTrue(EVENT_AMY_BIRTHDAY.isSameEvent(editedEvent));
    }

    @Test
    void testEquals() {
        // same values -> returns true
        Event eventCopy = new EventBuilder(EVENT_AMY_BIRTHDAY).build();
        assertEquals(EVENT_AMY_BIRTHDAY, eventCopy);

        // same object -> returns true
        assertTrue(EVENT_AMY_BIRTHDAY.isSameEvent(EVENT_AMY_BIRTHDAY));

        // null -> returns false
        assertFalse(EVENT_AMY_BIRTHDAY.isSameEvent(null));

        // different type -> returns false
        assertNotEquals(5, EVENT_AMY_BIRTHDAY);

        // different event -> returns false
        assertNotEquals(EVENT_AMY_BIRTHDAY, EVENT_NO_DESCRIPTION);

        // different name -> returns false
        Event editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY)
                .withName(VALID_EVENT_NAME_OTHER).build();
        assertNotEquals(EVENT_AMY_BIRTHDAY, editedEvent);

        // different datetime -> returns false
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY)
                .withDateTime(VALID_EVENT_DATETIME_AMY_DIFF_TIME).build();
        assertNotEquals(EVENT_AMY_BIRTHDAY, editedEvent);

        // different description -> returns false
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY)
                .withDescription(VALID_EVENT_DESCRIPTION_OTHER).build();
        assertNotEquals(EVENT_AMY_BIRTHDAY, editedEvent);

        // different friends -> returns false
        editedEvent = new EventBuilder(EVENT_AMY_BIRTHDAY).withNames(VALID_NAME_BOB).build();
        assertNotEquals(EVENT_AMY_BIRTHDAY, editedEvent);
    }

    @Test
    void testOrder() {
        Event firstEvent = new EventBuilder().withName("DateTime test").withDateTime("14-04-2000 0000").build();
        Event secondEvent = new EventBuilder().withName("DateTime test").withDateTime("14-04-2000 0001").build();
        Event thirdEvent = new EventBuilder().withName("DateTime test").withDateTime("15-04-2000 0000").build();
        Event fourthEvent = new EventBuilder().withName("DateTime test").withDateTime("14-05-2000 0000").build();
        Event fifthEvent = new EventBuilder().withName("DateTime test").withDateTime("14-05-2001 0000").build();
        Event equalsFirstEvent = new EventBuilder().withName("DateTime test").withDateTime("14-04-2000 0000").build();

        assertTrue(firstEvent.compareTo(secondEvent) < 0);
        assertTrue(firstEvent.compareTo(thirdEvent) < 0);
        assertTrue(firstEvent.compareTo(fourthEvent) < 0);
        assertTrue(firstEvent.compareTo(fifthEvent) < 0);
        assertTrue(firstEvent.compareTo(equalsFirstEvent) == 0);
        assertTrue(secondEvent.compareTo(firstEvent) > 0);
        assertTrue(fifthEvent.compareTo(fourthEvent) > 0);

    }

}
