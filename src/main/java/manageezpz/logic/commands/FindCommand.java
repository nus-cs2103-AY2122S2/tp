package manageezpz.logic.commands;

import static manageezpz.logic.parser.CliSyntax.PREFIX_ASSIGNEES;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_IS_MARKED;
import static manageezpz.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TASK;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import java.util.function.Predicate;

import manageezpz.model.task.Task;

/**
 * Finds and lists all persons or task in address book whose name contains any properties.
 */
public abstract class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private static final String TASK_OPTIONS
            = String.join(", ",
            PREFIX_TASK.toString(),
            PREFIX_TODO.toString(),
            PREFIX_DEADLINE.toString(),
            PREFIX_EVENT.toString());
    private static final String TASK_PROPERTIES
            = String.join(", ",
            PREFIX_DESCRIPTION.toString(),
            PREFIX_DATE.toString(),
            PREFIX_PRIORITY.toString(),
            PREFIX_ASSIGNEES.toString(),
            PREFIX_IS_MARKED.toString());
    private static final String NOTE = "NOTE: All task properties option must be filled";
    private static final String EXAMPLE
            = String.join(" ",
            COMMAND_WORD,
            PREFIX_DEADLINE.toString(),
            "Finish TP",
            PREFIX_DATE.toString(),
            "2022-01-01");

    public static final String MESSAGE_USAGE
            = COMMAND_WORD + ": Finds all tasks that contains the properties specified.\n"
            + "Task type options: " + TASK_OPTIONS + "\n"
            + "Task properties " + TASK_PROPERTIES + "\n"
            + NOTE + "\n"
            + EXAMPLE;

    protected Predicate<Task> predicate;

    public FindCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof FindCommand) {
            FindCommand findCommand = (FindCommand) obj;
            return predicate.equals(findCommand.predicate);
        }
        return false;
    }
}
