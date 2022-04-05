package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.address.logic.commands.prescription.AddPrescriptionCommand;
import seedu.address.logic.commands.prescription.EditPrescriptionCommand.EditPrescriptionDescriptor;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class for Prescription.
 */
public class PrescriptionUtil {

    /**
     * Returns an add prescription command string for adding the {@code patient}.
     */
    public static String getAddPrescriptionCommand(Prescription prescription) {
        return AddPrescriptionCommand.COMMAND_WORD + " " + getPrescriptionDetails(prescription);
    }

    /**
     * Returns the part of command string for the given {@code prescription}'s details.
     */
    public static String getPrescriptionDetails(Prescription prescription) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TYPE + "prescription");
        sb.append(PREFIX_NRIC + prescription.getPrescriptionTarget().value + " ");
        sb.append(PREFIX_NAME + prescription.getDrugName().drugName + " ");
        sb.append(PREFIX_DATE + prescription.getPrescriptionDate().toDefaultString() + " ");
        sb.append(PREFIX_INSTRUCTION + prescription.getInstruction().value + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPrescriptionDescriptor}'s details.
     */
    public static String getEditPrescriptionDescriptorDetails(EditPrescriptionDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDrugName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.drugName).append(" "));
        descriptor.getPrescriptionDate().ifPresent(date -> sb.append(PREFIX_DATE)
                .append(date.toDefaultString()).append(" "));
        descriptor.getInstruction().ifPresent(instruction -> sb.append(PREFIX_INSTRUCTION)
                .append(instruction.value).append(" "));
        return sb.toString();
    }
}
