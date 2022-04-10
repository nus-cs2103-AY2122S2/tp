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

public class AddTagCommandTest {
    static final Tag TAG_1 = VALID_TAG_FRIEND.get(0);
    static final Tag TAG_2 = VALID_TAG_HUSBAND.get(0);

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, TAG_1));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_tagsAddedByPerson_addSuccessful() throws Exception {
        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);
        Tag tagToBeAdded = TAG_2;

        Person tagAddedPerson = new PersonBuilder().build();
        ArrayList<Tag> tagList = tagAddedPerson.getTags();
        boolean wasAdded = tagList.add(tagToBeAdded);
        tagAddedPerson.setTags(tagList);

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new AddTagCommand(index, tagToBeAdded).execute(modelManager);

        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, tagAddedPerson), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_indexOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(100);
        Tag tagToBeAdded = TAG_1;
        AddTagCommand addTagCommand = new AddTagCommand(index, tagToBeAdded);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
            addTagCommand.execute(modelManager));
    }

    @Test
    public void execute_duplicateTagAlreadyExists_throwsException() throws Exception {
        ArrayList<Tag> personTagList = new ArrayList<>(List.of(TAG_1, TAG_2));
        Person person = new PersonBuilder().withTags(personTagList).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        Tag alreadyPresentTag = TAG_2;

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        AddTagCommand addTagCommand = new AddTagCommand(index, alreadyPresentTag);

        assertThrows(CommandException.class, AddTagCommand.MESSAGE_DUPLICATE_TAG, () ->
                addTagCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(5);

        assertEquals(new AddTagCommand(index1, TAG_1), new AddTagCommand(index1, TAG_1)); // same values

        assertNotEquals(new AddTagCommand(index1, TAG_1), "1"); //different types
        assertNotEquals(new AddTagCommand(index1, TAG_1),
                new AddTagCommand(index2, TAG_1)); //different index
        assertNotEquals(new AddTagCommand(index1, TAG_1),
                new AddTagCommand(index1, TAG_2)); //different tag
    }
}
