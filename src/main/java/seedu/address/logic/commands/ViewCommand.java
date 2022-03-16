package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_NO_INTERVIEWS_IN_SYSTEM = "No interviews scheduled yet!";

    public static final String MESSAGE_SUCCESS = "Listed ";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String message = model.getInterviewSchedule().getInterviewList().size() > 0
                ? model.getInterviewSchedule().getInterviewList().stream().map(Object::toString)
                .collect(Collectors.joining("\n"))
                : MESSAGE_NO_INTERVIEWS_IN_SYSTEM;
        return new CommandResult(message);
    }
}
