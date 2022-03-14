package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAB3Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    private static final Pattern DELETE_COMMAND_FORMAT = Pattern.compile("(?<entityType>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteCommand parse(String args, Model model) throws ParseException {
        final Matcher matcher = DELETE_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        final String entityType = matcher.group("entityType");
        final String arguments = matcher.group("arguments");

        try {
            Index index = ParserUtil.parseIndex(arguments);
            return new DeleteCommand(index, EntityType.valueOf(entityType.trim().toUpperCase()));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAB3Command.MESSAGE_USAGE), pe);
        }
    }
}
