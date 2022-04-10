package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.tag.Tag;

public class ClearEmptyTagCommandTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());
    private final Model expectedModel = new ModelManager(model.getUnite(), new UserPrefs());

    @Test
    public void execute_noEmptyTags_success() {
        assertCommandSuccess(new ClearEmptyTagCommand(), model,
                String.format(ClearEmptyTagCommand.MESSAGE_SUCCESS, 0), expectedModel);
    }

    @Test
    public void execute_emptyTags_success() {
        Tag testTag = new Tag("testTag");
        model.addTag(testTag);
        assertTrue(model.hasTag(testTag));
        assertCommandSuccess(new ClearEmptyTagCommand(), model,
                String.format(ClearEmptyTagCommand.MESSAGE_SUCCESS, 1), expectedModel);
    }

}
