package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.person.Person;

public class AssignCommand extends Command {
    public static final String COMMAND_WORD = "assign";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Assigns a student to a lesson"
            + "Parameters: "
            + PREFIX_STUDENT + " STUDENT_ID "
            + PREFIX_LESSON + "LESSON_ID"
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_STUDENT + " 5"
            + PREFIX_LESSON + " 3";
    public static final String MESSAGE_SUCCESS = "%1$s has been assigned to %2$s successfully!";
    public static final String MESSAGE_ALREADY_ENROLLED = "%1$s is already enrolled into %2$s!";
    private final Index lessonIndex;
    private final Index studentIndex;

    /**
     * Constructor for AssignCommand class. This class handles the enrollment of a student to a lesson.
     * @param studentIndex the index of the student to be added
     * @param lessonIndex the index of the lesson that the student should be added to
     */
    public AssignCommand(Index studentIndex, Index lessonIndex) {
        requireNonNull(studentIndex);
        requireNonNull(lessonIndex);
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
    }

    private boolean assign(Person student, Lesson lesson) {
        return student.assignLesson(lesson) && lesson.assignStudent(student);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Lesson lesson = model.getFilteredLessonList().get(lessonIndex.getZeroBased());
        Person student = model.getFilteredPersonList().get(studentIndex.getZeroBased());
        if (!assign(student, lesson)) {
            throw new CommandException(MESSAGE_ALREADY_ENROLLED);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentIndex, lessonIndex));
    }
}
