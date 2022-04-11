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
import unibook.testutil.builders.StudentBuilder;

public class AddCommandParserTest {
    public static final String DEFAULT_NAME = "James";
    public static final String DEFAULT_PHONE = "91893940";
    public static final String DEFAULT_EMAIL = "bond@m16.com";

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new StudentBuilder().build();
        // whitespace only preamble
        assertParseSuccess(parser,
            CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.OPTION_DESC_STUDENT
                    + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                    + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + CommandTestUtil.NAME_DESC_AMY
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + " " + CliSyntax.PREFIX_NAME
                + DEFAULT_NAME + CommandTestUtil.PHONE_DESC_AMY + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + " " + CliSyntax.PREFIX_NAME
                + DEFAULT_NAME
                + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE + CommandTestUtil.EMAIL_DESC_AMY
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags =
                new StudentBuilder().withTags("friend", "smart").build();
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT + " " + CliSyntax.PREFIX_NAME
                        + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE + " "
                        + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL + " " + CliSyntax.PREFIX_TAG + "friend" + " "
                        + CliSyntax.PREFIX_TAG + "smart",
                new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new StudentBuilder().withTags().build();
        assertParseSuccess(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing option prefix
        assertParseFailure(parser, CommandTestUtil.VALID_OPTION_STUDENT
                        + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                        + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL,
                expectedMessage);

        // missing name prefix
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                        + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                        + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL,
                AddCommandParser.MESSAGE_CONSTRAINTS_OPTION);

        // all prefixes missing
        assertParseFailure(parser, CommandTestUtil.VALID_OPTION_STUDENT
                        + DEFAULT_NAME + DEFAULT_PHONE + DEFAULT_EMAIL,
            expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid option
        assertParseFailure(parser, CommandTestUtil.INVALID_OPTION_DESC
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL,
                AddCommandParser.MESSAGE_CONSTRAINTS_OPTION);

        // invalid name
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.INVALID_NAME_DESC + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                        + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + CommandTestUtil.INVALID_PHONE_DESC + " "
                + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + CommandTestUtil.INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL
                + CommandTestUtil.INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, CommandTestUtil.OPTION_DESC_STUDENT
                + CommandTestUtil.INVALID_NAME_DESC + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                + CommandTestUtil.INVALID_EMAIL_DESC, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
            CommandTestUtil.PREAMBLE_NON_EMPTY + CommandTestUtil.OPTION_DESC_STUDENT
                    + " " + CliSyntax.PREFIX_NAME + DEFAULT_NAME + " " + CliSyntax.PREFIX_PHONE + DEFAULT_PHONE
                    + " " + CliSyntax.PREFIX_EMAIL + DEFAULT_EMAIL,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
