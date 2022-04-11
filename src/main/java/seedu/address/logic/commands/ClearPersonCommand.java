package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INTERVIEW_LIST_NOT_EMPTY;
import static seedu.address.logic.parser.Type.PERSON;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;

public class ClearPersonCommand extends ClearCommand {
    public static final String MESSAGE_SUCCESS = "Applicants have all been cleared!";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.getFilteredInterviewList().isEmpty()) {
            model.resetPersons();
        } else {
            throw new CommandException(MESSAGE_INTERVIEW_LIST_NOT_EMPTY);
        }
        return new CommandResult(MESSAGE_SUCCESS, getType());
    }

    @Override
    public Type getType() {
        return PERSON;
    }
}
