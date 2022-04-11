package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;

import org.junit.jupiter.api.Test;


public class AddNoteCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditCommand.EditClientDescriptor descriptorWithSameValues = new EditCommand.EditClientDescriptor(DESC_AMY);
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(null, descriptorWithSameValues));
    }

    @Test
    public void constructor_nullEditClientDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddNoteCommand(INDEX_FIRST_CLIENT, null));
    }
}
