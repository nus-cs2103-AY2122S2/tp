package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.TutorialTestUtil.INVALID_TUTORIAL_NAME;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T01;
import static seedu.address.logic.commands.TutorialTestUtil.TUTORIALNAME_DESC_T02;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalTutorials.T01;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteClassCommand;
import seedu.address.model.tutorial.TutorialName;

public class DeleteClassCommandParserTest {

    private DeleteClassCommandParser parser = new DeleteClassCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteClassCommand() {
        TutorialName expectedTutorialNameToDelete = T01.getTutorialName();
        Index expectedIndex = INDEX_FIRST;

        // whitespace only preamble - tutorial
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TUTORIALNAME_DESC_T01,
                new DeleteClassCommand(null, expectedTutorialNameToDelete, true));

        // whitespace only preamble - index
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + INDEX_FIRST.getOneBased(),
                new DeleteClassCommand(expectedIndex, null, false));

        // multiple tutorial names - last tutorial name accepted
        assertParseSuccess(parser, TUTORIALNAME_DESC_T02 + TUTORIALNAME_DESC_T01,
                new DeleteClassCommand(null, expectedTutorialNameToDelete, true));

    }

    @Test
    public void parse_invalidArgs_failure() {

        // invalid tutorial name value
        assertParseFailure(parser, INVALID_TUTORIAL_NAME, TutorialName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TUTORIALNAME_DESC_T01,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClassCommand.MESSAGE_USAGE));

        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () ->
                new DeleteClassCommand(Index.fromZeroBased(-1), null, false));

    }
}
