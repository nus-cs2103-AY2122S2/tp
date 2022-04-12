package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUM_OPENINGS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.ExportCsvOpenException;
import seedu.address.logic.commands.applicant.EditApplicantCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.position.EditPositionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.HireLah;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantNamePredicate;
import seedu.address.testutil.EditApplicantDescriptorBuilder;
import seedu.address.testutil.EditPositionDescriptorBuilder;

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
    public static final String VALID_AGE_AMY = "77";
    public static final String VALID_AGE_BOB = "88";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_GENDER_AMY = "F";
    public static final String VALID_GENDER_BOB = "M";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String VALID_POSITION_NAME_JR_SWE = "Junior Software Engineer";
    public static final String VALID_POSITION_NAME_SR_FE_SWE = "Senior Front-end Software Engineer";
    public static final String VALID_DESCRIPTION_NAME_JR_SWE = "Flexible role. Must be hardworking.";
    public static final String VALID_DESCRIPTION_NAME_SR_FE_SWE = "In-charged of managing an entire frontend feature.";
    public static final String VALID_POSITION_OPENINGS_JR_SWE = "6";
    public static final String VALID_POSITION_OPENINGS_SR_FE_SWE = "2";
    public static final String VALID_REQUIREMENT_EXPERIENCE = ">=5 years of experience";
    public static final String VALID_REQUIREMENT_SKILL = "Expert in JavaScript";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String AGE_DESC_AMY = " " + PREFIX_AGE + VALID_AGE_AMY;
    public static final String AGE_DESC_BOB = " " + PREFIX_AGE + VALID_AGE_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String GENDER_DESC_AMY = " " + PREFIX_GENDER + VALID_GENDER_AMY;
    public static final String GENDER_DESC_BOB = " " + PREFIX_GENDER + VALID_GENDER_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String POSITION_DESC_JR_SWE = " " + PREFIX_POSITION + VALID_POSITION_NAME_JR_SWE;
    public static final String POSITION_DESC_SR_FE_SWE = " " + PREFIX_POSITION + VALID_POSITION_NAME_SR_FE_SWE;
    public static final String DESCRIPTION_DESC_JR_SWE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_NAME_JR_SWE;
    public static final String DESCRIPTION_DESC_SR_FE_SWE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_NAME_SR_FE_SWE;
    public static final String OPENING_DESC_JR_SWE = " " + PREFIX_NUM_OPENINGS + VALID_POSITION_OPENINGS_JR_SWE;
    public static final String OPENING_DESC_SR_FE_SWE = " " + PREFIX_NUM_OPENINGS + VALID_POSITION_OPENINGS_SR_FE_SWE;
    public static final String REQUIREMENT_DESC_EXPERIENCE = " " + PREFIX_REQUIREMENT + VALID_REQUIREMENT_EXPERIENCE;
    public static final String REQUIREMENT_DESC_SKILL = " " + PREFIX_REQUIREMENT + VALID_REQUIREMENT_SKILL;

    public static final String INVALID_POSITION_DESC = " " + PREFIX_POSITION + "^#$#"; // only special characters
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION + "  "; // only whitespaces
    public static final String INVALID_OPENING_DESC = " " + PREFIX_NUM_OPENINGS + "-1"; // negative integer
    public static final String INVALID_REQUIREMENT_DESC = " " + PREFIX_REQUIREMENT + "*!@#"; // only special characters

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_AGE_DESC = " " + PREFIX_AGE + "twenty"; // characters not allowed in age
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_GENDER_DESC = " " + PREFIX_GENDER + "T"; // 'T is not a valid gender'
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "***"; // only special chars not allowed for tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final String FLAG_APPLICANT = " -a ";
    public static final String FLAG_POSITION = " -p ";
    public static final String FLAG_INTERVIEW = " -i ";

    public static final EditApplicantCommand.EditApplicantDescriptor DESC_AMY;
    public static final EditApplicantCommand.EditApplicantDescriptor DESC_BOB;
    public static final EditPositionCommand.EditPositionDescriptor DESC_JR_SWE;
    public static final EditPositionCommand.EditPositionDescriptor DESC_SR_FE_SWE;

    static {
        DESC_AMY = new EditApplicantDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditApplicantDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_JR_SWE = new EditPositionDescriptorBuilder().withPositionName(VALID_POSITION_NAME_JR_SWE)
                .withDescription(VALID_DESCRIPTION_NAME_JR_SWE).withNumOpenings(VALID_POSITION_OPENINGS_JR_SWE)
                .withRequirements(VALID_REQUIREMENT_SKILL).build();
        DESC_SR_FE_SWE = new EditPositionDescriptorBuilder().withPositionName(VALID_POSITION_NAME_SR_FE_SWE)
                .withDescription(VALID_DESCRIPTION_NAME_SR_FE_SWE).withNumOpenings(VALID_POSITION_OPENINGS_SR_FE_SWE)
                .withRequirements(VALID_REQUIREMENT_EXPERIENCE).build();
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
            System.out.println(result.getFeedbackToUser());
            System.out.println(expectedCommandResult.getFeedbackToUser());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        } catch (FileNotFoundException | ExportCsvOpenException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, command.getCommandDataType());
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered applicant list and selected applicant in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        HireLah expectedHireLah = new HireLah(actualModel.getHireLah());
        List<Applicant> expectedFilteredList = new ArrayList<>(actualModel.getFilteredApplicantList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedHireLah, actualModel.getHireLah());
        assertEquals(expectedFilteredList, actualModel.getFilteredApplicantList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the applicant at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showApplicantAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredApplicantList().size());

        Applicant applicant = model.getFilteredApplicantList().get(targetIndex.getZeroBased());
        final String[] splitName = applicant.getName().fullName.split("\\s+");
        model.updateFilteredApplicantList(new ApplicantNamePredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredApplicantList().size());
    }

}
