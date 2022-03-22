package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COVID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
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
                + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB, Faculty.MESSAGE_CONSTRAINTS);

        // invalid covid status with valid faculty
        assertParseFailure(parser, PREFIX_FACULTY + VALID_FACULTY_BOB
                + PREFIX_COVID_STATUS + INVALID_COVID_STATUS_DESC, CovidStatus.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = FilterCommand.COMMAND_WORD + " " + PREFIX_FACULTY + VALID_FACULTY_BOB
                + " " + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB;

        FilterDescriptor descriptor = new FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        descriptor.setFaculty(faculty);
        descriptor.setCovidStatus(status);
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {

        // only faculty specified
        FilterDescriptor descriptor = new FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        descriptor.setFaculty(faculty);
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, "filter f/fass", expectedCommand);

        // only covid status specified
        FilterDescriptor descriptor2 = new FilterDescriptor();
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        descriptor2.setCovidStatus(status);
        expectedCommand = new FilterCommand(descriptor2);

        assertParseSuccess(parser, "filter cs/positive", expectedCommand);

    }

}
