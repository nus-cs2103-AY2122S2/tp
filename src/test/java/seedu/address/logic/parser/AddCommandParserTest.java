package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BLOCK_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOCK_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.COVID_STATUS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.COVID_STATUS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.FACULTY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FACULTY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOCK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COVID_STATUS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FACULTY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MATRICULATION_NUMBER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MATRICULATION_NUMBER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MATRICULATION_NUMBER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOCK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COVID_STATUS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRICULATION_NUMBER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Block;
import seedu.address.model.person.CovidStatus;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple blocks - last block accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_AMY + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple faculties - last faculty accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_AMY
                + FACULTY_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + FACULTY_DESC_BOB + BLOCK_DESC_BOB + PHONE_DESC_AMY
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple matriculation numbers - last number accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_AMY
                + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple statuses - last status accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                + COVID_STATUS_DESC_AMY + COVID_STATUS_DESC_BOB + TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + BLOCK_DESC_AMY + FACULTY_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + MATRICULATION_NUMBER_DESC_AMY + COVID_STATUS_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing block prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_BLOCK_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing faculty prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + VALID_FACULTY_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing matriculation number prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_MATRICULATION_NUMBER_BOB + COVID_STATUS_DESC_BOB,
                expectedMessage);

        // missing covid status prefix
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + VALID_COVID_STATUS_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_BLOCK_BOB + VALID_FACULTY_BOB + VALID_PHONE_BOB
                        + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + VALID_MATRICULATION_NUMBER_BOB + VALID_COVID_STATUS_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS_FORMAT);

        // invalid block
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_BLOCK_DESC + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Block.MESSAGE_CONSTRAINTS);

        // invalid faculty
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + INVALID_FACULTY_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Faculty.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid matriculation number
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_MATRICULATION_NUMBER_DESC + COVID_STATUS_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, MatriculationNumber.MESSAGE_CONSTRAINTS);

        // invalid covid status
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + INVALID_COVID_STATUS_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, CovidStatus.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + BLOCK_DESC_BOB + FACULTY_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + MATRICULATION_NUMBER_DESC_BOB + COVID_STATUS_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS_FORMAT);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + BLOCK_DESC_BOB + FACULTY_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + MATRICULATION_NUMBER_DESC_BOB
                        + COVID_STATUS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
