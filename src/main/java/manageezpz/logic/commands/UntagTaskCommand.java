package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.model.Model;
import manageezpz.model.person.Person;
import manageezpz.model.task.Task;

public class UntagTaskCommand extends Command {

    public static final String COMMAND_WORD = "untagTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untags the specified employee from the task identified by the "
            + "index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NAME + "EMPLOYEE_FULL_NAME\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NAME + "Alex Yeoh";

    public static final String MESSAGE_UNTAG_TASK_SUCCESS = "Employee %1$s is untagged from the task: ";

    public static final String MESSAGE_NO_SUCH_PERSON = "There is no employee with the full name %1$s "
            + "in the current displayed task list!";

    public static final String MESSAGE_PERSON_NOT_TAGGED_TO_TASK = "Employee %1$s is not tagged to the task: ";

    private final Index targetIndex;
    private final String name;

    /**
     * Constructor to initialize an UntagTaskCommand class with the given
     * targetIndex and name.
     *
     * @param targetIndex Index of the Task to untag the employee
     * @param name Name of the Employee to untag the Task from
     */
    public UntagTaskCommand(Index targetIndex, String name) {
        requireAllNonNull(targetIndex, name);
        this.targetIndex = targetIndex;
        this.name = name;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownTaskList = model.getFilteredTaskList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Task taskToUntagEmployee = lastShownTaskList.get(targetIndex.getZeroBased());

        Person person = null;

        for (Person p : lastShownPersonList) {
            if (p.getName().toString().equals(name)) {
                person = p;
                break;
            }
        }

        if (person == null) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_PERSON, name) + "\n\n" + MESSAGE_USAGE);
        }

        if (!model.isEmployeeTaggedToTask(taskToUntagEmployee, person)) {
            throw new CommandException(String.format(MESSAGE_PERSON_NOT_TAGGED_TO_TASK,
                    person.getName().toString()) + taskToUntagEmployee + "\n\n" + MESSAGE_USAGE);
        }

        Task untaggedEmployeeTask = model.untagEmployeeFromTask(taskToUntagEmployee, person);
        model.decreaseNumOfTasks(person);

        return new CommandResult(String.format(MESSAGE_UNTAG_TASK_SUCCESS,
                person.getName().toString()) + untaggedEmployeeTask);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntagTaskCommand// instanceof handles nulls
                && targetIndex.equals(((UntagTaskCommand) other).targetIndex)
                && name.equals(((UntagTaskCommand) other).name));
    }
}
