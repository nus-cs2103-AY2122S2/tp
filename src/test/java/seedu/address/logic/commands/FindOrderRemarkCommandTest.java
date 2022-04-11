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
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.RemarkContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderRemarkCommand}.
 */
public class FindOrderRemarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void equals() {
        RemarkContainsKeywordsPredicate firstPredicate =
                new RemarkContainsKeywordsPredicate(Collections.singletonList("first"));
        RemarkContainsKeywordsPredicate secondPredicate =
                new RemarkContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderRemarkCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderRemarkCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderRemarkCommand(firstPredicate);
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
                "remark", Arrays.asList("Allergic Hates".split(" ")));
        RemarkContainsKeywordsPredicate predicate = preparePredicate("Allergic Hates");
        FindOrderCommand command = new FindOrderRemarkCommand(predicate);
        expectedModel.updateFilteredOrderList(predicate);
        assertOrderCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EMILY, JERRY), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code RemarkContainsKeywordsPredicate}.
     */
    private RemarkContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] remarkKeywords = userInput.trim().split("\\s+");
        return new RemarkContainsKeywordsPredicate(Arrays.asList(remarkKeywords));
    }
}
