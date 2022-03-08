package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewLessonInfoCommand}
 */
public class ViewLessonInfoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // TODO: Can't test for lesson as TypicalPersons.java does not include lessons yet
    /*  @Test
        public void execute_validIndexUnfilteredList_success() {
            Person selectedPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            ViewLessonInfoCommand viewCommand = new ViewLessonInfoCommand(INDEX_FIRST_PERSON);

            String expectedMessage = String.format(ViewLessonInfoCommand.MESSAGE_VIEW_SUCCESS, selectedPerson);

            ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
            expectedModel.setSelectedPerson(selectedPerson);

            assertCommandSuccess(viewCommand, model, expectedMessage, expectedModel);
        }
    */
}
