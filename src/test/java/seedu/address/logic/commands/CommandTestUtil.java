package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENDTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STARTTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.EditPersonDescriptor;
import seedu.address.logic.EditTaskDescriptor;
import seedu.address.logic.commands.task.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.name.PersonNameContainsKeywordsPredicate;
import seedu.address.model.name.TaskNameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;

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
    public static final String VALID_NOTE_AMY = "Amy";
    public static final String VALID_NOTE_BOB = "Bob";

    public static final String VALID_NAME_TASK1 = "Task 1";
    public static final String VALID_NAME_TASK2 = "Task 2";
    public static final String VALID_DATE_TASK1 = "16-05-2022";
    public static final String VALID_DATE_TASK2 = "04-08-2022";
    public static final String VALID_STARTTIME_TASK1 = "09:00";
    public static final String VALID_STARTTIME_TASK2 = "10:00";
    public static final String VALID_ENDTIME_TASK1 = "12:00";
    public static final String VALID_ENDTIME_TASK2 = "13:00";
    public static final String VALID_TAG_EVENT = "Event";
    public static final String VALID_TAG_MEET = "Meet";
    public static final String VALID_PERSON1 = "Amy Bee";
    public static final String VALID_PERSON2 = "Bob Choo";



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

    public static final String NAME_DESC_TASK1 = " " + PREFIX_NAME + VALID_NAME_TASK1;
    public static final String NAME_DESC_TASK2 = " " + PREFIX_NAME + VALID_NAME_TASK2;
    public static final String DATE_DESC_TASK1 = " " + PREFIX_DATE + VALID_DATE_TASK1;
    public static final String DATE_DESC_TASK2 = " " + PREFIX_DATE + VALID_DATE_TASK2;
    public static final String STARTTIME_DESC_TASK1 = " " + PREFIX_STARTTIME + VALID_STARTTIME_TASK1;
    public static final String STARTTIME_DESC_TASK2 = " " + PREFIX_STARTTIME + VALID_STARTTIME_TASK2;
    public static final String ENDTIME_DESC_TASK1 = " " + PREFIX_ENDTIME + VALID_ENDTIME_TASK1;
    public static final String ENDTIME_DESC_TASK2 = " " + PREFIX_ENDTIME + VALID_ENDTIME_TASK2;
    public static final String TAG_DESC_EVENT = " " + PREFIX_TAG + VALID_TAG_EVENT;
    public static final String TAG_DESC_MEET = " " + PREFIX_TAG + VALID_TAG_MEET;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String INVALID_TASK_NAME_DESC = " " + PREFIX_NAME + "$hareholder"; // '$' not allowed in names
    public static final String INVALID_TASK_DATE_DESC = " " + PREFIX_DATE + "24/02/2022"; // DD-MM-YYYY format required
    public static final String INVALID_TASK_STARTTIME_DESC = " " + PREFIX_STARTTIME + "2pm"; // MM:HH format required
    public static final String INVALID_TASK_ENDTIME_DESC = " " + PREFIX_ENDTIME + "1030"; // MM:HH format required
    public static final String INVALID_TASK_TAG_DESC = " " + PREFIX_TAG + "test!"; // '!' not allowed in tags


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditPersonDescriptor DESC_AMY;
    public static final EditPersonDescriptor DESC_BOB;

    public static final EditTaskDescriptor DESC_TASK1;
    public static final EditTaskDescriptor DESC_TASK2;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).withMiscellaneous(VALID_NOTE_AMY).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withMiscellaneous(VALID_NOTE_BOB).build();
        DESC_TASK1 = new EditTaskDescriptorBuilder().withName(VALID_NAME_TASK1)
                .withDate(VALID_DATE_TASK1).withStartTime(VALID_STARTTIME_TASK1)
                .withEndTime(VALID_ENDTIME_TASK1).withTags(VALID_TAG_EVENT).withPersons(VALID_PERSON1).build();
        DESC_TASK2 = new EditTaskDescriptorBuilder().withName(VALID_NAME_TASK2)
                .withDate(VALID_DATE_TASK2).withStartTime(VALID_STARTTIME_TASK2)
                .withEndTime(VALID_ENDTIME_TASK2).withTags(VALID_TAG_EVENT).withPersons(VALID_PERSON2).build();
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
        model.updateFilteredPersonList(new PersonNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the tasks with the given {@code Name} in the
     * {@code model}'s task book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitName = task.getName().fullName.split("\\s+");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTaskList().size()); // The task book contains only unique names
    }

}
