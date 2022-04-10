package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_CONSECUTIVE_HYPHEN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_ENDING_HYPHEN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_LONGER_THAN_39_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_STARTING_HYPHEN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_END_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_MIDDLE_LETTER;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_START_LETTER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENTID_WRONG_LENGTH_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTLSTUDENT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_YEAR2;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTLSTUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_YEAR2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedPerson = new StudentBuilder()
                .withName(VALID_NAME_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withGithub(VALID_GITHUB_BOB)
                .withTelegram(VALID_TELEGRAM_BOB)
                .withStudentId(VALID_STUDENTID_BOB)
                .withTags(VALID_TAG_YEAR2).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_YEAR2, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_YEAR2, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_YEAR2, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_YEAR2, VALID_TAG_INTLSTUDENT)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_INTLSTUDENT + GITHUB_DESC_BOB
                        + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_YEAR2,
                new AddCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + EMAIL_DESC_AMY + GITHUB_DESC_AMY + TELEGRAM_DESC_AMY
                + STUDENTID_DESC_AMY, new AddCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_EMAIL_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB, expectedMessage);

        // missing github prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + VALID_GITHUB_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB, expectedMessage);

        // missing telegram prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + VALID_TELEGRAM_BOB
                + STUDENTID_DESC_BOB, expectedMessage);

        // missing studentid prefix
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + VALID_STUDENTID_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_EMAIL_BOB + VALID_GITHUB_BOB + VALID_TELEGRAM_BOB
                + VALID_STUDENTID_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2, Name.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_EMAIL_DESC + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_YEAR2, Tag.MESSAGE_CONSTRAINTS);

        // invalid github (consecutive hyphens)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_GITHUB_CONSECUTIVE_HYPHEN_DESC
                        + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                GithubUsername.MESSAGE_CONSTRAINTS);

        // invalid github (starting with hyphen)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_GITHUB_STARTING_HYPHEN_DESC
                        + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                GithubUsername.MESSAGE_CONSTRAINTS);

        // invalid github (ending with hyphen)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_GITHUB_ENDING_HYPHEN_DESC
                        + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                GithubUsername.MESSAGE_CONSTRAINTS);

        // invalid github (longer than 39 alphanumerical characters)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + INVALID_GITHUB_LONGER_THAN_39_DESC
                        + STUDENTID_DESC_BOB + TELEGRAM_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                GithubUsername.MESSAGE_CONSTRAINTS);

        // invalid telegram
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + INVALID_TELEGRAM_DESC
                + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT, Telegram.MESSAGE_CONSTRAINTS);

        // invalid student id (does not start with A)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                        + INVALID_STUDENTID_START_LETTER_DESC + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid student id (wrong length)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                        + INVALID_STUDENTID_WRONG_LENGTH_DESC + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid student id (does not end with letter)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                        + INVALID_STUDENTID_END_NUMBER_DESC + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid student id (has a letter in the middle)
        assertParseFailure(parser, NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                        + INVALID_STUDENTID_MIDDLE_LETTER + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                StudentId.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB
                + STUDENTID_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + EMAIL_DESC_BOB + GITHUB_DESC_BOB
                        + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB + TAG_DESC_INTLSTUDENT + TAG_DESC_YEAR2,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
