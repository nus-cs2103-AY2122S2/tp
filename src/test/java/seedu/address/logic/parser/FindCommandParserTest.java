package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.application.JobTitle;
import seedu.address.model.application.JobTitleContainsKeywordsPredicate;
import seedu.address.model.application.Name;
import seedu.address.model.application.NameContainsKeywordsPredicate;
import seedu.address.model.application.TagContainsKeywordsPredicate;
import seedu.address.model.tag.ApplicationStatusTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.model.tag.Tag;


public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Grab")));
        FindCommand expectedFindJobTitleCommand =
                new FindCommand(new JobTitleContainsKeywordsPredicate(Arrays.asList("SWE")));
        FindCommand expectedFindTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("overseas")));
        FindCommand expectedFindPriorityTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("HIGH")));
        FindCommand expectedFindApplicationStatusTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("APPLIED")));

        String[] nameArray = new String[]{"Grab", "Shopee"};
        FindCommand expectedFindCommandWithTwoNamesKeyword =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameArray)));

        String[] jobTitleArray = new String[]{"Software", "Engineering"};
        FindCommand expectedFindCommandWithTwoJobTitleKeyword =
                new FindCommand(new JobTitleContainsKeywordsPredicate(Arrays.asList(jobTitleArray)));


        // single field, single parameter
        assertParseSuccess(parser, "find n/Grab", expectedFindCommand);

        // multiple parameters for same keywords, last taken
        assertParseSuccess(parser, " find n/Lazada n/Grab", expectedFindCommand);

        // multiple different keywords, first taken in the sequence of n/ j/ t/ pt/ ast/ (check n/ taken)
        assertParseSuccess(parser, " find ast/APPLIED pt/HIGH t/overseas j/SWE n/Grab", expectedFindCommand);

        // multiple different keywords, first taken in the sequence of n/ j/ t/ pt/ ast/ (check j/ taken)
        assertParseSuccess(parser, " find ast/APPLIED pt/HIGH t/overseas j/SWE", expectedFindJobTitleCommand);

        // multiple different keywords, first taken in the sequence of n/ j/ t/ pt/ ast/ (check t/ taken)
        assertParseSuccess(parser, " find ast/APPLIED pt/HIGH t/overseas", expectedFindTagCommand);

        // multiple different keywords, first taken in the sequence of n/ j/ t/ pt/ ast/ (check pt/ taken)
        assertParseSuccess(parser, " find ast/APPLIED pt/HIGH", expectedFindPriorityTagCommand);

        // multiple different keywords, first taken in the sequence of n/ j/ t/ pt/ ast/ (check ast/ taken)
        assertParseSuccess(parser, " find ast/APPLIED", expectedFindApplicationStatusTagCommand);

        // one n/ field, multiple keywords (has spaces)
        assertParseSuccess(parser, " find n/Grab Shopee", expectedFindCommandWithTwoNamesKeyword);

        // one j/ field, multiple keywords (has spaces)
        assertParseSuccess(parser, " find j/Software Engineering", expectedFindCommandWithTwoJobTitleKeyword);
    }

    @Test
    public void parse_invalidValue_failure() {
    }

    @Test
    void checkNotEmptyName() {
        assertParseFailure(parser, "find n/", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find n/ ", Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    void checkNotEmptyJobTitle() {
        assertParseFailure(parser, "find j/", JobTitle.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find j/ ", JobTitle.MESSAGE_CONSTRAINTS);
    }

    @Test
    void checkNotEmptyApplicationStatusTag() {
        assertParseFailure(parser, "find ast/", ApplicationStatusTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find ast/ ", ApplicationStatusTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find ast/notapplicationstatustagtype",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_APPLICATION_STATUS_TAG));
    }

    @Test
    void checkNotEmptyPriorityTag() {
        assertParseFailure(parser, "find pt/", PriorityTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find pt/ ", PriorityTag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find pt/notprioritytagtype",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_PRIORITY_TAG));

    }

    @Test
    void checkNotEmptyTags() {
        assertParseFailure(parser, "find t/", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find t/ ", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "find t/two tags", Tag.MESSAGE_CONSTRAINTS);
    }
}
