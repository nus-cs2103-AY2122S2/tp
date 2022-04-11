package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.misc.InfoPanelTypes;
import seedu.address.logic.commands.misc.ViewTab;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.student.Student;

public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";
    public static final String COMMAND_DESCRIPTION = "Assign a student to a lesson";
    public static final String SHORTENED_COMMAND_WORD = "";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a student to a lesson\n"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_ID "
            + PREFIX_LESSON + " LESSON_ID"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT + " 5"
            + PREFIX_LESSON + " 3";
    public static final String MESSAGE_SUCCESS = "%1$s has been assigned to %2$s successfully!";
    public static final String MESSAGE_ALREADY_ENROLLED = "%1$s is already enrolled into %2$s!";
    public static final String MESSAGE_NO_SUCH_ID = "There is no %1$s with an ID of %2$s!";
    private final Index lessonIndex;
    private final Index studentIndex;

    /**
     * Constructor for AssignCommand class. This class handles the enrollment of a student to a lesson.
     * @param studentIndex the index of the student to be added
     * @param lessonIndex the index of the lesson that the student should be added to
     */
    public AssignCommand(Index studentIndex, Index lessonIndex) {
        requireAllNonNull(studentIndex, lessonIndex);
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.checkStudentListIndex(studentIndex)) {
            String errorMessage = String.format(MESSAGE_NO_SUCH_ID, "student", studentIndex.getOneBased());
            throw new CommandException(errorMessage);
        }
        if (model.checkLessonListIndex(lessonIndex)) {
            String errorMessage = String.format(MESSAGE_NO_SUCH_ID, "lesson", lessonIndex.getOneBased());
            throw new CommandException(errorMessage);

        }
        Lesson lesson = model.getFilteredLessonList().get(lessonIndex.getZeroBased());
        Student student = model.getFilteredStudentList().get(studentIndex.getZeroBased());

        boolean ifAlreadyEnrolled = student.isEnrolledIn(lesson) || lesson.hasAlreadyAssigned(student);
        if (ifAlreadyEnrolled) {
            String errorMessage = String.format(MESSAGE_ALREADY_ENROLLED, student.getName(), lesson.getName());
            throw new CommandException(errorMessage);
        }

        model.setSelectedLesson(lesson);
        model.updateAssignment(student, lesson);

        String commandResultMessage = String.format(MESSAGE_SUCCESS, student.getName(), lesson.getName());
        return new CommandResult(commandResultMessage, InfoPanelTypes.LESSON, ViewTab.LESSON);
    }

}
