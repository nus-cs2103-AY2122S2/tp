package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;


/**
 * Contains integration tests (interaction with the Model) for {@code AddMembershipCommand}.
 */
class AddMembershipCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addMembership_success() {
        Membership member = new Membership("Gold");
        AddMembershipCommand command = new AddMembershipCommand(Index.fromZeroBased(0), member);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person person = expectedModel.getFilteredPersonList().get(0);
        Person personEdited = person.addMembership(member);
        expectedModel.setPerson(person, personEdited);

        String expectedMessage = String.format(AddMembershipCommand.MESSAGE_SUCCESS, person.getName(), "Gold member");

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        AddMembershipCommand command = new AddMembershipCommand(Index.fromZeroBased(0), new Membership("Gold"));
        AddMembershipCommand otherCommand = new AddMembershipCommand(Index.fromZeroBased(0),
                new Membership("Gold"));
        AddMembershipCommand otherCommand2 = new AddMembershipCommand(Index.fromZeroBased(1),
                new Membership("Silver"));

        assertTrue(command.equals(command));

        assertTrue(command.equals(otherCommand));

        assertFalse(command.equals(otherCommand2));

    }
}
