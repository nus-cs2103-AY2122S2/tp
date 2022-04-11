package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.person.Person;


/**
 * Lists all persons in the hustle book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows updated list of all clients.\n"
            + "Optional Parameters: [flag|unflag]";

    public static final String MESSAGE_SUCCESS = "Clients listed have been updated";

    public final Predicate<Person> listFilterPredicate;
    public ListCommand(Predicate<Person> listFilterPredicate) {
        this.listFilterPredicate = listFilterPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(listFilterPredicate);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && listFilterPredicate.equals(((ListCommand) other).listFilterPredicate)); // state check
    }
}
