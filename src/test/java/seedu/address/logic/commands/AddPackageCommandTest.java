package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Person;

public class AddPackageCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPackageCommand(null));
    }

    @Test
    public void execute_packageAcceptedByModel_addSuccessful() throws Exception {
        AddPackageCommandTest.ModelStubAcceptingPackageAdded modelStub =
                new AddPackageCommandTest.ModelStubAcceptingPackageAdded();

        InsurancePackage validPackage = new InsurancePackage("Test", "Test Desc");
        CommandResult commandResult = new AddPackageCommand(validPackage).execute(modelStub);

        assertEquals(String.format(AddPackageCommand.MESSAGE_SUCCESS, validPackage), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validPackage), modelStub.packagesAdded);
    }

    @Test
    public void execute_duplicatePackage_throwsCommandException() {
        InsurancePackage validPackage = new InsurancePackage("Test");
        AddPackageCommand addPackageCommand = new AddPackageCommand(validPackage);
        AddPackageCommandTest.ModelStub modelStub = new AddPackageCommandTest.ModelStubWithPackage(validPackage);

        assertThrows(CommandException.class,
                AddPackageCommand.MESSAGE_DUPLICATE_PACKAGE, () -> addPackageCommand.execute(modelStub));
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
     * A Model stub that contains a single insurance package.
     */
    private class ModelStubWithPackage extends AddPackageCommandTest.ModelStub {
        private final InsurancePackage p;

        ModelStubWithPackage(InsurancePackage p) {
            requireNonNull(p);
            this.p = p;
        }

        @Override
        public boolean hasInsurancePackage(InsurancePackage p) {
            requireNonNull(p);
            return this.p.equals(p);
        }
    }

    /**
     * A Model stub that always accepts the package being added.
     */
    private class ModelStubAcceptingPackageAdded extends AddPackageCommandTest.ModelStub {
        final ArrayList<InsurancePackage> packagesAdded = new ArrayList<>();

        @Override
        public boolean hasInsurancePackage(InsurancePackage p) {
            requireNonNull(p);
            return packagesAdded.stream().anyMatch(p::equals);
        }

        @Override
        public void addInsurancePackage(InsurancePackage p) {
            requireNonNull(p);
            packagesAdded.add(p);
        }
    }

}
