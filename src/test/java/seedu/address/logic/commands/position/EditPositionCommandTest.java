package seedu.address.logic.commands.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalHireLah.getTypicalHireLah;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.position.EditPositionCommand.EditPositionDescriptor;
import seedu.address.model.HireLah;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.position.Position;
import seedu.address.testutil.EditPositionDescriptorBuilder;
import seedu.address.testutil.PositionBuilder;

/**
 * Contains integration tests (interaction with Model) and unit tests for EditPositionCommand
 */
public class EditPositionCommandTest {

    private Model model = new ModelManager(getTypicalHireLah(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Position editedPosition = new PositionBuilder().build();
        EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder(editedPosition).build();
        EditPositionCommand editCommand = new EditPositionCommand(INDEX_FIRST, descriptor);

        Model expectedModel = new ModelManager(new HireLah(model.getHireLah()), new UserPrefs());
        expectedModel.updatePosition(model.getFilteredPositionList().get(0), editedPosition);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPosition = Index.fromOneBased(model.getFilteredPositionList().size());
        Position lastPosition = model.getFilteredPositionList().get(indexLastPosition.getZeroBased());

        Position editedPosition = new PositionBuilder(lastPosition).withDescription(VALID_DESCRIPTION_NAME_JR_SWE)
                .withRequirements(VALID_REQUIREMENT_EXPERIENCE).build();

        EditPositionDescriptor descriptor =
                new EditPositionDescriptorBuilder(editedPosition).build();
        EditPositionCommand editCommand = new EditPositionCommand(indexLastPosition, descriptor);

        Model expectedModel = new ModelManager(new HireLah(model.getHireLah()), new UserPrefs());
        expectedModel.updatePosition(model.getFilteredPositionList().get(indexLastPosition.getZeroBased()),
                editedPosition);

        String expectedMessage = String.format(EditPositionCommand.MESSAGE_EDIT_POSITION_SUCCESS, editedPosition);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePositionUnfilteredList_failure() {
        Position firstPosition = model.getFilteredPositionList().get(INDEX_FIRST.getZeroBased());
        EditPositionDescriptor descriptor = new EditPositionDescriptorBuilder(firstPosition).build();
        EditPositionCommand editCommand = new EditPositionCommand(INDEX_SECOND, descriptor);
        assertCommandFailure(editCommand, model, EditPositionCommand.MESSAGE_DUPLICATE_POSITION);
    }

    @Test
    public void equals() {
        final EditPositionCommand standardCommand = new EditPositionCommand(INDEX_FIRST, DESC_JR_SWE);

        // same values -> returns true
        EditPositionDescriptor copyDescriptor = new EditPositionDescriptor(DESC_JR_SWE);
        EditPositionCommand commandWithSameValues = new EditPositionCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_SECOND, DESC_JR_SWE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPositionCommand(INDEX_FIRST, DESC_SR_FE_SWE)));
    }
}
