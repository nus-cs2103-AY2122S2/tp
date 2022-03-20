package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.entity.EntityType;
import seedu.address.model.entity.exceptions.UnknownEntityException;
import seedu.address.model.student.Student;

public class MarkCommand extends Command {

    private Index classGroupIndex;
    private Index weekIndex;
    private Optional<List<Index>> studentIndexes;
    private boolean isAllStudents;

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
