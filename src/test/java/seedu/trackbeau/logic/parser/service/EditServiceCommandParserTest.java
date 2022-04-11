package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DURATION_FIRST;
import static seedu.trackbeau.logic.commands.CommandTestUtil.DURATION_SECOND;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_DURATION;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_PRICE;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PRICE_FIRST;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PRICE_SECOND;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICENAME_FIRST;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICENAME_SECOND;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.service.EditServiceCommand;
import seedu.trackbeau.model.service.Duration;
import seedu.trackbeau.model.service.Price;

public class EditServiceCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditServiceCommand.MESSAGE_USAGE);

    private EditServiceCommandParser parser = new EditServiceCommandParser();

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-1 " + SERVICENAME_FIRST + PRICE_FIRST
                + DURATION_FIRST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + SERVICENAME_SECOND + PRICE_SECOND
                + DURATION_SECOND, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPrice_failure() {
        // invalid price
        System.out.println("1 " + SERVICENAME_FIRST + INVALID_PRICE
                + DURATION_SECOND);
        assertParseFailure(parser, "1 " + SERVICENAME_FIRST + INVALID_PRICE
                + DURATION_SECOND, Price.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDuration_failure() {
        // invalid duration
        assertParseFailure(parser, "1 " + SERVICENAME_FIRST + PRICE_FIRST
                + INVALID_DURATION, Duration.MESSAGE_CONSTRAINTS);
    }
}
