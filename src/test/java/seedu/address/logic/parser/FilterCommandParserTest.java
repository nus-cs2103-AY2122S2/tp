package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Faculty;

public class FilterCommandParserTest {

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid faculty
        assertParseFailure(parser, PREFIX_FACULTY + INVALID_FACULTY_DESC, Faculty.MESSAGE_CONSTRAINTS);

        // invalid covid status
        assertParseFailure(parser, PREFIX_COVID_STATUS + INVALID_COVID_STATUS_DESC, CovidStatus.MESSAGE_CONSTRAINTS);

        // invalid faculty with valid covid status
        assertParseFailure(parser, PREFIX_FACULTY + INVALID_FACULTY_DESC
                + PREFIX_COVID_STATUS + VALID_COVID_STATUS_AMY, Faculty.MESSAGE_CONSTRAINTS);

        // invalid covid status with valid faculty
        assertParseFailure(parser, PREFIX_FACULTY + VALID_FACULTY_AMY
                + PREFIX_COVID_STATUS + INVALID_COVID_STATUS_DESC, CovidStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = PREFIX_FACULTY + VALID_FACULTY_BOB + " " + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB;

        FilterCommand.FilterDescriptor descriptor = new FilterCommand.FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        descriptor.setFaculty(faculty);
        descriptor.setCovidStatus(status);
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = PREFIX_FACULTY + VALID_FACULTY_BOB;

        FilterCommand.FilterDescriptor descriptor = new FilterCommand.FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        descriptor.setFaculty(faculty);
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }

}
