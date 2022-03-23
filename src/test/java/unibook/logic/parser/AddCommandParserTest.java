package unibook.logic.parser;

import static unibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import unibook.logic.commands.AddCommand;
import unibook.logic.commands.CommandTestUtil;
import unibook.model.person.Email;
import unibook.model.person.Name;
import unibook.model.person.Person;
import unibook.model.person.Phone;
import unibook.model.tag.Tag;
import unibook.testutil.PersonBuilder;
import unibook.testutil.TypicalPersons;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();
    /*
    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson =
            new PersonBuilder(TypicalPersons.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser,
            CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.OPTION_DESC_STUDENT
                    + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                    + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_AMY
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_BOB
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser,
                CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                        + CommandTestUtil.TAG_DESC_FRIEND, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags =
                new PersonBuilder(TypicalPersons.BOB).withTags(CommandTestUtil.VALID_TAG_FRIEND,
                    CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                        + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(TypicalPersons.AMY).withTags().build();
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT
                        + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.PHONE_DESC_AMY
                        + CommandTestUtil.EMAIL_DESC_AMY, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
        String expectedMessageStudent = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE_STUDENT);

        // missing option prefix
        assertParseFailure(parser, CommandTestUtil.VALID_OPTION_STUDENT
                        + CommandTestUtil.NAME_DESC_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                        + CommandTestUtil.VALID_NAME_BOB
                        + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB,
            expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                        + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.VALID_PHONE_BOB
                        + CommandTestUtil.EMAIL_DESC_BOB, expectedMessageStudent);

        // missing email prefix
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                        + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                        + CommandTestUtil.VALID_EMAIL_BOB, expectedMessageStudent);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_OPTION_STUDENT
                + CommandTestUtil.VALID_NAME_BOB + CommandTestUtil.VALID_PHONE_BOB + CommandTestUtil.VALID_EMAIL_BOB,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid option
        assertParseFailure(parser, CommandTestUtil.INVALID_OPTION_DESC
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND
                + CommandTestUtil.TAG_DESC_FRIEND, AddCommandParser.MESSAGE_CONSTRAINTS_OPTION);

        // invalid name
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.INVALID_TAG_DESC + CommandTestUtil.VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.INVALID_NAME_DESC + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB,
            Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.OPTION_DESC_STUDENT
                    + CommandTestUtil.NAME_DESC_BOB + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.TAG_DESC_FRIEND,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
    */
}
