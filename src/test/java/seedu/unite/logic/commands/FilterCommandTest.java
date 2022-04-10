package seedu.unite.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalPersons.ALICE;
import static seedu.unite.testutil.TypicalPersons.BENSON;
import static seedu.unite.testutil.TypicalPersons.DANIEL;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.unite.commons.core.Messages;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;
import seedu.unite.model.person.PersonContainsTagPredicate;
import seedu.unite.model.tag.Tag;


public class FilterCommandTest {
    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());

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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0) + FilterCommand.MESSAGE_SUCCESS;
        Tag tag = new Tag("family");

        //Set up tag in models
        Model newModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        newModel.addTag(tag);
        Model newExpectedModel = new ModelManager(getTypicalUnite(), new UserPrefs());
        newExpectedModel.addTag(tag);

        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(tag);
        FilterCommand command = new FilterCommand(predicate, tag);
        newExpectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, newModel, expectedMessage, newExpectedModel);
        assertEquals(List.of(), newModel.getFilteredPersonList());
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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3) + FilterCommand.MESSAGE_SUCCESS;
        Tag existingTag = new Tag("friends");
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(existingTag);
        FilterCommand command = new FilterCommand(predicate, existingTag);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

}
