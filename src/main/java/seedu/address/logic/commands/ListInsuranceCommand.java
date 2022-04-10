package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INSURANCES;

import seedu.address.model.Model;

/**
 * Lists all insurances in the insurance book to the user.
 */
public class ListInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all insurances";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInsuranceList(PREDICATE_SHOW_ALL_INSURANCES);
        return new CommandResult(MESSAGE_SUCCESS, false, true, false,
                false, false, false, false, false);
    }
}
