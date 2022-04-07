package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NO_CHARGE_SET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARGE_MONTH_YEAR;

import java.time.YearMonth;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ChargeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.charge.Charge;


public class ChargeCommandParser implements Parser<ChargeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ChargeCommand
     * and returns an ChargeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChargeCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CHARGE_MONTH_YEAR, PREFIX_CHARGE);
        boolean isMonthYearMissing = argMultimap.getValue(PREFIX_CHARGE_MONTH_YEAR).isEmpty();
        boolean isChargeMissing = argMultimap.getValue(PREFIX_CHARGE).isEmpty();
        if (isMonthYearMissing && isChargeMissing) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChargeCommand.MESSAGE_USAGE));
        } else if (isMonthYearMissing) {
            throw new ParseException(Messages.MESSAGE_NO_CHARGE_MONTH_SET);
        } else if (isChargeMissing) {
            throw new ParseException(MESSAGE_NO_CHARGE_SET);
        }

        YearMonth chargeDate;
        try {
            chargeDate = ParserUtil.parseChargeDate(argMultimap.getValue(PREFIX_CHARGE_MONTH_YEAR).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChargeCommand.MESSAGE_INVALID_DATE_FORMAT), e);
        }

        Charge charge;
        try {
            charge = ParserUtil.parseCharge(argMultimap.getValue(PREFIX_CHARGE).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    Charge.MESSAGE_INVALID_CHARGE_FORMAT), e);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
        }

        return new ChargeCommand(index, chargeDate, charge);
    }
}

