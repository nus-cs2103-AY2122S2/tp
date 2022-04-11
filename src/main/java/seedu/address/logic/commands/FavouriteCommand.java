package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.ReminderPersons;

/**
 * Favourites an existing client in the address book by their index number
 */
public class FavouriteCommand extends Command {
    public static final String COMMAND_WORD = "favourite";
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS =
            "Favourited Client %1$s! Open up Favourites window to see if he/she is in it!\n"
            + "If the Favourites window is already open, close it and re-open it\n"
            + "(via 'fw' command or 'Favourites' button from the 'File' tab, or press 'F3' key) to refresh the data!";
    public static final String MESSAGE_UNFAVOURITE_PERSON_SUCCESS =
            "Unfavourited Client %1$s! Check that he/she is removed from the Favourites window!\n"
            + "If the Favourites window is already open, close it and re-open it\n"
            + "(via 'fw' command or 'Favourites' button from the 'File' tab , or press 'F3' key) to refresh the data!";;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourite/Unfavourite a selected client "
            + "by the index number used in the displayed person list.\n"
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
        List<Person> lastShownList = model.getFilteredAndSortedPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        // retrieve the person whose favourite status will be toggled
        Person personToFavourite = lastShownList.get(index.getZeroBased());
        // update the Favourite status of a person with a Reminder
        ReminderPersons.toggleFavouriteForReminder(personToFavourite);

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
        if (!personToFavourite.getFavourite().getStatus()) {
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
