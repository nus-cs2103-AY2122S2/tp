package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.Type.INTERVIEW;

import seedu.address.commons.core.Messages;
import seedu.address.logic.parser.Type;
import seedu.address.model.Model;
import seedu.address.model.interview.InterviewContainsKeywordsPredicate;

public class FindInterviewCommand extends FindCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + " [i] : Finds all interviews that meet search criteria.\n"
            + "Only accepts: g/, d/, t/, n/ and j/ flags\n"
            + "Example: " + COMMAND_WORD + " [i] g/n/john j/software engineer g/d/2021-05-06";

    private InterviewContainsKeywordsPredicate predicate;

    /**
     * Constructor for FindInterviewCommand
     *
     * @param predicate Search criteria to find interviews
     */

    public FindInterviewCommand(InterviewContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW,
                model.getFilteredInterviewList().size()), getType());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindInterviewCommand)) {
            return false;
        }

        // state check
        FindInterviewCommand e = (FindInterviewCommand) other;
        return predicate.equals(e.predicate);
    }

    @Override
    public Type getType() {
        return INTERVIEW;
    }
}
