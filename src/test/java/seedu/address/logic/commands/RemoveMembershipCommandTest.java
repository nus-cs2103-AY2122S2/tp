package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) for {@code RemoveMembershipCommandTest}.
 */
class RemoveMembershipCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_removeMembership_success() {
        RemoveMembershipCommand command = new RemoveMembershipCommand(Index.fromZeroBased(0));

        // Successful remove command

        Model editModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = editModel.getFilteredPersonList().get(0);
        Membership member = new Membership("Gold");
        Person personEdited = person.addMembership(member);
        editModel.setPerson(person, personEdited);
        String expectedMessage = String.format(RemoveMembershipCommand.MESSAGE_SUCCESS, person);
        assertCommandSuccess(command, editModel, expectedMessage, model);

        // No membership
        person = model.getFilteredPersonList().get(0);
        expectedMessage = String.format(RemoveMembershipCommand.MESSAGE_NO_MEMBERSHIP, person);
        assertCommandSuccess(command, model, expectedMessage, model);
    }

    @Test
    void execute_removeMembership_failure() {
        // Out of index
        RemoveMembershipCommand command = new RemoveMembershipCommand(Index.fromZeroBased(10));
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        assertCommandFailure(command, expectedModel, expectedMessage);
    }

    @Test
    void equals() {
        RemoveMembershipCommand command = new RemoveMembershipCommand(Index.fromZeroBased(0));
        RemoveMembershipCommand otherCommand = new RemoveMembershipCommand(Index.fromZeroBased(0));
        RemoveMembershipCommand otherCommand2 = new RemoveMembershipCommand(Index.fromZeroBased(1));

        assertTrue(command.equals(command));

        assertTrue(command.equals(otherCommand));

        assertFalse(command.equals(otherCommand2));

    }
}
