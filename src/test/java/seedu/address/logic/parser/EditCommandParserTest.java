package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.EditCommand.createEditedLineup;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.LineupBuilder.DEFAULT_LINEUP_NAME;
import static seedu.address.testutil.LineupBuilder.NEW_LINEUP_NAME;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_DATETIME;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_DESCRIPTION;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_SCHEDULE_NAME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SCHEDULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditScheduleDescriptorBuilder;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.ScheduleBuilder;

public class EditCommandParserTest {
    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_editPerson_success() {
        Person person = new PersonBuilder().build();
        EditCommand.EditPersonDescriptor editedPerson =
                new EditPersonDescriptorBuilder(person).build();

        assertParseSuccess(parser, PersonUtil.getEditCommand(person),
                new EditCommand(new Name(VALID_NAME_BOB), editedPerson));
    }

    @Test
    public void parse_editLineup_success() {
        Lineup lineup = new LineupBuilder().build();
        Lineup editedLineup = createEditedLineup(lineup, new LineupName(NEW_LINEUP_NAME));

        assertParseSuccess(parser, EditCommand.COMMAND_WORD + " " + PREFIX_LINEUP + DEFAULT_LINEUP_NAME + " "
                        + PREFIX_NAME + NEW_LINEUP_NAME,
                new EditCommand(lineup.getLineupName(), editedLineup.getLineupName()));
    }

    @Test
    public void parse_editSchedule_success() {
        Schedule schedule = new ScheduleBuilder().build();
        EditCommand.EditScheduleDescriptor editedScheduleDescriptor =
                new EditScheduleDescriptorBuilder(schedule).build();

        assertParseSuccess(parser, EditCommand.COMMAND_WORD + " " + PREFIX_SCHEDULE + "1 "
               + PREFIX_NAME + DEFAULT_SCHEDULE_NAME + " " + PREFIX_DESCRIPTION + DEFAULT_DESCRIPTION + " "
               + PREFIX_DATE + DEFAULT_DATETIME, new EditCommand(INDEX_FIRST_SCHEDULE, editedScheduleDescriptor));
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessageLineup = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE_LINEUP);
        String expectedMessageSchedule = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE_SCHEDULE);
        String expectedMessageNotEdited = EditCommand.MESSAGE_NOT_EDITED;

        // invalid lineup command
        assertParseFailure(parser, " " + PREFIX_LINEUP, expectedMessageLineup);

        // invalid schedule command
        assertParseFailure(parser, " " + PREFIX_SCHEDULE, expectedMessageSchedule);

        // invalid nothing edited command
        assertParseFailure(parser, " " + PREFIX_SCHEDULE + "1", expectedMessageNotEdited);
    }
}
