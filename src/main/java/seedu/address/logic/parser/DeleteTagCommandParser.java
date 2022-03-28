package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class DeleteTagCommandParser implements Parser<DeleteTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);
        Pair<Index, String> userInputs;
        Pair<Integer, String> trimOutNumber;

        try {
            userInputs = ParserUtil.parseOutIndex(argMultimap.getPreamble());
            trimOutNumber = ParserUtil.parseOutNumber(userInputs.getValue());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        Index index = userInputs.getKey();
        Integer tagNumber = trimOutNumber.getKey();
        return new DeleteTagCommand(index, tagNumber);
    }

}
