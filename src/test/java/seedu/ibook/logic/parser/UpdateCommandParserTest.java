package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.CATEGORY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.DESCRIPTION_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_PRICE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.NAME_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.PRICE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_CATEGORY_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_PRICE_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_THIRD_PRODUCT;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.index.Index;
import seedu.ibook.logic.commands.UpdateCommand;
import seedu.ibook.logic.commands.UpdateCommand.UpdateProductDescriptor;
import seedu.ibook.model.product.Category;
import seedu.ibook.model.product.Description;
import seedu.ibook.model.product.Name;
import seedu.ibook.model.product.Price;
import seedu.ibook.testutil.UpdateProductDescriptorBuilder;

public class UpdateCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE);

    private UpdateCommandParser parser = new UpdateCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NAME_FULL_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateCommand.MESSAGE_NOT_UPDATED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_FULL_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_FULL_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i:string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS);
        // invalid category
        assertParseFailure(parser, "1" + INVALID_CATEGORY_DESC, Category.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        // invalid price
        assertParseFailure(parser, "1" + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // invalid name followed by valid category
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + CATEGORY_FULL_A, Name.MESSAGE_CONSTRAINTS);

        // valid price followed by invalid price. The test case for invalid price followed by valid price
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PRICE_FULL_A + INVALID_PRICE_DESC, Price.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EXPIRY_DATE_DESC + VALID_PRICE_A,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PRODUCT;
        String userInput = targetIndex.getOneBased() + NAME_FULL_A + CATEGORY_FULL_A
                + DESCRIPTION_FULL_A + PRICE_FULL_A;

        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_A)
                .withCategory(VALID_CATEGORY_A)
                .withDescription(VALID_DESCRIPTION_A).withPrice(VALID_PRICE_A).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PRODUCT;
        String userInput = targetIndex.getOneBased() + NAME_FULL_A + DESCRIPTION_FULL_A;

        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_A)
                .withDescription(VALID_DESCRIPTION_A).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PRODUCT;
        String userInput = targetIndex.getOneBased() + NAME_FULL_A;
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_A).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + CATEGORY_FULL_A;
        descriptor = new UpdateProductDescriptorBuilder().withCategory(VALID_CATEGORY_A).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_FULL_A;
        descriptor = new UpdateProductDescriptorBuilder().withDescription(VALID_DESCRIPTION_A).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // price
        userInput = targetIndex.getOneBased() + PRICE_FULL_A;
        descriptor = new UpdateProductDescriptorBuilder().withPrice(VALID_PRICE_A).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PRODUCT;
        String userInput = targetIndex.getOneBased() + NAME_FULL_A + CATEGORY_FULL_A
                + DESCRIPTION_FULL_A + PRICE_FULL_A + NAME_FULL_B + CATEGORY_FULL_B
                + DESCRIPTION_FULL_B + PRICE_FULL_B;

        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_B)
                .withCategory(VALID_CATEGORY_B)
                .withDescription(VALID_DESCRIPTION_B).withPrice(VALID_PRICE_B).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_THIRD_PRODUCT;
        String userInput = targetIndex.getOneBased() + INVALID_PRICE_DESC + PRICE_FULL_B;
        UpdateProductDescriptor descriptor = new UpdateProductDescriptorBuilder().withPrice(VALID_PRICE_B).build();
        UpdateCommand expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_FULL_B + INVALID_PRICE_DESC + DESCRIPTION_FULL_B + PRICE_FULL_B;
        descriptor = new UpdateProductDescriptorBuilder().withName(VALID_NAME_B).withPrice(VALID_PRICE_B)
                .withDescription(VALID_DESCRIPTION_B).build();
        expectedCommand = new UpdateCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
