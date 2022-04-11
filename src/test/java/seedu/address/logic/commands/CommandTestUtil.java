package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DISH;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.NameCustomerContainsKeywordsPredicate;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDishContainsKeywordsPredicate;
import seedu.address.testutil.EditCustomerDescriptorBuilder;
import seedu.address.testutil.EditDishDescriptorBuilder;
import seedu.address.testutil.EditDriverDescriptorBuilder;
import seedu.address.testutil.EditOrderDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BAREL = "Barel Ran";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "81234567";
    public static final String VALID_PHONE_BAREL = "93021872";
    public static final String VALID_PHONE_BOB = "91234567";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_DISH_NAME_FRIES = "Fries";
    public static final String VALID_DISH_NAME_DONUT = "Donut";
    public static final String VALID_DISH_PRICE_FRIES = "3.50";
    public static final String VALID_DISH_PRICE_DONUT = "4.00";

    public static final String DISH_NAME_DESC_DONUT = " " + PREFIX_NAME + VALID_DISH_NAME_DONUT;
    public static final String DISH_NAME_DESC_FRIES = " " + PREFIX_NAME + VALID_DISH_NAME_FRIES;
    public static final String PRICE_DESC_DONUT = " " + PREFIX_PRICE + VALID_DISH_PRICE_DONUT;
    public static final String PRICE_DESC_FRIES = " " + PREFIX_PRICE + VALID_DISH_PRICE_FRIES;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BAREL = " " + PREFIX_NAME + VALID_NAME_BAREL;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BAREL = " " + PREFIX_PHONE + VALID_PHONE_BAREL;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String DISH_DESC_AMY = " " + PREFIX_DISH + "Sushi";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PRICE_DESC_NO_DP = " " + PREFIX_PRICE + "1"; // no 2 d.p.
    public static final String INVALID_PRICE_DESC_TOO_HIGH = " " + PREFIX_PRICE + "100000.00"; // above 99999.99
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCustomerCommand.EditCustomerDescriptor DESC_AMY;
    public static final EditCustomerCommand.EditCustomerDescriptor DESC_BOB;
    public static final EditOrderCommand.EditOrderDescriptor DESC_AMY_ORDER;
    public static final EditOrderCommand.EditOrderDescriptor DESC_BOB_ORDER;
    public static final EditDishCommand.EditDishDescriptor DESC_FRIES;
    public static final EditDishCommand.EditDishDescriptor DESC_DONUT;
    public static final EditDriverCommand.EditDriverDescriptor DESC_AMY_DRIVER;
    public static final EditDriverCommand.EditDriverDescriptor DESC_BOB_DRIVER;

    static {
        DESC_AMY = new EditCustomerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withAddress(VALID_ADDRESS_AMY)
                .build();
        DESC_BOB = new EditCustomerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withAddress(VALID_ADDRESS_BOB)
                .build();
        DESC_AMY_DRIVER = new EditDriverDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).build();
        DESC_BOB_DRIVER = new EditDriverDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        DESC_AMY_ORDER = new EditOrderDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        DESC_BOB_ORDER = new EditOrderDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        DESC_FRIES = new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_FRIES)
                .withPrice(VALID_DISH_PRICE_FRIES).build();
        DESC_DONUT = new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_DONUT)
                .withPrice(VALID_DISH_PRICE_DONUT).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered customer list and selected customer in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Customer> expectedFilteredList = new ArrayList<>(actualModel.getFilteredCustomerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredCustomerList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the customer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCustomerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCustomerList().size());

        Customer customer = model.getFilteredCustomerList().get(targetIndex.getZeroBased());
        final String[] splitName = customer.getName().fullName.split("\\s+");
        model.updateFilteredCustomerList(new NameCustomerContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCustomerList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the dish at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showDishAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredDishList().size());

        Dish dish = model.getFilteredDishList().get(targetIndex.getZeroBased());
        final String[] splitName = dish.getName().fullName.split("\\s+");
        model.updateFilteredDishList(new NameDishContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredDishList().size());
    }
}
