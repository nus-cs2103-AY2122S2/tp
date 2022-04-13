package seedu.tinner.logic.parser;

import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tinner.commons.core.Messages.MESSAGE_NO_VALUE_AFTER_PREFIX;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_ROLE_NAME;
import static seedu.tinner.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Arrays;

import seedu.tinner.logic.commands.FindCommand;
import seedu.tinner.logic.parser.exceptions.ParseException;
import seedu.tinner.model.company.CompanyNameContainsKeywordsPredicate;
import seedu.tinner.model.role.RoleNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE_NAME);

        if ((!arePrefixesPresent(argMultimap, PREFIX_COMPANY_NAME)
                && !arePrefixesPresent(argMultimap, PREFIX_ROLE_NAME)) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        if (ParserUtil.hasPrefixWithoutValue(argMultimap, PREFIX_COMPANY_NAME)
                || ParserUtil.hasPrefixWithoutValue(argMultimap, PREFIX_ROLE_NAME)) {
            throw new ParseException(String.format(MESSAGE_NO_VALUE_AFTER_PREFIX,
                    FindCommand.MESSAGE_USAGE));
        }

        String companyName = ParserUtil.parseNameReturnString(argMultimap.getOptionalValue(PREFIX_COMPANY_NAME).get());
        String roleName = ParserUtil.parseRoleNameReturnString(argMultimap.getOptionalValue(PREFIX_ROLE_NAME).get());

        String[] roleNameKeywords = roleName.split("\\s+");
        String[] companyNameKeywords = companyName.split("\\s+");

        if (roleName.isEmpty()) {
            roleNameKeywords = new String[0];
        }
        if (companyName.isEmpty()) {
            companyNameKeywords = new String[0];
        }

        return new FindCommand(new CompanyNameContainsKeywordsPredicate(Arrays.asList(roleNameKeywords),
                Arrays.asList(companyNameKeywords)),
                new RoleNameContainsKeywordsPredicate(Arrays.asList(roleNameKeywords)));
    }

}
