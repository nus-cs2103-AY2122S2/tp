package seedu.address.testutil;

import seedu.address.model.medical.Age;
import seedu.address.model.medical.BloodType;
import seedu.address.model.medical.Ethnicity;
import seedu.address.model.medical.FamilyHistory;
import seedu.address.model.medical.Gender;
import seedu.address.model.medical.Height;
import seedu.address.model.medical.Illnesses;
import seedu.address.model.medical.ImmunizationHistory;
import seedu.address.model.medical.Medical;
import seedu.address.model.medical.Medication;
import seedu.address.model.medical.Surgeries;
import seedu.address.model.medical.Weight;
import seedu.address.model.patient.Nric;

/**
 * A utility class to help with building Patient objects.
 */
public class MedicalBuilder {

    public static final String DEFAULT_NRIC = "S7654321L";

    private Nric nric;
    private Age age;
    private BloodType bloodType;
    private Medication medication;
    private Height height;
    private Weight weight;
    private Illnesses illnesses;
    private Surgeries surgeries;
    private FamilyHistory familyHistory;
    private ImmunizationHistory immunizationHistory;
    private Gender gender;
    private Ethnicity ethnicity;

    /**
     * Creates a {@code MedicalBuilder} with the default details.
     */
    public MedicalBuilder() {
        nric = new Nric(DEFAULT_NRIC);
    }

    /**
     * Sets the {@code Nric} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withNric(String nric) {
        this.nric = new Nric(nric);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withAge(String age) {
        this.age = new Age(age);
        return this;
    }

    /**
     * Sets the {@code Age} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withBloodType(String bloodType) {
        this.bloodType = new BloodType(bloodType);
        return this;
    }

    /**
     * Sets the {@code Medication} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withMedication(String medication) {
        this.medication = new Medication(medication);
        return this;
    }

    /**
     * Sets the {@code Height} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withHeight(String height) {
        this.height = new Height(height);
        return this;
    }

    /**
     * Sets the {@code Weight} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withWeight(String weight) {
        this.weight = new Weight(weight);
        return this;
    }

    /**
     * Sets the {@code Illnesses} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withIllnesses(String illnesses) {
        this.illnesses = new Illnesses(illnesses);
        return this;
    }

    /**
     * Sets the {@code Surgeries} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withSurgeries(String surgeries) {
        this.surgeries = new Surgeries(surgeries);
        return this;
    }

    /**
     * Sets the {@code FamilyHistory} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withFamilyHistory(String familyHistory) {
        this.familyHistory = new FamilyHistory(familyHistory);
        return this;
    }

    /**
     * Sets the {@code ImmunizationHistory} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withImmunizationHistory(String immunizationHistory) {
        this.immunizationHistory = new ImmunizationHistory(immunizationHistory);
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withGender(String gender) {
        this.gender = new Gender(gender);
        return this;
    }

    /**
     * Sets the {@code Ethnicity} of the {@code Medical} that we are building.
     */
    public MedicalBuilder withEthnicity(String ethnicity) {
        this.ethnicity = new Ethnicity(ethnicity);
        return this;
    }

    /**
     * Builds {@code Medical} object
     */
    public Medical build() {
        return new Medical(
                nric,
                age,
                bloodType,
                medication,
                height,
                weight,
                illnesses,
                surgeries,
                familyHistory,
                immunizationHistory,
                gender,
                ethnicity
        );
    }
}
