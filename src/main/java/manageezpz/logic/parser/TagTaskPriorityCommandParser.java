package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;

import java.util.stream.Stream;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.TagTaskPriorityCommand;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.task.Priority;

public class TagTaskPriorityCommandParser implements Parser<TagTaskPriorityCommand> {

    public static final String MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS =
            "Tag a priority to a task by specifying prefix priority/ "
                    + "followed by the priority values (NONE/LOW/MEDIUM/HIGH)!\n\n%1$s";

    public static final String MESSAGE_EMPTY_PRIORITY = "Priority field cannot be empty! "
            + "Valid priority values are NONE/LOW/MEDIUM/HIGH.\n\n%1$s";

    public static final String MESSAGE_INVALID_PRIORITY =
            "Invalid priority! " + "Valid priority values are NONE/LOW/MEDIUM/HIGH. \n\n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the TagTaskPriorityCommand
     * and returns a TagTaskPriorityCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagTaskPriorityCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapTagPriority = ArgumentTokenizer.tokenize(args, PREFIX_PRIORITY);

        // Invalid command if getPreamble() is empty
        if (argMultimapTagPriority.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }

        // Invalid command if getPreamble() contains whitespaces
        if (argMultimapTagPriority.getPreamble().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT + " "
                    + MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS, TagTaskPriorityCommand.MESSAGE_USAGE));
        }

        Index index;
        Priority priority;

        try {
            index = ParserUtil.parseIndex(argMultimapTagPriority.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + TagTaskPriorityCommand.MESSAGE_USAGE, pe);
        }

        if (!arePrefixesPresent(argMultimapTagPriority, PREFIX_PRIORITY)) {
            throw new ParseException(String.format(MESSAGE_TAG_PRIORITY_TO_TASK_INSTRUCTIONS,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }

        String priorityString = argMultimapTagPriority.getValue(PREFIX_PRIORITY).get();

        if (priorityString.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_PRIORITY,
                    TagTaskPriorityCommand.MESSAGE_USAGE));
        }

        try {
            priority = Priority.valueOf(priorityString.toUpperCase());
        } catch (IllegalArgumentException ie) {
            throw new ParseException(String.format(MESSAGE_INVALID_PRIORITY,
                    TagTaskPriorityCommand.MESSAGE_USAGE), ie);
        }

        return new TagTaskPriorityCommand(index, priority);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
