package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_SHOPSG;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERVIEW;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEntries.BIG_BANK;
import static seedu.address.testutil.TypicalEntries.JANICE_STREET;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCompanyCommand;
import seedu.address.model.entry.Company;
import seedu.address.model.entry.Email;
import seedu.address.model.entry.Name;
import seedu.address.model.entry.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CompanyBuilder;

public class AddCompanyCommandParserTest {
    private AddCompanyCommandParser parser = new AddCompanyCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Company expectedCompany = new CompanyBuilder(JANICE_STREET).withTags(VALID_TAG_APPLIED).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B + TAG_DESC_APPLIED, new AddCompanyCommand(expectedCompany));


        // multiple names - last name accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_SHOPSG
                + NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B + TAG_DESC_APPLIED, new AddCompanyCommand(expectedCompany));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANICE_STREET
                + PHONE_DESC_A + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B + TAG_DESC_APPLIED, new AddCompanyCommand(expectedCompany));

        // multiple emails - last email accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_A + EMAIL_DESC_B
                + ADDRESS_DESC_B + TAG_DESC_APPLIED, new AddCompanyCommand(expectedCompany));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_B
                + ADDRESS_DESC_A + ADDRESS_DESC_B + TAG_DESC_APPLIED, new AddCompanyCommand(expectedCompany));

        // multiple tags - all accepted
        Company expectedCompanyMultipleTags = new CompanyBuilder(JANICE_STREET)
                .withTags(VALID_TAG_APPLIED, VALID_TAG_INTERVIEW)
                .build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B + TAG_DESC_APPLIED
                + TAG_DESC_INTERVIEW, new AddCompanyCommand(expectedCompanyMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Company expectedCompany = new CompanyBuilder(BIG_BANK).withTags().build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BIG_BANK + PHONE_DESC_A
                + EMAIL_DESC_A + ADDRESS_DESC_A, new AddCompanyCommand(expectedCompany));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_COMPANY_JANICE_STREET + PHONE_DESC_B + EMAIL_DESC_B
                        + COMPANY_DESC_JANICE_STREET,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + VALID_PHONE_B + EMAIL_DESC_B
                        + COMPANY_DESC_JANICE_STREET,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + PHONE_DESC_B + VALID_EMAIL_B
                        + COMPANY_DESC_JANICE_STREET,
                expectedMessage);

        // missing company name prefix
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + PHONE_DESC_B + EMAIL_DESC_B
                        + VALID_COMPANY_JANICE_STREET,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_B + VALID_EMAIL_B
                        + VALID_COMPANY_JANICE_STREET,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B
                + TAG_DESC_APPLIED, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + INVALID_PHONE_DESC
                + EMAIL_DESC_B + ADDRESS_DESC_B
                + TAG_DESC_APPLIED, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + INVALID_EMAIL_DESC + ADDRESS_DESC_B
                + TAG_DESC_APPLIED, Email.MESSAGE_CONSTRAINTS);

        // invalid address is not possible

        // invalid tag
        assertParseFailure(parser, NAME_DESC_JANICE_STREET + PHONE_DESC_B
                + EMAIL_DESC_B + ADDRESS_DESC_B
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_B
                        + INVALID_EMAIL_DESC + ADDRESS_DESC_B,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_JANICE_STREET
                        + PHONE_DESC_B + EMAIL_DESC_B
                        + ADDRESS_DESC_B + TAG_DESC_APPLIED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
    }
}
