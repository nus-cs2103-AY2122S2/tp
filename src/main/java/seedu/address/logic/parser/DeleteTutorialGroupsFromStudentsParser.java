package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Set;

import seedu.address.logic.commands.DeleteTutorialGroupsFromStudentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Parses input arguments and creates a new DeleteTutorialGroupsFromStudents object
 */
public class DeleteTutorialGroupsFromStudentsParser implements Parser<DeleteTutorialGroupsFromStudentsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorialGroupsFromStudentsCommand
     * and returns a DeleteTutorialGroupsFromStudentsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteTutorialGroupsFromStudentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);

        // Exception thrown if prefix or value missing
        if (argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   DeleteTutorialGroupsFromStudentsCommand.MESSAGE_USAGE));
        }

        Set<TutorialGroup> tutorialGroupListToDelete = ParserUtil.parseTutorialGroups(argMultimap
                .getAllValues(PREFIX_TUTORIAL_GROUP));

        return new DeleteTutorialGroupsFromStudentsCommand(tutorialGroupListToDelete);
    }
}
