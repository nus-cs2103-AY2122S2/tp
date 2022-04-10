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
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENTID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTLSTUDENT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_YEAR2;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTLSTUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_YEAR2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.model.student.Email;
import seedu.address.model.student.GithubUsername;
import seedu.address.model.student.Name;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 z/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        // invalid github (consecutive hyphens)
        assertParseFailure(parser, "1" + INVALID_GITHUB_CONSECUTIVE_HYPHEN_DESC,
                GithubUsername.MESSAGE_CONSTRAINTS);
        // invalid github (starting with hyphens)
        assertParseFailure(parser, "1" + INVALID_GITHUB_STARTING_HYPHEN_DESC,
                GithubUsername.MESSAGE_CONSTRAINTS);
        // invalid github (ending with hyphens)
        assertParseFailure(parser, "1" + INVALID_GITHUB_ENDING_HYPHEN_DESC,
                GithubUsername.MESSAGE_CONSTRAINTS);
        // invalid github (consecutive hyphens)
        assertParseFailure(parser, "1" + INVALID_GITHUB_LONGER_THAN_39_DESC,
                GithubUsername.MESSAGE_CONSTRAINTS);
        // invalid telegram
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS);
        // invalid student id (does not start with A)
        assertParseFailure(parser, "1" + INVALID_STUDENTID_START_LETTER_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
        // invalid student id (wrong length)
        assertParseFailure(parser, "1" + INVALID_STUDENTID_WRONG_LENGTH_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
        // invalid student id (ends with number)
        assertParseFailure(parser, "1" + INVALID_STUDENTID_END_NUMBER_DESC,
                StudentId.MESSAGE_CONSTRAINTS);
        // invalid student id (contains middle letter)
        assertParseFailure(parser, "1" + INVALID_STUDENTID_MIDDLE_LETTER,
                StudentId.MESSAGE_CONSTRAINTS);

        // invalid email followed by valid github
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC + GITHUB_DESC_AMY, Email.MESSAGE_CONSTRAINTS);

        // valid email followed by invalid email. The test case for invalid email followed by valid email
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + EMAIL_DESC_BOB + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_YEAR2 + TAG_DESC_INTLSTUDENT + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_YEAR2 + TAG_EMPTY + TAG_DESC_INTLSTUDENT, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_YEAR2 + TAG_DESC_INTLSTUDENT, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_GITHUB_AMY
                + VALID_TELEGRAM_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_DESC_INTLSTUDENT
                + EMAIL_DESC_AMY + NAME_DESC_AMY + TAG_DESC_YEAR2
                + GITHUB_DESC_BOB + TELEGRAM_DESC_BOB + STUDENTID_DESC_BOB;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withEmail(VALID_EMAIL_AMY).withTags(VALID_TAG_INTLSTUDENT, VALID_TAG_YEAR2)
                .withGithub(VALID_GITHUB_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withStudentId(VALID_STUDENTID_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_YEAR2;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_YEAR2).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // github
        userInput = targetIndex.getOneBased() + GITHUB_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withGithub(VALID_GITHUB_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student id
        userInput = targetIndex.getOneBased() + STUDENTID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withStudentId(VALID_STUDENTID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_YEAR2 + TELEGRAM_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_YEAR2
                + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_INTLSTUDENT;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withGithub(VALID_GITHUB_BOB)
                .withTags(VALID_TAG_YEAR2, VALID_TAG_INTLSTUDENT).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_EMAIL_DESC + EMAIL_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_EMAIL_DESC + GITHUB_DESC_BOB
                + EMAIL_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_BOB).withEmail(VALID_EMAIL_BOB)
                .withGithub(VALID_GITHUB_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
