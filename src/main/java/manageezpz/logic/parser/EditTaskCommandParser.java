package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_FIELD_NOT_EDITED;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_AT_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

import java.util.Arrays;

/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns a EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DESCRIPTION, PREFIX_AT_DATETIME, PREFIX_DATE);

        // Invalid command if getPreamble() is empty or contains whitespaces
        if (argMultimap.getPreamble().isEmpty() || argMultimap.getPreamble().contains(" ")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND,
                    EditTaskCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage() + "\n\n" + EditTaskCommand.MESSAGE_USAGE, pe);
        }

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()
                && argMultimap.getValue(PREFIX_DATE).isEmpty()
                && argMultimap.getValue(PREFIX_AT_DATETIME).isEmpty()) {
            throw new ParseException(MESSAGE_FIELD_NOT_EDITED + EditTaskCommand.MESSAGE_USAGE);
        }

        String desc = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");
        String date = argMultimap.getValue(PREFIX_DATE).orElse("");
        String time = argMultimap.getValue(PREFIX_AT_DATETIME).orElse("");
        boolean[] prefixStatusArr = prefixStatusCheck(argMultimap);

        for (int i  = 0; i < prefixStatusArr.length; i++){
            System.out.println(prefixStatusArr[i]);
        }

        return new EditTaskCommand(index, desc, date, time, prefixStatusArr);
    }

    private boolean[] prefixStatusCheck (ArgumentMultimap argMultimap) {
        boolean[] prefixStatusArr = new boolean[3];
        Arrays.fill(prefixStatusArr, true);
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
            prefixStatusArr[0] = false;
        }

        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            prefixStatusArr[1] = false;
        }

        if (argMultimap.getValue(PREFIX_AT_DATETIME).isEmpty()) {
            prefixStatusArr[2] = false;
        }

        return prefixStatusArr;
    }
}
