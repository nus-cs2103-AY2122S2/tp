package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.model.Model;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.student.Student;

public class GradeCommand extends Command {

    private Assessment assessment;
    private Optional<List<Student>> students;
    private boolean isAllStudents;

    /**
     * Creates an EnrollCommand to enroll the students into class group and module
     */
    public GradeCommand(Assessment assessment, Optional<List<Student>> students,
                        boolean isAllStudents) {
        this.assessment = assessment;
        this.students = students;
        this.isAllStudents = isAllStudents;
    }

    @Override
    public CommandResult execute(Model model) {
        String result = "";
        return new CommandResult(result);
    }
}
