package seedu.address.logic.parser;

import static seedu.address.commons.core.TypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.TypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.TypeFlags.FLAG_POSITION;

import seedu.address.logic.commands.add.AddCommand;
import seedu.address.logic.parser.applicants.AddApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

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
            // calls add interview command parser
        } else if (flag == FLAG_POSITION) {
            // calls add position command parser
        }

        return null;
    }
}
