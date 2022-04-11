package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_OPERATOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILTER_VALUE;

import java.time.Month;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.Name;

/**
 * Filter clients in the address book.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filterClients";

    public static final String FILTER_ATTRIBUTES = "age, premium, company, birthMonth";
    public static final String FILTER_OPERATORS = "equal, greater, lesser, lesserorequal, greaterorequal";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters clients in the address book.\n"
            + "Parameters: "
            + "ATTRIBUTE " + PREFIX_FILTER_OPERATOR + "FILTER_OPERATOR "
            + PREFIX_FILTER_VALUE + "FILTER_VALUE\n"
            + "Example: " + COMMAND_WORD + "age "
            + PREFIX_FILTER_OPERATOR + "equal "
            + PREFIX_FILTER_VALUE + "50\n"
            + "Supported attributes: " + FILTER_ATTRIBUTES + "\n"
            + "Supported inequalities: " + FILTER_OPERATORS;

    public static final String MESSAGE_SUCCESS = "Successfully filtered: %s %s %s";
    public static final String MESSAGE_INVALID_FILTER_ATTRIBUTE = "The filter attribute %s is invalid. "
            + "Supported attributes: " + FILTER_ATTRIBUTES;
    public static final String MESSAGE_INVALID_FILTER_OPERATOR = "The filter operator %s is invalid. "
            + "Supported operators: " + FILTER_OPERATORS;
    public static final String MESSAGE_ONLY_EQUAL_FILTER_OPERATOR = "Only equal is a valid filter operator for this "
            + "attribute";
    public static final String MESSAGE_INVALID_FILTER_VALUE = "The filter value %s is invalid, expected a %s "
            + "for the attribute %s.";

    private final String attribute;
    private final String operator;
    private final String value;

    /**
     * Creates a FilterCommand to filter the {@code Client}s by a specified {@code attribute}.
     * @param attribute {@code Client} attribute to filter by
     * @param operator whether the filter should be equal, greater or less than
     * @param value the value to be filtered on
     */
    public FilterCommand(String attribute, String operator, String value) {
        requireNonNull(attribute);
        requireNonNull(operator);
        requireNonNull(value);
        this.attribute = attribute;
        this.operator = operator;
        this.value = value;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNoClients()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "filter"));
        }

        Predicate<Client> predicateLess = null;
        Predicate<Client> predicateEqual;
        Predicate<Client> predicateGreater = null;

        int compareValue;
        switch (attribute) {
        case "age":
            try {
                compareValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new CommandException(String.format(MESSAGE_INVALID_FILTER_VALUE, value, "integer", attribute));
            }
            predicateLess = (client) -> client.getAge() < compareValue;
            predicateEqual = (client) -> client.getAge() == compareValue;
            predicateGreater = (client) -> client.getAge() > compareValue;
            break;
        case "premium":
            try {
                compareValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new CommandException(String.format(MESSAGE_INVALID_FILTER_VALUE, value, "integer", attribute));
            }
            predicateLess = (client) -> client.getPolicies().totalPremiumSum() < compareValue;
            predicateEqual = (client) -> client.getPolicies().totalPremiumSum() == compareValue;
            predicateGreater = (client) -> client.getPolicies().totalPremiumSum() > compareValue;
            break;
        case "company":
            Name companyName;
            try {
                companyName = new Name(value);
            } catch (IllegalArgumentException e) {
                throw new CommandException(e.getMessage());
            }
            predicateEqual = (client) -> client.getPolicies().hasPolicyFromCompany(companyName);
            break;
        case "birthMonth":
            int month;
            try {
                month = Month.valueOf(value.toUpperCase()).getValue();
            } catch (IllegalArgumentException e) {
                throw new CommandException(String.format(MESSAGE_INVALID_FILTER_VALUE, value, "month (e.g. february)",
                        attribute));
            }
            predicateLess = (client) -> client.getBirthdayMonth() < month;
            predicateEqual = (client) -> client.getBirthdayMonth() == month;
            predicateGreater = (client) -> client.getBirthdayMonth() > month;
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER_ATTRIBUTE, attribute));
        }

        switch (operator) {
        case "greater":
            if (predicateGreater == null) {
                throw new CommandException(MESSAGE_ONLY_EQUAL_FILTER_OPERATOR);
            }
            model.updateFilteredClientList(predicateGreater);
            break;
        case "equal":
            model.updateFilteredClientList(predicateEqual);
            break;
        case "lesser":
            if (predicateLess == null) {
                throw new CommandException(MESSAGE_ONLY_EQUAL_FILTER_OPERATOR);
            }
            model.updateFilteredClientList(predicateLess);
            break;
        case "lesserorequal":
            if (predicateLess == null) {
                throw new CommandException(MESSAGE_ONLY_EQUAL_FILTER_OPERATOR);
            }
            model.updateFilteredClientList(predicateLess.or(predicateEqual));
            break;
        case "greaterorequal":
            if (predicateGreater == null) {
                throw new CommandException(MESSAGE_ONLY_EQUAL_FILTER_OPERATOR);
            }
            model.updateFilteredClientList(predicateGreater.or(predicateEqual));
            break;
        default:
            throw new CommandException(String.format(MESSAGE_INVALID_FILTER_OPERATOR, operator));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, attribute, operator, value));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherFc = (FilterCommand) other;
        return attribute.equals(otherFc.attribute)
                && operator.equals(otherFc.operator)
                && value.equals(otherFc.value);
    }
}
