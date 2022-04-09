package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class DeleteTagCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, 1));
    }

    @Test
    public void execute_tagDeletedByPerson_deleteSuccessful() throws Exception {
        Person person = new PersonBuilder().withTags(VALID_TAG_FRIEND).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        Person tagDeletedPerson = new PersonBuilder().withTags(new ArrayList<>()).build();

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new DeleteTagCommand(index, 1).execute(modelManager);

        assertEquals(String.format(DeleteTagCommand.MESSAGE_SUCCESS, tagDeletedPerson),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_indexOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(100);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(index, 1);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () ->
                deleteTagCommand.execute(modelManager));
    }

    @Test
    public void execute_tagNumberNotPositive_throwsException() throws Exception {
        Index index = Index.fromOneBased(1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(index, 0);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TAG_NUMBER, () ->
                deleteTagCommand.execute(modelManager));
    }

    @Test
    public void execute_tagNumberOutOfBounds_throwsException() throws Exception {
        Index index = Index.fromOneBased(1);
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(index, 100);

        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person); // one person
        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TAG_NUMBER, () ->
                deleteTagCommand.execute(modelManager));
    }

    @Test
    public void equals() {
        Index index1 = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(5);
        int tagNumber1 = 3;
        int tagNumber2 = 5;

        assertEquals(new DeleteTagCommand(index1, tagNumber1), new DeleteTagCommand(index1, tagNumber1)); // same values

        assertNotEquals(new DeleteTagCommand(index1, tagNumber1), "1"); //different types
        assertNotEquals(new DeleteTagCommand(index1, tagNumber1),
                new DeleteTagCommand(index2, tagNumber1)); //different index
        assertNotEquals(new DeleteTagCommand(index1, tagNumber1),
                new DeleteTagCommand(index1, tagNumber2)); //different tag number
    }
}
