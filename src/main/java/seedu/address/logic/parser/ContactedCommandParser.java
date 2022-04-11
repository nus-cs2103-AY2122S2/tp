package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAST_CONTACTED;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.LastContacted;

/**
 * Parses input arguments and creates a new ContactedCommand object
 */
public class ContactedCommandParser implements Parser<ContactedCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ContactedCommand
     * and returns a ContactedCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ContactedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAST_CONTACTED);

        if (!arePrefixesPresent(argMultimap, PREFIX_LAST_CONTACTED)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException((String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedCommand.MESSAGE_USAGE)));
        }

        EditCommand.EditClientDescriptor editClientDescriptor = new EditCommand.EditClientDescriptor();

        LastContacted lastContacted = ParserUtil.parseLastContacted(argMultimap.getValue(PREFIX_LAST_CONTACTED).get());

        editClientDescriptor.setLastContacted(lastContacted);
        return new ContactedCommand(index, editClientDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
