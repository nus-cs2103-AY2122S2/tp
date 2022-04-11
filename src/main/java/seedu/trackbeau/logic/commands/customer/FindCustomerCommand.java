package seedu.trackbeau.logic.commands.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_BIRTHDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_REGDATE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.customer.CustomerSearchContainsKeywordsPredicate;
import seedu.trackbeau.ui.Panel;

/**
 * Finds and lists all customers in trackBeau whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCustomerCommand extends Command {

    public static final String COMMAND_WORD = "findc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all customers whose details match "
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME]\n"
            + "[" + PREFIX_PHONE + "PHONE]\n"
            + "[" + PREFIX_EMAIL + "EMAIL]\n"
            + "[" + PREFIX_ADDRESS + "ADDRESS]\n"
            + "[" + PREFIX_REGDATE + "REGISTRATION DATE]\n"
            + "[" + PREFIX_BIRTHDATE + "BIRTHDATE]\n"
            + "[" + PREFIX_SKINTYPE + "SKIN TYPE]\n"
            + "[" + PREFIX_HAIRTYPE + "HAIR TYPE]\n"
            + "[" + PREFIX_STAFFS + "STAFFS]...\n"
            + "[" + PREFIX_SERVICES + "SERVICES]...\n"
            + "[" + PREFIX_ALLERGIES + "ALLERGIES]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John "
            + PREFIX_ALLERGIES + "Nickel";

    private final CustomerSearchContainsKeywordsPredicate predicate;

    public FindCustomerCommand(CustomerSearchContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredCustomerList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW, model.getFilteredCustomerList().size()),
                Panel.CUSTOMER_PANEL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCustomerCommand // instanceof handles nulls
                && predicate.equals(((FindCustomerCommand) other).predicate)); // state check
    }
}
