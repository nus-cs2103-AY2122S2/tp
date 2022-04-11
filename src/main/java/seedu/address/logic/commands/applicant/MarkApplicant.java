package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.ApplicantStatus;

public class MarkApplicant extends Command {
    public static final String COMMAND_WORD = "markapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks an existing Applicant's status. "
            + " The applicant is identified by the index number used in the displayed applicant list. \n"
            + "Parameters: \n"
            + "*[INDEX] (must be a positive integer) \n"
            + "*" + PREFIX_STATUS + "STATUS: "
            + "STATUS can be either rejected, pending, interviewed or accepted \n"
            + "Example: " + COMMAND_WORD + " "
            + "2 "
            + PREFIX_STATUS + "accepted";

    public static final String MESSAGE_SUCCESS = "Updated Applicant %1$s's status to: %2$s";

    private final Index index;
    private final ApplicantStatus applicantStatus;

    /**
     * Instantiates a new MarkApplicant to update the ApplicantStatus to a specified {@code ApplicantStatus}
     *
     * @param applicantStatus the updated applicant status
     */
    public MarkApplicant(Index index, ApplicantStatus applicantStatus) {
        requireAllNonNull(index, applicantStatus);

        this.index = index;
        this.applicantStatus = applicantStatus;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        int zeroBasedIndex = index.getZeroBased();
        if (zeroBasedIndex >= lastShownList.size() || zeroBasedIndex < 0) {
            throw new CommandException(String.format(MESSAGE_INVALID_INDEX, MESSAGE_USAGE));
        }

        Applicant applicantToMark = lastShownList.get(index.getZeroBased());
        Applicant markedApplicant = new Applicant(applicantToMark, applicantStatus);

        model.setApplicant(applicantToMark, markedApplicant);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, markedApplicant, applicantStatus), true,
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof MarkApplicant)
                && (index.equals(((MarkApplicant) other).index)
                    && applicantStatus.equals(((MarkApplicant) other).applicantStatus));
    }
}
