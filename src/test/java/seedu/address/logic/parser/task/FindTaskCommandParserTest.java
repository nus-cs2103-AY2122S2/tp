package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.FindTaskCommand;
import seedu.address.model.name.TaskNameContainsKeywordsPredicate;
import seedu.address.model.tag.TaskTagContainsKeywordsPredicate;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        List<String> nameList = Arrays.asList("Meeting", "Dinner");
        List<String> tagList = Arrays.asList("friends", "neighbours");
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindCommand =
                new FindTaskCommand(new TaskNameContainsKeywordsPredicate(nameList),
                        new TaskTagContainsKeywordsPredicate(tagList));
        assertParseSuccess(parser, " n/Meeting n/Dinner t/friends t/neighbours", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/Meeting     n/Dinner   t/friends t/neighbours    ", expectedFindCommand);
    }

}
