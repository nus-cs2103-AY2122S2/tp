package seedu.address.logic.commands.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2101;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LECTURE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showModuleAtIndex;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_FIRST_MODULE;
import static seedu.address.testutil.typical.TypicalIndexes.INDEX_SECOND_MODULE;
import static seedu.address.testutil.typical.TypicalLinkyTime.getTypicalLinkyTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.module.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.LinkyTime;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.testutil.module.EditModuleDescriptorBuilder;
import seedu.address.testutil.module.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditModuleCommand.
 */
public class EditModuleCommandTest {
    private final Model model = new ModelManager(getTypicalLinkyTime(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        final Module editedModule = new ModuleBuilder().build();
        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(editedModule).build();
        final EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE, descriptor);

        final String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        // This is for the side effect of editing the meetings that are dependent on this module
        if (editModuleCommand.hasDependentMeetings(expectedModel.getFilteredMeetingList(),
                model.getFilteredModuleList().get(0))) {
            editModuleCommand.editAssociatedMeetings(expectedModel, model.getFilteredModuleList().get(0), editedModule);
        }

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        final EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptor());
        final Module editedModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());

        final String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showModuleAtIndex(model, INDEX_SECOND_MODULE);

        final Module moduleInFilteredList = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        final Module editedModule = new ModuleBuilder(moduleInFilteredList).withCode("CS2102").build();
        final EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder().withCode("CS2102").build());

        final String expectedMessage = String.format(EditModuleCommand.MESSAGE_EDIT_MODULE_SUCCESS, editedModule);

        final Model expectedModel = new ModelManager(new LinkyTime(model.getLinkyTime()), new UserPrefs());
        expectedModel.setModule(model.getFilteredModuleList().get(0), editedModule);

        // This is for the side effect of editing the meetings that are dependent on this module
        if (editModuleCommand.hasDependentMeetings(expectedModel.getFilteredMeetingList(),
                model.getFilteredModuleList().get(0))) {
            editModuleCommand.editAssociatedMeetings(expectedModel, model.getFilteredModuleList().get(0), editedModule);
        }

        assertCommandSuccess(editModuleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateModuleUnfilteredList_failure() {
        final Module firstModule = model.getFilteredModuleList().get(INDEX_FIRST_MODULE.getZeroBased());
        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder(firstModule).build();
        final EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_SECOND_MODULE, descriptor);

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_duplicateModuleFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);

        // edit module in filtered list into a duplicate in LinkyTime
        final Module moduleInList = model.getLinkyTime().getModuleList().get(INDEX_SECOND_MODULE.getZeroBased());
        final EditModuleCommand editModuleCommand = new EditModuleCommand(INDEX_FIRST_MODULE,
                new EditModuleDescriptorBuilder(moduleInList).build());

        assertCommandFailure(editModuleCommand, model, EditModuleCommand.MESSAGE_DUPLICATE_MODULE);
    }

    @Test
    public void execute_invalidModuleIndexUnfilteredList_failure() {
        final Index outOfBoundIndex = Index.fromOneBased(model.getFilteredModuleList().size() + 1);
        final EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_LECTURE)
                .build();
        final EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of LinkyTime
     */
    @Test
    public void execute_invalidModuleIndexFilteredList_failure() {
        showModuleAtIndex(model, INDEX_FIRST_MODULE);
        final Index outOfBoundIndex = INDEX_SECOND_MODULE;
        // ensures that outOfBoundIndex is still in bounds of LinkyTime module list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLinkyTime().getModuleList().size());

        final EditModuleCommand editModuleCommand = new EditModuleCommand(outOfBoundIndex,
                new EditModuleDescriptorBuilder().withCode(VALID_MODULE_LECTURE).build());

        assertCommandFailure(editModuleCommand, model, Messages.MESSAGE_INVALID_MODULE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditModuleCommand standardCommand = new EditModuleCommand(INDEX_FIRST_MODULE, DESC_CS2103);

        // same values -> return true
        final EditModuleDescriptor copyDescriptor = new EditModuleDescriptor(DESC_CS2103);
        final EditModuleCommand commandWithSameValues = new EditModuleCommand(INDEX_FIRST_MODULE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> return true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> return false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_SECOND_MODULE, DESC_CS2103)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditModuleCommand(INDEX_FIRST_MODULE, DESC_CS2101)));
    }
}
