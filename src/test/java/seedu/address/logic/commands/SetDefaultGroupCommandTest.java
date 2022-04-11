package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.VersionedContents;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.ReadOnlyTaskList;
import seedu.address.model.tasks.Task;
import seedu.address.testutil.ModuleBuilder;

public class SetDefaultGroupCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetDefaultGroupCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModAdded modelStub = new ModelStubAcceptingModAdded();
        Mod validmod = new ModuleBuilder().build();

        CommandResult commandResult = new SetDefaultGroupCommand(validmod).execute(modelStub);

        assertEquals(String.format(SetDefaultGroupCommand.MESSAGE_SUCCESS, validmod.value,
                    validmod.getDefaultGroup()), commandResult.getFeedbackToUser());

        UniqueModuleList tester = new UniqueModuleList();
        tester.add(validmod);
        assertEquals(tester, modelStub.moduleList);

        validmod.setDefaultGroup("T21");
        CommandResult commandResult1 = new SetDefaultGroupCommand(validmod).execute(modelStub);
        assertEquals(String.format(SetDefaultGroupCommand.MESSAGE_DEFAULT_UPDATE, validmod.value, "T21",
                    validmod.getDefaultGroup()), commandResult1.getFeedbackToUser());
        UniqueModuleList tester1 = new UniqueModuleList();
        tester1.add(validmod);
        assertEquals(tester1, modelStub.moduleList);

        Mod validmod2 = new Mod("CS3232");
        CommandResult commandResult2 = new SetDefaultGroupCommand(validmod2, "T12").execute(modelStub);
        assertEquals(String.format(SetDefaultGroupCommand.MESSAGE_SUCCESS, validmod2.value,
                    validmod2.getDefaultGroup()), commandResult2.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Mod aliceMod = new ModuleBuilder().withMod("CS2100").withGroup("T01").build();
        Mod bobMod = new ModuleBuilder().withMod("CS2108").withGroup("T02").build();
        SetDefaultGroupCommand addAliceDefaultGroup = new SetDefaultGroupCommand(aliceMod);
        SetDefaultGroupCommand addBobDefaultGroup = new SetDefaultGroupCommand(bobMod);

        // same object -> returns true
        assertTrue(addAliceDefaultGroup.equals(addAliceDefaultGroup));

        // same values -> returns true
        SetDefaultGroupCommand addAliceCommandCopy = new SetDefaultGroupCommand(aliceMod);
        assertTrue(addAliceDefaultGroup.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceDefaultGroup.equals(1));

        // null -> returns false
        assertFalse(addAliceDefaultGroup.equals(null));

        // different person -> returns false
        assertFalse(addAliceDefaultGroup.equals(addBobDefaultGroup));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    protected static class ModelStub implements Model {
        @Override
        public ObservableList<Task> getUnmodifiableTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonIgnoreTags(Person person) {
            throw new AssertionError("This method should not be called.");
        }
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
        public Path getContactListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactListFilePath(Path contactListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setContactList(ReadOnlyContactList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyContactList getContactList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskListFilePath(Path taskListFilePath) {
            throw new AssertionError("This method should not be called.");

        }

        @Override
        public void setTaskList(ReadOnlyTaskList taskList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskList getTaskList() {
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
        public UniqueModuleList getModuleList() {
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
        public boolean isDefaultGroupOfModPresent(Mod mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean doesModExistInList(Mod mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String retrievePrevDefault(Mod mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMod(Mod mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Optional<Mod> getMod(Mod mod) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getDefaultGroupOfMod(Mod mod) {
            throw new AssertionError("This method should not be called.");

        }
        @Override
        public void setDefaultGroup(Mod mod, String value) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            return false;
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setModuleList(UniqueModuleList moduleList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public VersionedContents getVersionedContents() {
            return null;
        }

        @Override
        public void undoContents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoContents() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedo() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitContent() {
            return; // this method will be called when methods that changes content are called:
        }


    }

    /**
     * A Model stub that always accept the mod being added.
     */
    protected static class ModelStubAcceptingModAdded extends ModelStub {
        final UniqueModuleList moduleList = new UniqueModuleList();
        @Override
        public boolean doesModExistInList(Mod mod) {
            requireNonNull(mod);
            return moduleList.contains(mod);
        }

        @Override
        public void addMod(Mod mod) {
            requireNonNull(mod);
            moduleList.add(mod);
        }
        @Override
        public void setDefaultGroup(Mod mod, String value) {
            requireNonNull(mod);
            mod.setDefaultGroup(value);
        }
        @Override
        public Optional<Mod> getMod(Mod mod) {
            requireNonNull(mod);
            return moduleList.retrieveMod(mod);
        }
        @Override
        public boolean isDefaultGroupOfModPresent(Mod mod) {
            requireNonNull(mod);
            Mod modInList = this.getMod(mod).get();
            return modInList.getDefaultGroup() != null;
        }
        @Override
        public String retrievePrevDefault(Mod mod) {
            requireNonNull(mod);
            return mod.getDefaultGroup();
        }


    }

}

