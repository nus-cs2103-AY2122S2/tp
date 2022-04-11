package seedu.trackermon.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.logic.commands.EditCommand;
import seedu.trackermon.logic.commands.EditCommand.EditShowDescriptor;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @param args the {@code String} of arguments in the context of the EditCommand
     * @return returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STATUS, PREFIX_TAG, PREFIX_COMMENT, PREFIX_RATING);

        String indexArg = argMultimap.getPreamble();
        if (indexArg.isBlank() || indexArg.split("\\s+").length > 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(indexArg);

        EditCommand.EditShowDescriptor editShowDescriptor = new EditShowDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editShowDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editShowDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        if (argMultimap.getValue(PREFIX_RATING).isPresent()) {
            editShowDescriptor.setRating(ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).get()));
        }

        if (argMultimap.getValue(PREFIX_COMMENT).isPresent()) {
            editShowDescriptor.setComment(ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editShowDescriptor::setTags);

        if (!editShowDescriptor.isAnyFieldEdited()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        return new EditCommand(index, editShowDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
