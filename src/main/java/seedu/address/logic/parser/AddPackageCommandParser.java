package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PACKAGE_DESC;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddPackageCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.InsurancePackage;

public class AddPackageCommandParser implements Parser<AddPackageCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPackageCommand
     * and returns an AddPackageCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPackageCommand parse(String args) throws ParseException {
        System.out.println(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INSURANCE_PACKAGE, PREFIX_PACKAGE_DESC);

        if (!arePrefixesPresent(argMultimap, PREFIX_INSURANCE_PACKAGE, PREFIX_PACKAGE_DESC)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPackageCommand.MESSAGE_USAGE));
        }

        String packageName = ParserUtil.parseInsurancePackageName(argMultimap.getValue(PREFIX_INSURANCE_PACKAGE).get());
        String packageDesc = ParserUtil.parseInsurancePackageDesc(argMultimap.getValue(PREFIX_PACKAGE_DESC).get());
        InsurancePackage insurancePackage = new InsurancePackage(packageName, packageDesc);
        return new AddPackageCommand(insurancePackage);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
