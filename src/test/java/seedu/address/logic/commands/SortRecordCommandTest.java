package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SortRecordCommandTest {
    @Test
    public void equals() {
        String firstSortOption = "firstOption";
        String secondSortOption = "secondOption";

        SortRecordCommand firstSortRecordCommand = new SortRecordCommand(firstSortOption);
        SortRecordCommand secondSortRecordCommand = new SortRecordCommand(secondSortOption);

        // same object -> returns true
        assertTrue(firstSortRecordCommand.equals(firstSortRecordCommand));

        // same values -> returns true
        SortRecordCommand firstSortRecordCommandCopy = new SortRecordCommand(firstSortOption);
        assertTrue(firstSortRecordCommand.equals(firstSortRecordCommandCopy));

        // different types -> returns false
        assertFalse(firstSortRecordCommand.equals(1));

        // null -> returns false
        assertFalse(firstSortRecordCommand.equals(null));

        // different person -> returns false
        assertFalse(firstSortRecordCommand.equals(secondSortRecordCommand));
    }
}
