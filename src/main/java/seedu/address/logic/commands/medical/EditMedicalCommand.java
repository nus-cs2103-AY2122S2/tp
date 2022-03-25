package seedu.address.logic.commands.medical;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOODTYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ETHNICITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FAMILY_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ILLNESSES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMMUNIZATION_HISTORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SURGERIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;
import seedu.address.model.Model;
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
 * Edits the details of an existing medical information in MedBook.
 */
public class EditMedicalCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final CommandType COMMAND_TYPE = CommandType.MEDICAL;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the medical information identified "
            + "by the index number used in the displayed medical information list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NRIC + "NRIC "
            + PREFIX_AGE + "AGE "
            + PREFIX_BLOODTYPE + "BLOODTYPE "
            + PREFIX_MEDICATION + "MEDICATION "
            + PREFIX_HEIGHT + "HEIGHT "
            + PREFIX_WEIGHT + "WEIGHT "
            + PREFIX_ILLNESSES + "ILLNESSES "
            + PREFIX_SURGERIES + "SURGERIES "
            + PREFIX_FAMILY_HISTORY + "FAMILY_HISTORY "
            + PREFIX_IMMUNIZATION_HISTORY + "IMMUNIZATION_HISTORY "
            + PREFIX_GENDER + "GENDER "
            + PREFIX_ETHNICITY + "ETHNICITY\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_BLOODTYPE + "B";

    public static final String MESSAGE_EDIT_MEDICAL_SUCCESS = "Edited Medical Information: %1$s";

    private final Index targetIndex;
    private final EditMedicalCommand.EditMedicalDescriptor editMedicalDescriptor;

    public EditMedicalCommand(Index targetIndex, EditMedicalCommand.EditMedicalDescriptor editMedicalDescriptor) {
        this.targetIndex = targetIndex;
        this.editMedicalDescriptor = editMedicalDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Medical> lastShownList = model.getFilteredMedicalList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_MEDICAL_INFORMATION_INDEX);
        }

        Medical medicalToEdit = lastShownList.get(targetIndex.getZeroBased());
        Medical editedMedical = createEditedMedical(medicalToEdit, editMedicalDescriptor);
        
        model.setMedical(medicalToEdit, editedMedical);
        
        return new CommandResult(String.format(MESSAGE_EDIT_MEDICAL_SUCCESS, editedMedical), COMMAND_TYPE);
    }

    private static Medical createEditedMedical(Medical medicalToEdit, EditMedicalCommand.EditMedicalDescriptor editMedicalDescriptor) {
        assert medicalToEdit != null;

        Nric updatedNric = editMedicalDescriptor.getNric().orElse(medicalToEdit.getPatientNric());
        Age updatedAge = editMedicalDescriptor.getAge().orElse(medicalToEdit.getAge());
        BloodType updatedBloodType
                = editMedicalDescriptor.getBloodType().orElse(medicalToEdit.getBloodType());
        Medication updatedMedication
                = editMedicalDescriptor.getMedication().orElse(medicalToEdit.getMedication());
        Height updatedHeight
                = editMedicalDescriptor.getHeight().orElse(medicalToEdit.getHeight());
        Weight updatedWeight
                = editMedicalDescriptor.getWeight().orElse(medicalToEdit.getWeight());
        Illnesses updatedIllnesses
                = editMedicalDescriptor.getIllnesses().orElse(medicalToEdit.getIllnesses());
        Surgeries updatedSurgeries
                = editMedicalDescriptor.getSurgeries().orElse(medicalToEdit.getSurgeries());
        FamilyHistory updatedFamilyHistory
                = editMedicalDescriptor.getFamilyHistory().orElse(medicalToEdit.getFamilyHistory());
        ImmunizationHistory updatedImmunizationHistory
                = editMedicalDescriptor.getImmunizationHistory().orElse(medicalToEdit.getImmunizationHistory());
        Gender updatedGender
                = editMedicalDescriptor.getGender().orElse(medicalToEdit.getGender());
        Ethnicity updatedEthnicity
                = editMedicalDescriptor.getEthnicity().orElse(medicalToEdit.getEthnicity());

        return new Medical(updatedNric,
                updatedAge,
                updatedBloodType,
                updatedMedication,
                updatedHeight,
                updatedWeight,
                updatedIllnesses,
                updatedSurgeries,
                updatedFamilyHistory,
                updatedImmunizationHistory,
                updatedGender,
                updatedEthnicity
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditMedicalCommand // instanceof handles nulls
                && targetIndex.equals(((EditMedicalCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the medical information with. Each non-empty field value will replace the
     * corresponding field value of the medical information.
     */
    public static class EditMedicalDescriptor {
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

        public EditMedicalDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMedicalDescriptor(EditMedicalCommand.EditMedicalDescriptor toCopy) {
            setNric(toCopy.nric);
            setAge(toCopy.age);
            setBloodType(toCopy.bloodType);
            setMedication(toCopy.medication);
            setHeight(toCopy.height);
            setWeight(toCopy.weight);
            setIllnesses(toCopy.illnesses);
            setSurgeries(toCopy.surgeries);
            setFamilyHistory(toCopy.familyHistory);
            setImmunizationHistory(toCopy.immunizationHistory);
            setGender(toCopy.gender);
            setEthnicity(toCopy.ethnicity);
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Optional<Age> getAge() {
            return Optional.ofNullable(age);
        }

        public void setAge(Age age) {
            this.age = age;
        }

        public Optional<BloodType> getBloodType() {
            return Optional.ofNullable(bloodType);
        }

        public void setBloodType(BloodType bloodType) {
            this.bloodType = bloodType;
        }

        public Optional<Medication> getMedication() {
            return Optional.ofNullable(medication);
        }

        public void setMedication(Medication medication) {
            this.medication = medication;
        }

        public Optional<Height> getHeight() {
            return Optional.ofNullable(height);
        }

        public void setHeight(Height height) {
            this.height = height;
        }

        public Optional<Weight> getWeight() {
            return Optional.ofNullable(weight);
        }

        public void setWeight(Weight weight) {
            this.weight = weight;
        }

        public Optional<Illnesses> getIllnesses() {
            return Optional.ofNullable(illnesses);
        }

        public void setIllnesses(Illnesses illnesses) {
            this.illnesses = illnesses;
        }

        public Optional<Surgeries> getSurgeries() {
            return Optional.ofNullable(surgeries);
        }

        public void setSurgeries(Surgeries surgeries) {
            this.surgeries = surgeries;
        }

        public Optional<FamilyHistory> getFamilyHistory() {
            return Optional.ofNullable(familyHistory);
        }

        public void setFamilyHistory(FamilyHistory familyHistory) {
            this.familyHistory = familyHistory;
        }

        public Optional<ImmunizationHistory> getImmunizationHistory() {
            return Optional.ofNullable(immunizationHistory);
        }

        public void setImmunizationHistory(ImmunizationHistory immunizationHistory) {
            this.immunizationHistory = immunizationHistory;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Ethnicity> getEthnicity() {
            return Optional.ofNullable(ethnicity);
        }

        public void setEthnicity(Ethnicity ethnicity) {
            this.ethnicity = ethnicity;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditMedicalCommand.EditMedicalDescriptor e = (EditMedicalCommand.EditMedicalDescriptor) other;

            return getNric().equals(e.getNric())
                    && getAge().equals(e.getAge())
                    && getBloodType().equals(e.getBloodType())
                    && getMedication().equals(e.getMedication())
                    && getHeight().equals(e.getHeight())
                    && getWeight().equals(e.getWeight())
                    && getIllnesses().equals(e.getIllnesses())
                    && getSurgeries().equals(e.getSurgeries())
                    && getFamilyHistory().equals(e.getFamilyHistory())
                    && getImmunizationHistory().equals(e.getImmunizationHistory())
                    && getGender().equals(e.getGender())
                    && getEthnicity().equals(e.getEthnicity());
        }
    }
}
