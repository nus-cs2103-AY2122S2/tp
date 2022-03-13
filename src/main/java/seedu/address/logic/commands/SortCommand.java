package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String SORT_BY_NAME = "name";
    public static final String SORT_BY_PHONE = "phone";
    public static final String SORT_BY_EMAIL = "email";
    public static final String SORT_BY_FAVOURITE = "favourite";
    public static final String SORT_BY_ADDRESS = "address";
    public static final String SORT_BY_USER_TYPE = "usertype";
    public static final String SORT_BY_NUM_PROPERTIES = "num_property";
    public static final char SORT_REVERSE = '!';

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of persons by the specified fields (case-insensitive) in the order specified."
            + "Prefix the field with " + SORT_REVERSE + " to sort in reverse order.\n"
            + "Parameters: [FIELD]...\n"
            + "Allowed values for FIELD: " + SORT_BY_NAME + ", " + SORT_BY_PHONE + ", " + SORT_BY_EMAIL + ", "
            + SORT_BY_FAVOURITE + ", " + SORT_BY_ADDRESS + ", " + SORT_BY_USER_TYPE + ", " + SORT_BY_NUM_PROPERTIES
            + "\n"
            + "Example: " + COMMAND_WORD + " " + SORT_BY_NAME + " " + SORT_BY_PHONE + " " + SORT_REVERSE
            + SORT_BY_EMAIL;

    private final Comparator<Person> comparator;

    public SortCommand(Comparator<Person> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        model.updateSortedPersonList(comparator);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredAndSortedPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
