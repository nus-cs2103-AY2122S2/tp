package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;

/**
 * Sort list of persons by name.
 */
public class SortPersonCommand extends SortCommand {

    public static final String COMMAND_WORD = "sortedp";

    public static final String MESSAGE_SUCCESS = COMMAND_WORD + ": Sorted list of persons by name";

    public SortPersonCommand(SearchType searchType, Ordering ordering) {
        super(searchType, ordering);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.sortPersonListByName(getOrdering(), getPredicate());
        return new CommandResult(MESSAGE_SUCCESS, false, false, true, false, false);
    }
}
