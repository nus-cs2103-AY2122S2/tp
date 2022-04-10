package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPersonCommand extends ListCommand {

    public static final String COMMAND_WORD = "listp";

    public static final String MESSAGE_SUCCESS = "Listed all%s persons";

    public static final String MESSAGE_USAGE = "Lists persons. The default setting is to list "
            + "only unarchived persons, but you can choose to include archived ones.\n"
            + "Parameters: [ " + PREFIX_SEARCH_TYPE + "SEARCH_TYPE] "
            + "(must be one of 'unarchived', 'archived', or 'all')\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SEARCH_TYPE + "unarchived";

    public ListPersonCommand(SearchType searchType) {
        super(searchType);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the persons and empty out all the temporary list for events and companies
        model.showPersonList(getPredicate());
        return new CommandResult(String.format(MESSAGE_SUCCESS, getSuccessMessage()),
                false, false, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListPersonCommand // instanceof handles nulls
                && searchType.equals(((ListPersonCommand) other).searchType)); // state check
    }
}
