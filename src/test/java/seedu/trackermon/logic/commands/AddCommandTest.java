package seedu.trackermon.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackermon.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.trackermon.commons.core.GuiSettings;
import seedu.trackermon.commons.core.Messages;
import seedu.trackermon.logic.commands.exceptions.CommandException;
import seedu.trackermon.model.Model;
import seedu.trackermon.model.ReadOnlyShowList;
import seedu.trackermon.model.ReadOnlyUserPrefs;
import seedu.trackermon.model.ShowList;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.testutil.ShowBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandTest {

    /**
     * Tests the adding of a null from the execution of {@code AddCommand}.
     */
    @Test
    public void constructor_nullShow_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    /**
     * Tests the adding of a valid show from the execution of {@code AddCommand}.
     */
    @Test
    public void execute_showAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingShowAdded modelStub = new ModelStubAcceptingShowAdded();
        Show validShow = new ShowBuilder().build();

        CommandResult commandResult = new AddCommand(validShow).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validShow), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validShow), modelStub.showsAdded);
    }

    /**
     * Tests the adding of a duplicate show from the execution of {@code AddCommand}.
     */
    @Test
    public void execute_duplicateShow_throwsCommandException() {
        Show validShow = new ShowBuilder().build();
        AddCommand addCommand = new AddCommand(validShow);
        ModelStub modelStub = new ModelStubWithShow(validShow);

        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_SHOW, () -> addCommand.execute(modelStub));
    }


    /**
     *  Tests if adding different types of shows are the same from the execution of {@code AddCommand}.
     */
    @Test
    public void equals() {
        Show another = new ShowBuilder().withName("Another").build();
        Show blackClover = new ShowBuilder().withName("Black Clover").build();
        AddCommand addAnotherCommand = new AddCommand(another);
        AddCommand addBlackCloverCommand = new AddCommand(blackClover);

        // same object -> returns true
        assertTrue(addAnotherCommand.equals(addAnotherCommand));

        // same values -> returns true
        AddCommand addAnotherCommandCopy = new AddCommand(another);
        assertTrue(addAnotherCommand.equals(addAnotherCommandCopy));

        // different types -> returns false
        assertFalse(addAnotherCommand.equals(1));

        // null -> returns false
        assertFalse(addAnotherCommand.equals(null));

        // different show -> returns false
        assertFalse(addAnotherCommand.equals(addBlackCloverCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {

        /**
         * {@inheritDoc}
         */
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Path getShowListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setShowListFilePath(Path showListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addShow(Show show) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setShowList(ReadOnlyShowList showList) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ReadOnlyShowList getShowList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns the ShowList size
         */
        @Override
        public int getShowListSize() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasShow(Show show) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void deleteShow(Show target) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void setShow(Show target, Show editedShow) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ObservableList<Show> getFilteredShowList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void updateFilteredShowList(Predicate<Show> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ObservableList<Show> getSortedShowList() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void updateSortedShowList(Comparator<Show> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void saveSortedShowList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithShow extends ModelStub {
        private final Show show;

        /**
         * Initializes a ModelStubWithShow with the given show.
         */
        ModelStubWithShow(Show show) {
            requireNonNull(show);
            this.show = show;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasShow(Show show) {
            requireNonNull(show);
            return this.show.isSameShow(show);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingShowAdded extends ModelStub {
        final ArrayList<Show> showsAdded = new ArrayList<>();

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasShow(Show show) {
            requireNonNull(show);
            return showsAdded.stream().anyMatch(show::isSameShow);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void addShow(Show show) {
            requireNonNull(show);
            showsAdded.add(show);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public ReadOnlyShowList getShowList() {
            return new ShowList();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public int getShowListSize() {
            return showsAdded.size();
        }
    }
}
