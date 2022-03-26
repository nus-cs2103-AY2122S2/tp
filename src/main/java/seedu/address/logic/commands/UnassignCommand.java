package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

public class UnassignCommand extends Command {

    public static final String COMMAND_WORD = "unassign";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unassigns a student from a lesson \n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_ID "
            + PREFIX_LESSON + " LESSON_ID"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT + " 5"
            + PREFIX_LESSON + " 3";
    public static final String MESSAGE_SUCCESS = "%1$s has been unassigned to %2$s successfully!";
    public static final String MESSAGE_NOT_ENROLLED = "%1$s is not enrolled in %2$s!";
    public static final String MESSAGE_NO_SUCH_ID = "There is no %1$s with an ID of %2$s!";
    private final Index lessonId;
    private final Index studentId;

    /**
     * Constructor for the UnassignCommand class
     * @param studentId {@code Index} of the student to be unassigned
     * @param lessonId {@code Index} of the lesson to be unassigned
     */
    public UnassignCommand(Index studentId, Index lessonId) {
        requireAllNonNull(studentId, lessonId);
        this.lessonId = lessonId;
        this.studentId = studentId;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.checkStudentListIndex(studentId)) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_ID, "lesson", lessonId.getOneBased()));
        }
        if (model.checkLessonListIndex(lessonId)) {
            throw new CommandException(String.format(MESSAGE_NO_SUCH_ID, "student", studentId.getOneBased()));
        }

        Student student = model.getFilteredStudentList().get(studentId.getZeroBased());
        Lesson lesson = model.getFilteredLessonList().get(lessonId.getZeroBased());
        if (!student.isEnrolledIn(lesson) || !lesson.hasAlreadyAssigned(student)) {
            throw new CommandException(String.format(MESSAGE_NOT_ENROLLED, student.getName(), lesson.getName()));
        }
        model.updateUnassignment(student, lesson);
        model.setSelectedStudent(student);
        return new CommandResult(String.format(MESSAGE_SUCCESS, student.getName(), lesson.getName()),
                true, InfoPanelTypes.STUDENT, ViewTab.STUDENT);
    }
}
