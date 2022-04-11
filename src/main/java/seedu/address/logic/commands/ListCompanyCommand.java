package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEARCH_TYPE;

import seedu.address.commons.core.SearchTypeUtil.SearchType;
import seedu.address.model.Model;

public class ListCompanyCommand extends ListCommand {

    public static final String COMMAND_WORD = "listc";

    public static final String MESSAGE_SUCCESS = "Listed all%s companies";

    public static final String MESSAGE_USAGE = "Lists companies. The default setting is to list "
            + "only unarchived companies, but you can choose to include archived ones.\n"
            + "Parameters: [ " + PREFIX_SEARCH_TYPE + "SEARCH_TYPE] "
            + "(must be one of 'unarchived', 'archived', or 'all')\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SEARCH_TYPE + "unarchived";

    public ListCompanyCommand(SearchType searchType) {
        super(searchType);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        //Show all the events and empty out all the temporary list for events and companies
        model.showCompanyList(getPredicate());
        return new CommandResult(String.format(MESSAGE_SUCCESS, getSuccessMessage()),
                false, false, false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCompanyCommand // instanceof handles nulls
                && searchType.equals(((ListCompanyCommand) other).searchType)); // state check
    }
}
