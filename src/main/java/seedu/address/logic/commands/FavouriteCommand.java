package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Notes;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

public class FavouriteCommand extends Command implements DetailedViewExecutable {
    public static final String COMMAND_WORD = "fav";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_FAVOURITE_PERSON_SUCCESS = "Changed Person's Favourite Status: %1$s";

    private final Index targetIndex;

    /**
     * @param targetIndex of the person in the filtered person list to favourite.
     */
    public FavouriteCommand(Index targetIndex) {
        requireNonNull(targetIndex);

        this.targetIndex = targetIndex;
    }

    public FavouriteCommand() {
        this.targetIndex = null;
    }

    /**
     * Executes the Favourite command based on the {@code targetIndex} given.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the result of the command.
     * @throws CommandException if the index given is out of bounds.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getSortedPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToFavourite = lastShownList.get(targetIndex.getZeroBased());
        Favourite newFavouriteStatus = personToFavourite.isFavourite()
                ? Favourite.NOT_FAVOURITE
                : Favourite.IS_FAVOURITE;
        Person editedPerson = createFavouritedPerson(personToFavourite, newFavouriteStatus);

        model.setPerson(personToFavourite, editedPerson);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_PERSON_SUCCESS, editedPerson));
    }

    @Override
    public CommandResult executeInDetailedView(Model model) {
        requireNonNull(model);

        Person personToFavourite = model.getDetailedContactViewPerson();
        Favourite newFavouriteStatus = personToFavourite.isFavourite()
                ? Favourite.NOT_FAVOURITE
                : Favourite.IS_FAVOURITE;
        Person editedPerson = createFavouritedPerson(personToFavourite, newFavouriteStatus);

        model.setPerson(personToFavourite, editedPerson);
        model.setDetailedContactView(editedPerson);
        return new CommandResult(String.format(MESSAGE_FAVOURITE_PERSON_SUCCESS, editedPerson),
                CommandResult.SpecialCommandResult.DETAILED_VIEW);
    }

    private static Person createFavouritedPerson(Person personToEdit, Favourite newFavouriteStatus) {
        assert personToEdit != null;
        assert newFavouriteStatus != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        DeadlineList deadlines = personToEdit.getDeadlines();
        Notes notes = personToEdit.getNotes();
        HighImportance highImportanceStatus = personToEdit.getHighImportanceStatus();
        Set<Tag> tags = personToEdit.getTags();
        ImageDetailsList imageDetailsList = personToEdit.getImageDetailsList();

        return new Person(name, phone, email, address, deadlines,
                notes, tags, newFavouriteStatus, highImportanceStatus, imageDetailsList);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FavouriteCommand)) {
            return false;
        }

        FavouriteCommand e = (FavouriteCommand) other;
        return Objects.equals(targetIndex, e.targetIndex);
    }
}
