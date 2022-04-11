package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_FIELD_NOT_EDITED;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.parser.CliSyntax.PREFIX_AT_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.HashMap;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditTaskCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditTaskCommand object.
 */
public class EditTaskCommandParser implements Parser<EditTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTaskCommand
     * and returns a EditTaskCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
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
        HashMap<String, Boolean> prefixStatusHash = prefixStatusCheck(argMultimap);

        return new EditTaskCommand(index, desc, date, time, prefixStatusHash);
    }

    /**
     * Initialize a hashmap that contains the status of a prefix.
     * By status, it means whether a prefix has been inputted by a user or not.
     */
    private HashMap<String, Boolean> initPrefixStatusHash() {
        HashMap<String, Boolean> prefixStatusHash = new HashMap<>();
        prefixStatusHash.put("description", true);
        prefixStatusHash.put("date", true);
        prefixStatusHash.put("datetime", true);
        return prefixStatusHash;
    }

    /**
     * Check if a prefix has been inputted by a user or not.
     * If yes, the boolean value of the corresponding prefix is true.
     * Else, it is false.
     * Note that it only checks if the prefix has been inputted.
     * It does not check if there's a value attached to the prefix.
     */
    private HashMap<String, Boolean> prefixStatusCheck (ArgumentMultimap argMultimap) {
        HashMap<String, Boolean> prefixStatusHash = initPrefixStatusHash();

        if (argMultimap.getValue(PREFIX_DESCRIPTION).isEmpty()) {
            prefixStatusHash.replace("description", false);
        }

        if (argMultimap.getValue(PREFIX_DATE).isEmpty()) {
            prefixStatusHash.replace("date", false);
        }

        if (argMultimap.getValue(PREFIX_AT_DATETIME).isEmpty()) {
            prefixStatusHash.replace("datetime", false);
        }

        return prefixStatusHash;
    }
}
