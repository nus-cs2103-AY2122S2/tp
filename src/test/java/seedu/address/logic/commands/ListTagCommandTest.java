package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTagCommand.
 */
public class ListTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Disabled
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        // get success message in String
        List<Tag> tags = expectedModel.getTagList();
        StringBuilder result = new StringBuilder(ListTagCommand.MESSAGE_SUCCESS);
        for (Tag tag : tags) {
            result.append(" ").append(tag.getTagName());
        }
        result.append(" ]");
        assertCommandSuccess(new ListTagCommand(), model, result.toString(), expectedModel);
    }
}
