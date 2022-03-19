package seedu.address.model.medical;

import java.util.Objects;

import seedu.address.model.patient.Nric;

/**
 * Represents a Contact in Medbook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Medical {

    // Relationship fields
    private final Nric patientNric;

    // Data fields
    private final Age age;
    private final BloodType bloodType;
    private final Medication medication;
    private final Height height;
    private final Weight weight;
    private final Illnesses illnesses;
    private final Surgeries surgeries;
    private final FamilyHistory familyHistory;
    private final ImmunizationHistory immunizationHistory;
    private final Gender gender;
    private final Ethnicity ethnicity;

    /**
     * Every field must be present and not null.
     */
    public Medical(Nric patientNric,
                   Age age,
                   BloodType bloodType,
                   Medication medication,
                   Height height,
                   Weight weight,
                   Illnesses illnesses,
                   Surgeries surgeries,
                   FamilyHistory familyHistory,
                   ImmunizationHistory immunizationHistory,
                   Gender gender,
                   Ethnicity ethnicity) {
        this.patientNric = patientNric;
        this.age = age;
        this.bloodType = bloodType;
        this.medication = medication;
        this.height = height;
        this.weight = weight;
        this.illnesses = illnesses;
        this.surgeries = surgeries;
        this.familyHistory = familyHistory;
        this.immunizationHistory = immunizationHistory;
        this.gender = gender;
        this.ethnicity = ethnicity;
    }

    public Nric getPatientNric() {
        return patientNric;
    }

    public Age getAge() {
        return age;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public Medication getMedication() {
        return medication;
    }

    public Height getHeight() {
        return height;
    }

    public Weight getWeight() {
        return weight;
    }

    public Illnesses getIllnesses() {
        return illnesses;
    }

    public Surgeries getSurgeries() {
        return surgeries;
    }

    public FamilyHistory getFamilyHistory() {
        return familyHistory;
    }

    public ImmunizationHistory getImmunizationHistory() {
        return immunizationHistory;
    }

    public Gender getGender() {
        return gender;
    }

    public Ethnicity getEthnicity() {
        return ethnicity;
    }

    /**
     * Returns true if both contacts have the same identity and data fields.
     * This defines a stronger notion of equality between two contacts.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Medical)) {
            return false;
        }

        Medical otherContact = (Medical) other;
        return otherContact.getPatientNric().equals(getPatientNric());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(patientNric,
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
                ethnicity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Patient NRIC: ")
                .append(getPatientNric())
                .append("; Age: ")
                .append(getAge())
                .append("; Blood type: ")
                .append(getBloodType())
                .append("; Medication: ")
                .append(getMedication())
                .append("; Height: ")
                .append(getHeight())
                .append("; Weight: ")
                .append(getWeight())
                .append("; Illnesses: ")
                .append(getIllnesses())
                .append("; Surgeries: ")
                .append(getSurgeries())
                .append("; Family history: ")
                .append(getFamilyHistory())
                .append("; Immunization history: ")
                .append(getImmunizationHistory())
                .append("; Gender ")
                .append(getGender())
                .append("; Ethnicity ")
                .append(getEthnicity());

        return builder.toString();
    }

}
