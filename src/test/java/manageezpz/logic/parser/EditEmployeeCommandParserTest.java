package manageezpz.logic.parser;

import static manageezpz.commons.core.Messages.MESSAGE_FIELD_NOT_EDITED;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT_BIND;
import static manageezpz.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static manageezpz.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static manageezpz.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static manageezpz.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static manageezpz.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static manageezpz.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static manageezpz.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static manageezpz.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static manageezpz.logic.commands.EditEmployeeCommand.MESSAGE_USAGE;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseFailure;
import static manageezpz.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static manageezpz.testutil.TypicalIndexes.INDEX_FIRST;
import static manageezpz.testutil.TypicalIndexes.INDEX_SECOND;
import static manageezpz.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.EditEmployeeCommand;
import manageezpz.logic.commands.EditEmployeeCommand.EditEmployeeDescriptor;
import manageezpz.model.person.Email;
import manageezpz.model.person.Name;
import manageezpz.model.person.Phone;
import manageezpz.testutil.EditEmployeeDescriptorBuilder;

public class EditEmployeeCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT_BIND, MESSAGE_USAGE);

    private EditEmployeeCommandParser parser = new EditEmployeeCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_FIELD_NOT_EDITED + MESSAGE_USAGE);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY,
                ParserUtil.MESSAGE_INVALID_INDEX + "\n\n" + MESSAGE_USAGE);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY,
                ParserUtil.MESSAGE_INVALID_INDEX + "\n\n" + MESSAGE_USAGE);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC,
                Email.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE); // invalid email

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY,
                Phone.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC,
                Phone.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS + "\n\n" + MESSAGE_USAGE);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + NAME_DESC_AMY;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditEmployeeCommand expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditEmployeeCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).build();
        EditEmployeeCommand expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditEmployeeCommand expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditEmployeeDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB;

        EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        EditEmployeeCommand expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditEmployeeCommand.EditEmployeeDescriptor descriptor = new EditEmployeeDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB).build();
        EditEmployeeCommand expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditEmployeeDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditEmployeeCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
