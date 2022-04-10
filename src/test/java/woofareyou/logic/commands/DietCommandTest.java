package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.VALID_DIET_AMY;
import static woofareyou.logic.commands.CommandTestUtil.VALID_DIET_BOB;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;



class DietCommandTest {

    private static final String DIET_STUB = "Some diet";

    private Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @Test
    public void execute_addDietUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet).withDiet(DIET_STUB).build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET, new Diet(editedPet.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteDietUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(firstPet).withDiet("").build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET,
                new Diet(editedPet.getDiet().toString()));

        String expectedMessage = String.format(DietCommand.MESSAGE_DELETE_DIET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased()))
                .withDiet(DIET_STUB).build();

        DietCommand dietCommand = new DietCommand(INDEX_FIRST_PET, new Diet(editedPet.getDiet().value));

        String expectedMessage = String.format(DietCommand.MESSAGE_ADD_DIET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(firstPet, editedPet);

        assertCommandSuccess(dietCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPetIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        DietCommand dietCommand = new DietCommand(outOfBoundIndex, new Diet(VALID_DIET_BOB));

        assertCommandFailure(dietCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**s
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of pet book
     */
    @Test
    public void execute_invalidPetIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of pet book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetBook().getPetList().size());

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
