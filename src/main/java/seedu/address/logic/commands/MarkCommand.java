package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

public class MarkCommand extends Command {

    private Index classGroupIndex;
    private Index weekIndex;
    private Optional<List<Index>> studentIndexes;
    private boolean isAllStudents;

    /**
     * Creates a MarkCommand to mark the attendance for the students in the specified class and week.
     */
    public MarkCommand(Index classGroupIndex, Index weekIndex, Optional<List<Index>> studentIndexes,
                       boolean isAllStudents) {
        this.classGroupIndex = classGroupIndex;
        this.weekIndex = weekIndex;
        this.studentIndexes = studentIndexes;
        this.isAllStudents = isAllStudents;
    }

    @Override
    public CommandResult execute(Model model) {
        String result = "";
        return new CommandResult(result);
    }
}
