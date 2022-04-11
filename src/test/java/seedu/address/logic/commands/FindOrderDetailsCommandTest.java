package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_ORDERS_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertOrderCommandSuccess;
import static seedu.address.testutil.TypicalOrders.EMILY;
import static seedu.address.testutil.TypicalOrders.SIMON;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBookOrders;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.DetailsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderDetailsCommand}.
 */
public class FindOrderDetailsCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void equals() {
        DetailsContainsKeywordsPredicate firstPredicate =
                new DetailsContainsKeywordsPredicate(Collections.singletonList("first"));
        DetailsContainsKeywordsPredicate secondPredicate =
                new DetailsContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderDetailsCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderDetailsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderDetailsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_FIND_ORDERS_OVERVIEW, 2,
                "details", Arrays.asList("chocolate vanilla".split(" ")));
        DetailsContainsKeywordsPredicate predicate = preparePredicate("chocolate vanilla");
        FindOrderCommand command = new FindOrderDetailsCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertOrderCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EMILY, SIMON), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code DetailsContainsKeywordsPredicate}.
     */
    private DetailsContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] detailsKeywords = userInput.trim().split("\\s+");
        return new DetailsContainsKeywordsPredicate(Arrays.asList(detailsKeywords));
    }
}
