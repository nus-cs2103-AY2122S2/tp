package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.exceptions.DuplicateTaskException;
import seedu.address.model.person.exceptions.ModuleCodeNotFoundException;
import seedu.address.model.person.exceptions.PartialDuplicateTaskException;
import seedu.address.model.person.exceptions.PersonNotFoundException;



/**
 * Assigns a task to a student in TAPA.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a tasks to a student. \n"
            + "Parameters: "
            + PREFIX_ID + "STUDENT_ID "
            + PREFIX_TASK_NAME + "TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_ID + "A0123456Z "
            + PREFIX_TASK_NAME + "assignment 1\n"
            + "or\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_TASK_NAME + "TASK_NAME\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS2103T "
            + PREFIX_TASK_NAME + "assignment 1\n";

    public static final String MESSAGE_SUCCESS = "Task Assigned: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task is already assigned to all students.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no person with the given studentId";
    public static final String MODULE_CODE_NOT_FOUND = "There is no person taking the given module";

    private final StudentId studentId;
    private final Task task;
    private final ModuleCode moduleCode;

    /**
     * @param studentId the student ID of the person to be assigned.
     * @param task the task to be assigned.
     */
    public AssignCommand(StudentId studentId, Task task) {
        requireNonNull(studentId);
        requireNonNull(task);

        this.studentId = studentId;
        this.task = task;
        this.moduleCode = null;
    }

    /**
     * @param moduleCode the module code of the module of which all students are to be assigned a task.
     * @param task the task to be assigned.
     */
    public AssignCommand(ModuleCode moduleCode, Task task) {
        requireNonNull(moduleCode);
        requireNonNull(task);

        this.moduleCode = moduleCode;
        this.task = task;
        this.studentId = null;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            requireNonNull(model);
            if (this.studentId != null) {
                model.assignTaskToPerson(studentId, task);
            } else {
                model.assignTaskToAllInModule(moduleCode, task);
            }
        } catch (DuplicateTaskException dte) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        } catch (PersonNotFoundException pnfe) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        } catch (ModuleCodeNotFoundException mnfe) {
            throw new CommandException(MODULE_CODE_NOT_FOUND);
        } catch (PartialDuplicateTaskException pdte) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, task.getTaskName() + ", to some students "
                    + "with Module Code: " + moduleCode));
        } finally {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        if (this.studentId != null) {
            return new CommandResult(String.format(MESSAGE_SUCCESS, task.getTaskName() + ", to student with ID: "
                    + studentId));
        } else {
            return new CommandResult(String.format(MESSAGE_SUCCESS, task.getTaskName()
                    + ", to all students with Module Code: " + moduleCode));
        }
    }

}
