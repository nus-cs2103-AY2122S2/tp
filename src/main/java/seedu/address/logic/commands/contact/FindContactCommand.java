package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactContainsKeywordsPredicate;

/**
 * Finds and lists all contact records in MedBook belonging to the current patient viewed whose information contains
 * any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindContactCommand extends Command {

    public static final String COMMAND_WORD = "find";
    public static final CommandType COMMAND_TYPE = CommandType.CONTACT;

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all contact records belonging to the currently viewed patient whose information contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 88888888";

    private final ContactContainsKeywordsPredicate predicate;

    public FindContactCommand(ContactContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                        model.getFilteredContactList().size(), ViewedNric.getViewedNric().toString()), COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindContactCommand // instanceof handles nulls
                && predicate.equals(((FindContactCommand) other).predicate)); // state check
    }
}
