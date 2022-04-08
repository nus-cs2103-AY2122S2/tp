package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static woofareyou.logic.commands.CommandTestUtil.DESC_AMY;
import static woofareyou.logic.commands.CommandTestUtil.DESC_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static woofareyou.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import org.junit.jupiter.api.Test;

import woofareyou.commons.core.Messages;
import woofareyou.commons.core.index.Index;
import woofareyou.logic.commands.EditCommand.EditPetDescriptor;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.EditPetDescriptorBuilder;
import woofareyou.testutil.PetBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Pet editedPet = new PetBuilder().build();
        EditPetDescriptor descriptor = new EditPetDescriptorBuilder(editedPet).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPet = Index.fromOneBased(model.getFilteredPetList().size());
        Pet lastPet = model.getFilteredPetList().get(indexLastPet.getZeroBased());

        PetBuilder petInList = new PetBuilder(lastPet);
        Pet editedPet = petInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTag(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPet, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(lastPet, editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET, new EditPetDescriptor());
        Pet editedPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet petInFilteredList = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(petInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET,
                new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePetUnfilteredList_failure() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(firstPet).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PET, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_duplicatePetFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        // edit pet in filtered list into a duplicate in pet book
        Pet petInList = model.getPetBook().getPetList().get(INDEX_SECOND_PET.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET,
                new EditPetDescriptorBuilder(petInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_duplicatePetDifferentTagUnfilteredList_failure() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(firstPet).withTag("poodle").build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PET, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_duplicatePetDifferentTagFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet petInList = model.getPetBook().getPetList().get(INDEX_SECOND_PET.getZeroBased());
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(petInList).withTag("poodle").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_invalidPetIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of pet book
     */
    @Test
    public void execute_invalidPetIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of pet book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetBook().getPetList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PET, DESC_AMY);

        // same values -> returns true
        EditPetDescriptor copyDescriptor = new EditPetDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PET, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PET, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PET, DESC_BOB)));
    }

}
