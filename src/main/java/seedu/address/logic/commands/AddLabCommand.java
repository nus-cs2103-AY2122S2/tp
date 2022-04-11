package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lab.Lab;

/**
 * Adds a Lab to the TAddressBook.
 */
public class AddLabCommand extends Command {

    public static final String COMMAND_WORD = "labadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a lab to all students in the TAddressBook. \n"
            + "Parameters: "
            + PREFIX_LAB + "LAB_NUMBER (has to be between 0 and 20 inclusive)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_LAB + "1";

    public static final String MESSAGE_SUCCESS = "New Lab added: %1$s";
    public static final String MESSAGE_DUPLICATE_LAB = "This Lab already exists in the TAddressBook";
    public static final String MESSAGE_EMPTY_STUDENT_LIST = "Note that the student list is currently empty. "
            + "But Lab %1$s has been added to the Master Lab List.\n"
            + "Subsequent additions of Students will include Lab %1$s.";

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
        String resultString = MESSAGE_SUCCESS;

        if (model.hasLab(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_LAB);
        }

        if (model.isStudentListEmpty()) {
            resultString = MESSAGE_EMPTY_STUDENT_LIST;
        }

        model.addLab(toAdd);
        return new CommandResult(String.format(resultString, toAdd.labNumber));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddLabCommand // instanceof handles nulls
                && toAdd.equals(((AddLabCommand) other).toAdd));
    }
}
