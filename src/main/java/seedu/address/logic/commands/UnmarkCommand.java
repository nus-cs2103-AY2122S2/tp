package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.MarkCommand.NONEXISTENT_CG;
import static seedu.address.logic.commands.MarkCommand.NONEXISTENT_WEEK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.classgroup.ClassGroup;
import seedu.address.model.entity.EntityType;
import seedu.address.model.student.Student;

//@@author EvaderFati
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_UNMARK_SUCCESS = "Successfully unmark given student(s) from %s(%s).";
    public static final String MESSAGE_STUDENT_NOT_ENROLLED = "Student(s) not enrolled:\n%s";
    public static final String MESSAGE_UNMARK_OTHERS = "Successfully unmark other student(s) from %s(%s).";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Unmarks the attendance(s) of the specified student(s)"
            + "belonging to the class group at the specified CLASS_GROUP_INDEX for the specified week.\n"
            + "\tParameters: " + PREFIX_CLASS_INDEX + "CLASS_GROUP_INDEX "
            + PREFIX_WEEK + "WEEK_INDEX "
            + PREFIX_STUDENT + "all|STUDENT_INDEXES|STUDENT_IDS "
            + "\n\tExamples: "
            + "\n\t\t1. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "all "
            + "\n\t\t2. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "1,2,3 "
            + "\n\t\t3. " + COMMAND_WORD + " "
            + PREFIX_CLASS_INDEX + "1 "
            + PREFIX_WEEK + "3 "
            + PREFIX_STUDENT + "e0123456,e0234567 \n";

    private Index classGroupIndex;
    private Index weekIndex;
    private List<Student> students;

    /**
     * Creates an UnmarkCommand to unmark the attendance for the students in the specified class and week.
     */
    public UnmarkCommand(Index classGroupIndex, Index weekIndex, List<Student> students) {
        this.classGroupIndex = classGroupIndex;
        this.weekIndex = weekIndex;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String result = "";
        List<ClassGroup> cgList = model.getUnfilteredClassGroupList();

        if (classGroupIndex.getZeroBased() >= cgList.size()) {
            throw new CommandException(String.format(NONEXISTENT_CG, classGroupIndex));
        }

        ClassGroup classGroupToEdit = cgList.get(classGroupIndex.getZeroBased());
        ClassGroup classGroup = new ClassGroup(classGroupToEdit);
        if (weekIndex.getZeroBased() >= classGroup.getLessons().size()) {
            throw new CommandException(String.format(NONEXISTENT_WEEK, weekIndex));
        }

        List<Student> notUnmarkedStudents = classGroup.unmarkAttendance(weekIndex, students);
        model.setEntity(classGroupToEdit, classGroup);
        if (notUnmarkedStudents.size() == 0) {
            return new CommandResult(String.format(MESSAGE_UNMARK_SUCCESS,
                    classGroup.getClassGroupId(), classGroup.getClassGroupType()));
        }
        String notEnrolledStudents = notUnmarkedStudents.stream().map(student ->
                        String.format("\t%s (%s)\n", student.getName(), student.getStudentId()))
                .reduce("", (x, y) -> x + y);
        result = String.format(MESSAGE_STUDENT_NOT_ENROLLED, notEnrolledStudents);
        if (notUnmarkedStudents.size() != students.size()) {
            result += String.format(MESSAGE_UNMARK_OTHERS,
                    classGroup.getClassGroupId(), classGroup.getClassGroupType());
        }
        return new CommandResult(result, EntityType.CLASS_GROUP);
    }
}
