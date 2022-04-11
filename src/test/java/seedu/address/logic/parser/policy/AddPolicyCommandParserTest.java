package seedu.address.logic.parser.policy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POLICY_MANAGER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PREMIUM_DESC;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_MANAGER_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_MANAGER_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.PREMIUM_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.PREMIUM_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_MANAGER_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREMIUM_INSURANCE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.policy.AddPolicyCommand;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.testutil.PolicyBuilder;

public class AddPolicyCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPolicyCommand.MESSAGE_USAGE);

    private AddPolicyCommandParser parser = new AddPolicyCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, POLICY_NAME_DESC_INSURANCE, MESSAGE_INVALID_FORMAT);

        // no field specified - need to change error msg
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + POLICY_NAME_DESC_INSURANCE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + POLICY_NAME_DESC_INSURANCE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // only valid name
        assertParseFailure(parser, "1" + POLICY_NAME_DESC_INSURANCE + INVALID_COMPANY_DESC
                + INVALID_POLICY_MANAGER_DESC + INVALID_PREMIUM_DESC, Name.MESSAGE_CONSTRAINTS);

        // only valid company
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + COMPANY_DESC_INSURANCE
                + INVALID_POLICY_MANAGER_DESC + INVALID_PREMIUM_DESC, Name.MESSAGE_CONSTRAINTS);

        // only valid policy_manager
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_COMPANY_DESC
                + POLICY_MANAGER_DESC_INSURANCE + INVALID_PREMIUM_DESC, Name.MESSAGE_CONSTRAINTS);

        // only valid premium
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_COMPANY_DESC
                + INVALID_POLICY_MANAGER_DESC + PREMIUM_DESC_INSURANCE, Name.MESSAGE_CONSTRAINTS);

        // all values invalid
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_COMPANY_DESC
                + INVALID_POLICY_MANAGER_DESC + INVALID_PREMIUM_DESC, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInput = targetIndex.getOneBased() + POLICY_NAME_DESC_INSURANCE
                + COMPANY_DESC_INSURANCE + POLICY_MANAGER_DESC_INSURANCE + PREMIUM_DESC_INSURANCE;

        Policy policyToAdd = new PolicyBuilder()
                .withName(VALID_POLICY_NAME_INSURANCE)
                .withCompany(VALID_COMPANY_INSURANCE)
                .withPolicyManager(VALID_POLICY_MANAGER_INSURANCE)
                .withPremium(VALID_PREMIUM_INSURANCE)
                .build();

        AddPolicyCommand expectedCommand = new AddPolicyCommand(targetIndex, policyToAdd);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_notAllFieldsSpecified_failure() {
        Index targetIndex = INDEX_SECOND_CLIENT;
        String userInputOneField = targetIndex.getOneBased() + POLICY_NAME_DESC_INSURANCE;
        String userInputSomeFields = targetIndex.getOneBased() + POLICY_NAME_DESC_INSURANCE
                + COMPANY_DESC_INSURANCE + POLICY_MANAGER_DESC_INSURANCE;

        // only one field provided in user input
        assertParseFailure(parser, userInputOneField, MESSAGE_INVALID_FORMAT);

        // some but not all fields provided in user input
        assertParseFailure(parser, userInputSomeFields, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_CLIENT;
        String userInput =
                targetIndex.getOneBased()
                        + POLICY_NAME_DESC_INVESTMENT + COMPANY_DESC_INVESTMENT
                        + POLICY_MANAGER_DESC_INVESTMENT + PREMIUM_DESC_INVESTMENT
                        + POLICY_NAME_DESC_INSURANCE + COMPANY_DESC_INSURANCE
                        + POLICY_MANAGER_DESC_INSURANCE + PREMIUM_DESC_INSURANCE;

        Policy policyToAdd = new PolicyBuilder()
                .withName(VALID_POLICY_NAME_INSURANCE)
                .withCompany(VALID_COMPANY_INSURANCE)
                .withPolicyManager(VALID_POLICY_MANAGER_INSURANCE)
                .withPremium(VALID_PREMIUM_INSURANCE)
                .build();
        AddPolicyCommand expectedCommand = new AddPolicyCommand(targetIndex, policyToAdd);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
