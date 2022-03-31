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
import woofareyou.model.pet.Pet;
import woofareyou.testutil.EditPetDescriptorBuilder;
import woofareyou.testutil.PetBuilder;
import woofareyou.testutil.TypicalIndexes;
import woofareyou.testutil.TypicalPets;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(TypicalPets.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Pet editedPet = new PetBuilder().build();
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(editedPet).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PET, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPet = Index.fromOneBased(model.getFilteredPetList().size());
        Pet lastPet = model.getFilteredPetList().get(indexLastPet.getZeroBased());

        PetBuilder petInList = new PetBuilder(lastPet);
        Pet editedPet = petInList.withName(CommandTestUtil.VALID_NAME_BOB).withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();

        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPet, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(lastPet, editedPet);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PET, new EditCommand.EditPetDescriptor());
        Pet editedPet = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);

        Pet petInFilteredList = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(petInFilteredList).withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PET,
                new EditPetDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePetUnfilteredList_failure() {
        Pet firstPet = model.getFilteredPetList().get(TypicalIndexes.INDEX_FIRST_PET.getZeroBased());
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder(firstPet).build();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_PET, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_duplicatePetFilteredList_failure() {
        CommandTestUtil.showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);

        // edit pet in filtered list into a duplicate in address book
        Pet petInList = model.getAddressBook().getPetList().get(TypicalIndexes.INDEX_SECOND_PET.getZeroBased());
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PET,
                new EditPetDescriptorBuilder(petInList).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_invalidPetIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        EditCommand.EditPetDescriptor descriptor = new EditPetDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPetIndexFilteredList_failure() {
        CommandTestUtil.showPetAtIndex(model, TypicalIndexes.INDEX_FIRST_PET);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPetList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPetDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_BOB).build());

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_PET, CommandTestUtil.DESC_AMY);

        // same values -> returns true
        EditCommand.EditPetDescriptor copyDescriptor = new EditCommand.EditPetDescriptor(CommandTestUtil.DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_PET, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_PET, CommandTestUtil.DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_PET, CommandTestUtil.DESC_BOB)));
    }

}
