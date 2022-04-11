package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ListTaskCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all tasks!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS, getType());
    }

    @Override
    public Type getType() {
        return TASK;
    }
}


