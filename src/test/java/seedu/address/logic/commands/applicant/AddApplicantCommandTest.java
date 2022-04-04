package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Phone;
import seedu.address.model.interview.Interview;
import seedu.address.model.position.Position;
import seedu.address.testutil.PersonBuilder;

public class AddApplicantCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicantCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Applicant validApplicant = new PersonBuilder().build();

        CommandResult commandResult = new AddApplicantCommand(validApplicant).execute(modelStub);

        assertEquals(String.format(AddApplicantCommand.MESSAGE_SUCCESS, validApplicant),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validApplicant), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Applicant validApplicant = new PersonBuilder().build();
        AddApplicantCommand addApplicantCommand = new AddApplicantCommand(validApplicant);
        ModelStub modelStub = new ModelStubWithPerson(validApplicant);

        assertThrows(CommandException.class,
                AddApplicantCommand.MESSAGE_DUPLICATE_PERSON, () -> addApplicantCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Applicant alice = new PersonBuilder().withName("Alice").build();
        Applicant bob = new PersonBuilder().withName("Bob").build();
        AddApplicantCommand addAliceCommand = new AddApplicantCommand(alice);
        AddApplicantCommand addBobCommand = new AddApplicantCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddApplicantCommand addAliceCommandCopy = new AddApplicantCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different applicant -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public void addPerson(Applicant applicant) {
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
        public boolean hasPerson(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Applicant getApplicantWithEmail(Email email) {
            return null;
        }

        @Override
        public Applicant getApplicantWithPhone(Phone phone) {
            return null;
        }

        @Override
        public boolean isSameApplicantPosition(Applicant applicant, Position position) {
            return true;
        }

        @Override
        public void deletePerson(Applicant target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Applicant target, Applicant editedApplicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Applicant> getFilteredApplicantList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Interview> getFilteredInterviewList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Position> getFilteredPositionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortApplicantList(Comparator<Applicant> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortInterviewList(Comparator<Interview> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortPositionList(Comparator<Position> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilterAndSortApplicantList(Predicate<Applicant> predicate, Comparator<Applicant> comparator) {

        }

        @Override
        public void updateFilterAndSortInterviewList(Predicate<Interview> predicate, Comparator<Interview> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilterAndSortPositionList(Predicate<Position> predicate, Comparator<Position> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void exportCsvApplicant() throws FileNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void exportCsvInterview() throws FileNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void exportCsvPosition() throws FileNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicantList(Predicate<Applicant> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isPassableInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isAcceptableInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isRejectableInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }
        //        @Override
        //        public void passInterview(Interview interview) {
        //            throw new AssertionError("This method should not be called.");
        //        }
        //
        //        @Override
        //        public void failInterview(Interview interview) {
        //            throw new AssertionError("This method should not be called.");
        //        }
        //
        //        @Override
        //        public void acceptInterview(Interview interview) {
        //            throw new AssertionError("This method should not be called.");
        //        }
        //
        //        @Override
        //        public void rejectInterview(Interview interview) {
        //            throw new AssertionError("This method should not be called.");
        //        }

        @Override
        public void setInterview(Interview target, Interview editedInterview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredInterviewList(Predicate<Interview> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPositionList(Predicate<Position> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Interview> getApplicantsInterviews(Applicant applicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Interview> getPositionsInterviews(Position position) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePosition(Position target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPosition(Position position) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasConflictingInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPosition(Position target, Position editedPosition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updatePosition(Position positionToBeUpdated, Position newPosition) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateApplicant(Applicant applicantToBeUpdated, Applicant newApplicant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPosition(Position position) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasInterview(Interview interview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteInterview(Interview target) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single applicant.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Applicant applicant;

        ModelStubWithPerson(Applicant applicant) {
            requireNonNull(applicant);
            this.applicant = applicant;
        }

        @Override
        public boolean hasPerson(Applicant applicant) {
            requireNonNull(applicant);
            return this.applicant.isSamePerson(applicant);
        }
    }

    /**
     * A Model stub that always accept the applicant being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Applicant> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Applicant applicant) {
            requireNonNull(applicant);
            return personsAdded.stream().anyMatch(applicant::isSamePerson);
        }

        @Override
        public void addPerson(Applicant applicant) {
            requireNonNull(applicant);
            personsAdded.add(applicant);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
