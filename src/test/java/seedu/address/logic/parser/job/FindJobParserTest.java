package seedu.address.logic.parser.job;



import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.JOBID_DESC_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_DATA_ANALYSIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_PROJECT_MANAGER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_DATA_ANALYSIS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.job.FindJob;
import seedu.address.model.job.JobIdSamePredicate;
import seedu.address.model.job.NameJobContainsKeywordsPredicate;

class FindJobParserTest {

    private FindJobParser parser = new FindJobParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsJobTitle_returnsJob() {
        String trimmedKeyword = VALID_JOB_TITLE_DATA_ANALYSIS;
        String[] jobTitleKeywords = trimmedKeyword.split("\\s+");

        // no leading and trailing whitespaces
        FindJob expectedJob =
                new FindJob(new NameJobContainsKeywordsPredicate(Arrays.asList(jobTitleKeywords)));
        assertParseSuccess(parser, JOB_TITLE_DESC_DATA_ANALYSIS, expectedJob);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " jt/ \n Data Analyst  \t", expectedJob);
    }

    @Test
    public void parse_validArgsID_returnsJob() {
        // no leading and trailing whitespaces
        FindJob expectedJob =
                new FindJob(new JobIdSamePredicate(VALID_JOB_ID_PROJECT_MANAGER));
        assertParseSuccess(parser, JOBID_DESC_PROJECT_MANAGER, expectedJob);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " id/ \n 2  \t", expectedJob);

    }

}
