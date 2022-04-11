package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Deletes tutorial groups given from all students in TACH. If student does not have a tutorial group after deleting
 * the student will be deleted.
 */
public class DeleteTutorialGroupsFromStudentsCommand extends Command {

    public static final String COMMAND_WORD = "deletetgall";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutorial group identified by the prefix from all students.\n"
            + "Students without a tutorial group after the deletion will also be deleted.\n"
            + "Parameters: "
            + PREFIX_TUTORIAL_GROUP + "TUTORIAL_GROUPS...\n"
            + "Example: " + COMMAND_WORD + PREFIX_TUTORIAL_GROUP + "CS2103-W15-3" + " "
            + PREFIX_TUTORIAL_GROUP + "CS2101 G08";

    public static final String MESSAGE_TUTORIAL_GROUP_DELETE_SUCCESS = "Deleted tutorial group(s): %1$s";

    private final Set<TutorialGroup> tutorialGroupsToDelete;

    public DeleteTutorialGroupsFromStudentsCommand(Set<TutorialGroup> tutorialGroupsToDelete) {
        this.tutorialGroupsToDelete = tutorialGroupsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> studentList = model.getSortedStudentList();
        List<Student> studentsToRemoveTutorialGroup = new ArrayList<>();
        for (Student student : studentList) {
            studentsToRemoveTutorialGroup.add(student);
        }

        for (Student studentToRemoveTutorialGroup : studentsToRemoveTutorialGroup) {
            Set <TutorialGroup> updatedTutorialGroupForStudent = removeTutorialGroups(
                    studentToRemoveTutorialGroup.getTutorialGroups(), tutorialGroupsToDelete);
            if (updatedTutorialGroupForStudent.size() == 0) {
                model.deleteStudent(studentToRemoveTutorialGroup);
            } else {
                Student updatedStudent = createNewStudent(studentToRemoveTutorialGroup, updatedTutorialGroupForStudent);
                model.setStudent(studentToRemoveTutorialGroup, updatedStudent);
            }
        }

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_TUTORIAL_GROUP_DELETE_SUCCESS,
                tutorialGroupsToDelete.toString()));
    }

    private static Set<TutorialGroup> removeTutorialGroups(Set<TutorialGroup> targetTutorialGroups,
                                                           Set<TutorialGroup> tutorialGroupsToRemove) {
        Set<TutorialGroup> updatedTutorialGroup = new HashSet<>();
        updatedTutorialGroup.addAll(targetTutorialGroups);
        for (TutorialGroup tgtr : tutorialGroupsToRemove) {
            for (TutorialGroup ttg : targetTutorialGroups) {
                if (ttg.equals(tgtr)) {
                    updatedTutorialGroup.remove(ttg);
                }
            }
        }
        return updatedTutorialGroup;
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code Set<TutorialGroup>}.
     */
    private static Student createNewStudent(Student studentToEdit, Set<TutorialGroup> newTutorialGroup) {
        assert studentToEdit != null;

        Name currName = studentToEdit.getName();
        Email currEmail = studentToEdit.getEmail();

        Telegram currTelegram = studentToEdit.getTelegram();
        GitHub currGitHub = studentToEdit.getGitHub();

        return new Student(currName, currTelegram, currEmail, currGitHub, newTutorialGroup);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTutorialGroupsFromStudentsCommand // instanceof handles nulls
                && tutorialGroupsToDelete.equals(((DeleteTutorialGroupsFromStudentsCommand) other)
                .tutorialGroupsToDelete)); // state check
    }

}
