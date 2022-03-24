package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.Model;
import seedu.address.model.pet.FilterByContainsFilterWordPredicate;
import seedu.address.model.pet.NameContainsKeywordsPredicate;
import seedu.address.model.pet.OwnerNameContainsFilterWordPredicate;

/**
 * Filters all pets in address book by keywords provided.
 * Keyword matching is case-insensitive.
 * Keywords can only be from a defined set.
 */
public class FilterCommand extends Command{

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all pets by "
            + "the specified field followed by filter/ search word (case-insensitive)"
            + "and displays them as a list with index numbers.\n"
            + "PARAMETERS: FILTERBY [FILTER WORD] \n"
            + "FILTERBY = { 'byDate/', 'today/', 'byOwner/', 'byTag/' } \n"
            + "'today/' does not require any additional filter word\n"
            + "Example: " + COMMAND_WORD + " tag/ " + "golden retriever\n"
            + "Example: " + COMMAND_WORD + " today";

    private final FilterByContainsFilterWordPredicate predicate;

    public FilterCommand(FilterByContainsFilterWordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPetList(predicate);
        return new CommandResult(
                String.format(Messages.FILTER_MESSAGE_SUCCESS, model.getFilteredPetList().size()));
    }

}
