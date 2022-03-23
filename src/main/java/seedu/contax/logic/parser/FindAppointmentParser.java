package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.contax.logic.commands.FindAppointmentCommand;
import seedu.contax.logic.commands.FindPersonCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.ClientNameContainsKeywordsPredicate;
import seedu.contax.model.appointment.HasClientPredicate;
import seedu.contax.model.appointment.NameContainsKeywordsPredicate;
import seedu.contax.model.person.AddressContainsKeywordsPredicate;
import seedu.contax.model.person.EmailContainsKeywordsPredicate;
import seedu.contax.model.person.Name;
import seedu.contax.model.person.PhoneContainsKeywordsPredicate;
import seedu.contax.model.util.SearchType;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new FindAppointment object
 */
public class FindAppointmentParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointment
     * and returns a FindAppointment object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if(trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_SEARCH_TYPE);

        String[] outputKeywords = argMultimap.getPreamble().trim().split("\\s+");

        if (argMultimap.getValue(PREFIX_SEARCH_TYPE).isEmpty()) {
            return new FindAppointmentCommand(new NameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
        } else {
            switch (argMultimap.getValue(PREFIX_SEARCH_TYPE).get()) {
                case "person":
                    return new FindAppointmentCommand(new ClientNameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
                default:
                    throw new ParseException("Search type should only be person");
            }
        }
    }
}
