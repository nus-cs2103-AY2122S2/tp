package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.model.AddressBook;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.TypicalIndexes;
import woofareyou.testutil.TypicalPets;


class DietCommandTest {

    private static final String DIET_STUB = "Some diet";

    private Model model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addDietUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson).withDiet(DIET_STUB).build();

        DietCommand dietCommand = new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(editedPerson.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDietUnfilteredList_success() {
        Pet firstPerson = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(firstPerson).withDiet("").build();

        DietCommand dietCommand = new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(editedPerson.getDiet().toString()));

        String expectedMessage = String.format(DietCommand.MESSAGE_DELETE_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);

        Pet firstPerson = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());
        Pet editedPerson = new PetBuilder(model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased()))
                .withDiet(DIET_STUB).build();

        DietCommand dietCommand = new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(editedPerson.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPerson, editedPerson);

        CommandTestUtil.assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        DietCommand dietCommand = new DietCommand(outOfBoundIndex, new Diet(CommandTestUtil.VALID_DIET_BOB));

        CommandTestUtil.assertCommandFailure(dietCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**s
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        CommandTestUtil.showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPetList().size());

        DietCommand remarkCommand = new DietCommand(outOfBoundIndex, new Diet(CommandTestUtil.VALID_DIET_BOB));

        CommandTestUtil.assertCommandFailure(remarkCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final DietCommand standardCommand = new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(CommandTestUtil.VALID_DIET_AMY));
        // same values -> returns true
        DietCommand commandWithSameValues = new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(CommandTestUtil.VALID_DIET_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new DietCommand(TypicalIndexes.INDEX_SECOND_PET,
                new Diet(CommandTestUtil.VALID_DIET_AMY))));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new DietCommand(TypicalIndexes.INDEX_FIRST_PET,
                new Diet(CommandTestUtil.VALID_DIET_BOB))));
    }
}
