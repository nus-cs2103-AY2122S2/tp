package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT3;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.DescriptionContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void equals() {
        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("first"));
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("second"));

        FindAppointmentCommand findFirstCommand = new FindAppointmentCommand(firstPredicate);
        FindAppointmentCommand findSecondCommand = new FindAppointmentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindAppointmentCommand findFirstCommandCopy = new FindAppointmentCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW, 0);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }


    @Test
    public void execute_multipleKeywords_multipleAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENT_LISTED_OVERVIEW, 3);
        DescriptionContainsKeywordsPredicate predicate = preparePredicate("Health Car Renewal");
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT1 , APPOINTMENT2, APPOINTMENT3), model.getFilteredAppointmentList());
    }

    /**
     * Parses {@code userInput} into a {@code DescriptionContainsKeywordsPredicate}.
     */
    private DescriptionContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
