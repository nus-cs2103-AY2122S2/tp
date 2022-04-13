package seedu.tinner.logic.parser;

import static seedu.tinner.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tinner.commons.core.Messages.MESSAGE_NO_VALUE_AFTER_PREFIX;
import static seedu.tinner.logic.commands.CommandTestUtil.DESCRIPTION_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.INDEX_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.INVALID_INDEX_DESC;
import static seedu.tinner.logic.commands.CommandTestUtil.INVALID_REMINDER_DATE_DESC;
import static seedu.tinner.logic.commands.CommandTestUtil.INVALID_ROLE_NAME_DESC;
import static seedu.tinner.logic.commands.CommandTestUtil.INVALID_STATUS_DESC;
import static seedu.tinner.logic.commands.CommandTestUtil.INVALID_STIPEND_DESC;
import static seedu.tinner.logic.commands.CommandTestUtil.NAME_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.REMINDER_DATE_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.STATUS_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.STIPEND_DESC_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_NAME_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.commands.CommandTestUtil.VALID_STATUS_SOFTWARE_ENGINEER;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.tinner.logic.parser.CliSyntax.PREFIX_STIPEND;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tinner.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tinner.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.tinner.testutil.TypicalRoles.SOFTWARE_ENGINEER;

import org.junit.jupiter.api.Test;

import seedu.tinner.logic.commands.AddRoleCommand;
import seedu.tinner.model.role.ReminderDate;
import seedu.tinner.model.role.Role;
import seedu.tinner.model.role.RoleName;
import seedu.tinner.model.role.Status;
import seedu.tinner.model.role.Stipend;
import seedu.tinner.testutil.RoleBuilder;

public class AddRoleCommandParserTest {
    private AddRoleCommandParser parser = new AddRoleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Role expectedRole = new RoleBuilder(SOFTWARE_ENGINEER).build();
        assertParseSuccess(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                        + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                        + DESCRIPTION_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER,
                new AddRoleCommand(INDEX_FIRST_COMPANY, expectedRole));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no stipend
        Role expectedRole = new RoleBuilder(SOFTWARE_ENGINEER).withoutStipend().build();
        assertParseSuccess(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                        + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                        + DESCRIPTION_DESC_SOFTWARE_ENGINEER,
                new AddRoleCommand(INDEX_FIRST_COMPANY, expectedRole));

        // no description
        expectedRole = new RoleBuilder(SOFTWARE_ENGINEER).withoutDescription().build();
        assertParseSuccess(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                        + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                        + STIPEND_DESC_SOFTWARE_ENGINEER,
                new AddRoleCommand(INDEX_FIRST_COMPANY, expectedRole));

        // no description and stipend
        expectedRole = new RoleBuilder(SOFTWARE_ENGINEER).withoutDescription().withoutStipend().build();
        assertParseSuccess(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                        + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER,
                new AddRoleCommand(INDEX_FIRST_COMPANY, expectedRole));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoleCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, NAME_DESC_SOFTWARE_ENGINEER + STATUS_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER, expectedMessage);

        // missing name field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + STATUS_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER, expectedMessage);

        // missing status field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_SOFTWARE_ENGINEER + VALID_STATUS_SOFTWARE_ENGINEER
                + VALID_DESCRIPTION_SOFTWARE_ENGINEER, expectedMessage);
    }

    @Test
    public void parse_prefixWithoutValue_failure() {
        String expectedMessage = String.format(MESSAGE_NO_VALUE_AFTER_PREFIX, AddRoleCommand.MESSAGE_USAGE);

        // missing value for description field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER
                + " " + PREFIX_DESCRIPTION, expectedMessage);

        // missing value for reminder date field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + STIPEND_DESC_SOFTWARE_ENGINEER + " " + PREFIX_REMINDER_DATE, expectedMessage);

        // missing value for stipend field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER + " " + PREFIX_STIPEND, expectedMessage);

        // missing value for name field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER + " " + PREFIX_NAME, expectedMessage);

        // missing value for status field
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER
                + NAME_DESC_SOFTWARE_ENGINEER + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + REMINDER_DATE_DESC_SOFTWARE_ENGINEER + " " + PREFIX_STATUS, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, INVALID_INDEX_DESC + NAME_DESC_SOFTWARE_ENGINEER
                        + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                        + DESCRIPTION_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRoleCommand.MESSAGE_USAGE));

        // invalid name
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + INVALID_ROLE_NAME_DESC
                + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                + DESCRIPTION_DESC_SOFTWARE_ENGINEER + STIPEND_DESC_SOFTWARE_ENGINEER, RoleName.MESSAGE_CONSTRAINTS);

        // invalid status
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + INVALID_STATUS_DESC + REMINDER_DATE_DESC_SOFTWARE_ENGINEER + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + STIPEND_DESC_SOFTWARE_ENGINEER, Status.MESSAGE_CONSTRAINTS);

        // invalid reminder date
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + INVALID_REMINDER_DATE_DESC + DESCRIPTION_DESC_SOFTWARE_ENGINEER
                + STIPEND_DESC_SOFTWARE_ENGINEER, ReminderDate.MESSAGE_CONSTRAINTS);

        // invalid stipend
        assertParseFailure(parser, INDEX_DESC_SOFTWARE_ENGINEER + NAME_DESC_SOFTWARE_ENGINEER
                + STATUS_DESC_SOFTWARE_ENGINEER + REMINDER_DATE_DESC_SOFTWARE_ENGINEER
                + DESCRIPTION_DESC_SOFTWARE_ENGINEER + INVALID_STIPEND_DESC, Stipend.MESSAGE_CONSTRAINTS);
    }
}
