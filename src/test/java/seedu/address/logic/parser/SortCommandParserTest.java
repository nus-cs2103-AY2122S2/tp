package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.SortUtil;

class SortCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            SortCommand.MESSAGE_USAGE);

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalid_failure() {
        // invalid field, cannot sort by tags
        assertParseFailure(parser, Tag.PREFIX.getPrefix(), MESSAGE_INVALID_FORMAT);

        // invalid input, only accepts valid field input and descending keyword
        assertParseFailure(parser, "hello " + Phone.PREFIX.getPrefix(), MESSAGE_INVALID_FORMAT);

        // invalid prefix being passed in
        assertParseFailure(parser, "i/", MESSAGE_INVALID_FORMAT);

        // invalid values mixed with valid values
        assertParseFailure(parser,
                Phone.PREFIX.getPrefix() + SortUtil.DESC_STRING + " " + Email.PREFIX.getPrefix() + "   asce",
                MESSAGE_INVALID_FORMAT);

        // desc keyword without a prefix before it
        assertParseFailure(parser, SortCommand.DESCENDING_KEYWORD + " " + Phone.PREFIX.getPrefix(),
                MESSAGE_INVALID_FORMAT);

        // just desc keyword alone
        assertParseFailure(parser, SortCommand.DESCENDING_KEYWORD, MESSAGE_INVALID_FORMAT);

        // no spaces in between desc keyword and prefix
        assertParseFailure(parser, Phone.PREFIX.getPrefix() + SortCommand.DESCENDING_KEYWORD,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        SortCommand expectedCommand;

        // name ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Name.PREFIX.getPrefix(), expectedCommand);

        // name descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Name.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);

        // phone ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.PHONE_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Phone.PREFIX.getPrefix(), expectedCommand);

        // phone descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.PHONE_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Phone.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);

        // email ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Email.PREFIX.getPrefix(), expectedCommand);

        // email descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Email.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);

        // address ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Address.PREFIX.getPrefix(), expectedCommand);

        // address descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Address.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);

        // address ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Remark.PREFIX.getPrefix(), expectedCommand);

        // Remark descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.REMARK_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Remark.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);

        // Birthday ascending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Birthday.PREFIX.getPrefix(), expectedCommand);

        // Birthday descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        assertParseSuccess(parser, Birthday.PREFIX.getPrefix() + SortUtil.DESC_STRING, expectedCommand);
    }

    @Test
    public void parse_someFieldSpecified_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        SortCommand expectedCommand;
        String input;

        // birthday, name descending, phone, remark
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);
        fieldSortOrder.add(SortUtil.PHONE_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Birthday.PREFIX.getPrefix() + Name.PREFIX.getPrefix() + SortUtil.DESC_STRING + Phone.PREFIX.getPrefix()
                + Remark.PREFIX.getPrefix();
        assertParseSuccess(parser, input, expectedCommand);

        // birthday desc, name desc, email desc
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Birthday.PREFIX.getPrefix() + SortUtil.DESC_STRING + Name.PREFIX.getPrefix() + SortUtil.DESC_STRING
                + Email.PREFIX.getPrefix() + SortUtil.DESC_STRING;
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_repeatedFieldSpecified_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        SortCommand expectedCommand;
        String input;

        // double birthday
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Birthday.PREFIX.getPrefix() + Birthday.PREFIX.getPrefix();
        assertParseSuccess(parser, input, expectedCommand);

        // double birthday in different sort order
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Birthday.PREFIX.getPrefix() + Birthday.PREFIX.getPrefix() + SortUtil.DESC_STRING;
        assertParseSuccess(parser, input, expectedCommand);

        // multiple prefix input with duplicate name
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Name.PREFIX.getPrefix() + Email.PREFIX.getPrefix() + Remark.PREFIX.getPrefix()
                + Name.PREFIX.getPrefix();
        assertParseSuccess(parser, input, expectedCommand);

        // multiple prefix input with duplicate name but one is descending
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Name.PREFIX.getPrefix() + Email.PREFIX.getPrefix() + Remark.PREFIX.getPrefix() + Name.PREFIX.getPrefix()
                + SortUtil.DESC_STRING;
        assertParseSuccess(parser, input, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        ArrayList<SortCommand.FieldSortOrder> fieldSortOrder;
        SortCommand expectedCommand;
        String input;

        // all fields specified in default order
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.PHONE_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DEFAULT);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DEFAULT);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Name.PREFIX.getPrefix() + Phone.PREFIX.getPrefix() + Email.PREFIX.getPrefix()
                + Address.PREFIX.getPrefix() + Remark.PREFIX.getPrefix() + Birthday.PREFIX.getPrefix();
        assertParseSuccess(parser, input, expectedCommand);

        // all fields specified in descending order
        fieldSortOrder = new ArrayList<>();
        fieldSortOrder.add(SortUtil.NAME_SORT_DESC);
        fieldSortOrder.add(SortUtil.PHONE_SORT_DESC);
        fieldSortOrder.add(SortUtil.EMAIL_SORT_DESC);
        fieldSortOrder.add(SortUtil.ADDRESS_SORT_DESC);
        fieldSortOrder.add(SortUtil.REMARK_SORT_DESC);
        fieldSortOrder.add(SortUtil.BIRTHDAY_SORT_DESC);
        expectedCommand = new SortCommand(fieldSortOrder);
        input = Name.PREFIX.getPrefix() + SortUtil.DESC_STRING + Phone.PREFIX.getPrefix() + SortUtil.DESC_STRING
                + Email.PREFIX.getPrefix() + SortUtil.DESC_STRING + Address.PREFIX.getPrefix() + SortUtil.DESC_STRING
                + Remark.PREFIX.getPrefix() + SortUtil.DESC_STRING + Birthday.PREFIX.getPrefix() + SortUtil.DESC_STRING;
        assertParseSuccess(parser, input, expectedCommand);
    }

}
