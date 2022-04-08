package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_EMPTY_NAME;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.UntagTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

public class UntagTaskCommandParser implements Parser<UntagTaskCommand> {

    public static final String MESSAGE_UNTAG_EMPLOYEE_TO_TASK_INSTRUCTIONS =
            "Untag an employee from a task by specifying prefix n/ followed by the employee's full name!\n\n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the UntagTaskCommand
     * and returns an UntagTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UntagTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimapUntag = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        // Invalid command if getPreamble() is empty
        if (argMultimapUntag.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    UntagTaskCommand.MESSAGE_USAGE));
        }

        // Invalid command if getPreamble() contains whitespaces
        if (argMultimapUntag.getPreamble().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT + " "
                    + MESSAGE_UNTAG_EMPLOYEE_TO_TASK_INSTRUCTIONS, UntagTaskCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimapUntag.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + UntagTaskCommand.MESSAGE_USAGE, pe);
        }

        if (!arePrefixesPresent(argMultimapUntag, PREFIX_NAME)) {
            throw new ParseException(String.format(MESSAGE_UNTAG_EMPLOYEE_TO_TASK_INSTRUCTIONS,
                    UntagTaskCommand.MESSAGE_USAGE));
        }

        String name = argMultimapUntag.getValue(PREFIX_NAME).get();

        if (name.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_EMPTY_NAME, UntagTaskCommand.MESSAGE_USAGE));
        }

        return new UntagTaskCommand(index, name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
