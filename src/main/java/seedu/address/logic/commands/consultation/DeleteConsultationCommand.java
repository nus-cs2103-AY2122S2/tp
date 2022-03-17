package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationWithPredicates;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.patient.Nric;
import seedu.address.model.patient.NricPredicate;
import seedu.address.model.patient.Patient;


/**
 * Lists all persons in the address book to the user.
 */
public class DeleteConsultationCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the consultation that "
            + "matches the specified owner NRIC, date and time.\n"
            + "Parameters: TYPE, OWNER NRIC, DATE, TIME\n"
            + "Example: " + COMMAND_WORD
            + PREFIX_TYPE + "consultation "
            + PREFIX_NRIC + " S1234567L "
            + PREFIX_DATE + "19-09-2021 "
            + PREFIX_TIME + "19-00";
    public static final String MESSAGE_CONSULTATION_DELETE_OVERVIEW =
            "Delete consultation successful: %1$s, %2$s %3$s \n"
            + "Remaining %1$s consultations shown below.";
    public static final String MESSAGE_MISSING_CONSULTATION = "Consultation to delete is missing. \n"
            + "Cannot find %1$s, %2$s %3$s.";

    private final Nric ownerNric;
    private final Date date;
    private final Time time;


    /**
     * Creates an ViewContactCommand to view the specified {@code Patient}
     */
    public DeleteConsultationCommand(Nric ownerNric, Date date, Time time) {
        requireNonNull(ownerNric);
        requireNonNull(date);
        requireNonNull(time);
        this.ownerNric = ownerNric;
        this.date = date;
        this.time = time;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ObservableList<Patient> personList = model.getAddressBook().getPersonList();
        String nameAndNric = new String();

        for (Patient patient : personList) {
            if (patient.getNric().equals(ownerNric)) {
                nameAndNric = patient.getName().toString() + " / " + ownerNric;
            }
        }

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        if (!model.hasConsultation(new Consultation(ownerNric, date, time, null, null, null))) {
            throw new CommandException(String.format(MESSAGE_MISSING_CONSULTATION, nameAndNric, date, time));
        }

        model.deleteConsultation(new Consultation(ownerNric, date, time, null, null, null));
        model.updateFilteredConsultationList(new ConsultationWithPredicates(ownerNric));

        return new CommandResult(
                String.format(MESSAGE_CONSULTATION_DELETE_OVERVIEW,
                        nameAndNric, date, time),
                COMMAND_TYPE);
    }
}
