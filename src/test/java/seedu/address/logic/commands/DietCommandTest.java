package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIET_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DIET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Diet;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;



class DietCommandTest {

    private static final String REMARK_STUB = "Some remark";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addDietUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson).withDiet(REMARK_STUB).build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET, new Diet(editedPerson.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDietUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson).withDiet("").build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET,
                new Diet(editedPerson.getDiet().toString()));

        String expectedMessage = String.format(DietCommand.MESSAGE_DELETE_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet firstPerson = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased()))
                .withDiet(REMARK_STUB).build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET, new Diet(editedPerson.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        DietCommand dietCommand = new DietCommand(outOfBoundIndex, new Diet(VALID_DIET_BOB));

        assertCommandFailure(dietCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**s
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPetList().size());

        DietCommand remarkCommand = new DietCommand(outOfBoundIndex, new Diet(VALID_DIET_BOB));

        assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DietCommand standardCommand = new DietCommand(INDEX_FIRST_PET,
                new Diet(VALID_DIET_AMY));
        // same values -> returns true
        DietCommand commandWithSameValues = new DietCommand(INDEX_FIRST_PET,
                new Diet(VALID_DIET_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DietCommand(INDEX_SECOND_PET,
                new Diet(VALID_DIET_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new DietCommand(INDEX_FIRST_PET,
                new Diet(VALID_DIET_BOB))));
    }
}
