package seedu.unite.logic.commands;

import static seedu.unite.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.person.Person;
import seedu.unite.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUnite(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new AddCommand(validPerson), model, String.format(AddCommand.MESSAGE_SUCCESS, validPerson),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getUnite().getPersonList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
