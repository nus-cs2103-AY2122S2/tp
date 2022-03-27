package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;


public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");
        PersonContainsTagPredicate firstPredicate =
                new PersonContainsTagPredicate(firstTag);
        PersonContainsTagPredicate secondPredicate =
                new PersonContainsTagPredicate(secondTag);

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate, firstTag);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate, secondTag);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate, firstTag);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_existingEmptyTag_filterSuccessful() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        Tag tag = new Tag("family");

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        newModel.addTag(tag);
        Model newExpectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        newExpectedModel.addTag(tag);

        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(tag);
        FilterCommand command = new FilterCommand(predicate, tag);
        newExpectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        assertEquals(Arrays.asList(), newModel.getFilteredPersonList());
    }

    @Test
    public void execute_nonExistingTag_throwsCommandException() {
        Tag tag = new Tag("nonExistingTag");
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(tag);
        FilterCommand command = new FilterCommand(predicate, tag);
        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_TAG_NAME);
    }

    @Test
    public void execute_validTag_filterSuccessful() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        Tag existingTag = new Tag("friends");
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(existingTag);
        FilterCommand command = new FilterCommand(predicate, existingTag);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

}
