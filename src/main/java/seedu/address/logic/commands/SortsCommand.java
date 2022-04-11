package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.DeadlineList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Favourite;
import seedu.address.model.person.HighImportance;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

/**
 * Sorts list based on criteria given.
 * Criteria matching is case sensitive.
 */
public class SortsCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the list of people by specified "
            + "criteria and displays them as a list with index numbers.\n"
            + "Parameters: CRITERIA\n"
            + "Example: " + COMMAND_WORD + " name\n"
            + "Valid criteria: %1$s";

    private final String criteria;

    public SortsCommand(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (criteria) {

        case Name.MODEL_NAME:
            model.sortFilteredPersonListByName();
            break;

        case Address.MODEL_NAME:
            model.sortFilteredPersonListByAddress();
            break;

        case DeadlineList.MODEL_NAME:
            model.sortFilteredPersonListByDeadlineList();
            break;

        case Email.MODEL_NAME:
            model.sortFilteredPersonListByEmail();
            break;

        case Phone.MODEL_NAME:
            model.sortFilteredPersonListByPhone();
            break;

        case Favourite.MODEL_NAME:
            model.sortFilteredPersonListByFavourite();
            break;

        case HighImportance.MODEL_NAME:
            model.sortFilteredPersonListByHighImportance();
            break;

        default:
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_CRITERIA, criteria));
        }
        return new CommandResult(
                String.format(Messages.MESSAGE_SORTED, criteria));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortsCommand // instanceof handles nulls
                && criteria.equals(((SortsCommand) other).criteria)); // state check
    }
}
