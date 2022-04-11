package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;

public class DeleteNoteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditCommand.EditClientDescriptor descriptorWithSameValues = new EditCommand.EditClientDescriptor(DESC_AMY);
        assertThrows(NullPointerException.class, () -> new DeleteNoteCommand(null, descriptorWithSameValues));
    }

    @Test
    public void constructor_nullEditClientDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteNoteCommand(INDEX_FIRST_CLIENT, null));
    }
}
