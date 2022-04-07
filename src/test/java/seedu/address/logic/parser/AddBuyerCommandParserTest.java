package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC_2;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC_3;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_NAME_DESC_4;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PHONE_DESC_2;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_PHONE_DESC_3;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.BuyerCommandTestUtil.INVALID_TAG_DESC_2;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.NAME_DESC_WEIRD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PHONE_DESC_WEIRD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.BuyerCommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_NAME_WEIRD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_PHONE_WEIRD;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.BuyerCommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBuyers.AMY;
import static seedu.address.testutil.TypicalBuyers.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.client.Name;
import seedu.address.model.client.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.BuyerBuilder;

public class AddBuyerCommandParserTest {
    private final AddBuyerCommandParser parser = new AddBuyerCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Buyer expectedBuyer = new BuyerBuilder(BOB).withTags(VALID_TAG_FRIEND).build();
        Buyer weirdBuyer = new BuyerBuilder().withName(VALID_NAME_WEIRD).withPhone(VALID_PHONE_WEIRD).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_FRIEND, new AddBuyerCommand(expectedBuyer));

        // Weird buyer name and phone accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_WEIRD
                + PHONE_DESC_WEIRD, new AddBuyerCommand(weirdBuyer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_FRIEND, new AddBuyerCommand(expectedBuyer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB
                + PHONE_DESC_AMY + PHONE_DESC_BOB
                + TAG_DESC_FRIEND, new AddBuyerCommand(expectedBuyer));

        // multiple tags - all accepted
        Buyer expectedBuyerMultipleTags = new BuyerBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB
                + PHONE_DESC_BOB + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, new AddBuyerCommand(expectedBuyerMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Buyer expectedBuyer = new BuyerBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddBuyerCommand(expectedBuyer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE);

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
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBuyerCommand.MESSAGE_USAGE));
    }
}
