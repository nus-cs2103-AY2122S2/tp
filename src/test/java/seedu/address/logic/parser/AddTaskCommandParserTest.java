package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        assertParseSuccess(parser, "addt d/description t/01/01/2222",
                new AddTaskCommand("description", "01/01/2222"));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        assertParseSuccess(parser, "addt d/description",
                new AddTaskCommand("description"));
    }

    @Test
    public void parse_multipleDescriptions_failure() {
        String expectedMessage = "Duplicated prefix detected in input!\n" + AddTaskCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "addt d/abc d/abc", expectedMessage);
    }

    @Test
    public void parse_multipleDeadline_failure() {
        String expectedMessage = "Duplicated prefix detected in input!\n" + AddTaskCommand.MESSAGE_USAGE;
        assertParseFailure(parser, "addt d/abc t/01/01/2222 t/01/01/2222", expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = "Description is compulsory!\n" + AddTaskCommand.MESSAGE_USAGE;

        // has description prefix
        assertParseFailure(parser, "addt d/", expectedMessage);

        // has description prefix and deadline prefix
        assertParseFailure(parser, "addt d/ t/", expectedMessage);

        // no description prefix
        assertParseFailure(parser, "addt", expectedMessage);
    }

    @Test
    public void parse_deadlineFormat_success() {
        assertParseSuccess(parser, "addt d/description t/01/01/2222",
                new AddTaskCommand("description", "01/01/2222"));
    }

    @Test
    public void parse_deadlineFormat_failure() {
        String expectedMessage = "Deadline is not in dd/mm/yyyy!\n" + AddTaskCommand.MESSAGE_USAGE;

        // no "/" between date, month, year
        assertParseFailure(parser, "addt d/description t/01012222", expectedMessage);

        // uses "-" between date, month, year
        assertParseFailure(parser, "addt d/description t/01-01-2222", expectedMessage);
    }

    @Test
    public void parse_deadlineFormatUseAlphabets_failure() {
        String expectedMessage = "Deadline is not in dd/mm/yyyy!\n" + AddTaskCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "addt d/description t/monday", expectedMessage);
        assertParseFailure(parser, "addt d/description t/o1/o1/2o22", expectedMessage);
    }

    @Test
    public void parse_deadlineFormatUseSymbols_failure() {
        String expectedMessage = "Deadline is not in dd/mm/yyyy!\n" + AddTaskCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "addt d/description t/!@#$%^&*(){}[]|~`", expectedMessage);
        assertParseFailure(parser, "addt d/description t/*10!2()22", expectedMessage);
    }

    @Test
    public void parse_deadlineFormatIsBeforeTodayDate_failure() {
        String expectedMessage = "Deadline is before today's date!\n" + AddTaskCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "addt d/description t/01/01/2022", expectedMessage);
    }

    @Test
    public void parse_descriptionContainsDeadlinePrefix_failure() {
        String expectedMessage = "You cannot have 't/' or 'd/' prefix in the description!\n"
                + AddTaskCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "addt d/descriptiont/01/02/2022 t/01/01/2222", expectedMessage);
    }

    @Test
    public void parse_descriptionValidity_failure() {
        String expectedMessage = "Invalid date input!\n" + AddTaskCommand.MESSAGE_USAGE;

        assertParseFailure(parser, "addt d/description t/31/02/2022", expectedMessage);
        assertParseFailure(parser, "addt d/description t/30/02/2022", expectedMessage);
        assertParseFailure(parser, "addt d/description t/29/02/2022", expectedMessage);
    }
}
