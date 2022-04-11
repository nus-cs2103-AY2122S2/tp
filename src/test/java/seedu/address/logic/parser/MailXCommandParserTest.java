package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MOD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_STUDENT_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MOD_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MOD_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.STUDENT_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_NUMBER_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MailXCommand;
import seedu.address.model.person.Email;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentNumber;
import seedu.address.testutil.MailXDescriptorBuilder;


public class MailXCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MailXCommand.MESSAGE_USAGE);

    private MailXCommandParser parser = new MailXCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // unknown argument specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no argument specified
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
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, INVALID_STUDENT_NUMBER_DESC, StudentNumber.MESSAGE_CONSTRAINTS); // invalid
        // student number
        assertParseFailure(parser, INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, INVALID_MOD_DESC, Mod.MESSAGE_CONSTRAINTS); // invalid mod

        // invalid student number followed by valid email
        assertParseFailure(parser, INVALID_STUDENT_NUMBER_DESC + EMAIL_DESC_AMY,
                StudentNumber.MESSAGE_CONSTRAINTS);

        // valid student number followed by invalid student number. The test case for invalid student number
        // followed by valid student number is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, STUDENT_NUMBER_DESC_BOB + INVALID_STUDENT_NUMBER_DESC,
                StudentNumber.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, INVALID_NAME_DESC + INVALID_EMAIL_DESC
                        + VALID_MOD_AMY + VALID_STUDENT_NUMBER_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = STUDENT_NUMBER_DESC_BOB
                + EMAIL_DESC_AMY + MOD_DESC_AMY + NAME_DESC_AMY;

        MailXCommand.MailXDescriptor descriptor = new MailXDescriptorBuilder().withName(VALID_NAME_AMY)
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).withEmail(VALID_EMAIL_AMY).withMod(VALID_MOD_AMY)
                .build();
        MailXCommand expectedCommand = new MailXCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        String userInput = STUDENT_NUMBER_DESC_BOB + EMAIL_DESC_AMY;

        MailXCommand.MailXDescriptor descriptor = new MailXDescriptorBuilder()
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        MailXCommand expectedCommand = new MailXCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        String userInput = NAME_DESC_AMY;
        MailXCommand.MailXDescriptor descriptor = new MailXDescriptorBuilder().withName(VALID_NAME_AMY).build();
        MailXCommand expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // student number
        userInput = STUDENT_NUMBER_DESC_AMY;
        descriptor = new MailXDescriptorBuilder().withStudentNumber(VALID_STUDENT_NUMBER_AMY).build();
        expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = EMAIL_DESC_AMY;
        descriptor = new MailXDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // mod
        userInput = MOD_DESC_AMY;
        descriptor = new MailXDescriptorBuilder().withMod(VALID_MOD_AMY).build();
        expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput = STUDENT_NUMBER_DESC_AMY + MOD_DESC_AMY + EMAIL_DESC_AMY
                + STUDENT_NUMBER_DESC_AMY + MOD_DESC_AMY + EMAIL_DESC_AMY
                + STUDENT_NUMBER_DESC_BOB + MOD_DESC_BOB + EMAIL_DESC_BOB;

        MailXCommand.MailXDescriptor descriptor = new MailXDescriptorBuilder()
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB)
                .withEmail(VALID_EMAIL_BOB).withMod(VALID_MOD_BOB)
                .build();
        MailXCommand expectedCommand = new MailXCommand(descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        String userInput = INVALID_STUDENT_NUMBER_DESC + STUDENT_NUMBER_DESC_BOB;
        MailXCommand.MailXDescriptor descriptor = new MailXDescriptorBuilder()
                .withStudentNumber(VALID_STUDENT_NUMBER_BOB).build();
        MailXCommand expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = EMAIL_DESC_BOB + INVALID_STUDENT_NUMBER_DESC + MOD_DESC_BOB
                + STUDENT_NUMBER_DESC_BOB;
        descriptor =
                new MailXDescriptorBuilder().withStudentNumber(VALID_STUDENT_NUMBER_BOB).withEmail(VALID_EMAIL_BOB)
                        .withMod(VALID_MOD_BOB).build();
        expectedCommand = new MailXCommand(descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

