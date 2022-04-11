package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalPersons.getTypicalContactList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tasks.PriorityTaskList;


public class MailIndexCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), new PriorityTaskList());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MailIndexCommand mailIndexCommand = new MailIndexCommand(outOfBoundIndex);

        assertCommandFailure(mailIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of contact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getContactList().getPersonList().size());

        MailIndexCommand mailIndexCommand = new MailIndexCommand(outOfBoundIndex);

        assertCommandFailure(mailIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        MailIndexCommand mailIndexCommandFirst = new MailIndexCommand(INDEX_FIRST);
        MailIndexCommand mailIndexCommandSecond = new MailIndexCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(mailIndexCommandFirst, mailIndexCommandFirst);

        // same values -> returns true
        MailIndexCommand mailIndexCommandCopy = new MailIndexCommand(INDEX_FIRST);
        assertEquals(mailIndexCommandFirst, mailIndexCommandCopy);

        // different types -> returns false
        assertNotEquals(1, mailIndexCommandFirst);

        // null -> returns false
        assertNotEquals(null, mailIndexCommandFirst);

        // different person -> returns false
        assertNotEquals(mailIndexCommandFirst, mailIndexCommandSecond);
    }
}
