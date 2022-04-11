package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2103T_W15_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTutorialGroupCommand;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteTutorialGroupParserTest {

    private static final String TUTORIAL_GROUP_EMPTY = " " + PREFIX_TUTORIAL_GROUP;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTutorialGroupCommand.MESSAGE_USAGE);

    private DeleteTutorialGroupParser parser = new DeleteTutorialGroupParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TUTORIAL_GROUP_DESC_CS2101_G08,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-6" + TUTORIAL_GROUP_DESC_CS2103T_W15_3,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // zero
        assertParseFailure(parser, "0" + TUTORIAL_GROUP_DESC_CS2103T_W15_3,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // invalid preamble and invalid tutorial group, but only the first is captured
        assertParseFailure(parser, "-1" + INVALID_TUTORIAL_GROUP_DESC,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        //index larger than 2147483647
        assertParseFailure(parser, "2147483648" + TUTORIAL_GROUP_DESC_CS2103T_W15_3,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty tutorial group
        assertParseFailure(parser, "1" + TUTORIAL_GROUP_EMPTY, TutorialGroup.MESSAGE_CONSTRAINTS);

        // invalid tutorial group
        assertParseFailure(parser, "1" + INVALID_TUTORIAL_GROUP_DESC, TutorialGroup.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleValues_failure() {
        // more than one tutorial group
        assertParseFailure(parser,
                "1" + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08,
                DeleteTutorialGroupCommand.MESSAGE_NOT_DELETED);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // valid index and tutorial group
        Index targetIndex = INDEX_FIRST_STUDENT;
        String input = targetIndex.getOneBased() + TUTORIAL_GROUP_DESC_CS2101_G08;

        DeleteTutorialGroupCommand expectedCommand = new DeleteTutorialGroupCommand(
                targetIndex, new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08));

        assertParseSuccess(parser, input, expectedCommand);
    }

}
