package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import manageezpz.commons.core.Messages;
import manageezpz.model.Model;
import manageezpz.model.task.Task;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose description contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: task/ desc/ KEYWORD [MORE_KEYWORDS]..."
            + " or date/ YYYY-mm-dd\n"
            + "Example: " + COMMAND_WORD + " \"task/ desc/ Play Genshin Impact\""
            + " or " + COMMAND_WORD + " \"date/ 2022-01-01\"";

    private Predicate<Task> predicate;

    public FindCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        String result = printList(model.getFilteredTaskList());
        return new CommandResult(
                String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW, model.getFilteredTaskList().size())
                        + result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }

    private String printList(ObservableList<Task> filteredTaskList) {
        String result = "";
        int index = 1;
        for (Task task : filteredTaskList) {
            String curIndex = String.join("", String.valueOf(index), ".");
            String curTask = String.join(" ", curIndex, task.toString());
            result = String.join("\n", result, curTask);
            index++;
        }
        return result;
    }
}
