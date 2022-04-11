package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2103T_W15_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTutorialGroupsFromStudentsCommand;
import seedu.address.model.tutorialgroup.TutorialGroup;

public class DeleteTutorialGroupsFromStudentsParserTest {

    private static final String TUTORIAL_GROUP_EMPTY = " " + PREFIX_TUTORIAL_GROUP;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTutorialGroupsFromStudentsCommand.MESSAGE_USAGE);

    private DeleteTutorialGroupsFromStudentsParser parser = new DeleteTutorialGroupsFromStudentsParser();

    @Test
    public void parse_missingParts_failure() {
        // no prefix
        assertParseFailure(parser, VALID_TUTORIAL_GROUP_CS2101_G08, MESSAGE_INVALID_FORMAT);

        // empty string
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //no tutorial group given but name is given
        assertParseFailure(parser, VALID_EMAIL_BOB, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty tutorial group
        assertParseFailure(parser, TUTORIAL_GROUP_EMPTY, TutorialGroup.MESSAGE_CONSTRAINTS);

        // valid then invalid tutorial group
        assertParseFailure(parser, VALID_TUTORIAL_GROUP_CS2101_G08 + TUTORIAL_GROUP_EMPTY,
                TutorialGroup.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // valid tutorial groups
        String input = TUTORIAL_GROUP_DESC_CS2101_G08 + TUTORIAL_GROUP_DESC_CS2103T_W15_3;
        Set<TutorialGroup> tutorialGroupInputToDeleteTutorialGroupFromStudentsCommand = new HashSet<>(Arrays.asList(
                new TutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08),
                new TutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3)));

        DeleteTutorialGroupsFromStudentsCommand expectedCommand = new DeleteTutorialGroupsFromStudentsCommand(
                tutorialGroupInputToDeleteTutorialGroupFromStudentsCommand);

        assertParseSuccess(parser, input, expectedCommand);
    }
}
