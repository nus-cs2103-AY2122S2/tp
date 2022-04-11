package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicate.FindPersonPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals_sameFindCommand() {
        String withAll = " n/Alex" + " p/90100102" + " e/tester@mail.com" + " a/QueensTown blk 200 singapore 123123"
                + " t/friends colleagues" + " m/Close contract today";
        ArgumentMultimap withAllDescriptor = ArgumentTokenizer.tokenize(withAll, ARRAY_OF_PREFIX);
        FindCommand withAllFindCommand = new FindCommand(new FindPersonPredicate(withAllDescriptor));

        String withName = " n/Alex bob";
        ArgumentMultimap withNameDescriptor = ArgumentTokenizer.tokenize(withName, ARRAY_OF_PREFIX);
        FindCommand withNameFindCommand = new FindCommand(new FindPersonPredicate(withNameDescriptor));

        // same object -> returns true
        assertEquals(withAllFindCommand, withAllFindCommand);
        assertEquals(withNameFindCommand, withNameFindCommand);
    }

    @Test
    public void execute_zeroKeywords_allPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        String emptyInput = " n/";
        ArgumentMultimap emptyDescriptor = ArgumentTokenizer.tokenize(emptyInput, ARRAY_OF_PREFIX);
        FindCommand command = new FindCommand(new FindPersonPredicate(emptyDescriptor));
        expectedModel.updateFilteredPersonList(new FindPersonPredicate(emptyDescriptor));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        FindPersonPredicate predicate = preparePredicate();
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Prepares a PersonPredicate for testing.
     */
    private FindPersonPredicate preparePredicate() {
        ArgumentMultimap descriptor = ArgumentTokenizer.tokenize(" n/ Kurz n/Elle n/Kunz", ARRAY_OF_PREFIX);
        return new FindPersonPredicate(descriptor);
    }
}
