package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.meeting.MeetingContainsAllKeywordsPredicate;

/**
 * Finds and lists all meetings in LinkyTime whose name, module code and tags together contain all
 * the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindMeetingCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all meetings whose names, modules "
            + "and tags together contain all of the specified keywords (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " CS2101 cs2103t tutorial";

    private final MeetingContainsAllKeywordsPredicate predicate;

    public FindMeetingCommand(MeetingContainsAllKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredMeetingList(predicate);
        return new CommandResult(String.format(
                Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW, model.getFilteredMeetingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindMeetingCommand // instanceof handles nulls
                && predicate.equals(((FindMeetingCommand) other).predicate)); // state check
    }
}
