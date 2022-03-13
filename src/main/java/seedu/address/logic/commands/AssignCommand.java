package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.PersonNotFoundException;



/**
 * Assigns a task to a student in TAPA.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a tasks to a student. "
            + "Parameters: "
            + PREFIX_ID + "STUDENT_ID "
            + PREFIX_TASK_NAME + "TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A0123456Z "
            + PREFIX_TASK_NAME + "assignment 1";

    public static final String MESSAGE_SUCCESS = "Task Assigned: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already assigned to a specified person.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no person with the given studentId";

    private final StudentId studentId;
    private final Task task;

    /**
     * @param studentId the student ID of the person to be assigned.
     * @param task the task to be assigned.
     */
    public AssignCommand(StudentId studentId, Task task) {
        requireNonNull(studentId);
        requireNonNull(task);

        this.studentId = studentId;
        this.task = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            requireNonNull(model);
            model.assignTaskToPerson(studentId, task);
        } catch (DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        } catch (PersonNotFoundException pnfe) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        } finally {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, task + ", to student with ID: " + studentId));
    }

}
