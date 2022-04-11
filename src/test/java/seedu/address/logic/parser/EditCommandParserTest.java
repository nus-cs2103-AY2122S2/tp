package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.EXPENSE_CATEGORY_DESC_ENTERTAINMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANNUAL_SPOTIFY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUILD_A_BEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EXPENSE_CATEGORY_ENTERTAINMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.expense.Amount;
import seedu.address.testutil.EditExpenseDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_DESCRIPTION_ANNUAL_SPOTIFY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + DESCRIPTION_DESC_ANNUAL_SPOTIFY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + DESCRIPTION_DESC_ANNUAL_SPOTIFY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid amount
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_ANNUAL_SPOTIFY
                + EXPENSE_CATEGORY_DESC_ENTERTAINMENT + AMOUNT_DESC_ANNUAL_SPOTIFY;
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_ANNUAL_SPOTIFY)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_ANNUAL_SPOTIFY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUILD_A_BEAR;

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {

        // description
        Index targetIndex = INDEX_THIRD_EXPENSE;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_ANNUAL_SPOTIFY;
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_ANNUAL_SPOTIFY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // expense category
        userInput = targetIndex.getOneBased() + EXPENSE_CATEGORY_DESC_ENTERTAINMENT;
        descriptor = new EditExpenseDescriptorBuilder()
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


        // amount
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_ANNUAL_SPOTIFY;
        descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_ANNUAL_SPOTIFY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_BUILD_A_BEAR + AMOUNT_DESC_ANNUAL_SPOTIFY
                + EXPENSE_CATEGORY_DESC_ENTERTAINMENT
                + AMOUNT_DESC_BUILD_A_BEAR + DESCRIPTION_DESC_ANNUAL_SPOTIFY + DESCRIPTION_DESC_BUILD_A_BEAR;

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_BUILD_A_BEAR)
                .withExpenseCategory(VALID_EXPENSE_CATEGORY_ENTERTAINMENT)
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + INVALID_AMOUNT_DESC + AMOUNT_DESC_BUILD_A_BEAR;
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withAmount(VALID_AMOUNT_BUILD_A_BEAR).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
