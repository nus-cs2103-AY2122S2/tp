package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSURANCE_PACKAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PACKAGE_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPackageCommand;
import seedu.address.model.person.InsurancePackage;

public class AddPackageCommandParserTest {

    public static final String VALID_PACKAGE_NAME = "Test Package Name";
    public static final String VALID_PACKAGE_DESC = "Test Package Desc";

    private AddPackageCommandParser parser = new AddPackageCommandParser();

    private InsurancePackage expectedPackage = new InsurancePackage(VALID_PACKAGE_NAME, VALID_PACKAGE_DESC);

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, " " + PREFIX_INSURANCE_PACKAGE + VALID_PACKAGE_NAME
                + " " + PREFIX_PACKAGE_DESC + VALID_PACKAGE_DESC, new AddPackageCommand(expectedPackage));

    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPackageCommand.MESSAGE_USAGE);

        // missing description prefix
        assertParseFailure(parser, " " + PREFIX_INSURANCE_PACKAGE + VALID_PACKAGE_NAME, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, " " + PREFIX_PACKAGE_DESC + VALID_PACKAGE_DESC, expectedMessage);

    }

}
