package seedu.address.logic.parser;

import static seedu.address.commons.core.DataTypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.DataTypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.DataTypeFlags.FLAG_POSITION;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.applicants.AddApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.interview.AddInterviewCommandParser;
import seedu.address.logic.parser.position.AddPositionCommandParser;

/**
 * Parses add command and calls the respective AddXCommandParsers according to the flag.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicantCommand,
     * calls the respective AddXCommandParsers according to the flag specified
     * and returns an AddApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        char flag = ArgumentTokenizer.getFlag(args.trim());
        String argsWithoutFlag = ArgumentTokenizer.removeFlag(args.trim());

        if (flag == FLAG_APPLICANT) {
            return new AddApplicantCommandParser().parse(argsWithoutFlag);
        } else if (flag == FLAG_INTERVIEW) {
            return new AddInterviewCommandParser().parse(argsWithoutFlag);
        } else if (flag == FLAG_POSITION) {
            return new AddPositionCommandParser().parse(argsWithoutFlag);
        }

        return null;
    }
}
