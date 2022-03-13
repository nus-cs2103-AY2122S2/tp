package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.contact.AddContactCommand.MESSAGE_MISSING_PATIENT;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactWithNricPredicate;
import seedu.address.model.person.Nric;
import seedu.address.model.person.NricPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewContactCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final CommandType COMMAND_TYPE = CommandType.CONTACT;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all cotnact whose names contain any of "
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: OWNER NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567L";

    private final Nric ownerNric;

    public ViewContactCommand(Nric ownerNric) {
        requireNonNull(ownerNric);
        this.ownerNric = ownerNric;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredContactList(new ContactWithNricPredicate(ownerNric));

        if (!model.hasPerson(new NricPredicate(ownerNric))) {
            throw new CommandException(MESSAGE_MISSING_PATIENT);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW,
                        model.getFilteredContactList().size(), ownerNric),
                COMMAND_TYPE);
    }
}

