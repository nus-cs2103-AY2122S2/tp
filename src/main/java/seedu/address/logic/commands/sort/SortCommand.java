package seedu.address.logic.commands.sort;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_KEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_ORDER;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.task.Task;


/**
 * Sorts and lists all tasks in task list by the sorting specified by user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all tasks by the specified sort key and order "
            + "and displays them as a list with index numbers. Possible sort keys are deadline, name, and priority. "
            + "Parameters: "
            + PREFIX_SORT_KEY + "SORT_KEY "
            + PREFIX_SORT_ORDER + "SORT_ORDER\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_KEY + "deadline "
            + PREFIX_SORT_ORDER + "desc";

    /** {@code SortOrder} used to denote the order of sorting. */
    private final SortOrder sortOrder;

    /** {@code SortKey} used to denote the key used for sorting. */
    private final SortKey sortKey;

    /** {@code Comparator<Task>} used to sort tasks. */
    private final Comparator<Task> comparator;

    /**
     * Creates a {@code SortCommand} object.
     */
    public SortCommand(SortKey sortKey, SortOrder sortOrder) {
        requireNonNull(sortKey);
        requireNonNull(sortOrder);
        this.sortKey = sortKey;
        this.sortOrder = sortOrder;
        this.comparator = ComparatorFactory.createComparator(sortKey, sortOrder);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedTaskList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_SORTED,
                        model.getSortedTaskList().size(), sortKey.toString(), sortOrder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortKey.equals(((SortCommand) other).sortKey)
                && sortOrder.equals(((SortCommand) other).sortOrder)); // state check
    }
}
