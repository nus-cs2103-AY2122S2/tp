package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.trackbeau.model.Model;

/**
 * Lists all persons in the trackbeau book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
