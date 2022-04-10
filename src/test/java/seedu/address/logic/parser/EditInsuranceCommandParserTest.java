package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_INSURANCE2;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_INSURANCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_INSURANCE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_INSURANCE2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInsuranceCommand;
import seedu.address.logic.commands.EditInsuranceCommand.EditInsuranceDescriptor;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;
import seedu.address.testutil.EditInsuranceDescriptorBuilder;

public class EditInsuranceCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInsuranceCommand.MESSAGE_USAGE);

    private EditInsuranceCommandParser parser = new EditInsuranceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_INSURANCE1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditInsuranceCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_TITLE_INSURANCE1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_TITLE_INSURANCE1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PRICE_INSURANCE,
                Price.MESSAGE_CONSTRAINTS); // invalid datetime
        assertParseFailure(parser, "1" + INVALID_TITLE_INSURANCE,
                Title.MESSAGE_CONSTRAINTS); // invalid description
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_OBJECT;
        String userInput = targetIndex.getOneBased() + TITLE_INSURANCE1 + PRICE_INSURANCE1;

        EditInsuranceDescriptor descriptor =
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_INSURANCE1)
                        .withPrice(VALID_PRICE_INSURANCE1).build();
        EditInsuranceCommand expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldsSpecified_success() {
        // Title
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + TITLE_INSURANCE1;

        EditInsuranceDescriptor descriptor =
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_INSURANCE1).build();
        EditInsuranceCommand expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // Price
        userInput = targetIndex.getOneBased() + PRICE_INSURANCE1;

        descriptor = new EditInsuranceDescriptorBuilder().withPrice(VALID_PRICE_INSURANCE1).build();
        expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + TITLE_INSURANCE1 + TITLE_INSURANCE2 + PRICE_INSURANCE1
                + PRICE_INSURANCE2;

        EditInsuranceDescriptor descriptor =
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_INSURANCE1)
                        .withTitle(VALID_TITLE_INSURANCE2).withPrice(VALID_PRICE_INSURANCE1)
                        .withPrice(VALID_PRICE_INSURANCE2).build();
        EditInsuranceCommand expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_OBJECT;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_INSURANCE + TITLE_INSURANCE1;
        EditInsuranceDescriptor descriptor =
                new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_INSURANCE1).build();
        EditInsuranceCommand expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_TITLE_INSURANCE + TITLE_INSURANCE1 + PRICE_INSURANCE1;
        descriptor = new EditInsuranceDescriptorBuilder().withTitle(VALID_TITLE_INSURANCE1)
                .withPrice(VALID_PRICE_INSURANCE1).build();
        expectedCommand = new EditInsuranceCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
