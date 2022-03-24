package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AppendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Field;
import seedu.address.model.person.FieldRegistry;
import seedu.address.model.tag.Tag;

public class AppendCommandParser implements Parser<AppendCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AppendCommand
     * and returns an AppendCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AppendCommand parse(String args) throws ParseException, IllegalArgumentException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length + 1);
        allPrefixes[allPrefixes.length - 1] = Tag.PREFIX;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AppendCommand.MESSAGE_USAGE), pe);
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

        Set<Tag> tags = Tag.createSet(argMultimap.getAllValues(Tag.PREFIX));

        return new AppendCommand(index, fields, tags);
    }
}
