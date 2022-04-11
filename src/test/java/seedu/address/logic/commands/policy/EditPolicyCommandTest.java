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
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CLIENT;
import static seedu.address.testutil.TypicalPolicies.INSURANCE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.exceptions.DuplicatePolicyException;
import seedu.address.model.policy.exceptions.EmptyPolicyListException;
import seedu.address.model.policy.exceptions.InvalidPolicyIndexException;
import seedu.address.model.policy.exceptions.PolicyNotEditedException;
import seedu.address.testutil.PolicyBuilder;


public class EditPolicyCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Policy editedPolicy = new PolicyBuilder().build();
    private Client targetClient = model.getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
    private EditPolicyDescriptor editPolicyDescriptor = new EditPolicyDescriptor(editedPolicy);

    @Test
    public void constructor_nullClientIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(null, INDEX_FIRST_CLIENT,
                editPolicyDescriptor));
    }

    @Test
    public void constructor_nullPolicyIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(INDEX_SECOND_CLIENT, null,
                editPolicyDescriptor));
    }

    @Test
    public void constructor_nullEditPolicyDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT,
                null));
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        String expectedResult = String.format(EditPolicyCommand.MESSAGE_SUCCESS, editedPolicy, targetClient.getName());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setClient(targetClient, targetClient.setPolicy(INDEX_FIRST_CLIENT.getZeroBased(), editedPolicy));
        assertCommandSuccess(editPolicyCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidClientIndex_throwsCommandException() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_INVALID_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        String expectedResult = Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX;
        assertCommandFailure(editPolicyCommand, model, expectedResult);
    }

    @Test
    public void execute_invalidPolicyIndex_throwsCommandException() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_INVALID_CLIENT,
                editPolicyDescriptor);
        String expectedResult = new InvalidPolicyIndexException().getMessage();
        assertCommandFailure(editPolicyCommand, model, expectedResult);
    }

    @Test
    public void execute_emptyPolicyList_throwsCommandException() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_THIRD_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        String expectedResult = new EmptyPolicyListException().getMessage();
        assertCommandFailure(editPolicyCommand, model, expectedResult);

    }

    @Test
    public void execute_uneditedPolicy_throwsCommandException() {
        EditPolicyDescriptor insurancePolicyDescriptor = new EditPolicyDescriptor(INSURANCE);
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_FIRST_CLIENT,
                insurancePolicyDescriptor);
        String expectedResult = new PolicyNotEditedException().getMessage();
        assertCommandFailure(editPolicyCommand, model, expectedResult);
    }

    @Test
    public void execute_editDuplicatePolicy_throwsCommandException() {
        EditPolicyDescriptor insurancePolicyDescriptor = new EditPolicyDescriptor(INSURANCE);
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_SECOND_CLIENT, INDEX_SECOND_CLIENT,
                insurancePolicyDescriptor);
        String expectedResult = new DuplicatePolicyException().getMessage();
        assertCommandFailure(editPolicyCommand, model, expectedResult);
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        assertTrue(editPolicyCommand.equals(editPolicyCommand));
    }

    @Test
    public void equals_null_returnsFalse() {
        EditPolicyCommand editPolicyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editPolicyDescriptor);
        assertFalse(editPolicyCommand.equals(null));
    }
}
