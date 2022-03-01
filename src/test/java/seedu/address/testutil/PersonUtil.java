package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;


import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.expense.Expense;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Expense person) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(new Expense());
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Expense expense) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_DESCRIPTION + expense.getName().fullName + " ");
        sb.append(PREFIX_CATEGORY + expense.getPhone().value + " ");
        sb.append(PREFIX_AMOUNT + expense.getEmail().value + " ");

        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditExpenseDescriptorDetails(EditExpenseDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(name -> sb.append(PREFIX_DESCRIPTION).append(name.fullName).append(" "));
        descriptor.getCategory().ifPresent(phone -> sb.append(PREFIX_CATEGORY).append(phone.value).append(" "));
        descriptor.getAmount().ifPresent(email -> sb.append(PREFIX_AMOUNT).append(email.value).append(" "));

        return sb.toString();
    }
}
