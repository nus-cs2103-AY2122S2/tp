package seedu.address.logic.parser.policy;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_INDEX_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_INDEX_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_MANAGER_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_MANAGER_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.POLICY_NAME_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.PREMIUM_DESC_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.PREMIUM_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_MANAGER_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_MANAGER_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NAME_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREMIUM_INSURANCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PREMIUM_INVESTMENT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.policy.EditPolicyCommand;
import seedu.address.logic.commands.policy.EditPolicyCommand.EditPolicyDescriptor;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;
import seedu.address.testutil.PolicyBuilder;

public class EditPolicyParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPolicyCommand.MESSAGE_USAGE);

    private EditPolicyCommandParser parser = new EditPolicyCommandParser();

    private Policy insurancePolicy = new PolicyBuilder()
            .withName(VALID_POLICY_NAME_INSURANCE)
            .withCompany(VALID_COMPANY_INSURANCE)
            .withPolicyManager(VALID_POLICY_MANAGER_INSURANCE)
            .withPremium(VALID_PREMIUM_INSURANCE)
            .build();
    private EditPolicyDescriptor insurancePolicyDescriptor = new EditPolicyDescriptor(insurancePolicy);

    private Policy investmentPolicy = new PolicyBuilder()
            .withName(VALID_POLICY_NAME_INVESTMENT)
            .withCompany(VALID_COMPANY_INVESTMENT)
            .withPolicyManager(VALID_POLICY_MANAGER_INVESTMENT)
            .withPremium(VALID_PREMIUM_INVESTMENT)
            .build();
    private EditPolicyDescriptor investmentPolicyDescriptor = new EditPolicyDescriptor(investmentPolicy);


    @Test
    public void parse_noFieldsSpecified_failure() {
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
    public void parse_oneValidDescriptiveField_success() {
        // only valid name
        EditPolicyDescriptor editNamePolicyDescriptor = new EditPolicyDescriptor();
        editNamePolicyDescriptor.setName(new Name(VALID_POLICY_NAME_INVESTMENT));
        EditPolicyCommand expectedNameCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editNamePolicyDescriptor);
        assertParseSuccess(parser,
                "1" + POLICY_INDEX_DESC_INSURANCE + POLICY_NAME_DESC_INVESTMENT, expectedNameCommand);

        // only valid company
        EditPolicyDescriptor editCompanyPolicyDescriptor = new EditPolicyDescriptor();
        editCompanyPolicyDescriptor.setCompany(new Name(VALID_COMPANY_INVESTMENT));
        EditPolicyCommand expectedCompanyCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editCompanyPolicyDescriptor);
        assertParseSuccess(parser, "1" + POLICY_INDEX_DESC_INSURANCE + COMPANY_DESC_INVESTMENT,
                expectedCompanyCommand);

        // only valid policy_manager
        EditPolicyDescriptor editManagerPolicyDescriptor = new EditPolicyDescriptor();
        editManagerPolicyDescriptor.setPolicyManager(new Name(VALID_POLICY_MANAGER_INVESTMENT));
        EditPolicyCommand expectedManagerCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editManagerPolicyDescriptor);
        assertParseSuccess(parser, "1" + POLICY_INDEX_DESC_INSURANCE + POLICY_MANAGER_DESC_INVESTMENT,
                expectedManagerCommand);

        // only valid premium
        EditPolicyDescriptor editPremiumPolicyDescriptor = new EditPolicyDescriptor();
        editPremiumPolicyDescriptor.setPremium(new Premium(VALID_PREMIUM_INVESTMENT));
        EditPolicyCommand expectedPremiumCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                editPremiumPolicyDescriptor);
        assertParseSuccess(parser, "1" + POLICY_INDEX_DESC_INSURANCE + PREMIUM_DESC_INVESTMENT,
                expectedPremiumCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String userInput = INDEX_FIRST_CLIENT.getOneBased() + POLICY_INDEX_DESC_INSURANCE + POLICY_NAME_DESC_INSURANCE
                + COMPANY_DESC_INSURANCE + POLICY_MANAGER_DESC_INSURANCE + PREMIUM_DESC_INSURANCE;

        EditPolicyCommand expectedCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_FIRST_CLIENT,
                insurancePolicyDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        String userInput =
                INDEX_FIRST_CLIENT.getOneBased()
                        + POLICY_INDEX_DESC_INSURANCE + POLICY_INDEX_DESC_INVESTMENT
                        + POLICY_NAME_DESC_INSURANCE + POLICY_NAME_DESC_INVESTMENT
                        + COMPANY_DESC_INSURANCE + COMPANY_DESC_INVESTMENT
                        + POLICY_MANAGER_DESC_INSURANCE + POLICY_MANAGER_DESC_INVESTMENT
                        + PREMIUM_DESC_INSURANCE + PREMIUM_DESC_INVESTMENT;

        EditPolicyCommand expectedCommand = new EditPolicyCommand(INDEX_FIRST_CLIENT, INDEX_SECOND_CLIENT,
                investmentPolicyDescriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
