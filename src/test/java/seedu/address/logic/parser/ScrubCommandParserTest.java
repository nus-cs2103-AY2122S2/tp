package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.ARRAY_OF_PREFIX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScrubCommand;
import seedu.address.model.person.predicate.ScrubPersonPredicate;

class ScrubCommandParserTest {
    private final ScrubCommandParser parser = new ScrubCommandParser();

    @Test
    void parse_invalidArg_throwsParseException() {
        String invalidArgMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScrubCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "     ", invalidArgMessage);
        assertParseFailure(parser, " a/tester st 123", invalidArgMessage);
        assertParseFailure(parser, " e/@gmail.com n/hello", invalidArgMessage);
        assertParseFailure(parser, " a/tester st 123 n/hello", invalidArgMessage);
        assertParseFailure(parser, " e/tester@gmail.com", ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
        assertParseFailure(parser, " e/@gmail.com e/tester@gmail.com", ScrubCommand.MESSAGE_WRONG_DOMAIN_FORMAT);
    }

    @Test
    void parse_validArgs_returnsScrubCommand() {
        String validPhone = " p/90400204";
        ArgumentMultimap validPhoneMap = ArgumentTokenizer.tokenize(validPhone, ARRAY_OF_PREFIX);
        ScrubPersonPredicate validPhonePredicate = new ScrubPersonPredicate(validPhoneMap);
        ScrubCommand validScrubPhoneCommand = new ScrubCommand(validPhonePredicate);
        assertParseSuccess(parser, validPhone, validScrubPhoneCommand);

        String multiplePhone = " p/90400204 p/90400203";
        ArgumentMultimap validMultiplePhone = ArgumentTokenizer.tokenize(multiplePhone, ARRAY_OF_PREFIX);
        ScrubPersonPredicate validMultiplePhonePredicate = new ScrubPersonPredicate(validMultiplePhone);
        ScrubCommand validMultiplePhoneCommand = new ScrubCommand(validMultiplePhonePredicate);
        assertParseSuccess(parser, multiplePhone, validMultiplePhoneCommand);

        String multipleEmail = " e/@mail.com e/@example";
        ArgumentMultimap validMultipleEmail = ArgumentTokenizer.tokenize(multipleEmail, ARRAY_OF_PREFIX);
        ScrubPersonPredicate validMultipleEmailPredicate = new ScrubPersonPredicate(validMultipleEmail);
        ScrubCommand validMultipleEmailCommand = new ScrubCommand(validMultipleEmailPredicate);
        assertParseSuccess(parser, multipleEmail, validMultipleEmailCommand);
    }
}
