package seedu.address.logic.parser;

import seedu.address.logic.commands.DetailedViewExecutable;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input to create a
 * {@code DetailViewExecutable} of type {@code T}.
 */
public interface DetailedViewExecutableParser<T extends DetailedViewExecutable> {

    /**
     * Parses {@code userInput} into a command that can execute in detailed view mode
     * and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parseInDetailedViewContext(String userInput) throws ParseException;
}
