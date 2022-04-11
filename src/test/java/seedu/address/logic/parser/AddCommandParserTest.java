package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SKILL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TEAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_C;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_C_N_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_C_N_PYTHON_WITH_SPACES;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.SKILL_DESC_UNIT_TESTING;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_GOOGLE_N_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_GOOGLE_N_YAHOO_SEPARATOR_WITH_SPACES;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_HACKING_TEAM;
import static seedu.address.logic.commands.CommandTestUtil.TEAM_DESC_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_PYTHON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_UNIT_TESTING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_HACKING_TEAM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_YAHOO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Skill;
import seedu.address.model.team.Team;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    private String separator = ",";

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTeams(VALID_TEAM_YAHOO).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + USERNAME_DESC_BOB + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + USERNAME_DESC_BOB + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + USERNAME_DESC_BOB + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
            + USERNAME_DESC_BOB + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple addresses - last github username accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_AMY
            + USERNAME_DESC_BOB + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple teams - last team accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE + TEAM_DESC_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // multiple skills - last skill accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_YAHOO + SKILL_DESC_C + SKILL_DESC_PYTHON, new AddCommand(expectedPerson));

        // space in team names
        Person expectedPersonWithTeamWithSpace =
            new PersonBuilder(expectedPerson).withTeams(VALID_TEAM_HACKING_TEAM).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_HACKING_TEAM + SKILL_DESC_PYTHON, new AddCommand(expectedPersonWithTeamWithSpace));

        // space in skill names
        Person expectedPersonWithSkillWithSpace =
            new PersonBuilder(expectedPerson).withSkillSet(VALID_SKILL_UNIT_TESTING).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_YAHOO + SKILL_DESC_UNIT_TESTING, new AddCommand(expectedPersonWithSkillWithSpace));

        //multiple teams separated by comma
        Person expectedPersonWithManyTeams = new PersonBuilder(BOB).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE_N_YAHOO + SKILL_DESC_PYTHON, new AddCommand(expectedPersonWithManyTeams));

        //multiple skills separated by comma
        Person expectedPersonWithManySkills =
            new PersonBuilder(expectedPerson).withSkillSet(VALID_SKILL_C, VALID_SKILL_PYTHON).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_YAHOO + SKILL_DESC_C_N_PYTHON, new AddCommand(expectedPersonWithManySkills));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero teams
        Person expectedPersonWithNoTeams = new PersonBuilder(AMY).withTeams().build();

        //when the team prefix is not present in command
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + USERNAME_DESC_AMY + SKILL_DESC_C,
            new AddCommand(expectedPersonWithNoTeams));

        //when the team prefix is present but the values are empty
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + USERNAME_DESC_AMY + TEAM_DESC_EMPTY + SKILL_DESC_C,
            new AddCommand(expectedPersonWithNoTeams));

        // zero skills
        Person expectedPersonWithNoSkills = new PersonBuilder(AMY).withSkillSet().build();

        // when the skill prefix is not present in command
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + USERNAME_DESC_AMY + TEAM_DESC_YAHOO,
            new AddCommand(expectedPersonWithNoSkills));

        //when the skill prefix is present but the values are empty
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + USERNAME_DESC_AMY + TEAM_DESC_YAHOO + SKILL_DESC_EMPTY,
            new AddCommand(expectedPersonWithNoSkills));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB,
            expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + USERNAME_DESC_BOB,
            expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_USERNAME_BOB,
            expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_USERNAME_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE_N_YAHOO, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE_N_YAHOO, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE_N_YAHOO, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_USERNAME_DESC
            + TEAM_DESC_GOOGLE_N_YAHOO, GithubUsername.MESSAGE_CONSTRAINTS);

        // invalid team
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + INVALID_TEAM_DESC, Team.MESSAGE_CONSTRAINTS);

        // invalid skill
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + USERNAME_DESC_BOB
            + TEAM_DESC_GOOGLE_N_YAHOO + INVALID_SKILL_DESC, Skill.NAME_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_USERNAME_DESC,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TEAM_DESC_GOOGLE + TEAM_DESC_YAHOO,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_separatorWithWhitespaces_success() {
        Person expectedPersonWithTeams = new PersonBuilder(BOB).build();

        //valid separator with spaces for teams
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TEAM_DESC_GOOGLE_N_YAHOO_SEPARATOR_WITH_SPACES + SKILL_DESC_PYTHON,
            new AddCommand(expectedPersonWithTeams));

        //valid separator with spaces for skills
        Person expectedPersonWithSkills =
            new PersonBuilder(BOB).withSkillSet(VALID_SKILL_C, VALID_SKILL_PYTHON).build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + USERNAME_DESC_BOB + TEAM_DESC_GOOGLE_N_YAHOO + SKILL_DESC_C_N_PYTHON_WITH_SPACES,
            new AddCommand(expectedPersonWithSkills));
    }

}
