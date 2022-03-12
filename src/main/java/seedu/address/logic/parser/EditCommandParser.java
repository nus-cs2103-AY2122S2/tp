package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;
import seedu.address.model.person.FieldRegistry;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length + 1);
        allPrefixes[allPrefixes.length - 1] = Tag.PREFIX;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        // Parse all fields.
        ArrayList<Field> fields = new ArrayList<>();
        for (Prefix p : FieldRegistry.PREFIXES) {
            Optional<String> argOpt = argMultimap.getValue(p);
            if (argOpt.isPresent()) {
                FieldParser<? extends Field> parser = FieldRegistry.PARSERS.get(p);
                Field field = parser.parse(argOpt.get());
                fields.add(field);
            }
        }

        // Parse tags. Null represents no change. Empty set represents clear tags.
        Set<Tag> tags = parseTagsForEdit(argMultimap.getAllValues(Tag.PREFIX)).orElse(null);

        // Check that something was edited.
        if (tags == null && fields.isEmpty()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        return new EditCommand(index, fields, tags);
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
        return Optional.of(Tag.createSet(tagSet));
    }

}
