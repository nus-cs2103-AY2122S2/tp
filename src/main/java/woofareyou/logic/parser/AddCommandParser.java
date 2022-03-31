package woofareyou.logic.parser;

import java.util.Set;
import java.util.stream.Stream;

import woofareyou.commons.core.Messages;
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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_OWNER_NAME,
                        CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_OWNER_NAME, CliSyntax.PREFIX_NAME,
                CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        OwnerName ownerName = ParserUtil.parseOwnerName(argMultimap.getValue(CliSyntax.PREFIX_OWNER_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(CliSyntax.PREFIX_TAG));
        Diet diet = new Diet("");
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
