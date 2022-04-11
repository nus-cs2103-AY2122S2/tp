package seedu.unite.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.unite.commons.core.GuiSettings;
import seedu.unite.logic.commands.exceptions.CommandException;
import seedu.unite.model.Model;
import seedu.unite.model.ReadOnlyUnite;
import seedu.unite.model.ReadOnlyUserPrefs;
import seedu.unite.model.Unite;
import seedu.unite.model.person.Person;
import seedu.unite.model.tag.Tag;
import seedu.unite.testutil.PersonBuilder;
import seedu.unite.ui.theme.Theme;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(List.of(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        private boolean isShowProfile;

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
        public Path getUniteFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniteFilePath(Path uniteFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUnite(ReadOnlyUnite newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUnite getUnite() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFullPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void disableMouseUX() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void enableMouseUX() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isMouseUxEnabled() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int countPersonsInTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showProfile(Person person) {
            isShowProfile = true;
        }

        @Override
        public void showTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showGrabResult(String grabResult) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void removeProfile(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchTheme(Theme theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isShowProfile() {
            return isShowProfile;
        }

        @Override
        public boolean isShowTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isShowGrabResult() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRemoveProfile() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isSwitchTheme() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Person getPerson() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getGrabResult() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Theme getTheme() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private static class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyUnite getUnite() {
            return new Unite();
        }
    }

}
