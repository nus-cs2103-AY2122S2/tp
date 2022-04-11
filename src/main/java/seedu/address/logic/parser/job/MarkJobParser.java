package seedu.address.logic.parser.job;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_JOB_MARK_STATUS;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBSTATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.job.MarkJob;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.JobStatus;

public class MarkJobParser implements Parser<MarkJob> {

    @Override
    public MarkJob parse(String userInput) throws ParseException {
        requireAllNonNull(userInput);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput,
                PREFIX_JOBSTATUS);

        if (!argMultimap.getValue(PREFIX_JOBSTATUS).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkJob.MESSAGE_USAGE));
        }

        Index index;
        JobStatus jobStatus;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_INDEX, MarkJob.MESSAGE_USAGE), e);
        }

        try {
            jobStatus = ParserUtil.parseJobStatus(argMultimap.getValue(PREFIX_JOBSTATUS).get());
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_JOB_MARK_STATUS, MarkJob.MESSAGE_USAGE), e);
        }

        return new MarkJob(index, jobStatus);
    }
}
