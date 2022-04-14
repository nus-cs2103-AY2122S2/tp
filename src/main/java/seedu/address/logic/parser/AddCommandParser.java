package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.ModuleCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.TelegramHandle;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @SuppressWarnings("OptionalGetWithoutIsPresent") // This is checked using our custom function (arePrefixesPresent).
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_NAME, PREFIX_MODULE_CODE, PREFIX_PHONE,
                        PREFIX_TELEGRAM_HANDLE, PREFIX_EMAIL);

        // Check if compulsory fields are present in the user input.
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_NAME, PREFIX_MODULE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        StudentId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_ID).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ModuleCode module = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULE_CODE).get());

        Optional<String> currPhone = argMultimap.getValue(PREFIX_PHONE);
        Phone phone;
        if (currPhone.isEmpty()) {
            phone = null;
        } else {
            phone = ParserUtil.parsePhone(currPhone.get());
        }

        Optional<String> currTelegramHandle = argMultimap.getValue(PREFIX_TELEGRAM_HANDLE);
        TelegramHandle telegramHandle;
        if (currTelegramHandle.isEmpty()) {
            telegramHandle = null;
        } else {
            telegramHandle = ParserUtil.parseTelegramHandle(currTelegramHandle.get());
        }

        Optional<String> currEmail = argMultimap.getValue(PREFIX_EMAIL);
        Email email;
        if (currEmail.isEmpty()) {
            email = null;
        } else {
            email = ParserUtil.parseEmail(currEmail.get());
        }

        Person person = new Person(studentId, name, module, phone, telegramHandle, email);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
