package seedu.address.logic.commands.applicant;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORTAPPLICANT;

import java.util.Comparator;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;

public class SortApplicant extends Command {

    public static final String SORTAPPLICANT_BY_DATEAPPLIED = "dateapplied";

    public static final String SORTAPPLICANT_BY_INTERVIEW = "interview";

    public static final String SORTAPPLICANT_BY_JOB = "job";

    public static final String COMMAND_WORD = "sortapplicant";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all applicants by a given attribute.\n"
            + "Parameters: \n"
            + "*" + PREFIX_SORTAPPLICANT + "ATTRIBUTE: "
            + "ATTRIBUTE can be either dateapplied, interview or job \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORTAPPLICANT + "dateapplied";

    public static final String MESSAGE_SORT_APPLICANT_SUCCESS = "Applicants successfully sorted by %1$s.";

    public static final String MESSAGE_CONSTRAINTS = "Applicants should be sorted by their "
            + "'dateapplied', 'interview', or 'job'.";

    public static final String ATTRIBUTE_DATEAPPLIED = "date applied";

    public static final String ATTRIBUTE_INTERVIEW = "interview date";

    public static final String ATTRIBUTE_JOB = "job applied";

    public static final Comparator<Applicant> COMPARE_BY_DATEAPPLIED =
            Comparator.comparing(Applicant::getDateApplied);

    public static final Comparator<Applicant> COMPARE_BY_INTERVIEWDATE =
            Comparator.comparing(Applicant::getInterviewDate);

    public static final Comparator<Applicant> COMPARE_BY_JOBID =
            Comparator.comparing(Applicant::getJobId);

    private final Comparator<Applicant> compareBy;
    private final String attribute;

    /**
     * Instantiates a new SortApplicant to sort the Applicant list by a specified attribute.
     *
     * @param compareBy the compare by
     */
    public SortApplicant(String compareBy) {
        requireNonNull(compareBy);
        checkArgument(isValid(compareBy), MESSAGE_CONSTRAINTS);

        switch (compareBy.toLowerCase()) {
        case SORTAPPLICANT_BY_DATEAPPLIED:
            this.compareBy = COMPARE_BY_DATEAPPLIED;
            this.attribute = ATTRIBUTE_DATEAPPLIED;
            break;
        case SORTAPPLICANT_BY_INTERVIEW:
            this.compareBy = COMPARE_BY_INTERVIEWDATE;
            this.attribute = ATTRIBUTE_INTERVIEW;
            break;
        case SORTAPPLICANT_BY_JOB:
            this.compareBy = COMPARE_BY_JOBID;
            this.attribute = ATTRIBUTE_JOB;
            break;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given attribute is valid.
     */
    public static boolean isValid(String sortBy) {
        return sortBy.toLowerCase().matches(SORTAPPLICANT_BY_DATEAPPLIED
                + "|" + SORTAPPLICANT_BY_INTERVIEW
                + "|" + SORTAPPLICANT_BY_JOB);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (lastShownList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_APPLICANT_LIST);
        }

        model.sortApplicant(compareBy);

        return new CommandResult(String.format(MESSAGE_SORT_APPLICANT_SUCCESS, attribute));
    }

    @Override
    public String toString() {
        return attribute;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortApplicant)
                && (compareBy.equals(((SortApplicant) other).compareBy));
    }
}
