package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.EditCommandParser;
import seedu.address.logic.parser.FindCommandParser;
import seedu.address.logic.parser.consultations.AddConsultationCommandParser;
import seedu.address.logic.parser.consultations.DeleteConsultationCommandParser;
import seedu.address.logic.parser.consultations.EditConsultationCommandParser;
import seedu.address.logic.parser.consultations.FindConsultationCommandParser;
import seedu.address.logic.parser.consultations.ViewConsultationCommandParser;
import seedu.address.logic.parser.contact.AddContactCommandParser;
import seedu.address.logic.parser.contact.DeleteContactCommandParser;
import seedu.address.logic.parser.contact.EditContactCommandParser;
import seedu.address.logic.parser.contact.FindContactCommandParser;
import seedu.address.logic.parser.contact.ViewContactCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.medical.AddMedicalCommandParser;
import seedu.address.logic.parser.medical.DeleteMedicalCommandParser;
import seedu.address.logic.parser.medical.EditMedicalCommandParser;
import seedu.address.logic.parser.medical.FindMedicalCommandParser;
import seedu.address.logic.parser.medical.ViewMedicalCommandParser;
import seedu.address.logic.parser.prescription.AddPrescriptionCommandParser;
import seedu.address.logic.parser.prescription.DeletePrescriptionCommandParser;
import seedu.address.logic.parser.prescription.EditPrescriptionCommandParser;
import seedu.address.logic.parser.prescription.FindPrescriptionCommandParser;
import seedu.address.logic.parser.prescription.ViewPrescriptionCommandParser;
import seedu.address.logic.parser.testresult.AddTestResultCommandParser;
import seedu.address.logic.parser.testresult.DeleteTestResultCommandParser;
import seedu.address.logic.parser.testresult.EditTestResultCommandParser;
import seedu.address.logic.parser.testresult.FindTestResultCommandParser;
import seedu.address.logic.parser.testresult.ViewTestResultCommandParser;

public enum CommandType {
    DEFAULT, CONTACT, MEDICAL, CONSULTATION, PRESCRIPTION, TEST, SUMMARY;
}
