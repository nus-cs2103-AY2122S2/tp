package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE, PREFIX_STUDENT_EMAIL,
                        PREFIX_STUDENT_ADDRESS, PREFIX_STUDENT_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_NAME, PREFIX_STUDENT_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        Student newStudent = createStudent(argMultimap);
        return new AddStudentCommand(newStudent);
    }

    private Student createStudent(ArgumentMultimap argMultimap) throws ParseException {
        Name name = getName(argMultimap);
        Phone phone = getPhone(argMultimap);
        Email email = getEmail(argMultimap);
        Address address = getAddress(argMultimap);
        Set<Tag> tagList = getTagList(argMultimap);

        return new Student(name, phone, email, address, tagList);
    }

    /**
     * Retrieve a {@code Name} from the provided arguments.
     */
    private static Name getName(ArgumentMultimap argMultimap) throws ParseException {
        return ParserUtil.parseName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
    }

    /**
     * Retrieve a {@code Phone} from the provided arguments.
     */
    private static Phone getPhone(ArgumentMultimap argMultimap) throws ParseException {
        return ParserUtil.parsePhone(argMultimap.getValue(PREFIX_STUDENT_PHONE).get());
    }

    /**
     * Retrieve an {@code Email} from the provided arguments.
     */
    private static Email getEmail(ArgumentMultimap argMultimap) throws ParseException {
        if (hasEmailField(argMultimap)) {
            return ParserUtil.parseEmail(argMultimap.getValue(PREFIX_STUDENT_EMAIL).get());
        }
        return Email.EMPTY_EMAIL;
    }

    private static boolean hasEmailField(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_EMAIL).isPresent();
    }

    /**
     * Retrieve an {@code Address} from the provided arguments.
     */
    private static Address getAddress(ArgumentMultimap argMultimap) throws ParseException {
        if (hasAddressField(argMultimap)) {
            return ParserUtil.parseAddress(argMultimap.getValue(PREFIX_STUDENT_ADDRESS).get());
        }
        return Address.EMPTY_ADDRESS;
    }

    private static boolean hasAddressField(ArgumentMultimap argMultimap) {
        return argMultimap.getValue(PREFIX_STUDENT_ADDRESS).isPresent();
    }

    /**
     * Retrieve the {@code Tag}s from the provided arguments and return it in a {@code Set<Tag>}.
     */
    private static Set<Tag> getTagList(ArgumentMultimap argMultimap) throws ParseException {
        return ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_STUDENT_TAG));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
