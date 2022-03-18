package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the hustle book according to whether they are flagged and prevDateMet.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Persons are now sorted by date";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonListByDate();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
