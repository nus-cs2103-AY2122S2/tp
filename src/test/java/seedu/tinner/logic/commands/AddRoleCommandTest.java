package seedu.tinner.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX;
import static seedu.tinner.logic.commands.AddRoleCommand.MESSAGE_DUPLICATE_ROLE;
import static seedu.tinner.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.tinner.commons.core.GuiSettings;
import seedu.tinner.commons.core.index.Index;
import seedu.tinner.logic.commands.exceptions.CommandException;
import seedu.tinner.model.Model;
import seedu.tinner.model.ReadOnlyCompanyList;
import seedu.tinner.model.ReadOnlyUserPrefs;
import seedu.tinner.model.company.Company;
import seedu.tinner.model.company.RoleManager;
import seedu.tinner.model.role.Role;
import seedu.tinner.testutil.CompanyBuilder;
import seedu.tinner.testutil.RoleBuilder;

public class AddRoleCommandTest {

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoleCommand(Index.fromOneBased(1), null));
    }

    @Test
    public void execute_roleAcceptedByModel_addSuccessful() throws Exception {
        Company meta = new CompanyBuilder().withName("Meta").build();
        Role softwareEngineer = new RoleBuilder().withName("Software Engineer").build();
        Index firstIndex = Index.fromOneBased(1);
        ModelStub modelStub = new ModelStubWithCompanyAcceptingRoleAdded(meta);

        CommandResult commandResult = new AddRoleCommand(firstIndex, softwareEngineer).execute(modelStub);

        assertEquals(String.format(AddRoleCommand.MESSAGE_SUCCESS, softwareEngineer),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidCompanyIndex_throwsCommandException() {
        Company meta = new CompanyBuilder().withName("Meta").build();
        Role softwareEngineer = new RoleBuilder().withName("Software Engineer").build();
        Index secondIndex = Index.fromOneBased(2);
        AddRoleCommand addRoleCommand = new AddRoleCommand(secondIndex, softwareEngineer);
        ModelStub modelStub = new ModelStubWithCompanyAcceptingRoleAdded(meta);

        assertThrows(CommandException.class, MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX, () ->
                addRoleCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicateRole_throwsCommandException() {
        Company meta = new CompanyBuilder().withName("Meta").build();
        Role softwareEngineer = new RoleBuilder().withName("Software Engineer").build();
        Role softwareEngineerCopy = new RoleBuilder().withName("Software Engineer").build();
        meta.getRoleManager().addRole(softwareEngineer);
        Index firstIndex = Index.fromOneBased(1);
        AddRoleCommand addRoleCommand = new AddRoleCommand(firstIndex, softwareEngineerCopy);
        ModelStub modelStub = new ModelStubWithCompanyAcceptingRoleAdded(meta);

        assertThrows(CommandException.class, MESSAGE_DUPLICATE_ROLE, () ->
                addRoleCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Role softwareEngineer = new RoleBuilder().withName("Software Engineer").build();
        Role mobileEngineer = new RoleBuilder().withName("Mobile Engineer").build();
        Index firstIndex = Index.fromOneBased(1);
        AddRoleCommand addSoftwareEngineerCommand = new AddRoleCommand(firstIndex, softwareEngineer);
        AddRoleCommand addMobileEngineerCommand = new AddRoleCommand(firstIndex, mobileEngineer);

        // same object -> returns true
        assertTrue(addSoftwareEngineerCommand.equals(addSoftwareEngineerCommand));

        // same values -> returns true
        AddRoleCommand addSoftwareEngineerCommandCopy = new AddRoleCommand(firstIndex, softwareEngineer);
        assertTrue(addSoftwareEngineerCommand.equals(addSoftwareEngineerCommandCopy));

        // different types -> returns false
        assertFalse(addSoftwareEngineerCommand.equals(1));

        // null -> returns false
        assertFalse(addSoftwareEngineerCommand.equals(null));

        // different company -> returns false
        assertFalse(addSoftwareEngineerCommand.equals(addMobileEngineerCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setReminderWindow(int reminderWindow) {
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
        public Path getCompanyListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompanyListFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompanyList(ReadOnlyCompanyList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyCompanyList getCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> companyPredicate, Predicate<Role> rolePredicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRole(Index companyIndex, Role role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasRole(Index companyIndex, Role role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Role> getFilteredRoleList(Index companyIndex) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteRole(Index companyIndex, Role role) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRole(Index companyIndex, Role target, Role editedRole) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredRoleList(Index companyIndex, Predicate<Role> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single company that accepts roles being added
     */
    private class ModelStubWithCompanyAcceptingRoleAdded extends ModelStub {
        private final Company company;

        ModelStubWithCompanyAcceptingRoleAdded(Company company) {
            requireNonNull(company);
            this.company = company;
        }

        @Override
        public boolean hasRole(Index companyIndex, Role role) {
            RoleManager roleManager = company.getRoleManager();
            return roleManager.hasRole(role);
        }

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return this.company.isSameCompany(company);
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            ObservableList<Company> filteredCompanyList = FXCollections.observableArrayList();
            filteredCompanyList.add(company);
            return filteredCompanyList;
        }

        @Override
        public void addRole(Index companyIndex, Role role) {
            assert (companyIndex.getZeroBased() < 1);
            requireNonNull(role);
            RoleManager roleManager = company.getRoleManager();
            roleManager.addRole(role);
        }
    }

}
