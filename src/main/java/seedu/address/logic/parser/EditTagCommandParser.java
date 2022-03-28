package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTagCommand parse(String args) throws ParseException {
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
        Tag tag = ParserUtil.parseTag(trimOutNumber.getValue());
        return new EditTagCommand(index, tagNumber, tag);
    }

}
