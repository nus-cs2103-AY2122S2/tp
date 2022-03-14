package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.commons.core.Messages.MESSAGE_TAGS_LISTED_OVERVIEW;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.FRIENDS;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.tag.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByTagCommand}.
 */
public class FindByTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void execute_validName_tagFound() {
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_TAGS_LISTED_OVERVIEW, 1),
                GuiListContentType.TAG);

        // Complete keyword
        NameContainsKeywordsPredicate friendsPredicate = new NameContainsKeywordsPredicate("friends");
        FindByTagCommand command = new FindByTagCommand(friendsPredicate);
        expectedModel.updateFilteredTagList(friendsPredicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(List.of(FRIENDS), model.getFilteredTagList());

        // Partial keyword
        NameContainsKeywordsPredicate friendsPartialPredicate = new NameContainsKeywordsPredicate("fri");
        FindByTagCommand command2 = new FindByTagCommand(friendsPartialPredicate);
        expectedModel.updateFilteredTagList(friendsPartialPredicate);
        assertCommandSuccess(command2, model, expectedResult, expectedModel);
        assertEquals(List.of(FRIENDS), model.getFilteredTagList());
    }

    @Test
    public void execute_validName_noTagFound() {
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_TAGS_LISTED_OVERVIEW, 0),
                GuiListContentType.TAG);

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate("bosses");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredTagList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(List.of(), model.getFilteredTagList());
    }

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate = new NameContainsKeywordsPredicate("first");
        NameContainsKeywordsPredicate secondPredicate = new NameContainsKeywordsPredicate("second");

        FindByTagCommand firstCommand = new FindByTagCommand(firstPredicate);
        FindByTagCommand secondCommand = new FindByTagCommand(secondPredicate);

        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(firstCommand.equals(new FindByTagCommand(firstPredicate)));

        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(0));
    }
}
