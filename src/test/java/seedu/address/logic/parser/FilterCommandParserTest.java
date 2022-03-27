package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.filter.AppointmentContainsFilterWordPredicate;
import seedu.address.model.filter.DateContainsFilterDatePredicate;
import seedu.address.model.filter.OwnerNameContainsFilterWordPredicate;
import seedu.address.model.filter.TagContainsFilterWordPredicate;

public class FilterCommandParserTest {

    private static final String PARSE_EX_THROWN_MESSAGE = "Should not have thrown parse exception.";

    private FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOneField_throwsParseException() {
        assertParseFailure(parser, "byDate/today byOwner/Alice",
                Messages.FILTER_COMMAND_MORE_THAN_ONE_FIELD);
    }

    @Test
    public void parse_invalidField_throwsParseException() {
        assertParseFailure(parser, "byOtherStuff/abc",
                FilterCommand.INVALID_FILTER_FIELD);
    }

    @Test
    public void parse_emptyKeyword_throwsParseException() {
        assertParseFailure(parser, "byDate/",
                "You did not provide a keyword!");
    }

    @Test
    public void parse_validOwnerArg_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new OwnerNameContainsFilterWordPredicate("Alice"));
        assertParseSuccess(parser, " byOwner/Alice", expectedFilterCommand);
    }

    @Test
    public void parse_validTagArg_returnsFilterCommand() {
        FilterCommand expectedFilterCommand =
                new FilterCommand(new TagContainsFilterWordPredicate("ABC DEF"));
        assertParseSuccess(parser, " byTags/ABC DEF", expectedFilterCommand);
    }

    @Test
    public void parse_validDateArg_returnsFilterCommand() {
        try {
            FilterCommand firstExpectedFilterCommand =
                    new FilterCommand(new DateContainsFilterDatePredicate("today"));
            FilterCommand secondExpectedFilterCommand =
                    new FilterCommand(new DateContainsFilterDatePredicate("23-03-2022"));
            assertParseSuccess(parser, " byDate/today", firstExpectedFilterCommand);
            assertParseSuccess(parser, " byDate/23-03-2022", secondExpectedFilterCommand);
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void parse_validAppArg_returnsFilterCommand() {
        try {
            FilterCommand firstExpectedFilterCommand =
                    new FilterCommand(new AppointmentContainsFilterWordPredicate("today"));
            FilterCommand secondExpectedFilterCommand =
                    new FilterCommand(new AppointmentContainsFilterWordPredicate("23-03-2022"));
            assertParseSuccess(parser, " byApp/today", firstExpectedFilterCommand);
            assertParseSuccess(parser, " byApp/23-03-2022", secondExpectedFilterCommand);
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }
}
