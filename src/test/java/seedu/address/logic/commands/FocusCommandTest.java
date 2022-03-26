package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;



public class FocusCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void constructor_nullComparator_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FocusCommand(null));
    }

    @Test
    public void equals() {
        FocusCommand fc1 = new FocusCommand(Index.fromZeroBased(1));
        FocusCommand fc2 = new FocusCommand(Index.fromZeroBased(1));

        // same object -> returns true
        assertTrue(fc1.equals(fc2));

        // null -> returns false
        assertFalse(fc1.equals(null));

        // different sort key -> returns false
        assertFalse(fc2.equals(null));
    }

}
