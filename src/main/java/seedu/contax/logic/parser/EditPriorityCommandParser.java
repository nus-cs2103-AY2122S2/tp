package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_PRIORITY_LEVEL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.Locale;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditPriorityCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.Priority;

/**
 * Parses input arguments and creates a new EditPriorityCommand object.
 */
public class EditPriorityCommandParser implements Parser<EditPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of
     * EditPriorityCommand and returns an EditPriorityCommand
     * object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPriorityCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PRIORITY);

        if (argumentMultimap.getValue(PREFIX_PRIORITY).isEmpty() || argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPriorityCommand.MESSAGE_USAGE));
        }

        Index index;
        Priority priority;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }
        switch (argumentMultimap.getValue(PREFIX_PRIORITY).get().toLowerCase(Locale.ROOT)) {
        case "high":
            priority = Priority.HIGH;
            break;
        case "medium":
            priority = Priority.MEDIUM;
            break;
        case "low":
            priority = Priority.LOW;
            break;
        default:
            throw new ParseException(
                    String.format(MESSAGE_INVALID_PRIORITY_LEVEL, EditPriorityCommand.MESSAGE_USAGE));
        }

        return new EditPriorityCommand(index, priority);
    }
}
