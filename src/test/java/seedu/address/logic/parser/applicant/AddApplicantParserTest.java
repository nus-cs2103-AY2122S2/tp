package seedu.address.logic.parser.applicant;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DATEAPPLIED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INTERVIEWDATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NRIC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC;
import static seedu.address.logic.commands.applicant.AddApplicant.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.commands.applicant.AddApplicant.MESSAGE_USAGE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.applicant.AddApplicant;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.Nric;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

class AddApplicantParserTest {

    private AddApplicantParser parser = new AddApplicantParser();

    @Test
    void parse_allCompulsoryFieldsPresent_success() {
        String arg = " n/John Doe p/98765432 e/johnd@example.com a/311, Clementi Ave 2 "
                + " d/2022-12-12 nric/S1234567D ";
        Set<Tag> tagSet = new HashSet<Tag>();

        Applicant app = new Applicant(new Name("John Doe"), new Phone("98765432"), new Email("johnd@example.com"),
                new Address("311, Clementi Ave 2"), tagSet, new DateApplied("2022-12-12"),
                new Nric("S1234567D")
        );
        assertParseSuccess(parser, arg, new AddApplicant(app));
    }

    @Test
    void parse_missingField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);

        //Missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC + DATEAPPLIED_DESC, expectedMessage);

        //Missing nric prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_NRIC + DATEAPPLIED_DESC, expectedMessage);

        //Missing dateApplied prefix
        assertParseFailure(parser, NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + NRIC_DESC + VALID_DATE, expectedMessage);

    }

    @Test
    void parse_extraField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_FIELDS + "\n" + MESSAGE_USAGE);

        //Contains InterviewDate
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC + DATEAPPLIED_DESC + INTERVIEWDATE_DESC, expectedMessage);
        //Contains Qualification
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC + DATEAPPLIED_DESC + QUALIFICATION_DESC, expectedMessage);
        //Contains JobId
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + NRIC_DESC + DATEAPPLIED_DESC + JOB_DESC, expectedMessage);
    }
}
