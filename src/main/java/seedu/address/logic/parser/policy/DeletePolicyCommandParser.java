package seedu.address.logic.parser.policy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.policy.DeletePolicyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeletePolicyCommand object
 */
public class DeletePolicyCommandParser implements Parser<DeletePolicyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePolicyCommand
     * and returns a DeletePolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePolicyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POLICY_INDEX);

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE));
        }

        Index policyIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_POLICY_INDEX).get());
        Index clientIndex;
        try {
            clientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePolicyCommand.MESSAGE_USAGE),
                    pe);
        }

        return new DeletePolicyCommand(clientIndex, policyIndex);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
