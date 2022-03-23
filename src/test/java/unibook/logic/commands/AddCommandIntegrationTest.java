package unibook.logic.commands;

import static unibook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UserPrefs;
import unibook.model.person.Person;
import unibook.testutil.PersonBuilder;
import unibook.testutil.StudentBuilder;
import unibook.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalPersons.getTypicalUniBook(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Person validPerson = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getUniBook(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model,
            String.format(AddCommand.MESSAGE_SUCCESS_PERSON, validPerson), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getUniBook().getPersonList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
