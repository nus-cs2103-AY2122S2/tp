package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.ADDRESS_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.ADDRESS_DESC_2;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.HOUSE_TYPE_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.HOUSE_TYPE_DESC_2;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_HOUSE_TYPE_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_PR_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_PR_DESC_2;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_PR_DESC_3;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_PR_DESC_4;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.INVALID_PR_DESC_5;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.LOCATION_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.LOCATION_DESC_2;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.PR_DESC_1;
import static seedu.address.logic.commands.PropertyCommandsTestUtil.PR_DESC_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPropertyToSell.PROPERTY_TO_SELL_ONE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddPropertyToSellCommand;
import seedu.address.model.property.Address;
import seedu.address.model.property.HouseType;
import seedu.address.model.property.Location;
import seedu.address.model.property.PriceRange;
import seedu.address.model.property.PropertyToSell;
import seedu.address.testutil.PropertyToSellBuilder;

public class AddPtsCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyToSellCommand.MESSAGE_USAGE);


    private AddPropertyToSellCommandParser parser = new AddPropertyToSellCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyToSellCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_weirdString_failure() {
        assertParseFailure(parser, "This is weird", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsPresent_success() {
        PropertyToSell validProperty = new PropertyToSellBuilder(PROPERTY_TO_SELL_ONE).build();
        Index stubIndex = Index.fromOneBased(1);
        String index = "1 ";

        // All inputs present
        assertParseSuccess(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
                + PR_DESC_1 + ADDRESS_DESC_1, new AddPropertyToSellCommand(stubIndex, validProperty));

        // All inputs present, multiple locations, last one taken
        assertParseSuccess(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_2
                + LOCATION_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1, new AddPropertyToSellCommand(stubIndex, validProperty));

        // All inputs present, multiple prices, last one taken
        assertParseSuccess(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
                + PR_DESC_2 + PR_DESC_1 + ADDRESS_DESC_1, new AddPropertyToSellCommand(stubIndex, validProperty));

        // All inputs present, multiple house types, last one taken
        assertParseSuccess(parser, index + HOUSE_TYPE_DESC_2 + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
                + PR_DESC_1 + ADDRESS_DESC_1, new AddPropertyToSellCommand(stubIndex, validProperty));

        // All inputs present, multiple addresses, last one taken
        assertParseSuccess(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1 + ADDRESS_DESC_2
                + ADDRESS_DESC_1, new AddPropertyToSellCommand(stubIndex, validProperty));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyToSellCommand.MESSAGE_USAGE);
        String index = "1 ";

        // missing location prefix
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1, expectedMessage);

        // missing housetype prefix
        assertParseFailure(parser, index + LOCATION_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1, expectedMessage);

        // missing pricerange prefix
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + ADDRESS_DESC_1,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1,
                expectedMessage);
    }

    @Test
    public void parse_emptyCompulsoryFields_failure() {
        String index = "1 ";

        // Empty location
        assertParseFailure(parser, index + PREFIX_LOCATION + HOUSE_TYPE_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1,
                Location.MESSAGE_CONSTRAINTS);
        // Empty price range
        assertParseFailure(parser, index + PREFIX_PRICE_RANGE + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);
        // Empty house type
        assertParseFailure(parser, index + PREFIX_HOUSE_TYPE + LOCATION_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1,
                HouseType.MESSAGE_CONSTRAINTS);
        // Empty address
        assertParseFailure(parser, index + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1 + PR_DESC_1 + " "
                + PREFIX_ADDRESS, Address.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidValue_failure() {
        String index = "1 ";

        // invalid ht
        assertParseFailure(parser, index + INVALID_HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1
                + ADDRESS_DESC_1, HouseType.MESSAGE_CONSTRAINTS);

        // invalid pr
        assertParseFailure(parser, index + INVALID_PR_DESC_1 + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, index + INVALID_PR_DESC_2 + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, index + INVALID_PR_DESC_3 + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, index + INVALID_PR_DESC_4 + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, index + INVALID_PR_DESC_5 + LOCATION_DESC_1 + HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);

        // valid + empty location
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + " " + PREFIX_LOCATION
                + PR_DESC_1 + ADDRESS_DESC_1, Location.MESSAGE_CONSTRAINTS);

        // valid + invalid price range
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1 + INVALID_PR_DESC_2
                + ADDRESS_DESC_1, PriceRange.MESSAGE_CONSTRAINTS);

        // valid + invalid house types
        assertParseFailure(parser, index + HOUSE_TYPE_DESC_2 + INVALID_HOUSE_TYPE_DESC_1 + LOCATION_DESC_1
                + PR_DESC_1 + ADDRESS_DESC_1, HouseType.MESSAGE_CONSTRAINTS);

        // invalid index
        assertParseFailure(parser, "-1 " + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1 + ADDRESS_DESC_1,
                String.format(MESSAGE_INVALID_FORMAT, AddPropertyToSellCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "4294967296 " + HOUSE_TYPE_DESC_1 + LOCATION_DESC_1 + PR_DESC_1
                + ADDRESS_DESC_1, String.format(MESSAGE_INVALID_FORMAT, AddPropertyToSellCommand.MESSAGE_USAGE));

        // Multiple failed inputs
        assertParseFailure(parser, index + LOCATION_DESC_1 + INVALID_PR_DESC_1 + INVALID_HOUSE_TYPE_DESC_1
                + ADDRESS_DESC_1, HouseType.MESSAGE_CONSTRAINTS);
    }
}
