package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class DisenrolCommand extends Command {

    private Index classGroupIndex;
    private Optional<List<Student>> students;

    /**
     * Creates an EnrollCommand to enroll the students into class group and module
     */
    public DisenrolCommand(Index classGroupIndex, Optional<List<Student>> students) {
        this.classGroupIndex = classGroupIndex;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) {
        String result = "";
        return new CommandResult(result);
    }
}
