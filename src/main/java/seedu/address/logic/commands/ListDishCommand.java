package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DISHES;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListDishCommand extends Command {

    public static final String COMMAND_WORD = "listdish";

    public static final String MESSAGE_SUCCESS = "Listed all dishes";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredDishList(PREDICATE_SHOW_ALL_DISHES);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
