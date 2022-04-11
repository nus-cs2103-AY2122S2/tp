package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;

/**
 *  Finds and lists all persons in ReadyBakey whose predicate (phone) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindPersonPhoneCommand extends FindPersonCommand {
    private PhoneContainsKeywordsPredicate predicate;
    private final String attributeName = "phone";

    public FindPersonPhoneCommand(PhoneContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_FIND_PERSONS_OVERVIEW, model.getFilteredPersonList().size(),
                        attributeName, predicate.getKeywordsString()),
                false, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPersonPhoneCommand // instanceof handles nulls
                && predicate.equals(((FindPersonPhoneCommand) other).predicate)); // state check
    }
}
