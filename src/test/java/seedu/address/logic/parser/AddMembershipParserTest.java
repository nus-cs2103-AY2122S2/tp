package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddMembershipCommand;
import seedu.address.model.person.Membership;

public class AddMembershipParserTest {
    private AddMembershipParser parser = new AddMembershipParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Membership expectedMember = new Membership("GOLD");
        Membership expectedMemberWithDate = new Membership("GOLD", LocalDate.parse("1920-02-02"));

        // valid name
        assertParseSuccess(parser, "1 m/GOLD",
                new AddMembershipCommand(Index.fromOneBased(1), expectedMember));

        // valid name and date
        assertParseSuccess(parser, "1 m/GOLD d/1920-02-02",
                new AddMembershipCommand(Index.fromOneBased(1), expectedMemberWithDate));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1 m/-", Membership.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, "1 m/Gold d/Fail", Membership.MESSAGE_DATE_CONSTRAINTS);
    }
}
