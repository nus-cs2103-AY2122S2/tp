package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CANDIDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CANDIDATE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.logic.parser.schedule.AddScheduleCommandParser;


/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the AddScheduleCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the AddScheduleCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class AddScheduleCommandParserTest {

    private final AddScheduleCommandParser parser = new AddScheduleCommandParser();
    private LocalDateTime interviewDateTime = LocalDateTime.of(2023, 03, 20, 10, 00);

    @Test
    public void parse_validArgs_returnsAddScheduleCommand() {
        assertParseSuccess(parser, " " + PREFIX_CANDIDATE + "1  " + PREFIX_DATETIME + "20-03-2023 10:00",
                new AddScheduleCommand(INDEX_FIRST_CANDIDATE, interviewDateTime));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddScheduleCommand.MESSAGE_USAGE));
    }
}
