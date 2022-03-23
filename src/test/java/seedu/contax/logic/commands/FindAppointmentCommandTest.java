package seedu.contax.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.ClientNameContainsKeywordsPredicate;
import seedu.contax.model.appointment.NameContainsKeywordsPredicate;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.testutil.AppointmentBuilder;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.contax.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.contax.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalAppointments.APPOINTMENT_ALICE;
import static seedu.contax.testutil.TypicalPersons.*;

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
    public void clientName_equals() {
        ClientNameContainsKeywordsPredicate firstPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("client1"));
        ClientNameContainsKeywordsPredicate secondPredicate =
                new ClientNameContainsKeywordsPredicate(Collections.singletonList("client2"));

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
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("bob", "charlie"));
        FindAppointmentCommand command = new FindAppointmentCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_MatchingNameKeywords_noAppointmentFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
        Appointment a1 = new AppointmentBuilder(APPOINTMENT_ALICE).build();
        model.addAppointment(a1);
        expectedModel.addAppointment(a1);
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice"));;
        expectedModel.updateFilteredAppointmentList(predicate);
        assertEquals(Arrays.asList(a1), model.getFilteredAppointmentList());
    }



}
