package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TAssist;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.classgroup.ClassGroupId;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.Student;
import seedu.address.model.tamodule.TaModule;

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
    public static final String VALID_ID_AMY = "e0123456";
    public static final String VALID_ID_BOB = "e0123457";
    public static final String VALID_TELEGRAM_AMY = "amy123";
    public static final String VALID_TELEGRAM_BOB = "b0bSOC";

    public static final String VALID_CG_ID_CS2103T_TUT = "T13";
    public static final String VALID_CG_TYPE_CS2103T_TUT = "TUTORIAL";
    public static final String VALID_MOD_CODE_CS2103T_TUT = "CS2103T";
    public static final String VALID_CG_ID_CS2106_LAB = "B01";
    public static final String VALID_CG_TYPE_CS2106_LAB = "LAB";
    public static final String VALID_MOD_CODE_CS2106_LAB = "CS2106";

    public static final String VALID_WEEK_ID_1 = "1";
    public static final String VALID_WEEK_ID_13 = "13";
    public static final String VALID_ATTENDANCE_TRUE = "true";
    public static final String VALID_ATTENDANCE_FALSE = "false";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";


    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command,
                                            Model actualModel,
                                            CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result); // something is wrong with this line
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
                                            Model expectedModel, EntityType entity) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, entity);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
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
     * - the TAssist, unfiltered entity list and selected entity in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailureUnfiltered(Command command,
                                                      Model actualModel,
                                                      EntityType entity,
                                                      String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TAssist expectedTAssist = new TAssist(actualModel.getTAssist());
        List<Entity> expectedList;
        switch(entity) {
        case STUDENT:
            expectedList = new ArrayList<>(actualModel.getUnfilteredStudentList());
            assertEquals(expectedList, actualModel.getUnfilteredStudentList());
            break;
        case TA_MODULE:
            expectedList = new ArrayList<>(actualModel.getUnfilteredModuleList());
            assertEquals(expectedList, actualModel.getUnfilteredModuleList());
            break;
        case CLASS_GROUP:
            expectedList = new ArrayList<>(actualModel.getUnfilteredClassGroupList());
            assertEquals(expectedList, actualModel.getUnfilteredClassGroupList());
            break;
        case ASSESSMENT:
            expectedList = new ArrayList<>(actualModel.getUnfilteredAssessmentList());
            assertEquals(expectedList, actualModel.getUnfilteredAssessmentList());
            break;
        default:
            throw new UnknownEntityException();
        }

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTAssist, actualModel.getTAssist());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the TAssist, filtered entity list and selected entity in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailureFiltered(Command command,
                                                    Model actualModel,
                                                    EntityType entity,
                                                    String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TAssist expectedTAssist = new TAssist(actualModel.getTAssist());
        List<Entity> expectedList;
        switch(entity) {
        case STUDENT:
            expectedList = new ArrayList<>(actualModel.getFilteredStudentList());
            assertEquals(expectedList, actualModel.getFilteredStudentList());
            break;
        case TA_MODULE:
            expectedList = new ArrayList<>(actualModel.getFilteredModuleList());
            assertEquals(expectedList, actualModel.getFilteredModuleList());
            break;
        case CLASS_GROUP:
            expectedList = new ArrayList<>(actualModel.getFilteredClassGroupList());
            assertEquals(expectedList, actualModel.getFilteredClassGroupList());
            break;
        case ASSESSMENT:
            expectedList = new ArrayList<>(actualModel.getFilteredAssessmentList());
            assertEquals(expectedList, actualModel.getFilteredAssessmentList());
            break;
        default:
            throw new UnknownEntityException();
        }

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedTAssist, actualModel.getTAssist());
    }

    /**
     * Updates {@code model}'s filtered list to show only the entity at the given {@code targetIndex} in the
     * {@code model}'s TAssist.
     */
    public static void showEntityAtIndex(Model model, Index targetIndex, EntityType entity) {
        switch (entity) {
        case STUDENT:
            assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());
            Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
            final String[] splitName = student.getName().fullName.split("\\s+");
            model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));
            assertEquals(1, model.getFilteredStudentList().size());
            break;
        case CLASS_GROUP:
            assertTrue(targetIndex.getZeroBased() < model.getFilteredClassGroupList().size());
            ClassGroup classGroup = model.getFilteredClassGroupList().get(targetIndex.getZeroBased());
            ClassGroupId classGroupId = classGroup.getClassGroupId();
            model.updateFilteredClassGroupList(new Predicate<ClassGroup>() {
                @Override
                public boolean test(ClassGroup classGroup) {
                    return (classGroup.getClassGroupId().equals(classGroupId));
                }
            });
            assertEquals(1, model.getFilteredClassGroupList().size());
            break;
        case ASSESSMENT:
            assertTrue(targetIndex.getZeroBased() < model.getFilteredAssessmentList().size());
            Assessment assessment = model.getFilteredAssessmentList().get(targetIndex.getZeroBased());
            AssessmentName assessmentName = assessment.getAssessmentName();
            TaModule module = assessment.getModule();
            model.updateFilteredAssessmentList(new Predicate<Assessment>() {
                @Override
                public boolean test(Assessment assessment) {
                    return (assessment.getAssessmentName().equals(assessmentName)
                            && assessment.getModule().equals(module));
                }
            });
            assertEquals(1, model.getFilteredAssessmentList().size());
            break;
        default:
            throw new UnknownEntityException();
        }
    }
}
