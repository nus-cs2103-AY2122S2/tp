package seedu.address.logic.parser;

import static seedu.address.commons.core.DataTypeFlags.FLAG_APPLICANT;
import static seedu.address.commons.core.DataTypeFlags.FLAG_INTERVIEW;
import static seedu.address.commons.core.DataTypeFlags.FLAG_POSITION;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.applicants.EditApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.position.EditPositionCommandParser;

/**
 * Parses edit command and calls the respective EditXCommandParsers according to the flag.
 */
public class EditCommandParser implements Parser<EditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddApplicantCommand,
     * calls the respective AddXCommandParsers according to the flag specified
     * and returns an AddApplicantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        char flag = ArgumentTokenizer.getFlag(args.trim());
        String argsWithoutFlag = ArgumentTokenizer.removeFlag(args.trim());

        if (flag == FLAG_APPLICANT) {
            return new EditApplicantCommandParser().parse(argsWithoutFlag);
        } else if (flag == FLAG_INTERVIEW) {
            // TO ADD: EDIT INTERVIEW PARSER
        } else if (flag == FLAG_POSITION) {
            return new EditPositionCommandParser().parse(argsWithoutFlag);
        }
        return null;
    }
}
