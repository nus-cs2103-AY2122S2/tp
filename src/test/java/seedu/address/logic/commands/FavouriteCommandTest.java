package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

class FavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_favouriteOnValidIndexUnfilteredList_success() throws CommandException {
        Person personToFavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        CommandResult commandResult = new FavouriteCommand(INDEX_FIRST_PERSON).execute(expectedModel);

        /* Runs test based on the starting favourite status of the first person in the address book */
        if (!personToFavourite.getFavourite().getStatus()) {
            assertEquals(String.format(FavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS, personToFavourite.getName()), commandResult.getFeedbackToUser());
        } else {
            assertEquals(String.format(FavouriteCommand.MESSAGE_FAVOURITE_PERSON_SUCCESS, personToFavourite.getName()), commandResult.getFeedbackToUser());
        }
    }

    @Test
    public void execute_favouriteOnInvalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FavouriteCommand favouriteCommand = new FavouriteCommand(outOfBoundIndex);

        assertCommandFailure(favouriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

    }
}