package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddMembershipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Membership;

import java.time.LocalDate;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddMembershipParser implements Parser<AddMembershipCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMembershipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, Membership.PREFIX, Membership.DATE_PREFIX);

        Index index;
        try {
            index = IndexParser.parse(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddMembershipCommand.MESSAGE_USAGE), ive);
        }
        String membershipName = argMultimap.getValue(Membership.PREFIX).orElse("");
        if (!Membership.isValidName(membershipName)) {
            throw new ParseException(Membership.MESSAGE_CONSTRAINTS);
        }

        Membership membership;
        String date = argMultimap.getValue(Membership.DATE_PREFIX).orElse("");
        if (!date.equals("")) {
            if (!Membership.isValidDate(date)) {
                throw new ParseException(Membership.MESSAGE_DATE_CONSTRAINTS);
            }
            membership = new Membership(membershipName, LocalDate.parse(date));
        } else {
            membership = new Membership(membershipName);
        }

        return new AddMembershipCommand(index, membership);
    }
}
