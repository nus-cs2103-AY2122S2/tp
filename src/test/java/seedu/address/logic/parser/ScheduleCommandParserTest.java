package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CANDIDATE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.schedule.AddScheduleCommand;
import seedu.address.logic.commands.schedule.DeleteScheduleCommand;
import seedu.address.logic.commands.schedule.EditScheduleCommand;
import seedu.address.logic.commands.schedule.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ScheduleCommandParserTest {
    private final ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_validArgs_returnsAddScheduleCommand() throws Exception {
        AddScheduleCommand command = (AddScheduleCommand) parser.parse("add candidate/"
                + INDEX_FIRST_CANDIDATE.getOneBased() + " at/01-01-2023 10:00");
        assertEquals(new AddScheduleCommand(INDEX_FIRST_CANDIDATE,
                LocalDateTime.of(2023, 01, 01, 10, 00)), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() throws ParseException {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleCommand.MESSAGE_USAGE), () -> parser.parse(ScheduleCommand.COMMAND_WORD + ""));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleCommand.MESSAGE_USAGE), () -> parser.parse(ScheduleCommand.COMMAND_WORD + " modify"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ScheduleCommand.MESSAGE_USAGE), () -> parser.parse(ScheduleCommand.COMMAND_WORD + " remove"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddScheduleCommand.MESSAGE_USAGE), () -> parser.parse("add"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddScheduleCommand.MESSAGE_USAGE), () -> parser.parse("add 2"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditScheduleCommand.MESSAGE_USAGE), () -> parser.parse("edit"));
        assertThrows(ParseException.class, String.format(EditScheduleCommand.MESSAGE_NOT_EDITED), () ->
                parser.parse("edit 2"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScheduleCommand.MESSAGE_USAGE), () -> parser.parse("delete"));
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteScheduleCommand.MESSAGE_USAGE), () -> parser.parse("delete candidate/2"));
    }
}
