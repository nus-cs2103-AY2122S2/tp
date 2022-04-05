package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyMedBook;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.contact.Contact;
import seedu.address.model.medical.Medical;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.testresult.TestResult;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getMedBook()
     */
    ReadOnlyMedBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of prescriptions */
    ObservableList<Prescription> getFilteredPrescriptionList();

    /** Returns an unmodifiable view of the filtered list of contacts */
    ObservableList<Contact> getFilteredContactList();


    /** Returns an unmodifiable view of the filtered list of consultations */
    ObservableList<Consultation> getFilteredConsultationList();

    /** Returns an unmodifiable view of the filtered list of medicals */
    ObservableList<Medical> getFilteredMedicalList();


    /** Returns an unmodifiable view of the filtered list of test results */
    ObservableList<TestResult> getFilteredTestResultList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
