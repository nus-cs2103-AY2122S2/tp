package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.InsuranceBook;
import seedu.address.model.Model;
import seedu.address.model.RecordBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DescriptionContainsKeywordsPredicate;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.TitleContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.model.record.RecordContainsKeywordsPredicate;
import seedu.address.testutil.EditAppointmentDescriptorBuilder;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    public static final String VALID_DESCRIPTION_FELIX = "Meet Felix at Orchard";
    public static final String VALID_DESCRIPTION_TED = "Meet Ted at Chinatown";
    public static final String VALID_DATETIME_FELIX = "06-12-2022 18:00";
    public static final String VALID_DATETIME_TED = "02-04-2022 12:00";

    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_FELIX;
    public static final EditAppointmentCommand.EditAppointmentDescriptor DESC_TED;
    public static final String VALID_DESC_APPOINTMENT1 = "Meet James for health insurance";
    public static final String VALID_DATETIME_APPOINTMENT1 = "02-03-2023 15:00";
    public static final String VALID_DESC_APPOINTMENT2 = "Meet Alice for car insurance";
    public static final String VALID_DATETIME_APPOINTMENT2 = "02-04-2023 19:00";
    public static final String VALID_DESC_APPOINTMENT3 = "Meet Tom for insurance renewal";
    public static final String VALID_DATETIME_APPOINTMENT3 = "04-03-2023 18:00";
    public static final String INVALID_DATETIME_APPOINTMENT = " " + PREFIX_APPT_DATETIME + "04-03-2023 1800";
    public static final String INVALID_DESC_APPOINTMENT = " " + PREFIX_APPT_DESCRIPTION + " ";

    public static final String DESC_APPOINTMENT1 = " " + PREFIX_APPT_DESCRIPTION + VALID_DESC_APPOINTMENT1;
    public static final String DATETIME_APPOINTMENT1 = " " + PREFIX_APPT_DATETIME + VALID_DATETIME_APPOINTMENT1;
    public static final String DESC_APPOINTMENT2 = " " + PREFIX_APPT_DESCRIPTION + VALID_DESC_APPOINTMENT2;
    public static final String DATETIME_APPOINTMENT2 = " " + PREFIX_APPT_DATETIME + VALID_DATETIME_APPOINTMENT2;
    public static final String DESC_APPOINTMENT3 = " " + PREFIX_APPT_DESCRIPTION + VALID_DESC_APPOINTMENT3;
    public static final String DATETIME_APPOINTMENT3 = " " + PREFIX_APPT_DATETIME + VALID_DATETIME_APPOINTMENT3;

    public static final String VALID_TITLE_INSURANCE1 = "Health";
    public static final String VALID_TITLE_INSURANCE2 = "Car";
    public static final String VALID_PRICE_INSURANCE1 = "123";
    public static final String VALID_PRICE_INSURANCE2 = "111";
    public static final String INVALID_TITLE_INSURANCE = " " + PREFIX_TITLE + " ";
    public static final String INVALID_PRICE_INSURANCE = " " + PREFIX_PRICE + "two";
    public static final String TITLE_INSURANCE1 = " " + PREFIX_TITLE + VALID_TITLE_INSURANCE1;
    public static final String PRICE_INSURANCE1 = " " + PREFIX_PRICE + VALID_PRICE_INSURANCE1;
    public static final String TITLE_INSURANCE2 = " " + PREFIX_TITLE + VALID_TITLE_INSURANCE2;
    public static final String PRICE_INSURANCE2 = " " + PREFIX_PRICE + VALID_PRICE_INSURANCE2;

    public static final String VALID_TITLE_HEART = "Heart";
    public static final String VALID_TITLE_LIFE = "Life";
    public static final String VALID_PRICE_HEART = "300";
    public static final String VALID_PRICE_LIFE = "500";

    public static final EditInsuranceCommand.EditInsuranceDescriptor DESC_HEART;
    public static final EditInsuranceCommand.EditInsuranceDescriptor DESC_LIFE;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_FELIX = new EditAppointmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_FELIX)
                .withDateTime(VALID_DATETIME_FELIX).build();
        DESC_TED = new EditAppointmentDescriptorBuilder().withDescription(VALID_DESCRIPTION_TED)
                .withDateTime(VALID_DATETIME_TED).build();
        DESC_HEART = new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_HEART)
                .withPrice(VALID_PRICE_HEART).build();
        DESC_LIFE = new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_LIFE)
                .withPrice(VALID_PRICE_LIFE).build();
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
            // assertEquals(expectedModel, actualModel);
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
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the insurance book, filtered insurance list and selected insurance in {@code actualModel} remain unchanged
     */
    public static void assertInsuranceCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InsuranceBook expectedInsuranceBook = new InsuranceBook(actualModel.getInsuranceBook());
        List<Insurance> expectedFilteredList = new ArrayList<>(actualModel.getFilteredInsuranceList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInsuranceBook, actualModel.getInsuranceBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredInsuranceList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the record book, filtered record list and selected record in {@code actualModel} remain unchanged
     */
    public static void assertRecordCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RecordBook expectedRecordBook = new RecordBook(actualModel.getRecordBook());
        List<Record> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecordList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRecordBook, actualModel.getRecordBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecordList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the appointment book, filtered appointment list
     * and selected appointment in {@code actualModel} remain unchanged
     */
    public static void assertAppointmentCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AppointmentBook expectedAppointmentBook = new AppointmentBook(actualModel.getAppointmentBook());
        List<Appointment> expectedFilteredList = new ArrayList<>(actualModel.getFilteredAppointmentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAppointmentBook, actualModel.getAppointmentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredAppointmentList());
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

    /**
     * Updates {@code model}'s filtered list to show only the appointment at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showAppointmentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredAppointmentList().size());

        Appointment appointment = model.getFilteredAppointmentList().get(targetIndex.getZeroBased());
        final String[] splitName = appointment.getDescription().description.split("\\s+");
        model.updateFilteredAppointmentList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitName[1])));

        assertEquals(1, model.getFilteredAppointmentList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Insurance at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showInsuranceAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredInsuranceList().size());

        Insurance insurance = model.getFilteredInsuranceList().get(targetIndex.getZeroBased());
        final String[] splitName = insurance.getTitle().title.split("\\s+");
        model.updateFilteredInsuranceList(new TitleContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredInsuranceList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the Record at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showRecordAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecordList().size());

        Record record = model.getFilteredRecordList().get(targetIndex.getZeroBased());
        final String[] splitName = record.getInsuranceID().id.split("\\s+");
        model.updateFilteredRecordList(new RecordContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecordList().size());
    }
}
