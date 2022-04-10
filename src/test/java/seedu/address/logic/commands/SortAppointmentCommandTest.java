package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortAppointmentCommandTest {
    @Test
    public void equals() {
        String firstSortOption = "firstOption";
        String secondSortOption = "secondOption";

        SortAppointmentCommand firstSortAppointmentCommand = new SortAppointmentCommand(firstSortOption);
        SortAppointmentCommand secondSortAppointmentCommand = new SortAppointmentCommand(secondSortOption);

        // same object -> returns true
        assertTrue(firstSortAppointmentCommand.equals(firstSortAppointmentCommand));

        // same values -> returns true
        SortAppointmentCommand firstSortAppointmentCommandCopy = new SortAppointmentCommand(firstSortOption);
        assertTrue(firstSortAppointmentCommand.equals(firstSortAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(firstSortAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortAppointmentCommand.equals(null));

        // different person -> returns false
        assertFalse(firstSortAppointmentCommand.equals(secondSortAppointmentCommand));
    }
}
