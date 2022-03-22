package seedu.trackermon.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_COMMENT;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.commons.exceptions.IllegalValueException;
import seedu.trackermon.logic.commands.CommentCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.Comment;

public class CommentCommandParser {
    /**
     * hi.
     * @param args
     * @return class
     * @throws ParseException exception.
     */
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMMENT);

        Index index;
        Comment comment;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format("1", "2"), ive);
        }
        comment = new Comment(argumentMultimap.getValue(PREFIX_COMMENT).orElse(""));

        return new CommentCommand(index, comment);
    }
}
