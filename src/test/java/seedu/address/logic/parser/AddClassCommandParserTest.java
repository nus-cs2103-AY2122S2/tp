package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.TutorialTestUtil.DAY_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.DAY_DESC_T02;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_TIME_DESC;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_TUTORIAL_NAME;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_WEEK_DESC;
import static seedu.address.logic.commands.TutorialTestUtil.TIME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.TIME_DESC_T02;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T02;
import static seedu.address.logic.commands.TutorialTestUtil.VENUE_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.VENUE_DESC_T02;
import static seedu.address.logic.commands.TutorialTestUtil.WEEKS_DESC_T01;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.testutil.TutorialBuilder;

public class AddClassCommandParserTest {
    private AddClassCommandParser parser = new AddClassCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tutorial expectedTutorial = new TutorialBuilder(T01).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TUTORIALNAME_DESC_T01 + DAY_DESC_T01
                + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01, new AddClassCommand(expectedTutorial));

        // multiple names - last name accepted
        assertParseSuccess(parser, TUTORIALNAME_DESC_T02 + TUTORIALNAME_DESC_T01 + DAY_DESC_T01
                + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01, new AddClassCommand(expectedTutorial));

        // multiple days - last day accepted
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T02 + DAY_DESC_T01
                + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01, new AddClassCommand(expectedTutorial));

        // multiple time - last time accepted
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T02
                + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01, new AddClassCommand(expectedTutorial));

        // multiple venue - last venue accepted
        assertParseSuccess(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01
                + VENUE_DESC_T02 + VENUE_DESC_T01 + WEEKS_DESC_T01, new AddClassCommand(expectedTutorial));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClassCommand.MESSAGE_USAGE);

        // missing tutorial name prefix
        assertParseFailure(parser, DAY_DESC_T01 + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01,
                expectedMessage);

        // missing day prefix
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + TIME_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01,
                expectedMessage);

        // missing time prefix
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + VENUE_DESC_T01 + WEEKS_DESC_T01,
                expectedMessage);

        // missing week prefix
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01 + VENUE_DESC_T01,
                expectedMessage);

        // missing venue prefix
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01 + WEEKS_DESC_T01,
                expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid tutorial name
        assertParseFailure(parser, INVALID_TUTORIAL_NAME + DAY_DESC_T01 + TIME_DESC_T01 + VENUE_DESC_T01
                + WEEKS_DESC_T01, TutorialName.MESSAGE_CONSTRAINTS);

        // invalid day
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + INVALID_DAY_DESC + TIME_DESC_T01 + VENUE_DESC_T01
                + WEEKS_DESC_T01, Day.MESSAGE_CONSTRAINTS);

        // invalid time
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + INVALID_TIME_DESC + VENUE_DESC_T01
                + WEEKS_DESC_T01, Time.MESSAGE_CONSTRAINTS);

        // invalid week
        assertParseFailure(parser, TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01 + VENUE_DESC_T01
                + INVALID_WEEK_DESC, "Weeks should be presented in positive integers between 1-60!");

        // multiple invalid values, only first invalid velue reported
        assertParseFailure(parser, INVALID_TUTORIAL_NAME + INVALID_DAY_DESC + TIME_DESC_T01 + VENUE_DESC_T01
                + WEEKS_DESC_T01, TutorialName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TUTORIALNAME_DESC_T01 + DAY_DESC_T01 + TIME_DESC_T01
                + VENUE_DESC_T01 + WEEKS_DESC_T01, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddClassCommand.MESSAGE_USAGE));

    }
}
