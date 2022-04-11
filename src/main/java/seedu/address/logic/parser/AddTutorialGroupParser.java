package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTutorialGroupCommand;
import seedu.address.logic.commands.AddTutorialGroupCommand.AddTutorialGroupDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Parses input arguments and creates a new AddTutorialGroupCommand object
 */
public class AddTutorialGroupParser implements Parser<AddTutorialGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTutorialGroupCommand
     * and returns an AddTutorialGroupCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTutorialGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL_GROUP);

        // Exception thrown if prefix or value missing
        if (argMultimap.getAllValues(PREFIX_TUTORIAL_GROUP).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   AddTutorialGroupCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, pe.getMessage()));
        }

        AddTutorialGroupDescriptor addTutorialGroupDescriptor = new AddTutorialGroupDescriptor();
        Set<TutorialGroup> tutorialGroupList = ParserUtil.parseTutorialGroups(argMultimap
                                                                              .getAllValues(PREFIX_TUTORIAL_GROUP));
        addTutorialGroupDescriptor.setTutorialGroups(tutorialGroupList);

        if (!addTutorialGroupDescriptor.isAnyFieldEdited()) {
            throw new ParseException(AddTutorialGroupCommand.MESSAGE_NOT_ADDED);
        }

        return new AddTutorialGroupCommand(index, addTutorialGroupDescriptor);
    }
}
