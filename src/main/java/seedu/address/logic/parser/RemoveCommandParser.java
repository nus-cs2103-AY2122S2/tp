package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FieldRegistry;
import seedu.address.model.tag.Tag;

public class RemoveCommandParser implements Parser<RemoveCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns an RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException, IllegalArgumentException {
        requireNonNull(args);

        Prefix[] allPrefixes = Arrays.copyOf(FieldRegistry.PREFIXES, FieldRegistry.PREFIXES.length + 1);
        allPrefixes[allPrefixes.length - 1] = Tag.PREFIX;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, allPrefixes);

        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE), pe);
        }

        return new RemoveCommand(index, argMultimap.getPrefixes(), Tag.createSet(argMultimap.getAllValues(Tag.PREFIX)));
    }
}
