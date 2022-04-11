package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MedBook;
import seedu.address.model.consultation.Consultation;

/**
 * A utility class containing a list of {@code Consultation} objects to be used in tests.
 */
public class TypicalConsultations {

    public static final Consultation CONSULTATION_A = new ConsultationBuilder()
            .withNric("G1234567L")
            .withDate("2020-10-12")
            .withTime("19-00")
            .withDiagnosis("Upper Respiratory Infection. Prescribed antibiotics.")
            .withFee("54.50")
            .withNotes("NIL")
            .build();

    public static final Consultation CONSULTATION_B = new ConsultationBuilder()
            .withNric("G1234567L")
            .withDate("2020-09-02")
            .withTime("19-00")
            .withDiagnosis("H1N1 Influenza. Prescribed antibiotics.")
            .withFee("10.50")
            .withNotes("NIL")
            .build();

    private TypicalConsultations() {}

    /**
     * Returns an {@code MedBook} with all the typical medicals.
     */
    public static MedBook getTypicalAddressBook() {
        MedBook ab = new MedBook();
        for (Consultation consultation : getTypicalConsultations()) {
            ab.addConsultation(consultation);
        }
        return ab;
    }

    public static List<Consultation> getTypicalConsultations() {
        return new ArrayList<>(Arrays.asList(CONSULTATION_A, CONSULTATION_B));
    }
}
