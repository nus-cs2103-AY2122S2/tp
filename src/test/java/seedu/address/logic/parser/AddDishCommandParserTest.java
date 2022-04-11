package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DISH_NAME_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.DISH_NAME_DESC_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_DESC_NO_DP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_DESC_TOO_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_DONUT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalAddressBook.DONUT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddDishCommand;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.testutil.DishBuilder;

public class AddDishCommandParserTest {
    private final AddDishCommandParser parser = new AddDishCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Dish expectedDish = new DishBuilder(DONUT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + DISH_NAME_DESC_DONUT + PRICE_DESC_DONUT,
                new AddDishCommand(expectedDish));

        // multiple names - last name accepted
        assertParseSuccess(parser, DISH_NAME_DESC_FRIES + DISH_NAME_DESC_DONUT + PRICE_DESC_DONUT,
                new AddDishCommand(expectedDish));

        // multiple prices - last phone accepted
        assertParseSuccess(parser, DISH_NAME_DESC_DONUT + PRICE_DESC_FRIES + PRICE_DESC_DONUT,
                new AddDishCommand(expectedDish));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDishCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_DISH_NAME_DONUT + DISH_NAME_DESC_DONUT,
                expectedMessage);

        // missing price prefix
        assertParseFailure(parser, DISH_NAME_DESC_DONUT + VALID_DISH_PRICE_DONUT,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_DISH_NAME_DONUT + VALID_DISH_PRICE_DONUT,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PRICE_DESC_DONUT,
                NameDish.MESSAGE_CONSTRAINTS);

        // invalid price, too high
        assertParseFailure(parser, DISH_NAME_DESC_DONUT + INVALID_PRICE_DESC_TOO_HIGH,
                PriceDish.MESSAGE_CONSTRAINTS);

        // invalid price, missing 2 d.p.
        assertParseFailure(parser, DISH_NAME_DESC_DONUT + INVALID_PRICE_DESC_NO_DP,
                PriceDish.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_PRICE_DESC_NO_DP,
                NameDish.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + DISH_NAME_DESC_DONUT + PRICE_DESC_DONUT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDishCommand.MESSAGE_USAGE));
    }
}
