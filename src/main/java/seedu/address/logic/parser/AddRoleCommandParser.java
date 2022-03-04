package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import javafx.util.Pair;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.role.Status;
import seedu.address.model.role.Role;
import seedu.address.model.role.Deadline;
import seedu.address.model.role.RoleName;
import seedu.address.model.role.Description;
import seedu.address.model.role.Stipend;

/**
 * Parses input arguments and creates a new AddRoleCommand object
 */
public class AddRoleCommandParser implements Parser<AddRoleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRoleCommand
     * and returns an AddRoleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRoleCommand parse(String args) throws ParseException {
       Pair<Index, String> parsedInput;
        try {
            parsedInput = ParserUtil.parseIndexWithContent(args);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoleCommand.MESSAGE_USAGE), pe);
        }

        Index index = parsedInput.getKey();
        String content = parsedInput.getValue();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(content, PREFIX_NAME, PREFIX_STATUS,
                        PREFIX_DEADLINE, PREFIX_DESCRIPTION, PREFIX_STIPEND);
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoleCommand.MESSAGE_USAGE));
        }
        RoleName name = ParserUtil.parseRoleName(argMultimap.getValue(PREFIX_NAME).get());
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());
        Deadline deadline = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getOptionalValue(PREFIX_DESCRIPTION).get());
        Stipend stipend = ParserUtil.parseStipend(argMultimap.getOptionalValue(PREFIX_STIPEND).get());
        Role role = new Role(name, status, deadline, description, stipend);
        return new AddRoleCommand(index, role);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
