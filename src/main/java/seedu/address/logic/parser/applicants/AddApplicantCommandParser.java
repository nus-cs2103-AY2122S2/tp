package seedu.address.logic.parser.applicants;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.applicant.AddApplicantCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddApplicantCommand object
 */
public class AddApplicantCommandParser implements Parser<AddApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicantCommand
     * and returns an AddApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddApplicantCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AGE, PREFIX_ADDRESS, PREFIX_GENDER, PREFIX_PHONE,
                        PREFIX_EMAIL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_AGE, PREFIX_ADDRESS, PREFIX_GENDER, PREFIX_PHONE,
                PREFIX_EMAIL) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicantCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Age age = ParserUtil.parseAge(argMultimap.getValue(PREFIX_AGE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Gender gender = ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Applicant applicant = new Applicant(name, phone, email, age, address, gender, tagList);

        return new AddApplicantCommand(applicant);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
