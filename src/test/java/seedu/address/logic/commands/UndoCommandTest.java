package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIFTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UndoCommand}.
 */
public class UndoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());

    @Test
    public void execute_undoCommand_throwsCommandException() {
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, "No previous command to undo");
    }

    @Test
    public void execute_undoCommand_addUndoSuccess() {
        try {
            Person validPerson = new PersonBuilder().build();
            AddCommand addCommand = new AddCommand(validPerson);
            UndoCommand undoCommand = new UndoCommand();

            String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new InsurancePackagesSet());
            expectedModel.addPerson(validPerson);
            expectedModel.undoCommand();

            addCommand.execute(model);
            CommandResult result = undoCommand.execute(model);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);

        } catch (CommandException ce) {
            throw new AssertionError("Execution of undo should not fail.", ce);
        }
    }

    @Test
    public void execute_undoCommand_deleteUndoSuccess() {
        try {
            Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
            DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
            UndoCommand undoCommand = new UndoCommand();

            String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);
            CommandResult expectedCommandResult = new CommandResult(expectedMessage);

            Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), new InsurancePackagesSet());
            expectedModel.deletePerson(personToDelete);
            expectedModel.undoCommand();

            deleteCommand.execute(model);
            CommandResult result = undoCommand.execute(model);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of undo should not fail.", ce);
        }
    }

    @Test
    public void execute_undoCommand_editUndoSuccess() {
        try {
            Person editedPerson = new PersonBuilder().build();
            EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(editedPerson).build();
            EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);
            UndoCommand undoCommand = new UndoCommand();

            String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);

            Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs(),
                    new InsurancePackagesSet());
            expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);
            expectedModel.undoCommand();

            editCommand.execute(model);

            assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of undo should not fail.", ce);
        }
    }

    @Test
    public void execute_undoCommand_addTagUndoSuccess() throws Exception {
        Person person = new PersonBuilder().build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);
        Tag tag = VALID_TAG_FRIEND.get(0);

        Person tagAddedPerson = new PersonBuilder().build();
        ArrayList<Tag> tagAddedTagList = tagAddedPerson.getTags();
        boolean b = tagAddedTagList.add(tag);

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new AddTagCommand(index, tag).execute(modelManager);
        commandResult = new UndoCommand().execute(modelManager);

        assertEquals(String.format(UndoCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_undoCommand_deleteTagUndoSuccess() throws Exception {
        Person person = new PersonBuilder().withTags(VALID_TAG_FRIEND).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);

        Person tagDeletedPerson = new PersonBuilder().withTags(new ArrayList<>()).build();

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new DeleteTagCommand(index, 1).execute(modelManager);
        commandResult = new UndoCommand().execute(modelManager);

        assertEquals(String.format(UndoCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_undoCommand_editTagUndoSuccess() throws Exception {
        Person person = new PersonBuilder().withTags(VALID_TAG_FRIEND).build();
        AddressBookBuilder addressBookBuilder = new AddressBookBuilder().withPerson(person);
        Index index = Index.fromOneBased(1);
        Tag editedTag = VALID_TAG_HUSBAND.get(0);

        Person tagAddedPerson = new PersonBuilder().withTags(new ArrayList<>(
                List.of(editedTag))).build();

        ModelManager modelManager = new ModelManager();
        modelManager.setAddressBook(addressBookBuilder.build());

        CommandResult commandResult = new EditTagCommand(index, 1, editedTag).execute(modelManager);
        commandResult = new UndoCommand().execute(modelManager);

        assertEquals(String.format(UndoCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_undoCommand_clearUndoSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());
        ClearCommand clearCommand = new ClearCommand();
        UndoCommand undoCommand = new UndoCommand();

        String expectedMessage = String.format(UndoCommand.MESSAGE_SUCCESS);

        clearCommand.execute(model);

        assertCommandSuccess(undoCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_undoCommand_undoLimit() {
        try {
            DeleteCommand deleteCommand6 = new DeleteCommand(INDEX_SIXTH_PERSON);
            DeleteCommand deleteCommand5 = new DeleteCommand(INDEX_FIFTH_PERSON);
            DeleteCommand deleteCommand4 = new DeleteCommand(INDEX_FOURTH_PERSON);
            DeleteCommand deleteCommand3 = new DeleteCommand(INDEX_THIRD_PERSON);
            DeleteCommand deleteCommand2 = new DeleteCommand(INDEX_SECOND_PERSON);
            DeleteCommand deleteCommand1 = new DeleteCommand(INDEX_FIRST_PERSON);
            UndoCommand undoCommand = new UndoCommand();

            deleteCommand6.execute(model);
            deleteCommand5.execute(model);
            deleteCommand4.execute(model);
            deleteCommand3.execute(model);
            deleteCommand2.execute(model);
            deleteCommand1.execute(model);
            undoCommand.execute(model);
            undoCommand.execute(model);
            undoCommand.execute(model);
            undoCommand.execute(model);

            assertCommandFailure(undoCommand, model, "No previous command to undo");

        } catch (CommandException ce) {
            throw new AssertionError("Execution of undo should fail.", ce);
        }
    }

    /**
     * A default model stub that has all of the methods failing.
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInsurancePackagesFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInsurancePackagesFilePath(Path insurancePackagesFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public InsurancePackagesSet getInsurancePackagesSet() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInsurancePackagesSet(InsurancePackagesSet s) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInsurancePackage(InsurancePackage p) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInsurancePackage(InsurancePackage p) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInsurancePackage(InsurancePackage p) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInsurancePackage(String targetPackageName, String newPackageDesc) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByPriority() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoCommand() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoCommand() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends UndoCommandTest.ModelStub {
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
        public void addInsurancePackage(InsurancePackage p) {
            // do nothing with the package
            requireNonNull(p);

            // this method is written because the Model now adds an insurance package when a person is added
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
