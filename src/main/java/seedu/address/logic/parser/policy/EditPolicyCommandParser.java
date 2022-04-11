package seedu.address.logic.parser.policy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POLICY_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREMIUM;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.policy.EditPolicyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Premium;

/**
 * Parses input arguments and creates a new EditPolicyCommand object
 */
public class EditPolicyCommandParser implements Parser<EditPolicyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditPolicyCommand
     * and returns a EditPolicyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPolicyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_POLICY_INDEX, PREFIX_NAME,
                        PREFIX_COMPANY, PREFIX_POLICY_MANAGER, PREFIX_PREMIUM);

        if (!arePrefixesPresent(argMultimap, PREFIX_POLICY_INDEX)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE));
        }
        Index policyIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_POLICY_INDEX).get());
        Index clientIndex;
        try {
            clientIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE),
                    pe);
        }

        EditPolicyCommand.EditPolicyDescriptor editPolicyDescriptor = new EditPolicyCommand.EditPolicyDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name policyName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            editPolicyDescriptor.setName(policyName);
        }

        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            Name company = ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY).get());
            editPolicyDescriptor.setCompany(company);
        }

        if (argMultimap.getValue(PREFIX_POLICY_MANAGER).isPresent()) {
            Name policyManager = ParserUtil.parseName(argMultimap.getValue(PREFIX_POLICY_MANAGER).get());
            editPolicyDescriptor.setPolicyManager(policyManager);
        }

        if (argMultimap.getValue(PREFIX_PREMIUM).isPresent()) {
            Premium premium = ParserUtil.parsePremium(argMultimap.getValue(PREFIX_PREMIUM).get());
            editPolicyDescriptor.setPremium(premium);
        }

        if (!editPolicyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPolicyCommand(clientIndex, policyIndex, editPolicyDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
