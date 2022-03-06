package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.AB3Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.AB3Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(AB3Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
