package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_HOURS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION_MINUTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.StudentBook;
import seedu.address.model.student.NameOrTagsContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;

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

    public static final String NAME_DESC_AMY = " " + PREFIX_STUDENT_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_STUDENT_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_STUDENT_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_STUDENT_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_STUDENT_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_STUDENT_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_STUDENT_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_STUDENT_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_STUDENT_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_STUDENT_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_DUPLICATE_PREFIX = "Duplicate prefix "; // Duplicates except tag not allowed
    public static final String INVALID_NAME_DESC = " " + PREFIX_STUDENT_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_STUDENT_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_STUDENT_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = "  "
            + PREFIX_STUDENT_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_STUDENT_TAG + "hubby*"; // '*' not allowed in tags

    public static final String VALID_LESSON_NAME = "Trial lesson for Henry";
    public static final String VALID_LESSON_SUBJECT = "Biology";
    public static final String VALID_LESSON_START_TIME = "18:00";
    public static final String VALID_LESSON_DURATION_HOURS = "2";
    public static final String VALID_LESSON_DURATION_MINUTES = "30";
    public static final String VALID_LESSON_ADDRESS = "Blk 11 Ang Mo Kio Street 74, #11-04";
    // TODO: create a function to generate valid lesson date for next Monday as logic will be implemented to
    //       prevent new lessons from being created in past dates
    public static final String VALID_LESSON_DATE = "1-12-2022";

    public static final String LESSON_NAME_DESC_TRIAL_LESSON = " " + PREFIX_LESSON_NAME + VALID_LESSON_NAME;
    public static final String LESSON_SUBJECT_DESC_BIOLOGY = " " + PREFIX_SUBJECT + VALID_LESSON_SUBJECT;
    public static final String LESSON_ADDRESS_DESC_AMK = " " + PREFIX_LESSON_ADDRESS + VALID_LESSON_ADDRESS;
    public static final String LESSON_DATE_DESC = " " + PREFIX_DATE + VALID_LESSON_DATE;
    public static final String LESSON_START_TIME_DESC_6PM = " " + PREFIX_START_TIME + VALID_LESSON_START_TIME;
    public static final String LESSON_DURATION_HOURS_DESC_2HOUR = " "
            + PREFIX_DURATION_HOURS + VALID_LESSON_DURATION_HOURS;
    public static final String LESSON_DURATION_MINUTES_DESC_30MIN = " "
            + PREFIX_DURATION_MINUTES + VALID_LESSON_DURATION_MINUTES;

    public static final String INVALID_DURATION_MINUTES_NEGATIVE_DESC = " " // negative minutes
            + PREFIX_DURATION_MINUTES + "-1";
    public static final String INVALID_DURATION_MINUTES_EXCEEDS_59_DESC = " " // minutes exceeding 59 not allowed
            + PREFIX_DURATION_MINUTES + "60";
    public static final String INVALID_DURATION_HOURS_NEGATIVE_DESC = " " // negative hours
            + PREFIX_DURATION_HOURS + "-1";
    public static final String INVALID_LESSON_DATE_FORMAT_DESC = " " // date must be given in "DD-MM-YYYY" format
            + PREFIX_DATE + "25 March 2022";
    public static final String INVALID_LESSON_START_TIME_FORMAT_DESC = " " // start time must be given in "HH:mm" format
            + PREFIX_START_TIME + "6pm";

    // hours and minutes cannot both be zero as the duration of a lesson should not be zero
    public static final String INVALID_DURATION_MINUTES_ZERO_DESC = " "
            + PREFIX_DURATION_MINUTES + "0";
    public static final String INVALID_DURATION_HOURS_ZERO_DESC = " "
            + PREFIX_DURATION_HOURS + "0";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
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
     * - the student book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StudentBook expectedStudentBook = new StudentBook(actualModel.getStudentBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStudentBook, actualModel.getStudentBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s student book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameOrTagsContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
