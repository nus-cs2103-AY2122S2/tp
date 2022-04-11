package seedu.trackbeau.logic.commands;

import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.ui.Panel;

public class ClearCommandTest {

    @Test
    public void execute_emptyTrackBeau_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, Panel.NO_CHANGE);
    }

    @Test
    public void execute_nonEmptyTrackBeau_success() {
        Model model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
        expectedModel.setTrackBeau(new TrackBeau());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel, Panel.NO_CHANGE);
    }

}
