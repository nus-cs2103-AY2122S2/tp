package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.StudentIdContainsKeywordsPredicate;
import seedu.address.model.person.Task;
import seedu.address.model.person.TaskList;

/**
 * Displays the list of task that a particular student currently has.
 */
public class TaskCommand extends Command {

    public static final String COMMAND_WORD = "task";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the tasks that are allocated to a particular student. "
            + "Parameters: "
            + PREFIX_ID + "STUDENT_ID "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A0123456Z";

    public static final String MESSAGE_SUCCESS = "Here are the tasks that %1$s has:\n\n" + "%2$s";
    public static final String MESSAGE_EMPTY_TASKLIST = "No task assigned to this student.\n"
            + "Perhaps you might want to assign a task using the 'assign' command?";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no student with the specified Student ID!";

    private final StudentId studentId;

    /**
     * Constructs a new TaskCommand.
     * @param studentId the student ID of the student to be assigned. //TODO
     */
    public TaskCommand(StudentId studentId) {
        requireNonNull(studentId);
        this.studentId = studentId;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Checks if a student with the specified studentId exist.
        assert studentId != null;
        StudentIdContainsKeywordsPredicate pred =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList(studentId.toString()));
        model.updateFilteredPersonList(pred);
        if (model.getFilteredPersonList().size() == 0) { // Student with specified studentId does not exist
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        }

        // There should only be a single student with this studentId since studentId is a UNIQUE field.
        Person targetStudent = model.getFilteredPersonList().get(0);
        TaskList targetTaskList = targetStudent.getTaskList();

        // Resets model so that it displays all students (since we have already extracted the Person of interest).
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // Empty or non-existent taskList
        if (targetTaskList == null || targetTaskList.isEmpty()) {
            throw new CommandException(MESSAGE_EMPTY_TASKLIST);
        }

        // Categorises the tasks into two new TaskList, depending on the completion status.
        TaskList incompleteTasks = new TaskList();
        TaskList completedTasks = new TaskList();
        for (Task i : targetTaskList.getTaskList()) {
            if (i.isTaskComplete()) {
                completedTasks.addTask(i);
            } else {
                incompleteTasks.addTask(i);
            }
        }

        if (incompleteTasks.isEmpty()) { // This means that all tasks are completed.
            return new CommandResult(String.format(MESSAGE_SUCCESS, studentId,
                    "Completed tasks:\n" + completedTasks));
        } else if (completedTasks.isEmpty()) { // This means that all tasks are incomplete.
            return new CommandResult(String.format(MESSAGE_SUCCESS, studentId,
                    "Incomplete tasks:\n" + incompleteTasks));
        } else { // Mixture of different types of tasks.
            return new CommandResult(String.format(MESSAGE_SUCCESS, studentId,
                    "Incomplete tasks:\n" + incompleteTasks + "\n" + "Completed tasks:\n" + completedTasks));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskCommand // instanceof handles nulls
                && studentId.equals(((TaskCommand) other).studentId)); // state check
    }

}
