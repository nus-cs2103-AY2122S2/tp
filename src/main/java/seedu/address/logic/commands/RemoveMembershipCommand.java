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
 * Adds a person to the address book.
 */
public class RemoveMembershipCommand extends Command {

    public static final String COMMAND_WORD = "removeMembership";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes membership from the specified client. "
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 ";

    public static final String MESSAGE_SUCCESS = "Membership removed";
    public static final String MESSAGE_NO_MEMBERSHIP = "This client does not have a membership";

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
            return new CommandResult(MESSAGE_NO_MEMBERSHIP);
        }

        Person editedPerson = personToEdit.removeFields(Membership.PREFIX);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RemoveMembershipCommand // instanceof handles nulls
                && index.equals(((RemoveMembershipCommand) other).index));
    }
}
