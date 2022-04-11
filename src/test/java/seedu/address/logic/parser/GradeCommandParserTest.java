package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_ARG_INVALID;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_EMPTY;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_INVALID;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_ENTITY;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2040;
import static seedu.address.testutil.TypicalModules.CS2103T_WITH_STUDENT;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.SimpleName;
import seedu.address.testutil.TypicalAssessments;
import seedu.address.testutil.TypicalModules;
import seedu.address.testutil.TypicalStudents;

public class GradeCommandParserTest {
    private static final String MESSAGE_USAGE = "\n" + GradeCommand.MESSAGE_USAGE;
    private GradeCommandParser parser = new GradeCommandParser();
    private String expectedMessage;
    private String actualMessage;
    private Model model = new ModelManager(getTypicalTAssist(), new UserPrefs());
    private Assessment assessment;

    // a/1 s/all g/1
    // sn/part m/1 s/all

    @Test
    public void parse_validAllStudents_returnsAllEnrolledMessage() throws ParseException, CommandException {
        //CS2103T_PARTICIPATION_NO_ATTEMPT
        assessment = model.getUnfilteredAssessmentList().get(INDEX_THIRD_ENTITY.getZeroBased());
        Command command = parser.parse(" a/3 s/all g/5", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = TypicalModules.getModule(CS2103T_WITH_STUDENT).getStudents().stream().map(student ->
                String.format(GradeCommand.GRADED_STUDENTS, student.getName(), student.getStudentId(), 5))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_validStudentIndexesAndAssessmentWithNoGrade_returnsAllEnrolledMessage()
            throws ParseException, CommandException {
        //CS2103T_PARTICIPATION_NO_ATTEMPT
        assessment = TypicalAssessments.getAssessment(2);
        Command command = parser.parse(" sn/part m/6 s/1,2,3,4,5,6", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = TypicalModules.getModule(CS2103T_WITH_STUDENT).getStudents().stream().map(student ->
                        String.format(GradeCommand.GRADED_STUDENTS, student.getName(), student.getStudentId(), 1))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_validStudentIdsUpperCase_returnsAllEnrolledMessage() throws ParseException, CommandException {
        //CS2040_LAB2_NO_ATTEMPT
        assessment = model.getUnfilteredAssessmentList().get(7);
        Command command = parser.parse(" a/8 s/E0123457", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = TypicalModules.getModule(CS2040).getStudents().stream().map(student ->
                        String.format(GradeCommand.GRADED_STUDENTS, student.getName(), student.getStudentId(), 1))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_validAllStudents_returnsAllDisenrolledMessage() throws ParseException, CommandException {
        //CS2103T_NO_STUDENT_PARTICIPATION_NO_ATTEMPT
        assessment = model.getUnfilteredAssessmentList().get(INDEX_SECOND_ENTITY.getZeroBased());
        Command command = parser.parse(" a/2 s/all", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = TypicalStudents.getTypicalStudents().stream().map(student ->
                        String.format(GradeCommand.UNGRADED_STUDENTS, student.getName(), student.getStudentId()))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_validAllStudents_returnsMixedMessage() throws ParseException, CommandException {
        //CS2030_LAB1_WITH_ATTEMPT
        assessment = model.getUnfilteredAssessmentList().get(INDEX_FOURTH_ENTITY.getZeroBased());
        Command command = parser.parse(" a/4 s/all g/10", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = TypicalModules.getModule(CS2030).getStudents().stream().map(student ->
                        String.format(GradeCommand.GRADED_STUDENTS, student.getName(), student.getStudentId(), 10))
                .reduce("", (x, y) -> x + y) + "\n\n";
        actualMessage += TypicalStudents.getTypicalStudents().stream().filter(student ->
                !TypicalModules.getModule(CS2030).hasStudent(student)).map(student ->
                        String.format(GradeCommand.UNGRADED_STUDENTS, student.getName(), student.getStudentId()))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_validAllStudents_returnsErrorMessage() throws ParseException, CommandException {
        //CS2030_LAB1_WITH_ATTEMPT
        assessment = model.getUnfilteredAssessmentList().get(INDEX_FOURTH_ENTITY.getZeroBased());
        Command command = parser.parse(" a/4 s/E0458690 g/10", model);
        command.execute(model);
        command = parser.parse(" a/4 s/E0123457,E0345689 g/2147483647", model);
        command.execute(model);
        command = parser.parse(" a/4 s/all", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = String.format(GradeCommand.GRADED_STUDENTS, TypicalStudents.FIONA.getName(),
                TypicalStudents.FIONA.getStudentId(), 11) + "\n";
        actualMessage += String.format(GradeCommand.INVALID_INCREMENT, TypicalStudents.ALICE.getName(),
                TypicalStudents.ALICE.getStudentId());
        actualMessage += String.format(GradeCommand.INVALID_INCREMENT, TypicalStudents.ELLE.getName(),
                TypicalStudents.ELLE.getStudentId()) + "\n";
        actualMessage += TypicalStudents.getTypicalStudents().stream().filter(student ->
                        !TypicalModules.getModule(CS2030).hasStudent(student)).map(student ->
                        String.format(GradeCommand.UNGRADED_STUDENTS, student.getName(), student.getStudentId()))
                .reduce("", (x, y) -> x + y);
        assertEquals(expectedMessage, actualMessage.trim());
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing index
        assertParseFailure(parser, " a/1 s/", model,
                MESSAGE_STUDENT_EMPTY + MESSAGE_USAGE);
        assertParseFailure(parser, " a/ s/1", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " a/ s/", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        // invalid format
        assertParseFailure(parser, "a/", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "sn/1 s/1,2,3", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "m/1 s/1,2,3", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "s/", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeCommand.MESSAGE_USAGE));
        // invalid indexes
        assertParseFailure(parser, " a/1 s/10", model,
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " a/20 s/1", model,
                MESSAGE_INVALID_ASSESSMENT_DISPLAYED_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " sn/part m/10 s/1", model,
                MESSAGE_INVALID_TA_MODULE_DISPLAYED_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " sn/part Test m/1 s/1", model,
                SimpleName.MESSAGE_CONSTRAINTS + MESSAGE_USAGE);
        assertParseFailure(parser, " sn/part m/5 s/1", model,
                GradeCommandParser.MESSAGE_ASSESSMENT_NOT_FOUND + MESSAGE_USAGE);
        assertParseFailure(parser, " a/a s/a", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " a/1 s/a", model,
                MESSAGE_STUDENT_INVALID + MESSAGE_USAGE);
        // invalid student args
        assertParseFailure(parser, " a/1 s/1,E0234568", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " a/1 s/E0234568,1", model,
                String.format(MESSAGE_STUDENT_ARG_INVALID, 1) + MESSAGE_USAGE);
        assertParseFailure(parser, " a/1 s/all,1", model,
                MESSAGE_STUDENT_INVALID + MESSAGE_USAGE);
    }

}
