package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class ClearEmptyTagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

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
        assertFalse(model.hasTag(testTag));
    }

}
