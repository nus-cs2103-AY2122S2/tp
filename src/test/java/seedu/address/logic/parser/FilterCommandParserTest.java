package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOCK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COVID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COVID_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.commands.FilterCommand.FilterDescriptor;
import seedu.address.model.person.Block;
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
        // values without prefixes
        assertParseFailure(parser, VALID_BLOCK_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        // wrong types of values
        assertParseFailure(parser, VALID_NAME_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FilterCommand.MESSAGE_USAGE));

        // invalid faculty
        assertParseFailure(parser, INVALID_FACULTY_DESC, Faculty.MESSAGE_CONSTRAINTS);

        // invalid covid status
        assertParseFailure(parser, INVALID_COVID_STATUS_DESC, CovidStatus.MESSAGE_CONSTRAINTS);

        // invalid block
        assertParseFailure(parser, INVALID_BLOCK_DESC, Block.MESSAGE_CONSTRAINTS);

        // invalid faculty with valid covid status and valid block
        assertParseFailure(parser, INVALID_FACULTY_DESC + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB
                + PREFIX_BLOCK + VALID_BLOCK_BOB, Faculty.MESSAGE_CONSTRAINTS);

        // invalid covid status with valid faculty and valid block
        assertParseFailure(parser, PREFIX_FACULTY + VALID_FACULTY_BOB + INVALID_COVID_STATUS_DESC
                + PREFIX_BLOCK + VALID_BLOCK_BOB, CovidStatus.MESSAGE_CONSTRAINTS);

        // invalid block with valid faculty and valid covid status
        assertParseFailure(parser, PREFIX_FACULTY + VALID_FACULTY_BOB + PREFIX_COVID_STATUS
                + VALID_COVID_STATUS_BOB + INVALID_BLOCK_DESC, Block.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = FilterCommand.COMMAND_WORD + " " + PREFIX_FACULTY + VALID_FACULTY_BOB + " "
                + PREFIX_COVID_STATUS + VALID_COVID_STATUS_BOB + " " + PREFIX_BLOCK + VALID_BLOCK_BOB;

        FilterDescriptor descriptor = new FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        Block block = new Block(VALID_BLOCK_BOB);
        descriptor.setFaculty(faculty);
        descriptor.setCovidStatus(status);
        descriptor.setBlock(block);
        FilterCommand expectedCommand = new FilterCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {

        // only faculty specified
        FilterDescriptor descriptorFaculty = new FilterDescriptor();
        Faculty faculty = new Faculty(VALID_FACULTY_BOB);
        descriptorFaculty.setFaculty(faculty);
        FilterCommand expectedCommand = new FilterCommand(descriptorFaculty);

        assertParseSuccess(parser, "filter f/fass", expectedCommand);

        // only covid status specified
        FilterDescriptor descriptorStatus = new FilterDescriptor();
        CovidStatus status = new CovidStatus(VALID_COVID_STATUS_BOB);
        descriptorStatus.setCovidStatus(status);
        expectedCommand = new FilterCommand(descriptorStatus);

        assertParseSuccess(parser, "filter cs/positive", expectedCommand);

        // only block specified
        FilterDescriptor descriptorBlock = new FilterDescriptor();
        Block block = new Block(VALID_BLOCK_BOB);
        descriptorBlock.setBlock(block);
        expectedCommand = new FilterCommand(descriptorBlock);

        assertParseSuccess(parser, "filter b/e", expectedCommand);

        // only faculty and covid status specified
        FilterDescriptor descriptorFacultyAndStatus = new FilterDescriptor();
        descriptorFacultyAndStatus.setFaculty(faculty);
        descriptorFacultyAndStatus.setCovidStatus(status);
        expectedCommand = new FilterCommand(descriptorFacultyAndStatus);

        assertParseSuccess(parser, "filter f/fass cs/positive", expectedCommand);

        // only faculty and block specified
        FilterDescriptor descriptorFacultyAndBlock = new FilterDescriptor();
        descriptorFacultyAndBlock.setFaculty(faculty);
        descriptorFacultyAndBlock.setBlock(block);
        expectedCommand = new FilterCommand(descriptorFacultyAndBlock);

        assertParseSuccess(parser, "filter f/fass b/e", expectedCommand);

        // only covid status and block specified
        FilterDescriptor descriptorStatusAndBlock = new FilterDescriptor();
        descriptorStatusAndBlock.setCovidStatus(status);
        descriptorStatusAndBlock.setBlock(block);
        expectedCommand = new FilterCommand(descriptorStatusAndBlock);

        assertParseSuccess(parser, "filter b/e cs/positive", expectedCommand);


    }

}
