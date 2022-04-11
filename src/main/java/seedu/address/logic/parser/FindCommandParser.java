package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARTICIPANTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindCommand.FindPersonDescriptor;
import seedu.address.logic.commands.FindEventCommand;
import seedu.address.logic.commands.FindEventCommand.FindEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<Command> {

    public static final String DATE_TIME_FORMAT = "Please provide the date followed by time\n"
            + "Eg: find -e dt/2022-08-08 03:00";

    public static final String EMPTY_EMAIL = "Please provide a valid string for email.";

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @return a FindCommand object that will execute the search.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_CCA, PREFIX_EDUCATION, PREFIX_MODULE, PREFIX_INTERNSHIP, PREFIX_EVENT_NAME,
                        PREFIX_INFO, PREFIX_PARTICIPANTS, PREFIX_DATETIME);

        boolean isAndSearch = false;
        boolean isEventSearch = false;

        if (argMultimap.getPreamble().equals("-s")) {
            isAndSearch = true;
        } else if (argMultimap.getPreamble().equals("-e")) {
            isEventSearch = true;
        } else if (!argMultimap.getPreamble().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!isEventSearch) {
            FindPersonDescriptor personDescriptor = new FindCommand.FindPersonDescriptor();
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                personDescriptor.setNames(ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_NAME)));
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                personDescriptor.setPhones(ParserUtil.parsePhones(argMultimap.getAllValues(PREFIX_PHONE)));
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                List<String> emails = argMultimap.getAllValues(PREFIX_EMAIL);
                if (emails.contains("")) {
                    throw new ParseException(EMPTY_EMAIL);
                }
                personDescriptor.setEmails(emails);
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                personDescriptor.setAddresses(ParserUtil.parseAddresses(argMultimap.getAllValues(PREFIX_ADDRESS)));
            }
            if (argMultimap.getValue(PREFIX_CCA).isPresent()) {
                List<Tag> cca = ParserUtil.parseTagsForFind(argMultimap.getAllValues(PREFIX_CCA), Tag.CCA);
                personDescriptor.setCcas(cca);
            }
            if (argMultimap.getValue(PREFIX_EDUCATION).isPresent()) {
                List<Tag> education = ParserUtil.parseTagsForFind(argMultimap.getAllValues(PREFIX_EDUCATION),
                        Tag.EDUCATION);
                personDescriptor.setEducations(education);
            }
            if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
                List<Tag> module = ParserUtil.parseTagsForFind(argMultimap.getAllValues(PREFIX_MODULE), Tag.MODULE);
                personDescriptor.setModules(module);
            }
            if (argMultimap.getValue(PREFIX_INTERNSHIP).isPresent()) {
                List<Tag> internship = ParserUtil.parseTagsForFind(argMultimap.getAllValues(PREFIX_INTERNSHIP),
                        Tag.INTERNSHIP);
                personDescriptor.setInternships(internship);
            }

            if (!personDescriptor.isAnyFieldPresent()) {
                throw new ParseException(FindCommand.MESSAGE_NO_PARAMETERS);
            }

            if (isAndSearch) {
                return new FindAndPredicateParser().parse(personDescriptor);
            } else {
                return new FindOrPredicateParser().parse(personDescriptor);
            }
        } else {
            FindEventDescriptor eventDescriptor = new FindEventCommand.FindEventDescriptor();
            if (argMultimap.getValue(PREFIX_EVENT_NAME).isPresent()) {
                eventDescriptor.setNames(ParserUtil.parseEventNames(argMultimap.getAllValues(PREFIX_EVENT_NAME)));
            }
            if (argMultimap.getValue(PREFIX_INFO).isPresent()) {
                eventDescriptor.setInformations(ParserUtil.parseInfos(argMultimap.getAllValues(PREFIX_INFO)));
            }
            if (argMultimap.getValue(PREFIX_PARTICIPANTS).isPresent()) {
                eventDescriptor.setParticipants(ParserUtil.parseNames(argMultimap.getAllValues(PREFIX_PARTICIPANTS)));
            }
            if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
                eventDescriptor.setDateTimes(ParserUtil.parseDateTimes(argMultimap.getAllValues(PREFIX_DATETIME)));
            }

            if (!eventDescriptor.isAnyFieldPresent()) {
                throw new ParseException(FindCommand.MESSAGE_NO_PARAMETERS);
            }

            return new FindEventPredicateParser().parse(eventDescriptor);
        }
    }
}
