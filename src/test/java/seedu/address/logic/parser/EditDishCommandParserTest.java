package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DISH_NAME_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.DISH_NAME_DESC_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_DESC_NO_DP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_DESC_TOO_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_DESC_FRIES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_NAME_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_DONUT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DISH_PRICE_FRIES;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDishCommand;
import seedu.address.model.dish.NameDish;
import seedu.address.model.dish.PriceDish;
import seedu.address.testutil.EditDishDescriptorBuilder;

public class EditDishCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDishCommand.MESSAGE_USAGE);

    private final EditDishCommandParser parser = new EditDishCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DISH_NAME_DONUT, MESSAGE_INVALID_FORMAT);

        // no field specified covered in EditDishCommand

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DISH_NAME_DESC_DONUT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DISH_NAME_DESC_DONUT, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, NameDish.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_PRICE_DESC_NO_DP, PriceDish.MESSAGE_CONSTRAINTS); // invalid price, no 2 d.p.
        assertParseFailure(parser, "1"
                + INVALID_PRICE_DESC_TOO_HIGH, PriceDish.MESSAGE_CONSTRAINTS); // invalid price, too high

        // valid name followed by invalid price
        assertParseFailure(parser, "1" + DISH_NAME_DESC_DONUT
                + INVALID_PRICE_DESC_NO_DP, PriceDish.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + DISH_NAME_DESC_DONUT
                + INVALID_PRICE_DESC_TOO_HIGH, PriceDish.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid price
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + PRICE_DESC_DONUT, NameDish.MESSAGE_CONSTRAINTS);

        // valid name followed by invalid name. The test case for invalid name followed by valid name
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + DISH_NAME_DESC_DONUT + INVALID_NAME_DESC, NameDish.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PRICE_DESC_DONUT + DISH_NAME_DESC_DONUT;

        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_DONUT)
                        .withPrice(VALID_DISH_PRICE_DONUT).build();
        EditDishCommand expectedCommand = new EditDishCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + DISH_NAME_DESC_DONUT;
        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder().withName(VALID_DISH_NAME_DONUT).build();
        EditDishCommand expectedCommand = new EditDishCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PRICE_DESC_DONUT;
        descriptor = new EditDishDescriptorBuilder().withPrice(VALID_DISH_PRICE_DONUT).build();
        expectedCommand = new EditDishCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput =
                targetIndex.getOneBased() + PRICE_DESC_DONUT + PRICE_DESC_FRIES
                        + DISH_NAME_DESC_FRIES + DISH_NAME_DESC_DONUT;

        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder().withPrice(VALID_DISH_PRICE_FRIES)
                        .withName(VALID_DISH_NAME_DONUT)
                        .build();
        EditDishCommand expectedCommand = new EditDishCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PRICE_DESC_NO_DP + PRICE_DESC_FRIES;
        EditDishCommand.EditDishDescriptor descriptor =
                new EditDishDescriptorBuilder().withPrice(VALID_DISH_PRICE_FRIES).build();
        EditDishCommand expectedCommand = new EditDishCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PRICE_DESC_NO_DP + DISH_NAME_DESC_DONUT
                + PRICE_DESC_DONUT;
        descriptor = new EditDishDescriptorBuilder().withPrice(VALID_DISH_PRICE_DONUT)
                .withName(VALID_DISH_NAME_DONUT).build();
        expectedCommand = new EditDishCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

