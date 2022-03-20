package seedu.address.logic.commands;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;

public class UnmarkCommand extends Command {

    private Index classGroupIndex;
    private Index weekIndex;
    private Optional<List<Index>> studentIndexes;
    private boolean isAllStudents;

    public UnmarkCommand(Index classGroupIndex, Index weekIndex, Optional<List<Index>> studentIndexes,
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
