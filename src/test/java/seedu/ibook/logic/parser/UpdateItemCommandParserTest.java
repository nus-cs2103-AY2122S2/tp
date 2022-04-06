package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ibook.logic.commands.CommandTestUtil.EXPIRY_DATE_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.EXPIRY_DATE_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_EXPIRY_DATE_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.INVALID_QUANTITY_DESC;
import static seedu.ibook.logic.commands.CommandTestUtil.QUANTITY_FULL_A;
import static seedu.ibook.logic.commands.CommandTestUtil.QUANTITY_FULL_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_A;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_B;
import static seedu.ibook.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT_FIRST_ITEM;

import org.junit.jupiter.api.Test;

import seedu.ibook.commons.core.index.CompoundIndex;
import seedu.ibook.logic.commands.item.UpdateItemCommand;
import seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import seedu.ibook.model.item.ExpiryDate;
import seedu.ibook.model.item.Quantity;
import seedu.ibook.testutil.UpdateItemDescriptorBuilder;

public class UpdateItemCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateItemCommand.MESSAGE_USAGE);

    private UpdateItemCommandParser parser = new UpdateItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, QUANTITY_FULL_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1-1", UpdateItemCommand.MESSAGE_NOT_UPDATED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + QUANTITY_FULL_B, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-5-1" + QUANTITY_FULL_B, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + QUANTITY_FULL_B, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0-1" + QUANTITY_FULL_B, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1-0" + QUANTITY_FULL_B, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1-1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1-1 i:string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid expiry date
        assertParseFailure(parser, "1-1" + INVALID_EXPIRY_DATE_DESC, ExpiryDate.MESSAGE_CONSTRAINTS);

        // valid quantity followed by invalid quantity. The test case for invalid quantity followed by valid quantity
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1-1" + QUANTITY_FULL_A + INVALID_QUANTITY_DESC, Quantity.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        CompoundIndex targetIndex = INDEX_SECOND_PRODUCT_FIRST_ITEM;
        String userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + EXPIRY_DATE_FULL_B + QUANTITY_FULL_B;

        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder()
                .withExpiryDate(VALID_EXPIRY_DATE_B)
                .withQuantity(VALID_QUANTITY_B)
                .build();
        UpdateItemCommand expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // expiry date
        CompoundIndex targetIndex = INDEX_SECOND_PRODUCT_FIRST_ITEM;
        String userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + EXPIRY_DATE_FULL_B;

        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder()
                .withExpiryDate(VALID_EXPIRY_DATE_B)
                .build();
        UpdateItemCommand expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // quantity
        targetIndex = INDEX_SECOND_PRODUCT_FIRST_ITEM;
        userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + QUANTITY_FULL_B;

        descriptor = new UpdateItemDescriptorBuilder()
                .withQuantity(VALID_QUANTITY_B)
                .build();
        expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        CompoundIndex targetIndex = INDEX_SECOND_PRODUCT_FIRST_ITEM;
        String userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + EXPIRY_DATE_FULL_A + QUANTITY_FULL_A
                + EXPIRY_DATE_FULL_B + QUANTITY_FULL_B;

        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder()
                .withExpiryDate(VALID_EXPIRY_DATE_B)
                .withQuantity(VALID_QUANTITY_B)
                .build();
        UpdateItemCommand expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        CompoundIndex targetIndex = INDEX_SECOND_PRODUCT_FIRST_ITEM;
        String userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + INVALID_EXPIRY_DATE_DESC + EXPIRY_DATE_FULL_A;

        UpdateItemDescriptor descriptor = new UpdateItemDescriptorBuilder()
                .withExpiryDate(VALID_EXPIRY_DATE_A)
                .build();
        UpdateItemCommand expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBasedFirst() + "-" + targetIndex.getOneBasedSecond()
                + INVALID_QUANTITY_DESC + EXPIRY_DATE_FULL_A + QUANTITY_FULL_B;

        descriptor = new UpdateItemDescriptorBuilder()
                .withExpiryDate(VALID_EXPIRY_DATE_A)
                .withQuantity(VALID_QUANTITY_B)
                .build();
        expectedCommand = new UpdateItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
