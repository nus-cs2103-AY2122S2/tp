package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sort list of persons by name.
 */
public class SortCompanyCommand extends Command {

    public static final String COMMAND_WORD = "sortedp";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD + ": Sorted list of persons by name";

    private boolean ascending;

    public SortCompanyCommand(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.sortCompanyListByName(ascending);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
