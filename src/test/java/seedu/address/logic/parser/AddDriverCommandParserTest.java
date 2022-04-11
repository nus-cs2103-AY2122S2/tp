package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BAREL;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BAREL;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BAREL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BAREL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAddressBook.BAREL;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDriverCommand;
import seedu.address.model.customer.Customer;
import seedu.address.model.driver.Driver;
import seedu.address.model.driver.NameDriver;
import seedu.address.model.driver.PhoneDriver;
import seedu.address.testutil.DriverBuilder;

public class AddDriverCommandParserTest {
    private final AddDriverCommandParser parser = new AddDriverCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Driver expectedDriver = new DriverBuilder(BAREL).build();
        Customer dummyCustomer = null;

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BAREL + PHONE_DESC_BAREL,
                new AddDriverCommand(expectedDriver, dummyCustomer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BAREL + PHONE_DESC_BAREL,
                new AddDriverCommand(expectedDriver, dummyCustomer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BAREL + PHONE_DESC_AMY + PHONE_DESC_BAREL,
                new AddDriverCommand(expectedDriver, dummyCustomer));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Driver expectedDriver = new DriverBuilder(BAREL).build();
        Customer dummyCustomer = null;

        assertParseSuccess(parser, NAME_DESC_BAREL + PHONE_DESC_BAREL,
                new AddDriverCommand(expectedDriver, dummyCustomer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDriverCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BAREL + PHONE_DESC_BAREL,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BAREL + VALID_PHONE_BAREL,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BAREL + VALID_PHONE_BAREL,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BAREL,
                NameDriver.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BAREL + INVALID_PHONE_DESC,
                PhoneDriver.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BAREL + PHONE_DESC_BAREL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDriverCommand.MESSAGE_USAGE));
    }
}
