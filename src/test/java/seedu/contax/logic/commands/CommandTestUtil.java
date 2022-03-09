package seedu.contax.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_FILE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.contax.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.exceptions.CommandException;
import seedu.contax.model.AddressBook;
import seedu.contax.model.Model;
import seedu.contax.model.person.NameContainsKeywordsPredicate;
import seedu.contax.model.person.Person;
import seedu.contax.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_CSV_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat.csv";
    public static final String INVALID_BAD_EXTENSION_CSV_FILEPATH = "wrongFile.txt";
    public static final String INVALID_NO_EXTENSION_CSV_FILEPATH = "./src/test/data/ImportCsvTest/ValidContaXFormat";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String VALID_APPOINTMENT_NAME_AMELIA = "Meeting With Amelia";
    public static final String VALID_APPOINTMENT_NAME_ALONE = "Do some work alone";
    public static final String VALID_DATE = "07-10-2022";
    public static final String VALID_DATE2 = "05-10-2022";
    public static final String VALID_TIME = "22:50";
    public static final String VALID_TIME2 = "21:50";
    public static final String VALID_APPOINTMENT_DURATION_HOUR = "60";
    public static final String VALID_APPOINTMENT_DURATION_MINUTE = "1";

    public static final String INVALID_APPOINTMENT_NAME = "Meeting??";
    public static final String INVALID_DATE = "32-10-2022";
    public static final String INVALID_TIME = "25:30";
    public static final String INVALID_APPOINTMENT_DURATION = "-1";

    public static final String APPOINTMENT_NAME_AMELIA = " " + PREFIX_NAME + VALID_APPOINTMENT_NAME_AMELIA;
    public static final String APPOINTMENT_NAME_ALONE = " " + PREFIX_NAME + VALID_APPOINTMENT_NAME_ALONE;
    public static final String APPOINTMENT_DATE = " " + PREFIX_DATE + VALID_DATE;
    public static final String APPOINTMENT_DATE2 = " " + PREFIX_DATE + VALID_DATE2;
    public static final String APPOINTMENT_TIME = " " + PREFIX_TIME + VALID_TIME;
    public static final String APPOINTMENT_TIME2 = " " + PREFIX_TIME + VALID_TIME2;
    public static final String APPOINTMENT_DURATION = " " + PREFIX_DURATION + VALID_APPOINTMENT_DURATION_HOUR;
    public static final String APPOINTMENT_DURATION2 = " " + PREFIX_DURATION
            + VALID_APPOINTMENT_DURATION_MINUTE;
    public static final String APPOINTMENT_FIRST_PERSON = " " + PREFIX_PERSON + "1";
    public static final String APPOINTMENT_SECOND_PERSON = " " + PREFIX_PERSON + "2";

    public static final String INVALID_APPOINTMENT_NAME_DESC = " " + PREFIX_NAME + INVALID_APPOINTMENT_NAME;
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + INVALID_DATE;
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + INVALID_TIME;
    public static final String INVALID_APPOINTMENT_DURATION_DESC = " " + PREFIX_DURATION
            + INVALID_APPOINTMENT_DURATION;
    public static final String INVALID_APPOINTMENT_PERSON = " " + PREFIX_PERSON + "-1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String COMMAND_VALID_CSV_FILEPATH = " " + PREFIX_FILE + VALID_CSV_FILEPATH;
    public static final String COMMAND_INVALID_BAD_EXTENSION_FILEPATH = " " + PREFIX_FILE
            + INVALID_BAD_EXTENSION_CSV_FILEPATH;
    public static final String COMMAND_INVALID_NO_EXTENSION_FILEPATH = " " + PREFIX_FILE
            + INVALID_NO_EXTENSION_CSV_FILEPATH;
    public static final String COMMAND_CSV_NAMEPOSITION = " " + PREFIX_NAME + "1";
    public static final String COMMAND_CSV_NAMEPOSITION_CLASH = " " + PREFIX_NAME + "2";
    public static final String COMMAND_CSV_PHONEPOSITION = " " + PREFIX_PHONE + "2";
    public static final String COMMAND_CSV_PHONEPOSITION_CLASH = " " + PREFIX_PHONE + "1";
    public static final String COMMAND_CSV_EMAILPOSITION = " " + PREFIX_EMAIL + "3";
    public static final String COMMAND_CSV_EMAILPOSITION_CLASH = " " + PREFIX_EMAIL + "1";
    public static final String COMMAND_CSV_ADDRESSPOSITION = " " + PREFIX_ADDRESS + "4";
    public static final String COMMAND_CSV_ADDRESSPOSITION_CLASH = " " + PREFIX_ADDRESS + "1";
    public static final String COMMAND_CSV_TAGPOSITION = " " + PREFIX_TAG + "5";
    public static final String COMMAND_CSV_TAGPOSITION_CLASH = " " + PREFIX_TAG + "1";

    public static final String COMMAND_CSV_INVALID_NAMEPOSITION = " " + PREFIX_NAME + "text";
    public static final String COMMAND_CSV_INVALID_PHONEPOSITION = " " + PREFIX_PHONE + "text";
    public static final String COMMAND_CSV_INVALID_EMAILPOSITION = " " + PREFIX_EMAIL + "text";
    public static final String COMMAND_CSV_INVALID_ADDRESSPOSITION = " " + PREFIX_ADDRESS + "text";
    public static final String COMMAND_CSV_INVALID_TAGPOSITION = " " + PREFIX_TAG + "text";

    public static final String COMMAND_CSV_NEGATIVE_NAMEPOSITION = " " + PREFIX_NAME + "-1";
    public static final String COMMAND_CSV_NEGATIVE_PHONEPOSITION = " " + PREFIX_PHONE + "-1";
    public static final String COMMAND_CSV_NEGATIVE_EMAILPOSITION = " " + PREFIX_EMAIL + "-1";
    public static final String COMMAND_CSV_NEGATIVE_ADDRESSPOSITION = " " + PREFIX_ADDRESS + "-1";
    public static final String COMMAND_CSV_NEGATIVE_TAGPOSITION = " " + PREFIX_TAG + "-1";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
