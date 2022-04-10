package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_OPTION_R;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EDIT_OPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TEAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_C_N_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_GOOGLE_N_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Team;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TEAM_EMPTY = " " + PREFIX_TEAM;

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private final EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified for single edit
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        //no field specified for multiple edits
        assertParseFailure(parser, "1 2 3", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index. '-', the dashed used for minus is a prefix for options for edit command.
        // so below is equivalent to no preamble
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // second index being negative, equivalent to using an undefined option
        assertParseFailure(parser, "3 -5", MESSAGE_INVALID_FORMAT);

        // both indices being negative, equivalent to no preamble and using two undefined options
        assertParseFailure(parser, "-1 -2", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_singleEditInvalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_USERNAME_DESC,
            GithubUsername.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TEAM_DESC, Team.MESSAGE_CONSTRAINTS); // invalid team

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(
            parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_USERNAME_AMY + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleEditsInvalidValue_failure() {
        assertParseFailure(parser, "1 2" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1 2" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1 2" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1 2" + INVALID_USERNAME_DESC,
            GithubUsername.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1 2" + INVALID_TEAM_DESC, Team.MESSAGE_CONSTRAINTS); // invalid team

        // invalid phone followed by valid email
        assertParseFailure(parser, "1 2" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1 2" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(
            parser, "1 2" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_USERNAME_AMY + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
            + EMAIL_DESC_AMY + USERNAME_DESC_AMY + NAME_DESC_AMY + TEAM_DESC_YAHOO;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withGithubUsername(VALID_USERNAME_AMY)
            .withTeams(VALID_TEAM_YAHOO).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMode_success() {
        //single edit for team
        EditPersonDescriptor changeTeamDescriptor =
            new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_YAHOO, VALID_TEAM_GOOGLE).build();
        EditCommand expectedResetTeamChangeCommand = new EditCommand(INDEX_FIRST_PERSON, changeTeamDescriptor, true);
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + EDIT_OPTION_R + TEAM_DESC_GOOGLE_N_YAHOO,
            expectedResetTeamChangeCommand);

        //single edit for skills
        EditPersonDescriptor changeSkillDescriptor =
            new EditPersonDescriptorBuilder().withSkillSet(VALID_SKILL_C, VALID_SKILL_PYTHON).build();
        EditCommand expectedResetSkillChangeCommand = new EditCommand(INDEX_FIRST_PERSON, changeSkillDescriptor, true);
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + EDIT_OPTION_R + SKILL_DESC_C_N_PYTHON,
            expectedResetSkillChangeCommand);

        //multiple edits for team
        List<Index> targetIndices = new ArrayList<>();
        targetIndices.add(INDEX_FIRST_PERSON);
        targetIndices.add(INDEX_SECOND_PERSON);
        EditCommand expectedMultipleResetTeamChangeCommand =
            new EditCommand(targetIndices, changeTeamDescriptor, true);
        assertParseSuccess(parser,
            INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_SECOND_PERSON.getOneBased() + EDIT_OPTION_R
                + TEAM_DESC_GOOGLE_N_YAHOO, expectedMultipleResetTeamChangeCommand);

        //multiple edits for teams
        EditCommand expectedMultipleResetSkillChangeCommand =
            new EditCommand(targetIndices, changeSkillDescriptor, true);
        assertParseSuccess(parser,
            INDEX_FIRST_PERSON.getOneBased() + " " + INDEX_SECOND_PERSON.getOneBased() + EDIT_OPTION_R
                + SKILL_DESC_C_N_PYTHON, expectedMultipleResetSkillChangeCommand);
        //parse default mode is not tested separately as all success cases that are not reset mode are default mode
        // and they are tested in methods such as {@code parse_allFieldsSpecified_success}
    }

    @Test
    public void parse_invalidOption_failure() {
        // unrecognized option failure
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + INVALID_EDIT_OPTION_DESC + NAME_DESC_AMY,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_OPTIONS));
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, false);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + USERNAME_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGithubUsername(VALID_USERNAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // teams
        userInput = targetIndex.getOneBased() + TEAM_DESC_YAHOO;
        descriptor = new EditPersonDescriptorBuilder().withTeams(VALID_TEAM_YAHOO).build();
        expectedCommand = new EditCommand(targetIndex, descriptor, false);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + USERNAME_DESC_AMY + EMAIL_DESC_AMY
            + PHONE_DESC_AMY + USERNAME_DESC_AMY + EMAIL_DESC_AMY
            + PHONE_DESC_BOB + USERNAME_DESC_BOB + EMAIL_DESC_BOB + TEAM_DESC_GOOGLE;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withGithubUsername(VALID_USERNAME_BOB)
            .withTeams(VALID_TEAM_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, true);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + USERNAME_DESC_BOB
            + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
            .withGithubUsername(VALID_USERNAME_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTeams_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TEAM_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTeams().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor, true);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
