package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_FIND_ORDERS_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertOrderCommandSuccess;
import static seedu.address.testutil.TypicalOrders.EMILY;
import static seedu.address.testutil.TypicalOrders.JERRY;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBookOrders;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.CollectionTypeContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCollectionTypeCommand}.
 */
public class FindOrderCollectionTypeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void equals() {
        CollectionTypeContainsKeywordsPredicate firstPredicate =
                new CollectionTypeContainsKeywordsPredicate("delivery");
        CollectionTypeContainsKeywordsPredicate secondPredicate =
                new CollectionTypeContainsKeywordsPredicate("pickup");

        FindOrderCommand findFirstCommand = new FindOrderCollectionTypeCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderCollectionTypeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCollectionTypeCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_singleKeyword_multipleOrdersFound() {
        String expectedMessage = String.format(MESSAGE_FIND_ORDERS_OVERVIEW, 2,
                "collectionType", "delivery");
        CollectionTypeContainsKeywordsPredicate predicate = preparePredicate("delivery");
        FindOrderCommand command = new FindOrderCollectionTypeCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertOrderCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EMILY, JERRY), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code CollectionTypeContainsKeywordsPredicate}.
     */
    private CollectionTypeContainsKeywordsPredicate preparePredicate(String userInput) {
        String collectionTypeKeyword = userInput.trim();
        return new CollectionTypeContainsKeywordsPredicate(collectionTypeKeyword);
    }
}
