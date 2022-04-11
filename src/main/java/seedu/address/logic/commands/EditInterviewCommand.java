package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.Type.INTERVIEW;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_INTERVIEWS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Type;
import seedu.address.model.Date;
import seedu.address.model.Model;
import seedu.address.model.Time;
import seedu.address.model.interview.Interview;

public class EditInterviewCommand extends EditCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " [i] : Edits the details of the interview identified "
            + "by the index number used in the displayed interview list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "<" + PREFIX_DATE + "DATE> "
            + "<" + PREFIX_TIME + "TIME> \n"
            + "Example: " + COMMAND_WORD + " [i] 1 "
            + PREFIX_DATE + "2021-05-06 "
            + PREFIX_TIME + "10:30";

    public static final String MESSAGE_EDIT_INTERVIEW_SUCCESS = "Edited Interview: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INTERVIEW = "This interview already exists in the address book.";

    private final Index index;
    private final EditInterviewDescriptor editInterviewDescriptor;

    /**
     * @param index of the interview in the filtered interview list to edit
     * @param editInterviewDescriptor details to edit the interview with
     */
    public EditInterviewCommand(Index index, EditInterviewDescriptor editInterviewDescriptor) {
        requireNonNull(index);
        requireNonNull(editInterviewDescriptor);

        this.index = index;
        this.editInterviewDescriptor = new EditInterviewDescriptor(editInterviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Interview> lastShownList = model.getFilteredInterviewList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
        }

        Interview interviewToEdit = lastShownList.get(index.getZeroBased());

        Interview editedInterview = createEditedInterview(interviewToEdit, editInterviewDescriptor);

        if (!interviewToEdit.isSameInterview(editedInterview) && model.hasInterview(editedInterview)) {
            throw new CommandException(MESSAGE_DUPLICATE_INTERVIEW);
        }

        model.setInterview(interviewToEdit, editedInterview);
        model.updateFilteredInterviewList(PREDICATE_SHOW_ALL_INTERVIEWS);
        return new CommandResult(String.format(MESSAGE_EDIT_INTERVIEW_SUCCESS, editedInterview), getType());
    }

    /**
     * Creates and returns a {@code Interview} with the details of {@code interviewToEdit}
     * edited with {@code editInterviewDescriptor}.
     */
    private static Interview createEditedInterview(
            Interview interviewToEdit, EditInterviewDescriptor editInterviewDescriptor) {
        assert interviewToEdit != null;

        Date updatedDate = editInterviewDescriptor.getDate().orElse(interviewToEdit.getDate());
        Time updatedTime = editInterviewDescriptor.getTime().orElse(interviewToEdit.getTime());

        return new Interview(interviewToEdit.getPerson(), updatedDate, updatedTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInterviewCommand)) {
            return false;
        }

        // state check
        EditInterviewCommand e = (EditInterviewCommand) other;
        return index.equals(e.index)
                && editInterviewDescriptor.equals(e.editInterviewDescriptor);
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
    /**
     * Stores the details to edit the interview with. Each non-empty field value will replace the
     * corresponding field value of the interview.
     */
    public static class EditInterviewDescriptor {
        private Date date;
        private Time time;

        public EditInterviewDescriptor() {}

        /**
         * Copy constructor.
         *
         */
        public EditInterviewDescriptor(EditInterviewDescriptor toCopy) {
            setDate(toCopy.date);
            setTime(toCopy.time);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, time);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInterviewDescriptor)) {
                return false;
            }

            // state check
            EditInterviewDescriptor e = (EditInterviewDescriptor) other;

            return getDate().equals(e.getDate())
                    && getTime().equals(e.getTime());
        }
    }

}
