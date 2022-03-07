package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.OldModel;
import seedu.address.model.OldModelManager;
import seedu.address.model.OldUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the OldModel) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    /*
    private OldModel oldModel;

    @BeforeEach
    public void setUp() {
        oldModel = new OldModelManager(getTypicalAddressBook(), new OldUserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        OldModel expectedOldModel = new OldModelManager(oldModel.getAddressBook(), new OldUserPrefs());
        expectedOldModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), oldModel,
                String.format(AddCommand.MESSAGE_SUCCESS, validPerson), expectedOldModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = oldModel.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), oldModel, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

     */

}
