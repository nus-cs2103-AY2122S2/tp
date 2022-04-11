package seedu.address.logic.parser.job;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.job.DeleteJob;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteJobParser implements Parser<DeleteJob> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteJob command
     * and returns a DeleteJob object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteJob parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteJob(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(pe.getMessage(), DeleteJob.MESSAGE_USAGE), pe);
        }
    }
}

