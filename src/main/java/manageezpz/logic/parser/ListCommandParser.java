package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODAY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import manageezpz.logic.commands.ListCommand;
import manageezpz.logic.parser.exceptions.ParseException;

/**
 * The parser for list command.
 */
public class ListCommandParser implements Parser<ListCommand> {
    private static final Prefix[] VALID_PREFIXES = {PREFIX_TODO, PREFIX_DEADLINE, PREFIX_EVENT, PREFIX_TODAY};

    /**
     * Parse the user input before executing the list command.
     * @param userInput The input entered in the GUI by the user.
     * @return The List Command with the appropriate prefix.
     * @throws ParseException When there is more than one option, or no options.
     */
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimapList =
                ArgumentTokenizer.tokenize(userInput, VALID_PREFIXES);

        boolean hasMoreThanOneArgument = argMultimapList.numberOfArguments() > 2;
        boolean hasInvalidArguments = argMultimapList.getPreamble().isEmpty();

        if (hasMoreThanOneArgument || !hasInvalidArguments) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_INVALID_ARGUMENTS));
        }

        for (Prefix prefix: VALID_PREFIXES) {
            if (argMultimapList.isPrefixExist(prefix)) {
                String value = argMultimapList.getValue(prefix).get();
                if (!value.equals("")) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_INVALID_ARGUMENTS));
                }
                return new ListCommand(prefix);
            }
        }
        return new ListCommand();
    }
}
