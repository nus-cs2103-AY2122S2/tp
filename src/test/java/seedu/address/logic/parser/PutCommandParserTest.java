package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.LineupBuilder.DEFAULT_LINEUP_NAME;
import static seedu.address.testutil.PersonBuilder.DEFAULT_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PutCommand;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;

public class PutCommandParserTest {
    private PutCommandParser parser = new PutCommandParser();

    @Test
    public void parse_putIntoLineup_success() {
        // put P/PLAYER L/LINEUP
        assertParseSuccess(parser, PutCommand.COMMAND_WORD + " " + PREFIX_PLAYER + DEFAULT_NAME + " "
                        + PREFIX_LINEUP + DEFAULT_LINEUP_NAME,
                new PutCommand(new Name(DEFAULT_NAME), new LineupName(DEFAULT_LINEUP_NAME)));

        // put L/LINEUP P/PLAYER
        assertParseSuccess(parser, PutCommand.COMMAND_WORD + " " + PREFIX_LINEUP + DEFAULT_LINEUP_NAME + " "
                        + PREFIX_PLAYER + DEFAULT_NAME,
                new PutCommand(new Name(DEFAULT_NAME), new LineupName(DEFAULT_LINEUP_NAME)));
    }

    @Test
    public void parse_putIntoLineup_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, PutCommand.MESSAGE_USAGE);

        // without specifying the lineup
        assertParseFailure(parser, PutCommand.COMMAND_WORD + " " + PREFIX_PLAYER + DEFAULT_NAME,
                expectedMessage);
    }
}
