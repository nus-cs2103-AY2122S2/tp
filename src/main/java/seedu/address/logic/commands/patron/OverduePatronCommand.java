package seedu.address.logic.commands.patron;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all patrons in LibTask to the user.
 */
public class OverduePatronCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Listed all patrons with overdue books";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatronList(x -> model.hasOverdueBooks(x));
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
