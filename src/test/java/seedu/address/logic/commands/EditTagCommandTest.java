package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditTagCommandTest {
    static final Tag TAG_1 = VALID_TAG_FRIEND.get(0);
    static final Tag TAG_2 = VALID_TAG_HUSBAND.get(0);

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagCommand(null,
                1, TAG_1));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditTagCommand(Index.fromOneBased(1), 1, null));
    }

    @Test
    public void execute_tagEditedByPerson_editSuccessful() throws Exception {
        Person person = new PersonBuilder().withTags(new ArrayList<>(
                List.of(TAG_1))).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);
        Tag editedTag = TAG_2;

        Person tagAddedPerson = new PersonBuilder().withTags(new ArrayList<>(
                List.of(editedTag))).build();

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new EditTagCommand(index, 1, editedTag).execute(modelManager);

        assertEquals(String.format(EditTagCommand.MESSAGE_SUCCESS, tagAddedPerson), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_indexOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(100);
        Tag editedTag = TAG_2;
        EditTagCommand editTagCommand = new EditTagCommand(index, 1, editedTag);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                editTagCommand.execute(modelManager));
    }

    @Test
    public void execute_tagNumberNotPositive_throwsException() throws Exception {
        Index index = Index.fromOneBased(1);
        Tag editedTag = TAG_2;
        EditTagCommand editTagCommand = new EditTagCommand(index, 0, editedTag);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TAG_NUMBER, () ->
                editTagCommand.execute(modelManager));
    }

    @Test
    public void execute_tagNumberOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(1);
        Tag editedTag = TAG_1;
        EditTagCommand editTagCommand = new EditTagCommand(index, 100, editedTag);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TAG_NUMBER, () ->
                editTagCommand.execute(modelManager));
    }

    @Test
    public void execute_duplicateTagAlreadyExists_throwsException() throws Exception {
        ArrayList<Tag> personTagList = new ArrayList<>(List.of(TAG_1, TAG_2));
        Person person = new PersonBuilder().withTags(personTagList).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        Tag alreadyPresentTag = TAG_2;
        EditTagCommand editTagCommand = new EditTagCommand(index, 1, alreadyPresentTag);

        assertThrows(CommandException.class, EditTagCommand.MESSAGE_DUPLICATE_TAG, () ->
                editTagCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(5);
        int tagNumber1 = 3;
        int tagNumber2 = 5;

        Tag tag1 = this.TAG_1;
        Tag tag2 = this.TAG_2;

        assertEquals(new EditTagCommand(index1, tagNumber1, tag1),
                new EditTagCommand(index1, tagNumber1, tag1)); // same values

        assertNotEquals(new EditTagCommand(index1, tagNumber1, tag1), "1"); //different types
        assertNotEquals(new EditTagCommand(index1, tagNumber1, tag1),
            new EditTagCommand(index2, tagNumber1, tag1)); //different index
        assertNotEquals(new EditTagCommand(index1, tagNumber1, tag1),
                new EditTagCommand(index1, tagNumber2, tag1)); //different tag number
        assertNotEquals(new EditTagCommand(index1, tagNumber1, tag1),
                new EditTagCommand(index1, tagNumber1, tag2)); //different tag
    }
}
