package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.LineupBuilder.DEFAULT_LINEUP_NAME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;

public class DeleteCommandParserTest {
    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_deletePerson_success() {
        assertParseSuccess(parser, " " + PREFIX_PLAYER + VALID_NAME_BOB,
                new DeleteCommand(new Name(VALID_NAME_BOB)));
    }

    @Test
    public void parse_deleteLineup_success() {
        assertParseSuccess(parser, " " + PREFIX_LINEUP + DEFAULT_LINEUP_NAME,
                new DeleteCommand(new LineupName(DEFAULT_LINEUP_NAME)));
    }

    @Test
    public void parse_deleteSchedule_success() {
        assertParseSuccess(parser, " " + PREFIX_SCHEDULE + "1",
                new DeleteCommand(INDEX_FIRST_SCHEDULE));
    }

    @Test
    public void parse_deletePersonFromLineup_success() {
        assertParseSuccess(parser, " " + PREFIX_PLAYER + VALID_NAME_BOB
                        + " " + PREFIX_LINEUP + DEFAULT_LINEUP_NAME,
                new DeleteCommand(new Name(VALID_NAME_BOB), new LineupName(DEFAULT_LINEUP_NAME)));
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessageLineup = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_LINEUP);
        String expectedMessageSchedule = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCommand.MESSAGE_USAGE_SCHEDULE);

        // invalid lineup command
        assertParseFailure(parser, " " + PREFIX_LINEUP, expectedMessageLineup);

        // invalid schedule command
        assertParseFailure(parser, " " + PREFIX_SCHEDULE, expectedMessageSchedule);
    }
}
