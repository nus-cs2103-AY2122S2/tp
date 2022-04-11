package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonIsFavouriteContactPredicate;

/**
 * Finds and lists all persons in address book who are favourite contacts.
 */
public class ListFavouritesCommand extends Command {

    public static final String COMMAND_WORD = "favourites";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all person who have been set as a "
            + "favourite contact and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    private static final PersonIsFavouriteContactPredicate predicate = new PersonIsFavouriteContactPredicate();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearDetailedContactView();
        model.clearActivatedTagList();
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListFavouritesCommand); // instanceof handles nulls
    }
}
