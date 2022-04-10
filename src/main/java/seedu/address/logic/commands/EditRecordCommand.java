package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_CLIENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_ENDDATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_INSURANCEID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REC_STARTDATE;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurance.Insurance;
import seedu.address.model.person.Person;
import seedu.address.model.record.ClientID;
import seedu.address.model.record.EndDate;
import seedu.address.model.record.InsuranceID;
import seedu.address.model.record.Record;
import seedu.address.model.record.StartDate;

/**
 * Edits the details of an existing record in the record book.
 */
public class EditRecordCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -r : Edits the details of the record identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_REC_CLIENTID + "CLIENT INDEX] "
            + "[" + PREFIX_REC_INSURANCEID + "INSURANCE INDEX] "
            + "[" + PREFIX_REC_STARTDATE + "START DATE] "
            + "[" + PREFIX_REC_ENDDATE + "END DATE]\n"
            + "Example: " + COMMAND_WORD + "-r 1 "
            + PREFIX_REC_CLIENTID + "2 "
            + PREFIX_REC_INSURANCEID + "3 ";

    public static final String MESSAGE_EDIT_RECORD_SUCCESS = "Edited Record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RECORD = "This record already exists in the address book.";
    public static final String MESSAGE_STARTDATE_BEFORE_ENDATE = "Record start date must be before end date! "
            + "Please check Start date/ end date entered!";

    private final Index index;
    private final EditRecordDescriptor editRecordDescriptor;
    private Record editedRecord;

    /**
     * @param index                of the record in the filtered record list to edit
     * @param editRecordDescriptor details to edit the record with
     */
    public EditRecordCommand(Index index, EditRecordDescriptor editRecordDescriptor) {
        requireNonNull(index);
        requireNonNull(editRecordDescriptor);

        this.index = index;
        this.editRecordDescriptor = new EditRecordDescriptor(editRecordDescriptor);
    }

    /**
     * Creates and returns a {@code Record} with the details of {@code recordToEdit}
     * edited with {@code editRecordDescriptor}.
     */

    private static Record createEditedRecord(Record recordToEdit,
                                             EditRecordDescriptor editRecordDescriptor) {
        assert recordToEdit != null;

        ClientID updatedClientID = editRecordDescriptor.getClientID().orElse(recordToEdit.getClientID());
        InsuranceID updatedInsuranceID = editRecordDescriptor.getInsuranceID().orElse(recordToEdit.getInsuranceID());
        StartDate updatedStartDate = editRecordDescriptor.getStartDate().orElse(recordToEdit.getStartDate());
        EndDate updatedEndDate = editRecordDescriptor.getEndDate().orElse(recordToEdit.getEndDate());

        return new Record(updatedClientID, updatedInsuranceID, updatedStartDate, updatedEndDate);

    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Record> lastRecordShownList = model.getFilteredRecordList();
        List<Person> lastPersonShownList = model.getFilteredPersonList();
        List<Insurance> lastInsuranceList = model.getFilteredInsuranceList();

        if (editRecordDescriptor.getClientID().isPresent()) {

            int clientIndex = Integer.parseInt(editRecordDescriptor.getClientID().get().toString()) - 1;

            if (clientIndex >= lastPersonShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            ClientID clientIdName = new ClientID(lastPersonShownList.get(clientIndex).getName().fullName, true);
            editRecordDescriptor.setClientID(clientIdName);
        }

        if (editRecordDescriptor.getInsuranceID().isPresent()) {
            int recordIndex = Integer.parseInt(editRecordDescriptor.getInsuranceID().get().toString()) - 1;

            if (recordIndex >= lastInsuranceList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_INSURANCE_DISPLAYED_INDEX);
            }

            InsuranceID insuranceIdName =
                    new InsuranceID(lastInsuranceList.get(recordIndex).getTitle().title, true);
            editRecordDescriptor.setInsuranceID(insuranceIdName);
        }

        if (editRecordDescriptor.getStartDate().isPresent() || editRecordDescriptor.getEndDate().isPresent()) {

            StartDate startDate = editRecordDescriptor.getStartDate().orElse(
                    lastRecordShownList.get(index.getZeroBased()).getStartDate());
            EndDate endDate = editRecordDescriptor.getEndDate().orElse(
                    lastRecordShownList.get(index.getZeroBased()).getEndDate());
            if (startDate.getStartDate().isAfter(endDate.getEndDate())) {
                throw new CommandException(MESSAGE_STARTDATE_BEFORE_ENDATE);
            }

        }

        Record recordToEdit = lastRecordShownList.get(index.getZeroBased());
        editedRecord = createEditedRecord(recordToEdit, editRecordDescriptor);

        if (!recordToEdit.isSameRecord(editedRecord) && model.hasRecord(editedRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECORD);
        }

        model.setRecord(recordToEdit, editedRecord);
        Predicate<Record> predicate = r -> r.getEndLocalDate().isAfter(LocalDate.now());
        model.updateFilteredRecordList(predicate);
        return new CommandResult(String.format(MESSAGE_EDIT_RECORD_SUCCESS, editedRecord));

    }

    /**
     * Returns the edited Record.
     */
    public Record getEditedRecord() {
        return editedRecord;
    }

    /**
     * Returns the edited Record index.
     */
    public int getEditedRecordIndex() {
        return index.getOneBased();
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditRecordCommand)) {
            return false;
        }

        // state check
        EditRecordCommand e = (EditRecordCommand) other;
        return index.equals(e.index)
                && editRecordDescriptor.equals(e.editRecordDescriptor);
    }


    /**
     * Stores the details to edit the record with. Each non-empty field value will replace the
     * corresponding field value of the record.
     */
    public static class EditRecordDescriptor {
        private ClientID clientID;
        private InsuranceID insuranceID;
        private StartDate startDate;
        private EndDate endDate;

        public EditRecordDescriptor() {

        }

        /**
         * Copy constructor.
         * A defensive copy of {@code toCopy} is used internally.
         */
        public EditRecordDescriptor(EditRecordDescriptor toCopy) {
            setClientID(toCopy.clientID);
            setInsuranceID(toCopy.insuranceID);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(clientID, insuranceID, startDate, endDate);
        }

        public Optional<ClientID> getClientID() {
            return Optional.ofNullable(clientID);
        }

        public void setClientID(ClientID clientID) {
            this.clientID = clientID;
        }

        public Optional<InsuranceID> getInsuranceID() {
            return Optional.ofNullable(insuranceID);
        }

        public void setInsuranceID(InsuranceID insuranceID) {
            this.insuranceID = insuranceID;
        }

        public Optional<StartDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setStartDate(StartDate startDate) {
            this.startDate = startDate;
        }

        public Optional<EndDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }

        public void setEndDate(EndDate endDate) {
            this.endDate = endDate;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditRecordDescriptor)) {
                return false;
            }

            // state check
            EditRecordDescriptor e = (EditRecordDescriptor) other;

            return getClientID().equals(e.getClientID())
                    && getInsuranceID().equals(e.getInsuranceID())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate());
        }

    }

}

