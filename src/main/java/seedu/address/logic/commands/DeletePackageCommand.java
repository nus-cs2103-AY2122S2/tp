package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InsurancePackage;
import seedu.address.model.person.Person;

/**
 * Deletes an insurance package from the address book.
 *
 * Note that the current implementation does not allow a package to be deleted, if it is in use by someone.
 */
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
    public static final String MESSAGE_PACKAGE_IN_USE = "This package is in use by someone, "
            + "perhaps set his/her package as another one before deleting this package.";

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

        InsurancePackage packageToDelete = new InsurancePackage(packageName);

        boolean canDelete = true;

        for (Person p : model.getAddressBook().getPersonList()) {
            if (p.getInsurancePackage().equals(packageToDelete)) {
                canDelete = false;
                break;
            }
        }

        if (canDelete) {
            model.deleteInsurancePackage(packageToDelete);
            assert !model.hasInsurancePackage(packageToDelete);
            return new CommandResult(String.format(MESSAGE_DELETE_PACKAGE_SUCCESS, packageName));
        } else {
            return new CommandResult(MESSAGE_PACKAGE_IN_USE);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePackageCommand // instanceof handles nulls
                && packageName.equals(((DeletePackageCommand) other).packageName)); // state check
    }
}
