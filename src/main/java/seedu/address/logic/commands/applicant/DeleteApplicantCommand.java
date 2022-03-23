package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.DataType;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.interview.Interview;

/**
 * Deletes a applicant identified using it's displayed index from the address book.
 */
public class DeleteApplicantCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " -a : Deletes the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " -a 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Applicant: %1$s";

    public static final String MESSAGE_DELETE_INTERVIEWS = "Deleted %d related interview(s)";

    private final Index targetIndex;

    public DeleteApplicantCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Applicant applicantToDelete = lastShownList.get(targetIndex.getZeroBased());
        int deleteInterviewCount = deleteApplicantsInterview(model, applicantToDelete);

        model.deletePerson(applicantToDelete);
        return new CommandResult(
                String.format(MESSAGE_DELETE_PERSON_SUCCESS, applicantToDelete) + "\n"
                        + String.format(MESSAGE_DELETE_INTERVIEWS, deleteInterviewCount),
                getCommandDataType());
    }

    /**
     * Deletes interviews which are for the applicant to delete.
     */
    private int deleteApplicantsInterview(Model model, Applicant applicantToDelete) {
        ObservableList<Interview> interviewList = model.getAddressBook().getInterviewList();
        ArrayList<Interview> interviewsToDelete = new ArrayList<>();

        int deleteCount = 0;
        for (Interview i : interviewList) {
            if (i.isInterviewForApplicant(applicantToDelete)) {
                interviewsToDelete.add(i);
                deleteCount++;
            }
        }

        for (Interview i : interviewsToDelete) {
            model.deleteInterview(i);
        }

        return deleteCount;
    }

    @Override
    public DataType getCommandDataType() {
        return DataType.APPLICANT;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteApplicantCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteApplicantCommand) other).targetIndex)); // state check
    }
}
