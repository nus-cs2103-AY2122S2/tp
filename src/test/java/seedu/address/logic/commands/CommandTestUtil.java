package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_TELEGRAM_AMY = "@11111111";
    public static final String VALID_TELEGRAM_BOB = "@22222222";
    public static final String VALID_EMAIL_AMY = "amy@u.nus.edu";
    public static final String VALID_EMAIL_BOB = "bob@u.nus.edu";
    public static final String VALID_EMPTY_GITHUB = "";
    public static final String VALID_EMPTY_TELEGRAM = "";
    public static final String VALID_GITHUB_AMY = "amybee14";
    public static final String VALID_GITHUB_BOB = "boblim23";
    public static final String VALID_TUTORIAL_GROUP_CS2103T_W15_3 = "CS2103T W15-3";
    public static final String VALID_TUTORIAL_GROUP_CS2103_W13_2 = "CS2103 W13-2";
    public static final String VALID_TUTORIAL_GROUP_CS2101_G08 = "CS2101 G08";
    public static final String VALID_TUTORIAL_GROUP_CS2106_T02 = "CS2106 T02";

    public static final String EMPTY_GITHUB_DESC = " " + PREFIX_GITHUB + VALID_EMPTY_GITHUB;
    public static final String EMPTY_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + VALID_EMPTY_TELEGRAM;
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String TELEGRAM_DESC_AMY = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_AMY;
    public static final String TELEGRAM_DESC_BOB = " " + PREFIX_TELEGRAM + VALID_TELEGRAM_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String GITHUB_DESC_AMY = " " + PREFIX_GITHUB + VALID_GITHUB_AMY;
    public static final String GITHUB_DESC_BOB = " " + PREFIX_GITHUB + VALID_GITHUB_BOB;
    public static final String TUTORIAL_GROUP_DESC_CS2101_G08 = " " + PREFIX_TUTORIAL_GROUP
            + VALID_TUTORIAL_GROUP_CS2101_G08;
    public static final String TUTORIAL_GROUP_DESC_CS2103T_W15_3 = " " + PREFIX_TUTORIAL_GROUP
            + VALID_TUTORIAL_GROUP_CS2103T_W15_3;
    public static final String TUTORIAL_GROUP_DESC_CS2103_W13_2 = " " + PREFIX_TUTORIAL_GROUP
            + VALID_TUTORIAL_GROUP_CS2103_W13_2;
    public static final String TUTORIAL_GROUP_DESC_CS2106_T02 = " " + PREFIX_TUTORIAL_GROUP
            + VALID_TUTORIAL_GROUP_CS2106_T02;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_TELEGRAM_DESC = " " + PREFIX_TELEGRAM + "911!"; // non-alphanumeric character
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_GITHUB_DESC = " " + PREFIX_GITHUB + "johson!"; // non-alphanumeric
    public static final String INVALID_TUTORIAL_GROUP_DESC = " " + PREFIX_TUTORIAL_GROUP
        + "CS2106* T05"; // '*' not allowed as a tutorial group
    public static final String INVALID_EMPTY_TUTORIAL_GROUP = " " + PREFIX_TUTORIAL_GROUP;

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_AMY).withEmail(VALID_EMAIL_AMY).withGitHub(VALID_GITHUB_AMY)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB).withGitHub(VALID_GITHUB_BOB)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3, VALID_TUTORIAL_GROUP_CS2101_G08).build();
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
     * - the address book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
