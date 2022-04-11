package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

/**
 *  Finds and lists all persons in ReadyBakey whose predicate (address) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindPersonAddressCommand extends FindPersonCommand {
    private AddressContainsKeywordsPredicate predicate;
    private final String attributeName = "address";

    public FindPersonAddressCommand(AddressContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindPersonAddressCommand // instanceof handles nulls
                && predicate.equals(((FindPersonAddressCommand) other).predicate)); // state check
    }
}
