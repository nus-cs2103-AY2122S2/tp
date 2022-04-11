package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.GROUP_DESC_NUS_FINTECH_SOCIETY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.group.GroupName.MESSAGE_CONSTRAINTS;
import static seedu.address.testutil.TypicalGroups.NUS_FINTECH_SOCIETY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.model.group.Group;
import seedu.address.testutil.GroupBuilder;

public class DeleteGroupCommandParserTest {

    private DeleteGroupCommandParser parser = new DeleteGroupCommandParser();

    private Group expectedGroup = new GroupBuilder(NUS_FINTECH_SOCIETY).build();

    @Test
    public void parse_validArgs_returnsDeleteGroupCommand() {
        assertParseSuccess(parser, GROUP_DESC_NUS_FINTECH_SOCIETY, new DeleteGroupCommand(expectedGroup));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " " + PREFIX_GROUP_NAME, MESSAGE_CONSTRAINTS);
    }
}
