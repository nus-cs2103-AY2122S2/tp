package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListMembersCommand;
import seedu.address.model.person.Membership;
import seedu.address.model.person.util.PersonContainsMembershipPredicate;

public class ListMembersCommandParserTest {
    private ListMembersCommandParser parser = new ListMembersCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        // valid input
        assertParseSuccess(parser, "GOLD",
                new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.Tier.GOLD)));

        // valid input
        assertParseSuccess(parser, "BRONZE",
                new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.Tier.BRONZE)));

        // valid input
        assertParseSuccess(parser, "SILVER",
                new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.Tier.SILVER)));

        // valid input
        assertParseSuccess(parser, "",
                new ListMembersCommand(new PersonContainsMembershipPredicate(Membership.Tier.ALL)));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid input
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListMembersCommand.MESSAGE_USAGE));

        // invalid input string
        assertParseFailure(parser, "hello", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ListMembersCommand.MESSAGE_USAGE));
    }
}
