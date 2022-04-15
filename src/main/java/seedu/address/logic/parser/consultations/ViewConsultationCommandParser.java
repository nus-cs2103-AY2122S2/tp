package seedu.address.logic.parser.consultations;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewedNric;
import seedu.address.logic.commands.consultation.ViewConsultationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;

public class ViewConsultationCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewConsultationCommand
     * and returns an ViewConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewConsultationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ViewConsultationCommand.MESSAGE_USAGE));
        }

        Nric ownerNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        ViewedNric.setViewedNric(ownerNric);

        return new ViewConsultationCommand(ownerNric);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
