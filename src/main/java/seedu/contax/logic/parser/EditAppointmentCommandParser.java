package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PERSON;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object.
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    public static final String KEYWORD_REMOVE_PERSON = "none";

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE,
                PREFIX_TIME, PREFIX_DURATION, PREFIX_PERSON);

        Index appointmentIndex;
        try {
            appointmentIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        parseName(argMultimap.getValue(PREFIX_NAME), editAppointmentDescriptor);
        parseDate(argMultimap.getValue(PREFIX_DATE), editAppointmentDescriptor);
        parseTime(argMultimap.getValue(PREFIX_TIME), editAppointmentDescriptor);
        parseDuration(argMultimap.getValue(PREFIX_DURATION), editAppointmentDescriptor);
        parsePerson(argMultimap.getValue(PREFIX_PERSON), editAppointmentDescriptor);

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(appointmentIndex, editAppointmentDescriptor);
    }

    /**
     * Parses the input appointment name and registers the parsed object with the {@code editDescriptor}.
     */
    private void parseName(Optional<String> nameString, EditAppointmentDescriptor editDescriptor)
            throws ParseException {
        if (nameString.isPresent()) {
            editDescriptor.setName(ParserUtil.parseAppointmentName(nameString.get()));
        }
    }

    /**
     * Parses the input date string and registers the parsed object with the {@code editDescriptor}.
     */
    private void parseDate(Optional<String> dateString, EditAppointmentDescriptor editDescriptor)
            throws ParseException {
        if (dateString.isPresent()) {
            LocalDate dateObject = DateUtil.parseDate(dateString.get())
                    .orElseThrow(() -> new ParseException(Messages.MESSAGE_INVALID_DATE));
            editDescriptor.setStartDate(dateObject);
        }
    }

    /**
     * Parses the input time string and registers the parsed object with the {@code editDescriptor}.
     */
    private void parseTime(Optional<String> timeString, EditAppointmentDescriptor editDescriptor)
            throws ParseException {
        if (timeString.isPresent()) {
            LocalTime timeObject = DateUtil.parseTime(timeString.get())
                    .orElseThrow(() -> new ParseException(Messages.MESSAGE_INVALID_TIME));
            editDescriptor.setStartTime(timeObject);
        }
    }

    /**
     * Parses the input duration string and registers the parsed object with the {@code editDescriptor}.
     */
    private void parseDuration(Optional<String> durationString, EditAppointmentDescriptor editDescriptor)
            throws ParseException {
        if (durationString.isPresent()) {
            editDescriptor.setDuration(ParserUtil.parseDuration(durationString.get()));
        }
    }

    /**
     * Parses the input person index string and registers the parsed object with the {@code editDescriptor}.
     */
    private void parsePerson(Optional<String> personIndexString, EditAppointmentDescriptor editDescriptor)
            throws ParseException {
        if (personIndexString.isPresent()) {
            String newPersonIndex = personIndexString.get();
            editDescriptor.setPersonIndex(newPersonIndex.equalsIgnoreCase(KEYWORD_REMOVE_PERSON)
                    ? null : ParserUtil.parseIndex(newPersonIndex));
        }
    }
}
