package woofareyou.logic.parser;

import static woofareyou.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static woofareyou.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static woofareyou.logic.parser.CliSyntax.PREFIX_DIET;
import static woofareyou.logic.parser.CliSyntax.PREFIX_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static woofareyou.logic.parser.CliSyntax.PREFIX_PHONE;
import static woofareyou.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import woofareyou.logic.commands.AddCommand;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.pet.Address;
import woofareyou.model.pet.Appointment;
import woofareyou.model.pet.AttendanceHashMap;
import woofareyou.model.pet.Diet;
import woofareyou.model.pet.Name;
import woofareyou.model.pet.OwnerName;
import woofareyou.model.pet.Pet;
import woofareyou.model.pet.Phone;
import woofareyou.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_OWNER_NAME, PREFIX_PHONE,
                        PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_OWNER_NAME, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Diet diet = new Diet("");
        if (arePrefixesPresent(argMultimap, PREFIX_DIET)) {
            diet = ParserUtil.parseDiet(argMultimap.getValue(PREFIX_DIET).get());
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        OwnerName ownerName = ParserUtil.parseOwnerName(argMultimap.getValue(PREFIX_OWNER_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Appointment appointment = new Appointment();
        AttendanceHashMap attendanceHashMap = new AttendanceHashMap();

        Pet pet = new Pet(name, ownerName, phone, address, tagList, diet, appointment, attendanceHashMap);

        return new AddCommand(pet);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
