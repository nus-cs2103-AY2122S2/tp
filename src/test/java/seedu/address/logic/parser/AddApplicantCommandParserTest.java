package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.add.AddApplicantCommand;
import seedu.address.model.applicant.Address;
import seedu.address.model.applicant.Age;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Gender;
import seedu.address.model.applicant.Email;
import seedu.address.model.applicant.Name;
import seedu.address.model.applicant.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddApplicantCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Applicant expectedApplicant = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple names - last name accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple emails - last email accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                        + EMAIL_DESC_BOB + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple ages - last age accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_AMY + ADDRESS_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_AMY + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple genders - last gender accepted
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + AGE_DESC_BOB + GENDER_DESC_AMY + GENDER_DESC_BOB + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicant));

        // multiple tags - all accepted
        Applicant expectedApplicantMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                new AddApplicantCommand(expectedApplicantMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Applicant expectedApplicant = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, FLAG_APPLICANT + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + AGE_DESC_AMY + ADDRESS_DESC_AMY + GENDER_DESC_AMY,
                new AddApplicantCommand(expectedApplicant));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicantCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, FLAG_APPLICANT + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, FLAG_APPLICANT + NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing age prefix
        assertParseFailure(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + VALID_AGE_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + VALID_ADDRESS_BOB + GENDER_DESC_BOB, expectedMessage);

        // missing gender prefix
        assertParseFailure(parser, FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + AGE_DESC_BOB + ADDRESS_DESC_BOB + VALID_GENDER_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                FLAG_APPLICANT + VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_AGE_BOB
                        + VALID_ADDRESS_BOB + VALID_GENDER_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                FLAG_APPLICANT + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser,
                FLAG_APPLICANT + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser,
                FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser,
                FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + INVALID_ADDRESS_DESC + GENDER_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser,
                FLAG_APPLICANT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB + INVALID_TAG_DESC
                        + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                FLAG_APPLICANT + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + AGE_DESC_BOB + INVALID_ADDRESS_DESC + GENDER_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, FLAG_APPLICANT + PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + AGE_DESC_BOB + ADDRESS_DESC_BOB + GENDER_DESC_BOB
                        + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddApplicantCommand.MESSAGE_USAGE));
    }
}
