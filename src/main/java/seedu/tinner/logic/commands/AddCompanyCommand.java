package seedu.tinner.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.tinner.logic.commands.exceptions.CommandException;
import seedu.tinner.model.Model;
import seedu.tinner.model.company.Company;

/**
 * Adds a Company to the company list.
 */
public class AddCompanyCommand extends Command {

    public static final String COMMAND_WORD = "addCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + "\n"
            + "Function: Adds a company to the company list."
            + "\n"
            + "Format: " + COMMAND_WORD + " "
            + PREFIX_NAME + "COMPANY_NAME "
            + "[" + PREFIX_PHONE + "PHONE_NUMBER] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Meta "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "hr@meta.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 ";

    public static final String MESSAGE_SUCCESS = "New company added: %1$s";
    public static final String MESSAGE_DUPLICATE_COMPANY = "This company already exists in the company list";

    private final Company toAdd;

    /**
     * Creates an AddCompanyCommand to add the specified {@code Company}
     */
    public AddCompanyCommand(Company company) {
        requireNonNull(company);
        toAdd = company;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCompany(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_COMPANY);
        }

        model.addCompany(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCompanyCommand // instanceof handles nulls
                && toAdd.equals(((AddCompanyCommand) other).toAdd));
    }
}
