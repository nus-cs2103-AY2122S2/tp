package seedu.ibook.logic.commands.item;

import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithItems;

import org.junit.jupiter.api.Test;

import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;

class UpdateItemCommandTest {

    private final Model model = new ModelManager(getTypicalIBookWithItems(), new UserPrefs());

    @Test
    void execute() {
    }

    @Test
    void equals() {
    }
}
