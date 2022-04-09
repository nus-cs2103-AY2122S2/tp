package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.util.PersonContainsMembershipPredicate;

/**
 * Lists all persons in address book membership matches the provided tier
 */
public class ListMembersCommand extends Command {

    public static final String COMMAND_WORD = "listMembers";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all clients of the specified tier. "
            + "It displays all members if no tier is specified. "
            + "There are 3 membership tiers: Bronze, Silver & Gold.\n"
            + "Parameters: "
            + "[MEMBERSHIP]\n"
            + "Example: " + COMMAND_WORD + " gold";

    private final PersonContainsMembershipPredicate predicate;

    public ListMembersCommand(PersonContainsMembershipPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);

        int numPersons = model.getFilteredPersonList().size();
        if (numPersons == 0) {
            return new CommandResult(Messages.MESSAGE_NO_PERSONS_FOUND_OVERVIEW);
        }
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_FOUND_OVERVIEW, numPersons));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListMembersCommand // instanceof handles nulls
                && predicate.equals(((ListMembersCommand) other).predicate)); // state check
    }
}
