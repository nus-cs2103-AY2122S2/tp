package unibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static unibook.commons.core.Messages.MESSAGE_CHANGE_TO_PERSON_VIEW;

import unibook.commons.core.Messages;
import unibook.logic.commands.exceptions.CommandException;
import unibook.model.Model;
import unibook.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in UniBook whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
        + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
        + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
        + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, Boolean isPersonListShowing,
                                 Boolean isModuleListShowing, Boolean isGroupListShowing) throws CommandException {
        requireNonNull(model);
        //disallow use of this commmand on any other page other than person page
        if (!isPersonListShowing) {
            throw new CommandException(MESSAGE_CHANGE_TO_PERSON_VIEW);
        }
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FindCommand // instanceof handles nulls
            && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
