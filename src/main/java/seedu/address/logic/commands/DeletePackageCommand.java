package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InsurancePackage;


public class DeletePackageCommand extends Command {

    public static final String COMMAND_WORD = "deletep";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes an existing insurance package in the address book. "
            + "Parameters: "
            + PREFIX_INSURANCE_PACKAGE + "PACKAGE NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INSURANCE_PACKAGE + "Golden Package ";

    public static final String MESSAGE_DELETE_PACKAGE_SUCCESS = "Deleted Package: %1$s";
    public static final String MESSAGE_INVALID_PACKAGE = "This package does not exist in the address book.";

    public final String packageName;

    public DeletePackageCommand(String packageName) {
        this.packageName = packageName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasInsurancePackage(new InsurancePackage(packageName))) {
            throw new CommandException(MESSAGE_INVALID_PACKAGE);
        }

        model.deleteInsurancePackage(new InsurancePackage(packageName));

        return new CommandResult(String.format(MESSAGE_DELETE_PACKAGE_SUCCESS, packageName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePackageCommand // instanceof handles nulls
                && packageName.equals(((DeletePackageCommand) other).packageName)); // state check
    }
}
