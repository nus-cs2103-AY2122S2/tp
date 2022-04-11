package seedu.address.logic.commands.policy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_INVALID_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.exceptions.InvalidPolicyIndexException;

public class DeletePolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullClientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePolicyCommand(null, INDEX_FIRST_CLIENT));
    }

    @Test
    public void constructor_nullPolicyIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeletePolicyCommand(INDEX_FIRST_CLIENT, null));
    }

    @Test
    public void execute_invalidClientIndex_failure() {
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_INVALID_CLIENT, INDEX_FIRST_CLIENT);
        String expectedMessage = Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
        assertCommandFailure(deletePolicyCommand, model, expectedMessage);
    }

    @Test
    public void execute_validPolicyIndex_success() {
        Client targetClient = model.getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        Policy policyToDelete = targetClient.getPolicy(INDEX_FIRST_CLIENT.getZeroBased());

        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT);
        String expectedMessage = String.format(DeletePolicyCommand.MESSAGE_DELETE_POLICY_SUCCESS,
                policyToDelete, targetClient.getName());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(deletePolicyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPolicyIndex_failure() {
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_SECOND_CLIENT);
        String expectedMessage = new InvalidPolicyIndexException().getMessage();

        assertCommandFailure(deletePolicyCommand, model, expectedMessage);
    }


    @Test
    public void execute_emptyAddressBook_failure() {
        Model model = new ModelManager();
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT);
        String expectedMessage = String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "delete policy from");

        assertCommandFailure(deletePolicyCommand, model, expectedMessage);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT);
        assertTrue(deletePolicyCommand.equals(deletePolicyCommand));
    }

    @Test
    public void equals_null_returnsTrue() {
        DeletePolicyCommand deletePolicyCommand = new DeletePolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT);
        assertFalse(deletePolicyCommand.equals(null));
    }
}
