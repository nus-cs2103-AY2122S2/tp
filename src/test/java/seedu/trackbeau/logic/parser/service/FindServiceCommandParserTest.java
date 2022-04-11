package seedu.trackbeau.logic.parser.service;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.model.service.ServiceSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.service.FindServiceCommand;
import seedu.trackbeau.model.service.ServiceSearchContainsKeywordsPredicate;

public class FindServiceCommandParserTest {

    private FindServiceCommandParser parser = new FindServiceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindServiceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Find services with no leading and trailing whitespaces
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        prefixArr.set(0, Arrays.asList(new String[]{"Facial", "Cut"}));
        prefixArr.set(1, Arrays.asList(new String[]{"50"}));
        prefixArr.set(2, Arrays.asList(new String[]{"90"}));
        FindServiceCommand expectedFindServiceCommand =
                new FindServiceCommand(new ServiceSearchContainsKeywordsPredicate(prefixArr));
        assertParseSuccess(parser, " n/Facial Cut pr/50 d/90", expectedFindServiceCommand);

        // Find services with multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Facial Cut \n \t pr/50 d/90\t" , expectedFindServiceCommand);

    }

}
