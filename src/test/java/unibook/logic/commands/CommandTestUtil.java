package unibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import unibook.commons.core.index.Index;
import unibook.logic.commands.exceptions.CommandException;
import unibook.logic.parser.CliSyntax;
import unibook.model.Model;
import unibook.model.UniBook;
import unibook.model.person.NameContainsKeywordsPredicate;
import unibook.model.person.Person;
import unibook.testutil.Assert;

/**
 * Contains helper methods for testing commands.
 * Also contains static text fields for testing parsing of commands.
 */
public class CommandTestUtil {


    //Data for objects to be created from scratch for testing
    public static final String VALID_MODULE_NAME_CS2100 = "Computer Organisation";
    public static final String VALID_MODULE_CODE_CS2100 = "CS2100";
    public static final String VALID_NAME_STUDENT_AMY = "Amy Beasley";
    public static final String VALID_NAME_PROFESSOR_CHARLES = "Charles Tootkins";
    public static final String VALID_PHONE_STUDENT_AMY = "89092807";
    public static final String VALID_PHONE_PROFESSOR_CHARLES = "91230283";
    public static final String VALID_EMAIL_STUDENT_AMY = "amybee@example.com";
    public static final String VALID_EMAIL_PROFESSOR_CHARLES = "charles@example.com";
    public static final String VALID_OFFICE_2 = "COM2 03-12";
    public static final String VALID_TAG = "friend";


    //Option fields
    public static final String VALID_OPTION_MODULE = "module";
    public static final String VALID_OPTION_STUDENT = "student";
    public static final String VALID_OPTION_PROFESSOR = "professor";

    public static final String OPTION_DESC_MODULE = " " + CliSyntax.PREFIX_OPTION + VALID_OPTION_MODULE;
    public static final String OPTION_DESC_STUDENT = " " + CliSyntax.PREFIX_OPTION + VALID_OPTION_STUDENT;
    public static final String OPTION_DESC_PROFESSOR = " " + CliSyntax.PREFIX_OPTION + VALID_OPTION_PROFESSOR;
    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_STUDENT_AMY;
    public static final String MODULE_NAME_DESC_CS2100 = " " + CliSyntax.PREFIX_NAME + VALID_MODULE_NAME_CS2100;
    public static final String PHONE_DESC_AMY = " " + CliSyntax.PREFIX_PHONE + VALID_PHONE_STUDENT_AMY;
    public static final String EMAIL_DESC_AMY = " " + CliSyntax.PREFIX_EMAIL + VALID_EMAIL_STUDENT_AMY;
    public static final String MODULE_CODE_DESC_CS2100 = " " + CliSyntax.PREFIX_MODULE + VALID_MODULE_CODE_CS2100;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG;


    public static final String INVALID_OPTION_DESC = " "
        + CliSyntax.PREFIX_OPTION + "STUDENT"; // only 'student'/'module'/'professor' allowed in option
    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + CliSyntax.PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + CliSyntax.PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " "
        + CliSyntax.PREFIX_OFFICE + " "; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final Boolean PERSON_LIST_SHOWING = true;
    public static final Boolean PERSON_LIST_NOT_SHOWING = false;
    public static final Boolean MODULE_LIST_SHOWING = true;
    public static final Boolean MODULE_LIST_NOT_SHOWING = false;


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, PERSON_LIST_SHOWING, MODULE_LIST_SHOWING);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**d
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
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     * Meant to test result on calling Methods on a certain correct page
     */
    public static void assertCommandSuccessPage(Command command, Model actualModel, CommandResult expectedCommandResult,
                                                Model expectedModel, boolean personListShowing,
                                                boolean moduleListShowing) {
        try {
            CommandResult result = command.execute(actualModel, personListShowing, moduleListShowing);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * Able to modify page showing
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccessPage(Command command, Model actualModel, String expectedMessage,
                                                Model expectedModel, boolean personListShowing,
                                                boolean moduleListShowing) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccessPage(command, actualModel, expectedCommandResult,
            expectedModel, personListShowing, moduleListShowing);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the unibook, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UniBook expectedUniBook = new UniBook(actualModel.getUniBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class,
            expectedMessage, () -> command.execute(actualModel, PERSON_LIST_SHOWING, MODULE_LIST_SHOWING));
        assertEquals(expectedUniBook, actualModel.getUniBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the unibook, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage,
                                            Boolean personListShowing, Boolean moduleListShowing) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        UniBook expectedUniBook = new UniBook(actualModel.getUniBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        Assert.assertThrows(CommandException.class,
            expectedMessage, () -> command.execute(actualModel, personListShowing, moduleListShowing));
        assertEquals(expectedUniBook, actualModel.getUniBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s unibook.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
