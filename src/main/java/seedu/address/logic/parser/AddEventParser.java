//package seedu.address.logic.parser;
//
//import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIEND;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//
//import java.util.Set;
//
//import seedu.address.logic.commands.AddCommand;
//import seedu.address.logic.commands.AddEventCommand;
//import seedu.address.logic.parser.exceptions.ParseException;
//import seedu.address.model.event.DateTime;
//import seedu.address.model.event.Event;
//import seedu.address.model.person.Description;
//import seedu.address.model.person.Name;
//import seedu.address.model.person.Person;
//
///**
// * Parses input arguments and creates a new AddEventCommand object
// */
//public class AddEventParser implements Parser<AddEventCommand> {
//
//    /**
//     * Parses the given {@code String} of arguments in the context of the AddEventCommand
//     * and returns an AddEventCommand object for execution.
//     * @throws ParseException if the user input does not conform the expected format
//     */
//    public AddEventCommand parse(String args) throws ParseException {
//        ArgumentMultimap argMultimap =
//                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATETIME, PREFIX_DESCRIPTION, PREFIX_FRIEND);
//
//        if (!argMultimap.arePrefixesPresent(PREFIX_NAME, PREFIX_DATETIME)
//                || !argMultimap.getPreamble().isEmpty()) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//        }
//
//        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
//        DateTime dateTime = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_DATETIME).get());
//        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
//        Set<Person> friendList = ParserUtil.parseFriends(argMultimap.getAllValues(PREFIX_FRIEND));
//
//        Event event = new Event(name, dateTime,description, friendList);
//
//        return new AddEventCommand(event);
//    }
//}
