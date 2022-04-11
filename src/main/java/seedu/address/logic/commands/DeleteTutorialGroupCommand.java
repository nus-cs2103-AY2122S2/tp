package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Deletes the specified tutorial group from a student using their displayed index
 */
public class DeleteTutorialGroupCommand extends Command {

    public static final String COMMAND_WORD = "deletetg";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified tutorial group "
            + "from a student identified by the index number used in the displayed student list.\n"
            + "Only one tutorial group can be deleted at a time.\n"
            + "The tutorial group must be written EXACTLY, but is case-insensitive.\n"
            + "The tutorial group cannot be deleted if it is the ONLY tutorial group a student has.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUP\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_TUTORIAL_GROUP + "Cs2101 g08";

    public static final String MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS = "Deleted Tutorial Group: %1$s";
    public static final String MESSAGE_NOT_DELETED = "Exactly one tutorial group must be provided.";
    public static final String MESSAGE_NO_SUCH_TUTORIAL_GROUP = "The student at this index does not "
            + "have this tutorial group.";
    public static final String MESSAGE_CANNOT_DELETE_ONLY_TUTORIAL_GROUP = "This tutorial group cannot be deleted "
            + "because the student at this index only has this tutorial group.";
    public static final String MESSAGE_INDEX_OUT_OF_RANGE = String.format(
        Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, "Index is larger than the number of "
            + "students in the viewed list.");

    private final Index index;
    private final TutorialGroup tutorialGroupToDelete;

    /**
     * DeleteTutorialGroupCommand constructor.
     *
     * @param index of the student in the filtered student list to edit
     * @param tutorialGroupToDelete {@code String} of tutorial group to delete from student
     */
    public DeleteTutorialGroupCommand(Index index, String tutorialGroupToDelete) {
        requireNonNull(index);
        requireNonNull(tutorialGroupToDelete);

        this.index = index;
        this.tutorialGroupToDelete = new TutorialGroup(tutorialGroupToDelete);
    }

    /**
     * Another DeleteTutorialGroupCommand constructor.
     *
     * @param index of the student in the filtered student list to edit
     * @param tutorialGroupToDelete {@code TutorialGroup} to delete from student
     */
    public DeleteTutorialGroupCommand(Index index, TutorialGroup tutorialGroupToDelete) {
        requireNonNull(index);
        requireNonNull(tutorialGroupToDelete);

        this.index = index;
        this.tutorialGroupToDelete = tutorialGroupToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_RANGE);
        }
        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // Identify if tutorial group does not exist
        if (!studentToEdit.tutorialGroupExists(tutorialGroupToDelete)) {
            throw new CommandException(MESSAGE_NO_SUCH_TUTORIAL_GROUP);
        }

        Student updatedStudent = createNewStudent(studentToEdit, tutorialGroupToDelete);
        model.setStudent(studentToEdit, updatedStudent);
        model.getFilteredStudentList();
        return new CommandResult(String.format(MESSAGE_DELETE_TUTORIAL_GROUP_SUCCESS, updatedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code deleteTutorialGroupDescriptor}.
     */
    private static Student createNewStudent(Student studentToEdit, TutorialGroup tutorialGroupToDelete)
            throws CommandException {
        assert studentToEdit != null;

        Name currName = studentToEdit.getName();
        Email currEmail = studentToEdit.getEmail();
        Telegram currTelegram = studentToEdit.getTelegram();
        GitHub currGitHub = studentToEdit.getGitHub();

        Set<TutorialGroup> currTutorialGroups = studentToEdit.getTutorialGroups();

        assert studentToEdit.tutorialGroupExists(tutorialGroupToDelete);

        // Identify if tutorial group to delete is the only tutorial group
        if (currTutorialGroups.size() == 1) {
            throw new CommandException(MESSAGE_CANNOT_DELETE_ONLY_TUTORIAL_GROUP);
        }

        Set<TutorialGroup> updatedTutorialGroups = new HashSet<>();
        for (TutorialGroup tg : currTutorialGroups) {
            if (tg.equals(tutorialGroupToDelete)) {
                continue;
            }
            updatedTutorialGroups.add(tg);
        }

        return new Student(currName, currTelegram, currEmail, currGitHub, updatedTutorialGroups);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTutorialGroupCommand)) {
            return false;
        }

        // state check
        DeleteTutorialGroupCommand d = (DeleteTutorialGroupCommand) other;
        return index.equals(d.index)
                && tutorialGroupToDelete.equals(d.tutorialGroupToDelete);
    }

}
