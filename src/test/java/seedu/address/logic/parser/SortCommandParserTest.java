package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.SORT_BY_ADDRESS;
import static seedu.address.logic.commands.SortCommand.SORT_BY_EMAIL;
import static seedu.address.logic.commands.SortCommand.SORT_BY_FAVOURITE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NAME;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NUM_PROPERTIES;
import static seedu.address.logic.commands.SortCommand.SORT_BY_PHONE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_USER_TYPE;
import static seedu.address.logic.commands.SortCommand.SORT_REVERSE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.SortCommandParser.ADDRESS_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.ADDRESS_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.EMAIL_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.EMAIL_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.FAVOURITE_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.FAVOURITE_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.NAME_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.NAME_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.NUM_PROPERTIES_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.NUM_PROPERTIES_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.PHONE_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.PHONE_COMPARATOR_REVERSE;
import static seedu.address.logic.parser.SortCommandParser.USER_TYPE_COMPARATOR;
import static seedu.address.logic.parser.SortCommandParser.USER_TYPE_COMPARATOR_REVERSE;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.PersonComparator;

public class SortCommandParserTest {
    public static final String SORT_BY_NAME_REVERSE = SORT_REVERSE + SORT_BY_NAME;
    public static final String SORT_BY_PHONE_REVERSE = SORT_REVERSE + SORT_BY_PHONE;
    public static final String SORT_BY_EMAIL_REVERSE = SORT_REVERSE + SORT_BY_EMAIL;
    public static final String SORT_BY_FAVOURITE_REVERSE = SORT_REVERSE + SORT_BY_FAVOURITE;
    public static final String SORT_BY_ADDRESS_REVERSE = SORT_REVERSE + SORT_BY_ADDRESS;
    public static final String SORT_BY_USER_TYPE_REVERSE = SORT_REVERSE + SORT_BY_USER_TYPE;
    public static final String SORT_BY_NUM_PROPERTIES_REVERSE = SORT_REVERSE + SORT_BY_NUM_PROPERTIES;

    private static final String INVALID_KEYWORD = "invalidKeyword";
    private static final String INVALID_KEYWORD_REVERSE = SORT_REVERSE + "invalidKeyword";

    private static final String EXPECTED_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_failure() {
        // empty string
        assertParseFailure(parser, "", EXPECTED_MESSAGE);

        // only whitespace
        assertParseFailure(parser, "     ", EXPECTED_MESSAGE);
    }

    @Test
    public void parse_invalidKeyword_failure() {
        // only invalid
        assertParseFailure(parser, INVALID_KEYWORD, EXPECTED_MESSAGE);
        assertParseFailure(parser, INVALID_KEYWORD_REVERSE, EXPECTED_MESSAGE);

        // valid followed by invalid
        assertParseFailure(parser, SORT_BY_NAME + " " + INVALID_KEYWORD, EXPECTED_MESSAGE);
        assertParseFailure(parser, SORT_BY_NAME + " " + INVALID_KEYWORD_REVERSE, EXPECTED_MESSAGE);

        // invalid followed by valid
        assertParseFailure(parser, INVALID_KEYWORD + " " + SORT_BY_NAME, EXPECTED_MESSAGE);
        assertParseFailure(parser, INVALID_KEYWORD_REVERSE + " " + SORT_BY_NAME, EXPECTED_MESSAGE);
    }


    @Test
    public void parse_oneValidKeyword_success() {
        SortCommand expectedSortByNameCommand = new SortCommand(new PersonComparator(List.of(NAME_COMPARATOR)));
        SortCommand expectedSortByPhoneCommand = new SortCommand(new PersonComparator(List.of(PHONE_COMPARATOR)));
        SortCommand expectedSortByEmailCommand = new SortCommand(new PersonComparator(List.of(EMAIL_COMPARATOR)));
        SortCommand expectedSortByFavouriteCommand =
                new SortCommand(new PersonComparator(List.of(FAVOURITE_COMPARATOR)));
        SortCommand expectedSortByAddressCommand = new SortCommand(new PersonComparator(List.of(ADDRESS_COMPARATOR)));
        SortCommand expectedSortByUserTypeCommand =
                new SortCommand(new PersonComparator(List.of(USER_TYPE_COMPARATOR)));
        SortCommand expectedSortByNumPropertiesCommand =
                new SortCommand(new PersonComparator(List.of(NUM_PROPERTIES_COMPARATOR)));

        // no whitespace
        assertParseSuccess(parser, SORT_BY_NAME, expectedSortByNameCommand);
        assertParseSuccess(parser, SORT_BY_PHONE, expectedSortByPhoneCommand);
        assertParseSuccess(parser, SORT_BY_EMAIL, expectedSortByEmailCommand);
        assertParseSuccess(parser, SORT_BY_FAVOURITE, expectedSortByFavouriteCommand);
        assertParseSuccess(parser, SORT_BY_ADDRESS, expectedSortByAddressCommand);
        assertParseSuccess(parser, SORT_BY_USER_TYPE, expectedSortByUserTypeCommand);
        assertParseSuccess(parser, SORT_BY_NUM_PROPERTIES, expectedSortByNumPropertiesCommand);

        // leading space
        assertParseSuccess(parser, " " + SORT_BY_NAME, expectedSortByNameCommand);
        // trailing space
        assertParseSuccess(parser, SORT_BY_NAME + " ", expectedSortByNameCommand);
        // leading and trailing space
        assertParseSuccess(parser, " " + SORT_BY_NAME + " ", expectedSortByNameCommand);

        // leading newline
        assertParseSuccess(parser, "\n" + SORT_BY_NAME, expectedSortByNameCommand);
        // trailing newline
        assertParseSuccess(parser, SORT_BY_NAME + "\n", expectedSortByNameCommand);
        // leading and trailing newline
        assertParseSuccess(parser, "\n" + SORT_BY_NAME + "\n", expectedSortByNameCommand);

        // leading tab
        assertParseSuccess(parser, "\t" + SORT_BY_NAME, expectedSortByNameCommand);
        // trailing tab
        assertParseSuccess(parser, SORT_BY_NAME + "\t", expectedSortByNameCommand);
        // leading and trailing tab
        assertParseSuccess(parser, "\t" + SORT_BY_NAME + "\t", expectedSortByNameCommand);

        // leading space, newline and tab
        assertParseSuccess(parser, " \n\t" + SORT_BY_NAME, expectedSortByNameCommand);
        // trailing space, newline and tab
        assertParseSuccess(parser, SORT_BY_NAME + " \n\t", expectedSortByNameCommand);
        // leading and trailing space, newline and tab
        assertParseSuccess(parser, " \n\t" + SORT_BY_NAME + " \n\t", expectedSortByNameCommand);
    }

    @Test
    public void parse_oneValidReverseKeyword_success() {
        SortCommand expectedSortByNameCommand = new SortCommand(new PersonComparator(List.of(NAME_COMPARATOR_REVERSE)));
        SortCommand expectedSortByPhoneCommand =
                new SortCommand(new PersonComparator(List.of(PHONE_COMPARATOR_REVERSE)));
        SortCommand expectedSortByEmailCommand =
                new SortCommand(new PersonComparator(List.of(EMAIL_COMPARATOR_REVERSE)));
        SortCommand expectedSortByFavouriteCommand =
                new SortCommand(new PersonComparator(List.of(FAVOURITE_COMPARATOR_REVERSE)));
        SortCommand expectedSortByAddressCommand =
                new SortCommand(new PersonComparator(List.of(ADDRESS_COMPARATOR_REVERSE)));
        SortCommand expectedSortByUserTypeCommand =
                new SortCommand(new PersonComparator(List.of(USER_TYPE_COMPARATOR_REVERSE)));
        SortCommand expectedSortByNumPropertiesCommand =
                new SortCommand(new PersonComparator(List.of(NUM_PROPERTIES_COMPARATOR_REVERSE)));

        // no whitespace
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE, expectedSortByNameCommand);
        assertParseSuccess(parser, SORT_BY_PHONE_REVERSE, expectedSortByPhoneCommand);
        assertParseSuccess(parser, SORT_BY_EMAIL_REVERSE, expectedSortByEmailCommand);
        assertParseSuccess(parser, SORT_BY_FAVOURITE_REVERSE, expectedSortByFavouriteCommand);
        assertParseSuccess(parser, SORT_BY_ADDRESS_REVERSE, expectedSortByAddressCommand);
        assertParseSuccess(parser, SORT_BY_USER_TYPE_REVERSE, expectedSortByUserTypeCommand);
        assertParseSuccess(parser, SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortByNumPropertiesCommand);

        // leading space
        assertParseSuccess(parser, " " + SORT_BY_NAME_REVERSE, expectedSortByNameCommand);
        // trailing space
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + " ", expectedSortByNameCommand);
        // leading and trailing space
        assertParseSuccess(parser, " " + SORT_BY_NAME_REVERSE + " ", expectedSortByNameCommand);

        // leading newline
        assertParseSuccess(parser, "\n" + SORT_BY_NAME_REVERSE, expectedSortByNameCommand);
        // trailing newline
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\n", expectedSortByNameCommand);
        // leading and trailing newline
        assertParseSuccess(parser, "\n" + SORT_BY_NAME_REVERSE + "\n", expectedSortByNameCommand);

        // leading tab
        assertParseSuccess(parser, "\t" + SORT_BY_NAME_REVERSE, expectedSortByNameCommand);
        // trailing tab
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\t", expectedSortByNameCommand);
        // leading and trailing tab
        assertParseSuccess(parser, "\t" + SORT_BY_NAME_REVERSE + "\t", expectedSortByNameCommand);

        // leading space, newline and tab
        assertParseSuccess(parser, " \n\t" + SORT_BY_NAME_REVERSE, expectedSortByNameCommand);
        // trailing space, newline and tab
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + " \n\t", expectedSortByNameCommand);
        // leading and trailing space, newline and tab
        assertParseSuccess(parser, " \n\t" + SORT_BY_NAME_REVERSE + " \n\t", expectedSortByNameCommand);
    }

    @Test
    public void parse_multipleKeywords_success() {
        SortCommand expectedSortCommand =
                new SortCommand(new PersonComparator(List.of(NAME_COMPARATOR, PHONE_COMPARATOR)));

        // one space between keywords
        assertParseSuccess(parser, SORT_BY_NAME + " " + SORT_BY_PHONE, expectedSortCommand);
        // multiple spaces between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "   " + SORT_BY_PHONE, expectedSortCommand);
        // multiple spaces between keywords with leading and trailing spaces
        assertParseSuccess(parser, "   " + SORT_BY_NAME + "   " + SORT_BY_PHONE + "   ",
                expectedSortCommand);

        // one tab between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t" + SORT_BY_PHONE, expectedSortCommand);
        // multiple tabs between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE,
                expectedSortCommand);
        // multiple tabs between keywords with leading and trailing tabs
        assertParseSuccess(parser,
                "\t\t\t" + SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE + "\t\t\t",
                expectedSortCommand);

        // one newline between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n" + SORT_BY_PHONE, expectedSortCommand);
        // multiple newlines between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE,
                expectedSortCommand);
        // multiple newlines between keywords with leading and trailing newlines
        assertParseSuccess(parser,
                "\n\n\n" + SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE + "\n\n\n",
                expectedSortCommand);
    }

    @Test
    public void parse_multipleReverseKeywords_success() {
        SortCommand expectedSortCommand =
                new SortCommand(new PersonComparator(List.of(NAME_COMPARATOR_REVERSE, PHONE_COMPARATOR_REVERSE)));

        // one space between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + " " + SORT_BY_PHONE_REVERSE, expectedSortCommand);
        // multiple spaces between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "   " + SORT_BY_PHONE_REVERSE, expectedSortCommand);
        // multiple spaces between keywords with leading and trailing spaces
        assertParseSuccess(parser, "   " + SORT_BY_NAME_REVERSE + "   " + SORT_BY_PHONE_REVERSE + "   ",
                expectedSortCommand);

        // one tab between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\t" + SORT_BY_PHONE_REVERSE, expectedSortCommand);
        // multiple tabs between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\t\t\t" + SORT_BY_PHONE_REVERSE,
                expectedSortCommand);
        // multiple tabs between keywords with leading and trailing tabs
        assertParseSuccess(parser,
                "\t\t\t" + SORT_BY_NAME_REVERSE + "\t\t\t" + SORT_BY_PHONE_REVERSE + "\t\t\t",
                expectedSortCommand);

        // one newline between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\n" + SORT_BY_PHONE_REVERSE, expectedSortCommand);
        // multiple newlines between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\n\n\n" + SORT_BY_PHONE_REVERSE,
                expectedSortCommand);
        // multiple newlines between keywords with leading and trailing newlines
        assertParseSuccess(parser,
                "\n\n\n" + SORT_BY_NAME_REVERSE + "\n\n\n" + SORT_BY_PHONE_REVERSE + "\n\n\n",
                expectedSortCommand);
    }

    @Test
    public void parse_allKeywords_success() {
        SortCommand expectedSortCommand =
                new SortCommand(new PersonComparator(
                        List.of(NAME_COMPARATOR, PHONE_COMPARATOR, EMAIL_COMPARATOR, FAVOURITE_COMPARATOR,
                                ADDRESS_COMPARATOR, USER_TYPE_COMPARATOR, NUM_PROPERTIES_COMPARATOR)));

        // one space between keywords
        assertParseSuccess(parser, SORT_BY_NAME + " " + SORT_BY_PHONE + " "
                + SORT_BY_EMAIL + " " + SORT_BY_FAVOURITE + " "
                + SORT_BY_ADDRESS + " " + SORT_BY_USER_TYPE + " "
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple spaces between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "   " + SORT_BY_PHONE + "   "
                + SORT_BY_EMAIL + "   " + SORT_BY_FAVOURITE + "   "
                + SORT_BY_ADDRESS + "   " + SORT_BY_USER_TYPE + "   "
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple spaces between keywords with leading and trailing spaces
        assertParseSuccess(parser, "   " + SORT_BY_NAME + "   " + SORT_BY_PHONE + "   "
                + SORT_BY_EMAIL + "   " + SORT_BY_FAVOURITE + "   "
                + SORT_BY_ADDRESS + "   " + SORT_BY_USER_TYPE + "   "
                + SORT_BY_NUM_PROPERTIES + "   ", expectedSortCommand);

        // one tab between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t" + SORT_BY_PHONE + "\t"
                + SORT_BY_EMAIL + "\t" + SORT_BY_FAVOURITE + "\t"
                + SORT_BY_ADDRESS + "\t" + SORT_BY_USER_TYPE + "\t"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple tabs between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE + "\t\t\t"
                + SORT_BY_EMAIL + "\t\t\t" + SORT_BY_FAVOURITE + "\t\t\t"
                + SORT_BY_ADDRESS + "\t\t\t" + SORT_BY_USER_TYPE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple tabs between keywords with leading and trailing tabs
        assertParseSuccess(parser, "\t\t\t" + SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE + "\t\t\t"
                + SORT_BY_EMAIL + "\t\t\t" + SORT_BY_FAVOURITE + "\t\t\t"
                + SORT_BY_ADDRESS + "\t\t\t" + SORT_BY_USER_TYPE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES + "\t\t\t", expectedSortCommand);

        // one newline between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n" + SORT_BY_PHONE + "\n"
                + SORT_BY_EMAIL + "\n" + SORT_BY_FAVOURITE + "\n"
                + SORT_BY_ADDRESS + "\n" + SORT_BY_USER_TYPE + "\n"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple newlines between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE + "\n\n\n"
                + SORT_BY_EMAIL + "\n\n\n" + SORT_BY_FAVOURITE + "\n\n\n"
                + SORT_BY_ADDRESS + "\n\n\n" + SORT_BY_USER_TYPE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple newlines between keywords with leading and trailing newlines
        assertParseSuccess(parser, "\n\n\n" + SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE + "\n\n\n"
                + SORT_BY_EMAIL + "\n\n\n" + SORT_BY_FAVOURITE + "\n\n\n"
                + SORT_BY_ADDRESS + "\n\n\n" + SORT_BY_USER_TYPE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES + "\n\n\n", expectedSortCommand);
    }

    @Test
    public void parse_allReverseKeywords_success() {
        SortCommand expectedSortCommand =
                new SortCommand(new PersonComparator(
                        List.of(NAME_COMPARATOR_REVERSE, PHONE_COMPARATOR_REVERSE, EMAIL_COMPARATOR_REVERSE,
                                FAVOURITE_COMPARATOR_REVERSE, ADDRESS_COMPARATOR_REVERSE, USER_TYPE_COMPARATOR_REVERSE,
                                NUM_PROPERTIES_COMPARATOR_REVERSE)));

        // one space between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + " " + SORT_BY_PHONE_REVERSE + " "
                + SORT_BY_EMAIL_REVERSE + " " + SORT_BY_FAVOURITE_REVERSE + " "
                + SORT_BY_ADDRESS_REVERSE + " " + SORT_BY_USER_TYPE_REVERSE + " "
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple spaces between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "   " + SORT_BY_PHONE_REVERSE + "   "
                + SORT_BY_EMAIL_REVERSE + "   " + SORT_BY_FAVOURITE_REVERSE + "   "
                + SORT_BY_ADDRESS_REVERSE + "   " + SORT_BY_USER_TYPE_REVERSE + "   "
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple spaces between keywords with leading and trailing spaces
        assertParseSuccess(parser, "   " + SORT_BY_NAME_REVERSE + "   " + SORT_BY_PHONE_REVERSE + "   "
                + SORT_BY_EMAIL_REVERSE + "   " + SORT_BY_FAVOURITE_REVERSE + "   "
                + SORT_BY_ADDRESS_REVERSE + "   " + SORT_BY_USER_TYPE_REVERSE + "   "
                + SORT_BY_NUM_PROPERTIES_REVERSE + "   ", expectedSortCommand);

        // one tab between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\t" + SORT_BY_PHONE_REVERSE + "\t"
                + SORT_BY_EMAIL_REVERSE + "\t" + SORT_BY_FAVOURITE_REVERSE + "\t"
                + SORT_BY_ADDRESS_REVERSE + "\t" + SORT_BY_USER_TYPE_REVERSE + "\t"
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple tabs between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\t\t\t" + SORT_BY_PHONE_REVERSE + "\t\t\t"
                + SORT_BY_EMAIL_REVERSE + "\t\t\t" + SORT_BY_FAVOURITE_REVERSE + "\t\t\t"
                + SORT_BY_ADDRESS_REVERSE + "\t\t\t" + SORT_BY_USER_TYPE_REVERSE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple tabs between keywords with leading and trailing tabs
        assertParseSuccess(parser, "\t\t\t" + SORT_BY_NAME_REVERSE + "\t\t\t" + SORT_BY_PHONE_REVERSE + "\t\t\t"
                + SORT_BY_EMAIL_REVERSE + "\t\t\t" + SORT_BY_FAVOURITE_REVERSE + "\t\t\t"
                + SORT_BY_ADDRESS_REVERSE + "\t\t\t" + SORT_BY_USER_TYPE_REVERSE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES_REVERSE + "\t\t\t", expectedSortCommand);

        // one newline between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\n" + SORT_BY_PHONE_REVERSE + "\n"
                + SORT_BY_EMAIL_REVERSE + "\n" + SORT_BY_FAVOURITE_REVERSE + "\n"
                + SORT_BY_ADDRESS_REVERSE + "\n" + SORT_BY_USER_TYPE_REVERSE + "\n"
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple newlines between keywords
        assertParseSuccess(parser, SORT_BY_NAME_REVERSE + "\n\n\n" + SORT_BY_PHONE_REVERSE + "\n\n\n"
                + SORT_BY_EMAIL_REVERSE + "\n\n\n" + SORT_BY_FAVOURITE_REVERSE + "\n\n\n"
                + SORT_BY_ADDRESS_REVERSE + "\n\n\n" + SORT_BY_USER_TYPE_REVERSE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES_REVERSE, expectedSortCommand);
        // multiple newlines between keywords with leading and trailing newlines
        assertParseSuccess(parser, "\n\n\n" + SORT_BY_NAME_REVERSE + "\n\n\n" + SORT_BY_PHONE_REVERSE + "\n\n\n"
                + SORT_BY_EMAIL_REVERSE + "\n\n\n" + SORT_BY_FAVOURITE_REVERSE + "\n\n\n"
                + SORT_BY_ADDRESS_REVERSE + "\n\n\n" + SORT_BY_USER_TYPE_REVERSE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES_REVERSE + "\n\n\n", expectedSortCommand);
    }

    @Test
    public void parse_multipleReverseAndNonReverseKeywords_success() {
        SortCommand expectedSortCommand =
                new SortCommand(new PersonComparator(
                        List.of(NAME_COMPARATOR, PHONE_COMPARATOR_REVERSE, EMAIL_COMPARATOR,
                                FAVOURITE_COMPARATOR_REVERSE, ADDRESS_COMPARATOR, USER_TYPE_COMPARATOR_REVERSE,
                                NUM_PROPERTIES_COMPARATOR)));

        // one space between keywords
        assertParseSuccess(parser, SORT_BY_NAME + " " + SORT_BY_PHONE_REVERSE + " "
                + SORT_BY_EMAIL + " " + SORT_BY_FAVOURITE_REVERSE + " "
                + SORT_BY_ADDRESS + " " + SORT_BY_USER_TYPE_REVERSE + " "
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple spaces between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "   " + SORT_BY_PHONE_REVERSE + "   "
                + SORT_BY_EMAIL + "   " + SORT_BY_FAVOURITE_REVERSE + "   "
                + SORT_BY_ADDRESS + "   " + SORT_BY_USER_TYPE_REVERSE + "   "
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple spaces between keywords with leading and trailing spaces
        assertParseSuccess(parser, "   " + SORT_BY_NAME + "   " + SORT_BY_PHONE_REVERSE + "   "
                + SORT_BY_EMAIL + "   " + SORT_BY_FAVOURITE_REVERSE + "   "
                + SORT_BY_ADDRESS + "   " + SORT_BY_USER_TYPE_REVERSE + "   "
                + SORT_BY_NUM_PROPERTIES + "   ", expectedSortCommand);

        // one tab between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t" + SORT_BY_PHONE_REVERSE + "\t"
                + SORT_BY_EMAIL + "\t" + SORT_BY_FAVOURITE_REVERSE + "\t"
                + SORT_BY_ADDRESS + "\t" + SORT_BY_USER_TYPE_REVERSE + "\t"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple tabs between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE_REVERSE + "\t\t\t"
                + SORT_BY_EMAIL + "\t\t\t" + SORT_BY_FAVOURITE_REVERSE + "\t\t\t"
                + SORT_BY_ADDRESS + "\t\t\t" + SORT_BY_USER_TYPE_REVERSE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple tabs between keywords with leading and trailing tabs
        assertParseSuccess(parser, "\t\t\t" + SORT_BY_NAME + "\t\t\t" + SORT_BY_PHONE_REVERSE + "\t\t\t"
                + SORT_BY_EMAIL + "\t\t\t" + SORT_BY_FAVOURITE_REVERSE + "\t\t\t"
                + SORT_BY_ADDRESS + "\t\t\t" + SORT_BY_USER_TYPE_REVERSE + "\t\t\t"
                + SORT_BY_NUM_PROPERTIES + "\t\t\t", expectedSortCommand);

        // one newline between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n" + SORT_BY_PHONE_REVERSE + "\n"
                + SORT_BY_EMAIL + "\n" + SORT_BY_FAVOURITE_REVERSE + "\n"
                + SORT_BY_ADDRESS + "\n" + SORT_BY_USER_TYPE_REVERSE + "\n"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple newlines between keywords
        assertParseSuccess(parser, SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE_REVERSE + "\n\n\n"
                + SORT_BY_EMAIL + "\n\n\n" + SORT_BY_FAVOURITE_REVERSE + "\n\n\n"
                + SORT_BY_ADDRESS + "\n\n\n" + SORT_BY_USER_TYPE_REVERSE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES, expectedSortCommand);
        // multiple newlines between keywords with leading and trailing newlines
        assertParseSuccess(parser, "\n\n\n" + SORT_BY_NAME + "\n\n\n" + SORT_BY_PHONE_REVERSE + "\n\n\n"
                + SORT_BY_EMAIL + "\n\n\n" + SORT_BY_FAVOURITE_REVERSE + "\n\n\n"
                + SORT_BY_ADDRESS + "\n\n\n" + SORT_BY_USER_TYPE_REVERSE + "\n\n\n"
                + SORT_BY_NUM_PROPERTIES + "\n\n\n", expectedSortCommand);
    }

}
