package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupName;

/**
 * Parses input arguments and creates a new ViewTaskCommand object.
 */
public class ViewTaskCommandParser implements Parser<ViewTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code ViewTaskCommand}
     * and returns an {@code ViewTaskCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public ViewTaskCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_GROUP_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTaskCommand.MESSAGE_USAGE));
        }

        GroupName groupName = ParserUtil.parseGroupName(argMultimap.getValue(PREFIX_GROUP_NAME).orElse(""));
        Group group = new Group(groupName);

        return new ViewTaskCommand(group);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
