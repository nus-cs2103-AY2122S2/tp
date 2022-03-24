package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import java.util.Arrays;

import seedu.contax.logic.commands.FindAppointmentCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.NameContainsKeywordsPredicate;
import seedu.contax.model.appointment.PersonNameContainsKeywordsPredicate;




/**
 * Parses input arguments and creates a new FindAppointmentCommand  object
 */
public class FindAppointmentCommandParser implements Parser<FindAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindAppointmentCommand
     * and returns a FindAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
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
                return new FindAppointmentCommand(new
                        PersonNameContainsKeywordsPredicate(Arrays.asList(outputKeywords)));
            default:
                throw new ParseException("Search type should only be person");
            }
        }
    }
}
