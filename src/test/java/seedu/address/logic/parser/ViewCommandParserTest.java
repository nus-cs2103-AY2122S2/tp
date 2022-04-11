package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ACTIVE_SCHEDULE_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WITHOUT_LINEUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ACTIVE_SCHEDULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;
import static seedu.address.model.Model.PREDICATE_SHOW_ARCHIVED_SCHEDULES;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;
import seedu.address.model.person.Person;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_viewCommand_success() {
        // view all players
        List<String> prefixAndArgPerson = new ArrayList<>();
        prefixAndArgPerson.add(PREFIX_PLAYER.toString());
        List<Predicate<Person>> predicatesPerson = new ArrayList<>();
        predicatesPerson.add(PREDICATE_SHOW_ALL_PERSONS);
        assertParseSuccess(parser, " " + PREFIX_PLAYER,
                new ViewCommand(predicatesPerson, null, prefixAndArgPerson));

        // view all lineups
        List<String> prefixAndArgLineup = new ArrayList<>();
        prefixAndArgLineup.add(PREFIX_WITHOUT_LINEUP.toString());
        List<Predicate<Person>> predicatesLineup = new ArrayList<>();
        predicatesLineup.add(PREDICATE_SHOW_ALL_PERSONS_WITH_LINEUP);
        assertParseSuccess(parser, " " + PREFIX_LINEUP,
                new ViewCommand(predicatesLineup, null, prefixAndArgLineup));

        // view all schedules
        List<String> prefixAndArgumentSchedule = new ArrayList<>();
        prefixAndArgumentSchedule.add(PREFIX_SCHEDULE.toString());
        assertParseSuccess(parser, " " + PREFIX_SCHEDULE,
                new ViewCommand(null, PREDICATE_SHOW_ACTIVE_SCHEDULES, prefixAndArgumentSchedule));
        assertParseSuccess(parser, " " + PREFIX_SCHEDULE + " " + PREFIX_ALL_SCHEDULE + "all",
                new ViewCommand(null, PREDICATE_SHOW_ALL_SCHEDULES, prefixAndArgumentSchedule));
        assertParseSuccess(parser, " " + PREFIX_SCHEDULE + " " + PREFIX_ALL_SCHEDULE + "archive",
                new ViewCommand(null, PREDICATE_SHOW_ARCHIVED_SCHEDULES, prefixAndArgumentSchedule));
    }

    @Test
    public void parse_invalidPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);

        assertParseFailure(parser, " " + PREFIX_PLAYER + " " + PREFIX_LINEUP, expectedMessage);
    }

    @Test
    public void parse_invalidScheduleArchive_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_ACTIVE_SCHEDULE_FORMAT,
                ViewCommand.MESSAGE_ACTIVE_SCHEDULE_USAGE);

        assertParseFailure(parser, " " + PREFIX_SCHEDULE + " " + PREFIX_ALL_SCHEDULE + " invalid_input",
                expectedMessage);
    }
}
