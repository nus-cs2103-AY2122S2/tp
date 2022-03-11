package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.CovidStatus;

/**
 * Lists all persons in the address book to the user.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons who are of the specified health "
            + "status (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: [COVID STATUS]...\n"
            + "Example: " + COMMAND_WORD + " POSITIVE";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private CovidStatus status;

    public FilterCommand(String status) {
        requireNonNull(status);

        this.status = new CovidStatus(status);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(p -> p.isStatus(status));
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        // state check
        FilterCommand e = (FilterCommand) other;
        return status.equals(e.status);
    }
}
