package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.SellerAddressBook;
import seedu.address.model.property.House;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToBuy;
import seedu.address.model.seller.Seller;
import seedu.address.model.seller.SellerNameContainsKeywordsPredicate;
import seedu.address.testutil.EditSellerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class SellerCommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_CHAD = "Chad chadson";
    public static final String VALID_NAME_WEIRD = "123BBBaln123  23asdsd";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_CHAD = "33333333";
    public static final String VALID_PHONE_WEIRD = "00000000";
    public static final PropertyToBuy VALID_PROPERTY_BUY_CHAD = new PropertyToBuy(new House(HouseType.HDB_FLAT,
            new Location("jurong")), new PriceRange(1000, 2000));
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_REMARK_AMY = "A remark";
    public static final String VALID_REMARK_BOB = "B remark";
    public static final String VALID_APPOINTMENT_AMY = "2022-09-08-12-00";
    public static final String VALID_APPOINTMENT_BOB = "2022-09-03-09-10";
    public static final String VALID_APPOINTMENT_CHAD = "2023-09-03-09-10";


    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_WEIRD = " " + PREFIX_NAME + VALID_NAME_WEIRD;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String PHONE_DESC_WEIRD = " " + PREFIX_PHONE + VALID_PHONE_WEIRD;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_NAME_DESC_2 = " " + PREFIX_NAME + "James pr/1,2";
    public static final String INVALID_NAME_DESC_3 = " " + PREFIX_NAME + "Jamesn/David";
    public static final String INVALID_NAME_DESC_4 = " " + PREFIX_NAME + "Jamesp/90872";
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_PHONE_DESC_2 = " " + PREFIX_PHONE + "911 09"; // spaces not allowed in phones
    public static final String INVALID_PHONE_DESC_3 = " " + PREFIX_PHONE + "91"; // too short phone number
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TAG_DESC_2 = " " + PREFIX_TAG + "hubby 123"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditSellerCommand.EditSellerDescriptor DESC_AMY;
    public static final EditSellerCommand.EditSellerDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditSellerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withTags(VALID_TAG_FRIEND)
                .withAppointment(VALID_APPOINTMENT_AMY).build();
        DESC_BOB = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withAppointment(VALID_APPOINTMENT_BOB).build();
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
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        SellerAddressBook expectedSellerAddressBook = new SellerAddressBook(actualModel.getSellerAddressBook());
        List<Seller> expectedFilteredSellerList = new ArrayList<>(actualModel.getFilteredSellerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSellerAddressBook, actualModel.getSellerAddressBook());
        assertEquals(expectedFilteredSellerList, actualModel.getFilteredSellerList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered seller list and selected seller in {@code actualModel} remain unchanged
     */
    public static void assertSellerCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.

        SellerAddressBook expectedSellerAddressBook = new SellerAddressBook(actualModel.getSellerAddressBook());
        List<Seller> expectedFilteredSellerList = new ArrayList<>(actualModel.getFilteredSellerList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedSellerAddressBook, actualModel.getSellerAddressBook());
        assertEquals(expectedFilteredSellerList, actualModel.getFilteredSellerList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the seller at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showSellerAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredSellerList().size());

        Seller seller = model.getFilteredSellerList().get(targetIndex.getZeroBased());
        final String[] splitName = seller.getName().fullName.split("\\s+");
        model.updateFilteredSellerList(new SellerNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredSellerList().size());
    }

}
