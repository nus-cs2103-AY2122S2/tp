package manageezpz.logic.parser;

import manageezpz.logic.commands.AddEventTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;

import java.util.stream.Stream;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;

public class AddEventTaskCommandParser {
    public AddEventTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapTodo =
                ArgumentTokenizer.tokenize(args, PREFIX_EVENT, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimapTodo, PREFIX_EVENT, PREFIX_DESCRIPTION)
                || !argMultimapTodo.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventTaskCommand.MESSAGE_USAGE));
        }

        return new AddEventTaskCommand(new Event(new Description("dummy")));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}