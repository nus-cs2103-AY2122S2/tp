package seedu.address.logic.parser.policy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.policy.AddPolicyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;

/**
 * Parses input arguments and creates a new AddPolicyCommand object
 */
public class AddPolicyCommandParser implements Parser<AddPolicyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddPolicyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_COMPANY, PREFIX_POLICY_MANAGER, PREFIX_PREMIUM);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_COMPANY, PREFIX_POLICY_MANAGER, PREFIX_PREMIUM)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE), pe);
        }

        assert argMultimap.getValue(PREFIX_NAME).isPresent();
        Name policyName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        assert argMultimap.getValue(PREFIX_COMPANY).isPresent();
        Name company = ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY).get());
        assert argMultimap.getValue(PREFIX_POLICY_MANAGER).isPresent();
        Name policyManager = ParserUtil.parseName(argMultimap.getValue(PREFIX_POLICY_MANAGER).get());
        assert argMultimap.getValue(PREFIX_PREMIUM).isPresent();
        Premium premium = ParserUtil.parsePremium(argMultimap.getValue(PREFIX_PREMIUM).get());

        Policy policy = new Policy(policyName, company, policyManager, premium);
        return new AddPolicyCommand(index, policy);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
