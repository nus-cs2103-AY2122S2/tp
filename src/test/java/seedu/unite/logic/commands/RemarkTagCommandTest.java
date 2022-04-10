package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.tag.Remark;
import seedu.unite.model.tag.Tag;


/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkTagCommand.
 */
public class RemarkTagCommandTest {

    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());
    private final String normalRemark = "This is a remark";
    private final Tag newTag = new Tag("family");

    @Test
    public void execute_validTagAndRemark_success() throws CommandException {
        Tag t = model.getTagList().get(0);
        String expectedMessage = String.format(RemarkTagCommand.MESSAGE_SUCCESS, t.toString());

        CommandResult commandResult = new RemarkTagCommand(t, new Remark(normalRemark)).execute(model);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals(normalRemark, t.getRemark().toString());
    }

    @Test
    public void execute_nonExistingTag_throwsCommandException() {
        String expectedMessage = RemarkTagCommand.MESSAGE_NO_SUCH_TAG;

        RemarkTagCommand remarkTagCommand = new RemarkTagCommand(newTag, new Remark(normalRemark));

        assertThrows(CommandException.class, expectedMessage, () ->
                remarkTagCommand.execute(model));
    }

    @Test
    public void execute_validTagAndEmptyRemark_success() throws CommandException {
        Tag t = model.getTagList().get(0);
        String expectedMessage = String.format(RemarkTagCommand.MESSAGE_SUCCESS, t.toString());

        CommandResult commandResult = new RemarkTagCommand(t, new Remark("")).execute(model);
        assertEquals(expectedMessage, commandResult.getFeedbackToUser());
        assertEquals("", t.getRemark().toString());
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        RemarkTagCommand remarkFirstTagCommand = new RemarkTagCommand(firstTag, new Remark(normalRemark));
        RemarkTagCommand remarkSecondTagCommand = new RemarkTagCommand(secondTag, new Remark(normalRemark));

        //same tag different remark -> returns false
        RemarkTagCommand remarkFirstTagCommandCopy = new RemarkTagCommand(firstTag, new Remark(""));
        assertFalse(remarkFirstTagCommand.equals(remarkFirstTagCommandCopy));

        // different tag same remark -> returns false
        assertFalse(remarkFirstTagCommand.equals(remarkSecondTagCommand));

        //same tag and remark -> returns true
        RemarkTagCommand remarkSecondTagCommandCopy = new RemarkTagCommand(secondTag, new Remark(normalRemark));
        assertTrue(remarkSecondTagCommand.equals(remarkSecondTagCommandCopy));

        // same object -> returns true
        assertTrue(remarkFirstTagCommand.equals(remarkFirstTagCommand));

        // different types -> returns false
        assertFalse(remarkFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(remarkFirstTagCommand.equals(null));

    }

}
