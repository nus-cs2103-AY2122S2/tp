package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDERING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;

/**
 * Sort list of persons by name.
 */
public class SortPersonCommand extends SortCommand {

    public static final String COMMAND_WORD = "sortp";

    public static final String MESSAGE_SUCCESS = "Sorted all people%s";

    public static final String MESSAGE_USAGE = "Sorts people. The default setting is to sort "
            + "only unarchived people in ascending order, but you can choose to include archived ones "
            + "or sort in descending order.\n"
            + "Parameters: [ " + PREFIX_SEARCH_TYPE + "SEARCH_TYPE] "
            + "(must be one of 'unarchived', 'archived', or 'all') "
            + "[" + PREFIX_ORDERING + "ORDERING] (must be one of 'ascending' or 'descending')\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SEARCH_TYPE + "unarchived " + PREFIX_ORDERING + "descending";

    public SortPersonCommand(SearchType searchType, Ordering ordering) {
        super(searchType, ordering);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.sortPersonListByName(getOrdering(), getPredicate());
        return new CommandResult(String.format(MESSAGE_SUCCESS, getSuccessMessage()),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPersonCommand // instanceof handles nulls
                && searchType.equals(((SortPersonCommand) other).searchType)
                && ordering.equals(((SortPersonCommand) other).ordering)); // state check
    }
}
