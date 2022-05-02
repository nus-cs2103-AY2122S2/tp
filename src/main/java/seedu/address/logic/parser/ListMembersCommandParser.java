package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ListMembersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Membership;
import seedu.address.model.person.util.PersonContainsMembershipPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class ListMembersCommandParser implements Parser<ListMembersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListMembersCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.Tier.ALL));
        }
        trimmedArgs = trimmedArgs.toLowerCase();
        if (!trimmedArgs.equals("gold") && !trimmedArgs.equals("silver") && !trimmedArgs.equals("bronze")) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListMembersCommand.MESSAGE_USAGE));
        }

        return new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.getTierFromString(trimmedArgs)));
    }

}
