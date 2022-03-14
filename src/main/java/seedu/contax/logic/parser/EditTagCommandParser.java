package seedu.contax.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NEWTAGNAME;

import java.util.stream.Stream;

import seedu.contax.commons.core.index.Index;
import seedu.contax.logic.commands.EditTagCommand;
import seedu.contax.logic.commands.EditTagCommand.EditTagDescriptor;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.tag.Name;

/**
 * Parses input arguments and creates a new EditTagCommand object.
 */
public class EditTagCommandParser implements Parser<EditTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of EditTagCommand and returns an EditTagCommand
     * object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NEWTAGNAME);

        // Unlike the other edit commands, Tag only contains the tag name field. Hence, if the new tag name and/or
        // index is not present,the command must be wrong
        if (!arePrefixesPresent(argumentMultimap, PREFIX_NEWTAGNAME) || argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE));
        }

        if (!Name.isValidName(argumentMultimap.getValue(PREFIX_NEWTAGNAME).get())) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTagCommand.MESSAGE_USAGE), pe);
        }

        EditTagDescriptor editTagDescriptor = new EditTagDescriptor();
        editTagDescriptor.setTagName(argumentMultimap.getValue(PREFIX_NEWTAGNAME).get());

        return new EditTagCommand(index, editTagDescriptor);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
