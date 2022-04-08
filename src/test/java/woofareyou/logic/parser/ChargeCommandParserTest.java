package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_CHARGE_AMY_INVALID_ARGS;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_CHARGE_AMY_INVALID_CHARGE_AMOUNT;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_CHARGE_AMY_INVALID_CHARGE_DATE;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_CHARGE_AMY_NO_CHARGE_AMOUNT;
import static woofareyou.logic.commands.CommandTestUtil.INVALID_CHARGE_AMY_NO_CHARGE_DATE;
import static woofareyou.logic.commands.CommandTestUtil.VALID_CHARGEAMT_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_CHARGEDATE_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_CHARGE_AMY;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseFailure;
import static woofareyou.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.ChargeCommand;
import woofareyou.model.charge.Charge;


public class ChargeCommandParserTest {
    private ChargeCommandParser parser = new ChargeCommandParser();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_PET;
        String userInput = targetIndex.getOneBased() + VALID_CHARGE_AMY;
        YearMonth chargeDate = YearMonth.parse(VALID_CHARGEDATE_AMY, formatter);

        Charge charge = new Charge(VALID_CHARGEAMT_AMY);
        ChargeCommand expectedCommand = new ChargeCommand(targetIndex, chargeDate, charge);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CHARGE_AMY, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);

        // no charge date and amount specified
        assertParseFailure(parser, "1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ChargeCommand.MESSAGE_USAGE));

        // no charge date only
        assertParseFailure(parser, "1 " + INVALID_CHARGE_AMY_NO_CHARGE_DATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChargeCommand.MESSAGE_INVALID_DATE_FORMAT));

        // no charge amount only
        assertParseFailure(parser, "1 " + INVALID_CHARGE_AMY_NO_CHARGE_AMOUNT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Charge.MESSAGE_INVALID_CHARGE_FORMAT));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid charge date
        assertParseFailure(parser, "1 " + INVALID_CHARGE_AMY_INVALID_CHARGE_DATE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChargeCommand.MESSAGE_INVALID_DATE_FORMAT));

        // invalid charge
        assertParseFailure(parser, "1 " + INVALID_CHARGE_AMY_INVALID_CHARGE_AMOUNT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Charge.MESSAGE_INVALID_CHARGE_FORMAT));

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, "1 " + INVALID_CHARGE_AMY_INVALID_ARGS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ChargeCommand.MESSAGE_INVALID_DATE_FORMAT));

    }
}
