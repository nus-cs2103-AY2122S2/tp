package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.ContactList;
import seedu.address.model.Model;
import seedu.address.model.person.UniqueModuleList;
import seedu.address.model.tasks.PriorityTaskList;

/**
 * Clears the class group.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Contact list has been cleared!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the contact list.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setContactList(new ContactList());
        model.setTaskList(new PriorityTaskList());
        model.setModuleList(new UniqueModuleList());
        model.commitContent();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
