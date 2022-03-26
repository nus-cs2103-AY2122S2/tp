package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Task;
import seedu.address.model.person.exceptions.InvalidTaskIndexException;
import seedu.address.model.person.exceptions.ModuleCodeNotFoundException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.TaskNotFoundException;

/**
 * Deletes a tasks assigned to a student or a student taking a module.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "deleteTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete a specific task for a particular student. "
            + "Parameters: STUDENT_ID and INDEX  (or) MODULE_CODE and TASK_NAME\n"
            + "Example: \n" + COMMAND_WORD + " " + PREFIX_ID + "A0123456Z " + PREFIX_INDEX + "1\n (or) \n"
            + "Example: \n" + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2030 " + PREFIX_TASK_NAME + "Assignment 1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Task for %1$s deleted.";
    public static final String MESSAGE_MODULE_CODE_NOT_FOUND = "There is no person taking the given module.";
    public static final String MESSAGE_TASK_NOT_FOUND = "There is no person assigned to the given task.";
    public static final String MESSAGE_PERSON_NOT_FOUND = "There is no person with the given studentId.";
    public static final String INVALID_TASK_INDEX = "Invalid index.";

    private final Optional<Index> index;
    private final Optional<ModuleCode> moduleCode;
    private final Optional<StudentId> studentId;
    private final Optional<Task> task;

    /**
     * Creates a DeleteTaskCommand to delete the {@code Task} of the specified {@code Person} using the specified index.
     */
    public DeleteTaskCommand(StudentId studentId, Index index) {
        requireNonNull(studentId);
        requireNonNull(index);

        this.studentId = Optional.of(studentId);
        this.index = Optional.of(index);
        this.moduleCode = Optional.empty();
        this.task = Optional.empty();
    }

    /**
     * Creates a DeleteTaskCommand to delete the {@code Task} assigned to any {@code Person} taking {@code moduleCode}.
     */
    public DeleteTaskCommand(ModuleCode moduleCode, Task task) {
        requireNonNull(moduleCode);
        requireNonNull(task);

        this.moduleCode = Optional.of(moduleCode);
        this.task = Optional.of(task);
        this.studentId = Optional.empty();
        this.index = Optional.empty();
    }

    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using if conditionals.
    public CommandResult execute(Model model) throws CommandException {

        try {
            requireNonNull(model);
            if (studentId.isPresent() && index.isPresent()) {
                model.deleteTaskOfPerson(studentId.get(), index.get());
            } else {
                model.deleteTaskForAllInModule(moduleCode.get(), task.get());
            }
        } catch (InvalidTaskIndexException invalidTaskIndexException) {
            throw new CommandException(INVALID_TASK_INDEX);
        } catch (PersonNotFoundException personNotFoundException) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        } catch (ModuleCodeNotFoundException moduleCodeNotFoundException) {
            throw new CommandException(MESSAGE_MODULE_CODE_NOT_FOUND);
        } catch (TaskNotFoundException taskNotFoundException) {
            throw new CommandException(MESSAGE_TASK_NOT_FOUND);
        } finally {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                studentId.isPresent() ? studentId.get() : moduleCode.get()));
    }

    @Override
    public boolean equals(Object other) {
        boolean isEquals = false;

        if (other == this) {
            isEquals = true;
        } else {
            boolean isInstanceOf = other instanceof DeleteTaskCommand;
            if (!isInstanceOf) {
                return false;
            }
            DeleteTaskCommand commandToCompare = (DeleteTaskCommand) other;

            // check both student id and index.
            if (this.studentId.isPresent() && this.index.isPresent()) {
                isEquals = studentId.equals(commandToCompare.studentId) && index.equals(commandToCompare.index);
            }

            // check both module code and task.
            if (this.moduleCode.isPresent() && this.task.isPresent()) {
                isEquals = moduleCode.equals(commandToCompare.moduleCode) && task.equals(commandToCompare.task);
            }

        }
        return isEquals;
    }
}
