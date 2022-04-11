package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTutorialGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Parses input arguments and creates a new DeleteTutorialGroupCommand object
 */
public class DeleteTutorialGroupParser implements Parser<DeleteTutorialGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteTutorialGroupCommand
     * and returns a DeleteTutorialGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform with the expected format.
     */
    public DeleteTutorialGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);

        // Exception thrown if prefix or value missing
        if (argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   DeleteTutorialGroupCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, pe.getMessage()));
        }

        // Exception thrown if more than one value
        if (argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP).size() > 1) {
            throw new ParseException(DeleteTutorialGroupCommand.MESSAGE_NOT_DELETED);
        }

        assert argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP).size() == 1;

        TutorialGroup tutorialGroupToDelete = ParserUtil.parseTutorialGroup(argMultimap
                                                                            .getAllValues(PREFIX_TUTORIAL_GROUP)
                                                                            .get(0));

        return new DeleteTutorialGroupCommand(index, tutorialGroupToDelete);
    }
}
