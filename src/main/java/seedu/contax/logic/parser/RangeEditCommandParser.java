package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_FROM;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_RANGE_TO;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditCommand;
import seedu.contax.logic.commands.RangeEditCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.tag.Tag;



public class RangeEditCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the RangeEditCommandParser
     * and returns an RangeEditCommandParser object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RangeEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RANGE_FROM, PREFIX_RANGE_TO,
                        PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index fromIndex;
        Index toIndex;
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_RANGE_FROM).isPresent()) {
            fromIndex = Index.fromOneBased(Integer.parseInt(argMultimap.getValue(PREFIX_RANGE_FROM).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_RANGE_TO).isPresent()) {
            toIndex = Index.fromOneBased(Integer.parseInt(argMultimap.getValue(PREFIX_RANGE_TO).get()));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new RangeEditCommand(fromIndex, toIndex, editPersonDescriptor);
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
