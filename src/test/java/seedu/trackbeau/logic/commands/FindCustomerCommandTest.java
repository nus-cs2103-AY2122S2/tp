package seedu.trackbeau.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;
import static seedu.trackbeau.testutil.TypicalCustomers.CARL;
import static seedu.trackbeau.testutil.TypicalCustomers.ELLE;
import static seedu.trackbeau.testutil.TypicalCustomers.FIONA;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate;
import seedu.trackbeau.ui.Panel;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCustomerCommand}.
 */
public class FindCustomerCommandTest {
    private Model model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackBeau(), new UserPrefs());

    @Test
    public void equals() {
        ArrayList<List<String>> firstPrefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        ArrayList<List<String>> secondPrefixArr2 = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        firstPrefixArr.set(0, Collections.singletonList("first"));
        CustomerSearchContainsKeywordsPredicate firstPredicate =
                new CustomerSearchContainsKeywordsPredicate(firstPrefixArr);
        secondPrefixArr2.set(0, Collections.singletonList("second"));
        CustomerSearchContainsKeywordsPredicate secondPredicate =
                new CustomerSearchContainsKeywordsPredicate(secondPrefixArr2);

        FindCustomerCommand findFirstCommand = new FindCustomerCommand(firstPredicate);
        FindCustomerCommand findSecondCommand = new FindCustomerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCustomerCommand findFirstCommandCopy = new FindCustomerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCustomerFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        CustomerSearchContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, Panel.CUSTOMER_PANEL);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        CustomerSearchContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCustomerCommand command = new FindCustomerCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, Panel.CUSTOMER_PANEL);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private CustomerSearchContainsKeywordsPredicate preparePredicate(String userInput) {
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        prefixArr.set(0, Arrays.asList(userInput.split("\\s+")));
        return new CustomerSearchContainsKeywordsPredicate(prefixArr);
    }
}
