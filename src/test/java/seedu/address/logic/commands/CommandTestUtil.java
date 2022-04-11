package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGENDA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRONOUN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.meeting.AgendaContainsKeywordsPredicate;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.UpdateMeetingDescriptor;
import seedu.address.model.person.EditPersonDescriptor;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.UpdateMeetingDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_COMPANY_AMY = "KFC";
    public static final String VALID_COMPANY_BOB = "MCDONALDS";
    public static final String VALID_JOB_TITLE_AMY = "Cook";
    public static final String VALID_JOB_TITLE_BOB = "Cashier";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_PRONOUN_SHE = "she";
    public static final String VALID_PRONOUN_HIM = "him";
    public static final String VALID_PRONOUN_THEY = "they";

    //@Todo CHANGE THIS TO HAVE ANOTHER WHTIESPACE AFTER PREFIX
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String COMPANY_DESC_AMY = " " + PREFIX_COMPANY + VALID_COMPANY_AMY;
    public static final String COMPANY_DESC_BOB = " " + PREFIX_COMPANY + VALID_COMPANY_BOB;
    public static final String JOB_TITLE_DESC_AMY = " " + PREFIX_JOBTITLE + VALID_JOB_TITLE_AMY;
    public static final String JOB_TITLE_DESC_BOB = " " + PREFIX_JOBTITLE + VALID_JOB_TITLE_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String PRONOUN_DESC_HIM = " " + PREFIX_PRONOUN + VALID_PRONOUN_HIM;
    public static final String PRONOUN_DESC_SHE = " " + PREFIX_PRONOUN + VALID_PRONOUN_SHE;
    public static final String PRONOUN_DESC_THEY = " " + PREFIX_PRONOUN + VALID_PRONOUN_THEY;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    // empty string not allowed for addresses
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS + "  l/home";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String VALID_AGENDA_QUARTERLY = "Quarterly meeting";
    public static final String VALID_AGENDA_PROJECT = "Project meeting";
    public static final String VALID_ATTENDEE_QUARTERLY = "1";
    public static final String VALID_ATTENDEE_PROJECT = "2";
    public static final String VALID_TIME_QUARTERLY = "01-01-2023 10:00";
    public static final String VALID_TIME_PROJECT = "28-05-2023 15:00";
    public static final String VALID_PLACE_QUARTERLY = "Conference Room #03-26";
    public static final String VALID_PLACE_PROJECT = "Study Room 3, Cambridge Hall";

    public static final String AGENDA_DESC_QUARTERLY = " " + PREFIX_AGENDA + VALID_AGENDA_QUARTERLY;
    public static final String AGENDA_DESC_PROJECT = " " + PREFIX_AGENDA + VALID_AGENDA_PROJECT;
    public static final String ATTENDEE_DESC_PROJECT = " " + PREFIX_ATTENDEES_INDEX + VALID_ATTENDEE_PROJECT;
    public static final String TIME_DESC_QUARTERLY = " " + PREFIX_TIME + VALID_TIME_QUARTERLY;
    public static final String TIME_DESC_PROJECT = " " + PREFIX_TIME + VALID_TIME_PROJECT;
    public static final String PLACE_DESC_QUARTERLY = " " + PREFIX_PLACE + VALID_PLACE_QUARTERLY;
    public static final String PLACE_DESC_PROJECT = " " + PREFIX_PLACE + VALID_PLACE_PROJECT;

    public static final String INVALID_AGENDA_DESC = " " + PREFIX_AGENDA + " "; // Agenda cannot be empty
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "15:00 01-01-2023"; //Wrong order of date & time
    public static final String EMPTY_TIME_DESC = " " + PREFIX_TIME + " ";
    public static final String EMPTY_PLACE_DESC = " " + PREFIX_PLACE + " ";
    public static final String EMPTY_ATTENDEE_DESC = " " + PREFIX_ATTENDEES_INDEX + " ";
    public static final String PAST_TIME_DESC = " " + PREFIX_TIME + "22-08-2021 12:30";

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;
    public static final UpdateMeetingDescriptor DESC_QUARTERLY;
    public static final UpdateMeetingDescriptor DESC_PROJECT;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withCompany(VALID_COMPANY_AMY).withJobTitle(VALID_JOB_TITLE_AMY)
                .withNumbers(VALID_PHONE_AMY).withEmails(VALID_EMAIL_AMY).withAddresses(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withPronouns(VALID_PRONOUN_SHE).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withCompany(VALID_COMPANY_BOB).withJobTitle(VALID_JOB_TITLE_BOB)
                .withNumbers(VALID_PHONE_BOB).withEmails(VALID_EMAIL_BOB).withAddresses(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withPronouns(VALID_PRONOUN_THEY, VALID_PRONOUN_HIM).build();

        DESC_QUARTERLY = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_QUARTERLY)
                .withAttendees(VALID_ATTENDEE_QUARTERLY).withTime(VALID_TIME_QUARTERLY)
                .withPlace(VALID_PLACE_QUARTERLY).build();
        DESC_PROJECT = new UpdateMeetingDescriptorBuilder().withAgenda(VALID_AGENDA_PROJECT)
                .withAttendees(VALID_ATTENDEE_QUARTERLY).withTime(VALID_TIME_PROJECT)
                .withPlace(VALID_PLACE_PROJECT).build();
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
        MeetingBook expectedMeetingBook = new MeetingBook(actualModel.getMeetingBook());
        List<Person> expectedFilteredPersonList = new ArrayList<>(actualModel.getFilteredPersonList());
        List<Meeting> expectedFilteredMeetingList = new ArrayList<>(actualModel.getFilteredMeetingList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedMeetingBook, actualModel.getMeetingBook());
        assertEquals(expectedFilteredPersonList, actualModel.getFilteredPersonList());
        assertEquals(expectedFilteredMeetingList, actualModel.getFilteredMeetingList());
    }

    /**
     * Updates {@code model}'s filtered person list to show only the person at the given {@code targetIndex} in the
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
     * Updates {@code model}'s filtered meeting list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s meeting book.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        final String[] splitAgenda = meeting.getAgenda().description.split("\\s+");
        model.updateFilteredMeetingList(new AgendaContainsKeywordsPredicate(Arrays.asList(splitAgenda[0])));

        assertEquals(1, model.getFilteredMeetingList().size());
    }
}
