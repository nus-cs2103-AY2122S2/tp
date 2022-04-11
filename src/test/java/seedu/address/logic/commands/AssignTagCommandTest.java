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
 * {@code AssignTagCommand}.
 */
public class AssignTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexTagExistsAndPersonNotTagged_success() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertFalse(personToAddTag.getTags().contains(VALID_TAG_OWESMONEY));
        AssignTagCommand assignTagCommand = new AssignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_OWESMONEY);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Tag newTag = VALID_TAG_OWESMONEY;
        Person editedPerson = new PersonBuilder(personToAddTag).withNewTag(newTag).build();
        String expectedMessage = String.format(AssignTagCommand.MESSAGE_SUCCESS, editedPerson);
        expectedModel.setPerson(personToAddTag, editedPerson);

        assertCommandSuccess(assignTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTagExistsAndPersonTagged_throwsCommandException() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        assertTrue(personToAddTag.getTags().contains(VALID_TAG_FRIENDS));
        AssignTagCommand assignTagCommand = new AssignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);

        assertCommandFailure(assignTagCommand, model, AssignTagCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_validIndexTagDoesNotExist_throwsCommandException() {
        AssignTagCommand assignTagCommand = new AssignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(AssignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);

        assertCommandFailure(assignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexTagExists_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AssignTagCommand assignTagCommand = new AssignTagCommand(outOfBoundIndex, VALID_TAGNAME_FRIENDS);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(assignTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexTagDoesNotExist_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AssignTagCommand assignTagCommand = new AssignTagCommand(outOfBoundIndex, VALID_TAGNAME_TEST);
        String expectedMessage = String.format(AssignTagCommand.MESSAGE_UNKNOWN_TAG, VALID_TAGNAME_TEST);
        assertCommandFailure(assignTagCommand, model, expectedMessage);
    }

    @Test
    public void executeInDetailedView_personNotTagged_success() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToAddTag);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Tag newTag = VALID_TAG_OWESMONEY;
        Person editedPerson = new PersonBuilder(personToAddTag).withNewTag(newTag).build();
        expectedModel.setPerson(personToAddTag, editedPerson);
        expectedModel.setDetailedContactView(editedPerson);

        AssignTagCommand assignTagCommand = new AssignTagCommand(VALID_TAGNAME_OWESMONEY);
        CommandResult expectedResult = new CommandResult(String.format(AssignTagCommand.MESSAGE_SUCCESS, editedPerson),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
        assertDetailedViewCommandSuccess(assignTagCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void executeInDetailedView_personTagged_failure() {
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(personToAddTag);
        AssignTagCommand assignTagCommand = new AssignTagCommand(VALID_TAGNAME_FRIENDS);

        assertDetailedViewCommandFailure(assignTagCommand, model, AssignTagCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void equals() {
        AssignTagCommand assignTagFirstCommand = new AssignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);
        AssignTagCommand assignTagSecondCommand = new AssignTagCommand(INDEX_SECOND_PERSON, VALID_TAGNAME_COLLEAGUES);

        // same object -> returns true
        assertTrue(assignTagFirstCommand.equals(assignTagFirstCommand));

        // same values -> returns true
        AssignTagCommand assignTagFirstCommandCopy = new AssignTagCommand(INDEX_FIRST_PERSON, VALID_TAGNAME_FRIENDS);
        assertTrue(assignTagFirstCommand.equals(assignTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(assignTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(assignTagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(assignTagFirstCommand.equals(assignTagSecondCommand));
    }

}
