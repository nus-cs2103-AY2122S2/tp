package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.predicates.CombineContainsKeywordsPredicate;
import seedu.address.model.person.predicates.FieldContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.PhoneContainsKeywordsPredicate;
import seedu.address.testutil.PredicatesListBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void oneFieldEquals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        CombineContainsKeywordsPredicate firstCombinePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder().addNamePredicate(firstPredicate).build());
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        CombineContainsKeywordsPredicate secondCombinePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder().addNamePredicate(secondPredicate).build());

        FindCommand findFirstCommand = new FindCommand(firstCombinePredicate);
        FindCommand findSecondCommand = new FindCommand(secondCombinePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCombinePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void multipleFieldsEquals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneContainsKeywordsPredicate firstPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("88888888"));
        CombineContainsKeywordsPredicate firstCombinePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(firstNamePredicate)
                        .addPhonePredicate(firstPhonePredicate)
                        .build());

        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        PhoneContainsKeywordsPredicate secondPhonePredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("77777777"));
        CombineContainsKeywordsPredicate secondCombinePredicate = new CombineContainsKeywordsPredicate(
                new PredicatesListBuilder()
                        .addNamePredicate(secondNamePredicate)
                        .addPhonePredicate(secondPhonePredicate)
                        .build());

        FindCommand findFirstCommand = new FindCommand(firstCombinePredicate);
        FindCommand findSecondCommand = new FindCommand(secondCombinePredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCombinePredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate(" ");
        List<FieldContainsKeywordsPredicate> predicatesList =
                predicatesListBuilder.addNamePredicate(namePredicate).build();
        CombineContainsKeywordsPredicate combinePredicate = new CombineContainsKeywordsPredicate(predicatesList);
        FindCommand command = new FindCommand(combinePredicate);
        expectedModel.updateFilteredPersonList(combinePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        List<FieldContainsKeywordsPredicate> predicatesList =
                predicatesListBuilder.addNamePredicate(namePredicate).build();
        CombineContainsKeywordsPredicate combinePredicate = new CombineContainsKeywordsPredicate(predicatesList);
        FindCommand command = new FindCommand(combinePredicate);
        expectedModel.updateFilteredPersonList(combinePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleFields_multiplePersonsFound() {
        PredicatesListBuilder predicatesListBuilder = new PredicatesListBuilder();
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate namePredicate = prepareNamePredicate("Kurz Elle Kunz");
        PhoneContainsKeywordsPredicate phonePredicate = preparePhonePredicate("95352563 9482224 9482427");
        List<FieldContainsKeywordsPredicate> predicatesList =
                predicatesListBuilder
                        .addNamePredicate(namePredicate)
                        .addPhonePredicate(phonePredicate)
                        .build();
        CombineContainsKeywordsPredicate combinePredicate = new CombineContainsKeywordsPredicate(predicatesList);
        FindCommand command = new FindCommand(combinePredicate);
        expectedModel.updateFilteredPersonList(combinePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    /**
     * Parses {@code userInput} for the {@code NameContainsKeywordsPredicate}
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} for the {@code PhoneContainsKeywordsPredicate}
     */
    private PhoneContainsKeywordsPredicate preparePhonePredicate(String userInput) {
        return new PhoneContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
