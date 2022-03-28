package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Comment;

/**
 * Parses input arguments and creates a new {@code AddCommentCommand} object
 * AddCommentCommandParser is adapted from https://nus-cs2103-ay2122s2.github.io/tp/tutorials/AddRemark.html
 */
public class CommentCommandParser implements Parser<CommentCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public CommentCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_COMMENT);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommentCommand.MESSAGE_USAGE), ive);
        }

        if (!argMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommentCommand.MESSAGE_USAGE));
        }
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get());
        return new CommentCommand(index, comment);
    }
}
