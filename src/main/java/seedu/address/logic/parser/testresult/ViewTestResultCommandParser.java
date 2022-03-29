package seedu.address.logic.parser.testresult;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewedNric;
import seedu.address.logic.commands.testresult.ViewTestResultCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;


public class ViewTestResultCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewTestResultCommand
     * and returns an ViewTestResultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewTestResultCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewTestResultCommand.MESSAGE_USAGE));
        }

        Nric patientNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        ViewedNric.setViewedNric(patientNric);

        return new ViewTestResultCommand(patientNric);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
