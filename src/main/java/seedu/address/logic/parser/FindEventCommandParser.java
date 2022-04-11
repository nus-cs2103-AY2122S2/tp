package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDateIsAfterPredicate;
import seedu.address.model.event.EventDateIsBeforePredicate;
import seedu.address.model.event.EventFriendNamesContainSubstringPredicate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventNameContainsSubstringPredicate;
import seedu.address.model.person.FriendName;

/**
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    public static final String MESSAGE_INVALID_DATE_RANGE = "The start date cannot be after the end date";

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE_START, PREFIX_DATE_END, PREFIX_FRIEND_NAME);

        boolean isAnyArgumentGiven = argMultimap.arePrefixesPresent(PREFIX_NAME)
                || argMultimap.arePrefixesPresent(PREFIX_DATE_START)
                || argMultimap.arePrefixesPresent(PREFIX_DATE_END)
                || argMultimap.arePrefixesPresent(PREFIX_FRIEND_NAME);

        if (!isAnyArgumentGiven || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        ArrayList<Predicate<Event>> eventPredicates = new ArrayList<>();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            EventName eventNameSubstring = ParserUtil.parseEventName(argMultimap.getValue(PREFIX_NAME).get());
            eventPredicates.add(new EventNameContainsSubstringPredicate(eventNameSubstring));
        }

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (argMultimap.getValue(PREFIX_DATE_START).isPresent()) {
            startDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_START).get());
            LocalDate nonInclusiveStartDate = startDate.minusDays(1);
            eventPredicates.add(new EventDateIsAfterPredicate(nonInclusiveStartDate));
        }

        if (argMultimap.getValue(PREFIX_DATE_END).isPresent()) {
            endDate = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE_END).get());
            LocalDate nonInclusiveEndDate = endDate.plusDays(1);
            eventPredicates.add(new EventDateIsBeforePredicate(nonInclusiveEndDate));
        }

        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new ParseException(MESSAGE_INVALID_DATE_RANGE);
        }

        List<String> friendNameSubstrings = argMultimap.getAllValues(PREFIX_FRIEND_NAME);
        for (String substring : friendNameSubstrings) {
            FriendName friendNameSubstring = ParserUtil.parseFriendName(substring);
            eventPredicates.add(new EventFriendNamesContainSubstringPredicate(friendNameSubstring));
        }

        return new FindEventCommand(Collections.unmodifiableList(eventPredicates));
    }
}
