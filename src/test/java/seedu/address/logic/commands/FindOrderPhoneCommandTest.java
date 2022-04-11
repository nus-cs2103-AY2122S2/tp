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
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.order.OrderUuidContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindOrderPhoneCommand}.
 */
public class FindOrderPhoneCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookOrders(), new UserPrefs());

    @Test
    public void equals() {
        PhoneContainsKeywordsPredicate firstPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneContainsKeywordsPredicate secondPredicate =
                new PhoneContainsKeywordsPredicate(Collections.singletonList("second"));

        FindOrderCommand findFirstCommand = new FindOrderPhoneCommand(firstPredicate);
        FindOrderCommand findSecondCommand = new FindOrderPhoneCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindOrderCommand findFirstCommandCopy = new FindOrderPhoneCommand(firstPredicate);
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
                "phone", Arrays.asList("90032123 91029322".split(" ")));
        PhoneContainsKeywordsPredicate predicate = preparePredicate("90032123 91029322");
        FindOrderCommand command = new FindOrderPhoneCommand(predicate);

        List<Person> filteredList = expectedModel.getFilteredPersonList().filtered(predicate);
        String[] uuidKeywords = filteredList.stream().map(person->person.getUuid().toString()).toArray(String[]::new);
        expectedModel.updateFilteredOrderList(new OrderUuidContainsKeywordsPredicate(Arrays.asList(uuidKeywords)));

        assertOrderCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(EMILY, SIMON), model.getFilteredOrderList());
    }

    /**
     * Parses {@code userInput} into a {@code PhoneContainsKeywordsPredicate}.
     */
    private PhoneContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] phoneKeywords = userInput.trim().split("\\s+");
        return new PhoneContainsKeywordsPredicate(Arrays.asList(phoneKeywords));
    }
}
