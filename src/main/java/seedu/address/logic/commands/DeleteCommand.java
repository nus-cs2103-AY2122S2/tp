package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.MedicalWithNricPredicate;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionWithNricPredicate;
import seedu.address.model.testresult.TestResult;
import seedu.address.model.testresult.TestResultWithNricPredicate;

/**
 * Deletes a patient identified using its displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patientToDelete = lastShownList.get(targetIndex.getZeroBased());

        Nric nric = patientToDelete.getNric();

        model.updateFilteredContactList(new ContactWithNricPredicate(nric));
        model.updateFilteredMedicalList(new MedicalWithNricPredicate(nric));
        model.updateFilteredPrescriptionList(new PrescriptionWithNricPredicate(nric));
        model.updateFilteredConsultationList(new ConsultationWithPredicates(nric));
        model.updateFilteredTestResultList(new TestResultWithNricPredicate(nric));

        List<Contact> contactList = model.getFilteredContactList();
        List<Medical> medicalList = model.getFilteredMedicalList();
        List<Prescription> prescriptionList = model.getFilteredPrescriptionList();
        List<Consultation> consultationList = model.getFilteredConsultationList();
        List<TestResult> testResultList = model.getFilteredTestResultList();

        int contactSize = contactList.size();
        int medicalSize = medicalList.size();
        int prescriptionSize = prescriptionList.size();
        int consultationSize = consultationList.size();
        int testResultSize = testResultList.size();

        for (int i = 0; i < contactSize; i++) {
            model.deleteContact(contactList.get(0));
        }
        for (int i = 0; i < medicalSize; i++) {
            model.deleteMedical(medicalList.get(0));
        }
        for (int i = 0; i < prescriptionSize; i++) {
            model.deletePrescription(prescriptionList.get(0));
        }
        for (int i = 0; i < consultationSize; i++) {
            model.deleteConsultation(consultationList.get(0));
        }
        for (int i = 0; i < testResultSize; i++) {
            model.deleteTestResult(testResultList.get(0));
        }

        model.deletePatient(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
