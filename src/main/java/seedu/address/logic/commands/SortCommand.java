package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.PersonComparator;

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
            + ": Sorts the list of persons by the specified keywords (case-insensitive) in the order specified.\n"
            + "Parameters: [" + SORT_REVERSE + "]KEYWORD [[" + SORT_REVERSE + "]MORE_KEYWORDS]...\n"
            + "Prefixing a keyword with " + SORT_REVERSE + " will reverse the sorting order for that keyword.\n"
            + "List of supported keywords: " + SORT_BY_NAME + ", " + SORT_BY_PHONE + ", " + SORT_BY_EMAIL + ", "
            + SORT_BY_FAVOURITE + ", " + SORT_BY_ADDRESS + ", " + SORT_BY_USER_TYPE + ", " + SORT_BY_NUM_PROPERTIES
            + "\n"
            + "Example: " + COMMAND_WORD + " " + SORT_BY_NAME + " " + SORT_BY_PHONE + " " + SORT_REVERSE
            + SORT_BY_EMAIL;

    private final PersonComparator comparator;

    public SortCommand(PersonComparator comparator) {
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
