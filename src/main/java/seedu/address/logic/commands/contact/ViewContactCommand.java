package seedu.address.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.contact.ContactWithNricPredicate;

/**
 * Lists all persons in the address book to the user.
 */
public class ViewContactCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all cotnact whose names contain any of "
            + "the specified owner NRIC and displays them as a list with index numbers.\n"
            + "Parameters: OWNER NRIC\n"
            + "Example: " + COMMAND_WORD + " S1234567L";

    private final ContactWithNricPredicate predicate;

    public ViewContactCommand(ContactWithNricPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACTS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}

