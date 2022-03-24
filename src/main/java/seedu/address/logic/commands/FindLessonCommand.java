package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;

/**
 * Finds and lists all lessons in the lesson book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindLessonCommand extends Command {

    public static final String COMMAND_WORD = "findlessons";
    public static final String SHORTENED_COMMAND_WORD = "fl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all lessons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " makeup lesson";

    private final LessonNameContainsKeywordsPredicate predicate;

    public FindLessonCommand(LessonNameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredLessonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredLessonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLessonCommand // instanceof handles nulls
                && predicate.equals(((FindLessonCommand) other).predicate)); // state check
    }
}
