package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
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
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Nric;

public class JsonAdaptedMedical {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Contact's %s field is missing!";

    private final String nric;
    private final String age;
    private final String bloodType;
    private final String medication;
    private final String weight;
    private final String height;
    private final String illnesses;
    private final String surgeries;
    private final String familyHistory;
    private final String immunizationHistory;
    private final String gender;
    private final String ethnicity;

    /**
     * Constructs a {@code JsonAdaptedMedical} with the given prescription details.
     */
    @JsonCreator
    public JsonAdaptedMedical(@JsonProperty("nric") String nric,
                              @JsonProperty("age") String age,
                              @JsonProperty("bloodType") String bloodType,
                              @JsonProperty("medication") String medication,
                              @JsonProperty("weight") String weight,
                              @JsonProperty("height") String height,
                              @JsonProperty("illnesses") String illnesses,
                              @JsonProperty("surgeries") String surgeries,
                              @JsonProperty("familyHistory") String familyHistory,
                              @JsonProperty("immunizationHistory") String immunizationHistory,
                              @JsonProperty("gender") String gender,
                              @JsonProperty("ethnicity") String ethnicity
                              ) {
        this.nric = nric;
        this.age = age;
        this.bloodType = bloodType;
        this.medication = medication;
        this.weight = weight;
        this.height = height;
        this.illnesses = illnesses;
        this.surgeries = surgeries;
        this.familyHistory = familyHistory;
        this.immunizationHistory = immunizationHistory;
        this.gender = gender;
        this.ethnicity = ethnicity;
    }

    /**
     * Converts a given {@code Medical} into this class for Jackson use.
     */
    public JsonAdaptedMedical(Medical source) {
        nric = source.getPatientNric().value;
        age = source.getAge().value;
        bloodType = source.getBloodType().value;
        medication = source.getMedication().value;
        weight = source.getWeight().value;
        height = source.getHeight().value;
        illnesses = source.getIllnesses().value;
        surgeries = source.getSurgeries().value;
        familyHistory = source.getFamilyHistory().value;
        immunizationHistory = source.getImmunizationHistory().value;
        gender = source.getGender().value;
        ethnicity = source.getEthnicity().value;
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Medical} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted prescription.
     */
    public Medical toModelType() throws IllegalValueException {

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Nric.class.getSimpleName()));
        }
        if (!Nric.isValidNric(nric)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Nric modelNric = new Nric(nric);

        if (age == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Age.class.getSimpleName()));
        }

        if (!Age.isValidAge(age)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Age modelAge = new Age(age);

        if (bloodType == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            BloodType.class.getSimpleName())
            );
        }
        if (!BloodType.isValidBloodType(bloodType)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final BloodType modelBloodType = new BloodType(bloodType);


        if (medication == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Medication.class.getSimpleName())
            );
        }
        if (!Medication.isValidMedication(medication)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Medication modelMedication = new Medication(medication);


        if (weight == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName()));
        }
        if (!Weight.isValidWeight(weight)) {
            throw new IllegalValueException(Weight.MESSAGE_CONSTRAINTS);
        }
        final Weight modelWeight = new Weight(weight);


        if (height == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName()));
        }
        if (!Height.isValidHeight(weight)) {
            throw new IllegalValueException(Height.MESSAGE_CONSTRAINTS);
        }
        final Height modelHeight = new Height(height);


        if (illnesses == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Illnesses.class.getSimpleName())
            );
        }
        if (!Illnesses.isValidIllnesses(illnesses)) {
            throw new IllegalValueException(Illnesses.MESSAGE_CONSTRAINTS);
        }
        final Illnesses modelIllnesses = new Illnesses(illnesses);


        if (surgeries == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Surgeries.class.getSimpleName())
            );
        }
        if (!Surgeries.isValidSurgeries(surgeries)) {
            throw new IllegalValueException(Surgeries.MESSAGE_CONSTRAINTS);
        }
        final Surgeries modelSurgeries = new Surgeries(surgeries);


        if (familyHistory == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            FamilyHistory.class.getSimpleName())
            );
        }
        if (!FamilyHistory.isValidFamilyHistory(familyHistory)) {
            throw new IllegalValueException(FamilyHistory.MESSAGE_CONSTRAINTS);
        }
        final FamilyHistory modelFamilyHistory = new FamilyHistory(familyHistory);


        if (immunizationHistory == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            ImmunizationHistory.class.getSimpleName())
            );
        }
        if (!ImmunizationHistory.isValidImmunizationHistory(immunizationHistory)) {
            throw new IllegalValueException(ImmunizationHistory.MESSAGE_CONSTRAINTS);
        }
        final ImmunizationHistory modelImmunizationHistory = new ImmunizationHistory(immunizationHistory);


        if (gender == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Gender.class.getSimpleName())
            );
        }
        if (!Gender.isValidGender(gender)) {
            throw new IllegalValueException(Gender.MESSAGE_CONSTRAINTS);
        }
        final Gender modelGender = new Gender(gender);



        if (ethnicity == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT,
                            Ethnicity.class.getSimpleName())
            );
        }
        if (!Ethnicity.isValidEthnicity(ethnicity)) {
            throw new IllegalValueException(Ethnicity.MESSAGE_CONSTRAINTS);
        }
        final Ethnicity modelEthnicity = new Ethnicity(ethnicity);


        return new Medical(modelNric,
                            modelAge,
                            modelBloodType,
                            modelMedication,
                            modelHeight,
                            modelWeight,
                            modelIllnesses,
                            modelSurgeries,
                            modelFamilyHistory,
                            modelImmunizationHistory,
                            modelGender,
                            modelEthnicity
                );
    }
}
