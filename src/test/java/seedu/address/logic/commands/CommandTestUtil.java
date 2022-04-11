package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MailXDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_STUDENT_NUMBER_AMY = "A0123456B";
    public static final String VALID_STUDENT_NUMBER_BOB = "A1234567Z";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_GROUP_BOB = "T01";
    public static final String VALID_GROUP_AMY = "T02";
    public static final String VALID_MOD_AMY = "CS1101S";
    public static final String VALID_MOD_BOB = "GER1000";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String GROUP_DESC_BOB = " " + PREFIX_GROUP + VALID_GROUP_BOB;
    public static final String GROUP_DESC_AMY = " " + PREFIX_GROUP + VALID_GROUP_AMY;
    public static final String STUDENT_NUMBER_DESC_AMY = " " + PREFIX_STUDENT_NUMBER + VALID_STUDENT_NUMBER_AMY;
    public static final String STUDENT_NUMBER_DESC_BOB = " " + PREFIX_STUDENT_NUMBER + VALID_STUDENT_NUMBER_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String MOD_DESC_AMY = " " + PREFIX_MOD + VALID_MOD_AMY;
    public static final String MOD_DESC_BOB = " " + PREFIX_MOD + VALID_MOD_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_STUDENT_NUMBER_DESC = " " + PREFIX_STUDENT_NUMBER + "0123456A"; // beginning "A"
    // is compulsory in student numbers
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_MOD_DESC = " " + PREFIX_MOD; // empty string not allowed for mods
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    public static final MailXCommand.MailXDescriptor MAIL_X_DESC_AMY;
    public static final MailXCommand.MailXDescriptor MAIL_X_DESC_BOB;

    public static final String VALID_DESCRIPTION_HOMEWORK = "Do homework";
    public static final String VALID_DESCRIPTION_ASSIGNMENT = "Check assignment submissions";
    public static final String VALID_DEADLINE_HOMEWORK = "2022-03-21T21:24";
    public static final String VALID_DEADLINE_ASSIGNMENT = "1234-05-06T07:08";
    public static final String DEADLINE_HOMEWORK = " " + PREFIX_DEADLINE + VALID_DEADLINE_HOMEWORK;
    public static final String DEADLINE_ASSIGNMENT = " " + PREFIX_DEADLINE + VALID_DEADLINE_ASSIGNMENT;

    public static final String INVALID_DESCRIPTION = "&&"; // symbols not allowed
    public static final String INVALID_DEADLINE = "1234-05-06T00:08"; // Cannot have 0th hour.

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentNumber(VALID_STUDENT_NUMBER_AMY).withEmail(VALID_EMAIL_AMY).withMod(VALID_MOD_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withEmail(VALID_EMAIL_BOB).withMod(VALID_MOD_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

        MAIL_X_DESC_AMY = new MailXDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentNumber(VALID_STUDENT_NUMBER_AMY).withEmail(VALID_EMAIL_AMY).withMod(VALID_MOD_AMY)
                .build();

        MAIL_X_DESC_BOB = new MailXDescriptorBuilder().withName(VALID_NAME_BOB)
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withEmail(VALID_EMAIL_BOB).withMod(VALID_MOD_BOB)
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
     * - the contact list, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ContactList expectedContactList = new ContactList(actualModel.getContactList());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedContactList, actualModel.getContactList());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s contact list.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
