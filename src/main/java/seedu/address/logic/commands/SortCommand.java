package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "Sorted all pets!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPetList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
