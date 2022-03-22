package seedu.contax.logic.parser;

import seedu.contax.logic.commands.FindAppointmentCommand;
import seedu.contax.logic.commands.FindByTagCommand;
import seedu.contax.logic.parser.exceptions.ParseException;
import seedu.contax.model.appointment.HasClientPredicate;
import seedu.contax.model.person.TagNameContainsKeywordsPredicate;
import seedu.contax.model.tag.Name;

import static seedu.contax.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.contax.logic.parser.CliSyntax.PREFIX_TAG;

public class FindAppointmentParser implements  Parser<FindAppointmentCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of FindByTagCommand and returns a FindByTagCommand
     * object for execution.
     */
    public FindAppointmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (argumentMultimap.getValue(PREFIX_NAME).isEmpty() || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAppointmentCommand.MESSAGE_USAGE));
        }

        // Guarantees the keyword string is valid
        if (!Name.isValidName(argumentMultimap.getValue(PREFIX_NAME).get())) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }

        HasClientPredicate predicate =
                new HasClientPredicate(argumentMultimap.getValue(PREFIX_NAME).get());

        return new FindAppointmentCommand(predicate);
    }
}
