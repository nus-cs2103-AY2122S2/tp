package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_CONTACTED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_DETAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCE_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.NameContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.EditClientDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final int VALID_INDEX = 0;

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_BIRTHDAY_AMY = "10-12-2001";
    public static final String VALID_BIRTHDAY_BOB = "21-03-1999";
    public static final String VALID_LAST_CONTACTED_AMY = "06-01-2022 12:00";
    public static final String VALID_LAST_CONTACTED_BOB = "21-03-2022 21:03";


    public static final String VALID_POLICY_NAME_INSURANCE = "Insurance Policy";
    public static final String VALID_POLICY_NAME_INVESTMENT = "Investment Policy";
    public static final String VALID_COMPANY_INSURANCE = "Insurance Company";
    public static final String VALID_COMPANY_INVESTMENT = "Investment Company";
    public static final String VALID_POLICY_MANAGER_INSURANCE = "Ari";
    public static final String VALID_POLICY_MANAGER_INVESTMENT = "Elston";
    public static final String VALID_PREMIUM_INSURANCE = "1000";
    public static final String VALID_PREMIUM_INVESTMENT = "2000";
    public static final String VALID_POLICY_INDEX_INSURANCE = "1";
    public static final String VALID_POLICY_INDEX_INVESTMENT = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String BIRTHDAY_DESC_AMY = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_AMY;
    public static final String BIRTHDAY_DESC_BOB = " " + PREFIX_BIRTHDAY + VALID_BIRTHDAY_BOB;
    public static final String LAST_CONTACTED_DESC_AMY = " " + PREFIX_LAST_CONTACTED + VALID_LAST_CONTACTED_AMY;
    public static final String LAST_CONTACTED_DESC_BOB = " " + PREFIX_LAST_CONTACTED + VALID_LAST_CONTACTED_BOB;

    public static final String POLICY_NAME_DESC_INSURANCE = " " + PREFIX_NAME + VALID_POLICY_NAME_INSURANCE;
    public static final String POLICY_NAME_DESC_INVESTMENT = " " + PREFIX_NAME + VALID_POLICY_NAME_INVESTMENT;
    public static final String COMPANY_DESC_INSURANCE = " " + PREFIX_COMPANY + VALID_COMPANY_INSURANCE;
    public static final String COMPANY_DESC_INVESTMENT = " " + PREFIX_COMPANY + VALID_COMPANY_INVESTMENT;
    public static final String POLICY_MANAGER_DESC_INSURANCE =
            " " + PREFIX_POLICY_MANAGER + VALID_POLICY_MANAGER_INSURANCE;
    public static final String POLICY_MANAGER_DESC_INVESTMENT =
            " " + PREFIX_POLICY_MANAGER + VALID_POLICY_MANAGER_INVESTMENT;
    public static final String PREMIUM_DESC_INSURANCE = " " + PREFIX_PREMIUM + VALID_PREMIUM_INSURANCE;
    public static final String PREMIUM_DESC_INVESTMENT = " " + PREFIX_PREMIUM + VALID_PREMIUM_INVESTMENT;
    public static final String POLICY_INDEX_DESC_INSURANCE = " " + PREFIX_POLICY_INDEX + VALID_POLICY_INDEX_INSURANCE;
    public static final String POLICY_INDEX_DESC_INVESTMENT = " " + PREFIX_POLICY_INDEX + VALID_POLICY_INDEX_INVESTMENT;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_BIRTHDAY_DESC = " " + PREFIX_BIRTHDAY + "21/03/2001";
    public static final String INVALID_LAST_CONTACTED_DESC = " " + PREFIX_LAST_CONTACTED + "21/03/2022 21/03";
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "James&";
    public static final String INVALID_POLICY_MANAGER_DESC = " " + PREFIX_POLICY_MANAGER + "James&";
    public static final String INVALID_PREMIUM_DESC = " " + PREFIX_PREMIUM + "abc";

    public static final String VALID_PREFERENCE_CATEGORY = "Sports";
    public static final String VALID_PREFERENCE_VALUE = "Tennis";
    public static final String VALID_PREFERENCE_CATEGORY_2 = "Drink";
    public static final String VALID_PREFERENCE_VALUE_2 = "Coke";
    public static final String PREFERENCE_CAT_PREF = " " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY
            + " " + PREFIX_PREFERENCE_DETAIL + VALID_PREFERENCE_VALUE;
    public static final String PREFERENCE_INDEX_CAT = "1 " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY;
    public static final String PREFERENCE_INDEX_VALUE = "1 " + PREFIX_PREFERENCE_DETAIL + VALID_PREFERENCE_VALUE;
    public static final String PREFERENCE_INDEX_CAT_VALUE = "1 " + PREFIX_PREFERENCE_KEY + VALID_PREFERENCE_CATEGORY
            + " " + PREFIX_PREFERENCE_DETAIL + VALID_PREFERENCE_VALUE;


    public static final String INVALID_PREFERENCE_CATEGORY = "None";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_START_DATETIME_INPUT = "01-02-2023 10:00";
    public static final String VALID_START_DATETIME_STORE = "2023-02-01T10:00:00.000000";
    public static final String VALID_START_DATETIME_STORE_FUTURE = "2099-02-01T10:00:00.000000";
    public static final String VALID_END_DATETIME_INPUT = "01-02-2023 11:00";
    public static final String VALID_END_DATETIME_STORE = "2023-02-01T11:00:00.000000";
    public static final String VALID_END_DATETIME_STORE_FUTURE = "2099-02-01T11:00:00.000000";
    public static final String VALID_LABEL = "Dinner";
    public static final String VALID_MEETING_INDEX = "1";
    public static final String VALID_CLIENT_INDEX = "1";

    public static final String INVALID_DATETIME_INPUT = "1-2-23 10:00";
    public static final String INVALID_DATETIME_RANGE_INPUT = "31-02-2022 10:00";

    public static final EditCommand.EditClientDescriptor DESC_AMY;
    public static final EditCommand.EditClientDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withBirthday(VALID_BIRTHDAY_AMY).withLastContacted(VALID_LAST_CONTACTED_AMY).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
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
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Client> expectedFilteredList = new ArrayList<>(actualModel.getFilteredClientList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredClientList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        model.updateFilteredMeetingList(meeting1 -> meeting1.equals(meeting), true);

        assertEquals(1, model.getFilteredMeetingList().size());
    }

}
