package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_PAST_EVENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_UPCOMING_EVENTS;

import java.util.function.Predicate;

import seedu.address.logic.commands.ShowEventsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Event;

public class ShowEventsCommandParser implements Parser<ShowEventsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowEventCommand
     * and returns a ShowEventCommand object for execution.
     *
     * @return a ShowEventCommand object that will filter the events list.
     * @throws ParseException if the user input does not conform the expected format, such as having an invalid flag.
     */
    public ShowEventsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Predicate<Event> predicate;

        if (argMultimap.getPreamble().equals("-upcoming")) {
            predicate = PREDICATE_SHOW_UPCOMING_EVENTS;
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
