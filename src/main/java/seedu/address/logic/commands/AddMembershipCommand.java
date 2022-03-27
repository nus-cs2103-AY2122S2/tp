package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
public class AddMembershipCommand extends Command {

    public static final String COMMAND_WORD = "addMembership";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds membership to the specified person. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + Membership.PREFIX + "MembershipName "
            + Membership.DATE_PREFIX + "Date\n"
            + "Example: " + COMMAND_WORD + " "
            + "1 "
            + Membership.PREFIX + "Glee Club "
            + "(optional)" + Membership.DATE_PREFIX + "2022-02-02";

    public static final String MESSAGE_SUCCESS = "Membership added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEMBERSHIP = "This person is already a member";

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
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, membership.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddMembershipCommand // instanceof handles nulls
                && membership.equals(((AddMembershipCommand) other).membership)
                && index.equals(((AddMembershipCommand) other).index));
    }
}
