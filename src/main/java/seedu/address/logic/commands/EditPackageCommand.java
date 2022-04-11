package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PACKAGE_DESC;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.InsurancePackage;

/**
 * Edits an existing insurance package in the address book.
 */
public class EditPackageCommand extends Command {

    public static final String COMMAND_WORD = "editp";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits an existing insurance package in the address book."
            + "Parameters: "
            + PREFIX_INSURANCE_PACKAGE + "PACKAGE NAME "
            + PREFIX_PACKAGE_DESC + "PACKAGE DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INSURANCE_PACKAGE + "Golden Package "
            + PREFIX_PACKAGE_DESC + "Lifetime insurance!";

    public static final String MESSAGE_EDIT_PACKAGE_SUCCESS = "Edited Package: %1$s";
    public static final String MESSAGE_INVALID_PACKAGE = "This package does not exist in the address book.";

    private final String packageName;
    private final String newPackageDesc;

    /**
     * Creates an EditPackageCommand to edit the specified insurance package.
     */
    public EditPackageCommand(String packageName, String newPackageDesc) {
        this.packageName = packageName;
        this.newPackageDesc = newPackageDesc;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasInsurancePackage(new InsurancePackage(packageName))) {
            throw new CommandException(MESSAGE_INVALID_PACKAGE);
        }

        model.setInsurancePackage(packageName, newPackageDesc);
        assert model.hasInsurancePackage(new InsurancePackage(packageName, newPackageDesc));

        return new CommandResult(String.format(MESSAGE_EDIT_PACKAGE_SUCCESS, packageName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EditPackageCommand // instanceof handles nulls
                && packageName.equals(((EditPackageCommand) other).packageName)
                && newPackageDesc.equals(((EditPackageCommand) other).newPackageDesc));
    }
}
