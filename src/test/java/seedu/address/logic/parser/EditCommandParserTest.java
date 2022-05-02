package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.PersonUtil.ADDRESS_DESC_AMY;
import static seedu.address.testutil.PersonUtil.ADDRESS_DESC_BOB;
import static seedu.address.testutil.PersonUtil.EMAIL_DESC_AMY;
import static seedu.address.testutil.PersonUtil.EMAIL_DESC_BOB;
import static seedu.address.testutil.PersonUtil.INVALID_ADDRESS_DESC;
import static seedu.address.testutil.PersonUtil.INVALID_EMAIL_DESC;
import static seedu.address.testutil.PersonUtil.INVALID_NAME_DESC;
import static seedu.address.testutil.PersonUtil.INVALID_PHONE_DESC;
import static seedu.address.testutil.PersonUtil.INVALID_TAG_DESC;
import static seedu.address.testutil.PersonUtil.NAME_DESC_AMY;
import static seedu.address.testutil.PersonUtil.PHONE_DESC_AMY;
import static seedu.address.testutil.PersonUtil.PHONE_DESC_BOB;
import static seedu.address.testutil.PersonUtil.REMARK_DESC_AMY;
import static seedu.address.testutil.PersonUtil.TAG_DESC_COWORKER;
import static seedu.address.testutil.PersonUtil.TAG_DESC_FRIEND;
import static seedu.address.testutil.PersonUtil.VALID_ADDRESS_AMY;
import static seedu.address.testutil.PersonUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.PersonUtil.VALID_EMAIL_AMY;
import static seedu.address.testutil.PersonUtil.VALID_EMAIL_BOB;
import static seedu.address.testutil.PersonUtil.VALID_NAME_AMY;
import static seedu.address.testutil.PersonUtil.VALID_PHONE_AMY;
import static seedu.address.testutil.PersonUtil.VALID_PHONE_BOB;
import static seedu.address.testutil.PersonUtil.VALID_REMARK_AMY;
import static seedu.address.testutil.PersonUtil.VALID_TAG_COWORKER;
import static seedu.address.testutil.PersonUtil.VALID_TAG_FRIEND;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Field;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.tag.Tag;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + Tag.PREFIX;

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
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code Tag.PREFIX} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_COWORKER + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_COWORKER, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_COWORKER, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput =
            targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_COWORKER + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + REMARK_DESC_AMY + TAG_DESC_FRIEND;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Name(VALID_NAME_AMY));
        fields.add(new Phone(VALID_PHONE_BOB));
        fields.add(new Email(VALID_EMAIL_AMY));
        fields.add(new Address(VALID_ADDRESS_AMY));
        fields.add(new Remark(VALID_REMARK_AMY));
        Set<Tag> tags = Stream.of(VALID_TAG_FRIEND, VALID_TAG_COWORKER).map(Tag::new).collect(Collectors.toSet());
        EditCommand expectedCommand = new EditCommand(targetIndex, fields, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Email(VALID_EMAIL_AMY));
        fields.add(new Phone(VALID_PHONE_BOB));
        EditCommand expectedCommand = new EditCommand(targetIndex, fields, null);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;

        // name
        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Name(VALID_NAME_AMY));
        EditCommand expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        fields = new ArrayList<>();
        fields.add(new Phone(VALID_PHONE_AMY));
        expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        fields = new ArrayList<>();
        fields.add(new Email(VALID_EMAIL_AMY));
        expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        fields = new ArrayList<>();
        fields.add(new Address(VALID_ADDRESS_AMY));
        expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        //remark
        userInput = targetIndex.getOneBased() + REMARK_DESC_AMY;
        fields = new ArrayList<>();
        fields.add(new Remark(VALID_REMARK_AMY));
        expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        fields = new ArrayList<>();
        expectedCommand = new EditCommand(targetIndex, fields, Tag.createSet(VALID_TAG_FRIEND));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput =
            targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_COWORKER;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Phone(VALID_PHONE_BOB));
        fields.add(new Email(VALID_EMAIL_BOB));
        fields.add(new Address(VALID_ADDRESS_BOB));
        Set<Tag> tags = Tag.createSet(VALID_TAG_FRIEND, VALID_TAG_COWORKER);
        EditCommand expectedCommand = new EditCommand(targetIndex, fields, tags);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;

        ArrayList<Field> fields = new ArrayList<>();
        fields.add(new Phone(VALID_PHONE_BOB));

        EditCommand expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB + PHONE_DESC_BOB;
        fields.add(new Email(VALID_EMAIL_BOB));
        fields.add(new Address(VALID_ADDRESS_BOB));
        expectedCommand = new EditCommand(targetIndex, fields, null);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand expectedCommand = new EditCommand(targetIndex, new ArrayList<>(), new HashSet<>());

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
