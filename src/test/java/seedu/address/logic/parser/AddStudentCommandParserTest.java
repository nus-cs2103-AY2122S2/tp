package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2103T_W15_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.StudentBuilder;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudent));

        // multiple telegram - last telegram accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_AMY + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudent));

        // multiple gitHub - last gitHub accepted
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_AMY
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudent));

        // multiple tutorial groups - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08,
                VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        assertParseSuccess(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08,
                new AddStudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // missing telegram
        Student expectedStudentWithoutTelegram = new StudentBuilder(AMY).withTelegram(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + GITHUB_DESC_AMY
                        + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudentWithoutTelegram));

        // missing github
        Student expectedStudentWithoutGithub = new StudentBuilder(AMY).withGitHub(null).build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY
                + TUTORIAL_GROUP_DESC_CS2101_G08, new AddStudentCommand(expectedStudentWithoutGithub));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                        + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2103T_W15_3, expectedMessage);

        // missing tutorial group prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                        + GITHUB_DESC_BOB + VALID_TUTORIAL_GROUP_CS2103T_W15_3, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + VALID_EMAIL_BOB
                        + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2103T_W15_3, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_TELEGRAM_BOB + VALID_EMAIL_BOB
                        + VALID_GITHUB_BOB + VALID_TUTORIAL_GROUP_CS2103T_W15_3, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08, Name.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_TELEGRAM_DESC + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08, Telegram.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + INVALID_EMAIL_DESC + GITHUB_DESC_BOB
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08, Email.MESSAGE_CONSTRAINTS);

        // invalid gitHub
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + INVALID_GITHUB_DESC
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08, GitHub.MESSAGE_CONSTRAINTS);

        // invalid tutorial group
        assertParseFailure(parser, NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + INVALID_TUTORIAL_GROUP_DESC + VALID_TUTORIAL_GROUP_CS2101_G08, TutorialGroup.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_GITHUB_DESC + TUTORIAL_GROUP_DESC_CS2101_G08,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB
                + GITHUB_DESC_BOB + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2101_G08,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
    }
}
