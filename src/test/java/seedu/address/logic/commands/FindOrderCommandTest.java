package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalOrders.AMY_ORDER;
import static seedu.address.testutil.TypicalOrders.BOB_ORDER;
import static seedu.address.testutil.TypicalOrders.CARL_ORDER;
import static seedu.address.testutil.TypicalOrders.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderCommand}.
 */
public class FindOrderCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        OrderContainsKeywordsPredicate firstPredicate =
                new OrderContainsKeywordsPredicate(Collections.singletonList("first"));
        OrderContainsKeywordsPredicate secondPredicate =
                new OrderContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderCommand(secondPredicate);

        // same object -> returns True
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> return false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noOrderFound() {
        OrderContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertThrows(CommandException.class, () -> command.execute(model));
        assertEquals(Collections.emptyList(), model.getFilteredOrderList());
    }

    @Test
    public void execute_multipleKeywords_multipleOrdersFound() {
        OrderContainsKeywordsPredicate predicate = preparePredicate("81234567 91234567 95352563");
        FindOrderCommand command = new FindOrderCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertEquals(Arrays.asList(AMY_ORDER, BOB_ORDER, CARL_ORDER), expectedModel.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code OrderContainsKeywordsPredicate}.
     */
    private OrderContainsKeywordsPredicate preparePredicate(String userInput) {
        return new OrderContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
