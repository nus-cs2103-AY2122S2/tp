package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsModulePredicate;

/**
 * Searches persons by their corresponding modules. This allows filtering by modules for users within ModuleMateFinder
 * Keyword matching is case insensitive.
 */
public class FilterCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_CONSTRAINTS = "Modules names should have 2-3 letters prefix "
            + "followed by 4 digits and an optional letter\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose modules contain any of "
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD \n"
            + "Example: " + COMMAND_WORD + " CS3230";

    private final NameContainsModulePredicate predicate;

    public FilterCommand(NameContainsModulePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult executeUndoableCommand(Model model, CommandHistory commandHistory,
                                                StackUndoRedo undoRedoStack) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
