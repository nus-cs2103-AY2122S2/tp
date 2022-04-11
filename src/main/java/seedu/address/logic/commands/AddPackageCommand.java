package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PACKAGE_DESC;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InsurancePackage;

/**
 * Adds an insurance package to the address book.
 */
public class AddPackageCommand extends Command {

    public static final String COMMAND_WORD = "addp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an insurance package to the address book. "
            + "Parameters: "
            + PREFIX_INSURANCE_PACKAGE + "PACKAGE NAME "
            + PREFIX_PACKAGE_DESC + "PACKAGE DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INSURANCE_PACKAGE + "Golden Package "
            + PREFIX_PACKAGE_DESC + "Lifetime insurance!";

    public static final String MESSAGE_SUCCESS = "New package added: %1$s";
    public static final String MESSAGE_DUPLICATE_PACKAGE = "This package already exists in the address book";

    private final InsurancePackage toAdd;

    /**
     * Creates an AddPackageCommand to add the specified insurance package.
     */
    public AddPackageCommand(InsurancePackage p) {
        requireNonNull(p);
        toAdd = p;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasInsurancePackage(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PACKAGE);
        }

        model.addInsurancePackage(toAdd);
        assert model.hasInsurancePackage(toAdd);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    public InsurancePackage getToAdd() {
        return toAdd;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddPackageCommand // instanceof handles nulls
                && toAdd.equals(((AddPackageCommand) other).toAdd));
    }

}
