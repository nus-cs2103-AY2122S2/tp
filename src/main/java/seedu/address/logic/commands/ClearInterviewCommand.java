package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.INTERVIEW;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ClearInterviewCommand extends ClearCommand {
    public static final String MESSAGE_SUCCESS = "Interviews have all been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.resetInterviews();
        return new CommandResult(MESSAGE_SUCCESS, getType());
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}

