package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.NameContainsKeywordsPredicate;
import seedu.contax.model.appointment.PersonNameContainsKeywordsPredicate;
import seedu.contax.testutil.AppointmentBuilder;


public class FindAppointmentCommandTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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
    public void personName_equals() {
        PersonNameContainsKeywordsPredicate firstPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("client1"));
        PersonNameContainsKeywordsPredicate secondPredicate =
                new PersonNameContainsKeywordsPredicate(Collections.singletonList("client2"));

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

        // different keyword -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nonMatchingNameKeywords_noAppointmentFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0),
                GuiListContentType.APPOINTMENT);
        model.addAppointment(new AppointmentBuilder(APPOINTMENT_ALICE).build());
        expectedModel.addAppointment(new AppointmentBuilder(APPOINTMENT_ALICE).build());
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("playing", "baby"));
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_nonMatchingPersonNameKeywords_noAppointmentFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0),
                GuiListContentType.APPOINTMENT);
        model.addAppointment(new AppointmentBuilder(APPOINTMENT_ALICE).build());
        expectedModel.addAppointment(new AppointmentBuilder(APPOINTMENT_ALICE).build());
        PersonNameContainsKeywordsPredicate predicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("bob", "charlie"));
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_matchingPersonNameKeywords_AppointmentFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Appointment a1 = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        model.addAppointment(a1);
        PersonNameContainsKeywordsPredicate predicate = new PersonNameContainsKeywordsPredicate(Arrays.asList("Alice"));;
        model.updateFilteredAppointmentList(predicate);
        assertEquals(Arrays.asList(a1), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_matchingNameKeywords_AppointmentFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Appointment a1 = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        model.addAppointment(a1);
        System.out.println(a1.getName());
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("first", "meeting"));;
        model.updateFilteredAppointmentList(predicate);
        assertEquals(Arrays.asList(a1), model.getFilteredAppointmentList());
    }



}
