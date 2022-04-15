package seedu.address.testutil;

import static seedu.address.testutil.TypicalPatients.getTypicalPatients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MedBook;
import seedu.address.model.patient.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class containing a list of {@code Prescription} objects to be used in tests.
 */
public class TypicalPrescriptions {

    public static final Prescription PRESCRIPTION_A = new PrescriptionBuilder().withNric("G1234567L")
            .withDrugName("Acetaminophen").withDate("2022-02-04")
            .withInstruction("2 tablets after meal everyday")
            .build();
    public static final Prescription PRESCRIPTION_B = new PrescriptionBuilder().withNric("G1234567L")
            .withDrugName("Adderall")
            .withDate("2022-02-07").withInstruction("1 tablet per day").build();
    public static final Prescription PRESCRIPTION_C = new PrescriptionBuilder().withNric("G1234568L")
            .withDrugName("Ativan")
            .withDate("2022-02-04").withInstruction("1 tablet per day").build();

    private TypicalPrescriptions() {}

    /**
     * Returns an {@code MedBook} with all the typical prescriptions.
     */
    public static MedBook getTypicalMedBook() {
        MedBook ab = new MedBook();
        for (Patient patient : getTypicalPatients()) {
            ab.addPatient(patient);
        }
        for (Prescription prescription : getTypicalPrescription()) {
            ab.addPrescription(prescription);
        }
        return ab;
    }

    public static List<Prescription> getTypicalPrescription() {
        return new ArrayList<>(Arrays.asList(PRESCRIPTION_A, PRESCRIPTION_B, PRESCRIPTION_C));
    }
}
