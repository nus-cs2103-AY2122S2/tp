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

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.person.NameContainsModulePredicate;
import seedu.address.testutil.TypicalPersons;


/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new AddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new AddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsModulePredicate firstPredicate =
                new NameContainsModulePredicate("CS2103T");
        NameContainsModulePredicate secondPredicate =
                new NameContainsModulePredicate("CS2102");

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
    public void execute_zeroModules_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsModulePredicate predicate = preparePredicate("CS2102");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneModule_onePersonFound() {
        Optional<Module> tagToString = BENSON.getModules().stream()
                .findFirst();
        String moduleName = tagToString
                .map(x -> x.toString().substring(1, x.toString().length() - 1))
                .orElse("CS2103T");
        Integer numOfMatches = (int) (TypicalPersons.getTypicalPersons().stream()
                .filter(x -> x.getModules().contains(tagToString.orElse(new Module("CS2103T"))))
                .count());

        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, numOfMatches);
        NameContainsModulePredicate predicate = preparePredicate(moduleName);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().contains(BENSON));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsModulePredicate}.
     */
    private NameContainsModulePredicate preparePredicate(String userInput) {
        return new NameContainsModulePredicate(userInput);
    }
}
