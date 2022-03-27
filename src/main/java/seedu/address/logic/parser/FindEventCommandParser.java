package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.*;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.EditEventCommand;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.*;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindEventPersonCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns a FindEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY,
                        PREFIX_DATE, PREFIX_TIME, PREFIX_LOCATION, PREFIX_TAG);

        if(!isValid(argMultimap)) {
            throw new ParseException(FindEventCommand.MESSAGE_NOT_QUERIED);
        }

        return new FindEventCommand(argMultimap);
    }

    private boolean isValid(ArgumentMultimap argumentMultimap) {
        return (argumentMultimap.getValue(PREFIX_NAME).isPresent() ||
                argumentMultimap.getValue(PREFIX_COMPANY).isPresent() ||
                argumentMultimap.getValue(PREFIX_DATE).isPresent() ||
                argumentMultimap.getValue(PREFIX_TIME).isPresent() ||
                argumentMultimap.getValue(PREFIX_LOCATION).isPresent() ||
                argumentMultimap.getValue(PREFIX_COMPANY).isPresent() ||
                argumentMultimap.getValue(PREFIX_TAG).isPresent()) ;
    }
}