package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.exceptions.InvalidTaskIndexException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.person.exceptions.TaskAlreadyCompleteException;
import seedu.address.model.person.exceptions.TaskAlreadyNotCompleteException;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a specific done task as undone for a particular student. "
            + "Parameters: STUDENT_ID and INDEX \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_ID + "A0123456Z " + PREFIX_INDEX + "1";

    public static final String MARKED_TASK_SUCCESS = "Task for %1$s unmarked";

    private final StudentId studentId;
    private final Index index;

    /**
     * Creates a UnmarkCommand to unmark the {@code Task} of the specified {@code Person} using the specified index.
     */
    public UnmarkCommand(StudentId studentId, Index index) {
        requireNonNull(studentId);
        requireNonNull(index);

        this.studentId = studentId;
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        try {
            requireNonNull(model);
            model.unmarkTaskOfPerson(studentId, index);
        } catch (InvalidTaskIndexException invalidTaskIndexException) {
            throw new InvalidTaskIndexException();
        } catch (TaskAlreadyNotCompleteException taskAlreadyCompleteException) {
            throw new TaskAlreadyCompleteException();
        } catch (PersonNotFoundException personNotFoundException) {
            throw new PersonNotFoundException();
        } finally {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MARKED_TASK_SUCCESS, studentId));
    }
}
