package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_NAME_DESC_2;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_NAME_DESC_3;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_NAME_DESC_4;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_PHONE_DESC_2;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_PHONE_DESC_3;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.SellerCommandTestUtil.INVALID_TAG_DESC_2;
import static seedu.address.logic.commands.SellerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.NAME_DESC_WEIRD;
import static seedu.address.logic.commands.SellerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.SellerCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.PHONE_DESC_WEIRD;
import static seedu.address.logic.commands.SellerCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.SellerCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.SellerCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.SellerCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_NAME_WEIRD;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_PHONE_WEIRD;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.SellerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalSellers.AMY;
import static seedu.address.testutil.TypicalSellers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.seller.Seller;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.SellerBuilder;

public class AddSellerCommandParserTest {
    private final AddSellerCommandParser parser = new AddSellerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Seller expectedSeller = new SellerBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        Seller weirdSeller = new SellerBuilder().withName(VALID_NAME_WEIRD).withPhone(VALID_PHONE_WEIRD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_FRIEND, new AddSellerCommand(expectedSeller));

        // Weird Seller name and phone accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WEIRD
                + PHONE_DESC_WEIRD, new AddSellerCommand(weirdSeller));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_FRIEND, new AddSellerCommand(expectedSeller));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB
                + PHONE_DESC_AMY + PHONE_DESC_BOB
                + TAG_DESC_FRIEND, new AddSellerCommand(expectedSeller));

        // multiple tags - all accepted
        Seller expectedSellerMultipleTags = new SellerBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new AddSellerCommand(expectedSellerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Seller expectedSeller = new SellerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddSellerCommand(expectedSeller));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_emptyCompulsoryFields_failure() {

        // Empty name
        assertParseFailure(parser, " " + PREFIX_NAME + PHONE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // Empty phone
        assertParseFailure(parser, NAME_DESC_AMY + " " + PREFIX_PHONE,
                Phone.MESSAGE_CONSTRAINTS);

        // Empty tag
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + " " + PREFIX_TAG, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, INVALID_NAME_DESC_2 + PHONE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_NAME_DESC_3 + PHONE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INVALID_NAME_DESC_4 + PHONE_DESC_AMY, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC_AMY + INVALID_PHONE_DESC_2, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_PHONE_DESC_3, Phone.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TAG_DESC_2 + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_PHONE_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // last occurrence of name invalid and valid phone, fail at invalid name
        assertParseFailure(parser, NAME_DESC_AMY + INVALID_NAME_DESC + PHONE_DESC_AMY,
                Name.MESSAGE_CONSTRAINTS);

        // last occurrence of phone invalid and valid name, fail at invalid phone
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSellerCommand.MESSAGE_USAGE));
    }
}
