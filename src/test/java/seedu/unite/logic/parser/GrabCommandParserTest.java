package seedu.unite.logic.parser;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_COURSE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_MATRICCARD;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.unite.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.unite.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.GrabCommand;

public class GrabCommandParserTest {

    private final GrabCommandParser parser = new GrabCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsWithIndexNoTag_returnsGrabCommand() {
        // no leading and trailing whitespaces
        GrabCommand expectedGrabCommandWithAddress =
                new GrabCommand(PREFIX_ADDRESS, "", null);
        assertParseSuccess(parser, " a/" , expectedGrabCommandWithAddress);

        GrabCommand expectedGrabCommandWithCourse =
                new GrabCommand(PREFIX_COURSE, "", null);
        assertParseSuccess(parser, " c/" , expectedGrabCommandWithCourse);


        GrabCommand expectedGrabCommandWithEmail =
                new GrabCommand(PREFIX_EMAIL, "", null);
        assertParseSuccess(parser, " e/" , expectedGrabCommandWithEmail);


        GrabCommand expectedGrabCommandWithMatricCard =
                new GrabCommand(PREFIX_MATRICCARD, "", null);
        assertParseSuccess(parser, " m/" , expectedGrabCommandWithMatricCard);


        GrabCommand expectedGrabCommandWithName =
                new GrabCommand(PREFIX_NAME, "", null);
        assertParseSuccess(parser, " n/" , expectedGrabCommandWithName);


        GrabCommand expectedGrabCommandWithPhone =
                new GrabCommand(PREFIX_PHONE, "", null);
        assertParseSuccess(parser, " p/", expectedGrabCommandWithPhone);


        GrabCommand expectedGrabCommandWithTelegram =
                new GrabCommand(PREFIX_TELEGRAM, "", null);
        assertParseSuccess(parser, " tele/" , expectedGrabCommandWithTelegram);
    }

    @Test
    public void parse_tooManyArgs_throwsParseException() {
        assertParseFailure(parser, " tele/ a/",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GrabCommand.MESSAGE_TOO_MANY_ARGUMENT));
    }
}
