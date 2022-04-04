package seedu.ibook.logic.commands.item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_A;
import static seedu.ibook.logic.commands.CommandTestUtil.ITEM_DESCRIPTOR_B;
import static seedu.ibook.logic.commands.item.UpdateItemCommand.UpdateItemDescriptor;
import static seedu.ibook.testutil.Assert.assertThrows;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_FIRST_PRODUCT_FIRST_ITEM;
import static seedu.ibook.testutil.TypicalIndexes.INDEX_SECOND_PRODUCT_FIRST_ITEM;
import static seedu.ibook.testutil.TypicalProducts.getTypicalIBookWithItems;

import org.junit.jupiter.api.Test;

import seedu.ibook.logic.commands.ClearCommand;
import seedu.ibook.model.Model;
import seedu.ibook.model.ModelManager;
import seedu.ibook.model.UserPrefs;

class UpdateItemCommandTest {

    private final Model model = new ModelManager(getTypicalIBookWithItems(), new UserPrefs());

    @Test
    void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, null));
        assertThrows(NullPointerException.class, () -> new UpdateItemCommand(null, new UpdateItemDescriptor()));
    }

    @Test
    void execute() {
    }

    @Test
    void equals() {
        final UpdateItemCommand standardCommand = new UpdateItemCommand(
                INDEX_FIRST_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_A);

        // same values -> returns true
        UpdateItemDescriptor copyDescriptor = new UpdateItemDescriptor(ITEM_DESCRIPTOR_A);
        UpdateItemCommand commandWithSameValues = new UpdateItemCommand(
                INDEX_FIRST_PRODUCT_FIRST_ITEM, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new UpdateItemCommand(INDEX_SECOND_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new UpdateItemCommand(INDEX_FIRST_PRODUCT_FIRST_ITEM, ITEM_DESCRIPTOR_B));
    }
}
