package seedu.address.logic.parser.job;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_JOB_MARK_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.job.MarkJob;
import seedu.address.model.job.JobStatus;
import seedu.address.testutil.TypicalIndexes;

public class MarkJobParserTest {

    private MarkJobParser parser = new MarkJobParser();

    @Test
    void parse_validArgs_returnsMarkJob() {

        Index firstTargetIndex = TypicalIndexes.INDEX_THIRD_JOB;
        Index secondTargetIndex = TypicalIndexes.INDEX_FIRST_JOB;

        String firstUserInput = firstTargetIndex.getOneBased() + CommandTestUtil.VALID_INPUT_JOB_STATUS_FILLED;
        String secondUserInput = secondTargetIndex.getOneBased() + CommandTestUtil.VALID_INPUT_JOB_STATUS_VACANT;

        JobStatus firstStatus = new JobStatus(CommandTestUtil.VALID_JOB_STATUS_FILLED);
        JobStatus secondStatus = new JobStatus(CommandTestUtil.VALID_JOB_STATUS_VACANT);

        // Valid job status filled
        assertParseSuccess(parser, firstUserInput, new MarkJob(firstTargetIndex, firstStatus));

        // Valid job status vacant
        assertParseSuccess(parser, secondUserInput, new MarkJob(secondTargetIndex, secondStatus));

    }

    @Test
    void parse_invalidStatus_throwsParseException() {

        // invalid job status prefix
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_INPUT_JOB_STATUS_FILLED,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkJob.MESSAGE_USAGE));

        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_INPUT_JOB_STATUS_VACANT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkJob.MESSAGE_USAGE));

        // invalid job status
        assertParseFailure(parser, "1" + CommandTestUtil.INVALID_INPUT_JOB_STATUS_DECIDING,
                String.format(MESSAGE_INVALID_JOB_MARK_STATUS, MarkJob.MESSAGE_USAGE));

        // invalid index
        assertParseFailure(parser, "-1" + CommandTestUtil.VALID_INPUT_JOB_STATUS_VACANT,
                String.format(MESSAGE_INVALID_INDEX, MarkJob.MESSAGE_USAGE));
    }

    @Test
    void parse_invalidArgs_throwsParseException() {

        // invalid argument
        assertParseFailure(parser, "invalid argument", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkJob.MESSAGE_USAGE));

        // empty argument
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkJob.MESSAGE_USAGE));

    }
}
