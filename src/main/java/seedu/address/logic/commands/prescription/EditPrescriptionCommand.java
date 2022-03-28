package seedu.address.logic.commands.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.patient.Nric;
import seedu.address.model.prescription.DrugName;
import seedu.address.model.prescription.Instruction;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.PrescriptionDate;


public class EditPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final CommandType COMMAND_TYPE = CommandType.PRESCRIPTION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the prescription identified "
            + "by the index number used in the displayed prescription information list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_NRIC + "PATIENT_NRIC "
            + PREFIX_NAME + "DRUG_NAME "
            + PREFIX_DATE + "DATE "
            + PREFIX_INSTRUCTION + "INSTRUCTION \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NRIC + "S1234567L "
            + PREFIX_INSTRUCTION + "1 tablet per day";

    public static final String MESSAGE_EDIT_PRESCRIPTION_SUCCESS = "Edited Prescription Information: %1$s";

    private final Index targetIndex;
    private final EditPrescriptionCommand.EditPrescriptionDescriptor editPrescriptionDescriptor;

    /**
     * @param targetIndex of the test result information in the filtered test result information list to edit
     * @param editPrescriptionDescriptor details to edit the test result information with
     */
    public EditPrescriptionCommand(Index targetIndex,
                                   EditPrescriptionCommand.EditPrescriptionDescriptor editPrescriptionDescriptor) {
        this.targetIndex = targetIndex;
        this.editPrescriptionDescriptor = editPrescriptionDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Prescription> lastShownList = model.getFilteredPrescriptionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEST_RESULT_INDEX);
        }

        Prescription prescription = lastShownList.get(targetIndex.getZeroBased());
        Prescription editedPrescription = createEditedPrescription(prescription, editPrescriptionDescriptor);
        model.setPrescription(prescription, editedPrescription);

        return new CommandResult(String.format(MESSAGE_EDIT_PRESCRIPTION_SUCCESS, editedPrescription), COMMAND_TYPE);
    }

    private static Prescription createEditedPrescription(Prescription prescription,
                                                     EditPrescriptionCommand.EditPrescriptionDescriptor
                                                             editPrescriptionDescriptor) {
        assert prescription != null;

        Nric updatedNric = editPrescriptionDescriptor.getNric().orElse(prescription.getPrescriptionTarget());
        DrugName updatedDrugName = editPrescriptionDescriptor.getDrugName().orElse(prescription.getDrugName());
        PrescriptionDate updatedPrescriptionDate =
                editPrescriptionDescriptor.getPrescriptionDate().orElse(prescription.getPrescriptionDate());
        Instruction updatedInstruction =
                editPrescriptionDescriptor.getInstruction().orElse(prescription.getInstruction());

        return new Prescription(updatedNric,
                updatedDrugName,
                updatedPrescriptionDate,
                updatedInstruction
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditPrescriptionCommand // instanceof handles nulls
                && targetIndex.equals(((EditPrescriptionCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the test result information with. Each non-empty field value will replace the
     * corresponding field value of the test result information.
     */
    public static class EditPrescriptionDescriptor {
        private Nric nric;
        private DrugName drugName;
        private PrescriptionDate date;
        private Instruction instruction;

        public EditPrescriptionDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPrescriptionDescriptor(EditPrescriptionCommand.EditPrescriptionDescriptor toCopy) {
            setNric(toCopy.nric);
            setDrugName(toCopy.drugName);
            setPrescriptionDate(toCopy.date);
            setResult(toCopy.instruction);
        }

        public Optional<Nric> getNric() {
            return Optional.ofNullable(nric);
        }

        public void setNric(Nric nric) {
            this.nric = nric;
        }

        public Optional<DrugName> getDrugName() {
            return Optional.ofNullable(drugName);
        }

        public void setDrugName(DrugName drugName) {
            this.drugName = drugName;
        }

        public Optional<PrescriptionDate> getPrescriptionDate() {
            return Optional.ofNullable(date);
        }

        public void setPrescriptionDate(PrescriptionDate prescriptionDate) {
            this.date = prescriptionDate;
        }

        public Optional<Instruction> getInstruction() {
            return Optional.ofNullable(instruction);
        }

        public void setResult(Instruction instruction) {
            this.instruction = instruction;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPrescriptionCommand.EditPrescriptionDescriptor)) {
                return false;
            }

            // state check
            EditPrescriptionCommand.EditPrescriptionDescriptor e =
                    (EditPrescriptionCommand.EditPrescriptionDescriptor) other;

            return getNric().equals(e.getNric())
                    && getDrugName().equals(e.getDrugName())
                    && getPrescriptionDate().equals(e.getPrescriptionDate())
                    && getInstruction().equals(e.getInstruction());
        }
    }
}
