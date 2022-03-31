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
 * Removes a membership from a person in the address book.
 */
public class RemoveMembershipCommand extends Command {

    public static final String COMMAND_WORD = "removeMembership";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Remove membership from the client in the list as specified by the index.\n"
            + "Parameters: "
            + "INDEX (between 1 and 2147483647 inclusive)\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String MESSAGE_SUCCESS = "Removed membership from %s.";
    public static final String MESSAGE_NO_MEMBERSHIP = "%1$s does not have a membership";

    private final Index index;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public RemoveMembershipCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (!personToEdit.hasField(Membership.PREFIX)) {
            return new CommandResult(String.format(MESSAGE_NO_MEMBERSHIP, personToEdit));
        }

        Person editedPerson = personToEdit.removeFields(Membership.PREFIX);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveMembershipCommand // instanceof handles nulls
                && index.equals(((RemoveMembershipCommand) other).index));
    }
}
