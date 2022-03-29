package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CANDIDATES;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.candidate.Candidate;
import seedu.address.model.candidate.Remark;

/**
 * Changes the remark of an existing candidate in the address book.
 */
public class RemarkCommand extends Command {

    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the candidate identified "
            + "by the index number used in the last candidate listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Candidate: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Candidate: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the candidate in the filtered candidate list to edit the remark
     * @param remark of the candidate to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Candidate> lastShownList = model.getFilteredCandidateList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CANDIDATE_DISPLAYED_INDEX);
        }

        Candidate candidateToEdit = lastShownList.get(index.getZeroBased());

        Candidate editedCandidate = new Candidate(
                candidateToEdit.getStudentId(),
                candidateToEdit.getName(),
                candidateToEdit.getPhone(),
                candidateToEdit.getEmail(),
                candidateToEdit.getCourse(),
                candidateToEdit.getSeniority(),
                candidateToEdit.getApplicationStatus(),
                candidateToEdit.getInterviewStatus(),
                candidateToEdit.getAvailability(),
                remark);

        model.setCandidate(candidateToEdit, editedCandidate);
        model.updateFilteredCandidateList(PREDICATE_SHOW_ALL_CANDIDATES);

        return new CommandResult(generateSuccessMessage(editedCandidate));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code candidateToEdit}.
     */
    private String generateSuccessMessage(Candidate candidateToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, candidateToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
