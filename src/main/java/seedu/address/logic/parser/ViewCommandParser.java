package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.stream.Stream;

import seedu.address.logic.commands.CommandManager;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;

public class ViewCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewCommand
     * and returns an ViewMedicalCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC);

        Nric nric = null;

        if (arePrefixesPresent(argMultimap, PREFIX_NRIC)) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
            CommandManager.setCurrentViewType(CommandType.SUMMARY);
        }

        return new ViewCommand(nric);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
