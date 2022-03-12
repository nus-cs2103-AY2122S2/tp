package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;


/**
 * Assigns a task to a person in address book.
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

    public static final String MESSAGE_ASSIGN_TASK_SUCCESS = "Task Assigned: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already assigned to person.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no person with the given studentId";

    private final StudentId studentId;
    private final Task task;

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
        }

        return new CommandResult(String.format(MESSAGE_ASSIGN_TASK_SUCCESS, task + "to student with ID: " + studentId));
    }

}
