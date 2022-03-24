package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.ALLERGY_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.ALLERGY_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.BIRTHDATE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.BIRTHDATE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.HAIR_TYPE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.HAIR_TYPE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_ALLERGY_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_HAIR_TYPE_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_SERVICE_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_SKIN_TYPE_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.INVALID_STAFFS_DESC;
import static seedu.trackbeau.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.trackbeau.logic.commands.CommandTestUtil.REG_DATE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.REG_DATE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SERVICE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SKIN_TYPE_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.SKIN_TYPE_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.STAFF_DESC_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.STAFF_DESC_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_BOB;
import static seedu.trackbeau.logic.parser.AddCommandParser.EMPTY_HAIR_TYPE;
import static seedu.trackbeau.logic.parser.AddCommandParser.EMPTY_SKIN_TYPE;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.trackbeau.testutil.TypicalCustomers.AMY;
import static seedu.trackbeau.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.AddCommand;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;
import seedu.trackbeau.testutil.CustomerBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Customer expectedCustomer = new CustomerBuilder(BOB).withStaffs(VALID_STAFF_BOB)
                .withServices(VALID_SERVICE_BOB).withAllergies(VALID_ALLERGY_BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB
                + STAFF_DESC_BOB
                + SERVICE_DESC_BOB + ALLERGY_DESC_BOB
                + REG_DATE_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB
                + REG_DATE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB
                + HAIR_TYPE_DESC_BOB + REG_DATE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB
                + ALLERGY_DESC_BOB + BIRTHDATE_DESC_BOB , new AddCommand(expectedCustomer));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB
                + HAIR_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB
                + HAIR_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple skin types - last skin type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_AMY + SKIN_TYPE_DESC_BOB
                + HAIR_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple hair types - last hair type accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_AMY
                + HAIR_TYPE_DESC_BOB + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomer));

        // multiple staffs tags - all accepted
        Customer expectedCustomerMultipleStaffs = new CustomerBuilder(BOB)
                .withStaffs(VALID_STAFF_AMY, VALID_STAFF_BOB)
                .withServices(VALID_SERVICE_BOB).withAllergies(VALID_ALLERGY_BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_AMY + STAFF_DESC_BOB + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + SERVICE_DESC_BOB + ALLERGY_DESC_BOB, new AddCommand(expectedCustomerMultipleStaffs));

        // multiple services tags - all accepted
        Customer expectedCustomerMultipleServices = new CustomerBuilder(BOB)
                .withStaffs(VALID_STAFF_BOB)
                .withServices(VALID_SERVICE_AMY,
                        VALID_SERVICE_BOB)
                .withAllergies(VALID_ALLERGY_BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB + STAFF_DESC_BOB
                + SERVICE_DESC_AMY + SERVICE_DESC_BOB
                + BIRTHDATE_DESC_BOB + REG_DATE_DESC_BOB
                + ALLERGY_DESC_BOB, new AddCommand(expectedCustomerMultipleServices));


        // multiple allergy tags - all accepted
        Customer expectedCustomerMultipleAllergies = new CustomerBuilder(BOB)
                .withStaffs(VALID_STAFF_BOB)
                .withServices(VALID_SERVICE_BOB)
                .withAllergies(VALID_ALLERGY_AMY, VALID_ALLERGY_BOB)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + BIRTHDATE_DESC_BOB
                + ALLERGY_DESC_AMY + REG_DATE_DESC_BOB
                + ALLERGY_DESC_BOB, new AddCommand(expectedCustomerMultipleAllergies));
    }

    @Test
    public void parse_optionalSkinTypeMissing_success() {
        // no skin type
        Customer expectedCustomer = new CustomerBuilder(AMY).withSkinType(EMPTY_SKIN_TYPE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + HAIR_TYPE_DESC_AMY + STAFF_DESC_AMY
                        + SERVICE_DESC_AMY + ALLERGY_DESC_AMY + BIRTHDATE_DESC_AMY
                        + REG_DATE_DESC_AMY,
                new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_optionalHairTypeMissing_success() {
        // no hair type
        Customer expectedCustomer = new CustomerBuilder(AMY).withHairType(EMPTY_HAIR_TYPE).build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + SKIN_TYPE_DESC_AMY + BIRTHDATE_DESC_AMY + STAFF_DESC_AMY
                        + SERVICE_DESC_AMY + ALLERGY_DESC_AMY + REG_DATE_DESC_AMY,
                new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_optionalStaffsMissing_success() {
        // zero staffs
        Customer expectedCustomer = new CustomerBuilder(AMY).withStaffs().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + SKIN_TYPE_DESC_AMY + HAIR_TYPE_DESC_AMY + BIRTHDATE_DESC_AMY
                        + SERVICE_DESC_AMY + ALLERGY_DESC_AMY + REG_DATE_DESC_AMY,
                new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_optionalServicesMissing_success() {
        // zero services
        Customer expectedCustomer = new CustomerBuilder(AMY).withServices().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + SKIN_TYPE_DESC_AMY + HAIR_TYPE_DESC_AMY + BIRTHDATE_DESC_AMY
                        + STAFF_DESC_AMY + ALLERGY_DESC_AMY + REG_DATE_DESC_AMY,
                new AddCommand(expectedCustomer));
    }

    @Test
    public void parse_optionalAllergiesMissing_success() {
        // zero allergies
        Customer expectedCustomer = new CustomerBuilder(AMY).withAllergies().build();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY
                        + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + SKIN_TYPE_DESC_AMY + HAIR_TYPE_DESC_AMY + BIRTHDATE_DESC_AMY
                        + STAFF_DESC_AMY + SERVICE_DESC_AMY + REG_DATE_DESC_AMY,
                new AddCommand(expectedCustomer));
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + REG_DATE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + REG_DATE_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                        + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + REG_DATE_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + REG_DATE_DESC_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB
                        + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + REG_DATE_DESC_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + REG_DATE_DESC_BOB
                + ALLERGY_DESC_BOB, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPhone_failure() {
        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + REG_DATE_DESC_BOB
                + BIRTHDATE_DESC_BOB
                + ALLERGY_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidEmail_failure() {
        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + REG_DATE_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB
                + ALLERGY_DESC_BOB, Email.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidAddress_failure() {
        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + REG_DATE_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB
                + ALLERGY_DESC_BOB, Address.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_twoInvalidValues_failure() {
        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                        + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                        + STAFF_DESC_BOB + SERVICE_DESC_BOB
                        + ALLERGY_DESC_BOB + REG_DATE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                        + STAFF_DESC_BOB + SERVICE_DESC_BOB
                        + ALLERGY_DESC_BOB + REG_DATE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidSkinType_failure() {
        // invalid skin type
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_SKIN_TYPE_DESC + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + REG_DATE_DESC_BOB
                + ALLERGY_DESC_BOB, SkinType.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidHairType_failure() {
        // invalid hair type
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + INVALID_HAIR_TYPE_DESC
                + STAFF_DESC_BOB + SERVICE_DESC_BOB + REG_DATE_DESC_BOB
                + ALLERGY_DESC_BOB, HairType.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidStaff_failure() {
        // invalid staff
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                        + INVALID_STAFFS_DESC + SERVICE_DESC_BOB
                        + REG_DATE_DESC_BOB + ALLERGY_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidService_failure() {
        // invalid service
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + INVALID_SERVICE_DESC
                + REG_DATE_DESC_BOB + ALLERGY_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidAllergy_failure() {
        // invalid allergy
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + SKIN_TYPE_DESC_BOB + HAIR_TYPE_DESC_BOB
                + STAFF_DESC_BOB + REG_DATE_DESC_BOB + SERVICE_DESC_BOB
                + INVALID_ALLERGY_DESC, Tag.MESSAGE_CONSTRAINTS);
    }

}
