package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COLLEAGUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_COMPANION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FAMILY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
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

class AddTagCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_oneNonExistingTagSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson =
                new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE,
                        VALID_TAG_FRIEND, VALID_TAG_COMPANION).build();

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_APPEND_TAG_SUCCESS, tagsToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneNonExistingTagDifferentCapitalizationSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE,
                VALID_TAG_FRIEND, VALID_TAG_COMPANION).build();

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION.toUpperCase()));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_APPEND_TAG_SUCCESS, tagsToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleNonExistingTagSpecifiedUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_COLLEAGUE, VALID_TAG_WIFE,
                VALID_TAG_FRIEND, VALID_TAG_COMPANION, VALID_TAG_FAMILY).build();

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));
        tagsToAdd.add(new Tag(VALID_TAG_FAMILY));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_APPEND_TAG_SUCCESS, tagsToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noTagSpecifiedUnfilteredList_success() {
        Person editedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Set<Tag> tagsToAdd = new HashSet<>();

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_APPEND_TAG_SUCCESS, tagsToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_oneExistingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FRIEND));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_DUPLICATE_TAG, tagsToAdd);

        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_twoExistingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FRIEND));
        tagsToAdd.add(new Tag(VALID_TAG_COLLEAGUE));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_DUPLICATE_TAG, tagsToAdd);

        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneExistingTagFollowedByOneNonExistingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FRIEND));
        tagsToAdd.add(new Tag(VALID_TAG_FAMILY));

        Set<Tag> existingTags = new HashSet<>();
        existingTags.add(new Tag(VALID_TAG_FRIEND));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_DUPLICATE_TAG, existingTags);

        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneNonExistingTagFollowedByOneExistingTagSpecifiedUnfilteredList_failure() {
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_FAMILY));
        tagsToAdd.add(new Tag(VALID_TAG_FRIEND));

        Set<Tag> existingTags = new HashSet<>();
        existingTags.add(new Tag(VALID_TAG_FRIEND));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_DUPLICATE_TAG, existingTags);

        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_oneNonExistingTagSpecifiedFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withTags(VALID_TAG_FRIEND, VALID_TAG_COLLEAGUE,
                VALID_TAG_WIFE, VALID_TAG_COMPANION).build();

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_APPEND_TAG_SUCCESS, tagsToAdd);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
        expectedModel.saveAddressBookState();

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, tagsToAdd);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, tagsToAdd);

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag(VALID_TAG_COMPANION));

        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_PERSON, tagsToAdd);

        // same values -> returns true
        Set<Tag> copyTagsToAdd = new HashSet<>(tagsToAdd);
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_PERSON, copyTagsToAdd);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new AddTagCommand(INDEX_SECOND_PERSON, tagsToAdd));

        // different tags to add -> returns false
        assertNotEquals(standardCommand, new AddTagCommand(INDEX_SECOND_PERSON, new HashSet<>()));
    }
}
