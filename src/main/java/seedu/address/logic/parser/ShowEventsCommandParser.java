package seedu.address.logic.parser;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.ShowEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_PAST_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_EVENTS;

public class ShowEventsCommandParser {

    public ShowEventsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Predicate<Event> predicate;

        if (argMultimap.getPreamble().equals("-upcoming")) {
            predicate  = PREDICATE_SHOW_UPCOMING_EVENTS;
        } else if (argMultimap.getPreamble().equals("-past")) {
            predicate = PREDICATE_SHOW_PAST_EVENTS;
        } else if (argMultimap.getPreamble().equals("")) {
            predicate = PREDICATE_SHOW_ALL_EVENTS;
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ShowEventsCommand.MESSAGE_USAGE));
        }

        return new ShowEventsCommand(predicate);
    }
}
