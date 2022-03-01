package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Person;

/**
 * Favourites an existing client in the address book by their index number
 */
public class FavouriteCommand extends Command {
    private final Index index;

    public static final String COMMAND_WORD = "favourite";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites a client selected "
            + "by the index number used in the last client listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS =
            "Favourited Client! Check your favourites list by navigating to it from the toolbar!";

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
        Person favouritedPerson = new Person(personToFavourite.getName(), personToFavourite.getPhone(), personToFavourite.getEmail(),
                new Favourite(true), personToFavourite.getAddress(), personToFavourite.getTags());
        model.setPerson(personToFavourite, favouritedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(generateSuccessMessage(favouritedPerson));
    }

    /**
     * Generates a command execution success message based on whether
     * the client is favourited or not
     * {@code personToFavourite}.
     */
    private String generateSuccessMessage(Person personToFavourite) {
        String message = "";
        if (personToFavourite.getFavourite().toString().equals("favourited")) {
            message = MESSAGE_FAVOURITE_PERSON_SUCCESS;
        }
        return String.format(message);
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
