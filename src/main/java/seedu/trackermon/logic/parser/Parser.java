package seedu.trackermon.logic.parser;

import seedu.trackermon.logic.commands.Command;
import seedu.trackermon.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface Parser<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @param userInput the user's input.
     * @return the parsed user's input.
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    T parse(String userInput) throws ParseException;

}
