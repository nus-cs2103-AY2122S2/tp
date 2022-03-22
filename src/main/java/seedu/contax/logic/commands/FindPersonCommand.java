package seedu.contax.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.contax.commons.core.GuiListContentType;
import seedu.contax.commons.core.Messages;
import seedu.contax.model.Model;
import seedu.contax.model.person.ContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindPersonCommand extends Command {

    public static final String COMMAND_WORD = "findperson";

    public static final String MESSAGE_USAGE = "`" + COMMAND_WORD + "`: **Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.**\n"
            + "Parameters: *KEYWORD [MORE_KEYWORDS]...*\n"
            + "Example: `" + COMMAND_WORD + " Alice Bob Charlie`";

    private final ContainsKeywordsPredicate predicate;

    public FindPersonCommand(ContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()),
                GuiListContentType.PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonCommand // instanceof handles nulls
                && predicate.equals(((FindPersonCommand) other).predicate)); // state check
    }
}
