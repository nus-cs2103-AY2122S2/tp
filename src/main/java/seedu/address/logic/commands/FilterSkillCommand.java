package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonContainsSkillPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterSkillCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons whose skills contain the "
            + "provided skill name (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: SKILL\n"
            + "Example: " + COMMAND_WORD + " Java";

    private final PersonContainsSkillPredicate predicate;

    public FilterSkillCommand(PersonContainsSkillPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateDisplayPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getDisplayPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterSkillCommand // instanceof handles nulls
                && predicate.equals(((FilterSkillCommand) other).predicate)); // state check
    }
}
