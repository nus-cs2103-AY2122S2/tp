package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.EventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EventCommandParser implements Parser<EventCommand> {

    public EventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        return null;
    }
}
