package unibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import unibook.model.Model;
import unibook.model.ModelManager;
import unibook.model.UserPrefs;
import unibook.model.person.Person;
import unibook.testutil.typicalclasses.TypicalIndexes;
import unibook.testutil.typicalclasses.TypicalUniBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    public static final Boolean PERSON_LIST_SHOWING = true;
    public static final Boolean PERSON_LIST_NOT_SHOWING = false;
    public static final Boolean MODULE_LIST_SHOWING = true;
    public static final Boolean MODULE_LIST_NOT_SHOWING = false;

    private Model model = new ModelManager(TypicalUniBook.getTypicalUniBook(), new UserPrefs());

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredPersonList(p -> false);

        assertTrue(model.getFilteredPersonList().isEmpty());
    }
}
