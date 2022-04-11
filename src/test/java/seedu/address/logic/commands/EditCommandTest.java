package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplications.getTypicalInternApplyMemory;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;
import seedu.address.testutil.EditApplicationDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalInternApplyMemory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Application editedApplication = new ApplicationBuilder().build(); // build application
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder(editedApplication).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new InternApplyMemory(model.getInternApplyMemory()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application lastApplication = model.getFilteredApplicationList().get(indexLastApplication.getZeroBased());

        ApplicationBuilder applicationInList = new ApplicationBuilder(lastApplication);
        Application editedApplication = applicationInList.withName(VALID_NAME_GARENA).withPhone(VALID_PHONE_GARENA)
                .withTags(VALID_TAG_LOCAL).build();

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withName(VALID_NAME_GARENA)
                .withPhone(VALID_PHONE_GARENA).withTags(VALID_TAG_LOCAL).build();
        EditCommand editCommand = new EditCommand(indexLastApplication, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new InternApplyMemory(model.getInternApplyMemory()), new UserPrefs());
        expectedModel.setApplication(lastApplication, editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION, new EditApplicationDescriptor());
        Application editedApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new InternApplyMemory(model.getInternApplyMemory()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        model.updateFilteredApplicationList(Model.PREDICATE_SHOW_ALL_APPLICATIONS);

        Application applicationInFilteredList = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        Application editedApplication = new ApplicationBuilder(applicationInFilteredList)
                .withName(VALID_NAME_GARENA).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder().withName(VALID_NAME_GARENA).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_APPLICATION_SUCCESS, editedApplication);

        Model expectedModel = new ModelManager(new InternApplyMemory(model.getInternApplyMemory()), new UserPrefs());
        expectedModel.setApplication(model.getFilteredApplicationList().get(0), editedApplication);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateApplicationUnfilteredList_failure() {
        Application firstApplication = model.getFilteredApplicationList().get(INDEX_FIRST_APPLICATION.getZeroBased());
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withName(firstApplication.getName().fullName).withJobTitle(firstApplication.getJobTitle().value)
                .withTags("REMOVETAGS").build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_APPLICATION, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_duplicateApplicationFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        // edit application in filtered list into a duplicate in intern apply memory
        Application applicationInList = model.getInternApplyMemory().getApplicationList()
                .get(INDEX_SECOND_APPLICATION.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_APPLICATION,
                new EditApplicationDescriptorBuilder(applicationInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

    @Test
    public void execute_invalidApplicationIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withName(VALID_NAME_GARENA).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of intern apply memory
     */
    @Test
    public void execute_invalidApplicationIndexFilteredList_failure() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of intern apply memory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternApplyMemory().getApplicationList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditApplicationDescriptorBuilder().withName(VALID_NAME_GARENA).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_APPLICATION, DESC_SHOPEE);

        // same values -> returns true
        EditApplicationDescriptor copyDescriptor = new EditApplicationDescriptor(DESC_SHOPEE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_APPLICATION, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_APPLICATION, DESC_SHOPEE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_APPLICATION, DESC_GARENA)));
    }

}
