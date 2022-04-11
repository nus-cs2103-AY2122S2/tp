package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MAX_INPUT_LIMIT;
import static seedu.address.commons.core.Messages.MESSAGE_MAX_SIZE_LIMIT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COLLECTION_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COLLECTION_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DELIVERYDATETIME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DELIVERYDATETIME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DETAILS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DETAILS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COLLECTIONTYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DELIVERYDATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DETAILS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DETAILS_ITEM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DETAILS_QUANTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REMARK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COLLECTIONTYPE_AMY_STRING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DELIVERYDATETIME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DETAILS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.order.Details.ITEM_MESSAGE_LIMIT;
import static seedu.address.model.order.Details.QUANTITY_MESSAGE_LIMIT;
import static seedu.address.model.util.SampleDataUtil.getDetailsList;
import static seedu.address.testutil.TypicalOrders.AMY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.order.CollectionType;
import seedu.address.model.order.DeliveryDateTime;
import seedu.address.model.order.Details;
import seedu.address.model.order.Order;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.testutil.OrderBuilder;


public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Order expectedOrder = new OrderBuilder(AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_AMY), new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, PHONE_DESC_BOB + PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_AMY), new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY
                + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_AMY), new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));


        // multiple details - all details accepted
        assertParseSuccess(parser, PHONE_DESC_AMY + DETAILS_DESC_BOB + DETAILS_DESC_AMY + REMARK_DESC_AMY
                + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_BOB, VALID_DETAILS_AMY),
                        new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));

        // multiple deliveryDateTime - last deliveryDateTime accepted
        assertParseSuccess(parser, PHONE_DESC_AMY + DETAILS_DESC_AMY + REMARK_DESC_AMY
                        + DELIVERYDATETIME_DESC_BOB + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_AMY), new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));
        // multiple collectionType - last collectionType accepted
        assertParseSuccess(parser, PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_BOB + COLLECTION_TYPE_DESC_AMY,
                new AddOrderCommand(new Phone(VALID_PHONE_AMY), new Remark(VALID_REMARK_AMY),
                        getDetailsList(VALID_DETAILS_AMY), new DeliveryDateTime(VALID_DELIVERYDATETIME_AMY),
                        CollectionType.DELIVERY));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + DETAILS_DESC_AMY
                         + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_AMY + VALID_PHONE_AMY + ADDRESS_DESC_AMY + DETAILS_DESC_AMY
                         + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + VALID_ADDRESS_AMY + DETAILS_DESC_AMY
                         + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                expectedMessage);

        // missing details prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + VALID_DETAILS_AMY
                         + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                expectedMessage);

        // missing deliverydatetime prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + DETAILS_DESC_AMY
                         + VALID_DELIVERYDATETIME_AMY + COLLECTION_TYPE_DESC_AMY,
                expectedMessage);

        // missing collectionType prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_AMY + ADDRESS_DESC_AMY + DETAILS_DESC_AMY
                         + DELIVERYDATETIME_DESC_AMY + VALID_COLLECTIONTYPE_AMY_STRING,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_AMY + VALID_PHONE_AMY + VALID_ADDRESS_AMY + VALID_DETAILS_AMY
                        + VALID_DELIVERYDATETIME_AMY + VALID_COLLECTIONTYPE_AMY_STRING,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid phone
        assertParseFailure(parser, INVALID_PHONE_DESC
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // invalid details
        assertParseFailure(parser, PHONE_DESC_AMY
                + INVALID_DETAILS_DESC + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_AMY, Details.MESSAGE_CONSTRAINTS);

        // valid + invalid details still fails
        assertParseFailure(parser, PHONE_DESC_AMY
                + DETAILS_DESC_AMY + INVALID_DETAILS_DESC + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_AMY, Details.MESSAGE_CONSTRAINTS);

        // invalid quantity passed
        assertParseFailure(parser, PHONE_DESC_AMY
                + INVALID_DETAILS_QUANTITY_DESC + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_AMY,
                String.format(MESSAGE_MAX_INPUT_LIMIT, PREFIX_DETAILS, QUANTITY_MESSAGE_LIMIT));

        // invalid number of characters in order item > 30
        assertParseFailure(parser, PHONE_DESC_AMY
                + INVALID_DETAILS_ITEM_DESC + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + COLLECTION_TYPE_DESC_AMY,
                String.format(MESSAGE_MAX_INPUT_LIMIT, PREFIX_DETAILS, ITEM_MESSAGE_LIMIT));

        // invalid deliveryDateTime
        assertParseFailure(parser, PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + INVALID_DELIVERYDATETIME_DESC
                + COLLECTION_TYPE_DESC_AMY, DeliveryDateTime.MESSAGE_CONSTRAINTS);

        // invalid collectionType
        assertParseFailure(parser, PHONE_DESC_AMY
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY
                + INVALID_COLLECTIONTYPE_DESC, CollectionType.MESSAGE_CONSTRAINTS);

        // two invalid values - only first invalid value
        assertParseFailure(parser, INVALID_PHONE_DESC
                + DETAILS_DESC_AMY + REMARK_DESC_AMY + INVALID_DELIVERYDATETIME_DESC
                + COLLECTION_TYPE_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PHONE_DESC_AMY
            + DETAILS_DESC_AMY, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE));

        // invalid number of details passed in (>5)
        assertParseFailure(parser, PHONE_DESC_AMY
                        + DETAILS_DESC_AMY + DETAILS_DESC_AMY + DETAILS_DESC_AMY + DETAILS_DESC_AMY + DETAILS_DESC_AMY
                        + DETAILS_DESC_AMY + REMARK_DESC_AMY + DELIVERYDATETIME_DESC_AMY + COLLECTION_TYPE_DESC_AMY,
                String.format(MESSAGE_MAX_SIZE_LIMIT, PREFIX_DETAILS, Order.MAX_DETAIL_SIZE));
    }
}
