package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_ARG_INVALID;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_EMPTY;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_STUDENT_INVALID;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTITY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTITY;
import static seedu.address.testutil.TypicalTAssist.getTypicalTAssist;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EnrolCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.classgroup.ClassGroup;

//@@author wxliong
public class EnrolCommandParserTest {
    private static final String MESSAGE_USAGE = "\n" + EnrolCommand.MESSAGE_USAGE;
    private EnrolCommandParser parser = new EnrolCommandParser();
    private String expectedMessage;
    private String actualMessage;
    private Model model = new ModelManager(getTypicalTAssist(), new UserPrefs());
    private ClassGroup classGroup;

    @Test
    public void parse_validStudentIndexes_returnsEnrolCommand() throws ParseException, CommandException {
        classGroup = model.getUnfilteredClassGroupList().get(INDEX_FIRST_ENTITY.getZeroBased());
        Command command = parser.parse(" c/1 s/1,3", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_validStudentIdsLowerCase_returnsEnrolCommand() throws ParseException, CommandException {
        classGroup = model.getUnfilteredClassGroupList().get(INDEX_SECOND_ENTITY.getZeroBased());
        Command command = parser.parse(" c/2 s/e0234568, e0123457", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_validStudentIdsUpperCase_returnsEnrolCommand() throws ParseException, CommandException {
        classGroup = model.getUnfilteredClassGroupList().get(INDEX_SECOND_ENTITY.getZeroBased());
        Command command = parser.parse(" c/2 s/E0234568, E0123457", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_validAllStudents_returnsEnrolCommand() throws ParseException, CommandException {
        classGroup = model.getUnfilteredClassGroupList().get(INDEX_SECOND_ENTITY.getZeroBased());
        Command command = parser.parse(" c/2 s/all", model);
        CommandResult cr = command.execute(model);
        expectedMessage = cr.getFeedbackToUser();
        actualMessage = String.format(EnrolCommand.MESSAGE_ENROL_SUCCESS,
                classGroup.getClassGroupId(), classGroup.getClassGroupType());
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // missing index
        assertParseFailure(parser, " c/1 s/", model,
                MESSAGE_STUDENT_EMPTY + MESSAGE_USAGE);
        assertParseFailure(parser, " c/ s/1", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " c/ s/", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        // invalid format
        assertParseFailure(parser, "c/", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrolCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrolCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "s/", model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EnrolCommand.MESSAGE_USAGE));
        // invalid indexes
        assertParseFailure(parser, " c/1 s/10", model,
                MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " c/a s/a", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " c/1 s/a", model,
                MESSAGE_STUDENT_INVALID + MESSAGE_USAGE);
        // invalid student args
        assertParseFailure(parser, " c/1 s/1,E0234568", model,
                MESSAGE_INVALID_INDEX + MESSAGE_USAGE);
        assertParseFailure(parser, " c/1 s/E0234568,1", model,
                String.format(MESSAGE_STUDENT_ARG_INVALID, 1) + MESSAGE_USAGE);
        assertParseFailure(parser, " c/1 s/all,1", model,
                MESSAGE_STUDENT_INVALID + MESSAGE_USAGE);
    }

}
