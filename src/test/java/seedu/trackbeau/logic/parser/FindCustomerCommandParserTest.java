package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate.FIND_ATTRIBUTE_COUNT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.customer.FindCustomerCommand;
import seedu.trackbeau.logic.parser.customer.FindCustomerCommandParser;
import seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate;

public class FindCustomerCommandParserTest {

    private FindCustomerCommandParser parser = new FindCustomerCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCustomerCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Find name with no leading and trailing whitespaces
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections
                .nCopies(FIND_ATTRIBUTE_COUNT, null));
        prefixArr.set(0, Arrays.asList(new String[]{"alex", "yeoh"}));
        prefixArr.set(1, Arrays.asList(new String[]{"87438807"}));
        prefixArr.set(2, Arrays.asList(new String[]{"alex@example.com"}));
        prefixArr.set(3, Arrays.asList(new String[]{"geylang"}));
        prefixArr.set(4, Arrays.asList(new String[]{"oily"}));
        prefixArr.set(5, Arrays.asList(new String[]{"dry"}));
        prefixArr.set(8, Arrays.asList(new String[]{"jason"}));
        prefixArr.set(9, Arrays.asList(new String[]{"acne"}));
        prefixArr.set(10, Arrays.asList(new String[]{"nickel"}));
        FindCustomerCommand expectedFindNameCommand =
                new FindCustomerCommand(new CustomerSearchContainsKeywordsPredicate(prefixArr));
        assertParseSuccess(parser, " n/alex yeoh p/87438807 e/alex@example.com"
                + " a/geylang s/oily h/dry stp/jason sep/acne al/nickel", expectedFindNameCommand);

        // Find name with multiple whitespaces between keywords
        assertParseSuccess(parser, " n/alex yeoh \n \t p/87438807 e/alex@example.com"
                + " a/geylang s/oily h/dry stp/jason sep/acne al/nickel\t", expectedFindNameCommand);

    }

}
