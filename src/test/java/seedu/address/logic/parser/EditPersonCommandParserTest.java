package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PERSON_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_AMOUNT_1;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_AMOUNT_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_1_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_2_AMOUNT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.model.person.PersonAmount;
import seedu.address.testutil.EditPersonDescriptorBuilder;


/**
 * This class is to test the methods and method calls in the EditPersonCommandParser file, which
 * aims to handle the parsing of the "update" command input by the user. This will test all the
 * possible irregularities with the input, and output a suitable messsage and result.
 */
public class EditPersonCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE);

    private EditPersonCommandParser parser = new EditPersonCommandParser();

    /**
     * Tests the output of the EditPersonCommandParser when the given input is invalid. The cases
     * tested here include an empty index, and empty field and both index and field empty simultaneously.
     */
    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PERSON_AMOUNT_1, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditPersonCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    /**
     * Tests the validity of the parser by providing invalid preamble.
     */
    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + PERSON_AMOUNT_1, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + PERSON_AMOUNT_1, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    /**
     * Tests the validity of the parser when an invalid person amount is input by the user.
     */
    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PERSON_AMOUNT, PersonAmount.MESSAGE_CONSTRAINTS); // invalid amount
    }

    /**
     * Tests the validity of the parser when all the fields are provided and the format of the
     * command is correct.
     */
    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EXPENSE;
        String userInput = targetIndex.getOneBased() + PERSON_AMOUNT_1;
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the validity of the parser when only some of the fields are provided.
     */
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + PERSON_AMOUNT_1;

        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests the validity of the parser when only one field is provided.
     */
    @Test
    public void parse_oneFieldSpecified_success() {

        // description
        Index targetIndex = INDEX_THIRD_EXPENSE;
        String userInput = targetIndex.getOneBased() + PERSON_AMOUNT_1;
        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_1_AMOUNT).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);


    }

    /**
     * Tests the validity of the parser when multiple values of a single field are provided.
     */
    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EXPENSE;
        String userInput = targetIndex.getOneBased() + PERSON_AMOUNT_1 + PERSON_AMOUNT_2;

        EditPersonCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPersonAmount(VALID_PERSON_2_AMOUNT).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
