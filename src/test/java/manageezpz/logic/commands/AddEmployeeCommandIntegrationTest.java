package manageezpz.logic.commands;

import static manageezpz.logic.commands.AddEmployeeCommand.MESSAGE_USAGE;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandFailure;
import static manageezpz.logic.commands.CommandTestUtil.assertCommandSuccess;
import static manageezpz.testutil.TypicalPersons.getTypicalAddressBookEmployees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manageezpz.model.Model;
import manageezpz.model.ModelManager;
import manageezpz.model.UserPrefs;
import manageezpz.model.person.Person;
import manageezpz.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddEmployeeCommand}.
 */
public class AddEmployeeCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookEmployees(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddEmployeeCommand(validPerson), model,
                String.format(AddEmployeeCommand.MESSAGE_SUCCESS, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddEmployeeCommand(personInList), model,
                String.format(AddEmployeeCommand.MESSAGE_DUPLICATE_PERSON,
                        personInList.getName().toString()) + "\n" + MESSAGE_USAGE);
    }

}
