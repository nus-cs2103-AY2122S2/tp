package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Sorts all persons in the hustle book according to whether they are flagged and prevDateMet.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons based on parameters given.\n"
            + "Parameters: PARAMETER \n"
            + "Example: " + COMMAND_WORD + "name";

    public static final String MESSAGE_SUCCESS = "Persons are now sorted by date";

    private final Comparator<Person> sortComparator;
    public SortCommand(Comparator<Person> sortComparator) {
        this.sortComparator = sortComparator;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortPersonListBy(sortComparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
