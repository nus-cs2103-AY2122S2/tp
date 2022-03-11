package seedu.address.logic.parser;

import static seedu.address.commons.core.TypeFlags.FLAG_APPLICANT;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.applicants.AddApplicantCommandParser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses add command and calls the respective AddXCommandParsers according to the flag.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand,
     * calls the respective AddXCommandParsers according to the flag specified
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        char flag = ArgumentTokenizer.getFlag(args.trim());
        String args_without_flag = ArgumentTokenizer.removeFlag(args.trim());

        if (flag == FLAG_APPLICANT) {
            return new AddApplicantCommandParser().parse(args_without_flag);
        }

        return null;
    }

}