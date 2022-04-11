package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.COST_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.INFO_DESC_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COST_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INFO_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COST_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_REPLACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPAIR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INFO_REPLACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROCEDURE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditProcCommand;
import seedu.address.logic.commands.EditProcCommand.EditProcedureDescriptor;
import seedu.address.model.procedure.Cost;
import seedu.address.model.procedure.Date;
import seedu.address.model.procedure.Information;
import seedu.address.testutil.EditProcedureDescriptorBuilder;

public class EditProcCommandParserTest {
    private static final String PREAMBLE = "%1$s %2$s";
    private static final String VALID_PREAMBLE = String.format(PREAMBLE, INDEX_FIRST_CLIENT.getOneBased(),
            INDEX_FIRST_PROCEDURE.getOneBased());

    private EditProcCommandParser parser = new EditProcCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        EditProcedureDescriptor editProcedureDescriptor = new EditProcedureDescriptorBuilder()
            .withInfo(VALID_INFO_REPAIR).withCost(VALID_COST_REPAIR).withDate(VALID_DATE_REPAIR).build();

        Index targetClient = INDEX_FIRST_CLIENT;
        Index targetIndex = INDEX_FIRST_PROCEDURE;

        String userInput = targetClient.getOneBased() + " " + targetIndex.getOneBased()
                + INFO_DESC_REPAIR + COST_DESC_REPAIR + DATE_DESC_REPAIR;
        assertParseSuccess(parser, userInput,
                new EditProcCommand(targetClient, targetIndex, editProcedureDescriptor));
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = String.format(PREAMBLE, INDEX_FIRST_CLIENT.getOneBased(),
            INDEX_FIRST_PROCEDURE.getOneBased()) + INFO_DESC_REPLACE + COST_DESC_REPLACE;
        EditProcedureDescriptor descriptor = new EditProcedureDescriptorBuilder().withInfo(VALID_INFO_REPLACE)
            .withCost(VALID_COST_REPLACE).build();
        EditProcCommand expectedCommand = new EditProcCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_PROCEDURE, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // information
        Index targetIndex = INDEX_FIRST_CLIENT;
        Index targetProcedure = INDEX_FIRST_CLIENT;
        String userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
                + INFO_DESC_REPLACE;
        EditProcedureDescriptor descriptor = new EditProcedureDescriptorBuilder().withInfo(VALID_INFO_REPLACE).build();
        EditProcCommand expectedCommand = new EditProcCommand(targetIndex, targetProcedure, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // cost
        userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
                + COST_DESC_REPLACE;
        descriptor = new EditProcedureDescriptorBuilder().withCost(VALID_COST_REPLACE).build();
        expectedCommand = new EditProcCommand(targetIndex, targetProcedure, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
                + DATE_DESC_REPLACE;
        descriptor = new EditProcedureDescriptorBuilder().withDate(VALID_DATE_REPLACE).build();
        expectedCommand = new EditProcCommand(targetIndex, targetProcedure, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditProcCommand.MESSAGE_USAGE);

        // Negative client index
        assertParseFailure(parser, String.format(PREAMBLE, -5, 1) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // Zero-ed client index
        assertParseFailure(parser, String.format(PREAMBLE, 0, 1) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // Invalid arguments being parsed in client index as preamble
        assertParseFailure(parser, String.format(PREAMBLE, "1 some Strings", 1) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // invalid prefix being parsed as preamble for client index
        assertParseFailure(parser, String.format(PREAMBLE, "1 n/notification", 1) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // Negative procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, -1) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // Zero-ed procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, 0) + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // Invalid arguments being parsed in procedure index as preamble
        assertParseFailure(parser, String.format(PREAMBLE, 1, "1 some Strings") + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);

        // invalid prefix being parsed as preamble for procedure index
        assertParseFailure(parser, String.format(PREAMBLE, 1, "1 n/notification") + INFO_DESC_REPAIR
                + DATE_DESC_REPAIR + COST_DESC_REPAIR, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        Index targetProcedure = INDEX_FIRST_PROCEDURE;
        String userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
            + INFO_DESC_REPAIR + COST_DESC_REPAIR + INFO_DESC_REPLACE + COST_DESC_REPLACE
            + DATE_DESC_REPLACE;

        assertParseFailure(parser, VALID_PREAMBLE + INVALID_INFO_DESC, Information.MESSAGE_CONSTRAINTS); // invalid info
        assertParseFailure(parser, VALID_PREAMBLE + INVALID_COST_DESC, Cost.MESSAGE_CONSTRAINTS); // invalid cost
        assertParseFailure(parser, VALID_PREAMBLE + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date

        // invalid information followed by valid cost
        assertParseFailure(parser, VALID_PREAMBLE + INVALID_INFO_DESC + INVALID_COST_DESC,
                Information.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_INFO_DESC + INVALID_COST_DESC + INVALID_DATE_DESC,
            Information.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        Index targetProcedure = INDEX_FIRST_PROCEDURE;
        String userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
            + INFO_DESC_REPAIR + COST_DESC_REPAIR + INFO_DESC_REPLACE + COST_DESC_REPLACE
            + DATE_DESC_REPLACE;

        EditProcedureDescriptor descriptor = new EditProcedureDescriptorBuilder().withInfo(VALID_INFO_REPLACE)
            .withCost(VALID_COST_REPLACE).withDate(VALID_DATE_REPLACE).build();
        EditProcCommand expectedCommand = new EditProcCommand(targetIndex, targetProcedure, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        Index targetProcedure = INDEX_FIRST_PROCEDURE;
        String userInput = String.format(PREAMBLE, targetIndex.getOneBased(), targetProcedure.getOneBased())
            + INVALID_INFO_DESC + COST_DESC_REPAIR + INFO_DESC_REPLACE + COST_DESC_REPLACE
            + DATE_DESC_REPLACE;

        EditProcedureDescriptor descriptor = new EditProcedureDescriptorBuilder().withInfo(VALID_INFO_REPLACE)
            .withCost(VALID_COST_REPLACE).withDate(VALID_DATE_REPLACE).build();
        EditProcCommand expectedCommand = new EditProcCommand(targetIndex, targetProcedure, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
