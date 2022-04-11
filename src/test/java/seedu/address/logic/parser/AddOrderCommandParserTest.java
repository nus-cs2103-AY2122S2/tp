package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DISH_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalOrders.AMY_ORDER;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddOrderCommand;
import seedu.address.model.dish.Dish;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.model.order.Order;
import seedu.address.testutil.OrderBuilder;

public class AddOrderCommandParserTest {
    private final AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Order expectedOrder = new OrderBuilder(AMY_ORDER).build();

        ArrayList<String> dishName = new ArrayList<>();
        for (Dish dish : expectedOrder.getDishes()) {
            dishName.add(dish.toString());
        }

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PHONE_DESC_AMY + DISH_DESC_AMY,
                new AddOrderCommand(expectedOrder.getCustomerPhone(), dishName.toArray(new String[0])));
    }

    @Test
    public void parse_allPresentMultipleDish_success() {
        ArrayList<Dish> dishes = new ArrayList<>();
        dishes.add(new Dish(new NameDish("Sushi"), new PriceDish("4.50")));
        dishes.add(new Dish(new NameDish("Fish and chips"), new PriceDish("6.70")));

        Order expectedOrder = new OrderBuilder(AMY_ORDER).withDish(dishes).build();

        ArrayList<String> dishName = new ArrayList<>();
        for (Dish dish : expectedOrder.getDishes()) {
            dishName.add(dish.toString());
        }

        assertParseSuccess(parser, PHONE_DESC_AMY + " d/Sushi, Fish and chips",
                new AddOrderCommand(expectedOrder.getCustomerPhone(), dishName.toArray(new String[0])));
    }

    @Test
    public void parse_compulsoryFiledMissin_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOrderCommand.MESSAGE_USAGE);

        // missing phone prefix
        assertParseFailure(parser, VALID_PHONE_AMY + " d/Sushi", expectedMessage);

        // missing dish prefix
        assertParseFailure(parser, PHONE_DESC_AMY + " Sushi", expectedMessage);

        // missing all prefixes
        assertParseFailure(parser, VALID_PHONE_AMY + " Sushi", expectedMessage);
    }
}
