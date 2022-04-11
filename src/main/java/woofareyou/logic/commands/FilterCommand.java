package woofareyou.logic.commands;

import static java.util.Objects.requireNonNull;

import woofareyou.commons.core.Messages;
import woofareyou.commons.util.FilterUtil;
import woofareyou.model.Model;
import woofareyou.model.filter.FilterByContainsFilterWordPredicate;

/**
 * Filters all pets in WoofAreYou based on the field provided by the pet owner.
 * Filter word matching is case-insensitive.
 * Fields can only be from a defined set.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all pets by "
            + "the specified field followed by filter/ search word (case-insensitive)"
            + "and displays them as a list with index numbers.\n"
            + "PARAMETERS: FIELD [FILTER WORD] \n"
            + "FIELD = { 'byDate/', 'byOwner/', 'byTag/', byApp/ } \n"
            + "Filter word following 'byDate/' or 'byApp/' can be 'today' or 'dd-MM-yyyy'.\n"
            + "Example: " + COMMAND_WORD + " tag/" + "golden retriever\n"
            + "Example: " + COMMAND_WORD + " byDate/today";

    public static final String INVALID_FILTER_FIELD = "The field you are trying to filter by is invalid!";

    private final FilterByContainsFilterWordPredicate predicate;

    public FilterCommand(FilterByContainsFilterWordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(predicate);
        return new CommandResult(
                String.format(Messages.FILTER_MESSAGE_SUCCESS, model.getFilteredPetList().size(),
                        FilterUtil.successMessageMatch(predicate)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }

}
