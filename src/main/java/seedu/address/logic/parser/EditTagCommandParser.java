package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditTagCommand object
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditTagCommand
     * and returns an EditTagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);
        Pair<Index, String> indexAndRemaining;
        Pair<Integer, String> tagNumberAndNewTag;

        try {
            indexAndRemaining = ParserUtil.parseOutIndex(argMultimap.getPreamble());
            tagNumberAndNewTag = ParserUtil.parseOutNumber(indexAndRemaining.getValue());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE), pe);
        }

        Index index = indexAndRemaining.getKey();
        Integer tagNumber = tagNumberAndNewTag.getKey();
        Tag newTag = ParserUtil.parseTag(tagNumberAndNewTag.getValue());
        return new EditTagCommand(index, tagNumber, newTag);
    }

}
