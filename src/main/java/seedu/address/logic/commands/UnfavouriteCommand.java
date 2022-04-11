package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModelMemento;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Role;
import seedu.address.model.tag.Tag;
import seedu.address.model.telegram.Telegram;

/**
 * Changes the favourite status of an existing person in the address book.
 */
public class UnfavouriteCommand extends Command {

    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the favourite status of a person identified by\n"
            + "       the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer greater than\n"
            + "            0 and less than 2147483648)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNFAVOURITE_PERSON_SUCCESS = "Removed From Favourites Person: %1$s";

    public static final String MESSAGE_DUPLICATE_PERSON = "This person is already removed from favourites.";

    private final Index targetIndex;
    private ModelMemento modelMemento;
    private Person personToUnfavourite;

    public UnfavouriteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        this.personToUnfavourite = lastShownList.get(targetIndex.getZeroBased());
        this.modelMemento = new ModelMemento();
        modelMemento.setModel(new ModelManager(model.makeCopy()));
        Person unfavouritedPerson = createUnfavouritedPerson(personToUnfavourite);

        if (personToUnfavourite.isSameFavouritePerson(unfavouritedPerson)
                && model.hasFavouritePerson(unfavouritedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToUnfavourite, unfavouritedPerson);
        return new CommandResult(String.format(MESSAGE_UNFAVOURITE_PERSON_SUCCESS, unfavouritedPerson));
    }

    @Override
    public CommandResult unExecute(Model model) throws CommandException {
        model.setAddressBook(this.modelMemento.getModel().getAddressBook());
        return new CommandResult("Un-favourite contact.");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnfavouriteCommand // instanceof handles nulls
                && targetIndex.equals(((UnfavouriteCommand) other).targetIndex)); // state check
    }

    private static Person createUnfavouritedPerson(Person personToUnfavourite) {
        assert personToUnfavourite != null;

        Name updatedName = personToUnfavourite.getName();
        Phone updatedPhone = personToUnfavourite.getPhone();
        Email updatedEmail = personToUnfavourite.getEmail();
        Faculty updatedFaculty = personToUnfavourite.getFaculty();
        Role updatedRole = personToUnfavourite.getRole();
        Telegram updatedTelegram = personToUnfavourite.getTelegram();
        Favourite updatedFavourite = new Favourite(false); // edit command does not allow editing favourite status
        Set<Tag> updatedTags = personToUnfavourite.getTags();


        return new Person(updatedName, updatedPhone, updatedEmail, updatedFaculty, updatedRole,
                updatedTelegram, updatedFavourite, updatedTags);
    }
}
