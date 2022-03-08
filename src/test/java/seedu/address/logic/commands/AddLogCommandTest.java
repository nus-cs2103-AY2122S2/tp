package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddLogCommand.
 */
public class AddLogCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    // ===== UNIT TESTS =====
    @Test
    public void equals() {
        // same values -> returns true

        // same object -> returns true

        // null -> returns false

        // different types -> returns false

        // different index -> returns false

        // different descriptor -> returns false
    }

    // ===== INTEGRATION TESTS WITH MODEL =====
    @Test
    public void execute_validIInput_success() {
        // only title


        // title and description

    }

    @Test
    public void execute_noChanges_success() {

    }

    @Test
    public void execute_duplicateLog_failure() {

    }

    @Test
    public void execute_invalidInput_failure() {
        // ===== FILTERED LIST =====
        // invalid index

        // missing index

        // invalid title

        // missing title


        // ===== UNFILTERED LIST =====
        // invalid index

        // missing index

        // invalid title

        // missing title
    }
}
