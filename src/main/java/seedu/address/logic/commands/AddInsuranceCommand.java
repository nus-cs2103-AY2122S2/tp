package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.insurance.Insurance;

/**
 * Adds an insurance to Mr. Agent.
 */
public class AddInsuranceCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " -i : Adds a insurance to the insurance book. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_PRICE + "PRICE \n"
            + "Example: " + COMMAND_WORD + " -i "
            + PREFIX_TITLE + "Health Care "
            + PREFIX_PRICE + "200 ";


    public static final String MESSAGE_SUCCESS = "New insurance added: %1$s";
    public static final String MESSAGE_DUPLICATE_INSURANCE = "This insurance already exists in the insurance book";

    private final Insurance toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Insurance}
     */
    public AddInsuranceCommand(Insurance insurance) {
        requireNonNull(insurance);
        toAdd = insurance;
    }

    /**
     * Returns toAdd.
     */
    public Insurance getToAdd() {
        return toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInsurance(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INSURANCE);
        }

        model.addInsurance(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddInsuranceCommand // instanceof handles nulls
                && toAdd.equals(((AddInsuranceCommand) other).toAdd));
    }
}
