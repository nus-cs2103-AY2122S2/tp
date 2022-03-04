package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showclientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_client;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_client;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditclientDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditclientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_client, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_client_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setclient(model.getFilteredclientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastclient = Index.fromOneBased(model.getFilteredclientList().size());
        Client lastClient = model.getFilteredclientList().get(indexLastclient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditclientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastclient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_client_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setclient(lastClient, editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_client, new EditclientDescriptor());
        Client editedClient = model.getFilteredclientList().get(INDEX_FIRST_client.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_client_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showclientAtIndex(model, INDEX_FIRST_client);

        Client clientInFilteredList = model.getFilteredclientList().get(INDEX_FIRST_client.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_client,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_client_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setclient(model.getFilteredclientList().get(0), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateclientUnfilteredList_failure() {
        Client firstClient = model.getFilteredclientList().get(INDEX_FIRST_client.getZeroBased());
        EditclientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_client, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_client);
    }

    @Test
    public void execute_duplicateclientFilteredList_failure() {
        showclientAtIndex(model, INDEX_FIRST_client);

        // edit client in filtered list into a duplicate in address book
        Client clientInList = model.getAddressBook().getclientList().get(INDEX_SECOND_client.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_client,
                new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_client);
    }

    @Test
    public void execute_invalidclientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredclientList().size() + 1);
        EditclientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_client_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidclientIndexFilteredList_failure() {
        showclientAtIndex(model, INDEX_FIRST_client);
        Index outOfBoundIndex = INDEX_SECOND_client;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getclientList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_client_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_client, DESC_AMY);

        // same values -> returns true
        EditclientDescriptor copyDescriptor = new EditclientDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_client, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_client, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_client, DESC_BOB)));
    }

}
