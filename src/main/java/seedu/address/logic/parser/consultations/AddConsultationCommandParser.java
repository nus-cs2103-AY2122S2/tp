package seedu.address.logic.parser.consultations;

import seedu.address.logic.commands.consultation.AddConsultationCommand;
import seedu.address.logic.parser.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.consultation.*;
import seedu.address.model.person.Nric;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class AddConsultationCommandParser implements Parser<AddConsultationCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddContactCommand
     * and returns an AddContactCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddConsultationCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_NRIC, PREFIX_DATE, PREFIX_TIME, PREFIX_NOTES,
                        PREFIX_PRESCRIPTION, PREFIX_TESTS_TAKEN_AND_RESULTS);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_NRIC, PREFIX_DATE, PREFIX_TIME, PREFIX_NOTES,
                PREFIX_PRESCRIPTION, PREFIX_TESTS_TAKEN_AND_RESULTS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddConsultationCommand.MESSAGE_USAGE));
        }

        Nric ownerNric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        Notes notes = ParserUtil.parseNotes(argMultimap.getValue(PREFIX_NOTES).get());
        Prescription prescription = ParserUtil.parsePrescription(argMultimap.getValue(PREFIX_PRESCRIPTION).get());
        TestsTakenAndResults testsTakenAndResults = ParserUtil.parseTestsTakenAndResults(argMultimap.getValue(PREFIX_TESTS_TAKEN_AND_RESULTS).get());

        Consultation consultation = new Consultation(ownerNric, date, time, notes, prescription, testsTakenAndResults);

        return new AddConsultationCommand(ownerNric, consultation);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
