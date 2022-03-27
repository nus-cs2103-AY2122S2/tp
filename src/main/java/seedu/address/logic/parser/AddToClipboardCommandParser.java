package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddToClipboardCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.predicates.NameExistsPredicate;

/**
 * Parses input arguments and creates a new {@code AddToClipboardCommand} object
 */
public class AddToClipboardCommandParser implements Parser<AddToClipboardCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddToClipboardCommand}
     * and returns a {@code AddToClipboardCommand} object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddToClipboardCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (argMultimap.getValue(PREFIX_NAME).isEmpty()) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, AddToClipboardCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new AddToClipboardCommand(new NameExistsPredicate(name));
    }

}
