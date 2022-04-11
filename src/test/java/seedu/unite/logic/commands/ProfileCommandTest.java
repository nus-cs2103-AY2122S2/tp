package seedu.unite.logic.commands;

import static seedu.unite.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.unite.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.unite.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.unite.testutil.TypicalIndexes.INDEX_PERSON_NOT_EXIST;
import static seedu.unite.testutil.TypicalPersons.getTypicalUnite;

import org.junit.jupiter.api.Test;

import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.ModelManager;
import seedu.unite.model.UserPrefs;

public class ProfileCommandTest {
    private final Model model = new ModelManager(getTypicalUnite(), new UserPrefs());

    @Test
    public void execute_validIndex_displaysProfile() throws CommandException {
        ProfileCommand profileCommand = new ProfileCommand(INDEX_FIRST_PERSON);

        ModelManager expectedModel = new ModelManager(model.getUnite(), new UserPrefs());
        CommandResult expectedCommandResult = profileCommand.execute(expectedModel);

        assertCommandSuccess(profileCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ProfileCommand profileCommand = new ProfileCommand(INDEX_PERSON_NOT_EXIST);

        assertCommandFailure(profileCommand, model, MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }
}
