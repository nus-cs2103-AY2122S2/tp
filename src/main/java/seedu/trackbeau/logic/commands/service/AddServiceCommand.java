package seedu.trackbeau.logic.commands.service;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PRICE;

import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.service.Service;

/**
 * Adds a service to trackBeau.
 */
public class AddServiceCommand extends Command {

    public static final String COMMAND_WORD = "adds";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a service to TrackBeau. "
        + "Parameters: "
        + PREFIX_NAME + "NAME "
        + PREFIX_PRICE + "PRICE "
        + PREFIX_DURATION + "DURATION (in minutes) "
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_NAME + "Relaxing Eye Treatment "
        + PREFIX_PRICE + "58 "
        + PREFIX_DURATION + "30";

    public static final String MESSAGE_SUCCESS = "New service added: %1$s";
    public static final String MESSAGE_DUPLICATE_SERVICE = "This service already exists in TrackBeau";

    private final Service toAdd;

    /**
     * Creates an AddServiceCommand to add the specified {@code Service}
     */
    public AddServiceCommand(Service service) {
        requireNonNull(service);
        toAdd = service;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasService(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SERVICE);
        }

        model.addService(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddServiceCommand // instanceof handles nulls
            && toAdd.equals(((AddServiceCommand) other).toAdd));
    }
}
