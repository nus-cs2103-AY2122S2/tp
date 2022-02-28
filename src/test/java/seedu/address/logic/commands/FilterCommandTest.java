package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsTagPredicate firstPredicate =
                new NameContainsTagPredicate("CS2103T");
        NameContainsTagPredicate secondPredicate =
                new NameContainsTagPredicate("CS2102");

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroTags_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsTagPredicate predicate = preparePredicate("CS2102");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneTag_onePersonFound() {
        Optional<Tag> tagToString = BENSON.getTags().stream()
                .findFirst();
        String moduleName = tagToString
                .map(x -> x.toString().substring(1, x.toString().length() - 1))
                .orElse("CS2103T");
        Integer numOfMatches = (int) (TypicalPersons.getTypicalPersons().stream()
                .filter(x -> x.getTags().contains(tagToString.orElse(new Tag("CS2103T"))))
                .count());

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, numOfMatches);
        NameContainsTagPredicate predicate = preparePredicate(moduleName);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().contains(BENSON));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsTagPredicate}.
     */
    private NameContainsTagPredicate preparePredicate(String userInput) {
        return new NameContainsTagPredicate(userInput);
    }
}
