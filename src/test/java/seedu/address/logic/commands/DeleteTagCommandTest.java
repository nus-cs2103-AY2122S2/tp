package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMPANION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WIFE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteTagCommand}.
 */
public class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_oneExistingTagSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE).build();

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneExistingTagDifferentCapitalizationSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE).build();

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND.toUpperCase()));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleExistingTagSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_WIFE).build();

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));
        tagsToDelete.add(new Tag(VALID_TAG_COLLEAGUE));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noTagSpecifiedUnfilteredList_success() {
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> tagsToDelete = new HashSet<>();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneMissingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_HUSBAND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_MISSING_TAG, tagsToDelete);

        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_twoMissingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_HUSBAND));
        tagsToDelete.add(new Tag(VALID_TAG_COMPANION));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_MISSING_TAG, tagsToDelete);

        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneMissingTagFollowedByOneExistingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_HUSBAND));
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        Set<Tag> missingTags = new HashSet<>();
        missingTags.add(new Tag(VALID_TAG_HUSBAND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_MISSING_TAG, missingTags);

        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneExistingTagFollowedByOneMissingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));
        tagsToDelete.add(new Tag(VALID_TAG_HUSBAND));

        Set<Tag> missingTags = new HashSet<>();
        missingTags.add(new Tag(VALID_TAG_HUSBAND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_MISSING_TAG, missingTags);

        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneExistingTagSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE).build();

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);
        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagsToDelete);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, tagsToDelete);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, tagsToDelete);

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(new Tag(VALID_TAG_FRIEND));

        final DeleteTagCommand standardCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tagsToDelete);

        // same values -> returns true
        Set<Tag> copyTagsToDelete = new HashSet<>(tagsToDelete);
        DeleteTagCommand commandWithSameValues = new DeleteTagCommand(INDEX_FIRST_PERSON, copyTagsToDelete);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_SECOND_PERSON, tagsToDelete)));

        // different tags to delete -> returns false
        assertFalse(standardCommand.equals(new DeleteTagCommand(INDEX_SECOND_PERSON, new HashSet<>())));
    }

}
