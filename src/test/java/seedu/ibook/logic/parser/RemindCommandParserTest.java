package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.item.RemindCommand;
import seedu.ibook.model.item.ExpiryDate;

public class RemindCommandParserTest {

    private static RemindCommandParser parser = new RemindCommandParser();

    @Test
    public void parse_positiveInteger_success() {
        String dateOffset = "3";
        String dateToday = LocalDate.now().plusDays(Integer.parseInt(dateOffset)).toString();
        ExpiryDate expiryDateToday = new ExpiryDate(dateToday);
        RemindCommand expectedRemindCommand = new RemindCommand(expiryDateToday);
        assertParseSuccess(parser, dateOffset, expectedRemindCommand);
    }

    @Test
    public void parse_zero_success() {
        String dateToday = LocalDate.now().toString();
        ExpiryDate expiryDateToday = new ExpiryDate(dateToday);
        RemindCommand expectedRemindCommand = new RemindCommand(expiryDateToday);
        assertParseSuccess(parser, "0", expectedRemindCommand);
    }

    @Test
    public void parse_negative_throwParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_bigNumber_throwParseException() {
        assertParseFailure(parser, "100000000000",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE));
    }
}
