package seedu.address.logic.parser;

import static seedu.address.commons.core.DataTypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.DataTypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.DataTypeFlags.FLAG_POSITION;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.applicants.DeleteApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.interview.DeleteInterviewCommandParser;
import seedu.address.logic.parser.position.DeletePositionCommandParser;

/**
 * Parses delete command and calls the respective DeleteXCommandParsers according to the flag.
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteApplicantCommand,
     * calls the respective DeleteXCommandParsers according to the flag specified
     * and returns an DeleteApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        char flag = ArgumentTokenizer.getFlag(args.trim());
        String argsWithoutFlag = ArgumentTokenizer.removeFlag(args.trim());

        if (flag == FLAG_APPLICANT) {
            return new DeleteApplicantCommandParser().parse(argsWithoutFlag);
        } else if (flag == FLAG_INTERVIEW) {
            return new DeleteInterviewCommandParser().parse(argsWithoutFlag);
        } else if (flag == FLAG_POSITION) {
            return new DeletePositionCommandParser().parse(argsWithoutFlag);
        }

        return null;
    }

}
