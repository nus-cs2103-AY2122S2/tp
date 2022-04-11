package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JERSEY_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JERSEY_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_PF;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JERSEY_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SF;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.LineupBuilder.DEFAULT_LINEUP_NAME;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_DATETIME;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_DESCRIPTION;
import static seedu.address.testutil.ScheduleBuilder.DEFAULT_SCHEDULE_NAME;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.LineupBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws ParseException {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_SF).build();
        assertTrue(true);

        // multiple names - last name accepted
        assertParseSuccess(parser, " " + PREFIX_PLAYER + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, " " + PREFIX_PLAYER + PHONE_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, " " + PREFIX_PLAYER + EMAIL_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPerson));

        // multiple heights - last height accepted
        assertParseSuccess(parser, " " + PREFIX_PLAYER + HEIGHT_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPerson));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, " " + PREFIX_PLAYER + WEIGHT_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_SF, VALID_TAG_PF)
                .build();
        assertParseSuccess(parser, " " + PREFIX_PLAYER + TAG_DESC_PF + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, " " + PREFIX_PLAYER + NAME_DESC_AMY + PHONE_DESC_AMY
                + EMAIL_DESC_AMY + HEIGHT_DESC_AMY + JERSEY_NUMBER_DESC_AMY + WEIGHT_DESC_AMY,
                new AddCommand(expectedPerson));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_PLAYER);

        // missing name prefix
        assertParseFailure(parser, " " + PREFIX_PLAYER + VALID_NAME_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, " " + PREFIX_PLAYER + VALID_NAME_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_HEIGHT_BOB + VALID_JERSEY_NUMBER_BOB + VALID_WEIGHT_BOB
                + VALID_TAG_SF, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, " " + PREFIX_PLAYER + INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + TAG_DESC_SF, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, " " + PREFIX_PLAYER + NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + HEIGHT_DESC_BOB + JERSEY_NUMBER_DESC_BOB + WEIGHT_DESC_BOB
                + INVALID_TAG_DESC, Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_lineup_success() {
        // new lineup with valid values
        Lineup expectedLineup = new LineupBuilder().build();
        assertParseSuccess(parser, " " + PREFIX_LINEUP + " " + PREFIX_NAME + DEFAULT_LINEUP_NAME,
                new AddCommand(expectedLineup));
    }

    @Test
    public void parse_schedule_success() {
        // new schedule with valid values
        Schedule expectedSchedule = new ScheduleBuilder().build();
        assertParseSuccess(parser, " " + PREFIX_SCHEDULE + " " + PREFIX_NAME + DEFAULT_SCHEDULE_NAME + " "
                + PREFIX_DESCRIPTION + DEFAULT_DESCRIPTION + " " + PREFIX_DATE + DEFAULT_DATETIME,
                new AddCommand(expectedSchedule));
    }
}
