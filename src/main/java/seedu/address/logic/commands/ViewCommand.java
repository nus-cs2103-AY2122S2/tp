package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_SUCCESS = "Listed all candidates in the system!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String message = model.getInterviewSchedule().getInterviewList().size() > 0
                ? model.getInterviewSchedule().getInterviewList().stream().map(Object::toString)
                .collect(Collectors.joining(", \n"))
                : Messages.MESSAGE_NO_CANDIDATES_IN_SYSTEM;
        return new CommandResult(message);
    }
}
