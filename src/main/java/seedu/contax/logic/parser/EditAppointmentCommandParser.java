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

import seedu.contax.commons.core.Messages;
import seedu.contax.commons.core.index.Index;
import seedu.contax.commons.util.DateUtil;
import seedu.contax.logic.commands.EditAppointmentCommand;
import seedu.contax.logic.commands.EditAppointmentCommand.EditAppointmentDescriptor;
import seedu.contax.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditAppointmentCommand object
 */
public class EditAppointmentCommandParser implements Parser<EditAppointmentCommand> {

    public static final String KEYWORD_REMOVE_PERSON = "none";

    /**
     * Parses the given {@code String} of arguments in the context of the EditAppointmentCommand
     * and returns an EditAppointmentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAppointmentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE,
                PREFIX_TIME, PREFIX_DURATION, PREFIX_PERSON);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAppointmentCommand.MESSAGE_USAGE), pe);
        }

        EditAppointmentDescriptor editAppointmentDescriptor = new EditAppointmentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editAppointmentDescriptor.setName(
                    ParserUtil.parseAppointmentName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            LocalDate dateObject = DateUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get())
                    .orElseThrow(() -> new ParseException(Messages.MESSAGE_INVALID_DATE));
            editAppointmentDescriptor.setStartDate(dateObject);
        }
        if (argMultimap.getValue(PREFIX_TIME).isPresent()) {
            LocalTime timeObject = DateUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get())
                    .orElseThrow(() -> new ParseException(Messages.MESSAGE_INVALID_TIME));
            editAppointmentDescriptor.setStartTime(timeObject);
        }
        if (argMultimap.getValue(PREFIX_DURATION).isPresent()) {
            editAppointmentDescriptor.setDuration(
                    ParserUtil.parseDuration(argMultimap.getValue(PREFIX_DURATION).get()));
        }
        if (argMultimap.getValue(PREFIX_PERSON).isPresent()) {
            String newPersonValue = argMultimap.getValue(PREFIX_PERSON).get();
            editAppointmentDescriptor.setPersonIndex(newPersonValue.equalsIgnoreCase(KEYWORD_REMOVE_PERSON)
                    ? null : ParserUtil.parseIndex(newPersonValue));
        }

        if (!editAppointmentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditAppointmentCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAppointmentCommand(index, editAppointmentDescriptor);
    }
}
