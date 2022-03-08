package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.ClassCodeContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose class code matches the given class code.
 * Keyword matching is case insensitive.
 */
public class FindClassCodeCommand extends Command {

    public static final String COMMAND_WORD = "findclasscode";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose class code contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 3B";

    private final ClassCodeContainsKeywordsPredicate predicate;

    public FindClassCodeCommand(ClassCodeContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindClassCodeCommand // instanceof handles nulls
                && predicate.equals(((FindClassCodeCommand) other).predicate)); // state check
    }

}
