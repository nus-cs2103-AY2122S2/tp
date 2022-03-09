package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Lists all events in the address book to the user.
 */
public class ShowEventsCommand extends Command {

    public static final String COMMAND_WORD = "showevents";

    public static final String MESSAGE_SUCCESS = "Listed all events";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof ShowEventsCommand);
    }
}
