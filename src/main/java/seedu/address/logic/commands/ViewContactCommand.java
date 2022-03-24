package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

/**
 * Views student contacts in the specific group in ArchDuke.
 */
public class ViewContactCommand extends Command {

    public static final String COMMAND_WORD = "viewcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views student contacts in a specified group in ArchDuke. "
            + "Parameters: "
            + PREFIX_GROUP_NAME + "GROUP_NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP_NAME + "CS2103-W16-3";

    public static final String MESSAGE_NON_EXISTENT_GROUP = "This group does not exist in the list";

    public static final String MESSAGE_SUCCESS = "Here are the student contacts in this group: %s";

    public static final String MESSAGE_ARGUMENTS = "View tasks in group: %s";

    private final Group group;

    /**
     * Constructs a {@code ViewContactCommand} with the specified field.
     *
     * @param group The group in which the tasks would be viewed.
     */
    public ViewContactCommand(Group group) {
        requireNonNull(group);

        this.group = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasGroup(group)) { //check whether the specified group exists
            return new CommandResult(model.viewContact(group));
        } else {
            throw new CommandException(MESSAGE_NON_EXISTENT_GROUP);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewContactCommand)) {
            return false;
        }

        // state check
        ViewContactCommand e = (ViewContactCommand) other;
        return group.equals(e.group);
    }
}
