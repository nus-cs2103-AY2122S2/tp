package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.MedBook;
import seedu.address.model.medical.Medical;

/**
 * A utility class containing a list of {@code Medical} objects to be used in tests.
 */
public class TypicalMedicals {

    public static final Medical MEDICAL_A = new MedicalBuilder()
            .withNric("G1234567L")
            .withAge("22")
            .withBloodType("AB")
            .withMedication("Paracetamol 500mg twice a day")
            .withIllnesses("Mild fever")
            .withSurgeries("Appendectomy")
            .withFamilyHistory("Has family history of high blood pressure")
            .withImmunizationHistory("MMR; 6 in 1; Hepatitis B")
            .withGender("Male")
            .withEthnicity("Chinese")
            .build();

    public static final Medical MEDICAL_B = new MedicalBuilder()
            .withNric("G1234568L")
            .withAge("30")
            .withBloodType("O")
            .withMedication("Paracetamol 500mg twice a day")
            .withIllnesses("Mild fever")
            .withGender("Non-binary")
            .build();

    private TypicalMedicals() {}

    /**
     * Returns an {@code MedBook} with all the typical medicals.
     */
    public static MedBook getTypicalMedBook() {
        MedBook ab = new MedBook();
        for (Medical medical : getTypicalMedical()) {
            ab.addMedical(medical);
        }
        return ab;
    }

    public static List<Medical> getTypicalMedical() {
        return new ArrayList<>(Arrays.asList(MEDICAL_A, MEDICAL_A));
    }
}
