package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Person;

public class EditPackageCommandTest {

    @Test
    public void execute_packageAcceptedByModel_editSuccessful() throws Exception {

        String packageName = "Test Package Name";
        String oldDesc = "Test Desc";
        String newDesc = "Test Desc 2";

        InsurancePackage validPackage = new InsurancePackage(packageName, oldDesc);

        // edit package in model stub
        EditPackageCommandTest.ModelStubWithPackage modelStub =
                new EditPackageCommandTest.ModelStubWithPackage(validPackage);
        modelStub.setInsurancePackage(packageName, newDesc);

        // edit package
        validPackage.setPackageDescription(newDesc);

        CommandResult commandResult = new EditPackageCommand(packageName, newDesc).execute(modelStub);

        assertEquals(String.format(EditPackageCommand.MESSAGE_EDIT_PACKAGE_SUCCESS, validPackage),
                commandResult.getFeedbackToUser());

        assertEquals(validPackage, modelStub.p);

        assertEquals(newDesc, modelStub.getPackageDesc());
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
     * A Model stub that contains a single insurance package, with description editable.
     */
    private class ModelStubWithPackage extends EditPackageCommandTest.ModelStub {
        private final InsurancePackage p;

        ModelStubWithPackage(InsurancePackage p) {
            requireNonNull(p);
            this.p = p;
        }

        @Override
        public boolean hasInsurancePackage(InsurancePackage p) {
            return this.p.equals(p);
        }

        @Override
        public void setInsurancePackage(String packageName, String newDesc) {
            if (p.getPackageName().equals(packageName)) {
                p.setPackageDescription(newDesc);
            }
        }

        public String getPackageDesc() {
            return p.getPackageDescription();
        }
    }
}
