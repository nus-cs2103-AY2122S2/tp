package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTKEY;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Reorders all persons in TAlent Assistant™ based on the sort key provided.
 * The sort key will point to a specific candidate field.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all persons by the field specified "
            + "and displays them as a list with index numbers.\n"
            + " The search can be conducted only on a specific field in persons' description by specifying the"
            + PREFIX_SORTKEY + " SORTKEY argument.\n"
            + "Parameters: " + PREFIX_SORTKEY + "SORTKEY \n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SORTKEY + "name";

    private final Comparator<Person> sortComparator;
    private final String sortKey;

    public SortCommand(Comparator<Person> sortComparator, String sortKey) {
        this.sortComparator = sortComparator;
        this.sortKey = sortKey;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedPersonList(sortComparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand
                && sortKey.equals(((SortCommand) other).sortKey)); // state check
    }
}
