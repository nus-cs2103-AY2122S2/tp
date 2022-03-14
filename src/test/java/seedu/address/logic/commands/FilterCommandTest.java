package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.lab.Lab;
import seedu.address.model.person.lab.LabStatus;
import seedu.address.model.person.lab.StudentHasLabPredicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StudentHasLabPredicate firstPredicate =
                new StudentHasLabPredicate((new Lab("1")).thatIs(LabStatus.GRADED));
        StudentHasLabPredicate secondPredicate =
                new StudentHasLabPredicate((new Lab("1")).thatIs(LabStatus.UNSUBMITTED));
        StudentHasLabPredicate thirdPredicate =
                new StudentHasLabPredicate((new Lab("3")).thatIs(LabStatus.GRADED));
        StudentHasLabPredicate fourthPredicate =
                new StudentHasLabPredicate((new Lab("4")).thatIs(LabStatus.SUBMITTED));

        FilterCommand firstFilterCommand = new FilterCommand(firstPredicate);
        FilterCommand secondFilterCommand = new FilterCommand(secondPredicate);
        FilterCommand thirdFilterCommand = new FilterCommand(thirdPredicate);
        FilterCommand fourthFilterCommand = new FilterCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(firstFilterCommand.equals(firstFilterCommand));

        // same values -> returns true
        FilterCommand firstFilterCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(firstFilterCommand.equals(firstFilterCommandCopy));

        // same labNumber different labStatus -> returns false
        assertFalse(firstFilterCommand.equals(secondFilterCommand));

        // different labNumber same labStatus -> returns false
        assertFalse(firstFilterCommand.equals(thirdFilterCommand));

        // different labNumber different labStatus -> returns false
        assertFalse(firstFilterCommand.equals(fourthFilterCommand));

        // null -> returns false
        assertFalse(firstFilterCommand.equals(null));
    }

    @Test
    public void execute_success() {

    }
}
