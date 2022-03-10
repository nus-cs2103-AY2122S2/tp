package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all candidates in the system!";
    public static final String MESSAGE_NO_CANDIDATES_IN_SYSTEM = "There are no candidates in the system!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        String message = model.getAddressBook().getPersonList().size() > 0
                ? MESSAGE_SUCCESS
                : MESSAGE_NO_CANDIDATES_IN_SYSTEM;
        return new CommandResult(message);
    }
}
