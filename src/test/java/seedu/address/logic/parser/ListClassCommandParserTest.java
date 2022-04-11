package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtilTest.TUTORIAL_DAY_DESC_1;
import static seedu.address.logic.parser.ParserUtilTest.TUTORIAL_DAY_DESC_2;
import static seedu.address.logic.parser.ParserUtilTest.TUTORIAL_NAME_DESC_1;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListClassCommand;
import seedu.address.model.tutorial.Tutorial;
import seedu.address.model.tutorial.TutorialHasSameDay;
import seedu.address.testutil.TutorialBuilder;

public class ListClassCommandParserTest {
    private ListClassCommandParser parser = new ListClassCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Tutorial expectedClass = new TutorialBuilder(T01).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TUTORIAL_DAY_DESC_1,
                new ListClassCommand(new TutorialHasSameDay(expectedClass.getDay())));

        // multiple days - last day accepted
        assertParseSuccess(parser, TUTORIAL_DAY_DESC_2 + TUTORIAL_DAY_DESC_1,
                new ListClassCommand(new TutorialHasSameDay(expectedClass.getDay())));

    }

    @Test
    public void parse_dayFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListClassCommand.MESSAGE_USAGE);

        // missing day field
        assertParseFailure(parser, TUTORIAL_NAME_DESC_1, expectedMessage);
    }

    @Test
    public void parse_emptyField_success() {
        String emptyString = "";
        assertParseSuccess(parser, emptyString, new ListClassCommand(null));
    }

    @Test
    public void parse_spaceField_success() {
        String blankString = "  ";
        assertParseSuccess(parser, blankString, new ListClassCommand(null));
    }

}
