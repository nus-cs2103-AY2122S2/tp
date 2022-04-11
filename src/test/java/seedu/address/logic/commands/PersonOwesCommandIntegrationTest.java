package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseExpert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;


public class PersonOwesCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalExpenseExpert(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Person validPerson = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getExpenseExpert(), new UserPrefs());
        expectedModel.addPerson(validPerson);

        assertCommandSuccess(new PersonOwesCommand(validPerson),
                model,
                String.format(PersonOwesCommand.MESSAGE_SUCCESS, validPerson),
                model);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person personInList = model.getExpenseExpert().getPersonList().get(0);
        assertCommandFailure(new PersonOwesCommand(personInList), model, PersonOwesCommand.MESSAGE_DUPLICATE_PERSON);
    }
}
