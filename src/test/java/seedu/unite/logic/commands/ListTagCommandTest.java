package seedu.unite.logic.commands;

import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListTagCommand.
 */
public class ListTagCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalUnite(), new UserPrefs());
        expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
    }

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
