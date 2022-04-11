package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
// import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PLAN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.PLAN_DESC_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TECH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BURGER;
// import static seedu.address.logic.commands.CommandTestUtil.VALID_PLAN_BURGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FASTFOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TECH;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalClients.APPLE;
import static seedu.address.testutil.TypicalClients.BURGER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClientCommand;
import seedu.address.model.client.Address;
import seedu.address.model.client.Client;
import seedu.address.model.client.Email;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.client.Plan;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.ClientBuilder;

public class AddClientCommandParserTest {
    private AddClientCommandParser parser = new AddClientCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Client expectedClient = new ClientBuilder(BURGER).withTags(VALID_TAG_FASTFOOD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_APPLE + NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BURGER + PHONE_DESC_APPLE + PHONE_DESC_BURGER + EMAIL_DESC_BURGER
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_APPLE + EMAIL_DESC_BURGER
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_APPLE
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple plans - last plan accepted
        assertParseSuccess(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + PLAN_DESC_APPLE + PLAN_DESC_BURGER + TAG_DESC_FASTFOOD, new AddClientCommand(expectedClient));

        // multiple tags - all accepted
        Client expectedClientMultipleTags = new ClientBuilder(BURGER).withTags(VALID_TAG_FASTFOOD, VALID_TAG_TECH)
                .build();
        assertParseSuccess(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD,
                new AddClientCommand(expectedClientMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Client expectedClient = new ClientBuilder(APPLE).withTags().build();
        assertParseSuccess(parser, NAME_DESC_APPLE + PHONE_DESC_APPLE + EMAIL_DESC_APPLE + ADDRESS_DESC_APPLE
                + PLAN_DESC_APPLE, new AddClientCommand(expectedClient));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BURGER + VALID_PHONE_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + VALID_EMAIL_BURGER + ADDRESS_DESC_BURGER,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + VALID_ADDRESS_BURGER,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BURGER + VALID_PHONE_BURGER + VALID_EMAIL_BURGER + VALID_ADDRESS_BURGER,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BURGER + INVALID_PHONE_DESC + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + INVALID_EMAIL_DESC + ADDRESS_DESC_BURGER
                + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + INVALID_ADDRESS_DESC
                + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD, Address.MESSAGE_CONSTRAINTS);

        // invalid plan
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + INVALID_PLAN_DESC + TAG_DESC_TECH + TAG_DESC_FASTFOOD, Plan.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + ADDRESS_DESC_BURGER
                + PLAN_DESC_BURGER + INVALID_TAG_DESC + VALID_TAG_FASTFOOD, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BURGER + EMAIL_DESC_BURGER + INVALID_ADDRESS_DESC
                + PLAN_DESC_BURGER, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BURGER + PHONE_DESC_BURGER + EMAIL_DESC_BURGER
                + ADDRESS_DESC_BURGER + PLAN_DESC_BURGER + TAG_DESC_TECH + TAG_DESC_FASTFOOD,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
    }
}
