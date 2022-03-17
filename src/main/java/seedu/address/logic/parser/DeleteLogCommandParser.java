package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.FLAG_ALL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOG_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteLogCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.FriendName;

/**
 * Parses the input arguments and creates a new DeleteLogCommand object.
 */
public class DeleteLogCommandParser implements Parser<DeleteLogCommand> {

    public static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLogCommand.MESSAGE_USAGE);


    /**
     * Parses a string of arguments and creates a {@code DeleteLogCommand}.
     *
     * @throws ParseException if arguments are formatted wrongly.
     */
    public DeleteLogCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // tokenize
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_LOG_INDEX, FLAG_ALL);

        // check that log index is present, or all flag present, but not both
        if (arePrefixesPresent(argMultimap, PREFIX_LOG_INDEX) == arePrefixesPresent(argMultimap, FLAG_ALL)) {
            throw new ParseException(MESSAGE_INVALID_FORMAT);
        }

        // initialize
        Index personIndex = null;
        Index logIndex = null;
        FriendName personName = null;
        boolean hasIndex = !argMultimap.getPreamble().isEmpty();
        boolean hasName = arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean isForOnePerson = hasIndex ^ hasName;
        boolean isForDeletingAllLogs = false;

        // parse index or name
        if (hasIndex) {
            personIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } else if (hasName) {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                personName = ParserUtil.parseFriendName(argMultimap.getValue(PREFIX_NAME).get());
            }
        }

        // read other arguments
        if (argMultimap.getValue(PREFIX_LOG_INDEX).isPresent()) {
            logIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_LOG_INDEX).get());
        }
        if (arePrefixesPresent(argMultimap, FLAG_ALL)) {
            isForDeletingAllLogs = true;
        }

        // check for validity
        // case 1: delete specific log, specific person
        // case 2: delete all logs, specific person
        // case 3: delete all logs
        if (!(isForOnePerson && !isForDeletingAllLogs && logIndex != null
                || isForOnePerson && isForDeletingAllLogs && logIndex == null
                || !isForOnePerson && isForDeletingAllLogs && logIndex == null && !hasIndex)) {
            throw new ParseException(MESSAGE_INVALID_FORMAT);
        }

        // call correct constructor
        DeleteLogCommand command;
        if (hasIndex) {
            command = new DeleteLogCommand(isForOnePerson, isForDeletingAllLogs, personIndex, logIndex);
        } else {
            if (personName == null) { // means delete all
                command = new DeleteLogCommand(isForDeletingAllLogs);
            } else {
                command = new DeleteLogCommand(isForOnePerson, isForDeletingAllLogs, personName, logIndex);
            }

        }
        return command;
    }
}
