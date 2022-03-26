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

    public static final String COMMAND_WORD = "findlesson";
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
                String.format(Messages.MESSAGE_LESSONS_LISTED_OVERVIEW, model.getFilteredLessonList().size()),
                ViewTab.LESSON);
    }

    /**
     * Returns true if the command word entered inside the user-input matches any of the
     * specified keywords identified with this command.
     */
    public boolean matchesCommandWord(String commandWord) {
        String lowerCaseCommandWord = commandWord.toLowerCase();

        return commandWord.equals(COMMAND_WORD) || commandWord.equals(SHORTENED_COMMAND_WORD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindLessonCommand // instanceof handles nulls
                && predicate.equals(((FindLessonCommand) other).predicate)); // state check
    }
}
