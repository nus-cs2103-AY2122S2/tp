package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;

public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": grades an assessment for a group of students\n"
            + "\tParameters: {a/ASSESSMENT_INDEX | sn/SIMPLE_NAME m/MODULE_INDEX}"
            + " s/all|STUDENT_INDEXES|STUDENT_IDS [g/GRADE]\n"
            + "\tExamples: \n"
            + "\t\t" + COMMAND_WORD + "sn/lab1 m/1 s/all g/1\n"
            + "\t\t" + COMMAND_WORD + "a/1 s/1,2,3,4,5,6\n"
            + "\t\t" + COMMAND_WORD + "a/1 s/e0123456,e0234567 g/1\n";

    private Assessment assessment;
    private Optional<Grade> grade;
    private List<Student> students;

    /**
     * Creates an EnrollCommand to enroll the students into class group and module
     */
    public GradeCommand(Assessment assessment, Optional<Grade> grade, List<Student> students) {
        this.assessment = assessment;
        this.grade = grade;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) {
        String result = "";
        return new CommandResult(result);
    }
}
