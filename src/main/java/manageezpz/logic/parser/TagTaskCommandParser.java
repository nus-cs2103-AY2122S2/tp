package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_EMPTY_NAME;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.TagTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

public class TagTaskCommandParser implements Parser<TagTaskCommand> {

    public static final String MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS =
            "Tag an employee to a task by specifying prefix n/ followed by the employee's full name!\n\n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the TagTaskCommand
     * and returns an TagTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public TagTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapTagTask = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // Invalid command if getPreamble() is empty
        if (argMultimapTagTask.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    TagTaskCommand.MESSAGE_USAGE));
        }

        // Invalid command if getPreamble() contains whitespaces
        if (argMultimapTagTask.getPreamble().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT + " "
                    + MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS, TagTaskCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimapTagTask.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + TagTaskCommand.MESSAGE_USAGE, pe);
        }

        if (!arePrefixesPresent(argMultimapTagTask, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_TAG_EMPLOYEE_TO_TASK_INSTRUCTIONS,
                    TagTaskCommand.MESSAGE_USAGE));
        }

        String name = argMultimapTagTask.getValue(PREFIX_NAME).get();

        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_NAME, TagTaskCommand.MESSAGE_USAGE));
        }

        return new TagTaskCommand(index, name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
