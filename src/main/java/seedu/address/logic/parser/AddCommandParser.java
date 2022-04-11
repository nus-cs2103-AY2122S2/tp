package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GitUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @param args String object of user input to be parsed.
     * @return AddCommand object
     * @throws ParseException If the input does not conform to the expected format.
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                 ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                         PREFIX_GIT_USERNAME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GIT_USERNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            StringBuffer sb = displayInvalidParameters(argMultimap);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, sb + "\n"
                    + AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        GitUsername gitUsername = ParserUtil.parseGitUsername(argMultimap.getValue(PREFIX_GIT_USERNAME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, gitUsername, tagList);

        return new AddCommand(person);
    }

    /**
     * Checks what parameters are missing in user's input. Returns the tags that are missing.
     * Example: If n/ and p/ are missing, return "Missing/Invalid parameters: n/, p/".
     *
     * @param argMultimap Argument Multimap of user input that is read.
     * @return StringBuffer format of missing parameters.
     */
    public StringBuffer displayInvalidParameters(ArgumentMultimap argMultimap) {
        String errorString = "Missing/Invalid parameters: ";
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            errorString += PREFIX_NAME + ", ";
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_PHONE)) {
            errorString += PREFIX_PHONE + ", ";
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_EMAIL)) {
            errorString += PREFIX_EMAIL + ", ";
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_GIT_USERNAME)) {
            errorString += PREFIX_GIT_USERNAME + ", ";
        }
        StringBuffer sb = new StringBuffer(errorString);
        sb.delete(sb.length() - 2, sb.length() - 1); //Deleting last comma
        return sb;
    }
}
