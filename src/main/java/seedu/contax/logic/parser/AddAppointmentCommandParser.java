package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.AddAppointmentCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.Appointment;
import seedu.contax.model.appointment.Duration;
import seedu.contax.model.appointment.Name;
import seedu.contax.model.appointment.StartDateTime;

/**
 * Parses input arguments and creates a new AddAppointmentCommand object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAppointmentCommand
     * and returns an AddAppointmentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_DURATION,
                        PREFIX_PERSON);

        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_DATE, PREFIX_TIME, PREFIX_DURATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseAppointmentName(argMultimap.getValue(PREFIX_NAME).get());
        StartDateTime startDateTime = ParserUtil.parseStartDateTime(argMultimap.getValue(PREFIX_DATE).get(),
                argMultimap.getValue(PREFIX_TIME).get());
        Duration duration = ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get());

        Index personIndex = null;
        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PERSON).get());
        }

        return new AddAppointmentCommand(new Appointment(name, startDateTime, duration, null),
                personIndex);
    }
}
