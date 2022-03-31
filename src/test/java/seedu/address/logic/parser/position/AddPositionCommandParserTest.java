package seedu.address.logic.parser.position;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_OPENING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REQUIREMENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.OPENING_DESC_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_SR_FE_SWE;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_EXPERIENCE;
import static seedu.address.logic.commands.CommandTestUtil.REQUIREMENT_DESC_SKILL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_NAME_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_NAME_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_OPENINGS_JR_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_EXPERIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_SKILL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPositions.JR_SOFTWARE_ENGINEER;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.position.AddPositionCommand;
import seedu.address.model.position.Description;
import seedu.address.model.position.Position;
import seedu.address.model.position.PositionName;
import seedu.address.model.position.PositionOpenings;
import seedu.address.model.position.Requirement;
import seedu.address.testutil.PositionBuilder;

public class AddPositionCommandParserTest {
    private AddPositionCommandParser parser = new AddPositionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Position expectedPosition = new PositionBuilder(JR_SOFTWARE_ENGINEER)
                .withRequirements(VALID_REQUIREMENT_SKILL).build();
        Position expectedPositionMultipleRequirements = new PositionBuilder(JR_SOFTWARE_ENGINEER)
                .withRequirements(VALID_REQUIREMENT_SKILL, VALID_REQUIREMENT_EXPERIENCE).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                 PREAMBLE_WHITESPACE + POSITION_DESC_JR_SWE + OPENING_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE
                         + REQUIREMENT_DESC_SKILL, new AddPositionCommand(expectedPosition));

        // multiple position names - last position name accepted
        assertParseSuccess(parser, POSITION_DESC_SR_FE_SWE + POSITION_DESC_JR_SWE
                + OPENING_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE + REQUIREMENT_DESC_SKILL,
                new AddPositionCommand(expectedPosition));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_SR_FE_SWE + DESCRIPTION_DESC_JR_SWE
                + OPENING_DESC_JR_SWE + REQUIREMENT_DESC_SKILL, new AddPositionCommand(expectedPosition));

        // multiple openings - last opening accepted
        assertParseSuccess(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_SR_FE_SWE
                + OPENING_DESC_JR_SWE + REQUIREMENT_DESC_SKILL, new AddPositionCommand(expectedPosition));

        // multiple requirements - all accepted
        assertParseSuccess(parser, POSITION_DESC_JR_SWE + OPENING_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE
                + REQUIREMENT_DESC_SKILL + REQUIREMENT_DESC_EXPERIENCE,
                new AddPositionCommand(expectedPositionMultipleRequirements));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no requirements
        Position expectedPosition = new PositionBuilder(JR_SOFTWARE_ENGINEER).withRequirements().build();
        assertParseSuccess(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_JR_SWE,
                new AddPositionCommand(expectedPosition));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPositionCommand.MESSAGE_USAGE);

        // missing position name prefix
        assertParseFailure(parser, VALID_POSITION_NAME_JR_SWE + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_JR_SWE
                + REQUIREMENT_DESC_SKILL, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, POSITION_DESC_JR_SWE + VALID_DESCRIPTION_NAME_JR_SWE + OPENING_DESC_JR_SWE
                + REQUIREMENT_DESC_SKILL, expectedMessage);

        // missing openings prefix
        assertParseFailure(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE
                + VALID_POSITION_OPENINGS_JR_SWE + REQUIREMENT_DESC_SKILL, expectedMessage);

        // all missing - no prefixes
        assertParseFailure(parser, VALID_POSITION_NAME_JR_SWE + VALID_DESCRIPTION_NAME_JR_SWE
                + VALID_POSITION_OPENINGS_JR_SWE + VALID_REQUIREMENT_SKILL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid position name
        assertParseFailure(parser, INVALID_POSITION_DESC + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_JR_SWE
                + REQUIREMENT_DESC_SKILL, PositionName.MESSAGE_CONSTRAINTS);

        // invalid description name
        assertParseFailure(parser, POSITION_DESC_JR_SWE + INVALID_DESCRIPTION_DESC + OPENING_DESC_JR_SWE
                + REQUIREMENT_DESC_SKILL, Description.MESSAGE_CONSTRAINTS);

        // invalid position openings
        assertParseFailure(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE + INVALID_OPENING_DESC
                + REQUIREMENT_DESC_SKILL, PositionOpenings.MESSAGE_CONSTRAINTS);

        // invalid requirement
        assertParseFailure(parser, POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE + OPENING_DESC_JR_SWE
                + INVALID_REQUIREMENT_DESC, Requirement.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_POSITION_DESC + INVALID_DESCRIPTION_DESC + OPENING_DESC_JR_SWE
                + INVALID_REQUIREMENT_DESC, PositionName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + POSITION_DESC_JR_SWE + DESCRIPTION_DESC_JR_SWE
                + OPENING_DESC_JR_SWE + REQUIREMENT_DESC_SKILL,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPositionCommand.MESSAGE_USAGE));
    }
}
