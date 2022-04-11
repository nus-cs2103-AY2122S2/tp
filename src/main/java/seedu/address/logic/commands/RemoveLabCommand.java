package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lab.Lab;

/**
 * Deletes a Lab identified using its LAB_NUMBER from the TAddressBook.
 */
public class RemoveLabCommand extends Command {

    public static final String COMMAND_WORD = "labrm";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the Lab identified by the LAB_NUMBER from the TAddressBook.\n"
            + "Parameters: "
            + PREFIX_LAB + "LAB_NUMBER (must be an Integer between 0 and 20 inclusive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB + "1";

    public static final String MESSAGE_SUCCESS = "Lab %1$s removed";
    public static final String MESSAGE_LAB_NOT_FOUND = "Lab %1$s does not exists in the TAddressBook";

    private final Lab toRemove;

    /**
     * Creates an RemoveLabCommand to add the specified {@code Lab}
     */
    public RemoveLabCommand(Lab lab) {
        requireNonNull(lab);
        toRemove = lab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasLab(toRemove)) {
            throw new CommandException(String.format(MESSAGE_LAB_NOT_FOUND, toRemove.labNumber));
        }

        model.removeLab(toRemove);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toRemove.labNumber));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        return (other instanceof RemoveLabCommand) && (toRemove.isSameLab(((RemoveLabCommand) other).toRemove));
    }
}
