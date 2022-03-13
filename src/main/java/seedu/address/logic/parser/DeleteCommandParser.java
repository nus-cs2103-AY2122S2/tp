package seedu.address.logic.parser;

import static seedu.address.commons.core.TypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.TypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.TypeFlags.FLAG_POSITION;

import seedu.address.logic.commands.delete.DeleteCommand;
import seedu.address.logic.parser.applicants.DeleteApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

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
            // calls delete interview command parser
        } else if (flag == FLAG_POSITION) {
            // calls delete position command parser
        }

        return null;
    }

}
