package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.exceptions.CommandException;
import woofareyou.logic.parser.CliSyntax;
import woofareyou.model.AddressBook;
import woofareyou.model.Model;
import woofareyou.model.pet.NameContainsKeywordsPredicate;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.Assert;
import woofareyou.testutil.EditPetDescriptorBuilder;
import woofareyou.testutil.PresentAttendanceDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_NAME_BOB_WITH_SPACES = "Bob    Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_OWNER_NAME_AMY = "Sarah Lee";
    public static final String VALID_OWNER_NAME_BOB = "Bob Lee";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "123 Bobby Street";
    public static final String VALID_ADDRESS_BOBA = "123, Jurong West Ave 6, #08-111";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friends";
    public static final String VALID_DIET_AMY = "No meat for Amy for one month.";
    public static final String VALID_DIET_BOB = "No dietary restrictions";
    public static final String VALID_CHARGEAMT_AMY = "200.50";
    public static final String VALID_CHARGEAMT_BOB = "400.50";
    public static final String VALID_CHARGEDATE_AMY = "03-2022";
    public static final String VALID_CHARGEDATE_BOB = "03-2021";
    public static final String INVALID_CHARGEDATE_AMY = "22-22";
    public static final String INVALID_CHARGEAMT_AMY = "200.509";
    public static final String VALID_CHARGE_AMY = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR + VALID_CHARGEDATE_AMY
            + " " + CliSyntax.PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_NO_CHARGE_DATE = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR
            + " " + CliSyntax.PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_NO_CHARGE_AMOUNT = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR
            + VALID_CHARGEDATE_AMY + " " + CliSyntax.PREFIX_CHARGE;
    public static final String INVALID_CHARGE_AMY_INVALID_CHARGE_DATE = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR
            + INVALID_CHARGEDATE_AMY + " " + CliSyntax.PREFIX_CHARGE + VALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_INVALID_CHARGE_AMOUNT = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR
            + VALID_CHARGEDATE_AMY + " " + CliSyntax.PREFIX_CHARGE + INVALID_CHARGEAMT_AMY;
    public static final String INVALID_CHARGE_AMY_INVALID_ARGS = " " + CliSyntax.PREFIX_CHARGE_MONTH_YEAR
            + INVALID_CHARGEDATE_AMY + " " + CliSyntax.PREFIX_CHARGE + INVALID_CHARGEAMT_AMY;





    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String OWNER_NAME_DESC_AMY = " " + CliSyntax.PREFIX_OWNER_NAME + VALID_OWNER_NAME_AMY;
    public static final String OWNER_NAME_DESC_BOB = " " + CliSyntax.PREFIX_OWNER_NAME + VALID_OWNER_NAME_BOB;
    public static final String ADDRESS_DESC_AMY = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    // '!' not allowed in ownerNames
    public static final String INVALID_OWNER_NAME_DESC = " " + CliSyntax.PREFIX_OWNER_NAME + "bob!yahoo";
    public static final String INVALID_ADDRESS_DESC = " " + CliSyntax.PREFIX_ADDRESS;
    // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPetDescriptor DESC_AMY;
    public static final EditCommand.EditPetDescriptor DESC_BOB;

    public static final PresentAttendanceCommand.PresentAttendanceDescriptor DESC_WITH_TRANSPORT_ARRANGEMENT;
    public static final PresentAttendanceCommand.PresentAttendanceDescriptor DESC_WITHOUT_TRANSPORT_ARRANGEMENT;

    static {
        DESC_AMY = new EditPetDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withOwnerName(VALID_OWNER_NAME_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withOwnerName(VALID_OWNER_NAME_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_WITH_TRANSPORT_ARRANGEMENT = new PresentAttendanceDescriptorBuilder()
            .withDate("2022-03-27").withPickUpTime("09:00").withDropOffTime("17:30")
            .build();
        DESC_WITHOUT_TRANSPORT_ARRANGEMENT = new PresentAttendanceDescriptorBuilder()
            .withDate("2022-03-27")
            .build();
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
     * - the address book, filtered pet list and selected pet in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Pet> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPetList());

        Assert.assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPetList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the pet at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPetAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPetList().size());

        Pet pet = model.getFilteredPetList().get(targetIndex.getZeroBased());
        final String[] splitName = pet.getName().fullName.split("\\s+");
        model.updateFilteredPetList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPetList().size());
    }

}
