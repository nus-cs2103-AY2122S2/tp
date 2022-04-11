package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;

import seedu.address.logic.commands.NewTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tasks.Deadline;
import seedu.address.model.tasks.Description;
import seedu.address.model.tasks.Task;

/**
 * Parses input arguments and creates a new NewTaskCommand object
 */
public class NewTaskCommandParser implements Parser<NewTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the NewTaskCommand
     * and returns an NewTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public NewTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DEADLINE);
        if (!argMultimap.getValue(PREFIX_DEADLINE).isPresent() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NewTaskCommand.MESSAGE_USAGE));
        }

        Description desc = ParserUtil.parseDesc(argMultimap.getPreamble());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        Task task = new Task(desc, deadline);

        return new NewTaskCommand(task);
    }
}
