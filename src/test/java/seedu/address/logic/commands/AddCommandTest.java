package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InternApplyMemory;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyInternApplyMemory;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.application.Application;
import seedu.address.model.summarybar.SummaryBox;
import seedu.address.testutil.ApplicationBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_applicationAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingApplicationAdded modelStub = new ModelStubAcceptingApplicationAdded();
        Application validApplication = new ApplicationBuilder().build();

        CommandResult commandResult = new AddCommand(validApplication).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validApplication), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.applicationsAdded);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application validApplication = new ApplicationBuilder().build();
        AddCommand addCommand = new AddCommand(validApplication);
        ModelStub modelStub = new ModelStubWithApplication(validApplication);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_APPLICATION, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Application grab = new ApplicationBuilder().withName("Grab").build();
        Application lazada = new ApplicationBuilder().withName("Lazada").build();
        AddCommand addGrabCommand = new AddCommand(grab);
        AddCommand addLazadaCommand = new AddCommand(lazada);

        // same object -> returns true
        assertTrue(addGrabCommand.equals(addGrabCommand));

        // same values -> returns true
        AddCommand addGrabCommandCopy = new AddCommand(grab);
        assertTrue(addGrabCommand.equals(addGrabCommandCopy));

        // different types -> returns false
        assertFalse(addGrabCommand.equals(1));

        // null -> returns false
        assertFalse(addGrabCommand.equals(null));

        // different person -> returns false
        assertFalse(addGrabCommand.equals(addLazadaCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternApplyMemoryFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternApplyMemoryFilePath(Path internApplyMemoryFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternApplyMemory(ReadOnlyInternApplyMemory newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternApplyMemory getInternApplyMemory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(Application target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(Application target, Application editedApplication) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getUpcomingApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateUpcomingApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortApplications(Comparator<Application> c, String orderBy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<SummaryBox> getSummaryBoxList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateSummaryBoxList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single application.
     */
    private class ModelStubWithApplication extends ModelStub {
        private final Application application;

        ModelStubWithApplication(Application application) {
            requireNonNull(application);
            this.application = application;
        }

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return this.application.isSameApplication(application);
        }
    }

    /**
     * A Model stub that always accept the application being added.
     */
    private class ModelStubAcceptingApplicationAdded extends ModelStub {
        final ArrayList<Application> applicationsAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(Application application) {
            requireNonNull(application);
            return applicationsAdded.stream().anyMatch(application::isSameApplication);
        }

        @Override
        public void addApplication(Application application) {
            requireNonNull(application);
            applicationsAdded.add(application);
        }

        @Override
        public ReadOnlyInternApplyMemory getInternApplyMemory() {
            return new InternApplyMemory();
        }
    }

}
