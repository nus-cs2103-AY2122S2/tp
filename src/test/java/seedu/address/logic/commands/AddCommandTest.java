package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.commandhistory.CommandHistoryEntry;
import seedu.address.model.image.ImageDetailsList;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
        assertEquals(CommandResult.SpecialCommandResult.NONE, commandResult.getSpecialCommandResult());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_newTag_createNewTagSuccessful() throws Exception {
        Person validPerson = new PersonBuilder().withTags("newTag").build();

        CommandResult commandResult = new AddCommand(validPerson).execute(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(CommandResult.SpecialCommandResult.NONE, commandResult.getSpecialCommandResult());
        assertTrue(model.hasTag(new Tag("newTag")));
        assertTrue(model.hasPerson(validPerson));
    }

    @Test
    public void executeInDetailedView_personAcceptedByModel_addSuccessful() throws Exception {
        Person firstPerson = model.getSortedPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.setDetailedContactView(firstPerson); //Enter detailed view mode
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).executeInDetailedView(model);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(CommandResult.SpecialCommandResult.NONE, commandResult.getSpecialCommandResult());

        //View mode doesn't change and stays on the current person
        assertEquals(model.getDetailedContactViewPerson(), firstPerson);

        assertTrue(model.hasPerson(validPerson));
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
    private class ModelStub implements Model {
        private final AssertionError uncalledAE = new AssertionError("This method should not be called.");

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw uncalledAE;
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw uncalledAE;
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw uncalledAE;
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw uncalledAE;
        }

        @Override
        public Path getAddressBookFilePath() {
            throw uncalledAE;
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw uncalledAE;
        }

        @Override
        public Path getContactImagesFilePath() {
            throw uncalledAE;
        }

        @Override
        public void setContactImagesFilePath(Path addressBookFilePath) {
            throw uncalledAE;
        }

        @Override
        public void addPerson(Person person) {
            throw uncalledAE;
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw uncalledAE;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw uncalledAE;
        }

        @Override
        public boolean hasPerson(Person person) {
            throw uncalledAE;
        }

        @Override
        public void deletePerson(Person target) {
            throw uncalledAE;
        }

        @Override
        public void addActivatedTag(Tag tag) {
            throw uncalledAE;
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw uncalledAE;
        }

        @Override
        public void addTag(Tag tag) {
            throw uncalledAE;
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw uncalledAE;
        }

        @Override
        public void deleteTag(Tag target) {
            throw uncalledAE;
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw uncalledAE;
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw uncalledAE;
        }

        @Override
        public ObservableList<Tag> getActivatedTagList() {
            throw uncalledAE;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw uncalledAE;
        }

        @Override
        public SortedList<Person> getSortedPersonList() {
            throw uncalledAE;
        }

        @Override
        public void clearActivatedTagList() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByName() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByAddress() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByDeadlineList() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByEmail() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByPhone() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByFavourite() {
            throw uncalledAE;
        }

        @Override
        public void sortFilteredPersonListByHighImportance() {
            throw uncalledAE;
        }

        @Override
        public void setImagesToView(ImageDetailsList images) {
            throw uncalledAE;
        }

        @Override
        public ImageDetailsList getImagesToView() {
            throw uncalledAE;
        }

        @Override
        public void updateCommandHistory(String commandText) {
            throw uncalledAE;
        }

        @Override
        public CommandHistoryEntry getCommandHistory(int i) {
            throw uncalledAE;
        }

        @Override
        public ObservableList<Person> getDetailedContactView() {
            throw uncalledAE;
        }

        @Override
        public void setDetailedContactView(Person person) {
            throw uncalledAE;
        }

        @Override
        public void clearDetailedContactView() {
            throw uncalledAE;
        }

        @Override
        public Person getDetailedContactViewPerson() {
            throw uncalledAE;
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
