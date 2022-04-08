package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.OrderingUtil.Ordering;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Entry;
import seedu.address.model.entry.Event;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Person;
import seedu.address.testutil.CompanyBuilder;

public class AddCompanyCommandTest {

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCompanyCommand(null));
    }

    @Test
    public void execute_companyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCompanyAdded modelStub = new ModelStubAcceptingCompanyAdded();
        Company validCompany = new CompanyBuilder().build();

        CommandResult commandResult = new AddCompanyCommand(validCompany).execute(modelStub);

        assertEquals(String.format(AddCompanyCommand.MESSAGE_SUCCESS, validCompany), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompany), modelStub.companiesAdded);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCompanyCommand addCompanyCommand = new AddCompanyCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertThrows(CommandException.class,
                AddCompanyCommand.MESSAGE_DUPLICATE_COMPANY, () -> addCompanyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Company alpha = new CompanyBuilder().withName("Alpha").build();
        Company beta = new CompanyBuilder().withName("Beta").build();
        AddCompanyCommand addAlphaCommand = new AddCompanyCommand(alpha);
        AddCompanyCommand addBetaCommand = new AddCompanyCommand(beta);

        // same object -> returns true
        assertTrue(addAlphaCommand.equals(addAlphaCommand));

        // same values -> returns true
        AddCompanyCommand addAlphaCommandCopy = new AddCompanyCommand(alpha);
        assertTrue(addAlphaCommand.equals(addAlphaCommandCopy));

        // different types -> returns false
        assertFalse(addAlphaCommand.equals(1));

        // null -> returns false
        assertFalse(addAlphaCommand.equals(null));

        // different person -> returns false
        assertFalse(addAlphaCommand.equals(addBetaCommand));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
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
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEvent(Event event) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Name companyName) {
            return true;
        }; // Leave it this way for now

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEvent(Event target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEvent(Event target, Event editedEvent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateCurrentlyDisplayedList(Predicate<Entry> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showCompanyList(Predicate<? super Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void showEventList(Predicate<? super Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<? super Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEventList(Predicate<? super Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entry deleteEntry(int index) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entry archiveEntry(int index, boolean isArchived) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPersonListByName(Ordering ordering, Predicate<? super Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortCompanyListByName(Ordering ordering, Predicate<? super Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortEventListByDate(Ordering ordering, Predicate<? super Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single company.
     */
    private class ModelStubWithCompany extends ModelStub {
        private final Company company;

        ModelStubWithCompany(Company company) {
            requireNonNull(company);
            this.company = company;
        }

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return this.company.isSameEntry(company);
        }
    }

    /**
     * A Model stub that always accept the Company being added.
     */
    private class ModelStubAcceptingCompanyAdded extends ModelStub {
        final ArrayList<Company> companiesAdded = new ArrayList<>();

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return companiesAdded.stream().anyMatch(company::isSameEntry);
        }

        @Override
        public void addCompany(Company company) {
            requireNonNull(company);
            companiesAdded.add(company);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
