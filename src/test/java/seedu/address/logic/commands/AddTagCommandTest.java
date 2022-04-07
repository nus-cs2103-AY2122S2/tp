package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import seedu.address.testutil.TagListBuilder;

public class AddTagCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, new TagListBuilder().build()));
    }

    @Test
    public void constructor_nullTags_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_tagsAddedByPerson_addSuccessful() throws Exception {
        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);
        ArrayList<Tag> tagList = new TagListBuilder().build();

        Person tagAddedPerson = new PersonBuilder().build();
        ArrayList<Tag> tagAddedTagList = tagAddedPerson.getTags();
        for (Tag tag : tagList) {
            boolean b = tagAddedTagList.add(tag);
        }

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new AddTagCommand(index, tagList).execute(modelManager);

        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, tagAddedPerson), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_indexOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(100);
        ArrayList<Tag> tagList = new TagListBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(index, tagList);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
            addTagCommand.execute(modelManager));
    }

    @Test
    public void execute_duplicateTagAlreadyExists_throwsException() throws Exception {
        ArrayList<Tag> personTagList = new ArrayList<>(List.of(TagListBuilder.ADD_TAG_1, TagListBuilder.ADD_TAG_2));
        Person person = new PersonBuilder().withTags(personTagList).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        ArrayList<Tag> tagList = new ArrayList<>();
        Tag alreadyPresentTag = TagListBuilder.ADD_TAG_2;
        boolean b = tagList.add(alreadyPresentTag);

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        AddTagCommand addTagCommand = new AddTagCommand(index, tagList);

        assertThrows(CommandException.class, AddTagCommand.MESSAGE_DUPLICATE_TAG, () ->
                addTagCommand.execute(modelManager));
    }

    @Test
    public void execute_duplicateTagInUserInput_throwsException() throws Exception {
        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        ArrayList<Tag> tagList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            tagList.add(TagListBuilder.ADD_TAG_1); // two duplicates
        }

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        AddTagCommand addTagCommand = new AddTagCommand(index, tagList);

        assertThrows(CommandException.class, AddTagCommand.MESSAGE_DUPLICATE_TAG, () ->
                addTagCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(5);

        ArrayList<Tag> tagList1 = new TagListBuilder().build();
        ArrayList<Tag> tagList2 = new TagListBuilder(TagListBuilder.ADD_TAG_1, TagListBuilder.ADD_TAG_2).build();

        assertEquals(new AddTagCommand(index1, tagList1), new AddTagCommand(index1, tagList1)); // same values

        assertNotEquals(new AddTagCommand(index1, tagList1), "1"); //different types
        assertNotEquals(new AddTagCommand(index1, tagList1),
                new AddTagCommand(index2, tagList1)); //different index
        assertNotEquals(new AddTagCommand(index1, tagList1),
                new AddTagCommand(index1, tagList2)); //different tagList
    }
}
