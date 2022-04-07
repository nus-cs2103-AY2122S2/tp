package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EventCommand;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Information;

public class EventCommandParserTest {
    private EventCommandParser parser = new EventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EventCommand expectedEventCommand = new EventCommand(new Index[]{Index.fromOneBased(1)}, new EventName("lunch"),
                new Information("at HDL"), new DateTime(2022, 10, 10, 11, 11));

        assertParseSuccess(parser, " 1 name/lunch info/at HDL d/2022-10-10 t/11:11",
                expectedEventCommand);
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EventCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " 1 name/lunch d/2022-10-10 t/11:11",
                expectedMessage);
        assertParseFailure(parser, " 1 info/at HDL d/2022-10-10 t/11:11",
                expectedMessage);
        assertParseFailure(parser, " 1 name/lunch info/at HDL t/11:11",
                expectedMessage);
        assertParseFailure(parser, " 1 name/lunch info/at HDL d/2022-10-10",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " 1 name/ info/at HDL d/2022-10-10 t/11:11",
                EventName.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " 1 name/lunch info/ d/2022-10-10 t/11:11",
                Information.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " 1 name/lunch info/at HDL d/ t/11:11",
                DateTime.DATE_MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " 1 name/lunch info/at HDL d/2022-10-10 t/",
                DateTime.TIME_MESSAGE_CONSTRAINTS);
    }
}
