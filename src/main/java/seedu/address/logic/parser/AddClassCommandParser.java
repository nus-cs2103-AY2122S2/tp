package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VENUE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.Venue;

public class AddClassCommandParser implements Parser<AddClassCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddClassCommand
     * and returns an AddClassCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClassCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TUTORIALNAME, PREFIX_VENUE,
                        PREFIX_DAY, PREFIX_TIME, PREFIX_WEEK);

        if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIALNAME, PREFIX_VENUE, PREFIX_DAY, PREFIX_TIME, PREFIX_WEEK)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE));
        }

        TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
        Venue venue = ParserUtil.parseVenue(argMultimap.getValue(PREFIX_VENUE).get());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Time time = ParserUtil.parseTime(argMultimap.getValue(PREFIX_TIME).get());
        int week = ParserUtil.parseWeek(argMultimap.getValue(PREFIX_WEEK).get());

        Tutorial tutorial = new Tutorial(tutorialName, venue, day, time, week);

        return new AddClassCommand(tutorial);
    }

}
