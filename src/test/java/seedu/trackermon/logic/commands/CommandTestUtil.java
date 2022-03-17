package seedu.trackermon.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.logic.commands.exceptions.CommandException;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.ShowList;
import seedu.trackermon.model.show.NameContainsKeywordsPredicate;
import seedu.trackermon.model.show.Show;

/**
 * Contains helper methods for testing commands.
 */

public class CommandTestUtil {

    public static final String VALID_NAME_YOU = "You";
    public static final String VALID_NAME_ME = "ME";
    public static final String VALID_STATUS_COMPLETED = "completed";
    public static final String VALID_STATUS_WATCHING = "watching";
    public static final String VALID_TAG_MOVIE = "movie";
    public static final String VALID_TAG_SERIES = "series";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the show list, filtered show list and selected show in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        ShowList expectedShowList = new ShowList(actualModel.getShowList());
        List<Show> expectedFilteredList = new ArrayList<>(actualModel.getFilteredShowList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedShowList, actualModel.getShowList());
        assertEquals(expectedFilteredList, actualModel.getFilteredShowList());
    }
    /**
     * Updates {@code model}'s filtered list to display only the show at the given {@code targetIndex} in the
     * {@code model}'s show list.
     */
    public static void showShowAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredShowList().size());

        Show show = model.getFilteredShowList().get(targetIndex.getZeroBased());
        final String[] splitName = show.getName().fullName.split("\\s+");
        model.updateFilteredShowList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredShowList().size());
    }
}
