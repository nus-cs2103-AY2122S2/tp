package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessInDetailedViewMode;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Deadline;
import seedu.address.model.person.Person;
import seedu.address.model.util.SampleDataUtil;
import seedu.address.testutil.PersonBuilder;

public class DeadlineCommandTest {
    private static final String[] VALID_DEADLINE = new String[]{"a 1/1/2023"};
    private static final String[] INVALID_DEADLINE = new String[]{"a 1/13/2023"};

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addValidDeadlineInViewMode_success() {
        Person firstPerson = model.getSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(firstPerson);
        Person personToAddDeadline = model.getDetailedContactViewPerson();
        Person editedPerson = new PersonBuilder(personToAddDeadline).withDeadlines(VALID_DEADLINE).build();

        DeadlineCommand deadlineCommand = new DeadlineCommand(SampleDataUtil.getDeadlineList(VALID_DEADLINE));

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_ADD_DEADLINE_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        assertCommandSuccessInDetailedViewMode(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addValidDeadlineUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDeadlines(VALID_DEADLINE).build();

        DeadlineCommand deadlineCommand = new DeadlineCommand(INDEX_FIRST_PERSON,
                SampleDataUtil.getDeadlineList(VALID_DEADLINE));

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_ADD_DEADLINE_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addValidDeadlineFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withDeadlines(VALID_DEADLINE).build();

        DeadlineCommand deadlineCommand = new DeadlineCommand(INDEX_FIRST_PERSON,
                SampleDataUtil.getDeadlineList(VALID_DEADLINE));

        String expectedMessage = String.format(DeadlineCommand.MESSAGE_ADD_DEADLINE_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(deadlineCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws ParseException {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeadlineCommand deadlineCommand = new DeadlineCommand(outOfBoundIndex,
                ParserUtil.parseDeadlines(Arrays.asList(VALID_DEADLINE)));

        assertCommandFailure(deadlineCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() throws ParseException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());
        DeadlineCommand deadlineCommand = new DeadlineCommand(outOfBoundIndex,
                ParserUtil.parseDeadlines(Arrays.asList(VALID_DEADLINE)));

        assertCommandFailure(deadlineCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_addInvalidDeadline_throwsParseException() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new PersonBuilder(firstPerson).withDeadlines(INVALID_DEADLINE).build());
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    public void executeInDetailedView_validDeadline_success() throws ParseException {
        Person personToEdit = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToEdit);

        Person editedPerson = new PersonBuilder(personToEdit).withDeadlines(VALID_DEADLINE).build();
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        DeadlineCommand deadlineCommand = new DeadlineCommand(ParserUtil.parseDeadlines(
                Arrays.asList(VALID_DEADLINE)));
        CommandResult expectedResult = new CommandResult(String.format(DeadlineCommand.MESSAGE_ADD_DEADLINE_SUCCESS,
                personToEdit), CommandResult.SpecialCommandResult.DETAILED_VIEW);
        assertDetailedViewCommandSuccess(deadlineCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void equals() throws ParseException {
        DeadlineCommand deadlineFirstCommand = new DeadlineCommand(INDEX_FIRST_PERSON,
                ParserUtil.parseDeadlines(Arrays.asList(VALID_DEADLINE)));
        DeadlineCommand deadlineSecondCommand = new DeadlineCommand(INDEX_SECOND_PERSON,
                ParserUtil.parseDeadlines(Arrays.asList(VALID_DEADLINE)));

        // same object -> returns true
        assertEquals(deadlineFirstCommand, deadlineFirstCommand);

        // same values -> returns true
        DeadlineCommand deadlineFirstCommandCopy = new DeadlineCommand(INDEX_FIRST_PERSON,
                ParserUtil.parseDeadlines(Arrays.asList(VALID_DEADLINE)));
        assertEquals(deadlineFirstCommand, deadlineFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deadlineFirstCommand);

        // null -> returns false
        assertNotNull(deadlineFirstCommand);

        // different person -> returns false
        assertNotEquals(deadlineFirstCommand, deadlineSecondCommand);

        // other is null -> returns false
        assertNotEquals(deadlineFirstCommand, null);

    }
}
