package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewConsultationCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows consultations that "
            + "matches the specified owner NRIC, and displays them as a list with index numbers.\n"
            + "Parameters: TYPE, OWNER NRIC\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_TYPE + "consultation "
            + PREFIX_NRIC + " S1234567L";

    private final Nric ownerNric;

    /**
     * Creates an ViewContactCommand to view the specified {@code Patient}
     */
    public ViewConsultationCommand(Nric ownerNric) {
        requireNonNull(ownerNric);
        this.ownerNric = ownerNric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredConsultationList(new ConsultationWithPredicates(ownerNric));

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        ObservableList<Patient> personList = model.getAddressBook().getPersonList();
        String nameAndNric = new String();

        for (Patient patient : personList) {
            if (patient.getNric().equals(ownerNric)) {
                nameAndNric = patient.getName().toString() + " / " + ownerNric;
            }
        }

        System.out.println("ViewConsultationCommand.java: " + COMMAND_TYPE);

        return new CommandResult(
                String.format(Messages.MESSAGE_CONSULTATION_LISTED_OVERVIEW,
                        model.getFilteredConsultationList().size(), nameAndNric),
                COMMAND_TYPE);
    }
}

