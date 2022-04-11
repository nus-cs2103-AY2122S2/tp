package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_SLOT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.testutil.EditApplicationDescriptorBuilder;
/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_SHOPEE = "Shopee Singapore";
    public static final String VALID_NAME_GARENA = "Garena SEA";
    public static final String VALID_PHONE_SHOPEE = "11111111";
    public static final String VALID_PHONE_GARENA = "22222222";
    public static final String VALID_EMAIL_SHOPEE = "shopee@example.com";
    public static final String VALID_EMAIL_GARENA = "garena@example.com";
    public static final String VALID_INTERVIEW_SLOT_SHOPEE = "02-12-2030 13:00";
    public static final String VALID_INTERVIEW_SLOT_GARENA = "02-12-2035 13:00";
    public static final String VALID_ADDRESS_SHOPEE = "Block 312, Shopee Street 1";
    public static final String VALID_ADDRESS_GARENA = "Block 123, Garena Street 3";
    public static final String VALID_TAG_LOCAL = "local";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_JOBTITLE_SHOPEE = "Intern";
    public static final String VALID_JOBTITLE_GARENA = "DataScientist";

    public static final String NAME_DESC_SHOPEE = " " + PREFIX_NAME + VALID_NAME_SHOPEE;
    public static final String NAME_DESC_GARENA = " " + PREFIX_NAME + VALID_NAME_GARENA;
    public static final String PHONE_DESC_SHOPEE = " " + PREFIX_PHONE + VALID_PHONE_SHOPEE;
    public static final String PHONE_DESC_GARENA = " " + PREFIX_PHONE + VALID_PHONE_GARENA;
    public static final String EMAIL_DESC_SHOPEE = " " + PREFIX_EMAIL + VALID_EMAIL_SHOPEE;
    public static final String EMAIL_DESC_GARENA = " " + PREFIX_EMAIL + VALID_EMAIL_GARENA;
    public static final String ADDRESS_DESC_SHOPEE = " " + PREFIX_ADDRESS + VALID_ADDRESS_SHOPEE;
    public static final String ADDRESS_DESC_GARENA = " " + PREFIX_ADDRESS + VALID_ADDRESS_GARENA;
    public static final String INTERVIEWSLOT_DESC_SHOPEE = " " + PREFIX_INTERVIEW_SLOT + VALID_INTERVIEW_SLOT_SHOPEE;
    public static final String INTERVIEWSLOT_DESC_GARENA = " " + PREFIX_INTERVIEW_SLOT + VALID_INTERVIEW_SLOT_GARENA;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_LOCAL = " " + PREFIX_TAG + VALID_TAG_LOCAL;
    public static final String JOBTITLE_DESC_SHOPEE = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_SHOPEE;
    public static final String JOBTITLE_DESC_GARENA = " " + PREFIX_JOBTITLE + VALID_JOBTITLE_GARENA;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Grab&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "grab!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_INTERVIESLOT_DESC = " " + PREFIX_INTERVIEW_SLOT + "02-31-2022 16:00";
    // MM-dd-yyyy HH:mm slot format not allowed
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "overseas*"; // '*' not allowed in tags
    public static final String INVALID_JOBTITLE_DESC = " " + PREFIX_JOBTITLE
            + "J@v@ developer"; // '@' not allowed in job title

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditApplicationDescriptor DESC_SHOPEE;
    public static final EditCommand.EditApplicationDescriptor DESC_GARENA;

    static {
        DESC_SHOPEE = new EditApplicationDescriptorBuilder().withName(VALID_NAME_SHOPEE)
                .withPhone(VALID_PHONE_SHOPEE).withEmail(VALID_EMAIL_SHOPEE).withAddress(VALID_ADDRESS_SHOPEE)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_GARENA = new EditApplicationDescriptorBuilder().withName(VALID_NAME_GARENA)
                .withPhone(VALID_PHONE_GARENA).withEmail(VALID_EMAIL_GARENA).withAddress(VALID_ADDRESS_GARENA)
                .withTags(VALID_TAG_LOCAL, VALID_TAG_FRIEND).build();
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
            assertEquals(expectedCommandResult.toString(), result.toString());
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
     * - the intern apply memory, filtered application list and
     * selected application in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InternApplyMemory expectedInternApplyMemory = new InternApplyMemory(actualModel.getInternApplyMemory());
        List<Application> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicationList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInternApplyMemory, actualModel.getInternApplyMemory());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicationList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the application at the given {@code targetIndex} in the
     * {@code model}'s intern apply memory.
     */
    public static void showApplicationAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicationList().size());

        Application application = model.getFilteredApplicationList().get(targetIndex.getZeroBased());
        final String[] splitName = application.getName().fullName.split("\\s+");
        model.updateFilteredApplicationList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicationList().size());
    }

}
