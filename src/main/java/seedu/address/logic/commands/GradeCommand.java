package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.Grade;
import seedu.address.model.student.Student;

public class GradeCommand extends Command {

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
