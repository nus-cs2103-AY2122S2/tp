package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertDetailedViewCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_COLLEAGUES;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_OWESMONEY;
import static seedu.address.testutil.TypicalTags.VALID_TAGNAME_TEST;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWESMONEY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnassignTagCommand}.
 */
public class UnassignTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexTagExistsAndPersonTagged_success() {
        Person personToRemoveTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertTrue(personToRemoveTag.getTags().contains(VALID_TAG_FRIENDS));
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Tag removingTag = VALID_TAG_FRIENDS;
        Person editedPerson = new PersonBuilder(personToRemoveTag).withoutTag(removingTag).build();
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_SUCCESS, editedPerson);
        expectedModel.setPerson(personToRemoveTag, editedPerson);

        assertCommandSuccess(unassignTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTagExistsAndPersonNotTagged_throwsCommandException() {
        Person personToRemoveTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertFalse(personToRemoveTag.getTags().contains(VALID_TAG_OWESMONEY));
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_OWESMONEY);

        assertCommandFailure(unassignTagCommand, model, UnassignTagCommand.MESSAGE_NOT_TAGGED);
    }

    @Test
    public void execute_validIndexTagDoesNotExist_throwsCommandException() {
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);

        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexTagExists_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(outOfBoundIndex, VALID_TAGNAME_FRIENDS);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexTagDoesNotExist_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(outOfBoundIndex, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(UnassignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);
        assertCommandFailure(unassignTagCommand, model, expectedMessage);
    }

    @Test
    public void executeInDetailedView_personTagged_success() {
        Person personToRemoveTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToRemoveTag);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Tag removingTag = VALID_TAG_FRIENDS;
        Person editedPerson = new PersonBuilder(personToRemoveTag).withoutTag(removingTag).build();
        expectedModel.setPerson(personToRemoveTag, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(VALID_TAGNAME_FRIENDS);
        CommandResult expectedResult = new CommandResult(String.format(UnassignTagCommand.MESSAGE_SUCCESS,
                editedPerson), CommandResult.SpecialCommandResult.DETAILED_VIEW);
        assertDetailedViewCommandSuccess(unassignTagCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void executeInDetailedView_personNotTagged_failure() {
        Person personToRemoveTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToRemoveTag);
        UnassignTagCommand unassignTagCommand = new UnassignTagCommand(VALID_TAGNAME_OWESMONEY);

        assertDetailedViewCommandFailure(unassignTagCommand, model, UnassignTagCommand.MESSAGE_NOT_TAGGED);
    }

    @Test
    public void equals() {
        UnassignTagCommand unassignTagFirstCommand = new UnassignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);
        UnassignTagCommand unassignTagSecondCommand = new UnassignTagCommand(INDEX_SECOND_PERSON,
                VALID_TAGNAME_COLLEAGUES);

        // same object -> returns true
        assertTrue(unassignTagFirstCommand.equals(unassignTagFirstCommand));

        // same values -> returns true
        UnassignTagCommand unassignTagFirstCommandCopy = new UnassignTagCommand(INDEX_FIRST_PERSON,
                VALID_TAGNAME_FRIENDS);
        assertTrue(unassignTagFirstCommand.equals(unassignTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(unassignTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unassignTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unassignTagFirstCommand.equals(unassignTagSecondCommand));
    }

}
