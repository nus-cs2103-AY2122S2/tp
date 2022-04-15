package seedu.address.logic.commands.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DIAGNOSIS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.consultation.Consultation;
import seedu.address.model.consultation.ConsultationDiagnosis;
import seedu.address.model.consultation.ConsultationFee;
import seedu.address.model.consultation.ConsultationNotes;
import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.patient.Nric;

/**
 * Edits the details of an existing consultation in MedBook.
 */
public class EditConsultationCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final CommandType COMMAND_TYPE = CommandType.CONSULTATION;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the consultation identified "
            + "by the index number used in the displayed consultation list. "
            + "Existing values will be overwritten by the input values.\n"
            + "NRIC FIELD CANNOT BE MODIFIED - CREATE A NEW CONSULTATION WITH THE CORRECT NRIC INSTEAD.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_DIAGNOSIS + "DIAGNOSIS] "
            + "[" + PREFIX_FEE + "FEE] "
            + "[" + PREFIX_NOTES + "NOTES] \n"
            + "Example: " + COMMAND_WORD
            + " 1 "
            + PREFIX_DATE + "2020-09-20 "
            + PREFIX_TIME + "19-00 ";

    public static final String MESSAGE_EDIT_TEST_RESULT_SUCCESS = "Edited Consultation Information: \n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CONSULTATION = "This consultation already exists in MedBook.";
    public static final String MESSAGE_NRIC_EDIT_NOT_ALLOWED =
            "NRIC field cannot be modified. Create a new consultation with the correct NRIC instead.";

    private final Index targetIndex;
    private final EditConsultationDescriptor editConsultationDescriptor;

    /**
     * @param targetIndex of the consultation in the filtered consultation list to edit
     * @param editConsultationDescriptor details to edit the consultation with
     */
    public EditConsultationCommand(Index targetIndex, EditConsultationDescriptor editConsultationDescriptor) {
        this.targetIndex = targetIndex;
        this.editConsultationDescriptor = new EditConsultationDescriptor(editConsultationDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (targetIndex.getZeroBased() >= lastShownList.size() || targetIndex.getZeroBased() < 0) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONSULTATION_INDEX);
        }

        Consultation consultation = lastShownList.get(targetIndex.getZeroBased());
        Consultation editedConsultation = createEditedConsultation(consultation, editConsultationDescriptor);

        if (!consultation.equals(editedConsultation) && model.hasConsultation(editedConsultation)) {
            throw new CommandException(MESSAGE_DUPLICATE_CONSULTATION);
        }

        model.setConsultation(consultation, editedConsultation);

        return new CommandResult(String.format(MESSAGE_EDIT_TEST_RESULT_SUCCESS, editedConsultation), COMMAND_TYPE);
    }

    private static Consultation createEditedConsultation(Consultation consultation,
                                                     EditConsultationDescriptor editConsultationDescriptor) {
        assert consultation != null;

        Nric nric = consultation.getNric();
        Date updatedDate = editConsultationDescriptor.getDate().orElse(consultation.getDate());
        Time updatedTime = editConsultationDescriptor.getTime().orElse(consultation.getTime());
        ConsultationDiagnosis updatedDiagnosis =
                editConsultationDescriptor.getDiagnosis().orElse(consultation.getDiagnosis());
        ConsultationFee updatedFee =
                editConsultationDescriptor.getFee().orElse(consultation.getFee());
        ConsultationNotes updatedNotes =
                editConsultationDescriptor.getNotes().orElse(consultation.getNotes());

        return new Consultation(nric,
                updatedDate,
                updatedTime,
                updatedDiagnosis,
                updatedFee,
                updatedNotes
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditConsultationCommand // instanceof handles nulls
                && targetIndex.equals(((EditConsultationCommand) other).targetIndex)); // state check
    }

    /**
     * Stores the details to edit the consultation with. Each non-empty field value will replace the
     * corresponding field value of the consultation.
     */
    public static class EditConsultationDescriptor {
        private Date date;
        private Time time;
        private ConsultationDiagnosis diagnosis;
        private ConsultationFee fee;
        private ConsultationNotes notes;

        public EditConsultationDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditConsultationDescriptor(EditConsultationDescriptor toCopy) {
            setDate(toCopy.date);
            setTime(toCopy.time);
            setDiagnosis(toCopy.diagnosis);
            setFee(toCopy.fee);
            setNotes(toCopy.notes);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, time, diagnosis, fee, notes);
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<ConsultationDiagnosis> getDiagnosis() {
            return Optional.ofNullable(diagnosis);
        }

        public void setDiagnosis(ConsultationDiagnosis diagnosis) {
            this.diagnosis = diagnosis;
        }

        public Optional<ConsultationFee> getFee() {
            return Optional.ofNullable(fee);
        }

        public void setFee(ConsultationFee fee) {
            this.fee = fee;
        }

        public Optional<ConsultationNotes> getNotes() {
            return Optional.ofNullable(notes);
        }

        public void setNotes(ConsultationNotes notes) {
            this.notes = notes;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditConsultationDescriptor)) {
                return false;
            }

            // state check
            EditConsultationDescriptor e = (EditConsultationDescriptor) other;

            return getDate().equals(e.getDate())
                    && getTime().equals(e.getTime())
                    && getDiagnosis().equals(e.getDiagnosis())
                    && getFee().equals(e.getFee())
                    && getNotes().equals(e.getNotes());
        }
    }
}
