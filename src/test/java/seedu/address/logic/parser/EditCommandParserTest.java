package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMPTY_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GITHUB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMPTY_TUTORIAL_GROUP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GITHUB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TUTORIAL_GROUP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.TUTORIAL_GROUP_DESC_CS2103T_W15_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2101_G08;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_GROUP_CS2103T_W15_3;
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
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "",
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY,
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string",
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string",
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));

        //index larger than 2147483647
        assertParseFailure(parser, "2147483648",
            String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, Index.MESSAGE_CONSTRAINT));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
            + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
            + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); // invalid telegram
        assertParseFailure(parser, "1"
            + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1"
            + INVALID_GITHUB_DESC, GitHub.MESSAGE_CONSTRAINTS); // invalid gitHub
        assertParseFailure(parser, "1"
            + INVALID_TUTORIAL_GROUP_DESC, TutorialGroup.MESSAGE_CONSTRAINTS); // invalid tutorial group
        assertParseFailure(parser, "1" + INVALID_EMPTY_TUTORIAL_GROUP,
            TutorialGroup.MESSAGE_CONSTRAINTS); //empty tutorial group

        // invalid telegram followed by valid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC + EMAIL_DESC_AMY, Telegram.MESSAGE_CONSTRAINTS);

        // valid telegram followed by invalid telegram. The test case for invalid telegram followed by valid telegram
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TELEGRAM_DESC_BOB + INVALID_TELEGRAM_DESC,
            Telegram.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC
                + VALID_GITHUB_AMY + VALID_TELEGRAM_AMY, Name.MESSAGE_CONSTRAINTS);

        //valid tutorial group followed by empty tutorial group
        assertParseFailure(parser, "1" + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + INVALID_EMPTY_TUTORIAL_GROUP,
            TutorialGroup.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + TUTORIAL_GROUP_DESC_CS2103T_W15_3
                + EMAIL_DESC_AMY + GITHUB_DESC_AMY + NAME_DESC_AMY + TUTORIAL_GROUP_DESC_CS2101_G08;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_AMY).withGitHub(VALID_GITHUB_AMY)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2103T_W15_3, VALID_TUTORIAL_GROUP_CS2101_G08).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_BOB + EMAIL_DESC_AMY;

        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_AMY).build();
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

        // telegram
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gitHub
        userInput = targetIndex.getOneBased() + GITHUB_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withGitHub(VALID_GITHUB_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tutorial groups
        userInput = targetIndex.getOneBased() + TUTORIAL_GROUP_DESC_CS2101_G08;
        descriptor = new EditStudentDescriptorBuilder().withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_emptyFields_success() {
        //Only for gitHub and telegram

        //gitHub
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + EMPTY_GITHUB_DESC;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withGitHub(null).build(); //empty gitHub instantiated using null during the parsing
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        //telegram
        userInput = targetIndex.getOneBased() + EMPTY_TELEGRAM_DESC;
        descriptor = new EditStudentDescriptorBuilder()
            .withTelegram(null).build(); //empty telegram instantiated using null during the parsing
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY
                + TUTORIAL_GROUP_DESC_CS2101_G08 + TELEGRAM_DESC_AMY + GITHUB_DESC_AMY + EMAIL_DESC_AMY
                + TUTORIAL_GROUP_DESC_CS2101_G08 + TELEGRAM_DESC_BOB + GITHUB_DESC_BOB + EMAIL_DESC_BOB
                + TUTORIAL_GROUP_DESC_CS2103T_W15_3;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB)
                .withEmail(VALID_EMAIL_BOB).withGitHub(VALID_GITHUB_BOB)
                .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08, VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        //repeatedTutorialGroups
        userInput = targetIndex.getOneBased() + TUTORIAL_GROUP_DESC_CS2103T_W15_3 + TUTORIAL_GROUP_DESC_CS2103T_W15_3
            + TUTORIAL_GROUP_DESC_CS2101_G08 + TUTORIAL_GROUP_DESC_CS2101_G08 + TUTORIAL_GROUP_DESC_CS2101_G08;
        descriptor = new EditStudentDescriptorBuilder()
            .withTutorialGroup(VALID_TUTORIAL_GROUP_CS2101_G08, VALID_TUTORIAL_GROUP_CS2103T_W15_3).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_TELEGRAM_DESC + TELEGRAM_DESC_BOB;
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder()
            .withTelegram(VALID_TELEGRAM_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_TELEGRAM_DESC + GITHUB_DESC_BOB
                + TELEGRAM_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_BOB).withEmail(VALID_EMAIL_BOB)
                .withGitHub(VALID_GITHUB_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
