package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    private Index classGroupIndex;
    private Index weekIndex;
    private Optional<List<Student>> students;

    /**
     * Creates a MarkCommand to mark the attendance for the students in the specified class and week.
     */
    public MarkCommand(Index classGroupIndex, Index weekIndex, Optional<List<Student>> students) {
        this.classGroupIndex = classGroupIndex;
        this.weekIndex = weekIndex;
        this.students = students;
    }

    @Override
    public CommandResult execute(Model model) {
        String result = "";
        return new CommandResult(result);
    }
}
