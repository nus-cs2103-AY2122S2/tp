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
import seedu.unite.testutil.TagBuilder;
import seedu.unite.ui.theme.Theme;

public class AddTagCommandTest {
    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        AddTagCommandTest.ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult commandResult = new AddTagCommand(validTag).execute(modelStub);
        assertEquals(String.format(AddTagCommand.MESSAGE_SUCCESS, validTag), commandResult.getFeedbackToUser());
        assertEquals(List.of(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTag_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        AddTagCommand addTagCommand = new AddTagCommand(validTag);
        AddTagCommandTest.ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class, AddTagCommand.MESSAGE_DUPLICATE_TAG, () ->
                addTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag aTag = new TagBuilder().withTagName("ATag").build();
        Tag bTag = new TagBuilder().withTagName("BTag").build();
        AddTagCommand addATagCommand = new AddTagCommand(aTag);
        AddTagCommand addBTagCommand = new AddTagCommand(bTag);

        // same object -> returns true
        assertTrue(addATagCommand.equals(addATagCommand));

        // same values -> returns true
        AddTagCommand addATagCommandCopy = new AddTagCommand(aTag);
        assertTrue(addATagCommand.equals(addATagCommandCopy));

        // different types -> returns false
        assertFalse(addATagCommand.equals(1));

        // null -> returns false
        assertFalse(addATagCommand.equals(null));

        // different tag -> returns false
        assertFalse(addATagCommand.equals(addBTagCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private static class ModelStub implements Model {
        private boolean isShowTagList;

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
        public void setPerson(Person target, Person editedTag) {
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
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showTagList() {
            isShowTagList = true;
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
            return isShowTagList;
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
     * A Model stub that contains a single tag.
     */
    private static class ModelStubWithTag extends AddTagCommandTest.ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.isSameTag(tag);
        }
    }

    /**
     * A Model stub that always accept the tag being added.
     */
    private static class ModelStubAcceptingTagAdded extends AddTagCommandTest.ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyUnite getUnite() {
            return new Unite();
        }
    }
}
