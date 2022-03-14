package seedu.contax.logic.parser;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.contax.logic.commands.FindByTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;
import seedu.contax.model.tag.Name;

/**
 * Parses input arguments and creates a new FindByTagCommand object.
 */
public class FindByTagCommandParser implements Parser<FindByTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of FindByTagCommand and returns a FindByTagCommand
     * object for execution.
     */
    public FindByTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (argumentMultimap.getValue(PREFIX_TAG).isEmpty() || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindByTagCommand.MESSAGE_USAGE));
        }

        // Guarantees the keyword string is valid
        if (!Name.isValidName(argumentMultimap.getValue(PREFIX_TAG).get())) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        TagNameContainsKeywordsPredicate predicate =
                new TagNameContainsKeywordsPredicate(argumentMultimap.getValue(PREFIX_TAG).get());

        return new FindByTagCommand(predicate);
    }
}
