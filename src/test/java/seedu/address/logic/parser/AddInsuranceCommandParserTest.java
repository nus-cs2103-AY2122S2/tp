package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRICE_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.PRICE_INSURANCE2;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_INSURANCE1;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_INSURANCE2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalInsurances.INSURANCE1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddInsuranceCommand;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.insurance.Price;
import seedu.address.model.insurance.Title;
import seedu.address.testutil.InsuranceBuilder;

public class AddInsuranceCommandParserTest {
    private AddInsuranceCommandParser parser = new AddInsuranceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Insurance expectedInsurance = new InsuranceBuilder(INSURANCE1).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_INSURANCE1 + PRICE_INSURANCE1,
                new AddInsuranceCommand(expectedInsurance));

        // multiple title - last title accepted
        assertParseSuccess(parser,
                TITLE_INSURANCE2 + TITLE_INSURANCE1 + PRICE_INSURANCE1,
                new AddInsuranceCommand(expectedInsurance));

        // multiple price - last price accepted
        assertParseSuccess(parser, TITLE_INSURANCE1 + PRICE_INSURANCE2 + PRICE_INSURANCE1,
                new AddInsuranceCommand(expectedInsurance));
    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInsuranceCommand.MESSAGE_USAGE);

        // missing title
        assertParseFailure(parser, PRICE_INSURANCE1, expectedMessage);

        // missing price
        assertParseFailure(parser, TITLE_INSURANCE1, expectedMessage);
    }
    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_INSURANCE + PRICE_INSURANCE1,
                Title.MESSAGE_CONSTRAINTS);
        // invalid price
        assertParseFailure(parser, TITLE_INSURANCE1 + INVALID_PRICE_INSURANCE,
                Price.MESSAGE_CONSTRAINTS);
    }
}
