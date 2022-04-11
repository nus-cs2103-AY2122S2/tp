package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SORT_DIRECTION;

import java.util.Comparator;
import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.client.Client;
import seedu.address.model.client.SortCriteria;

/**
 * Sorts clients in the address book.
 *
 * birthday
 * premium
 * lastContacted
 * premium due date
 * num policies
 *
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sortClients";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts clients in the address book.\n"
            + "Parameters: "
            + "[" + PREFIX_SORT_DIRECTION + "SORT_DIRECTION]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SORT_DIRECTION + "desc";

    public static final String MESSAGE_SUCCESS = "Successfully sorted";
    public static final String MESSAGE_INVALID_ATTRIBUTE = "This attribute is invalid.\nThe available attributes are"
            + " `empty` (default), `numPolicies`, `premium` and `lastContacted`.";
    public static final String MESSAGE_INVALID_SORT_DIRECTION = "Use 'asc' for ascending or 'desc' for descending";


    private final boolean isAscending;
    private final String attribute;

    /**
     * Creates a SortCommand to sort the {@code Client}s by a specified {@code attribute} and direction.
     *
     * @param attribute {@code Client} attribute to sort by
     * @param isAscending Indicator on whether the sort should be in ascending or descending order
     */
    public SortCommand(String attribute, boolean isAscending) {
        requireNonNull(attribute);
        requireNonNull(isAscending);
        this.attribute = attribute;
        this.isAscending = isAscending;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasNoClients()) {
            throw new CommandException(String.format(Messages.MESSAGE_EMPTY_CLIENT_LIST, "sort"));
        }

        Comparator<Client> comparator;
        SortCriteria criteria;
        if (!Objects.equals(attribute, "") && !SortCriteria.isValidEnum(attribute)) {
            throw new CommandException(MESSAGE_INVALID_ATTRIBUTE);
        }
        switch(attribute) {
        case "numPolicies":
            comparator = Comparator.comparingInt(a -> a.getPolicies().asUnmodifiableObservableList().size());
            criteria = SortCriteria.numPolicies;
            model.setIsSorted(true);
            break;
        case "premium":
            comparator = Comparator.comparingInt(a -> a.getPolicies().totalPremiumSum());
            criteria = SortCriteria.premium;
            model.setIsSorted(true);
            break;
        case "lastContacted":
            comparator = Comparator.comparing(a -> a.getLastContacted().getDateTime());
            criteria = SortCriteria.lastContacted;
            model.setIsSorted(true);
            break;
        default:
            comparator = Comparator.comparing(a -> a.getName().fullName);
            criteria = SortCriteria.DEFAULT;
            model.setIsSorted(false);
        }

        if (!isAscending) {
            comparator = comparator.reversed();
        }

        model.updateSortedClientList(comparator);
        return new CommandResult(MESSAGE_SUCCESS, criteria);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute)
                && isAscending == ((SortCommand) other).isAscending);
    }
}
