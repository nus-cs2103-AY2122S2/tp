package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;

/**
 * Adds a module to LinkyTime.
 */
public class AddModuleCommand extends Command {
    public static final String COMMAND_WORD = "madd";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to LinkyTime. "
            + "Parameters: "
            + PREFIX_NAME + "MODULE_CODE "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "CS2103T";

    public static final String MESSAGE_SUCCESS = "New module added: %1$s";
    public static final String MESSAGE_DUPLICATE_MODULE = "This module already exists in LinkyTime";

    private final Module moduleToAdd;

    /**
     * Creates an AddModuleCommand to add the specified {@code Module}
     */
    public AddModuleCommand(Module entry) {
        requireNonNull(entry);
        moduleToAdd = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasModule(moduleToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModule(moduleToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, moduleToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddModuleCommand // instanceof handles nulls
                && moduleToAdd.equals(((AddModuleCommand) other).moduleToAdd));
    }
}
