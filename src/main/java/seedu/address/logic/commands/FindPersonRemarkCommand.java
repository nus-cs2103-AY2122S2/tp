package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.RemarkContainsKeywordsPredicate;

/**
 *  Finds and lists all persons in ReadyBakey whose predicate (remark) contains any of the argument keywords.
 *  Keyword matching is case insensitive.
 */
public class FindPersonRemarkCommand extends FindPersonCommand {

    private RemarkContainsKeywordsPredicate predicate;
    private final String attributeName = "remark";

    public FindPersonRemarkCommand(RemarkContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindPersonRemarkCommand // instanceof handles nulls
                && predicate.equals(((FindPersonRemarkCommand) other).predicate)); // state check
    }
}
