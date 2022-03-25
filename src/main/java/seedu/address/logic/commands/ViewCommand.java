package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.interview.predicate.WithinTimePeriodPredicate;

/**
 * Lists all candidates in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View all scheduled interviews "
            + " within a specific time period.\n"
            + "Parameters: TIME_PERIOD\n"
            + "Example: " + COMMAND_WORD + " today\n"
            + "Allowable time periods include today (i.e. next 24 hours), week (i.e. this week), "
            + "month (i.e. this month)";

    public static final String MESSAGE_NO_INTERVIEWS_IN_SYSTEM = "No interviews scheduled yet!";

    public static final String MESSAGE_SUCCESS = "Listed below are your upcoming interviews: \n";

    private final WithinTimePeriodPredicate predicate;

    public ViewCommand(WithinTimePeriodPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInterviewSchedule(predicate);
        return new CommandResult(String
                .format(Messages.MESSAGE_INTERVIEWS_LISTED_OVERVIEW, model.getFilteredInterviewSchedule().size()));
    }
}
