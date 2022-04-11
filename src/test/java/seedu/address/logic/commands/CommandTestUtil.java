package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.ListType;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Person;
import seedu.address.model.entry.predicate.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditCompanyDescriptorBuilder;
import seedu.address.testutil.EditEventDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_COMPANY_BIG_BANK = "Big Bank";
    public static final String VALID_COMPANY_JANICE_STREET = "Janice Street";
    public static final String VALID_COMPANY_SHOPSG = "shopSG";
    public static final String VALID_EVENT_INTERVIEW_BIG_BANK = "Big Bank Interview";
    public static final String VALID_EVENT_INTERVIEW_JANICE_STREET = "Janice Street Interview";
    public static final String VALID_EVENT_INTERVIEW_SHOPSG = "shopSG Interview";
    public static final String VALID_PHONE_A = "94316789";
    public static final String VALID_PHONE_B = "89245223";
    public static final String VALID_EMAIL_A = "bigbank@example.com";
    public static final String VALID_EMAIL_B = "janicestreet@example.com";
    public static final String VALID_ADDRESS_A = "16 Race Course Road";
    public static final String VALID_ADDRESS_B = "21 Marina Bay Sands";
    public static final String VALID_DATE_A = "2022-03-03";
    public static final String VALID_DATE_B = "2022-05-05";
    public static final String VALID_TIME_A = "12:00";
    public static final String VALID_TIME_B = "13:30";
    public static final String VALID_LOCATION_A = "zoom";
    public static final String VALID_LOCATION_B = "meet";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_INTERVIEW = "interview";
    public static final String VALID_TAG_APPLIED = "applied";
    public static final String VALID_TAG_TECHNICAL = "technical";
    public static final String VALID_TAG_BEHAVIOURAL = "behavioural";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String NAME_DESC_BIG_BANK = " " + PREFIX_NAME + VALID_COMPANY_BIG_BANK;
    public static final String NAME_DESC_JANICE_STREET = " " + PREFIX_NAME + VALID_COMPANY_JANICE_STREET;
    public static final String NAME_DESC_SHOPSG = " " + PREFIX_NAME + VALID_COMPANY_SHOPSG;
    public static final String NAME_DESC_EVENT_INTERVIEW_BIG_BANK = " " + PREFIX_NAME + VALID_EVENT_INTERVIEW_BIG_BANK;
    public static final String NAME_DESC_EVENT_INTERVIEW_JANICE_STREET = " " + PREFIX_NAME
            + VALID_EVENT_INTERVIEW_JANICE_STREET;
    public static final String NAME_DESC_EVENT_INTERVIEW_SHOPSG = " " + PREFIX_NAME + VALID_EVENT_INTERVIEW_SHOPSG;
    public static final String COMPANY_DESC_BIG_BANK = " " + PREFIX_COMPANY + VALID_COMPANY_BIG_BANK;
    public static final String COMPANY_DESC_JANICE_STREET = " " + PREFIX_COMPANY + VALID_COMPANY_JANICE_STREET;
    public static final String COMPANY_DESC_SHOPSG = " " + PREFIX_COMPANY + VALID_COMPANY_SHOPSG;
    public static final String PHONE_DESC_A = " " + PREFIX_PHONE + VALID_PHONE_A;
    public static final String PHONE_DESC_B = " " + PREFIX_PHONE + VALID_PHONE_B;
    public static final String EMAIL_DESC_A = " " + PREFIX_EMAIL + VALID_EMAIL_A;
    public static final String EMAIL_DESC_B = " " + PREFIX_EMAIL + VALID_EMAIL_B;
    public static final String ADDRESS_DESC_A = " " + PREFIX_ADDRESS + VALID_ADDRESS_A;
    public static final String ADDRESS_DESC_B = " " + PREFIX_ADDRESS + VALID_ADDRESS_B;
    public static final String DATE_DESC_A = " " + PREFIX_DATE + VALID_DATE_A;
    public static final String DATE_DESC_B = " " + PREFIX_DATE + VALID_DATE_B;
    public static final String TIME_DESC_A = " " + PREFIX_TIME + VALID_TIME_A;
    public static final String TIME_DESC_B = " " + PREFIX_TIME + VALID_TIME_B;
    public static final String LOCATION_DESC_A = " " + PREFIX_LOCATION + VALID_LOCATION_A;
    public static final String LOCATION_DESC_B = " " + PREFIX_LOCATION + VALID_LOCATION_B;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_INTERVIEW = " " + PREFIX_TAG + VALID_TAG_INTERVIEW;
    public static final String TAG_DESC_APPLIED = " " + PREFIX_TAG + VALID_TAG_APPLIED;
    public static final String TAG_DESC_TECHNICAL = " " + PREFIX_TAG + VALID_TAG_TECHNICAL;
    public static final String TAG_DESC_BEHAVIOURAL = " " + PREFIX_TAG + VALID_TAG_BEHAVIOURAL;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_COMPANY_DESC = " " + PREFIX_COMPANY + "DB$$$"; // '$' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_DATE_DESC = " " + PREFIX_DATE + "test";
    public static final String INVALID_TIME_DESC = " " + PREFIX_TIME + "test";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonCommand.EditPersonDescriptor DESC_AMY;
    public static final EditPersonCommand.EditPersonDescriptor DESC_BOB;

    public static final EditCompanyCommand.EditCompanyDescriptor DESC_BIG_BANK;
    public static final EditCompanyCommand.EditCompanyDescriptor DESC_JANICE_STREET;

    public static final EditEventCommand.EditEventDescriptor DESC_INTERVIEW_BIG_BANK;
    public static final EditEventCommand.EditEventDescriptor DESC_INTERVIEW_JANICE_STREET;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_A).withEmail(VALID_EMAIL_A).withCompanyName(VALID_COMPANY_BIG_BANK)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B).withCompanyName(VALID_COMPANY_JANICE_STREET)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_BIG_BANK = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_BIG_BANK)
                .withEmail(VALID_EMAIL_A).withPhone(VALID_PHONE_A).withEmail(VALID_EMAIL_A)
                .withAddress(VALID_ADDRESS_A).withTags(VALID_TAG_APPLIED).build();
        DESC_JANICE_STREET = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_JANICE_STREET)
                .withEmail(VALID_EMAIL_B).withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B)
                .withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_INTERVIEW).build();
        DESC_INTERVIEW_BIG_BANK = new EditEventDescriptorBuilder().withName(VALID_EVENT_INTERVIEW_BIG_BANK)
                .withCompanyName(VALID_COMPANY_BIG_BANK).withDate(VALID_DATE_A)
                .withTime(VALID_TIME_A).withLocation(VALID_LOCATION_A).withTags(VALID_TAG_TECHNICAL).build();
        DESC_INTERVIEW_JANICE_STREET = new EditEventDescriptorBuilder().withName(VALID_EVENT_INTERVIEW_JANICE_STREET)
                .withCompanyName(VALID_COMPANY_JANICE_STREET).withDate(VALID_DATE_B)
                .withTime(VALID_TIME_B).withLocation(VALID_LOCATION_B).withTags(VALID_TAG_BEHAVIOURAL).build();
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
            expectedModel.equals(actualModel);
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
        model.showPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the company at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showCompanyAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredCompanyList().size());

        Company company = model.getFilteredCompanyList().get(targetIndex.getZeroBased());
        final String[] splitName = company.getName().fullName.split("\\s+");
        model.showCompanyList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredCompanyList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the event at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showEventAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredEventList().size());

        Event event = model.getFilteredEventList().get(targetIndex.getZeroBased());
        final String[] splitName = event.getName().fullName.split("\\s+");
        model.showEventList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredEventList().size());
    }

    public static CommandResult getExpectedListCommandResult(SearchType searchType, ListType listType) {
        String searchTypeString = "";
        String msgSuccess = "";

        switch (searchType) {
        case UNARCHIVED_ONLY:
            searchTypeString = " unarchived";
            break;
        case ARCHIVED_ONLY:
            searchTypeString = " archived";
            break;
        case ALL:
            searchTypeString = "";
            break;
        default:
            // should not reach here
        }

        switch (listType) {
        case PERSON:
            msgSuccess = ListPersonCommand.MESSAGE_SUCCESS;
            break;
        case COMPANY:
            msgSuccess = ListCompanyCommand.MESSAGE_SUCCESS;
            break;
        case EVENT:
            msgSuccess = ListEventCommand.MESSAGE_SUCCESS;
            break;
        default:
            // should not reach here
        }

        return new CommandResult(String.format(msgSuccess, searchTypeString),
                false, false, listType == ListType.PERSON, listType == ListType.COMPANY, listType == ListType.EVENT);
    }

    public static CommandResult getExpectedSortCommandResult(SearchType searchType,
            ListType listType, Ordering ordering) {
        String searchTypeString = "";
        String orderingString = "";
        String msgSuccess = "";

        switch (searchType) {
        case UNARCHIVED_ONLY:
            searchTypeString = " unarchived";
            break;
        case ARCHIVED_ONLY:
            searchTypeString = " archived";
            break;
        case ALL:
            searchTypeString = "";
            break;
        default:
            // should not reach here
        }

        switch (ordering) {
        case ASCENDING:
            orderingString = " in ascending order";
            break;
        case DESCENDING:
            orderingString = " in descending order";
            break;
        default:
            // should not reach here
        }

        switch (listType) {
        case PERSON:
            msgSuccess = SortPersonCommand.MESSAGE_SUCCESS;
            break;
        case COMPANY:
            msgSuccess = SortCompanyCommand.MESSAGE_SUCCESS;
            break;
        case EVENT:
            msgSuccess = SortEventCommand.MESSAGE_SUCCESS;
            break;
        default:
            // should not reach here
        }

        return new CommandResult(String.format(msgSuccess, searchTypeString + orderingString),
                false, false, listType == ListType.PERSON, listType == ListType.COMPANY, listType == ListType.EVENT);
    }
}
