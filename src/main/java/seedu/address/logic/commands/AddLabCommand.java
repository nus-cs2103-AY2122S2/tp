package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.lab.Lab;

/**
 * Adds a person to the address book.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "labadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to all students in the TAddressBook. "
            + "Parameters: "
            + PREFIX_LAB + "LAB NUMBER "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB + "1";

    public static final String MESSAGE_SUCCESS = "New Lab added: %1$s";
    public static final String MESSAGE_DUPLICATE_LAB = "This Lab already exists in the TAddressBook";

    private final Lab toAdd;

    /**
     * Creates an AddLabCommand to add the specified {@code Lab}
     */
    public AddLabCommand(Lab lab) {
        requireNonNull(lab);
        toAdd = lab;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasLab(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LAB);
        }

        model.addLab(toAdd);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand // instanceof handles nulls
                && toAdd.equals(((AddLabCommand) other).toAdd));
    }
}
