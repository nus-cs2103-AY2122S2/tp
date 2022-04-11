package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class AddImageCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddImageCommand(null));
    }

    @Test
    public void constructor_negativeIndex_throwsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> new AddImageCommand(Index.fromZeroBased(-1)));
    }

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        assertThrows(CommandException.class, () ->
                new AddImageCommand(Index.fromZeroBased(model.getSortedPersonList().size())).execute(model));
    }

    @Test
    public void execute_detailedViewConstructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddImageCommand().execute(model));
    }
}
