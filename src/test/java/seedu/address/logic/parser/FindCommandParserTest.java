package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.keyword.Keyword;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.tag.TagContainsKeywordsPredicate;
import seedu.address.model.task.DeadlineInRangePredicate;
import seedu.address.model.task.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // both name and tag keywords specified
        FindCommand expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Set.of(new Keyword("Tutorial"), new Keyword("Midterm"))),
                new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(new Keyword("CS2103T"),
                        new Keyword("Test")))),
                new DeadlineInRangePredicate(null, null));

        // name and tag keywords specified, no leading and trailing whitespaces
        assertParseSuccess(parser, " " + PREFIX_NAME + " Tutorial " + PREFIX_NAME + " Midterm"
                + TAG_DESC_CS2103T + TAG_DESC_TEST, expectedFindCommand);

        // name and tag keywords specified, multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + " \n Tutorial \n" + PREFIX_NAME + " \t Midterm \t"
                + TAG_DESC_CS2103T + "\n\t" + TAG_DESC_TEST, expectedFindCommand);


        // only name keywords
        expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Set.of(new Keyword("Tutorial"), new Keyword("Midterm"))),
                new TagContainsKeywordsPredicate(new HashSet<>()),
                new DeadlineInRangePredicate(null, null));

        assertParseSuccess(parser, " n/Tutorial n/Midterm", expectedFindCommand);


        // only tag keywords
        expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(new HashSet<>()),
                new TagContainsKeywordsPredicate(new HashSet<>(Arrays.asList(new Keyword("CS2103T"),
                        new Keyword("Test")))),
                new DeadlineInRangePredicate(null, null));

        assertParseSuccess(parser, TAG_DESC_CS2103T + TAG_DESC_TEST, expectedFindCommand);


        // name keywords and empty tag
        expectedFindCommand = new FindCommand(
                new NameContainsKeywordsPredicate(Set.of(new Keyword("Tutorial"), new Keyword("Midterm"))),
                new TagContainsKeywordsPredicate(new HashSet<>()),
                new DeadlineInRangePredicate(null, null));

        assertParseSuccess(parser, " n/Tutorial n/Midterm", expectedFindCommand);
    }

}
