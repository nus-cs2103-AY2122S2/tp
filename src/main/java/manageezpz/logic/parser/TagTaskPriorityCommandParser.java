package manageezpz.logic.parser;

import manageezpz.logic.commands.TagTaskPriorityCommand;
import manageezpz.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static manageezpz.commons.core.Messages.MESSAGE_EMPTY_PRIORITY;
import static manageezpz.commons.core.Messages.MESSAGE_EMPTY_TASK_NUMBER;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;

public class TagTaskPriorityCommandParser implements Parser<TagTaskPriorityCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddDeadlineTaskCommand
     * and returns an AddDeadlineTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagTaskPriorityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapPriority =
                ArgumentTokenizer.tokenize(args, PREFIX_PRIORITY);
        if (!arePrefixesPresent(argMultimapPriority, PREFIX_PRIORITY)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }
        if (argMultimapPriority.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_TASK_NUMBER,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }
        String enumString = argMultimapPriority.getValue(PREFIX_PRIORITY).get();
        if (enumString.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PRIORITY,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }
        String[] argsArr = args.trim().split(" ");
        int index = ParserUtil.parseIndex(argsArr[0]).getZeroBased();
        return new TagTaskPriorityCommand(index, enumString);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
