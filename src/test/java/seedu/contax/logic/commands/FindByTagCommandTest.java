package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.contax.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.contax.testutil.TypicalPersons.ALICE;
import static seedu.contax.testutil.TypicalPersons.BENSON;
import static seedu.contax.testutil.TypicalPersons.DANIEL;
import static seedu.contax.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.model.Model;
import seedu.contax.model.ModelManager;
import seedu.contax.model.Schedule;
import seedu.contax.model.UserPrefs;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByTagCommand}.
 */
public class FindByTagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new Schedule(), new UserPrefs());

    @Test
    public void execute_validName_tagFound() {
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3),
                GuiListContentType.PERSON);

        // Complete keyword
        TagNameContainsKeywordsPredicate friendsPredicate = new TagNameContainsKeywordsPredicate("friends");
        FindByTagCommand command = new FindByTagCommand(friendsPredicate);
        expectedModel.updateFilteredPersonList(friendsPredicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(List.of(ALICE, BENSON, DANIEL), model.getFilteredPersonList());

        // Partial keyword
        TagNameContainsKeywordsPredicate friendsPartialPredicate = new TagNameContainsKeywordsPredicate("fri");
        FindByTagCommand command2 = new FindByTagCommand(friendsPartialPredicate);
        expectedModel.updateFilteredPersonList(friendsPredicate);
        assertCommandSuccess(command2, model, expectedResult, expectedModel);
        assertEquals(List.of(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_validName_noTagFound() {
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0),
                GuiListContentType.PERSON);

        TagNameContainsKeywordsPredicate predicate = new TagNameContainsKeywordsPredicate("bosses");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedResult, expectedModel);
        assertEquals(List.of(), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        TagNameContainsKeywordsPredicate firstPredicate = new TagNameContainsKeywordsPredicate("first");
        TagNameContainsKeywordsPredicate secondPredicate = new TagNameContainsKeywordsPredicate("second");

        FindByTagCommand firstCommand = new FindByTagCommand(firstPredicate);
        FindByTagCommand secondCommand = new FindByTagCommand(secondPredicate);

        assertTrue(firstCommand.equals(firstCommand));
        assertTrue(firstCommand.equals(new FindByTagCommand(firstPredicate)));

        assertFalse(firstCommand.equals(secondCommand));
        assertFalse(firstCommand.equals(null));
        assertFalse(firstCommand.equals(0));
    }
}
