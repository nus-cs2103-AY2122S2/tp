package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Favourites an existing client in the address book by their index number
 */
public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS =
            "Favourited Client %1$s! Open up Favourites window to see if he/she is in it!\n"
            + "If you have your Favourites window opened already, "
            + "close it and re-open it (via 'fw' command or 'Favourites' button) to refresh the data!";
    public static final String MESSAGE_UNFAVOURITE_PERSON_SUCCESS =
            "Unfavourited client %1$s! Check that he/she is removed from the Favourites window!\n"
            + "If you have your Favourites window opened already, "
            + "close it and re-open it (via 'fw' command or 'Favourites' button) to refresh the data!";;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites a selected client "
            + "by the index number used in the last client listing.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to favourite
     */
    public FavouriteCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavourite = lastShownList.get(index.getZeroBased());
        model.setFavouriteStatus(personToFavourite);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(personToFavourite));
    }

    /**
     * Generates a command execution success message based on whether
     * the client is favourited or not
     * {@code personToFavourite}.
     */
    private String generateSuccessMessage(Person personToFavourite) {
        String message = "";
        if (personToFavourite.getFavourite().getStatus()) {
            message = MESSAGE_FAVOURITE_PERSON_SUCCESS;
        } else {
            message = MESSAGE_UNFAVOURITE_PERSON_SUCCESS;
        }
        return String.format(message, personToFavourite.getName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FavouriteCommand)) {
            return false;
        }

        // state check
        FavouriteCommand e = (FavouriteCommand) other;
        return index.equals(e.index);
    }
}
