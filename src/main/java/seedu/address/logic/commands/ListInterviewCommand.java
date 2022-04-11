package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.INTERVIEW;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ListInterviewCommand extends ListCommand {
    public static final String MESSAGE_SUCCESS = "Listed all interviews!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(MESSAGE_SUCCESS, getType());
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}


