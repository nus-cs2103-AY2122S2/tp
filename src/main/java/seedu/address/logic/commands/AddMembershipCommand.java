package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Person;

/**
 * Adds a membership to a person in the address book.
 */
public class AddMembershipCommand extends Command {

    public static final String COMMAND_WORD = "addMembership";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add a membership to the client in the list as specified by the index. "
            + "There are 3 membership tiers: Bronze, Silver & Gold.\n"
            + "Parameters: "
            + "INDEX (between 1 and 2147483647 inclusive) "
            + Membership.PREFIX + "MEMBERSHIP "
            + "[" + Membership.DATE_PREFIX + "DATE]\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + Membership.PREFIX + "gold "
            + Membership.DATE_PREFIX + "2022-02-03";

    public static final String MESSAGE_SUCCESS = "Added membership to %s (%s).";

    private final Index index;
    private final Membership membership;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddMembershipCommand(Index index, Membership membership) {
        requireNonNull(membership);
        this.index = index;
        this.membership = membership;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = personToEdit.addMembership(membership);
        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit, membership));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMembershipCommand // instanceof handles nulls
                && membership.equals(((AddMembershipCommand) other).membership)
                && index.equals(((AddMembershipCommand) other).index));
    }
}
